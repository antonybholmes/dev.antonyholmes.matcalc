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
package dev.antonyholmes.matcalc.toolbox.plot.scatter;

import java.awt.Color;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.graphplot.ColorCycle;
import org.jebtk.graphplot.PlotFactory;
import org.jebtk.graphplot.figure.Axes;
import org.jebtk.graphplot.figure.Figure;
import org.jebtk.graphplot.figure.SubFigure;
import org.jebtk.graphplot.figure.series.XYSeries;
import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.matcalc.MainMatCalcWindow;
import dev.antonyholmes.matcalc.figure.graph2d.Graph2dWindow;
import dev.antonyholmes.matcalc.toolbox.Module;
import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.ribbon.RibbonLargeButton;

/**
 * The class ScatterLineModule.
 */
public class ScatterLineModule extends Module implements ModernClickListener {

  /**
   * The member parent.
   */
  private MainMatCalcWindow mParent;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.NameProperty#getName()
   */
  @Override
  public String getName() {
    return "Scatter Line Graph";
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.columbia.rdf.apps.matcalc.modules.Module#init(edu.columbia.rdf.apps.
   * matcalc.MainMatCalcWindow)
   */
  @Override
  public void init(MainMatCalcWindow window) {
    mParent = window;

    RibbonLargeButton button = new RibbonLargeButton("Scatter Line Graph",
        AssetService.getInstance().loadIcon("scatter_line_graph", 24), "Scatter Line Graph",
        "Generate a scatter line graph.");
    button.addClickListener(this);
    button.setEnabled(false);

    mParent.getRibbon().getToolbar("Plot").getSection("Plot").add(button);
  }

  /**
   * Creates the plot.
   */
  private void createPlot() {
    DataFrame m = mParent.getCurrentMatrix();

    Figure figure = Figure.createFigure();

    SubFigure graph = figure.currentSubFigure();

    Axes axes = graph.currentAxes();

    ColorCycle colorCycle = new ColorCycle();

    for (int i = 0; i < m.getCols(); i += 2) {
      Color color = colorCycle.next();

      XYSeries series = new XYSeries(TextUtils.commonPrefix(m.getColumnName(i), m.getColumnName(i + 1)), color);

      series.getStyle().getFillStyle().setColor(ColorUtils.getTransparentColor50(color));
      series.getStyle().getLineStyle().setColor(color);

      PlotFactory.createScatterLinePlot(m, axes, series);
    }

    axes.setAxisLimitsAutoRound();

    // gp.getPlotLayout().setPlotSize(new Dimension(800, 800));

    // How big to make the x axis
    // double min = Mathematics.min(log2FoldChanges);
    // double max = Mathematics.max(log2FoldChanges);

    // gp.getXAxis().autoSetLimits(min, max);
    // gp.getXAxis().getMajorTicks().set(Linspace.evenlySpaced(min, max, inc));
    // gp.getXAxis().getMajorTickMarks().setNumbers(Linspace.evenlySpaced(min,
    // max,
    // inc));
    axes.getX1Axis().getTitle().setText("Series");

    // min = Mathematics.min(minusLog10PValues);
    // max = Mathematics.max(minusLog10PValues);

    // System.err.println("my " + min + " " + max);

    // gp.getYAxis().autoSetLimits(min, max);
    // gp.getYAxis().getMajorTicks().set(Linspace.evenlySpaced(min, max, inc));
    // gp.getYAxis().getMajorTickMarks().setNumbers(Linspace.evenlySpaced(min,
    // max,
    // inc));
    axes.getY1Axis().getTitle().setText("Count");

    Graph2dWindow window = new Graph2dWindow(mParent, figure);

    window.setVisible(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    createPlot();
  }

}
