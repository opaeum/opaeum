package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetResponsibilityViewer extends LazyInitializeUimQuery <Operation,ResponsibilityUserInteractionModel, ResponsibilityViewer>{

	@Override
	protected boolean generateModel(Operation context,FormSynchronizer2 fs2){
		return fs2.beforeOperation(context) instanceof ResponsibilityUserInteractionModel;
	}

	@Override
	protected ResponsibilityViewer getResult(ResponsibilityUserInteractionModel eObject){
		return eObject.getViewer();
	}

}
