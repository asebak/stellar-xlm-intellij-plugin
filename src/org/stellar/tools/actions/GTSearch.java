package org.stellar.tools.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.stellar.tools.localization.StellarBundle;

public class GTSearch  extends AnAction {

    /**
     * Action performed from Intellij
     * @param anActionEvent
     */
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DataContext dataContext = anActionEvent.getDataContext();
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        Editor editor = CommonDataKeys.EDITOR.getData(dataContext);
        if (project == null || editor == null) {
            return;
        }
        execute(editor, project);
    }

    /**
     * Executes the action
     * @param editor
     * @param project
     */
    private void execute(@NotNull Editor editor, @NotNull Project project) {
        String selectedTerm = getSearchTerm(editor);
        if (StringUtils.isNotBlank(selectedTerm)) {
            GTSearchDialog.builder().project(project).build().show(selectedTerm);
        } else {
            Messages.showErrorDialog(project, StellarBundle.getString("search.errormsg"), StellarBundle.getString("error"));
        }
    }

    public String getSearchTerm(@NotNull Editor editor) {
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null && selectedText.length() != 0) {
            if (selectedText.contains("\n")) {
                return "";
            } else {
                return selectedText;
            }
        }

        int caretOffset = editor.getCaretModel().getOffset();
        CharSequence charsSequence = editor.getDocument().getCharsSequence();
        return extract(charsSequence, caretOffset);
    }

    public static String extract(CharSequence charSequence, int position) {
        if (position > charSequence.length()) {
            throw new IllegalStateException();
        }
        if (position < 0) {
            throw new IllegalStateException();
        }

        int first = position;
        for (int c = position - 1; c >= 0; c--) {
            if (Character.isJavaIdentifierPart(charSequence.charAt(c))) {
                first = c;
            } else {
                break;
            }
        }

        int last = position;
        for (int c = last; c <= charSequence.length(); c++) {
            if (c == charSequence.length() || !Character
                    .isJavaIdentifierPart(charSequence.charAt(c))) {
                last = c;
                break;
            }
        }

        return charSequence.subSequence(first, last).toString();
    }
}
