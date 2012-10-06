package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetPrimaryEditor extends LazyInitializeUimQuery <Classifier,ClassUserInteractionModel, ObjectEditor>{

	@Override
	protected boolean generateModel(Classifier context,FormSynchronizer2 fs2){
		return fs2.beforeClass(context) instanceof ClassUserInteractionModel;
	}

	@Override
	protected ObjectEditor getResult(ClassUserInteractionModel eObject){
		return eObject.getPrimaryEditor();
	}

}
