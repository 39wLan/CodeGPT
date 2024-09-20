package ee.carlrobert.codegpt.settings.service.zhengyan;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import ee.carlrobert.codegpt.CodeGPTBundle;
import ee.carlrobert.codegpt.client.Zhengyan.config.ZhengyanModel;
import ee.carlrobert.codegpt.credentials.CredentialsStore;
import ee.carlrobert.codegpt.credentials.CredentialsStore.CredentialKey;
import ee.carlrobert.codegpt.ui.UIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class ZhengyanSettingsForm {

  private final JBPasswordField apiKeyField;
  private final JBTextField organizationField;
  private final ComboBox<ZhengyanModel> completionModelComboBox;
  private final JBCheckBox codeCompletionsEnabledCheckBox;

  public ZhengyanSettingsForm(ZhengyanSettingsState settings) {
    apiKeyField = new JBPasswordField();
    apiKeyField.setColumns(30);
    ApplicationManager.getApplication().executeOnPooledThread(() -> {
      var apiKey = CredentialsStore.getCredential(CredentialKey.ZY_API_KEY);
      SwingUtilities.invokeLater(() -> apiKeyField.setText(apiKey));
    });
    organizationField = new JBTextField(settings.getOrganization(), 30);
    completionModelComboBox = new ComboBox<>(
        new EnumComboBoxModel<>(ZhengyanModel.class));
    completionModelComboBox.setSelectedItem(
        ZhengyanModel.findByCode(settings.getModel()));
    codeCompletionsEnabledCheckBox = new JBCheckBox(
        CodeGPTBundle.get("codeCompletionsForm.enableFeatureText"),
        settings.isCodeCompletionsEnabled());
  }

  public JPanel getForm() {
    return FormBuilder.createFormBuilder()
        .addLabeledComponent(
            CodeGPTBundle.get("settingsConfigurable.shared.apiKey.label"), apiKeyField)
        .addComponentToRightColumn(
            UIUtil.createComment("settingsConfigurable.service.openai.apiKey.comment")
        )
        .addLabeledComponent(
            CodeGPTBundle.get("settingsConfigurable.service.openai.organization.label"),
            organizationField)
        .addComponentToRightColumn(
            UIUtil.createComment("settingsConfigurable.section.openai.organization.comment")
        )
        .addLabeledComponent(
            CodeGPTBundle.get("settingsConfigurable.shared.model.label"), completionModelComboBox)
        .addVerticalGap(4)
        .addComponent(codeCompletionsEnabledCheckBox)
        .addComponentFillVertically(new JPanel(), 0)
        .getPanel();
  }

  public @Nullable String getApiKey() {
    var apiKey = new String(apiKeyField.getPassword());
    return apiKey.isEmpty() ? null : apiKey;
  }

  public String getModel() {
    return ((ZhengyanModel) (completionModelComboBox.getModel()
        .getSelectedItem()))
        .getCode();
  }

  public ZhengyanSettingsState getCurrentState() {
    var state = new ZhengyanSettingsState();
    state.setModel(getModel());
    state.setOrganization(organizationField.getText());
    state.setCodeCompletionsEnabled(codeCompletionsEnabledCheckBox.isSelected());
    return state;
  }

  public void resetForm() {
    var state = ZhengyanSettings.getCurrentState();
    apiKeyField.setText(CredentialsStore.getCredential(CredentialKey.ZY_API_KEY));
    completionModelComboBox.setSelectedItem(
        ZhengyanModel.findByCode(state.getModel()));
    organizationField.setText(state.getOrganization());
    codeCompletionsEnabledCheckBox.setSelected(state.isCodeCompletionsEnabled());
  }
}
