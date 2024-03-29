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

import org.jebtk.graphplot.figure.props.LegendProps;

import dev.antonyholmes.modern.panel.ModernPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class AxisPlotControl.
 */
public class LegendControl extends VBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new axis plot control.
   *
   * @param parent the parent
   * @param legend the legend
   */
  public LegendControl(ModernWindow parent, LegendProps legend) {
    add(new VisibleControl(parent, legend));

    add(ModernPanel.createVGap());

    add(new LegendPositionControl(legend));

    add(ModernPanel.createVGap());

    add(new LegendLinePlotControl(parent, legend));

    add(ModernPanel.createVGap());

    add(new LegendFillPlotControl(parent, legend));

    add(ModernPanel.createVGap());

    add(new InsideControl(parent, legend));
  }
}
