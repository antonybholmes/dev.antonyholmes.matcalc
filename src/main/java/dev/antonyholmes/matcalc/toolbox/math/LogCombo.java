package dev.antonyholmes.matcalc.toolbox.math;

import dev.antonyholmes.modern.combobox.ModernComboBox;

public class LogCombo extends ModernComboBox {
  private static final long serialVersionUID = 1L;

  public LogCombo() {
    addMenuItem("None");
    addMenuItem("Log 2");
    addMenuItem("Log 10");
  }

  public LogType getLogType() {
    switch (getSelectedIndex()) {
    case 1:
      return LogType.LOG2;
    case 2:
      return LogType.LOG10;
    default:
      return LogType.NONE;
    }
  }
}
