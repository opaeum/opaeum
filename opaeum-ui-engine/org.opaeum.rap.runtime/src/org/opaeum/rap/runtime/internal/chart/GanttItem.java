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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;


public class GanttItem extends Item {

  private Gantt parent;
  Label text;
  Label bar;
  int indent;
  int start;
  int length;
  
  
  public GanttItem( final Gantt parent, final int style ) {
    this( parent, style, checkNull( parent).getItemCount() );
  }
  
  public GanttItem( final Gantt parent, final int style, final int index ) {
    super( parent, style );
    this.parent = parent;
    text = new Label( parent, SWT.NONE );
    bar = new Label( parent, SWT.NONE );
    bar.setBackground( parent.getBackground() );
    text.setBackground( parent.getBackground() );
    parent.createItem( this, index );
  }
  
  public Gantt getParent() {
    checkWidget();
    return parent; 
  }
  
  public void setStart( final int start ) {
    checkWidget();
    this.start = start;
  }
  
  public int getStart() {
    checkWidget();
    return start;
  }

  public void setLength( final int length ) {
    checkWidget();
    if( length < 0 ) {
      SWT.error( SWT.ERROR_INVALID_ARGUMENT );
    }
    this.length = length;
  }
  
  public int getLength() {
    checkWidget();
    return length;
  }

  public void setText( final String text ) {
    super.setText( text );
    this.text.setText( text );
    this.text.setToolTipText( text );
  }
  
  public int getIndent() {
    checkWidget();
    return indent;
  }

  public void setIndent( final int indent ) {
    checkWidget();
    this.indent = Math.max( 0, indent );
  }
  
  public void setBarText( final String barText ) {
    checkWidget();
    bar.setText( barText );
  }
  
  public String getBarText() {
    checkWidget();
    return bar.getText();
  }
  
  public void setToolTipText( final String toolTipText ) {
    checkWidget();
    bar.setToolTipText( toolTipText );
  }
  
  public String getToolTipText() {
    checkWidget();
    return bar.getToolTipText();
  }
  
  public void setBackground( final Color color ) {
    checkWidget();
    bar.setBackground( color );
  }
  
  public Color getBackground() {
    checkWidget();
    return bar.getBackground();
  }
  
  public void setForeground( final Color color ) {
    checkWidget();
    bar.setForeground( color );
  }
  
  public Color getForeground() {
    return bar.getForeground();
  }
  
  public void dispose() {
    if( !isDisposed() ) {
      super.dispose();
      parent.destroyItem( this );
      parent = null;
      text.dispose();
      text = null;
      bar.dispose();
      bar = null;
    }
  }
  
  //////////////////
  // Helping methods

  private static Gantt checkNull( final Gantt gantt ) {
    if( gantt == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    return gantt;
  }
}
