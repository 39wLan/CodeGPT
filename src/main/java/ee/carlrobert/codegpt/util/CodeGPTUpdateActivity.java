//package ee.carlrobert.codegpt.util;
//
//import com.intellij.ide.plugins.InstalledPluginsState;
//import com.intellij.notification.Notification;
//import com.intellij.notification.NotificationAction;
//import com.intellij.notification.NotificationType;
//import com.intellij.openapi.application.ApplicationManager;
//import com.intellij.openapi.progress.ProgressIndicator;
//import com.intellij.openapi.progress.Task;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.startup.StartupActivity;
//import com.intellij.openapi.updateSettings.impl.UpdateChecker.updateAndShowResult;
//import com.intellij.openapi.updateSettings.impl.UpdateSettings;
//import com.intellij.util.concurrency.AppExecutorUtil;
//import ee.carlrobert.codegpt.settings.configuration.ConfigurationSettings;
//import ee.carlrobert.codegpt.ui.OverlayUtil;
//
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class CodeGPTUpdateActivity implements StartupActivity.Background {
//
//    @Override
//    public void runActivity(Project project) {
//        // 检查是否处于单元测试模式，如果是则直接退出
//        if (ApplicationManager.getApplication().isUnitTestMode()) {
//            return;
//        }
//
//        // 调度插件更新检查任务
//        schedulePluginUpdateChecks(project);
//    }
//
//    private void schedulePluginUpdateChecks(Project project) {
//        // 定义一个Runnable命令，用于执行插件更新检查
//        Runnable command = () -> {
//            // 如果当前设置允许检查插件更新
//            if (ConfigurationSettings.getCurrentState().isCheckForPluginUpdates()) {
//                new CheckForUpdatesTask(project).queue();
//            }
//        };
//
//        // 使用AppExecutorUtil获取App级别的ScheduledExecutorService
//        ScheduledExecutorService executorService = AppExecutorUtil.getAppScheduledExecutorService();
//        // 每4小时执行一次上述命令
//        executorService.scheduleWithFixedDelay(command, 0, 4, TimeUnit.HOURS);
//    }
//
//    // 内部类，代表一个后台更新检查任务
//    private static class CheckForUpdatesTask extends Task.Backgroundable {
//        public CheckForUpdatesTask(Project project) {
//            super(project, "Checking for CodeGPT update...", true);
//        }
//
//        @Override
//        public void run(ProgressIndicator indicator) {
//            // 判断是否有插件新版本
//            boolean isLatestVersion = !InstalledPluginsState.getInstance().hasNewerVersion(CodeGPTPlugin.CODEGPT_ID);
//
//            // 如果项目已关闭或者已是最新版本，直接返回
//            if (isLatestVersion) {
//                return;
//            }
//
//            // 显示通知，包含"安装"和"不再提示"按钮
//            Notification notification = new Notification(
//                    "SinosigGPT",
//                    CodeGPTBundle.getMessage("checkForUpdatesTask.notification.title"),
//                    CodeGPTBundle.getMessage("checkForUpdatesTask.notification.message"),
//                    NotificationType.INFORMATION
//            );
//
//            // 添加"安装"按钮动作
//            NotificationAction installAction = new NotificationAction(
//                    CodeGPTBundle.getMessage("checkForUpdatesTask.notification.installButton")
//            ) {
//                @Override
//                public void actionPerformed(AnActionEvent e, Notification notification) {
//                    // 在后台线程执行安装更新
//                    ApplicationManager.getApplication().executeOnPooledThread(() -> installCodeGPTUpdate(getProject()));
//                }
//            };
//            notification.addAction(installAction);
//
//            // 添加"不再提示"按钮动作
//            NotificationAction doNotShowAgainAction = new NotificationAction(
//                    CodeGPTBundle.getMessage("shared.notification.doNotShowAgain")
//            ) {
//                @Override
//                public void actionPerformed(AnActionEvent e, Notification notification) {
//                    // 更新设置，不再检查更新
//                    ConfigurationSettings.getCurrentState().setCheckForPluginUpdates(false);
//                }
//            };
//            notification.addAction(doNotShowAgainAction);
//
//            // 显示通知
//            Notifications.Bus.notify(notification, getProject());
//        }
//
//        // 静态伴生类方法，用于执行插件更新安装
//        private static void installCodeGPTUpdate(Project project) {
//            // 复制当前更新设置
//            UpdateSettings settingsCopy = new UpdateSettings();
//            UpdateSettings.State settingsState = settingsCopy.getState();
//            settingsState.copyFrom(UpdateSettings.getInstance().getState());
//
//            // 设置更新参数
//            settingsState.setCheckNeeded(true);
//            settingsState.setPluginsCheckNeeded(true);
//            settingsState.setShowWhatsNewEditor(true);
//            settingsState.setThirdPartyPluginsAllowed(true);
//
//            // 更新并展示结果（此部分需替换为实际的更新逻辑）
//            // updateAndShowResult(project, settingsCopy);
//        }
//    }
//}
