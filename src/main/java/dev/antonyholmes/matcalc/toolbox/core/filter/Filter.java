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
package dev.antonyholmes.matcalc.toolbox.core.filter;

/**
 * The Interface Filter.
 */
public interface Filter {

  /**
   * Test.
   *
   * @param text  the text
   * @param value the value
   * @return true, if successful
   */
  boolean test(String text, double value);

  /**
   * Gets the name.
   *
   * @return the name
   */
  String getName();
}
