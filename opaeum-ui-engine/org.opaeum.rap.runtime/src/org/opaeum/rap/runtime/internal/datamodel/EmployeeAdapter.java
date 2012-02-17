// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IEmployee;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.EntityImage;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;

final class EmployeeAdapter implements IEntityAdapter {

  public Object[] getChildren( final Object parent ) {
    return null;
  }

  public ImageDescriptor getEditorImage( final Object element ) {
    return EntityAdapter.getImageDescriptor( EntityImage.EMPLOYEE );
  }

  public String getEditorName( final Object element ) {
    return getText( element );
  }

  public Object[] getElements( final Object parent ) {
    return null;
  }

  public Image getImage( final Object element ) {
    return getEditorImage( element ).createImage();
  }

  public Object getParent( final Object child ) {
    return DataModelRegistry.getFactory();
  }

  public String getText( final Object element ) {
    IEmployee employee = ( IEmployee )element;
    StringBuffer result = new StringBuffer();
    result.append( employee.getLastName() );
    result.append( ", " ); //$NON-NLS-1$
    result.append( employee.getFirstName() );
    return result.toString();
  }

  public boolean hasChildren( final Object parent ) {
    return false;
  }

  public void createNewMenu( final Object element,
                             final IMenuManager menuManager )
  {
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( Object element,
                                       final Class destinationType )
  {
    return null;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( final Class destinationType ) {
    return null;
  }

  public void refreshAssociations( final Object element, 
                                   final StructuredViewer viewer )
  {
    viewer.refresh();
  }

  public IPropertySource getPropertySource( final Object element ) {
    return new IPropertySource() {
      private final static String ID_LAST_NAME = "LastName"; //$NON-NLS-1$
      private final static String ID_FIRST_NAME = "FirstName"; //$NON-NLS-1$
      private final IEmployee employee = ( IEmployee )element;
      public Object getEditableValue() {
        return null;
      }
      public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
          new PropertyDescriptor( ID_LAST_NAME, RMSMessages.get().EmployeeAdapter_LastName ),
          new PropertyDescriptor( ID_FIRST_NAME, RMSMessages.get().EmployeeAdapter_FirstName ),
        };
      }
      public Object getPropertyValue( final Object id ) {
        Object result = null;
        if( ID_LAST_NAME == id ) {
          result = employee.getLastName();
        } else if( ID_FIRST_NAME == id ) {
          result = employee.getFirstName();
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
      new EmployeelOverviewPage( editor )
    };
  }
}