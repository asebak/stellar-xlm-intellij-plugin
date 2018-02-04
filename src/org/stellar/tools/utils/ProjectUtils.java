package org.stellar.tools.utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.util.DisposeAwareRunnable;


public class ProjectUtils {
    /**
     * Runs a thread when initialized
     *
     * @param project
     * @param r
     */
    public static void runWhenInitialized(final Project project, final Runnable r) {
        if (project.isDisposed()) return;

        if (isNoBackgroundMode()) {
            r.run();
            return;
        }

        if (!project.isInitialized()) {
            StartupManager.getInstance(project).registerPostStartupActivity(DisposeAwareRunnable.create(r, project));
            return;
        }

        runDumbAware(project, r);
    }

    /**
     * Runs the DumbService
     * @param project
     * @param r
     */
    public static void runDumbAware(final Project project, final Runnable r) {
        if (DumbService.isDumbAware(r)) {
            r.run();
        } else {
            DumbService.getInstance(project).runWhenSmart(DisposeAwareRunnable.create(r, project));
        }
    }

    /**
     * Checks if there is no background mode
     * @return
     */
    public static boolean isNoBackgroundMode() {
        return (ApplicationManager.getApplication().isUnitTestMode()
                || ApplicationManager.getApplication().isHeadlessEnvironment());
    }

    /**
     * adds run configuration dynamically
     * @param module
     * @param mainClass
     * @param boardName
     */
    public static void addProjectConfiguration(final Module module, final String mainClass, final String boardName) {
//        final Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                final RunManager runManager = RunManager.getInstance(module.getProject());
//                final RunnerAndConfigurationSettings settings = runManager.
//                        createRunConfiguration(module.getName(), EmbeddedLinuxJVMConfigurationType.getInstance().getFactory());
//                final EmbeddedLinuxJVMRunConfiguration configuration = (EmbeddedLinuxJVMRunConfiguration) settings.getConfiguration();
//
//                configuration.setName(StellarBundle.message("runner.name", boardName));
//                configuration.getRunnerParameters().setRunAsRoot(true);
//                configuration.getRunnerParameters().setMainclass(mainClass);
//
//                runManager.addConfiguration(settings, false);
//                runManager.setSelectedConfiguration(settings);
//
//                final Notification notification = new Notification(
//                        Notifications.GROUPDISPLAY_ID,
//                        StellarBundle.getString("pi.connection.required"), StellarBundle.message("pi.connection.notsetup", boardName),
//                        NotificationType.INFORMATION);
//                com.intellij.notification.Notifications.Bus.notify(notification);
//            }
//        };
//        r.run();
    }
}
