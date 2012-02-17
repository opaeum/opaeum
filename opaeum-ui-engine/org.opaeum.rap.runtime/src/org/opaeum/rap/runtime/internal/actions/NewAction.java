// Created on 13.09.2007
package org.opaeum.rap.runtime.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.swt.widgets.Display;
import org.opaeum.rap.runtime.internal.wizards.INewEntityWizard;
import org.opaeum.rap.runtime.internal.wizards.NewEntityWizard;


public class NewAction extends Action {

  private final IEntity entity;
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private final Class destinationType;
  
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public NewAction( final IEntity entity,
                    final Class destinationType,
                    final String text,
                    final ImageDescriptor imageDescriptor )
  {
    super( text, imageDescriptor );
    this.entity = entity;
    this.destinationType = destinationType;
  }
  
  @Override
  public void run() {
    Display display = Display.getCurrent();
    Wizard wizard = new NewEntityWizard( entity, destinationType );
    WizardDialog dlg = new WizardDialog( display.getActiveShell(), wizard );
    if( dlg.open() == Window.OK ) {
      IEntity newEntity = ( ( INewEntityWizard )wizard ).getEntity();
      OpenEditorAction.openEditor( newEntity, true );
    }
  }
}
