package ee.carlrobert.codegpt.toolwindow.chat.editor.actions;

import com.intellij.icons.AllIcons.Actions;
import com.intellij.icons.AllIcons.Diff;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.ui.JBMenuItem;
import ee.carlrobert.codegpt.CodeGPTBundle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.jetbrains.annotations.NotNull;

public class EditAction extends AbstractAction {

  private final EditorEx editor;

  public EditAction(@NotNull EditorEx editor) {
    super("编辑代码", Actions.EditSource);
    this.editor = editor;
  }

  @Override
  public void actionPerformed(@NotNull ActionEvent event) {
    editor.setViewer(!editor.isViewer());

    var viewer = editor.isViewer();
    editor.setCaretVisible(!viewer);
    editor.setCaretEnabled(!viewer);

    var settings = editor.getSettings();
    settings.setCaretRowShown(!viewer);

    var menuItem = (JBMenuItem) event.getSource();
    menuItem.setText(viewer
        ? "编辑代码"
        : "禁用编辑");
    menuItem.setIcon(viewer ? Actions.EditSource : Diff.Lock);
  }
}
