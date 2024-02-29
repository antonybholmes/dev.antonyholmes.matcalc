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

import java.awt.Graphics2D;
import java.awt.Rectangle;

import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.graphics.icons.ModernIcon;
import dev.antonyholmes.modern.table.ModernTableCheckboxCellRenderer;
import dev.antonyholmes.modern.theme.ThemeService;

/**
 * Displays an icon in a table cell.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTableLayerCellRenderer extends ModernTableCheckboxCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant VISIBLE_ICON.
   */
  private final static ModernIcon VISIBLE_ICON = AssetService.getInstance().loadIcon("layer_visible",
      AssetService.ICON_SIZE_16);

  /**
   * The constant INVISIBLE_ICON.
   */
  private final static ModernIcon INVISIBLE_ICON = AssetService.getInstance().loadIcon("blank",
      AssetService.ICON_SIZE_16);

  /**
   * Instantiates a new modern table layer cell renderer.
   */
  public ModernTableLayerCellRenderer() {
    setCanHighlight(false);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.table.ModernTableCheckboxCellRenderer#
   * drawForegroundAA( java.awt.Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {

    int x = (this.getWidth() - AssetService.ICON_SIZE_16) / 2;
    int y = (this.getHeight() - AssetService.ICON_SIZE_16) / 2;

    if (selected) {
      VISIBLE_ICON.drawIcon(g2, x, y, AssetService.ICON_SIZE_16);
    } else {
      INVISIBLE_ICON.drawIcon(g2, x, y, AssetService.ICON_SIZE_16);
    }

    x = (this.getWidth() - AssetService.ICON_SIZE_20) / 2;
    y = (this.getHeight() - AssetService.ICON_SIZE_20) / 2;

    drawRect(g2, ThemeService.getInstance().getColors().getTheme(2),
        new Rectangle(x, y, AssetService.ICON_SIZE_20, AssetService.ICON_SIZE_20));
  }
}