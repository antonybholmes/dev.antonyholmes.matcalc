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
package dev.antonyholmes.matcalc.toolbox.core.duplicate;

import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.dialog.ModernDialogHelpWindow;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class DuplicateDialog.
 */
public class DuplicateDialog extends ModernDialogHelpWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member duplicate panel.
   */
  private DuplicatePanel mDuplicatePanel;

  /**
   * The member matrix.
   */
  private DataFrame mMatrix;

  /**
   * Instantiates a new duplicate dialog.
   *
   * @param parent the parent
   * @param matrix the matrix
   */
  public DuplicateDialog(ModernWindow parent, DataFrame matrix) {
    super(parent, "matcalc.modules.duplicate.help.url");

    setTitle("Duplicate Rows");

    mMatrix = matrix;

    setup();

    createUi();

  }

  /**
   * Setup.
   */
  private void setup() {
    setSize(400, 200);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    mDuplicatePanel = new DuplicatePanel(mMatrix);

    setCard(mDuplicatePanel);
  }

  /**
   * Gets the delimiter.
   *
   * @return the delimiter
   */
  public String getDelimiter() {
    return mDuplicatePanel.getDelimiter();
  }
}
