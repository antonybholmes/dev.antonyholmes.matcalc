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

import org.jebtk.graphplot.figure.Axes;

import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.panel.ModernPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.text.ModernAutoSizeLabel;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class AxisPlotControl.
 */
public class AxesControl extends VBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new axis plot control.
   *
   * @param parent the parent
   * @param axes   the axes
   */
  public AxesControl(ModernWindow parent, Axes axes) {
    add(new LayerVisibleControl(parent, axes));

    add(ModernPanel.createVGap());

    add(new PlotTitleControl(parent, axes.getTitle()));

    add(UI.createVGap(10));

    add(new ModernAutoSizeLabel("LEGEND"));

    add(ModernPanel.createVGap());

    add(new LegendControl(parent, axes.getLegend()));

    add(UI.createVGap(10));

    add(new ModernAutoSizeLabel("MARGINS"));

    add(ModernPanel.createVGap());

    add(new MarginControl(parent, axes));

    add(UI.createVGap(10));

    add(new ModernAutoSizeLabel("SIZE"));

    add(ModernPanel.createVGap());

    add(new SizeControl(parent, axes));

    setBorder(ModernWidget.BORDER);
  }
}
