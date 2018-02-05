package org.stellar.tools.project;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.AbstractModuleBuilder;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.ProjectTemplate;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.localization.StellarBundle;

import javax.swing.*;

public class StellarJavaTemplate implements ProjectTemplate {
    @Nls
    @NotNull
    @Override
    public String getName() {
        return StellarBundle.getString("app.name.java");
    }

    @Override
    public String getDescription() {
        return StellarBundle.getString("app.description.java");
    }

    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Java;
    }

    @NotNull
    @Override
    public AbstractModuleBuilder createModuleBuilder() {
        return new StellarJavaModuleBuilder();
    }

    @Nullable
    @Override
    public ValidationInfo validateSettings() {
        return null;
    }
}
