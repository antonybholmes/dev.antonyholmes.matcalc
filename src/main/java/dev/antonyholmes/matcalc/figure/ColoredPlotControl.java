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
package dev.antonyholmes.matcalc.figure;

import java.awt.Color;

import javax.swing.Box;

import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.button.ModernTwoStateWidget;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.color.ColorSwatchButton2;
import dev.antonyholmes.modern.panel.HBox;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class ColoredPlotControl.
 */
public class ColoredPlotControl extends HBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member check box.
   */
  private ModernTwoStateWidget mCheckBox;

  /**
   * The member color button.
   */
  private ColorSwatchButton2 mColorButton;

  /**
   * Instantiates a new colored plot control.
   *
   * @param parent the parent
   * @param name   the name
   * @param color  the color
   */
  public ColoredPlotControl(ModernWindow parent, String name, Color color) {
    this(parent, name, color, true);
  }

  /**
   * Instantiates a new colored plot control.
   *
   * @param parent   the parent
   * @param name     the name
   * @param color    the color
   * @param selected the selected
   */
  public ColoredPlotControl(ModernWindow parent, String name, Color color, boolean selected) {
    mCheckBox = new ModernCheckSwitch(name);
    mCheckBox.setSelected(selected);

    mColorButton = new ColorSwatchButton2(parent, color);

    add(mCheckBox);
    add(Box.createHorizontalGlue());
    add(mColorButton);

    setAlignmentY(TOP_ALIGNMENT);
  }

  /**
   * Adds the click listener.
   *
   * @param l the l
   */
  public void addClickListener(ModernClickListener l) {
    mCheckBox.addClickListener(l);
    mColorButton.addClickListener(l);
  }

  /**
   * Checks if is selected.
   *
   * @return true, if is selected
   */
  public boolean isSelected() {
    return mCheckBox.isSelected();
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public Color getSelectedColor() {
    return isSelected() ? mColorButton.getSelectedColor() : null;
  }

}
