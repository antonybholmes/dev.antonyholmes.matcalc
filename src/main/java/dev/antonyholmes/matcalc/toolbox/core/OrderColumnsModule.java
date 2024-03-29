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
package dev.antonyholmes.matcalc.toolbox.core;

import org.jebtk.graphplot.figure.series.XYSeriesGroup;
import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.matcalc.MainMatCalcWindow;
import dev.antonyholmes.matcalc.toolbox.Module;
import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.icons.RunVectorIcon;
import dev.antonyholmes.modern.ribbon.RibbonLargeButton;

/**
 * Order columns by groups
 *
 * @author Antony Holmes
 */
public class OrderColumnsModule extends Module implements ModernClickListener {

  /**
   * The button.
   */
  private RibbonLargeButton mButton = new RibbonLargeButton(
      AssetService.getInstance().loadIcon(RunVectorIcon.class, 24), "Order Columns", "Order columns by groups.");

  /**
   * The member window.
   */
  private MainMatCalcWindow mWindow;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.NameProperty#getName()
   */
  @Override
  public String getName() {
    return "Order Columns";
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.columbia.rdf.apps.matcalc.modules.Module#init(edu.columbia.rdf.apps.
   * matcalc.MainMatCalcWindow)
   */
  @Override
  public void init(MainMatCalcWindow window) {
    mWindow = window;

    mButton.addClickListener(this);

    mWindow.getRibbon().getToolbar("Data").getSection("Sort").add(mButton);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    order();
  }

  /**
   * Order.
   */
  private void order() {
    DataFrame m = mWindow.getCurrentMatrix();

    if (m == null) {
      return;
    }

    XYSeriesGroup groups = mWindow.getGroups();

    if (groups.size() == 0) {
      return;
    }

    DataFrame ret = DataFrame.copyColumns(m, groups);

    mWindow.history().addToHistory("Order columns", ret);
  }
}
