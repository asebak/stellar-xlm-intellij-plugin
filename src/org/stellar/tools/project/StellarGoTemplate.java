package org.stellar.tools.project;

import com.intellij.lang.javascript.boilerplate.AbstractGithubTagDownloadedProjectGenerator;
import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.templates.github.GithubTagInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StellarGoTemplate extends AbstractGithubTagDownloadedProjectGenerator {

    @NotNull
    @Override
    protected String getDisplayName() {
        return "Go App";
    }

    @NotNull
    @Override
    public String getGithubUserName() {
        return "asebak";
    }

    @NotNull
    @Override
    public String getGithubRepositoryName() {
        return "stellar-go-sample-intellij";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "<html>This project is an application skeleton for a typical <a href=\"https://stellar.org\">Stellar Go</a> app.<br></html>";
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/go.png");
    }

    @Nullable
    @Override
    public String getPrimaryZipArchiveUrlForDownload(@NotNull GithubTagInfo tag) {
        return null;
    }
}
