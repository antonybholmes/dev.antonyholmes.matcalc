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
package dev.antonyholmes.matcalc.toolbox.plot.barchart;

import javax.swing.Box;

import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.matcalc.MatrixRowAnnotationCombo;
import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.dialog.ModernDialogTaskWindow;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.panel.MatrixPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.text.ModernAutoSizeLabel;
import dev.antonyholmes.modern.window.ModernWindow;
import dev.antonyholmes.modern.window.WindowWidgetFocusEvents;

/**
 * The class BarChartDialog.
 */
public class BarChartDialog extends ModernDialogTaskWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member row combo.
   */
  private MatrixRowAnnotationCombo mRowCombo;

  /**
   * Instantiates a new bar chart dialog.
   *
   * @param parent the parent
   * @param m      the m
   */
  public BarChartDialog(ModernWindow parent, DataFrame m) {
    super(parent);

    mRowCombo = new MatrixRowAnnotationCombo(m);

    setTitle("Plot Bar Chart");

    setup();

    createUi();

  }

  /**
   * Setup.
   */
  private void setup() {
    addWindowListener(new WindowWidgetFocusEvents(mOkButton));

    setSize(320, 240);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    Box content = VBox.create();

    int[] rows = { ModernWidget.WIDGET_HEIGHT };
    int[] cols = { 150, 120 };

    MatrixPanel matrixPanel;

    matrixPanel = new MatrixPanel(rows, cols, ModernWidget.PADDING, ModernWidget.PADDING);

    matrixPanel.add(new ModernAutoSizeLabel("Row Annotation"));
    matrixPanel.add(mRowCombo);

    matrixPanel.setBorder(ModernWidget.DOUBLE_BORDER);
    content.add(matrixPanel);

    setContent(content);
  }

  /**
   * Gets the annotation name.
   *
   * @return the annotation name
   */
  public String getAnnotationName() {
    return mRowCombo.getText();
  }
}
