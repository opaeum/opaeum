package org.opaeum.reverse.popup.actions;

import org.eclipse.jface.viewers.IStructuredSelection;

public class ReverseEngineerSimpleClassesAction extends AbstractReverseEngineerJavaAction{

	public ReverseEngineerSimpleClassesAction(IStructuredSelection selection){
		super(selection,"Reverse Engineer Normal Classes");
	}

	@Override
	protected AbstractUmlGenerator createGenerator(){
		return new SimpleUmlGenerator();
	}
}
