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
package dev.antonyholmes.matcalc.groups;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreeRootNode;
import org.jebtk.graphplot.figure.series.XYSeries;
import org.jebtk.graphplot.figure.series.XYSeriesGroup;
import org.jebtk.math.matrix.DataFrame;

import dev.antonyholmes.matcalc.MainMatCalcWindow;

/**
 * The class RowGroupTreePanel.
 */
public class RowGroupTreePanel extends ColumnGroupTreePanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new row group tree panel.
   *
   * @param parent the parent
   */
  public RowGroupTreePanel(MainMatCalcWindow parent) {
    super(parent, "Rows");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.columbia.rdf.apps.matcalc.ColumnGroupTreePanel#loadGroups(java.io.Path)
   */
  @Override
  public void loadGroups(Path file) throws IOException {
    setGroups(XYSeriesGroup.createRowGroupsByName(file, mMatrix));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.matcalc.groups.ColumnGroupTreePanel#getSelectedNames()
   */
  @Override
  public List<String> getSelectedNames() {
    // See if there are some columns selected

    List<Integer> columns = mParent.getSelectedRows();

    List<String> names = DataFrame.rowNames(mMatrix, columns);

    return names;
  }

  /*
   * @Override protected void createTree(Collection<XYSeries> groups, boolean
   * clear) {
   * 
   * TreeRootNode<XYSeries> root;
   * 
   * System.err.println("SDfsdf" + groups.size());
   * System.err.println(mMatrix.getRowNames());
   * 
   * if (clear) { root = new TreeRootNode<XYSeries>(); mTree.setRoot(root); } else
   * { root = mTree.getRoot(); }
   * 
   * for (XYSeries group : groups) { TreeNode<XYSeries> groupNode = new
   * TreeNode<XYSeries>(group.getName(), group);
   * 
   * System.err.println(mMatrix.getRowNames());
   * 
   * for (int i : XYSeries.findRowIndices(mMatrix, group)) { TreeNode<XYSeries>
   * childNode = new TreeNode<XYSeries>( mMatrix.getRowName(i));
   * 
   * groupNode.addChild(childNode); }
   * 
   * root.addChild(groupNode); }
   * 
   * mTree.setRoot(root); }
   */

  @Override
  protected void createTree(XYSeries group, boolean clear) {

    TreeRootNode<XYSeries> root;

    if (clear) {
      root = new TreeRootNode<XYSeries>();
      mTree.setRoot(root);
    } else {
      root = mTree.getRoot();
    }

    TreeNode<XYSeries> groupNode = new TreeNode<XYSeries>(group.getName(), group);

    for (int i : XYSeries.findRowIndices(mMatrix, group)) {

      TreeNode<XYSeries> childNode = new TreeNode<XYSeries>(mMatrix.getColumnName(i));

      // Don't render the children, the parent will. This is
      // because we allow dragging and we don't want users to
      // drag the children around
      childNode.setVisible(false);

      groupNode.addChild(childNode);
    }

    // Cannot inherit children
    groupNode.setExpanded(false);

    root.addChild(groupNode);
  }
}
