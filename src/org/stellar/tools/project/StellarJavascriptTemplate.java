package org.stellar.tools.project;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.lang.javascript.boilerplate.AbstractGithubTagDownloadedProjectGenerator;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.GithubTagInfo;
import com.intellij.util.Icons;
import lombok.Data;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.localization.StellarBundle;
import org.stellar.tools.ui.JavaScriptProjectPeer;
import org.stellar.tools.utils.StellarIcons;

import javax.swing.*;

//import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator;
//import javax.swing.*;
//
//public class StellarJavascriptTemplate extends NpmPackageProjectGenerator  {
//        @NotNull
//        @Override
//        protected String getDisplayName() {
//            return "Stellar Javascript";
//        }
//
//        @NotNull
//        @Override
//        public String getGithubUserName() {
//            return "stellar";
//        }
//
//        @NotNull
//        @Override
//        public String getGithubRepositoryName() {
//            return "js-stellar-sdk";
//        }
//
//        @Nullable
//        @Override
//        public String getDescription() {
//            return "<html>This project is an application skeleton for a typical <a href=\"https://stellar.org\">Stellar</a> javascript app.<br>" +
//                    "Don't forget to install dependencies by running<pre>npm install</pre></html>";
//        }
//
//
//    @Override
//        public Icon getIcon() {
//            return StellarIcons.getIcon();
//        }
//
//        @Nullable
//        @Override
//        public String getPrimaryZipArchiveUrlForDownload(@NotNull GithubTagInfo tag) {
//            return null;
//        }
//}
//
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


