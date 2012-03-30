// Created on 13.09.2007
package org.opaeum.rap.runtime.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.wizards.INewEntityWizard;
import org.opaeum.rap.runtime.internal.wizards.NewEntityWizard;
import org.opaeum.runtime.domain.IPersistentObject;


public class NewAction extends Action {

  private final IPersistentObject entity;
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private final Class destinationType;
  OpaeumRapSession opaeumSession;
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public NewAction( final IPersistentObject entity,
                    final Class destinationType,
                    final String text,
                    final ImageDescriptor imageDescriptor, OpaeumRapSession opaeumSession )
  {
    super( text, imageDescriptor );
    this.entity = entity;
    this.destinationType = destinationType;
    this.opaeumSession=opaeumSession;
  }
  
  @Override
  public void run() {
    Display display = Display.getCurrent();
    Wizard wizard = new NewEntityWizard( entity, destinationType );
    WizardDialog dlg = new WizardDialog( display.getActiveShell(), wizard );
    if( dlg.open() == Window.OK ) {
      IPersistentObject newEntity = ( ( INewEntityWizard )wizard ).getEntity();
      OpenEditorAction.openEditor( newEntity, true ,opaeumSession);
    }
  }
}
