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
package dev.antonyholmes.matcalc;

import dev.antonyholmes.matcalc.toolbox.core.ClipboardModule;
import dev.antonyholmes.matcalc.toolbox.core.ColumnAnnotationModule;
import dev.antonyholmes.matcalc.toolbox.core.ExtractDataModule;
import dev.antonyholmes.matcalc.toolbox.core.GroupModule;
import dev.antonyholmes.matcalc.toolbox.core.OrderColumnsModule;
import dev.antonyholmes.matcalc.toolbox.core.SplitModule;
import dev.antonyholmes.matcalc.toolbox.core.SummaryModule;
import dev.antonyholmes.matcalc.toolbox.core.collapse.CollapseModule;
import dev.antonyholmes.matcalc.toolbox.core.duplicate.DuplicateModule;
import dev.antonyholmes.matcalc.toolbox.core.filter.FilterModule;
import dev.antonyholmes.matcalc.toolbox.core.filter.column.ColumnFilterModule;
import dev.antonyholmes.matcalc.toolbox.core.filter.row.RowFilterModule;
import dev.antonyholmes.matcalc.toolbox.core.match.MatchModule;
import dev.antonyholmes.matcalc.toolbox.core.paste.PasteModule;
import dev.antonyholmes.matcalc.toolbox.core.rename.RenameModule;
import dev.antonyholmes.matcalc.toolbox.core.roworder.RowOrderModule;
import dev.antonyholmes.matcalc.toolbox.core.search.SearchColumnModule;
import dev.antonyholmes.matcalc.toolbox.core.sort.SortColumnsByRowModule;
import dev.antonyholmes.matcalc.toolbox.core.sort.SortModule;
import dev.antonyholmes.matcalc.toolbox.core.venn.VennModule;
import dev.antonyholmes.matcalc.toolbox.math.LogModule;
import dev.antonyholmes.matcalc.toolbox.math.NormalizeModule;
import dev.antonyholmes.matcalc.toolbox.math.PowerModule;
import dev.antonyholmes.matcalc.toolbox.math.RoundModule;
import dev.antonyholmes.matcalc.toolbox.math.StatsModule;
import dev.antonyholmes.matcalc.toolbox.math.ThresholdModule;
import dev.antonyholmes.matcalc.toolbox.math.TransposeModule;
import dev.antonyholmes.matcalc.toolbox.math.ZScoreModule;
import dev.antonyholmes.matcalc.toolbox.ml.KMeansModule;
import dev.antonyholmes.matcalc.toolbox.plot.barchart.BarChartModule;
import dev.antonyholmes.matcalc.toolbox.plot.barchart.HistogramModule;
import dev.antonyholmes.matcalc.toolbox.plot.barchart.StackedBarChartModule;
import dev.antonyholmes.matcalc.toolbox.plot.boxwhisker.BoxWhiskerPlotModule;
import dev.antonyholmes.matcalc.toolbox.plot.boxwhisker.BoxWhiskerScatterPlotModule;
import dev.antonyholmes.matcalc.toolbox.plot.heatmap.cluster.legacy.LegacyClusterModule;
import dev.antonyholmes.matcalc.toolbox.plot.heatmap.legacy.LegacyHeatMapModule;
import dev.antonyholmes.matcalc.toolbox.plot.scatter.ScatterModule;
import dev.antonyholmes.matcalc.toolbox.plot.scatter.SmoothedLineGraphModule;
import dev.antonyholmes.matcalc.toolbox.plot.volcano.VolcanoPlotModule;
import dev.antonyholmes.matcalc.toolbox.supervised.SupervisedModule;

/**
 * The Class ModuleLoader.
 */
public class BasicModuleLoader extends CoreModuleLoader {

  /**
   * Instantiates a new module loader.
   */
  public BasicModuleLoader() {
    addModule(ClipboardModule.class);

    addModule(TransposeModule.class);
    addModule(ThresholdModule.class);
    addModule(LogModule.class);
    addModule(PowerModule.class);
    addModule(RoundModule.class);
    addModule(NormalizeModule.class);
    addModule(ZScoreModule.class);
    addModule(StatsModule.class);

    addModule(SortModule.class);
    addModule(SortColumnsByRowModule.class);
    addModule(FilterModule.class);
    addModule(RowFilterModule.class);
    addModule(ColumnFilterModule.class);

    addModule(SearchColumnModule.class);
    addModule(MatchModule.class);

    addModule(SummaryModule.class);
    addModule(CollapseModule.class);
    addModule(DuplicateModule.class);
    addModule(SplitModule.class);
    addModule(ExtractDataModule.class);
    addModule(GroupModule.class);
    addModule(ColumnAnnotationModule.class);

    addModule(RowOrderModule.class);
    addModule(OrderColumnsModule.class);

    addModule(VennModule.class);

    addModule(PasteModule.class);

    addModule(LegacyHeatMapModule.class);
    addModule(LegacyClusterModule.class);
    addModule(SmoothedLineGraphModule.class);
    // addModule(ScatterLineModule.class);
    addModule(ScatterModule.class);
    addModule(BarChartModule.class);
    // addModule(BarChartHModule.class);
    addModule(StackedBarChartModule.class);
    addModule(HistogramModule.class);
    // addModule(PieChartModule.class);
    addModule(BoxWhiskerPlotModule.class);
    addModule(BoxWhiskerScatterPlotModule.class);
    addModule(VolcanoPlotModule.class);

    addModule(SupervisedModule.class);

    addModule(KMeansModule.class);

    addModule(RenameModule.class);
  }
}
