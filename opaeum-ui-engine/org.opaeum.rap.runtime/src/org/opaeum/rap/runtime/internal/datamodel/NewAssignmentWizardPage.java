// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IAssignment;
import org.eclipse.rap.rms.data.IEmployee;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.wizards.INewEntityPage;

final class NewAssignmentWizardPage extends WizardPage
  implements INewEntityPage
{

  private final IProject project;
  private IEmployee employee;
  private IAssignment newAssignment;
  private final class ComboContentProvider
    implements IStructuredContentProvider
  {

    public void dispose() {
    }

    public void inputChanged( final Viewer viewer,
                              final Object oldInput,
                              final Object newInput )
    {
    }

    public Object[] getElements( final Object inputElement ) {
      return DataModelRegistry.getFactory().getEmployees().toArray();
    }
  }
  private final class ComboLabelProvider extends LabelProvider {

    @Override
    public String getText( final Object element ) {
      IEmployee employeeLbl = ( IEmployee )element;
      return employeeLbl.getFirstName() + " " + employeeLbl.getLastName(); //$NON-NLS-1$
    }
  }

  NewAssignmentWizardPage( final IProject project ) {
    super( "New Resource Assignment" ); //$NON-NLS-1$
    setTitle( RMSMessages.get().NewAssignmentWizardPage_NewResourceAssign + project.getName() + "]" ); //$NON-NLS-2$ 
    setMessage( RMSMessages.get().NewAssignmentWizardPage_EnterInfo,
                IMessageProvider.WARNING );
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
    lblName.setText( RMSMessages.get().NewAssignmentWizardPage_SelectResource );
    ComboViewer viewer = new ComboViewer( composite );
    viewer.setContentProvider( new ComboContentProvider() );
    viewer.setLabelProvider( new ComboLabelProvider() );
    viewer.setInput( DataModelRegistry.getFactory() );
    viewer.addSelectionChangedListener( new ISelectionChangedListener() {

      public void selectionChanged( final SelectionChangedEvent event ) {
        setMessage( RMSMessages.get().NewAssignmentWizardPage_SelectResourceToAssign,
                    IMessageProvider.WARNING );
        setPageComplete( false );
        ISelection selection = event.getSelection();
        if( selection != null ) {
          IStructuredSelection sselection = ( IStructuredSelection )selection;
          employee = ( IEmployee )sselection.getFirstElement();
          setMessage( RMSMessages.get().NewAssignmentWizardPage_CreateAssignment,
                      IMessageProvider.INFORMATION );
          setPageComplete( true );
        }
      }
    } );
    viewer.getControl().setFocus();
    setControl( composite );
  }

  public boolean create() {
    newAssignment = project.newAssignment( employee );
    return newAssignment != null;
  }

  public IEntity getEntity() {
    return newAssignment;
  }
}