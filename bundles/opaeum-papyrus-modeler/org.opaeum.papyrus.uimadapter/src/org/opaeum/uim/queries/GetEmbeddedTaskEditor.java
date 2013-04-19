package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetEmbeddedTaskEditor extends LazyInitializeUimQuery<Action,EmbeddedTaskEditor,EmbeddedTaskEditor>{
	@Override
	protected boolean generateModel(Action context,FormSynchronizer2 fs2){
		return fs2.beforeAction((OpaqueAction) context) instanceof EmbeddedTaskEditor;
	}
	@Override
	protected EmbeddedTaskEditor getResult(EmbeddedTaskEditor eObject){
		return eObject;
	}
}
