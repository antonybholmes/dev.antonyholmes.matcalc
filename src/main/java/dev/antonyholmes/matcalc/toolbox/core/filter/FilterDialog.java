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
package dev.antonyholmes.matcalc.toolbox.core.filter;

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
import dev.antonyholmes.modern.panel.HSpacedBox;
import dev.antonyholmes.modern.scrollpane.ModernScrollPane;
import dev.antonyholmes.modern.scrollpane.ScrollBarPolicy;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * The class MatchDialog.
 */
public class FilterDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m add button. */
  private ModernButton mAddButton = new ModernButton("Add Filter",
      AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16));

  /** The m clear button. */
  private ModernButton mClearButton = new ModernButton(UI.MENU_CLEAR,
      AssetService.getInstance().loadIcon(RedCrossIcon.class, 16));

  /** The m content panel. */
  private ColumnFilters mContentPanel;

  /**
   * Instantiates a new match dialog.
   *
   * @param parent the parent
   * @param m      the m
   * @param c      the c
   */
  public FilterDialog(MainMatCalcWindow parent, DataFrame m, int c) {
    super(parent, "matcalc.filter.help.url");

    mContentPanel = new ColumnFilters(m);

    setTitle("Filter " + m.getColumnName(c) + " column");

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

    setSize(640, 300);

    setResizable(true);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    ModernComponent content = new ModernComponent();

    Box box = new HSpacedBox();

    box.add(mAddButton);
    box.add(mClearButton);

    box.setBorder(BorderService.getInstance().createBottomBorder(10));

    content.setHeader(box);

    ModernScrollPane scrollPane = new ModernScrollPane(mContentPanel)
        .setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER).setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    content.setBody(scrollPane);

    setCard(content);
  }

  /**
   * Gets the column filters.
   *
   * @return the column filters
   */
  public List<ColumnFilter> getColumnFilters() {
    return mContentPanel.getColumnFilters();
  }
}
