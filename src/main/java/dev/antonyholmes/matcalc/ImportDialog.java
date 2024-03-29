/**
 * Copyright 2016 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.antonyholmes.matcalc;

import java.util.List;

import javax.swing.Box;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.Splitter;

import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.CheckBox;
import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.panel.HExpandBox;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.spinner.ModernCompactSpinner;
import dev.antonyholmes.modern.text.ModernClipboardTextField;
import dev.antonyholmes.modern.text.ModernTextBorderPanel;
import dev.antonyholmes.modern.text.ModernTextField;
import dev.antonyholmes.modern.window.ModernWindow;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * User can select how many annotations there are.
 *
 * @author Antony Holmes
 */
public class ImportDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member spinner.
   */
  private ModernCompactSpinner mIndexColsSpinner;

  /**
   * The member check header.
   */
  private CheckBox mCheckHeader = new ModernCheckSwitch("Header rows",
      SettingsService.getInstance().getBool("matcalc.import.file.has-header"));

  /** The m check skip. */
  private CheckBox mCheckSkip = new ModernCheckSwitch("Skip lines starting with", true); // SettingsService.getInstance().getBool("matcalc.import.file.skip.lines"));

  /** The m field skip. */
  private ModernTextField mFieldSkip = new ModernClipboardTextField(
      SettingsService.getInstance().getString("matcalc.import.file.skip.matches"));

  private CheckBox mNumericalCheck = new ModernCheckSwitch("Numerical", true);

  private CheckBox mIndexColsCheck = new ModernCheckSwitch("Index columns", true);

  /** The m delimiter combo. */
  private DelimiterCombo mDelimiterCombo = new DelimiterCombo();

  private ModernCompactSpinner mHeaderSpinner;

  /**
   * Instantiates a new row annotation dialog.
   *
   * @param parent    the parent
   * @param indexCols the row annotations
   * @param isExcel   the is excel
   * @param delimiter the delimiter
   */
  public ImportDialog(ModernWindow parent, int headers, int indexCols, boolean isExcel, String delimiter,
      boolean isNumerical) {
    super(parent, "matcalc.import.help.url");

    setTitle("Import");

    mCheckHeader.setSelected(headers > 0);
    mHeaderSpinner = new ModernCompactSpinner(0, 100, headers);

    mIndexColsCheck.setSelected(indexCols > 0);
    mIndexColsSpinner = new ModernCompactSpinner(0, 100, indexCols);

    mDelimiterCombo.setDelimiter(delimiter);
    mNumericalCheck.setSelected(isNumerical);

    createUi(isExcel);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setSize(480, 320);

    mCheckSkip.setEnabled(false);

    addWindowFocusListener(new WindowWidgetFocusEvents(mOkButton));

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   *
   * @param isExcel the is excel
   */
  private final void createUi(boolean isExcel) {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    Box box = VBox.create();

    box.add(new HExpandBox(mCheckHeader, mHeaderSpinner));

    if (!isExcel) {
      box.add(UI.createVGap(5));
      box.add(new HExpandBox(mCheckSkip, new ModernTextBorderPanel(mFieldSkip, 80)));
      box.add(UI.createVGap(5));
      box.add(new HExpandBox("Delimiter", mDelimiterCombo));
    }

    box.add(UI.createVGap(5));

    // Text columns is more intuitive terminology than row annotations
    box.add(new HExpandBox(mIndexColsCheck, mIndexColsSpinner));

    box.add(UI.createVGap(5));
    box.add(mNumericalCheck);

    setCard(box);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mOkButton)) {
      SettingsService.getInstance().update("matcalc.import.file.has-header", mCheckHeader.isSelected());

      SettingsService.getInstance().update("matcalc.import.file.skip.lines", mCheckSkip.isSelected());

      SettingsService.getInstance().update("matcalc.import.file.skip.matches", mFieldSkip.getText());
    }

    super.clicked(e);
  }

  /**
   * Gets the row annotations.
   *
   * @return the row annotations
   */
  public int getIndexCols() {
    return mIndexColsCheck.isSelected() ? mIndexColsSpinner.getIntValue() : 0;
  }

  /**
   * Gets the checks for header.
   *
   * @return the checks for header
   */
  public boolean getHasHeader() {
    // If there are row annotations, there must be a header
    return getHeaders() > 0;
  }

  public int getHeaders() {
    // If there are row annotations, there must be a header
    return mCheckHeader.isSelected() ? mHeaderSpinner.getIntValue() : 0;
  }

  /**
   * Return the line prefixes to skip on on.
   *
   * @return the skip matches
   */
  public List<String> getSkipLines() {
    return Splitter.onComma().text(mFieldSkip.getText());
  }

  /**
   * Gets the delimiter.
   *
   * @return the delimiter
   */
  public String getDelimiter() {
    return mDelimiterCombo.getDelimiter();
  }

  public boolean isNumerical() {
    return mNumericalCheck.isSelected(); // mTextColsCheck.isSelected() ? mNumericalCheck.isSelected() : false;
  }
}
