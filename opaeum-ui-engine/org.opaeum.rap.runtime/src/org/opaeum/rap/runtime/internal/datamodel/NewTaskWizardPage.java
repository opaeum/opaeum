// Created on 08.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;
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

final class NewTaskWizardPage extends WizardPage implements INewEntityPage {

  private ITask newTask;
  private IProject project;
  private Text txtName;

  NewTaskWizardPage( final IProject project ) {
    super( "New Task" ); //$NON-NLS-1$
    setTitle( RMSMessages.get().NewTaskWizardPage_NewTask + project.getName() + "]" ); //$NON-NLS-2$
    setMessage( RMSMessages.get().NewTaskWizardPage_EnterTaskInfo, IMessageProvider.WARNING );
    setPageComplete( false );
    this.project = project;
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
    lblName.setText( RMSMessages.get().NewTaskWizardPage_EnterTaskName );
    txtName = new Text( composite, SWT.SINGLE | SWT.BORDER );
    txtName.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    txtName.addModifyListener( new ModifyListener() {
      public void modifyText( final ModifyEvent event ) {
        if( txtName.getText().length() > 0 ) {
          setMessage( RMSMessages.get().NewTaskWizardPage_CreateTask,
                      IMessageProvider.INFORMATION );
          setPageComplete( true );
        } else {
          setMessage( RMSMessages.get().NewTaskWizardPage_EnterName, IMessageProvider.WARNING );
          setPageComplete( false );
        }
      }
    } );
    txtName.setFocus();
    setControl( composite );
  }

  public boolean create() {
    newTask = project.newTask( txtName.getText() );
    return newTask != null;
  }

  public IPersistentObject getEntity() {
    return newTask;
  }
}
