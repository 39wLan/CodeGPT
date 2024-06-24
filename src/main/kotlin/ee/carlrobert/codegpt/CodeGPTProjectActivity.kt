package ee.carlrobert.codegpt

import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import ee.carlrobert.codegpt.actions.editor.EditorActionsUtil
import ee.carlrobert.codegpt.settings.GeneralSettings
import ee.carlrobert.codegpt.settings.configuration.ConfigurationSettings
import ee.carlrobert.codegpt.settings.service.ServiceType
import ee.carlrobert.codegpt.settings.service.codegpt.CodeGPTService
import ee.carlrobert.codegpt.toolwindow.chat.ui.textarea.AttachImageNotifier
import ee.carlrobert.codegpt.ui.OverlayUtil
import io.ktor.util.*
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class CodeGPTProjectActivity : StartupActivity.Background {

    private val watchExtensions = setOf("jpg", "jpeg", "png")

    override fun runActivity(project: Project) {
        EditorActionsUtil.refreshActions()

        val settings = service<GeneralSettings>().state
        if (settings.selectedService == ServiceType.CODEGPT) {
            project.service<CodeGPTService>().syncUserDetailsAsync()
        }

        if (!ApplicationManager.getApplication().isUnitTestMode
            && ConfigurationSettings.getCurrentState().isCheckForNewScreenshots
        ) {
            val desktopPath = Paths.get(System.getProperty("user.home"), "Desktop")
            project.service<FileWatcher>().watch(desktopPath) {
                if (watchExtensions.contains(it.extension.lowercase())) {
                    showImageAttachmentNotification(
                        project,
                        desktopPath.resolve(it).absolutePathString()
                    )
                }
            }
        }
    }

    private fun showImageAttachmentNotification(project: Project, filePath: String) {
        OverlayUtil.getDefaultNotification(
            CodeGPTBundle.get("imageAttachmentNotification.content"),
            NotificationType.INFORMATION
        )
            .addAction(NotificationAction.createSimpleExpiring(
                CodeGPTBundle.get("imageAttachmentNotification.action")
            ) {
                CodeGPTKeys.IMAGE_ATTACHMENT_FILE_PATH.set(project, filePath)
                project.messageBus
                    .syncPublisher<AttachImageNotifier>(
                        AttachImageNotifier.IMAGE_ATTACHMENT_FILE_PATH_TOPIC
                    )
                    .imageAttached(filePath)
            })
            .addAction(NotificationAction.createSimpleExpiring(
                CodeGPTBundle.get("shared.notification.doNotShowAgain")
            ) {
                ConfigurationSettings.getCurrentState().isCheckForNewScreenshots = false
            })
            .notify(project)
    }
}
