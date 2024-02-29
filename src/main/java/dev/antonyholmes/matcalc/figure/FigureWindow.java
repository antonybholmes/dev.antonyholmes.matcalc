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

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.batik.transcoder.TranscoderException;
import org.jebtk.core.io.FileUtils;
import org.jebtk.graphplot.Image;
import org.jebtk.graphplot.MatrixGroupModel;
import org.jebtk.graphplot.figure.Figure;
import org.jebtk.graphplot.figure.Graph2dStyleModel;
import org.jebtk.graphplot.figure.heatmap.ColorNormalizationModel;
import org.jebtk.graphplot.icons.FormatPlot32VectorIcon;
import org.jebtk.graphplot.plotbox.PlotBox;

import dev.antonyholmes.matcalc.colormap.ColorMapRibbonSection;
import dev.antonyholmes.matcalc.figure.graph2d.Graph2dStyleRibbonSection;
import dev.antonyholmes.matcalc.toolbox.plot.heatmap.ColorStandardizationRibbonSection;
import dev.antonyholmes.matcalc.toolbox.plot.heatmap.ScaleModel;
import dev.antonyholmes.modern.AssetService;
import dev.antonyholmes.modern.UI;
import dev.antonyholmes.modern.button.ModernClickWidget;
import dev.antonyholmes.modern.clipboard.ClipboardRibbonSection;
import dev.antonyholmes.modern.contentpane.CloseableHTab;
import dev.antonyholmes.modern.dialog.DialogEvent;
import dev.antonyholmes.modern.dialog.DialogEventListener;
import dev.antonyholmes.modern.dialog.ModernDialogStatus;
import dev.antonyholmes.modern.dialog.ModernMessageDialog;
import dev.antonyholmes.modern.event.ModernClickEvent;
import dev.antonyholmes.modern.event.ModernClickListener;
import dev.antonyholmes.modern.graphics.colormap.ColorMapModel;
import dev.antonyholmes.modern.graphics.icons.QuickSaveVectorIcon;
import dev.antonyholmes.modern.graphics.icons.Raster32Icon;
import dev.antonyholmes.modern.help.ModernAboutDialog;
import dev.antonyholmes.modern.io.RecentFilesService;
import dev.antonyholmes.modern.io.SaveAsRibbonPanel;
import dev.antonyholmes.modern.ribbon.QuickAccessButton;
import dev.antonyholmes.modern.ribbon.RibbonLargeButton;
import dev.antonyholmes.modern.ribbon.RibbonMenuItem;
import dev.antonyholmes.modern.tooltip.ModernToolTip;
import dev.antonyholmes.modern.window.ModernRibbonWindow;
import dev.antonyholmes.modern.window.ModernWindow;
import dev.antonyholmes.modern.window.ModernWindowConstructor;
import dev.antonyholmes.modern.window.WindowRibbonSection;
import dev.antonyholmes.modern.zoom.ModernStatusZoomSlider;
import dev.antonyholmes.modern.zoom.ZoomModel;
import dev.antonyholmes.modern.zoom.ZoomRibbonSection;

/**
 * Window for showing 2D graphs such as a scatter plot.
 * 
 * @author Antony Holmes
 *
 */
public abstract class FigureWindow extends ModernRibbonWindow implements ModernWindowConstructor, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member save as panel.
   */
  private SaveAsRibbonPanel mSaveAsPanel = new SaveAsRibbonPanel();

  /**
   * The member zoom model.
   */
  protected ZoomModel mZoomModel = new ZoomModel();

  /**
   * The member color map model.
   */
  protected ColorMapModel mColorMapModel = new ColorMapModel();

  /**
   * The member groups model.
   */
  protected MatrixGroupModel mGroupsModel = new MatrixGroupModel();

  /**
   * The member format pane.
   */
  protected FormatPlotPane mFormatPane = null;

  /**
   * The member color model.
   */
  protected ColorNormalizationModel mColorModel = new ColorNormalizationModel();

  /** The m style model. */
  protected Graph2dStyleModel mStyleModel = new Graph2dStyleModel();

  /** The m window. */
  protected ModernWindow mWindow;

  /** The m scale model. */
  protected ScaleModel mScaleModel = new ScaleModel();

  protected Figure mFigure;

  /**
   * The constant NEXT_ID.
   */
  private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

  /**
   * The member id.
   */
  private final int mId = NEXT_ID.getAndIncrement();

  /** The m allow style. */
  private boolean mAllowStyle;

  /**
   * The class ExportCallBack.
   */
  private class ExportCallBack implements DialogEventListener {

    /**
     * The member file.
     */
    private Path mFile;

    /**
     * The member pwd.
     */
    private Path mPwd;

    /**
     * Instantiates a new export call back.
     *
     * @param file the file
     * @param pwd  the pwd
     */
    public ExportCallBack(Path file, Path pwd) {
      mFile = file;
      mPwd = pwd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.ui.dialog.DialogEventListener#statusChanged(org.abh.
     * common. ui.ui.dialog.DialogEvent)
     */
    @Override
    public void statusChanged(DialogEvent e) {
      if (e.getStatus() == ModernDialogStatus.OK) {
        try {
          save(mFile);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } else {
        try {
          export(mPwd);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  /**
   * Instantiates a new figure window.
   *
   * @param window the window
   */
  public FigureWindow(ModernWindow window, Figure figure) {
    this(window, figure, true);
  }

  /**
   * Instantiates a new figure window.
   *
   * @param window     the window
   * @param figure     the figure
   * @param allowStyle the allow style
   */
  public FigureWindow(ModernWindow window, Figure figure, boolean allowStyle) {
    super(window.getAppInfo());

    mWindow = window;
    // add canvas to the plot
    mFigure = figure;

    mAllowStyle = allowStyle;

    setSubTitle("Figure " + mId);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    createRibbon();

    createUi();

    setSize(1280, 768);

    UI.centerWindowToScreen(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindowConstructor#createRibbon()
   */
  public void createRibbon() {
    // RibbongetRibbonMenu() getRibbonMenu() = new RibbongetRibbonMenu()(0);
    RibbonMenuItem menuItem;

    menuItem = new RibbonMenuItem(UI.MENU_SAVE_AS);
    getRibbonMenu().addTabbedMenuItem(menuItem, mSaveAsPanel);

    getRibbonMenu().addDefaultItems(getAppInfo(), false);

    getRibbonMenu().addClickListener(this);

    ModernClickWidget button;

    // Ribbon2 ribbon = new Ribbon2();
    getRibbon().setHelpButtonEnabled(getAppInfo());

    button = new QuickAccessButton(AssetService.getInstance().loadIcon(QuickSaveVectorIcon.class, 16));
    button.setClickMessage(UI.MENU_SAVE);
    button.setToolTip(new ModernToolTip("Save", "Save the current image."));
    button.addClickListener(this);
    addQuickAccessButton(button);

    getRibbon().getToolbar("Plot").add(new ClipboardRibbonSection(getRibbon()));

    if (mAllowStyle) {
      getRibbon().getToolbar("Plot").add(new Graph2dStyleRibbonSection(getRibbon(), mStyleModel));
    }

    getRibbon().getToolbar("Color").addSection(new ColorMapRibbonSection(this, mColorMapModel, mScaleModel));
    getRibbon().getToolbar("Color").addSection(new ColorStandardizationRibbonSection(this, getRibbon(), mColorModel));

    button = new RibbonLargeButton("Format", new Raster32Icon(new FormatPlot32VectorIcon()));
    button.addClickListener(this);
    getRibbon().getToolbar("View").getSection("Show").add(button);

    getRibbon().getToolbar("View").add(new ZoomRibbonSection(this, mZoomModel));
    getRibbon().getToolbar("View").add(new WindowRibbonSection(this, getRibbon()));

    getRibbon().setSelectedIndex(0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindow#createUi()
   */
  public void createUi() {
    mStatusBar.addRight(new ModernStatusZoomSlider(mZoomModel));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.MENU_SAVE)) {
      try {
        export();
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (TranscoderException e1) {
        e1.printStackTrace();
      }
    } else if (e.getMessage().equals(SaveAsRibbonPanel.DIRECTORY_SELECTED)) {
      try {
        export(mSaveAsPanel.getSelectedDirectory());
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (TranscoderException e1) {
        e1.printStackTrace();
      }
    } else if (e.getMessage().equals("Format")) {
      addFormatPane();
    } else if (e.getMessage().equals(UI.MENU_ABOUT)) {
      ModernAboutDialog.show(this, getAppInfo());
    } else if (e.getMessage().equals(UI.MENU_CLOSE)) {
      close();
    } else {

    }
  }

  /**
   * Adds the history pane to the layout if it is not already showing.
   */
  private void addFormatPane() {
    if (tabsPane().tabs().right().contains("Format")) {
      return;
    }

    tabsPane().tabs().right().add("Format", new CloseableHTab("Format", mFormatPane, tabsPane()), 300, 200, 500);
  }

  /**
   * Export.
   *
   * @throws IOException         Signals that an I/O exception has occurred.
   * @throws TranscoderException the transcoder exception
   */
  private void export() throws IOException, TranscoderException {
    export(RecentFilesService.getInstance().getPwd());
  }

  /**
   * Export.
   *
   * @param pwd the pwd
   * @throws IOException         Signals that an I/O exception has occurred.
   * @throws TranscoderException the transcoder exception
   */
  private void export(Path pwd) throws IOException, TranscoderException {
    Path file = Image.saveFile(this, pwd);

    if (file == null) {
      return;
    }

    if (FileUtils.exists(file)) {
      // createFileExistsDialog(file, new ExportCallBack(file, pwd));

      ModernMessageDialog.createFileReplaceDialog(this, file, new ExportCallBack(file, pwd));
    } else {
      save(file);
    }
  }

  /**
   * Save.
   *
   * @param file the file
   * @throws IOException         Signals that an I/O exception has occurred.
   * @throws TranscoderException the transcoder exception
   */
  private void save(Path file) throws IOException, TranscoderException {
    Image.write(getPlot(), file);

    RecentFilesService.getInstance().setPwd(file.getParent());

    // createFileSavedDialog(file);

    ModernMessageDialog.createFileSavedDialog(this, file);
  }

  /**
   * Gets the canvas.
   *
   * @return the canvas
   */
  public abstract PlotBox getPlot();

  /**
   * Gets the color normalization model.
   *
   * @return the color normalization model
   */
  public ColorNormalizationModel getColorNormalizationModel() {
    return mColorModel;
  }

  /**
   * Gets the style.
   *
   * @return the style
   */
  public Graph2dStyleModel getStyle() {
    return mStyleModel;
  }
}
