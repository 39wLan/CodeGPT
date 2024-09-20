package ee.carlrobert.codegpt.settings.service

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import ee.carlrobert.codegpt.credentials.CredentialsStore
import ee.carlrobert.codegpt.settings.GeneralSettings
import ee.carlrobert.codegpt.settings.service.openai.OpenAISettings
import ee.carlrobert.codegpt.settings.service.openai.OpenAISettingsForm
import ee.carlrobert.codegpt.settings.service.zhengyan.ZhengyanSettings
import ee.carlrobert.codegpt.settings.service.zhengyan.ZhengyanSettingsForm
import javax.swing.JComponent

class ZhengyanServiceConfigurable: Configurable {

    private lateinit var component: ZhengyanSettingsForm

    override fun getDisplayName(): String {
        return "常青藤: 正言 Service"
    }

    override fun createComponent(): JComponent {
        component = ZhengyanSettingsForm(service<ZhengyanSettings>().state)
        return component.form
    }

    override fun isModified(): Boolean {
        return component.getCurrentState() != service<ZhengyanSettings>().state
                || component.getApiKey() != CredentialsStore.getCredential(CredentialsStore.CredentialKey.ZY_API_KEY)
    }

    override fun apply() {
        service<GeneralSettings>().state.selectedService = ServiceType.ZHENGYAN
        CredentialsStore.setCredential(CredentialsStore.CredentialKey.ZY_API_KEY, component.getApiKey())
        service<ZhengyanSettings>().loadState(component.getCurrentState())
    }

    override fun reset() {
        component.resetForm()
    }
}
