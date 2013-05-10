package org.opaeum.reverse.popup.actions;

import org.eclipse.jface.viewers.IStructuredSelection;

public class ReverseEngineerJpaClassesAction extends AbstractReverseEngineerJavaAction{

	public ReverseEngineerJpaClassesAction(IStructuredSelection selection){
		super(selection,"Reverse Engineer JPA Classes");
	}

	@Override
	protected AbstractUmlGenerator createGenerator(){
		return new UmlGeneratorFromJpa();
	}
}
