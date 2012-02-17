// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.IAssignment;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;

final class ProjectAdapter implements IEntityAdapter {

  public Object[] getElements( final Object parent ) {
    return ( ( IProject )parent ).getAssignments().toArray();
  }

  public Object getParent( final Object child ) {
    return ( ( IProject )child ).getPrincipal();
  }

  public Object[] getChildren( final Object parent ) {
    return getElements( parent );
  }

  public boolean hasChildren( final Object parent ) {
//    return ( ( IProject )parent ).getAssignments().size() > 0;
    return false;
  }

  public Image getImage( final Object element ) {
    return getEditorImage( element ).createImage();
  }

  public String getText( final Object element ) {
    return ( ( IProject )element ).getName();
  }

  public String getEditorName( final Object element ) {
    return getText( element );
  }

  public ImageDescriptor getEditorImage( final Object element ) {
    Image image = Activator.getDefault().getImage( Activator.IMG_PROJECT );
    return ImageDescriptor.createFromImage( image );
  }

  public void createNewMenu( final Object element,
                             final IMenuManager menuManager )
  {
//    EntityImage assignment = EntityImage.ASSIGNMENT;
//    NewAction newAssignment
//      = new NewAction( ( IEntity )element,
//                       IAssignment.class,
//                       "New Assignment",
//                       EntityAdapter.getImageDescriptor( assignment ) );
//    menuManager.add( newAssignment );
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( final Object element, 
                                       final Class destinationType )
  {
    IWizardPage result = null;
    if( destinationType == IAssignment.class ) {
      result = new NewAssignmentWizardPage( ( IProject )element );
    } else if( destinationType == ITask.class ) {
      result = new NewTaskWizardPage( ( IProject )element );
    }
    return result;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( final Class destinationType ) {
    return RMSMessages.get().ProjectAdapter_CreateAssignment;
  }

  public void refreshAssociations( final Object element, 
                                   final StructuredViewer viewer )
  {
    IProject project = ( IProject )element;
    viewer.refresh( project.getPrincipal() );
  }

  public IPropertySource getPropertySource( final Object element ) {
    return new IPropertySource() {
      private final static String ID_NAME = "Name"; //$NON-NLS-1$
      private final static String ID_DESCRIPTION = "Description"; //$NON-NLS-1$
      private final static String ID_START_DATE = "Start Date"; //$NON-NLS-1$
      private final static String ID_END_DATE = "End Date"; //$NON-NLS-1$
      private final IProject project = ( IProject )element;
      public Object getEditableValue() {
        return null;
      }
      public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
          new PropertyDescriptor( ID_NAME, RMSMessages.get().ProjectAdapter_Name ),
          new PropertyDescriptor( ID_DESCRIPTION, RMSMessages.get().ProjectAdapter_Description ),
          new PropertyDescriptor( ID_START_DATE, RMSMessages.get().ProjectAdapter_StartDate ),
          new PropertyDescriptor( ID_END_DATE, RMSMessages.get().ProjectAdapter_EndDate )
        };
      }
      public Object getPropertyValue( final Object id ) {
        Object result = null;
        if( ID_NAME == id ) {
          result = project.getName();
        }
        if( ID_DESCRIPTION == id ) {
          result = project.getDescription();
        }
        if( ID_START_DATE == id ) {
          result = project.getStartDate();
        }
        if( ID_END_DATE == id ) {
          result = project.getEndDate();
        }
        return result;
      }
      public boolean isPropertySet( final Object id ) {
        return false;
      }
      public void resetPropertyValue( final Object id ) {
      }
      public void setPropertyValue( final Object id, final Object value ) {
      }
    };
  }

  public FormPage[] getEditorPages( final Object element,
                                    final FormEditor editor )
  {
    ProjectCopy project = new ProjectCopy( ( IProject )element );
    return new FormPage[] {
      new ProjectOverviewPage( editor, project ),
      new ProjectTasksPage( editor, project ),
      new ProjectGanttPage( editor, project )
    };
  }
}