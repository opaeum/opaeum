package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Behavior;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetBehaviorExecutionEditor extends LazyInitializeUimQuery <Behavior,BehaviorUserInteractionModel, BehaviorExecutionEditor>{

	@Override
	protected boolean generateModel(Behavior context,FormSynchronizer2 fs2){
		return fs2.beforeClass(context)  instanceof BehaviorUserInteractionModel;
	}

	@Override
	protected BehaviorExecutionEditor getResult(BehaviorUserInteractionModel eObject){
		return eObject.getEditor();
	}

}
