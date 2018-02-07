package org.stellar.tools.ui;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreter;
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterField;
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterManager;
import com.intellij.javascript.nodejs.interpreter.local.NodeJsLocalInterpreter;
import com.intellij.javascript.nodejs.util.NodePackage;
import com.intellij.javascript.nodejs.util.NodePackageField;
import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.WebProjectGenerator;
import com.intellij.ui.TextAccessor;
import com.intellij.util.PathUtil;
import com.intellij.util.ui.UIUtil;
import com.intellij.xml.util.XmlStringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.project.StellarJavascriptTemplate;

import javax.swing.*;
import java.awt.*;
public class JavaScriptProjectPeer  implements WebProjectGenerator.GeneratorPeer<NpmPackageProjectGenerator.Settings>{
    private JPanel panel;

    @NotNull
    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {

    }

    @NotNull
    @Override
    public NpmPackageProjectGenerator.Settings getSettings() {
        return null;
    }

    @Nullable
    @Override
    public ValidationInfo validate() {
        return null;
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }

    @Override
    public void addSettingsStateListener(@NotNull WebProjectGenerator.SettingsStateListener settingsStateListener) {

    }
}
//public class JavaScriptProjectPeer implements WebProjectGenerator.GeneratorPeer<NpmPackageProjectGenerator.Settings> {
//    private JPanel panel = createPanel();
//    private NodeJsInterpreterField myInterpreter;
//    private NodePackageField myPackageField;
//    private TextAccessor myLocation;
//
//    @NotNull
//    @Override
//    public JComponent getComponent() {
//        return panel;
//    }
//
//    protected JPanel createPanel() {
//        Project project = ProjectManager.getInstance().getDefaultProject();
//        this.myInterpreter = new NodeJsInterpreterField(project, false) {
//            public boolean isDefaultProjectInterpreterField() {
//                return true;
//            }
//        };
//        NodeJsInterpreterManagerExt service = ServiceManager.getService(project, NodeJsInterpreterManagerExt.class);
//        this.myInterpreter.setInterpreterRef(service.getInterpreterRef());
//        LabeledComponent component = LabeledComponent.create(this.myInterpreter, "Node &interpreter:");
//        component.setLabelLocation("West");
//        panel.add(component);
//        this.myPackageField = new NodePackageField(this.myInterpreter, StellarJavascriptTemplate.PACKAGE_NAME);
//        this.myPackageField.setSelected(NodePackage.findPreferredPackage(project, StellarJavascriptTemplate.PACKAGE_NAME, this.myInterpreter.getInterpreter()));
//        component = LabeledComponent.create(this.myPackageField, "Stellar &SDK");
//        component.setAnchor((JComponent)panel.getComponent(0));
//        component.setLabelLocation("West");
//        panel.add(component);
//        return panel;
//    }
//
//
//    @Override
//    public void buildUI(@NotNull SettingsStep settingsStep) {
//        if (settingsStep == null) {
//        }
//
////        final ModuleNameLocationSettings field = settingsStep.getModuleNameField().getText();
//        if (settingsStep.getModuleNameField() != null) {
//            this.myLocation = new TextAccessor() {
//                public void setText(String text) {
//                    settingsStep.getModuleNameField().setText(text);
//                }
//
//                public String getText() {
//                    return settingsStep.getModuleNameField().getText();
//                }
//            };
//        }
//
//        settingsStep.addSettingsField(UIUtil.replaceMnemonicAmpersand("Node &interpreter:"), this.myInterpreter);
//        settingsStep.addSettingsField(UIUtil.replaceMnemonicAmpersand("Stellar &SDK"), this.myPackageField);
//    }
//
//    @NotNull
//    @Override
//    public NpmPackageProjectGenerator.Settings getSettings() {
//        NpmPackageProjectGenerator.Settings settings =
//                new NpmPackageProjectGenerator.Settings(this.myInterpreter.getInterpreterRef(), this.myPackageField.getSelected().getSystemDependentPath());
//        return settings;
//    }
//
//    @Nullable
//    @Override
//    public ValidationInfo validate() {
//        NodeJsInterpreter interpreter = this.myInterpreter.getInterpreter();
//        String error = NodeJsLocalInterpreter.getErrorMessage(interpreter);
//        if (error != null) {
//            return new ValidationInfo(error);
//        } else {
//            String pkgError = this.myPackageField.getSelected().getErrorMessage(StellarJavascriptTemplate.PACKAGE_NAME);
//            if (pkgError != null) {
//                return new ValidationInfo(pkgError);
//            } else {
//                if (this.myLocation == null) {
//                    Component component = this.myInterpreter.getParent().getParent().getComponent(0);
//                    Component innerComponent = component instanceof LabeledComponent ? ((LabeledComponent)component).getComponent() : null;
//                    this.myLocation = innerComponent instanceof TextAccessor ? (TextAccessor)innerComponent : null;
//                }
//
//                String validateMessage = this.myLocation != null ? this.validateProjectPath(this.myLocation.getText()) : null;
//                return validateMessage != null ? new ValidationInfo(validateMessage) : null;
//            }
//        }
//    }
//
//    protected String validateProjectPath(@NotNull String path) {
//        String fileName = PathUtil.getFileName(path);
//        for (String segment : fileName.split("-")) {
//            if (!segment.matches("[a-zA-Z][.0-9a-zA-Z]*(-[.0-9a-zA-Z]*)*")) {
//                return XmlStringUtil.wrapInHtml(
//                        "Project name " + fileName + " is not valid. New project names must<br>\n" +
//                                "start with a letter, and must contain only alphanumeric characters or dashes.<br>\n" +
//                                "When adding a dash the segment after the dash must also start with a letter."
//                );
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean isBackgroundJobRunning() {
//        return false;
//    }
//
//    @Override
//    public void addSettingsStateListener(@NotNull WebProjectGenerator.SettingsStateListener settingsStateListener) {
//        if (settingsStateListener == null) {
//        }
//
//        this.myInterpreter.addChangeListener((interpreter) -> {
//            if (settingsStateListener == null) {
//            }
//
//            settingsStateListener.stateChanged(this.validate() == null);
//        });
//        this.myPackageField.addSelectionListener((newPackage) -> {
//
//            settingsStateListener.stateChanged(this.validate() == null);
//        });
//        settingsStateListener.stateChanged(this.validate() == null);
//    }
//
//}
//public class JavaScriptProjectPeer implements WebProjectGenerator.GeneratorPeer<StellarJavascriptTemplate.JSStellarProjectSettings> {
//    private JPanel panel = createPanel();
//    private NodeJsInterpreterField myInterpreter;
//    private NodePackageField myPackageField;
//    private TextAccessor myLocation;
//
//    @NotNull
//    @Override
//    public JComponent getComponent() {
//        return panel;
//    }
//
//    protected JPanel createPanel() {
//        Project project = ProjectManager.getInstance().getDefaultProject();
//        this.myInterpreter = new NodeJsInterpreterField(project, false) {
//            public boolean isDefaultProjectInterpreterField() {
//                return true;
//            }
//        };
//        this.myInterpreter.setInterpreterRef(NodeJsIntManager.getInstance(project).getInterpreterRef());
//        LabeledComponent component = LabeledComponent.create(this.myInterpreter, "Node &interpreter:");
//        component.setLabelLocation("West");
//        panel.add(component);
//        this.myPackageField = new NodePackageField(this.myInterpreter, StellarJavascriptTemplate.PACKAGE_NAME);
//        this.myPackageField.setSelected(NodePackage.findPreferredPackage(project, StellarJavascriptTemplate.PACKAGE_NAME, this.myInterpreter.getInterpreter()));
//        component = LabeledComponent.create(this.myPackageField, "Stellar &SDK");
//        component.setAnchor((JComponent)panel.getComponent(0));
//        component.setLabelLocation("West");
//        panel.add(component);
//        return panel;
//    }
//
//
//    @Override
//    public void buildUI(@NotNull SettingsStep settingsStep) {
//        if (settingsStep == null) {
//        }
//
////        final ModuleNameLocationSettings field = settingsStep.getModuleNameField().getText();
//        if (settingsStep.getModuleNameField() != null) {
//            this.myLocation = new TextAccessor() {
//                public void setText(String text) {
//                    settingsStep.getModuleNameField().setText(text);
//                }
//
//                public String getText() {
//                    return settingsStep.getModuleNameField().getText();
//                }
//            };
//        }
//
//        settingsStep.addSettingsField(UIUtil.replaceMnemonicAmpersand("Node &interpreter:"), this.myInterpreter);
//        settingsStep.addSettingsField(UIUtil.replaceMnemonicAmpersand("Stellar &SDK"), this.myPackageField);
//    }
//
//    @NotNull
//    @Override
//    public StellarJavascriptTemplate.JSStellarProjectSettings getSettings() {
//        StellarJavascriptTemplate.JSStellarProjectSettings settings =
//                new StellarJavascriptTemplate.JSStellarProjectSettings(this.myInterpreter.getInterpreterRef(), this.myPackageField.getSelected().getSystemDependentPath());
//        return settings;
//    }
//
//    @Nullable
//    @Override
//    public ValidationInfo validate() {
//        NodeJsInterpreter interpreter = this.myInterpreter.getInterpreter();
//        String error = NodeJsLocalInterpreter.getErrorMessage(interpreter);
//        if (error != null) {
//            return new ValidationInfo(error);
//        } else {
//            String pkgError = this.myPackageField.getSelected().getErrorMessage(StellarJavascriptTemplate.PACKAGE_NAME);
//            if (pkgError != null) {
//                return new ValidationInfo(pkgError);
//            } else {
//                if (this.myLocation == null) {
//                    Component component = this.myInterpreter.getParent().getParent().getComponent(0);
//                    Component innerComponent = component instanceof LabeledComponent ? ((LabeledComponent)component).getComponent() : null;
//                    this.myLocation = innerComponent instanceof TextAccessor ? (TextAccessor)innerComponent : null;
//                }
//
//                String validateMessage = this.myLocation != null ? this.validateProjectPath(this.myLocation.getText()) : null;
//                return validateMessage != null ? new ValidationInfo(validateMessage) : null;
//            }
//        }
//    }
//
//    protected String validateProjectPath(@NotNull String path) {
//        String fileName = PathUtil.getFileName(path);
//        for (String segment : fileName.split("-")) {
//            if (!segment.matches("[a-zA-Z][.0-9a-zA-Z]*(-[.0-9a-zA-Z]*)*")) {
//                return XmlStringUtil.wrapInHtml(
//                        "Project name " + fileName + " is not valid. New project names must<br>\n" +
//                                "start with a letter, and must contain only alphanumeric characters or dashes.<br>\n" +
//                                "When adding a dash the segment after the dash must also start with a letter."
//                );
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean isBackgroundJobRunning() {
//        return false;
//    }
//
//    @Override
//    public void addSettingsStateListener(@NotNull WebProjectGenerator.SettingsStateListener settingsStateListener) {
//        if (settingsStateListener == null) {
//        }
//
//        this.myInterpreter.addChangeListener((interpreter) -> {
//            if (settingsStateListener == null) {
//            }
//
//            settingsStateListener.stateChanged(this.validate() == null);
//        });
//        this.myPackageField.addSelectionListener((newPackage) -> {
//
//            settingsStateListener.stateChanged(this.validate() == null);
//        });
//        settingsStateListener.stateChanged(this.validate() == null);
//    }
//}
