/*******************************************************************************
 * Copyright (c) 2002-2007 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Innoopract Informationssysteme GmbH - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.chart;


public interface IGanttLabelProvider {
  
  static final int NO_MARK = -1;
  
  String[] getLegendText( final Object rootElement );
  int getMark( final Object rootElement );
}
