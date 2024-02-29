/**
normalize() * Copyright 2016 Antony Holmes
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
package dev.antonyholmes.matcalc.toolbox.math;

import org.jebtk.math.matrix.utils.MatrixOperations;

import dev.antonyholmes.matcalc.MainMatCalcWindow;
import dev.antonyholmes.matcalc.toolbox.WinModule;
import dev.antonyholmes.matcalc.toolbox.plot.heatmap.NormalizeDialog;
import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.dialog.ModernDialogStatus;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.menu.ModernPopupMenu2;
import dev.antonyholmes.modern.menu.ModernTwoLineMenuItem;
import dev.antonyholmes.modern.ribbon.RibbonLargeDropDownButton2;

/**
 * The class ZScoreModule.
 */
public class NormalizeModule extends WinModule implements ModernClickListener {

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.NameProperty#getName()
   */
  @Override
  public String getName() {
    return "Normalize";
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.columbia.rdf.apps.matcalc.modules.Module#init(edu.columbia.rdf.apps.
   * matcalc.MainMatCalcWindow)
   */
  @Override
  public void init(MainMatCalcWindow window) {
    super.init(window);

    ModernPopupMenu2 popup = new ModernPopupMenu2();

    popup.addMenuItem(new ModernTwoLineMenuItem("Normalize", "Normalize values between 0 and 1.",
        AssetService.getInstance().loadIcon("normalize", 32)));
    popup.addMenuItem(new ModernTwoLineMenuItem("Quantile Normalize", "Quantile normalize values.",
        AssetService.getInstance().loadIcon("normalize", 32)));
    popup.addMenuItem(new ModernTwoLineMenuItem("Median Ratios", "Median ratios.",
        AssetService.getInstance().loadIcon("normalize", 32)));

    // The default behaviour is to do a log2 transform.
    RibbonLargeDropDownButton2 button = new RibbonLargeDropDownButton2("Normalize",
        AssetService.getInstance().loadIcon("normalize", 32), popup);
    button.setChangeText(false);
    button.setToolTip("Normalize", "Normalization functions.");
    mWindow.getRibbon().getToolbar("Formulas").getSection("Functions").add(button);
    button.addClickListener(this);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getMessage().equals("Normalize")) {
      NormalizeDialog dialog = new NormalizeDialog(mWindow);

      dialog.setVisible(true);

      if (dialog.getStatus() == ModernDialogStatus.OK) {
        if (dialog.getAuto()) {
          mWindow.history().addToHistory("Normalize", MatrixOperations.scale(mWindow.getCurrentMatrix()));
        } else {
          mWindow.history().addToHistory("Normalize",
              MatrixOperations.scale(mWindow.getCurrentMatrix(), dialog.getMin(), dialog.getMax()));
        }
      }
    } else if (e.getMessage().equals("Quantile Normalize")) {
      mWindow.history().addToHistory("Quantile Normalized",
          MatrixOperations.quantileNormalize(mWindow.getCurrentMatrix()));
    } else if (e.getMessage().equals("Median Ratios")) {
      mWindow.history().addToHistory("Median Ratios", MatrixOperations.medianRatio(mWindow.getCurrentMatrix()));
    } else {
      // Do nothing
    }
  }
}
