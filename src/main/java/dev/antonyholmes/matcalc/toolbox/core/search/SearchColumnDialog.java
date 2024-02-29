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
package dev.antonyholmes.matcalc.toolbox.core.search;

import java.util.List;

import javax.swing.Box;

import dev.antonyholmes.matcalc.MainMatCalcWindow;
import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.CheckBox;
import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.scrollpane.ModernScrollPane;
import dev.antonyholmes.modern.scrollpane.ScrollBarPolicy;
import dev.antonyholmes.modern.text.ModernTextArea;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * The class MatchDialog.
 */
public class SearchColumnDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m check in list. */
  private CheckBox mCheckInList = new ModernCheckSwitch("Match in list", true);

  /** The m check exact. */
  private CheckBox mCheckExact = new ModernCheckSwitch("Match entire cell contents");

  /** The m check case. */
  private CheckBox mCheckCase = new ModernCheckSwitch("Case sensitive");

  /** The m text. */
  private ModernTextArea mText = new ModernTextArea();

  /**
   * Instantiates a new match dialog.
   *
   * @param parent the parent
   */
  public SearchColumnDialog(MainMatCalcWindow parent) {
    super(parent, "matcalc.search-column.help.url");

    setTitle("Search Column");

    setup();

    createUi();
  }

  /**
   * Setup.
   */
  private void setup() {
    addWindowListener(new WindowWidgetFocusEvents(mOkButton));

    setSize(640, 360);

    setResizable(true);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    ModernComponent content = new ModernComponent();

    // content.setHeader(new ModernSubHeadingLabel("Search for:",
    // BorderService.getInstance().createBottomBorder(5)));

    ModernScrollPane scrollPane = new ModernScrollPane(mText).setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER)
        .setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    content.setBody(scrollPane);

    Box box = VBox.create();

    box.add(UI.createVGap(10));
    box.add(mCheckInList);
    box.add(UI.createVGap(5));
    box.add(mCheckExact);
    box.add(UI.createVGap(5));
    box.add(mCheckCase);

    content.setFooter(box);

    setCard(content);
  }

  /**
   * Gets the window name.
   *
   * @return the window name
   */
  public List<String> getLines() {
    return mText.getLines();
  }

  /**
   * Case sensitive.
   *
   * @return true, if successful
   */
  public boolean caseSensitive() {
    return mCheckCase.isSelected();
  }

  /**
   * Gets the in list.
   *
   * @return the in list
   */
  public boolean getInList() {
    return mCheckInList.isSelected();
  }

  /**
   * Gets the exact.
   *
   * @return the exact
   */
  public boolean getExact() {
    return mCheckExact.isSelected();
  }
}
