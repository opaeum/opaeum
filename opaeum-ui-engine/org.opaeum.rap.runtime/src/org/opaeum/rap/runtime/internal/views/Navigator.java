package org.opaeum.rap.runtime.internal.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.ModelAdapter;
import org.eclipse.rap.rms.data.ModelEvent;
import org.eclipse.rap.rms.data.ModelListener;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.opaeum.rap.runtime.Constants;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.actions.OpenEditorAction;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter;

public class Navigator extends ViewPart {

  private TreeViewer viewer;
  private DrillDownAdapter drillDownAdapter;
  private Action openEditor;
  
  private class ViewContentProvider
    implements IStructuredContentProvider,
               ITreeContentProvider
  {
    
    private StructuredViewer viewer;
    private ModelListener modelListener = new ModelAdapter() {
      @Override
      public void entityCreated( ModelEvent evt ) {
        doEntityCreated( evt );
      }
    };
    
    private ViewContentProvider() {
      DataModelRegistry.getFactory().addModelListener( modelListener );
    }
    
    public void inputChanged( final Viewer viewer, 
                              final Object oldInput,
                              final Object newInput )
    {
      this.viewer = ( StructuredViewer )viewer;
    }
    public void dispose() {
      DataModelRegistry.getFactory().removeModelListener( modelListener );
    }
    public Object[] getElements( final Object parent ) {
      return EntityAdapter.getElements( parent );
    }
    public Object getParent( final Object child ) {
      return EntityAdapter.getParent( child );
    }
    public Object[] getChildren( final Object parent ) {
      return EntityAdapter.getChildren( parent );
    }
    public boolean hasChildren( final Object parent ) {
      return EntityAdapter.hasChildren( parent );
    }

    public void doEntityCreated( final ModelEvent evt ) {
      EntityAdapter.refreshAssociations( evt.getEntity(), viewer );
      if( viewer.getControl().getDisplay() == Display.getCurrent() ) {
        ISelection selection = new StructuredSelection( evt.getEntity() );
        viewer.setSelection( selection, true );
      }
    }
  }
  
  private class ViewLabelProvider extends LabelProvider {
    public String getText( final Object element ) {
      return EntityAdapter.getText( element );
    }
    public Image getImage( final Object element ) {
      return EntityAdapter.getImage( element );
    }
  }

  
  public void createPartControl( final Composite parent ) {
    viewer = new TreeViewer( parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL );
    drillDownAdapter = new DrillDownAdapter( viewer );
    viewer.setContentProvider( new ViewContentProvider() );
    viewer.setLabelProvider( new ViewLabelProvider() );
    viewer.setInput( getSite() );
    makeActions();
    hookContextMenu();
    hookDoubleClickAction();
    contributeToActionBars();
    
    getSite().getPage().addPartListener( new IPartListener() {
      public void partActivated( final IWorkbenchPart part ) {
        if( part != Navigator.this ) {
          IEntity entity = ( IEntity )part.getAdapter( IEntity.class );
          if( entity != null ) {
            ISelection selection = new StructuredSelection( entity );
            viewer.setSelection( selection, true );
          }
        }
      }
      public void partBroughtToTop( final IWorkbenchPart part ) {
      }
      public void partClosed( final IWorkbenchPart part ) {
      }
      public void partDeactivated( final IWorkbenchPart part ) {
      }
      public void partOpened( final IWorkbenchPart part ) {
      }
    } );
    viewer.expandToLevel( 2 );
    getSite().setSelectionProvider( viewer );
    
    IServiceStore serviceStore = RWT.getServiceStore();
    ISelection selection
      = ( ISelection )serviceStore.getAttribute( Constants.PRE_SELECTION );
    if( selection != null ) {
      viewer.setSelection( selection, true );
    }
  }

  private void hookContextMenu() {
    MenuManager menuMgr = new MenuManager( "#PopupMenu" ); //$NON-NLS-1$
    menuMgr.setRemoveAllWhenShown( true );
    menuMgr.addMenuListener( new IMenuListener() {
      public void menuAboutToShow( IMenuManager manager ) {
        Navigator.this.fillContextMenu( manager );
      }
    } );
    Menu menu = menuMgr.createContextMenu( viewer.getControl() );
    viewer.getControl().setMenu( menu );
    getSite().registerContextMenu( menuMgr, viewer );
  }

  private void contributeToActionBars() {
    IActionBars bars = getViewSite().getActionBars();
    fillLocalToolBar( bars.getToolBarManager() );
  }

  private void fillContextMenu( final IMenuManager manager ) {
    IStructuredSelection selection 
      = ( IStructuredSelection )viewer.getSelection();
    if( selection != null ) {
      EntityAdapter.createNewMenuItem( selection.getFirstElement(), manager );
    }
    manager.add( new Separator() );
    if( !( selection.getFirstElement() instanceof IDataModel ) ) {
      manager.add( openEditor );
      manager.add( new Separator() );
    }
    drillDownAdapter.addNavigationActions( manager );
    // Other plug-ins can contribute there actions here
    manager.add( new Separator( IWorkbenchActionConstants.MB_ADDITIONS ) );
  }

  private void fillLocalToolBar( IToolBarManager manager ) {
    drillDownAdapter.addNavigationActions( manager );
  }

  private void makeActions() {
    openEditor = new OpenEditorAction( viewer, RMSMessages.get().Navigator_Open );
  }

  private void hookDoubleClickAction() {
    viewer.addDoubleClickListener( new IDoubleClickListener() {
      public void doubleClick( DoubleClickEvent event ) {
        openEditor.run();
      }
    } );
  }

  public void setFocus() {
    viewer.getControl().setFocus();
  }
}