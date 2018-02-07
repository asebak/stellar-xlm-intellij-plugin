package org.stellar.tools.project;

import com.intellij.icons.AllIcons;
import com.intellij.lang.javascript.boilerplate.AbstractGithubTagDownloadedProjectGenerator;
import com.intellij.platform.templates.github.GithubTagInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StellarJavascriptTemplate extends AbstractGithubTagDownloadedProjectGenerator {
    @NotNull
    @Override
    protected String getDisplayName() {
        return "Javascript App";
    }

    @NotNull
    @Override
    public String getGithubUserName() {
        return "asebak";
    }

    @NotNull
    @Override
    public String getGithubRepositoryName() {
        return "stellar-js-sample-intellij";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "<html>This project is an application skeleton for a typical <a href=\"https://stellar.org\">Stellar Node</a> app.<br>" +
                "Don't forget to install dependencies by running<pre>npm install</pre></html>";
    }

    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.JavaScript;
    }

    @Nullable
    @Override
    public String getPrimaryZipArchiveUrlForDownload(@NotNull GithubTagInfo tag) {
        return null;
    }
}
