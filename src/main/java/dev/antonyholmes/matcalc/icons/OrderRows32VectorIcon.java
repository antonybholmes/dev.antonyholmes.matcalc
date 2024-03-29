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
package dev.antonyholmes.matcalc.icons;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;

import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.graphics.icons.ModernVectorIcon;
import dev.antonyholmes.modern.theme.ThemeService;

/**
 * Download arrow vector icon.
 * 
 * @author Antony Holmes
 *
 */
public class OrderRows32VectorIcon extends ModernVectorIcon {

  /**
   * The constant WIDTH.
   */
  private static final int WIDTH = 30;

  /**
   * The constant SIZE.
   */
  private static final int SIZE = 7;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props params) {
    x = x + (w - WIDTH) / 2;
    y = y + (h - WIDTH) / 2;

    g2.setColor(ColorUtils.decodeHtmlColor("#87de87"));

    g2.fillOval(x, y, SIZE, SIZE);
    g2.fillOval(x, y + SIZE + SIZE / 2, SIZE, SIZE);
    g2.fillOval(x, y + 3 * SIZE, SIZE, SIZE);
    // g2.fillOval(x, y + 4 * SIZE + SIZE / 2, SIZE, SIZE);

    y = y + WIDTH / 4;
    x = 16;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(x, y);

    // across
    gp.lineTo(x + 6, y);
    // down
    gp.lineTo(x + 6, y + WIDTH / 2 - 3);
    // across
    gp.lineTo(x + 9, y + WIDTH / 2 - 3);

    // down
    gp.lineTo(x + 3, y + WIDTH / 2 + 3);

    gp.lineTo(x - 3, y + WIDTH / 2 - 3);
    gp.lineTo(x, y + WIDTH / 2 - 3);

    gp.closePath();

    g2.setColor(ModernWidget.BACKGROUND_COLOR);
    g2.fill(gp);
    g2.setColor(ThemeService.getInstance().getColors().getGray(6));
    g2.draw(gp);
  }
}
