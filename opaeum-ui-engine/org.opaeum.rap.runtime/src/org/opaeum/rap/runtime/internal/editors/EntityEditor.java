package org.opaeum.rap.runtime.internal.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter;
import org.opaeum.rap.runtime.internal.datamodel.ILock;

public class EntityEditor
  extends FormEditor 
  implements ISelectionListener
{

  private FormPage[] editorPages;

  private final class SelectionProvider implements ISelectionProvider {
    public void addSelectionChangedListener( 
      final ISelectionChangedListener listener )
    {
    }
    public ISelection getSelection() {
      IEditorInput input = getEditorInput();
      return new StructuredSelection( input.getAdapter( IEntity.class ) );
    }
    public void removeSelectionChangedListener( 
      final ISelectionChangedListener listener )
    {
    }
    public void setSelection( final ISelection selection ) {
    }
  }

  @Override
  public void init( final IEditorSite site, final IEditorInput input )
    throws PartInitException
  {
    super.init( site, input );
    setPartName( input.getName() );
    setTitleImage( input.getImageDescriptor().createImage() );
    getSite().getPage().addSelectionListener( this );
    getSite().setSelectionProvider( new SelectionProvider() );
  }
  
  @Override
  public void dispose() {
    getSite().getPage().removeSelectionListener( this );
    IAdaptable entity
      = ( IAdaptable )getEditorInput().getAdapter( IEntity.class );
    ILock lock = ( ILock )entity.getAdapter( ILock.class );
    lock.unLock();
    super.dispose();
  }
  
  @Override
  protected void addPages() {
    try {
      IEntity entity = ( IEntity )getEditorInput().getAdapter( IEntity.class );
      editorPages = EntityAdapter.getEditorPages( entity, this );
      for( int i = 0; i < editorPages.length; i++ ) {
        addPage( editorPages[ i ] );
        editorPages[ i ].addPropertyListener( new IPropertyListener() {
          public void propertyChanged( Object source, int propertyId ) {
            EntityEditor.this.handlePropertyChange( propertyId );
          }          
        } );
      }
    } catch( final PartInitException pie ) {
      String id = "org.opaeum.rap.runtime"; //$NON-NLS-1$
      Status status = new Status( IStatus.ERROR, id, pie.getMessage(), pie );
      int style = StatusManager.SHOW | StatusManager.LOG;
      StatusManager.getManager().handle( status, style );
    }
  }

  @Override
  public void doSave( final IProgressMonitor monitor ) {
    for( int i = 0; i < editorPages.length; i++ ) {
      editorPages[ i ].doSave( monitor );
    }
  }

  @Override
  public void doSaveAs() {
  }

  @Override
  public boolean isSaveAsAllowed() {
    return true;
  }
  
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  @Override
  public Object getAdapter( final Class adapter ) {
    Object result;
    if( adapter == IEntity.class ) {
      result = getEditorInput().getAdapter( adapter );
    } else if( adapter == IPropertySheetPage.class ) {
      result = createPropertySheetPage();
    } else {
      result = super.getAdapter( adapter );
    }
    return result;
  }

  private PropertySheetPage createPropertySheetPage() {
    PropertySheetPage propertySheetPage = new PropertySheetPage();
    propertySheetPage.setPropertySourceProvider( 
      new IPropertySourceProvider()
    {
      public IPropertySource getPropertySource( final Object object ) {
        IPropertySource result = null;
        if( object instanceof IEntity ) {
          IAdaptable adaptable = ( IAdaptable )object;
          Class<IPropertySource> clazz = IPropertySource.class;
          result = ( IPropertySource )adaptable.getAdapter( clazz );
        }
        return result;
      }
    } );
    return propertySheetPage;
  }

  public void selectionChanged( final IWorkbenchPart part, 
                                final ISelection selection )
  {
    IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
    if( activePage.getActiveEditor() != this ) {
      if( selection instanceof IStructuredSelection ) {
        IStructuredSelection sselection
        = ( IStructuredSelection )selection;
        Object entity = getEditorInput().getAdapter( IEntity.class );
        if( sselection.getFirstElement() == entity ) {
          activePage.bringToTop( this );
        }
      }
    }
  }
}
