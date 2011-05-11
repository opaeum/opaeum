package org.nakeduml.uml2uim;

import java.io.IOException;


import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.diagrams.model.Diagrams;

public class SynchronizeAction extends AbstractUIMGenerationAction implements IObjectActionDelegate {



	protected void runActionRecursively(NamedElement modelElement) {
		try {
			Model umlModel = modelElement.getModel();
			UserInteractionModel uimModel = getUimModel(umlModel);
			Diagrams diagrams = getRootDiagrams(uimModel);
			new FormFolderSynchronizer(uimModel, diagrams,false).visitSafelyRecursively(modelElement);
			new FormSynchronizer(uimModel, diagrams,false).visitRecursively(modelElement);
			uimModel.eResource().save(null);
			diagrams.eResource().save(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
