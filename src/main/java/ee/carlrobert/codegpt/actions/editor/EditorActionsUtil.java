package ee.carlrobert.codegpt.actions.editor;

import static java.lang.String.format;

import com.intellij.icons.AllIcons.Actions;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import ee.carlrobert.codegpt.CodeGPTKeys;
import ee.carlrobert.codegpt.ReferencedFile;
import ee.carlrobert.codegpt.actions.IncludeFilesInContextAction;
import ee.carlrobert.codegpt.conversations.message.Message;
import ee.carlrobert.codegpt.settings.configuration.ConfigurationSettings;
import ee.carlrobert.codegpt.toolwindow.chat.ChatToolWindowContentManager;
import ee.carlrobert.codegpt.util.file.FileUtil;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.text.CaseUtils;

public class EditorActionsUtil {

  public static final Map<String, String> DEFAULT_ACTIONS = new LinkedHashMap<>();

  static {
    DEFAULT_ACTIONS.put("代码解释", "请解释并分析这段代码:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("代码补全", "请按照阿里的代码规范进行代码补全，并重写代码:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("代码优化", "请针对代码风格、性能、可读性这三个方面进行优化，并重写代码:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("生成注释", "请生成这段代码的注释:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("查找bug", "请查找这段代码的bug并给出解决方案:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("单元测试", "请按照阿里的代码规范，为该代码生成单元测试:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("安全检查", "请检查代码中存在的安全问题并进行重写:\n {{selectedCode}}");
    DEFAULT_ACTIONS.put("Spring代码生成", "通过如下代码，按照Spring Boot框架及阿里开发规范生成实体类、Controller类、Service接口、Dao接口、Mapper接口、实现类以及相关的XML配置文件,并在其中实现基础的增删改查操作:\n {{selectedCode}}");
//    DEFAULT_ACTIONS.put("性能检查", "请检查代码中存在的性能问题并进行重写:\n {{selectedCode}}");
//    DEFAULT_ACTIONS.put("代码生成", "请按照阿里的代码规范，为如下描述生成Java代码:\n {{selectedCode}}");
//    DEFAULT_ACTIONS.put("风格检查", "请检查代码风格是否符合阿里的代码规范，并进行重写:\n {{selectedCode}}");
//    DEFAULT_ACTIONS.put("可读性优化", "请提高代码可读性，并按照阿里的代码规范进行重写:\n {{selectedCode}}");


  }
  public static final String[][] DEFAULT_ACTIONS_ARRAY = toArray(DEFAULT_ACTIONS);

  public static String[][] toArray(Map<String, String> actionsMap) {
    return actionsMap.entrySet()
        .stream()
        .map(entry -> new String[]{entry.getKey(), entry.getValue()})
        .toArray(String[][]::new);
  }

  public static void refreshActions() {
    AnAction actionGroup =
        ActionManager.getInstance().getAction("action.editor.group.EditorActionGroup");
    if (actionGroup instanceof DefaultActionGroup group) {
      group.removeAll();
      group.add(new AskAction());
//      group.add(new EditCodeAction(Actions.EditSource));
      group.add(new EditPromptAction());
      group.addSeparator();

      var configuredActions = ConfigurationSettings.getCurrentState().getTableData();
      configuredActions.forEach((label, prompt) -> {
        // using label as action description to prevent com.intellij.diagnostic.PluginException
        // https://github.com/carlrobertoh/CodeGPT/issues/95
        var action = new BaseEditorAction(label, label) {
          @Override
          protected void actionPerformed(Project project, Editor editor, String selectedText) {
            var fileExtension = FileUtil.getFileExtension(
                ((EditorImpl) editor).getVirtualFile().getName());
            var message = new Message(prompt.replace(
                "{{selectedCode}}",
                format("%n```%s%n%s%n```", fileExtension, selectedText)));
            message.setUserMessage(prompt.replace("{{selectedCode}}", ""));
            var toolWindowContentManager =
                project.getService(ChatToolWindowContentManager.class);
            toolWindowContentManager.getToolWindow().show();

            message.setReferencedFilePaths(
                Stream.ofNullable(project.getUserData(CodeGPTKeys.SELECTED_FILES))
                    .flatMap(Collection::stream)
                    .map(ReferencedFile::getFilePath)
                    .toList());
            if(DEFAULT_ACTIONS.containsKey(label)){
              message.setActionType(label);
            }else {
              message.setActionType("自定义Prompt");
            }
            toolWindowContentManager.sendMessage(message);
          }
        };

        group.add(action);
      });
//      group.addSeparator();
//      group.add(new IncludeFilesInContextAction("action.includeFileInContext.title"));
    }
  }

  public static void registerAction(AnAction action) {
    ActionManager actionManager = ActionManager.getInstance();
    var actionId = convertToId(action.getTemplateText());
    if (actionManager.getAction(actionId) == null) {
      actionManager.registerAction(actionId, action, PluginId.getId("ee.carlrobert.chatgpt"));
    }
  }

  public static String convertToId(String label) {
    return "codegpt." + CaseUtils.toCamelCase(label, true);
  }
}
