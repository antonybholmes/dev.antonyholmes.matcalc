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
package dev.antonyholmes.matcalc.toolbox.core.venn;

import org.jebtk.core.AppVersion;

import dev.antonyholmes.modern.help.GuiAppInfo;

/**
 * The Class VennInfo.
 */
public class VennInfo extends GuiAppInfo {

  /**
   * Instantiates a new venn info.
   */
  public VennInfo() {
    super("Venn", new AppVersion(2), "Copyright (C) 2016-${year} Antony Holmes", new VennIcon(),
        "Create Venn diagrams.");
  }

}
