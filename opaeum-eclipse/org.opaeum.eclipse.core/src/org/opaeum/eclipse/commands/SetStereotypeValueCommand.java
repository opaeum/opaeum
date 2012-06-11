package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class SetStereotypeValueCommand extends AbstractCommand{
	Command delegate;
	public SetStereotypeValueCommand(EditingDomain domain,Element element,Stereotype stereotype,String property,Object value){
		EStructuralFeature feature = stereotype.getDefinition().getEStructuralFeature(property);
		delegate = SetCommand.create(domain, element.getStereotypeApplication(stereotype), feature, value);
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public void execute(){
		delegate.execute();
	}
	@Override
	public boolean canUndo(){
		return delegate.canUndo();
	}
	@Override
	public void undo(){
		delegate.undo();
	}
	public void redo(){
		execute();
	}
}
