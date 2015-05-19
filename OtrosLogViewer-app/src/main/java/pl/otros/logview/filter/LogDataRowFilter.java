/*******************************************************************************
 * Copyright 2011 Krzysztof Otrebski
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
 ******************************************************************************/
package pl.otros.logview.filter;

import pl.otros.logview.LogData;
import pl.otros.logview.gui.LogDataTableModel;

import javax.swing.*;

public class LogDataRowFilter extends RowFilter<LogDataTableModel, Integer> {

  public LogFilter[] filters;

  public LogDataRowFilter(LogFilter[] filters) {
    super();
    this.filters = filters;
  }

  public LogFilter[] getFilters() {
    return filters;
  }

  public void setFilters(LogFilter[] filters) {
    this.filters = filters;
  }

  @Override
  public boolean include(Entry<? extends LogDataTableModel, ? extends Integer> entry) {
    LogDataTableModel model = entry.getModel();
    LogData logData = model.getLogData(entry.getIdentifier());
    boolean result = true;
    for (int i = 0; i < filters.length; i++) {
      if (filters[i].isEnable()) {
        result = filters[i].accept(logData, entry.getIdentifier());
      }
      if (!result) {
        break;
      }
    }

    return result;
  }

}