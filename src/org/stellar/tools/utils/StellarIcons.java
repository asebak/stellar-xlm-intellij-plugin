package org.stellar.tools.utils;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class StellarIcons {
    /**
     * Private constructor
     */
    private StellarIcons() {

    }

    public static Icon getIcon(){
        return IconLoader.getIcon("/stellaricon.png", StellarIcons.class);
    }
}
