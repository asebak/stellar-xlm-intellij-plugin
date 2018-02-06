package org.stellar.tools.actions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.localization.StellarBundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;


@Builder
public class GTSearchDialog {
    private final static String API_PAGE = "https://galactictalk.org/?q=";
    private Project project;

    /**
     * Show the search dialog
     * @param searchTerm
     */
    public void show(@Nullable final String searchTerm) {

        final JTextField searchTextField = new JTextField(searchTerm);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(StellarBundle.getString("api.searchterm")), BorderLayout.WEST);
        panel.add(searchTextField, BorderLayout.CENTER);

        final DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle(StellarBundle.getString("api.search"));
        dialogBuilder.setCenterPanel(panel);
        dialogBuilder.addAction(getSearchAction(dialogBuilder, searchTextField.getText()));
        dialogBuilder.addCancelAction();
        dialogBuilder.showModal(true);
    }

    /**
     * Builds the action
     * @param dialogBuilder
     * @param term
     * @return
     */
    private AbstractAction getSearchAction(@NotNull final DialogBuilder dialogBuilder, @Nullable final String term) {
        return new AbstractAction(StellarBundle.getString("search")) {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().browse(new URL(API_PAGE + term).toURI());
                } catch (Exception e) {
                    Messages.showErrorDialog(project, StellarBundle.getString("error.msg"), StellarBundle.getString("error"));
                }
                dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        };
    }
}
