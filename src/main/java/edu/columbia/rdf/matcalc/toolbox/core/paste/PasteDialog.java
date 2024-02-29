package edu.columbia.rdf.matcalc.toolbox.core.paste;

import java.nio.file.Path;
import java.util.List;

import javax.swing.Box;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;

import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.CheckBox;
import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.dialog.ModernMessageDialog;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.io.AllGuiFilesFilter;
import dev.antonyholmes.modern.io.ChooseFilesPanel;
import dev.antonyholmes.modern.io.TsvGuiFileFilter;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;
import edu.columbia.rdf.matcalc.MainMatCalcWindow;

/**
 * Control which conservation scores are shown.
 * 
 * @author Antony Holmes
 *
 */
public class PasteDialog extends ModernDialogHelpWindow implements ModernClickListener {
  private static final long serialVersionUID = 1L;

  /** Assume first column is common between files */
  private CheckBox mCheckIndex = new ModernCheckSwitch("Header", true);

  // private DelimiterCombo mDelimiterCombo = new DelimiterCombo();

  private ChooseFilesPanel mChooseFilesPanel;

  public PasteDialog(MainMatCalcWindow parent) {
    super(parent, "matcalc.toolbox.paste.help.url");

    setTitle("Paste Files");

    mChooseFilesPanel = new ChooseFilesPanel(parent, AllGuiFilesFilter.INSTANCE, TsvGuiFileFilter.INSTANCE);

    setup();

    createUi();
  }

  private void setup() {
    addWindowListener(new WindowWidgetFocusEvents(mOkButton));

    mCheckIndex.setSelected(SettingsService.getInstance().getBool("org.matcalc.toolbox.paste.common-index", true));

    setSize(500, 420);

    UI.centerWindowToScreen(this);
  }

  private final void createUi() {
    ModernComponent content = new ModernComponent();

    Box box = VBox.create();
    box.add(UI.createVGap(10));
    box.add(mCheckIndex);

    content.setFooter(box);

    content.setBody(mChooseFilesPanel);

    setCard(content);
  }

  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mOkButton)) {
      if (getFiles().size() == 0) {
        ModernMessageDialog.createWarningDialog(mParent, "You must choose at least one file.");

        return;
      }

      SettingsService.getInstance().update("org.matcalc.toolbox.paste.common-index", mCheckIndex.isSelected());
    }

    super.clicked(e);
  }

  public List<Path> getFiles() {
    return mChooseFilesPanel.getFiles();
  }

  /**
   * Returns true if one way overlap is selected.
   * 
   * @return
   */
  public boolean getCommonIndex() {
    return mCheckIndex.isSelected();
  }

  public String getDelimiter() {
    return TextUtils.TAB_DELIMITER;
  }
}
