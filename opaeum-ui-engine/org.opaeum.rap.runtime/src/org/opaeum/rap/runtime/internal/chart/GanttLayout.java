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

import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;


final class GanttLayout extends Layout {

  private static final int Y_INDENT = 5;
  private static final int X_INDENT = 5;
  private static final int TEXT_LINE_SPACING = 5;
  private static final int ITEM_SPACING = 5;

  protected Point computeSize( final Composite composite,
                               final int wHint,
                               final int hHint,
                               final boolean flushCache )
  {
    Gantt gantt = ( Gantt )composite;
    int itemCount = gantt.getItemCount();
    int charHeight = Graphics.getCharHeight( gantt.getFont() );
    int height =    ( charHeight + 4 + ITEM_SPACING + Y_INDENT )
                  * ( itemCount + 1 );
    float charWidth = Graphics.getAvgCharWidth( gantt.getFont() );
    int width =   ( int )( ( charWidth + 2 ) * 62 )
                + X_INDENT + getMaxTextWidth( gantt );
    return new Point( width, height );
  }

  protected void layout( final Composite composite, final boolean flushCache ) {
    Gantt gantt = ( Gantt )composite;
    final int textWidth = getMaxTextWidth( gantt );
    final int maxEnd = getMaxEnd( gantt );
    final int barX = getBarX( textWidth );
    final int maxBarWidth = gantt.getClientArea().width - barX - X_INDENT;
    int y = Y_INDENT;
    int height = 20;
    layoutLegend( gantt, barX, y, maxBarWidth );
    y += getLegendHeight( gantt );
    GanttItem[] items = gantt.getItems();
    for( int i = 0; i < items.length; i++ ) {
      
      Label text = items[ i ].text;
      text.setBounds( X_INDENT + items[ i ].indent, 
                      y, 
                      textWidth - items[ i ].indent, 
                      height );
      
      Label bar = items[ i ].bar;
      int startX;
      int endX;
      if( maxEnd == 0 ) {
        startX = 0;
        endX = 0;
      } else {
        startX = items[ i ].start * maxBarWidth / maxEnd;
        endX = ( items[ i ].start + items[ i ].length ) * maxBarWidth / maxEnd;
      }
      bar.setBounds( barX + startX, y, endX - startX, height );
      
      y += height + ITEM_SPACING;
    }
    if( maxEnd > 0 && gantt.mark >= 0 ) {
      int legendStepWidth = getLegendStepWidth( gantt, maxBarWidth );
      int left 
        = barX 
        + ( gantt.mark * maxBarWidth / maxEnd ) 
        + legendStepWidth / 2;
      int top = Y_INDENT + getLegendHeight( gantt );
      int markHeight = gantt.getClientArea().height - Y_INDENT - top;
      gantt.markLabel.setBounds( left, top, 2, markHeight );
    }
  }

  private int getBarX( final int textWidth ) {
    final int barX = X_INDENT + textWidth + TEXT_LINE_SPACING;
    return barX;
  }

  protected boolean flushCache( final Control control ) {
    return true;
  }
  
  /////////////////////////
  // Layout helping methods
  
  private static void layoutLegend( final Gantt gantt, 
                                    final int x, 
                                    final int y, 
                                    final int width ) 
  {
    Label[] labels = new Label[ gantt.legendLabels.size() ];
    if( labels.length > 0 ) {
      gantt.legendLabels.toArray( labels );
      int labelWidth = getLegendStepWidth( gantt, width );
      int labelX = x;
      for( int i = 0; i < labels.length; i++ ) {
        labels[ i ].setBounds( labelX, y, labelWidth, 20 );
        labelX += labelWidth;
      }
    }
  }

  //////////////////
  // Helping methods
  
  private static int getLegendHeight( final Gantt gantt ) {
    return gantt.legendLabels.size() > 0 ? 20 + ITEM_SPACING : 0;
  }
  
  private static int getMaxTextWidth( final Gantt gantt ) {
    int result = 0;
    GanttItem[] items = gantt.getItems();
    Font font = gantt.getFont();
    for( int i = 0; i < items.length; i++ ) {
      int width 
        = items[ i ].indent
        + Graphics.stringExtent( font, items[ i ].getText() ).x;
      result = Math.max( width, result );
    }
    return result;
  }
  
  private static int getMaxEnd( final Gantt gantt ) {
    int result = 0;
    if( gantt.mark > 0 ) {
      result = gantt.mark;
    }
    if( gantt.legendLabels.size() > gantt.mark ) {
      result = gantt.legendLabels.size();
    }
    GanttItem[] items = gantt.getItems();
    for( int i = 0; i < items.length; i++ ) {
      int finish = items[ i ].start + items[ i ].length;
      result = Math.max( result, finish );
    }
    return result;
  }
  
  private static int getLegendStepWidth( final Gantt gantt, final int barWidth ) 
  {
    int legendCount = gantt.legendLabels.size(); 
    return legendCount == 0 ? 0 : barWidth / legendCount;
  }
}
