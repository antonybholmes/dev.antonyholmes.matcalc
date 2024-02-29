package edu.columbia.rdf.matcalc.toolbox.supervised;

import dev.antonyholmes.modern.ModernWidget;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.combobox.ModernComboBox;

public class SortCombo extends ModernComboBox {

  private static final long serialVersionUID = 1L;

  public SortCombo() {
    addMenuItem("Fold Change");
    addMenuItem("Z-score");

    setSelectedIndex(1);

    UI.setSize(this, 200, ModernWidget.WIDGET_HEIGHT);
  }

  public SortType getSortType() {
    switch (getSelectedIndex()) {
    case 0:
      return SortType.FOLD_CHANGE;
    default:
      return SortType.Z_SCORE;
    }
  }
}
