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
package dev.antonyholmes.matcalc.toolbox.core.sort;

import java.util.List;

import javax.swing.Box;

import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.matcalc.MainMatCalcWindow;
import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.BorderService;
import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.ModernButton;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.icons.PlusVectorIcon;
import dev.antonyholmes.modern.graphics.icons.RedCrossIcon;
import dev.antonyholmes.modern.panel.HBox;
import dev.antonyholmes.modern.panel.ModernLineBorderPanel;
import dev.antonyholmes.modern.scrollpane.ModernScrollPane;
import dev.antonyholmes.modern.scrollpane.ScrollBarPolicy;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * The class MatchDialog.
 */
public class SortDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m add button. */
  private ModernButton mAddButton = new ModernButton("Add Level",
      AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16));

  /** The m clear button. */
  private ModernButton mClearButton = new ModernButton(UI.MENU_CLEAR,
      AssetService.getInstance().loadIcon(RedCrossIcon.class, 16));

  /** The m content panel. */
  private ColumnSorters mContentPanel;

  /**
   * Instantiates a new match dialog.
   *
   * @param parent the parent
   * @param m      the m
   * @param c      the c
   */
  public SortDialog(MainMatCalcWindow parent, DataFrame m, int c) {
    super(parent, "matcalc.sort.help.url");

    mContentPanel = new ColumnSorters(m);

    setTitle("Sort");

    setup();

    createUi();

    mContentPanel.add(c);
  }

  /**
   * Setup.
   */
  private void setup() {
    mAddButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        mContentPanel.add(-1);
      }

    });

    mClearButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        mContentPanel.clear();
      }

    });

    addWindowListener(new WindowWidgetFocusEvents(mOkButton));

    setSize(600, 300);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    ModernComponent content = new ModernComponent();

    Box box = HBox.create();

    box.add(mAddButton);
    box.add(UI.createHGap(5));
    box.add(mClearButton);

    box.setBorder(BorderService.getInstance().createBottomBorder(5));

    content.setHeader(box);

    ModernScrollPane scrollPane = new ModernScrollPane(mContentPanel);

    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);

    content.setBody(new ModernLineBorderPanel(scrollPane));

    setCard(content);
  }

  /**
   * Gets the sorters.
   *
   * @return the sorters
   */
  public List<ColumnSort> getSorters() {
    return mContentPanel.getSorters();
  }
}
