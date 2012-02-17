// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IEmployee;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.wizards.INewEntityPage;

final class NewEmployeeWizardPage 
  extends WizardPage
  implements INewEntityPage
{
  private final ModifyListenerImplementation validator
    = new ModifyListenerImplementation();
  
  private Text txtFirstName;
  private Text txtLastName;
  private IEmployee newEmployee;

  
  private final class ModifyListenerImplementation 
    implements ModifyListener
  {

    public void modifyText( final ModifyEvent event ) {
      if(    txtFirstName.getText().length() > 0 
          && txtLastName.getText().length() > 0 ) 
      {
        setMessage( RMSMessages.get().NewEmployeeWizardPage_CreateEmployee,
                    IMessageProvider.INFORMATION );
        setPageComplete( true );
      } else if( txtLastName.getText().length() == 0 ) {
        setMessage( RMSMessages.get().NewEmployeeWizardPage_EnterLastname, 
                    IMessageProvider.WARNING );
        setPageComplete( false );
      } else if( txtFirstName.getText().length() == 0 ) {
        setMessage( RMSMessages.get().NewEmployeeWizardPage_EnterFirstname, 
                    IMessageProvider.WARNING );
        setPageComplete( false );
      }
    }
  }

  NewEmployeeWizardPage() {
    super( "New Employee" ); //$NON-NLS-1$
    setTitle( RMSMessages.get().NewEmployeeWizardPage_NewEmployee );
    setMessage( RMSMessages.get().NewEmployeeWizardPage_EnterEmplyeeInfo,
                IMessageProvider.WARNING );
    setPageComplete( false );
    Image img = Activator.getDefault().getImage( Activator.IMG_WIZ_BAN );
    setImageDescriptor( ImageDescriptor.createFromImage( img ) );
  }

  public void createControl( final Composite parent ) {
	BgColorUtil.setBgColor( parent.getParent() );
    BgColorUtil.setBgColor( parent );
    Composite composite = new Composite( parent, SWT.NONE );
    BgColorUtil.setBgColor( composite );
    composite.setLayout( new GridLayout( 1, false ) );

    Label lblLastName = new Label( composite, SWT.LEFT );
    BgColorUtil.setBgColor( lblLastName );
    lblLastName.setText( RMSMessages.get().NewEmployeeWizardPage_EnterEmployeeLastname );
    
    txtLastName = new Text( composite, SWT.SINGLE | SWT.BORDER );
    txtLastName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    txtLastName.addModifyListener( validator );

    Label lblFirstName = new Label( composite, SWT.LEFT );
    BgColorUtil.setBgColor( lblFirstName );
    lblFirstName.setText( RMSMessages.get().NewEmployeeWizardPage_EnterEmpoyeeFirstName );
    
    txtFirstName = new Text( composite, SWT.SINGLE | SWT.BORDER );
    txtFirstName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    txtFirstName.addModifyListener( validator );
    
    txtLastName.setFocus();
    setControl( composite );
  }

  public boolean create() {
    IDataModel model = DataModelRegistry.getFactory();
    newEmployee
      = model.newEmployee( txtLastName.getText(), txtFirstName.getText() );
    return newEmployee != null;
  }

  public IEntity getEntity() {
    return newEmployee;
  }
}