// Created on 13.09.2007
package org.opaeum.rap.runtime.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.opaeum.name.NameConverter;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.wizards.NewEntityWizard;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;

public class NewAction extends Action{
	private static final long serialVersionUID = 8979776016523486441L;
	private final IPersistentObject owner;
	OpaeumRapSession opaeumSession;
	private JavaTypedElement property;
	public NewAction(final IPersistentObject entity,JavaTypedElement property,final ImageDescriptor imageDescriptor,
			OpaeumRapSession opaeumSession){
		super("New " + NameConverter.separateWords(NameConverter.capitalize(property.getName())), imageDescriptor);
		this.owner = entity;
		this.property = property;
		this.opaeumSession = opaeumSession;
	}
	@Override
	public void run(){
		Display display = Display.getCurrent();
		CompositionNode child;
		try{
			child = (CompositionNode) property.getBaseType().newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		child.init((CompositionNode) owner);
		Wizard wizard = new NewEntityWizard(child, opaeumSession);
		WizardDialog dlg = new WizardDialog(display.getActiveShell(), wizard);
		if(dlg.open() == Window.OK){
			OpenEditorAction.openEntityEditor(child, true, opaeumSession);
		}
	}
}
