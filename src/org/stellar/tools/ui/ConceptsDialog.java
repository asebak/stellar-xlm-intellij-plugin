package org.stellar.tools.ui;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.TemplateKindCombo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.*;

public class ConceptsDialog extends DialogWrapper {
    @NotNull
    private final Project project;
    @NotNull
    private final PsiDirectory psiDirectory;
    private JPanel contentPane;
    private TemplateKindCombo conceptCombo;
    private JLabel actionLabel;

    public ConceptsDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory) {
        super(project);
        this.project = project;
        this.psiDirectory = psiDirectory;
        init();
    }
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }


    @Override
    protected void doOKAction() {
        super.doOKAction();
    }

    @Override
    protected void init() {
        conceptCombo.addItem("Accounts", AllIcons.FileTypes.JavaScript, "ssaad");
        conceptCombo.addItem("Balance", AllIcons.FileTypes.Properties, "dsadsa");
        super.init();
    }
}
