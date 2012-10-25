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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


public class AutoColoProvider implements IColorProvider {

  private int colorIndex;
  private final java.util.List<Color> colors;
  private final Map<Object,Color> elementColors;
  
  public AutoColoProvider() {
    colors = new ArrayList<Color>();
    elementColors = new HashMap<Object,Color>();
  }
  
  public void addSystemColors( final Display display ) {
    int start = SWT.COLOR_WHITE;
    int end = SWT.COLOR_DARK_GRAY;
    for( int i = start; i <= end; i++ ) {
      colors.add( display.getSystemColor( i ) );
    }
  }
  
  public void addBackground( final Color color ) {
    colors.add( color );
  }
  
  public Color getBackground( final Object element ) {
    Color result = elementColors.get( element );
    if( result == null ) {
      result = nextColor();
      elementColors.put( element, result );
    }
    return result;
  }

  public Color getForeground( final Object element ) {
    return null;
  }
  
  public void reset() {
    colorIndex = 0;
    elementColors.clear();
  }

  private Color nextColor() {
    Color result = null;
    if( colorIndex + 1 < colors.size() ) {
      colorIndex++;
      result = colors.get( colorIndex );
    }
    return result;
  }
}
