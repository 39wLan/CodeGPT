package ee.carlrobert.codegpt.actions.editor;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import ee.carlrobert.codegpt.conversations.ConversationsState;
import ee.carlrobert.codegpt.settings.configuration.ConfigurationConfigurable;
import ee.carlrobert.codegpt.toolwindow.chat.ChatToolWindowContentManager;
import org.jetbrains.annotations.NotNull;

public class EditPromptAction extends AnAction {

  public EditPromptAction() {
    super("自定义Prompt", "自定义Prompt", AllIcons.Actions.Edit);
    EditorActionsUtil.registerAction(this);
  }


  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), ConfigurationConfigurable.class);
  }


}
