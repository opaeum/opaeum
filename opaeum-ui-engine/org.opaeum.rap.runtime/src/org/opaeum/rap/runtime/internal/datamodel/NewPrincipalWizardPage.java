// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IPrincipal;
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
import org.opaeum.runtime.domain.IPersistentObject;

final class NewPrincipalWizardPage
  extends WizardPage
  implements INewEntityPage
{

  private Text txtName;
  private IPrincipal newPrincipal;

  NewPrincipalWizardPage() {
    super( "New Principal" ); //$NON-NLS-1$
    setTitle( RMSMessages.get().NewPrincipalWizardPage_NewPrincipal );
    setMessage( RMSMessages.get().NewPrincipalWizardPage_EnterPrincipalInfo,
                IMessageProvider.WARNING );
    setPageComplete( false );
    Image img = Activator.getDefault().getImage( Activator.IMG_WIZ_BAN  );
    setImageDescriptor( ImageDescriptor.createFromImage( img ) );
  }

  public void createControl( final Composite parent ) {
    BgColorUtil.setBgColor( parent.getParent() );
    BgColorUtil.setBgColor( parent.getParent() );
    BgColorUtil.setBgColor( parent );
    Composite composite = new Composite( parent, SWT.NONE );
    BgColorUtil.setBgColor( composite );
    composite.setLayout( new GridLayout( 1, false ) );
    
    Label lblName = new Label( composite, SWT.LEFT );
    BgColorUtil.setBgColor( lblName );
    lblName.setText( RMSMessages.get().NewPrincipalWizardPage_EnterPrincipalName );
    
    txtName = new Text( composite, SWT.SINGLE | SWT.BORDER );
    txtName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    txtName.addModifyListener( new ModifyListener() {
      public void modifyText( final ModifyEvent event ) {
        if( txtName.getText().length() > 0 ) {
          setMessage( RMSMessages.get().NewPrincipalWizardPage_CreatePrincipal,
                      IMessageProvider.INFORMATION );
          setPageComplete( true );
        } else {
          setMessage( RMSMessages.get().NewPrincipalWizardPage_EnterName, 
                      IMessageProvider.WARNING );
          setPageComplete( false );
        }
      }
    } );
    
    txtName.setFocus();
    setControl( composite );
  }

  public boolean create() {
    IDataModel model = DataModelRegistry.getFactory();
    newPrincipal = model.newPrincipal( txtName.getText() );
    return newPrincipal != null;
  }

  public IPersistentObject getEntity() {
    return newPrincipal;
  }
}