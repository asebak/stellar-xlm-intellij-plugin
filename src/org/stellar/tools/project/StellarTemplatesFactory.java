package org.stellar.tools.project;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import org.jetbrains.annotations.NotNull;
import org.stellar.tools.utils.StellarIcons;

import javax.swing.*;

public class StellarTemplatesFactory extends ProjectTemplatesFactory {
    @NotNull
    @Override
    public String[] getGroups() {
        return new String[]{"Stellar SDK"};
    }

    @Override
    public Icon getGroupIcon(String s) {
        return StellarIcons.getIcon();
    }

    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(String s, WizardContext wizardContext) {
        return new ProjectTemplate[]{
                new StellarJavascriptTemplate(),
                new StellarJavaTemplate()
        };
    }
}
