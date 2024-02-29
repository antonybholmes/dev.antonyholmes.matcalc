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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.Box;

import org.jebtk.math.matrix.DataFrame;
import org.jebtk.math.matrix.MatrixCellRef;
import org.jebtk.math.ui.matrix.MatrixTable;

import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.CheckBox;
import dev.antonyholmes.modern.button.ModernButton;
import dev.antonyholmes.modern.button.ModernCheckBox;
import dev.antonyholmes.modern.dialog.MessageDialogType;
import dev.antonyholmes.modern.dialog.ModernDialogButton;
import dev.antonyholmes.modern.dialog.ModernDialogTaskType;
import dev.antonyholmes.modern.dialog.ModernDialogTaskWindow;
import dev.antonyholmes.modern.dialog.ModernMessageDialog;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.panel.MatrixPanel;
import dev.antonyholmes.modern.panel.ModernPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.tabs.SegmentTabsPanel;
import dev.antonyholmes.modern.tabs.TabEvent;
import dev.antonyholmes.modern.tabs.TabEventListener;
import dev.antonyholmes.modern.tabs.TabsModel;
import dev.antonyholmes.modern.tabs.TabsViewPanel;
import dev.antonyholmes.modern.text.ModernAutoSizeLabel;
import dev.antonyholmes.modern.text.ModernTextBorderPanel;
import dev.antonyholmes.modern.text.ModernTextField;
import dev.antonyholmes.modern.window.ModernWindow;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * User can enter an integer option value.
 * 
 * @author Antony Holmes
 *
 */
public class FindReplaceDialog extends ModernDialogTaskWindow implements TabEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member find next button.
   */
  private ModernButton mFindNextButton = new ModernDialogButton("Find Next");

  /**
   * The member replace all button.
   */
  private ModernButton mReplaceAllButton = new ModernDialogButton("Replace All");

  /**
   * The member replace button.
   */
  private ModernButton mReplaceButton = new ModernDialogButton("Replace");

  /**
   * The member find field.
   */
  private ModernTextField mFindField = new ModernTextField();

  /**
   * The member replace find field.
   */
  private ModernTextField mReplaceFindField = new ModernTextField();

  /**
   * The member replace field.
   */
  private ModernTextField mReplaceField = new ModernTextField();

  /**
   * The member check find case sensitive.
   */
  private CheckBox mCheckFindCaseSensitive = new ModernCheckBox("Case sensitive");

  /**
   * The member check find entire cell.
   */
  private CheckBox mCheckFindEntireCell = new ModernCheckBox("Match entire cell contents");

  /**
   * The member check replace case sensitive.
   */
  private CheckBox mCheckReplaceCaseSensitive = new ModernCheckBox("Case sensitive");

  /**
   * The member check replace entire cell.
   */
  private CheckBox mCheckReplaceEntireCell = new ModernCheckBox("Match entire cell contents");

  /**
   * The member m.
   */
  private DataFrame mM;

  /**
   * The member start cell.
   */
  private MatrixCellRef mStartCell = DataFrame.START_CELL;

  /**
   * The member table.
   */
  private MatrixTable mTable;

  /**
   * The member group tabs model.
   */
  private TabsModel mGroupTabsModel = new TabsModel();

  /**
   * The class KeyEvents.
   */
  private class KeyEvents implements KeyListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        find();
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

  }

  /**
   * Instantiates a new find replace dialog.
   *
   * @param parent the parent
   */
  public FindReplaceDialog(ModernWindow parent) {
    super(parent, false, ModernDialogTaskType.CLOSE);

    setTitle("Find");

    setup();

    createUi();

  }

  /**
   * Setup.
   */
  private void setup() {
    mFindNextButton.addClickListener(this);
    mReplaceAllButton.addClickListener(this);
    mReplaceButton.addClickListener(this);

    setSize(480, 300);

    setResizable(true);

    UI.centerWindowToScreen(this);

    addWindowFocusListener(new WindowWidgetFocusEvents(mFindField));

    KeyEvents l = new KeyEvents();

    mFindField.addKeyListener(l);
    mReplaceFindField.addKeyListener(l);
  }

  /**
   * Creates the ui.
   */
  public final void createUi() {
    ModernComponent content = new ModernComponent();

    SegmentTabsPanel groupTabs = new SegmentTabsPanel(mGroupTabsModel, 100);

    content.setHeader(groupTabs);

    Box box = VBox.create();

    int[] rows = { ModernButton.getButtonHeight() };
    int[] cols = { 100, 300 };

    MatrixPanel matrixPanel = new MatrixPanel(rows, cols, ModernWidget.PADDING, ModernWidget.PADDING);

    matrixPanel.add(new ModernAutoSizeLabel("Find what:"));
    matrixPanel.add(new ModernTextBorderPanel(mFindField));

    box.add(matrixPanel);

    box.add(ModernWidget.createVGap());

    box.add(mCheckFindCaseSensitive);

    box.add(ModernWidget.createVGap());

    box.add(mCheckFindEntireCell);

    mGroupTabsModel.addTab("FIND", box);

    // Replace

    box = VBox.create();

    matrixPanel = new MatrixPanel(rows, cols, ModernWidget.PADDING, ModernWidget.PADDING);

    matrixPanel.add(new ModernAutoSizeLabel("Find what:"));
    matrixPanel.add(new ModernTextBorderPanel(mReplaceFindField));

    matrixPanel.add(new ModernAutoSizeLabel("Replace with:"));
    matrixPanel.add(new ModernTextBorderPanel(mReplaceField));

    box.add(matrixPanel);

    box.add(ModernWidget.createVGap());

    box.add(mCheckReplaceCaseSensitive);

    box.add(ModernWidget.createVGap());

    box.add(mCheckReplaceEntireCell);

    mGroupTabsModel.addTab("REPLACE", box);

    content.setBody(new ModernComponent(new TabsViewPanel(mGroupTabsModel), ModernWidget.TOP_BORDER));

    setContent(content);

    getButtonBar().addLeft(mFindNextButton);
    getButtonBar().addLeft(ModernPanel.createHGap());
    getButtonBar().addLeft(mReplaceAllButton);
    getButtonBar().addLeft(ModernPanel.createHGap());
    getButtonBar().addLeft(mReplaceButton);

    mGroupTabsModel.addTabListener(this);

    // Make the find tab visible
    mGroupTabsModel.changeTab("FIND");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public final void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mFindNextButton)) {
      find();
    } else if (e.getSource().equals(mReplaceButton)) {
      replace();
    } else if (e.getSource().equals(mReplaceAllButton)) {
      replaceAll();
    } else {
      close();
    }
  }

  /**
   * Find.
   */
  private void find() {
    String text;

    if (mGroupTabsModel.getSelectedTab().getName().equals("FIND")) {
      text = mFindField.getText();
    } else {
      text = mReplaceFindField.getText();
    }

    MatrixCellRef cell = DataFrame.find(mM, text, mCheckFindCaseSensitive.isSelected(),
        mCheckFindEntireCell.isSelected(), mStartCell);

    if (cell == null) {
      ModernMessageDialog.createDialog(mParent, "No matches were found.", MessageDialogType.WARNING);

      return;
    }

    mStartCell = cell;

    System.err.println("found " + cell.row + " " + cell.column);

    mTable.setViewCell(cell.row, cell.column);

    if (cell.row >= 0 && cell.column >= 0) {
      // If the selection is within the matrix itself, rather than
      // the meta data columns, highlight it.
      mTable.setSelectedCell(cell.row, cell.column, false, false);
    }
  }

  /**
   * Replace.
   */
  private void replace() {
    if (mStartCell == null) {
      ModernMessageDialog.createDialog(mParent, "Cannot find a match.", MessageDialogType.WARNING);

      return;
    }

    Pattern pattern;

    if (mCheckReplaceCaseSensitive.isSelected()) {
      pattern = Pattern.compile(mReplaceFindField.getText());
    } else {
      pattern = Pattern.compile(mReplaceFindField.getText(), Pattern.CASE_INSENSITIVE);
    }

    String ret = pattern.matcher(mM.getText(mStartCell.row, mStartCell.column)).replaceAll(mReplaceField.getText());

    System.err.println(ret + " " + pattern);

    mM.set(mStartCell.row, mStartCell.column, ret);
  }

  /**
   * Replace all.
   */
  private void replaceAll() {
    List<MatrixCellRef> cells = DataFrame.findAll(mM, mReplaceFindField.getText(),
        mCheckReplaceCaseSensitive.isSelected(), mCheckReplaceEntireCell.isSelected(), mStartCell);

    if (cells.size() == 0) {
      ModernMessageDialog.createDialog(mParent, "Cannot find a match.", MessageDialogType.WARNING);

      return;
    }

    Pattern pattern;

    if (mCheckReplaceCaseSensitive.isSelected()) {
      pattern = Pattern.compile(mReplaceFindField.getText());
    } else {
      pattern = Pattern.compile(mReplaceFindField.getText(), Pattern.CASE_INSENSITIVE);
    }

    for (MatrixCellRef cell : cells) {
      String ret = pattern.matcher(mM.getText(cell.row, cell.column)).replaceAll(mReplaceField.getText());

      mM.set(cell.row, cell.column, ret);
    }

    ModernMessageDialog.createDialog(mParent,
        cells.size() > 1 ? cells.size() + " replacements were made." : "1 replacements was made.",
        MessageDialogType.INFORMATION);
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  public String getText() {
    return mFindField.getText();
  }

  /**
   * Gets the case sensitive.
   *
   * @return the case sensitive
   */
  public boolean getCaseSensitive() {
    return false;
  }

  /**
   * Sets the visible.
   *
   * @param m     the m
   * @param table the table
   */
  public void setVisible(DataFrame m, MatrixTable table) {
    mM = m;
    mTable = table;

    mStartCell = DataFrame.START_CELL;

    setVisible(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernDialogWindow#close()
   */
  @Override
  public void close() {
    setVisible(false);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabChanged(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    boolean v = mGroupTabsModel.getSelectedTab().getName().equals("REPLACE");

    mReplaceAllButton.setVisible(v);
    mReplaceButton.setVisible(v);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabEventListener#tabAdded(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabResized(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabResized(TabEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void tabHighlighted(TabEvent e) {
    // TODO Auto-generated method stub

  }
}
