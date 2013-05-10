package org.opaeum.reverse.popup.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.context.OpenUmlFile;

public class ReverseEngineerAnnotationsToProfileAction extends AbstractReverseEngineerJavaAction{

	public ReverseEngineerAnnotationsToProfileAction(IStructuredSelection selection){
		super(selection,"Reverse Engineer Annotations to Profile");
	}

	@Override
	protected AbstractUmlGenerator createGenerator(){
		return new ProfileGenerator();
	}
	@Override
	protected boolean canReverseInto(OpenUmlFile ouf){
		return ouf.getModel() instanceof Profile;
	}
}
