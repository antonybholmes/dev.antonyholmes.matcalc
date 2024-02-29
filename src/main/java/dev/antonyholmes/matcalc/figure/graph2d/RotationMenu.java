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
package dev.antonyholmes.matcalc.figure.graph2d;

import dev.antonyholmes.modern.graphics.icons.ArrowDownVectorIcon;
import dev.antonyholmes.modern.graphics.icons.ArrowRightVectorIcon;
import dev.antonyholmes.modern.graphics.icons.Raster16Icon;
import dev.antonyholmes.modern.menu.ModernIconMenuItem;
import dev.antonyholmes.modern.menu.ModernPopupMenu;

/**
 * The class RotationMenu.
 */
public class RotationMenu extends ModernPopupMenu {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new rotation menu.
   */
  public RotationMenu() {
    addMenuItem(new ModernIconMenuItem("0", new Raster16Icon(new ArrowRightVectorIcon())));
    addMenuItem(new ModernIconMenuItem("90", new Raster16Icon(new ArrowDownVectorIcon())));
    // addMenuItem(new ModernIconMenuItem("180",
    // new Raster16Icon(new ArrowRightVectorIcon())));
    // addMenuItem(new ModernIconMenuItem("270",
    // new Raster16Icon(new ArrowUpVectorIcon())));
  }
}
