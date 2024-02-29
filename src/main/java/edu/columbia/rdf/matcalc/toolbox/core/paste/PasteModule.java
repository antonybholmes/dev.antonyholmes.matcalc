package edu.columbia.rdf.matcalc.toolbox.core.paste;

import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.button.ModernButton;
import dev.antonyholmes.modern.dialog.ModernDialogStatus;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.ribbon.Ribbon;
import dev.antonyholmes.modern.tooltip.ModernToolTip;
import edu.columbia.rdf.matcalc.MainMatCalcWindow;
import edu.columbia.rdf.matcalc.toolbox.Module;

public class PasteModule extends Module implements ModernClickListener {

  public final static Logger LOG = LoggerFactory.getLogger(PasteModule.class);

  private MainMatCalcWindow mWindow;

  @Override
  public String getName() {
    return "Paste";
  }

  @Override
  public void init(MainMatCalcWindow window) {
    mWindow = window;

    Ribbon ribbon = window.getRibbon();

    ModernButton button = ribbon.getToolbar("Data").getSection("Tools")
        .createButton(AssetService.getInstance().loadIcon("paste_files", 24));
    button.setToolTip(new ModernToolTip("Paste Files", "Paste multiple files column wise."));
    button.addClickListener(this);
  }

  @Override
  public void clicked(ModernClickEvent e) {
    paste();
  }

  private void paste() {
    PasteDialog dialog = new PasteDialog(mWindow);

    dialog.setVisible(true);

    if (dialog.getStatus() == ModernDialogStatus.CANCEL) {
      return;
    }

    List<Path> files = dialog.getFiles();

    if (files.size() == 0) {
      return;
    }

    PasteTask task = new PasteTask(mWindow, files, dialog.getDelimiter(), dialog.getCommonIndex());

    task.doInBackground();
  }
}
