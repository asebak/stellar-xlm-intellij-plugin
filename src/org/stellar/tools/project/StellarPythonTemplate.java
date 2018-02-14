package org.stellar.tools.project;

import com.intellij.icons.AllIcons;
import com.intellij.lang.javascript.boilerplate.AbstractGithubTagDownloadedProjectGenerator;
import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.templates.github.GithubTagInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StellarPythonTemplate extends AbstractGithubTagDownloadedProjectGenerator {
    @NotNull
    @Override
    protected String getDisplayName() {
        return "Python App";
    }

    @NotNull
    @Override
    public String getGithubUserName() {
        return "asebak";
    }

    @NotNull
    @Override
    public String getGithubRepositoryName() {
        return "stellar-py-sample-intellij";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "<html>This project is a bare <a href=\"https://stellar.org\">Stellar Python</a> app.<br>" +
                "Don't forget to install dependencies by running<pre>pip install -r requirements.txt</pre></html>";
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/python.png");
    }

    @Nullable
    @Override
    public String getPrimaryZipArchiveUrlForDownload(@NotNull GithubTagInfo tag) {
        return null;
    }
}
