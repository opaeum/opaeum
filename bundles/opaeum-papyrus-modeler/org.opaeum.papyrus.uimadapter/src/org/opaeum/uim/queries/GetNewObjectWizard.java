package org.opaeum.uim.queries;


import org.eclipse.uml2.uml.Classifier;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.wizard.NewObjectWizard;

public class GetNewObjectWizard extends LazyInitializeUimQuery<Classifier,ClassUserInteractionModel,NewObjectWizard> {
	protected boolean generateModel(final Classifier context,FormSynchronizer2 fs2){
		return fs2.beforeClass(context) instanceof ClassUserInteractionModel;
	}

	@Override
	protected NewObjectWizard getResult(ClassUserInteractionModel eObject){
		return eObject.getNewObjectWizard();
	}

}
