package ee.carlrobert.codegpt.toolwindow;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentManagerEvent;
import com.intellij.ui.content.ContentManagerListener;
import ee.carlrobert.codegpt.client.Zhengyan.config.IdeaConfig;
import ee.carlrobert.codegpt.toolwindow.chat.ChatToolWindowPanel;
import ee.carlrobert.codegpt.toolwindow.conversations.ConversationsToolWindow;
import javax.swing.JComponent;

import ee.carlrobert.codegpt.client.Zhengyan.util.IpUtil;
import org.jetbrains.annotations.NotNull;

public class ProjectToolWindowFactory implements ToolWindowFactory, DumbAware {

  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    IdeaConfig.ideaName = ApplicationNamesInfo.getInstance().getFullProductNameWithEdition();
    IdeaConfig.ideaVersion = ApplicationInfo.getInstance().getFullVersion();
    IdeaConfig.localIp = IpUtil.getLocalIP();
//    IdeaConfig.pluginVersion = PluginManagerCore.getPlugin(PluginId.getId("SinosigGPT")).getVersion();

    var chatToolWindowPanel = new ChatToolWindowPanel(project, toolWindow.getDisposable());
    var conversationsToolWindow = new ConversationsToolWindow(project);

    addContent(toolWindow, chatToolWindowPanel, "会话");
    addContent(toolWindow, conversationsToolWindow.getContent(), "会话历史");
    toolWindow.addContentManagerListener(new ContentManagerListener() {
      public void selectionChanged(@NotNull ContentManagerEvent event) {
        var content = event.getContent();
        if ("会话历史".equals(content.getTabName()) && content.isSelected()) {
          conversationsToolWindow.refresh();
        }
      }
    });
  }

  public void addContent(ToolWindow toolWindow, JComponent panel, String displayName) {
    var contentManager = toolWindow.getContentManager();
    contentManager.addContent(contentManager.getFactory().createContent(panel, displayName, false));
  }
}
