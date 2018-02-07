package org.stellar.tools.project;

import com.intellij.execution.filters.Filter;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterRef;
import com.intellij.lang.javascript.boilerplate.AbstractGithubTagDownloadedProjectGenerator;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.GithubTagInfo;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Icons;
import com.intellij.util.PathUtil;
import com.intellij.xml.util.XmlStringUtil;
import lombok.Data;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.localization.StellarBundle;
import org.stellar.tools.ui.JavaScriptProjectPeer;
import org.stellar.tools.utils.StellarIcons;

import javax.swing.*;

import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator;

import java.io.File;

//import javax.swing.*;
//
public class StellarJavascriptTemplate extends NpmPackageProjectGenerator  {
    public static final String PACKAGE_NAME = "stellar-sdk";

    @Nls
    @NotNull
    @Override
    public String getName() {
        return "Stellar Javascript App";
    }

    @Override
    public String getDescription() {
        return "The Stellar JS SDK provides a easy interface for building node js apps.";
    }

    @Override
    public Icon getIcon() {
               return AllIcons.FileTypes.JavaScript;
    }

    @Override
    protected void customizeModule(@NotNull VirtualFile baseDir, ContentEntry entry) {
        if (entry != null) {
            entry.addExcludeFolder(baseDir.getUrl() + "/dist");
            entry.addExcludeFolder(baseDir.getUrl() + "/tmp");
        }
    }

    @Override
    @NotNull
    protected String[] generatorArgs(@NotNull Project project, @NotNull VirtualFile baseDir) {
        return ArrayUtil.EMPTY_STRING_ARRAY;
    }

    @Override
    @NotNull
    protected String[] generatorArgs(@NotNull Project project, @NotNull VirtualFile baseDir, @NotNull Settings settings) {
        return new String[]{"new", baseDir.getName(), "--dir=."};
    }

    @NotNull
    @Override
    protected Filter[] filters(@NotNull Project project, @NotNull VirtualFile baseDir) {
        return new Filter[] {};
    }

    @Override
    @NotNull
    protected String executable(String path) {
        return ng(path);
    }

    @NotNull
    public static String ng(String path) {
        return path + File.separator + "bin" + File.separator + "ng";
    }

    @Override
    @NotNull
    protected String packageName() {
        return PACKAGE_NAME;
    }

    @Override
    @NotNull
    protected String presentablePackageName() {
        return "Stellar &JS";
    }

    @Override
    protected String validateProjectPath(@NotNull String path) {
        String fileName = PathUtil.getFileName(path);
        for (String segment : fileName.split("-")) {
            if (!segment.matches("[a-zA-Z][.0-9a-zA-Z]*(-[.0-9a-zA-Z]*)*")) {
                return XmlStringUtil.wrapInHtml(
                        "Project name " + fileName + " is not valid. New project names must<br>\n" +
                                "start with a letter, and must contain only alphanumeric characters or dashes.<br>\n" +
                                "When adding a dash the segment after the dash must also start with a letter."
                );
            }
        }
        return super.validateProjectPath(path);
    }

//    @NotNull
//    public GeneratorPeer<NpmPackageProjectGenerator.Settings> createPeer() {
////        NpmPackageProjectGenerator.NpmPackageGeneratorPeer var10000 = new NpmPackageProjectGenerator.NpmPackageGeneratorPeer();
////        if (var10000 == null) {
////            $$$reportNull$$$0(22);
////        }
////
////        return var10000;
//        return new JavaScriptProjectPeer();
//    }
}

//public class StellarJavascriptTemplate extends WebProjectTemplate<StellarJavascriptTemplate.JSStellarProjectSettings> {
//    public static final String PACKAGE_NAME = "stellar-sdk";
//
//    @Nls
//    @NotNull
//    @Override
//    public String getName() {
//        return StellarBundle.getString("app.name.js");
//    }
//
//    @Override
//    public String getDescription() {
//        return StellarBundle.getString("app.description.js");
//    }
//
//    @Override
//    public void generateProject(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final JSStellarProjectSettings settings, @NotNull Module module) {
//    }
//
//
//    @Override
//    public Icon getIcon() {
//        return AllIcons.FileTypes.JavaScript;
//    }
//
//    @NotNull
//    @Override
//    public JavaScriptProjectPeer createPeer() {
//        return new JavaScriptProjectPeer();
//    }
//
//    @Data
//    public final static class JSStellarProjectSettings extends UserDataHolderBase {
//        public NodeJsInterpreterRef myInterpreterRef;
//        public String myPackagePath;
//
//        public JSStellarProjectSettings(@NotNull NodeJsInterpreterRef interpreter, String text) {
//            super();
//            this.myInterpreterRef = interpreter;
//            this.myPackagePath = text;
//        }
//    }
//}
//
//
