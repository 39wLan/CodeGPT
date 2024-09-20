package ee.carlrobert.codegpt.toolwindow.ui

import com.intellij.icons.AllIcons
import com.intellij.ui.components.ActionLink
import com.intellij.util.ui.JBUI
import ee.carlrobert.codegpt.Icons
import ee.carlrobert.codegpt.settings.GeneralSettings
import ee.carlrobert.codegpt.toolwindow.chat.ui.ResponsePanel
import ee.carlrobert.codegpt.ui.UIUtil.createTextPane
import java.awt.BorderLayout
import java.awt.Point
import java.awt.event.ActionListener
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JPanel

class ChatToolWindowLandingPanel(onAction: (LandingPanelAction, Point) -> Unit) : ResponsePanel() {

    init {
        addContent(createContent(onAction))
    }

    private fun createContent(onAction: (LandingPanelAction, Point) -> Unit): JPanel {
        return JPanel(BorderLayout()).apply {
            add(createTextPane(getWelcomeMessage(), false), BorderLayout.NORTH)
            add(createActionsListPanel(onAction), BorderLayout.CENTER)
//            add(createTextPane(getCautionMessage(), false), BorderLayout.SOUTH)
        }
    }

    private fun createActionsListPanel(onAction: (LandingPanelAction, Point) -> Unit): JPanel {
        val listPanel = JPanel()
        listPanel.layout = BoxLayout(listPanel, BoxLayout.PAGE_AXIS)
        listPanel.border = JBUI.Borders.emptyLeft(4)
        listPanel.add(Box.createVerticalStrut(4))
        listPanel.add(createEditorActionLink(LandingPanelAction.EXPLAIN, onAction))
        listPanel.add(Box.createVerticalStrut(4))
        listPanel.add(createEditorActionLink(LandingPanelAction.WRITE_TESTS, onAction))
        listPanel.add(Box.createVerticalStrut(4))
        listPanel.add(createEditorActionLink(LandingPanelAction.FIND_BUGS, onAction))
        listPanel.add(Box.createVerticalStrut(4))
        return listPanel
    }

    private fun createEditorActionLink(
        action: LandingPanelAction,
        onAction: (LandingPanelAction, Point) -> Unit
    ): ActionLink {
        return ActionLink(action.userMessage, ActionListener { event ->
            onAction(action, (event.source as ActionLink).locationOnScreen)
        }).apply {
            icon = AllIcons.Actions.RunToCursor
        }
    }

    private fun getWelcomeMessage(): String {
        return """
            <html>
            <p style="margin-top: 4px; margin-bottom: 4px;">
            你好 <strong>${GeneralSettings.getCurrentState().displayName}</strong>, 我是常青藤辅助编程小助手！您可以问我任何问题，我可以帮助您解决一些代码相关的问题。以下是您可以问我的一些问题示例：
            </p>
            </html>
        """.trimIndent()
    }

    private fun getCautionMessage(): String {
        return """
            <html>
            <p style="margin-top: 4px; margin-bottom: 4px;">
            当然,我有时可能会犯错误，请注意检查我的回答内容哦~
            </p>
            </html>
        """.trimIndent()
    }
}

enum class LandingPanelAction(
    val label: String,
    val userMessage: String,
    val prompt: String
) {
    FIND_BUGS(
        "代码解释",
        "解释并分析这段代码",
            "请解释并分析这段代码:\n {{selectedCode}}"
    ),
    WRITE_TESTS(
        "单元测试",
        "为这段代码编写单元测试",
        "请按照阿里的代码规范，为该代码生成单元测试:\n" +
                " {{selectedCode}}"
    ),
    EXPLAIN(
        "代码优化",
        "对这段代码进行优化",
        "请针对代码bug、风格、性能、安全这四个方面进行优化，并重写代码:\n" +
                " {{selectedCode}}"
    )
}

