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
package dev.antonyholmes.matcalc.toolbox.core.rename;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.swing.Box;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jebtk.core.text.TextUtils;
import org.jebtk.math.external.microsoft.Excel;
import org.jebtk.math.ui.external.microsoft.XlsxTxtGuiFileFilter;

import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.ModernButton;
import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.button.ModernTwoStateWidget;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.icons.OpenFolderVectorIcon;
import dev.antonyholmes.modern.io.FileDialog;
import dev.antonyholmes.modern.io.RecentFilesService;
import dev.antonyholmes.modern.panel.ModernContentPanel;
import dev.antonyholmes.modern.panel.ModernPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.scrollpane.ModernScrollPane;
import dev.antonyholmes.modern.scrollpane.ScrollBarPolicy;
import dev.antonyholmes.modern.text.ModernClipboardTextArea;
import dev.antonyholmes.modern.text.ModernTextArea;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class MatrixRowFilterDialog.
 */
public class RenameDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The row list.
   */
  // private ModernList<String> mRowList = new ModernList<String>();

  private ModernTextArea mIdTextBox = new ModernClipboardTextArea();

  /**
   * The member model.
   */
  // private ModernListModel<String> mModel =
  // new ModernListModel<String>();

  /**
   * The member check exact.
   */
  private ModernTwoStateWidget mCheckExact = new ModernCheckSwitch("Match entire cell contents", true);

  /** The m check case. */
  private ModernTwoStateWidget mCheckCase = new ModernCheckSwitch("Case sensitive");

  private ModernTwoStateWidget mCheckSort = new ModernCheckSwitch("Sort by group", true);

  /**
   * The remove button.
   */
  // private ModernButton mRemoveButton =
  // new RibbonButton(Ui.MENU_REMOVE,
  // UIResources.getInstance().loadIcon("trash_bw", 16));

  /**
   * The clear button.
   */
  private ModernButton mClearButton = new ModernButton(UI.MENU_CLEAR, AssetService.getInstance().loadIcon("clear", 16),
      80);

  /**
   * The import button.
   */
  private ModernButton importButton = new ModernButton(UI.BUTTON_LOAD,
      AssetService.getInstance().loadIcon(OpenFolderVectorIcon.class, 16), 80);

  /**
   * Instantiates a new matrix row filter dialog.
   *
   * @param parent the parent
   * @param m      the m
   */
  public RenameDialog(ModernWindow parent) {
    super(parent, "matcalc.rename.help.url");

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setTitle("Rename In Column");

    // mRowList.setModel(mModel);

    // TabbedPane tabbedPane = new TabbedPane();

    // samples

    ModernComponent content = new ModernComponent();

    /*
     * ModernComponent content = new ModernComponent();
     * 
     * int[] rows = {ModernWidget.WIDGET_HEIGHT}; int[] columns = {100, 400,
     * ModernWidget.WIDGET_HEIGHT};
     * 
     * MatrixPanel panel = new MatrixPanel(rows, columns, ModernWidget.PADDING,
     * ModernWidget.PADDING);
     * 
     * panel.add(new ModernLabel("Column")); panel.add(mColumnsCombo);
     * panel.addEmpty();
     * 
     * panel.add(new ModernAutoSizeLabel("New row id")); panel.add(new
     * ModernTextBorderPanel(mNewRowTextField)); mAddButton.setToolTip(new
     * ModernToolTip("Add Row Id",
     * "Add a new row identifier to search for. The row identifier applys only to the first column in a table."
     * )); mAddButton.addClickListener(this); panel.add(mAddButton);
     */

    // content.setBody(new ModernLineBorderPanel(new
    // ModernScrollPane(mRowList)));

    Box box;

    ModernScrollPane scrollPane = new ModernScrollPane(mIdTextBox).setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER)
        .setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    content.setBody(new ModernContentPanel(scrollPane));

    box = VBox.create();
    box.setBorder(ModernWidget.LEFT_BORDER);

    box.add(importButton);
    box.add(ModernPanel.createVGap());
    // box2.add(mRemoveButton);
    // box2.add(ModernPanel.createHGap());
    box.add(mClearButton);
    content.setRight(box);

    box = VBox.create();
    box.setBorder(ModernWidget.TOP_BORDER);
    box.add(UI.createVGap(10));
    box.add(mCheckExact);

    box.add(UI.createVGap(5));
    box.add(mCheckCase);

    content.setFooter(box);

    setCard(content);

    setSize(600, 500);

    setResizable(true);

    UI.centerWindowToScreen(this);

    // mNewRowTextField.addKeyListener(this);

    importButton.setToolTip("Import Row Ids",
        "Import multiple row identifiers from a text file. There should be one row id per line in the file.");
    importButton.addClickListener(this);

    // mRemoveButton.setToolTip("Remove Row Ids",
    // "Remove selected row identifiers from the list.");
    // mRemoveButton.addClickListener(this);

    mClearButton.setToolTip("Clear All Ids", "Remove all row identifiers from the list.");
    mClearButton.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(importButton)) {
      try {
        importIds();
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (InvalidFormatException e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource().equals(mClearButton)) {
      clearIds();
    } else {
      super.clicked(e);
    }
  }

  /**
   * Gets the rows.
   *
   * @return the rows
   */

  /**
   * Adds the id.
   */
  /*
   * private void addId() {
   * 
   * if (mNewRowTextField.getText() != null &&
   * !mNewRowTextField.getText().equals("")) {
   * mModel.addValue(mNewRowTextField.getText()); }
   * 
   * //rowList.adjustSize(); //scrollPane.adjustDisplay(); }
   */

  /**
   * Removes the id.
   */
  /*
   * private void removeId() {
   * 
   * Stack<Integer> ids = new Stack<Integer>();
   * 
   * for (int i : mRowList.getSelectionModel()) { ids.push(i); }
   * 
   * while (!ids.empty()) { mModel.removeValueAt(ids.pop()); } }
   */

  /**
   * Clear ids.
   */
  private void clearIds() {
    // mModel.clear();
    mIdTextBox.clear();
  }

  /**
   * Import ids.
   *
   * @throws IOException            Signals that an I/O exception has occurred.
   * @throws InvalidFormatException the invalid format exception
   */
  private void importIds() throws IOException, InvalidFormatException {

    Path file = FileDialog.open(mParent).filter(new XlsxTxtGuiFileFilter())
        .getFile(RecentFilesService.getInstance().getPwd());

    if (file != null) {
      importIds(file);
    }
  }

  /**
   * Import ids.
   *
   * @param file the file
   * @throws IOException            Signals that an I/O exception has occurred.
   * @throws InvalidFormatException the invalid format exception
   */
  private void importIds(Path file) throws IOException, InvalidFormatException {
    /*
     * List<String> ids = new ArrayList<String>();
     * 
     * BufferedReader reader = new BufferedReader(new FileReader(file));
     * 
     * String line;
     * 
     * try { // Skip header reader.readLine();
     * 
     * while ((line = reader.readLine()) != null) { if (Io.isEmptyLine(line)) {
     * continue; }
     * 
     * List<String> tokens = TextUtils.fastSplit(line, TextUtils.TAB_DELIMITER);
     * 
     * ids.add(tokens.get(0)); } } finally { reader.close(); }
     */

    loadIds(Excel.load(file, true));
  }

  /**
   * Load ids.
   *
   * @param ids the ids
   */
  private void loadIds(String[] ids) {
    /*
     * for (String id : ids) { mModel.addValue(id); }
     */

    mIdTextBox.setText(ids);
  }

  /**
   * Gets the exact match.
   *
   * @return the exact match
   */
  public boolean getExactMatch() {
    return mCheckExact.isSelected();
  }

  /**
   * Gets the names.
   * 
   * @param searchTermToReplacementMap
   * @param nameToSearchTermMap
   * @param names
   *
   * @return the names
   */
  public void getNames(List<String> names, Map<String, String> nameToSearchTermMap,
      Map<String, String> searchTermToNameMap, Map<String, String> searchTermToReplacementMap) {

    List<String> lines = mIdTextBox.getLines();

    for (String line : lines) {
      line = line.replace(':', TextUtils.TAB_DELIMITER_CHAR).replace(';', TextUtils.TAB_DELIMITER_CHAR)
          .replace(',', TextUtils.TAB_DELIMITER_CHAR).replace(':', TextUtils.TAB_DELIMITER_CHAR);

      List<String> tokens = TextUtils.tabSplit(line);

      String name = tokens.get(0);
      String replacement = tokens.get(1);

      String searchTerm;

      if (getCaseSensitive()) {
        searchTerm = name;
      } else {
        searchTerm = name.toLowerCase();
      }

      names.add(name);
      nameToSearchTermMap.put(name, searchTerm);
      searchTermToNameMap.put(searchTerm, name);
      searchTermToReplacementMap.put(searchTerm, replacement);
    }
  }

  /**
   * Gets the case sensitive.
   *
   * @return the case sensitive
   */
  public boolean getCaseSensitive() {
    return mCheckCase.isSelected();
  }

  /**
   * Returns true if items should be sorted by their rename label
   * 
   * @return
   */
  public boolean getSort() {
    return mCheckSort.isSelected();
  }
}
