// Created on 11.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rap.rms.data.IAssignment;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IEmployee;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;


public class EntityAdapter {
  private static final TaskAdapter TASK_ADAPTER
    = new TaskAdapter();
  private static final AssignmentAdapter ASSIGNMENT_ADAPTER
    = new AssignmentAdapter();
  private static final ProjectAdapter PROJECT_ADAPTER
    = new ProjectAdapter();
  private static final PrincipalAdapter PRINCIPAL_ADAPTER
    = new PrincipalAdapter();
  private static final EmployeeAdapter EMPLOYEE_ADAPTER
    = new EmployeeAdapter();
  private static final DataModelAdapter DATA_MODEL_ADAPTER
    = new DataModelAdapter();
  private static final RootAdapter ROOT_ADAPTER
    = new RootAdapter();
  
  // TODO [fappel]: Hack to provide platform independent code
  //                could be done better...
  public static IEditorInputRegistry editorInputRegistry;
  public interface IEditorInputRegistry {
    Map<Object, IEditorInput> get();
  }
  
  enum EntityImage {
    ASSIGNMENT( ISharedImages.IMG_DEF_VIEW ),
    PROJECT( Activator.IMG_PROJECT ),
    PRINCIPAL( Activator.IMG_PRINCIPAL ),
    EMPLOYEE( ISharedImages.IMG_OBJ_FILE ),
    DATA_MODEL( Activator.IMG_REPOSITORY );
    
    private String symbolicName;
    private EntityImage( final String symbolicName ) {
      this.symbolicName = symbolicName;
    }
    public String toString() {
      return symbolicName;
    }
  }
  
  static interface IEntityAdapter {
    Object[] getElements( Object parent );
    Object getParent( Object child );
    boolean hasChildren( Object parent );
    Object[] getChildren( Object parent );
    String getText( Object element );
    Image getImage( Object element );
    String getEditorName( Object element );
    ImageDescriptor getEditorImage( Object element );
    void createNewMenu( Object element, IMenuManager menuManager );
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    String createWizardTitle( Class destinationType );
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    IWizardPage createWizardPage( Object element, Class destinationType );
    void refreshAssociations( Object element, StructuredViewer viewer );
    IPropertySource getPropertySource( Object element );
    FormPage[] getEditorPages( Object element, FormEditor editor );
  }
  
  private final static class EntityEditorInput implements IEditorInput {
    private final String name;
    private final ImageDescriptor imageDescriptor;
    private final IEntity entity;

    private EntityEditorInput( final IEntity entity,
                               final String name, 
                               final ImageDescriptor imageDescriptor )
    {
      this.entity = entity;
      this.name = name;
      this.imageDescriptor = imageDescriptor;
    }

    public boolean exists() {
      return false;
    }
    public ImageDescriptor getImageDescriptor() {
      return imageDescriptor;
    }
    public String getName() {
      return name;
    }
    public IPersistableElement getPersistable() {
      return null;
    }
    public String getToolTipText() {
      return ""; //$NON-NLS-1$
    }
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    public Object getAdapter( final Class adapter ) {
      Object result = null;
      if( adapter == IEntity.class ) {
        result = entity;
      }
      return result;
    }
  }

  public static Object[] getElements( final Object parent ) {
    return get( parent ).getElements( parent );
  }

  public static Object getParent( final Object child ) {
    return get( child ).getParent( child );
  }
  
  public static boolean hasChildren( final Object parent ) {
    return get( parent ).hasChildren( parent );
  }
  
  public static Object[] getChildren( final Object parent ) {
    return get( parent ).getChildren( parent );
  }
  
  public static String getText( final Object element ) {
    return get( element ).getText( element );
  }
  
  public static Image getImage( final Object element ) {
    return get( element ).getImage( element );
  }
  
  public static IEditorInput getEditorInput( final Object element ) {
    Map<Object, IEditorInput> editorInputs = editorInputRegistry.get();
    IEditorInput result = editorInputs.get( element );
    if( result == null && !( element instanceof IDataModel ) ) {
      IEntityAdapter entityAdapter = get( element );
      String name = entityAdapter.getEditorName( element );
      ImageDescriptor editorImage = entityAdapter.getEditorImage( element );
      result = new EntityEditorInput( ( IEntity )element, name, editorImage );
      editorInputs.put( element, result );
    }
    return result;
  }
  
  public static void createNewMenuItem( final Object element,
                                        final IMenuManager menuManager )
  {
    if( element != null ) {
      get( element ).createNewMenu( element, menuManager );
    }
  }
  
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public static String createWizardTitle( final Object element, 
                                          final Class destinationType )
  {
    return get( element ).createWizardTitle( destinationType );
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public static IWizardPage createWizardPage( final Object element,
                                              final Class destinationType )
  {
    return get( element ).createWizardPage( element, destinationType );
  }
  
  public static void refreshAssociations( final Object element, 
                                          final StructuredViewer viewer )
  {
    viewer.getControl().getDisplay().asyncExec( new Runnable() {
      public void run() {
        get( element ).refreshAssociations( element, viewer );
      }
    } );
  }
  
  public static IPropertySource getPropertySource( final Object element ) {
    return get( element ).getPropertySource( element );
  }
  

  public static FormPage[] getEditorPages( final Object element, 
                                           final FormEditor editor )
  {
    return get( element ).getEditorPages( element, editor );
  }


  
  //////////////////
  // helping methods
  
  static ImageDescriptor getImageDescriptor( final EntityImage name ) {
    return getSharedImages().getImageDescriptor( name.toString() );
  }
  
  private static IEntityAdapter get( final Object element ) {
    IEntityAdapter result = null;
    if( element instanceof IWorkbenchPartSite ) {
      result = ROOT_ADAPTER;
    } else if( element instanceof IDataModel ) {
      result = DATA_MODEL_ADAPTER;
    } else if( element instanceof IPrincipal ) {
      result = PRINCIPAL_ADAPTER;
    } else if( element instanceof IEmployee ) {
      result = EMPLOYEE_ADAPTER;
    } else if( element instanceof IProject ) {
      result = PROJECT_ADAPTER;
    } else if( element instanceof IAssignment ) {
      result = ASSIGNMENT_ADAPTER;
    } else if( element instanceof ITask ) {
      result = TASK_ADAPTER;
    } else {
      //TODO: [yao] NLS#
      Object[] param = new Object[] { element.getClass().getName() };
      String bound
        = NLS.bind( RMSMessages.get().EntityAdapter_ElementNotSupported, param );
      throw new IllegalArgumentException( bound );
    }
    return result;
  }
  
  private static ISharedImages getSharedImages() {
    return PlatformUI.getWorkbench().getSharedImages();
  }
}
