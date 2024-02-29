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
package edu.columbia.rdf.matcalc.figure;

import org.jebtk.graphplot.figure.heatmap.legacy.ColumnLabelPosition;

import dev.antonyholmes.modern.button.ModernButtonGroup;
import dev.antonyholmes.modern.button.ModernRadioButton;
import dev.antonyholmes.modern.button.ModernTwoStateWidget;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.panel.VBox;

/**
 * The class ColumnLabelPositionPlotElement.
 */
public class ColumnLabelPositionPlotElement extends VBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The top check.
   */
  private ModernTwoStateWidget mTopCheck;

  /**
   * The bottom check.
   */
  private ModernTwoStateWidget mBottomCheck;

  /**
   * Instantiates a new column label position plot element.
   */
  public ColumnLabelPositionPlotElement() {
    mTopCheck = new ModernRadioButton("Top");
    add(mTopCheck);
    // add(ModernPanel.createVGap());
    mBottomCheck = new ModernRadioButton("Bottom");
    add(mBottomCheck);

    new ModernButtonGroup(mTopCheck, mBottomCheck);

    mTopCheck.setSelected(true);
  }

  /**
   * Adds the click listener.
   *
   * @param l the l
   */
  public void addClickListener(ModernClickListener l) {
    mTopCheck.addClickListener(l);
    mBottomCheck.addClickListener(l);
  }

  /**
   * Gets the position.
   *
   * @return the position
   */
  public ColumnLabelPosition getPosition() {
    return mTopCheck.isSelected() ? ColumnLabelPosition.TOP : ColumnLabelPosition.BOTTOM;
  }

}
