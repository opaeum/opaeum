// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.internal.data.DataModel.Principal;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.actions.NewAction;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;
import org.opaeum.runtime.domain.IPersistentObject;

final class PrincipalAdapter implements IEntityAdapter {

  public Object[] getElements( final Object parent ) {
    return ( ( IPrincipal )parent ).getProjects().toArray();
  }

  public Object getParent( final Object child ) {
    return DataModelRegistry.getFactory();
  }

  public Object[] getChildren( final Object parent ) {
    return getElements( parent );
  }

  public boolean hasChildren( final Object parent ) {
    return ( ( IPrincipal )parent ).getProjects().size() > 0;
  }

  public Image getImage( final Object element ) {
    return getEditorImage( element ).createImage();
  }

  public String getText( final Object element ) {
    return ( ( IPrincipal )element ).getName();
  }

  public String getEditorName( final Object element ) {
    return getText( element );
  }

  public ImageDescriptor getEditorImage( final Object element ) {
    Image image = Activator.getDefault().getImage( Activator.IMG_PRINCIPAL );
    return ImageDescriptor.createFromImage( image );
  }

  public void createNewMenu( final Object element,
                             final IMenuManager menuManager )
  {
    NewAction newProject
      = new NewAction( ( IPersistentObject )element,
                       IProject.class,
                       RMSMessages.get().PrincipalAdapter_NewProject,
                       getEditorImage( element ),null );
    menuManager.add( newProject );
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( final Object element, 
                                       final Class destinationType )
  {
    return new NewProjectWizardPage( ( IPrincipal ) element );
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( final Class destinationType ) {
    return RMSMessages.get().PrincipalAdapter_CreateProject;
  }

  public void refreshAssociations( final Object element, 
                                   final StructuredViewer viewer )
  {
    viewer.refresh();
  }

  public IPropertySource getPropertySource( final Object element ) {
    return new IPropertySource() {
      private final static String ID_NAME = "Name"; //$NON-NLS-1$
      private final static String ID_STREET = "Street"; //$NON-NLS-1$
      private final static String ID_CITY = "City"; //$NON-NLS-1$
      private final static String ID_POST_CODE = "ZIP/Postal Code"; //$NON-NLS-1$
      private final static String ID_COUNTRY = "Country"; //$NON-NLS-1$
      private final static String ID_LAST_NAME = "Lastname"; //$NON-NLS-1$
      private final static String ID_FIRST_NAME = "Firstname"; //$NON-NLS-1$
      private final static String ID_EMAIL = "E-Mail"; //$NON-NLS-1$
      private final static String ID_PHONE = "Phone Number"; //$NON-NLS-1$
      private final static String ID_FAX = "FAX Number"; //$NON-NLS-1$
      private final static String ID_MOBILE = "Mobile Number"; //$NON-NLS-1$
      private final IPrincipal principal = ( IPrincipal )element;
      public Object getEditableValue() {
        return null;
      }
      public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
          new PropertyDescriptor( ID_NAME, RMSMessages.get().PrincipalAdapter_Name ),
          new PropertyDescriptor( ID_STREET, RMSMessages.get().PrincipalAdapter_Street ),
          new PropertyDescriptor( ID_CITY, RMSMessages.get().PrincipalAdapter_City ),
          new PropertyDescriptor( ID_POST_CODE, RMSMessages.get().PrincipalAdapter_ZipPostalCode ),
          new PropertyDescriptor( ID_COUNTRY, RMSMessages.get().PrincipalAdapter_Country ),
          new PropertyDescriptor( ID_LAST_NAME, RMSMessages.get().PrincipalAdapter_Lastname ),
          new PropertyDescriptor( ID_FIRST_NAME, RMSMessages.get().PrincipalAdapter_Firstname ),
          new PropertyDescriptor( ID_EMAIL, RMSMessages.get().PrincipalAdapter_EMail ),
          new PropertyDescriptor( ID_PHONE, RMSMessages.get().PrincipalAdapter_PhoneNumber ),
          new PropertyDescriptor( ID_FAX, RMSMessages.get().PrincipalAdapter_FAXNumber ),
          new PropertyDescriptor( ID_MOBILE, RMSMessages.get().PrincipalAdapter_MobileNumber )
        };
      }
      public Object getPropertyValue( final Object id ) {
        Object result = null;
        if( ID_NAME == id ) {
          result = principal.getName();
        }
        if( ID_STREET == id ) {
          result = principal.getStreet();
        }
        if( ID_CITY == id ) {
          result = principal.getCity();
        }
        if( ID_POST_CODE == id ) {
          result = principal.getPostCode();
        }
        if( ID_COUNTRY == id ) {
          result = principal.getCountry();
        }
        if( ID_LAST_NAME == id ) {
          result = principal.getLastName();
        }
        if( ID_FIRST_NAME == id ) {
          result = principal.getFirstName();
        }
        if( ID_EMAIL == id ) {
          result = principal.getEMail();
        }
        if( ID_PHONE == id ) {
          result = principal.getPhoneNumber();
        }
        if( ID_FAX == id ) {
          result = principal.getFaxNumber();
        }
        if( ID_MOBILE == id ) {
          result = principal.getMobileNumber();
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
    IPrincipal principal = (IPrincipal) element;
    return new FormPage[] {
      new PrincipalOverviewPage( editor, principal )
    };
  }
}