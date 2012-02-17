// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.rap.rms.data.IEntity;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter;


public class NewEntityWizard extends Wizard implements INewEntityWizard {

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public NewEntityWizard( final IEntity entity, final Class destinationType ) {
    String title = EntityAdapter.createWizardTitle( entity, destinationType );
    setWindowTitle( title );
    addPage( EntityAdapter.createWizardPage( entity, destinationType ) );
  }
  
  @Override
  public boolean performFinish() {
    return ( ( INewEntityPage )getPages()[ 0 ] ).create();
  }

  public IEntity getEntity() {
    return ( ( INewEntityPage )getPages()[ 0 ] ).getEntity();
  }
}
