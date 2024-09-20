package ee.carlrobert.codegpt.statusbar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.widget.StatusBarEditorBasedWidgetFactory;
import ee.carlrobert.codegpt.CodeGPTBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CodeGPTStatusBarWidgetFactory extends StatusBarEditorBasedWidgetFactory {

  @Override
  public @NonNls @NotNull String getId() {
    return "ee.carlrobert.codegpt.statusbar.widget";
  }

  @Override
  public @Nls @NotNull String getDisplayName() {
    return "常青藤";
  }

  @Override
  public @NotNull StatusBarWidget createWidget(@NotNull Project project) {
    return new CodeGPTStatusBarWidget(project);
  }

  @Override
  public void disposeWidget(@NotNull StatusBarWidget widget) {
    widget.dispose();
  }
}
