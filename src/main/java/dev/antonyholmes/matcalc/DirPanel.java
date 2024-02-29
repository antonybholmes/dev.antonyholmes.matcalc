package dev.antonyholmes.matcalc;

import java.nio.file.Path;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;

import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.ModernComponent;
import dev.antonyholmes.modern.button.ModernButton;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.icons.FolderVectorIcon;
import dev.antonyholmes.modern.io.FileDialog;
import dev.antonyholmes.modern.io.ModernFileCrumb;
import dev.antonyholmes.modern.io.PwdModel;
import dev.antonyholmes.modern.io.RecentFilesService;
import dev.antonyholmes.modern.window.ModernWindow;

public class DirPanel extends ModernComponent {
  private static final long serialVersionUID = 1L;

  private ModernFileCrumb mFileCrumb = new ModernFileCrumb(RecentFilesService.getInstance().getPwd());

  private ModernButton mDirButton = new ModernButton(AssetService.getInstance().loadIcon(FolderVectorIcon.class, 16));

  private PwdModel mModel;

  private ModernWindow mParent;

  public DirPanel(ModernWindow parent, PwdModel model) {
    mParent = parent;
    mModel = model;

    mFileCrumb = new ModernFileCrumb(model.getPwd());

    setLeft(mDirButton);
    setBody(mFileCrumb);

    setBorder(BORDER);

    mDirButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        setDir();
      }
    });

    model.addChangeListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent e) {
        mFileCrumb.updateDir(mModel.getPwd());
      }
    });

    mFileCrumb.addChangeListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent e) {
        mModel.setPwd(mFileCrumb.getDir());
      }
    });
  }

  protected void setDir() {
    Path dir = FileDialog.openDir(mParent, mFileCrumb.getDir());

    if (dir != null) {
      mFileCrumb.setDir(dir);
    }
  }
}
