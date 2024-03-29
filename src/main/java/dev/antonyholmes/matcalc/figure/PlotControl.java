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

import javax.swing.Box;

import org.jebtk.graphplot.figure.Plot;
import org.jebtk.graphplot.figure.series.XYSeries;

import dev.antonyholmes.matcalc.colormap.ColorMapControl;
import dev.antonyholmes.matcalc.figure.graph2d.XYSeriesPlotControl;
import dev.antonyholmes.modern.collapsepane.ModernSubCollapsePane;
import dev.antonyholmes.modern.panel.ModernPanel;
import dev.antonyholmes.modern.panel.VBox;
import dev.antonyholmes.modern.window.ModernWindow;

/**
 * The class AxisPlotControl.
 */
public class PlotControl extends ModernSubCollapsePane {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new axis plot control.
   *
   * @param parent the parent
   * @param plot   the plot
   */
  public PlotControl(ModernWindow parent, Plot plot) {
    Box box = VBox.create();
    box.add(new LayerVisibleControl(parent, plot));
    box.add(ModernPanel.createVGap());
    box.add(new ColorMapControl(parent, plot));
    box.add(ModernPanel.createVGap());
    box.setBorder(BORDER);

    addTab("Options", box);

    if (plot.getChildCount() > 0) {
      addTab("Layers", new PlotLayersVisibleControl(parent, plot));
    }

    int seriesId = 1;

    for (XYSeries series : plot.getAllSeries()) {
      addTab("Series " + seriesId, new XYSeriesPlotControl(parent, series));

      ++seriesId;
    }
  }
}
