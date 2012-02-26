// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.IAssignment;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.EntityImage;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;

final class AssignmentAdapter implements IEntityAdapter {

  public Object[] getElements( final Object parent ) {
    return null;
  }

  public Object getParent( final Object child ) {
    return ( ( IAssignment )child ).getProject();
  }

  public Object[] getChildren( final Object parent ) {
    return null;
  }

  public boolean hasChildren( final Object parent ) {
    return false;
  }

  public Image getImage( final Object element ) {
    return getEditorImage( element ).createImage();
  }

  public String getText( final Object element ) {
    return ( ( IAssignment )element ).getEmployee().getLastName();
  }

  public String getEditorName( final Object element ) {
    IAssignment assignment = ( IAssignment )element;
    StringBuffer result = new StringBuffer();
    result.append( assignment.getProject().getName() );
    result.append( ": " ); //$NON-NLS-1$
    result.append( assignment.getEmployee().getLastName() );
    return result.toString();
  }

  public ImageDescriptor getEditorImage( final Object element ) {
    return EntityAdapter.getImageDescriptor( EntityImage.ASSIGNMENT );
  }

  public void createNewMenu( final Object element,
                             final IMenuManager menuManager )
  {
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( final Object element,
                                       final Class destinationType )
  {
    return null;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( final Class destinationType ) {
    return null;
  }

  public void refreshAssociations( final Object element,
                                   final StructuredViewer viewer ) {
    IAssignment assignment = ( IAssignment )element;
    viewer.refresh( assignment.getProject() );
    viewer.refresh( assignment.getEmployee() );
  }

  public IPropertySource getPropertySource( final Object element ) {
    return new IPropertySource() {
      private final static String ID_PROJECT = "Project"; //$NON-NLS-1$
      private final static String ID_EMPLOYEE = "Employee"; //$NON-NLS-1$
      private final IAssignment assignment = ( IAssignment )element;
      public Object getEditableValue() {
        return null;
      }
      public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
          new PropertyDescriptor( ID_PROJECT, RMSMessages.get().AssignmentAdapter_Project ),
          new PropertyDescriptor( ID_EMPLOYEE, RMSMessages.get().AssignmentAdapter_Employee )
        };
      }
      public Object getPropertyValue( final Object id ) {
        Object result = null;
        if( ID_PROJECT == id ) {
          result = assignment.getProject().getName();
        } else if( ID_EMPLOYEE == id ) {
          StringBuffer buffer = new StringBuffer();
          buffer.append( assignment.getEmployee().getFirstName() );
          buffer.append( " " ); //$NON-NLS-1$
          buffer.append( assignment.getEmployee().getLastName() );
          result = buffer.toString();
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
    return new FormPage[] {
      new AssignmentOverviewPage( editor )
    };
  }
}