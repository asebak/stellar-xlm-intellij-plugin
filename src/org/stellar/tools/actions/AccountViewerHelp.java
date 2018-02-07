package org.stellar.tools.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.awt.*;
import java.net.URL;

public class AccountViewerHelp extends AnAction {
    private static final String URL = "https://www.stellar.org/account-viewer/#!/";
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        try {
            Desktop.getDesktop().browse(new URL(URL).toURI());
        } catch (Exception e) {}
    }
}
