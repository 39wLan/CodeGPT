package ee.carlrobert.codegpt.settings.service.zhengyan;

import ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel;

import java.util.Objects;

public class ZhengyanSettingsState {

  private String organization = "";
  private String model = ZhengyanModel.QWEN.getCode();
  private boolean codeCompletionsEnabled;

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public boolean isCodeCompletionsEnabled() {
    return codeCompletionsEnabled;
  }

  public void setCodeCompletionsEnabled(boolean codeCompletionsEnabled) {
    this.codeCompletionsEnabled = codeCompletionsEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZhengyanSettingsState that = (ZhengyanSettingsState) o;
    return Objects.equals(organization, that.organization)
        && Objects.equals(model, that.model)
        && codeCompletionsEnabled == that.codeCompletionsEnabled;
  }

  @Override
  public int hashCode() {
    return Objects.hash(organization, model, codeCompletionsEnabled);
  }
}
