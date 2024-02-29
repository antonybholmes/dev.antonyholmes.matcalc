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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.graphplot.figure.props.VisibleProps;

import dev.antonyholmes.modern.button.CheckBox;
import dev.antonyholmes.modern.button.ModernCheckSwitch;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.panel.HBox;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class AxisVisibleControl.
 */
public class VisibleControl extends HBox implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member check box.
   */
  private CheckBox mCheckBox;

  /**
   * The member axis.
   */
  private VisibleProps mVp;

  /**
   * Instantiates a new axis visible control.
   *
   * @param parent the parent
   * @param vp     the vp
   */
  public VisibleControl(ModernWindow parent, VisibleProps vp) {
    mCheckBox = new ModernCheckSwitch("Show");

    add(mCheckBox);
    // setBorder(ModernWidget.BORDER);

    mCheckBox.addClickListener(this);

    changeProperties(vp);

    mVp.addChangeListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent e) {
        mCheckBox.setSelected(mVp.getVisible());
      }
    });
  }

  /**
   * Change properties.
   *
   * @param vp the vp
   */
  public void changeProperties(VisibleProps vp) {
    mVp = vp;

    mCheckBox.setSelected(vp.getVisible());
  }

  /**
   * Checks if is selected.
   *
   * @return true, if is selected
   */
  public boolean isSelected() {
    return mCheckBox.isSelected();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.ui.event.ModernClickListener#clicked(org.abh.common.ui. ui.
   * event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    mVp.setVisible(mCheckBox.isSelected());
  }

}
