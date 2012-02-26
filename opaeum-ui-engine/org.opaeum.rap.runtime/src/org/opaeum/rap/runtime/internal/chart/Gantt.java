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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;


public class Gantt extends Composite {

  private java.util.List<GanttItem> items;
  private String[] legend;
  Label markLabel;
  int mark;
  java.util.List<Label> legendLabels;

  public Gantt( final Composite parent, final int style ) {
    super( parent, style );
    super.setLayout( new GanttLayout() );
    items = new ArrayList<GanttItem>();
    legendLabels = new ArrayList<Label>();
    mark = -1;
  }
  
  public void setLayout( final Layout layout ) {
    checkWidget();
    // do nothing, Gantt maintains its own layout
  }
  
  public GanttItem[] getItems() {
    checkWidget();
    GanttItem[] result = new GanttItem[ items.size() ];
    items.toArray( result );
    return result;
  }
  
  public GanttItem getItem( final int index ) {
    checkWidget();
    if( !( 0 <= index && index < items.size() ) ) {
      SWT.error( SWT.ERROR_INVALID_RANGE );
    }
    return items.get( index );
  }
  
  public int getItemCount() {
    checkWidget();
    return items.size();
  }
  
  public void setLegend( final String[] legend ) {
    checkWidget();
    if( legend == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    for( int i = 0; i < legend.length; i++ ) {
      if( legend[ i ] == null ) {
        SWT.error( SWT.ERROR_NULL_ARGUMENT );
      }
    }
    this.legend = new String[ legend.length ];
    System.arraycopy( legend, 0, this.legend, 0, legend.length );
    for( int i = 0; i < this.legend.length; i++ ) {
      Label label;
      if( i > legendLabels.size() - 1 ) {
        label = new Label( this, SWT.CENTER );
        label.setBackground( getBackground() );
        legendLabels.add( label );
      } else {
        label = legendLabels.get( i );
      }
      label.setText( this.legend[ i ] );
    }
  }
  
  public String[] getLegend() {
    checkWidget();
    String[] result = new String[ legend.length ];
    System.arraycopy( legend, 0, result, 0, legend.length );
    return result;
  }
  
  public void setMark( final int mark ) {
    checkWidget();
    if( this.mark != mark ) {
      this.mark = mark;
      updateMarkLabel();
    }
  }
  
  public int getMark() {
    checkWidget();
    return mark;
  }
  
  public void setMarkText( final String markText ) {
    checkWidget();
    markLabel.setToolTipText( markText );
  }
  
  public String getMarkText() {
    checkWidget();
    return markLabel.getToolTipText();
  }
  
  public void dispose() {
    if( !isDisposed() ) {
      super.dispose();
      items = null;
      markLabel = null;
      legendLabels = null;
    }
  }

  void createItem( final GanttItem item, final int index ) {
    items.add( index, item );
    updateMarkLabel();
  }
  
  void destroyItem( final GanttItem item ) {
    items.remove( item );
  }
  
  private void updateMarkLabel() {
    if( mark >= 0 ) {
      if( markLabel == null ) {
        markLabel = new Label( this, SWT.SEPARATOR | SWT.VERTICAL );
      }
      markLabel.moveAbove( null );
    }
    if( markLabel != null ) {
      markLabel.setVisible( mark >= 0 );
    }
  }
}
