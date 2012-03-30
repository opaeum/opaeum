// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
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

final class NewProjectWizardPage
  extends WizardPage
  implements INewEntityPage
{

  private IProject newProject;
  private IPrincipal principal;
  private Text txtName;
  
  NewProjectWizardPage( final IPrincipal principal ) {
    super( "New Project" ); //$NON-NLS-1$
    setTitle( RMSMessages.get().NewProjectWizardPage_NewProject+principal.getName() + "]" ); //$NON-NLS-2$ 
    setMessage( RMSMessages.get().NewProjectWizardPage_EnterProjectInfo,
                IMessageProvider.WARNING );
    setPageComplete( false );
    this.principal = principal;
    Image img = Activator.getDefault().getImage( Activator.IMG_WIZ_BAN );
    setImageDescriptor( ImageDescriptor.createFromImage( img ) );
  }

  public void createControl( final Composite parent ) {
    BgColorUtil.setBgColor( parent.getParent() );
    BgColorUtil.setBgColor( parent );
    Composite composite = new Composite( parent, SWT.NONE );
    BgColorUtil.setBgColor( composite );
    composite.setLayout( new GridLayout( 1, false ) );
    
    Label lblName = new Label( composite, SWT.LEFT );
    BgColorUtil.setBgColor( lblName );
    lblName.setText( RMSMessages.get().NewProjectWizardPage_EnterProjectName );
    
    txtName = new Text( composite, SWT.SINGLE | SWT.BORDER );
    txtName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    txtName.addModifyListener( new ModifyListener() {
      public void modifyText( final ModifyEvent event ) {
        if( txtName.getText().length() > 0 ) {
          setMessage( RMSMessages.get().NewProjectWizardPage_CreateProject,
                      IMessageProvider.INFORMATION );
          setPageComplete( true );
        } else {
          setMessage( RMSMessages.get().NewProjectWizardPage_EnterName, 
                      IMessageProvider.WARNING );
          setPageComplete( false );
        }
      }
    } );
    
    txtName.setFocus();
    setControl( composite );
  }

  public boolean create() {
    newProject = principal.newProject( txtName.getText() );
    return newProject != null;
  }

  public IPersistentObject getEntity() {
    return newProject;
  }
}