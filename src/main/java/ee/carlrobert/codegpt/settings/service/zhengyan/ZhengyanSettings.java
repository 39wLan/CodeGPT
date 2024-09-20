package ee.carlrobert.codegpt.settings.service.zhengyan;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(name = "ZhengyanSettings_210", storages = @Storage("ZhengyanSettings_210.xml"))
public class ZhengyanSettings implements PersistentStateComponent<ZhengyanSettingsState> {

  private ZhengyanSettingsState state = new ZhengyanSettingsState();

  @Override
  @NotNull
  public ZhengyanSettingsState getState() {
    return state;
  }

  @Override
  public void loadState(@NotNull ZhengyanSettingsState state) {
    this.state = state;
  }

  public static ZhengyanSettingsState getCurrentState() {
    return getInstance().getState();
  }

  public static ZhengyanSettings getInstance() {
    return ApplicationManager.getApplication().getService(ZhengyanSettings.class);
  }
}
