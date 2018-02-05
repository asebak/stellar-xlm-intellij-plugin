package org.stellar.tools.project;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Icons;
import lombok.Data;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.stellar.tools.localization.StellarBundle;
import org.stellar.tools.ui.JavaScriptProjectPeer;
import org.stellar.tools.utils.StellarIcons;

import javax.swing.*;

public class StellarJavascriptTemplate extends WebProjectTemplate<StellarJavascriptTemplate.JSStellarProjectSettings> {
    @Nls
    @NotNull
    @Override
    public String getName() {
        return StellarBundle.getString("app.name.js");
    }

    @Override
    public String getDescription() {
        return StellarBundle.getString("app.description.js");
    }

    @Override
    public void generateProject(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final JSStellarProjectSettings settings, @NotNull Module module) {
    }


    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.JavaScript;
    }

    @NotNull
    @Override
    public JavaScriptProjectPeer createPeer() {
        return new JavaScriptProjectPeer();
    }

    @Data
    public final static class JSStellarProjectSettings {
        private String name;
    }
}


