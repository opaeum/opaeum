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

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IViewerLabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Widget;


public class GanttViewer extends StructuredViewer {

  private final Gantt control;
  private boolean busy;
  
  public GanttViewer( final Gantt gantt ) {
    control = gantt;
  }
  
  public GanttViewer( final Composite parent ) {
    this( parent, SWT.NONE );
  }
  
  public GanttViewer( final Composite parent, final int style ) {
    this( new Gantt( parent, style ) );
  }
  
  public Control getControl() {
    return control;
  }
  
  public Gantt getGantt() {
    return control;
  }

  protected Widget doFindItem( final Object element ) {
    Widget result = null;
    Item[] items = control.getItems();
    for( int i = 0; result == null && i < items.length; i++ ) {
      Item item = items[ i ];
      Object data = item.getData();
      if( data != null && equals( data, element ) ) {
        result = item;
      }
    }
    return result;
  }
  
  protected Widget doFindInputItem( final Object element ) {
    Widget result = null;
    if( equals( element, getRoot() ) ) {
      result = getControl();
    }
    return result;
  }

  protected void doUpdateItem( final Widget widget, 
                               final Object element, 
                               final boolean fullMap ) 
  {
    boolean oldBusy = busy;
    busy = true;
    try {
      GanttItem item = ( GanttItem )widget;
      // remember element we are showing
      if( fullMap ) {
        associate( element, item );
      } else {
        Object data = item.getData();
        if( data != null ) {
          unmapElement( data, item );
        }
        item.setData( element );
        mapElement( element, item );
      }
      updateLabel( item, element );
      // As it is possible for user code to run the event
      // loop check here.
      if( item.isDisposed() ) {
        unmapElement( element, item );
      }
    } finally {
      busy = oldBusy;
    }
  }

  @SuppressWarnings("unchecked")
  protected List getSelectionFromWidget() {
    return Collections.EMPTY_LIST;
  }

  protected void inputChanged( Object input, Object oldInput ) {
    getControl().setRedraw( false );
    try {
      preservingSelection( new Runnable() {
        public void run() {
          internalRefresh( getRoot() );
        }
      } );
    } finally {
      getControl().setRedraw( true );
    }
  }
  
  protected void internalRefresh( final Object element ) {
    internalRefresh( element, true );
  }
  
  protected void internalRefresh( final Object element, 
                                  final boolean updateLabels ) 
  {
    if( element == null || equals( element, getRoot() ) ) {
      internalRefreshAll( updateLabels );
    } else {
      Widget item = findItem( element );
      if( item != null ) {
        updateItem( item, element );
      }
    }
  }
  
  public void reveal( final Object element ) {
  }

  @SuppressWarnings("unchecked")
  protected void setSelectionToWidget( final List list, final boolean reveal ) {
  }

  ///////////////////
  // Helping methods
  
  private void internalRefreshAll( final boolean updateLabels ) {
    if( updateLabels ) {
      updateLabel( control, getRoot() );
    }
    // in the code below, it is important to do all disassociates
    // before any associates, since a later disassociate can undo an
    // earlier associate
    // e.g. if (a, b) is replaced by (b, a), the disassociate of b to
    // item 1 could undo
    // the associate of b to item 0.
    Object[] children = getSortedChildren( getRoot() );
    Item[] items = control.getItems();
    int min = Math.min( children.length, items.length );
    for( int i = 0; i < min; ++i ) {
      Item item = items[ i ];
      // if the element is unchanged, update its label if appropriate
      if( equals( children[ i ], item.getData() ) ) {
        if( updateLabels ) {
          updateItem( item, children[ i ] );
        } else {
          // associate the new element, even if equal to the old
          // one,
          // to remove stale references (see bug 31314)
          associate( children[ i ], item );
        }
      } else {
        // updateItem does an associate(...), which can mess up
        // the associations if the order of elements has changed.
        // E.g. (a, b) -> (b, a) first replaces a->0 with b->0, then
        // replaces b->1 with a->1, but this actually removes b->0.
        // So, if the object associated with this item has changed,
        // just disassociate it for now, and update it below.
        // we also need to reset the item (set its text,images etc. to
        // default values) because the label decorators rely on this
        disassociate( item );
        clear( i );
      }
    }
    // dispose of all items beyond the end of the current elements
    if( min < items.length ) {
      for( int i = items.length; --i >= min; ) {
        disassociate( items[ i ] );
      }
      remove( min, items.length - 1 );
    }
    // Update items which were disassociated above
    for( int i = 0; i < min; ++i ) {
      Item item = items[ i ];
      if( item.getData() == null ) {
        updateItem( item, children[ i ] );
      }
    }
    // add any remaining elements
    for( int i = min; i < children.length; ++i ) {
      createItem( children[ i ], i );
    }
  }

  private void updateLabel( final Gantt gantt, final Object element ) {
    IBaseLabelProvider baseLabelProvider = getLabelProvider();
    if( baseLabelProvider instanceof IGanttLabelProvider ) {
      IGanttLabelProvider ganttLabelProvider 
        = ( IGanttLabelProvider )baseLabelProvider;
      String[] legendText = ganttLabelProvider.getLegendText( element );
      int mark = ganttLabelProvider.getMark( element );
      gantt.setLegend( legendText );
      gantt.setMark( mark );
    }
  }
  
  private void updateLabel( final GanttItem item, final Object element ) {
    IBaseLabelProvider baseLabelProvider = getLabelProvider();
    if( baseLabelProvider instanceof IViewerLabelProvider ) {
      IViewerLabelProvider viewerLabelProvider 
        = ( IViewerLabelProvider )baseLabelProvider;
      ViewerLabel viewerLabel = new ViewerLabel( item.getText(), null );
      viewerLabelProvider.updateLabel( viewerLabel, element );
      item.setBackground( viewerLabel.getBackground() );
      item.setForeground( viewerLabel.getForeground() );
      item.setText( viewerLabel.getText() );
      item.setToolTipText( viewerLabel.getTooltipText() );
    } else {
      if( baseLabelProvider instanceof IColorProvider ) {
        IColorProvider colorProvider = ( IColorProvider )baseLabelProvider;
        Color background = colorProvider.getBackground( element );
        Color foreground = colorProvider.getForeground( element );
        item.setBackground( background );
        item.setForeground( foreground );
      }
      if( baseLabelProvider instanceof ILabelProvider ) {
        ILabelProvider labelProvider = ( ILabelProvider )baseLabelProvider;
        String text = labelProvider.getText( element );
        item.setText( text );
      }
    }
    if( baseLabelProvider instanceof IGanttItemLabelProvider ) {
      IGanttItemLabelProvider itemLabelProvider 
        = ( IGanttItemLabelProvider )baseLabelProvider;
      int start = itemLabelProvider.getStart( element );
      int length = itemLabelProvider.getLength( element );
      item.setStart( start );
      item.setLength( length );
    }
  }

  ////////////////////
  // Item manipulation
  
  private void remove( final int start, final int end ) {
    Item[] items = control.getItems();
    for( int i = 0; i < items.length; i++ ) {
      if( i >= start && i <= end ) {
        items[ i ].dispose();
      }
    }
  }
  
  private void clear( final int index ) {
    GanttItem item = control.getItem( index );
    item.setStart( 0 );
    item.setLength( 0 );
    item.setBackground( null );
    item.setText( "" );
    item.setBarText( "" );
    item.setToolTipText( "" );
    item.setImage( null );
  }

  private void createItem( final Object element, final int index ) {
    Item item = new GanttItem( control, SWT.NONE, index );
    updateItem( item, element );
  }
}
