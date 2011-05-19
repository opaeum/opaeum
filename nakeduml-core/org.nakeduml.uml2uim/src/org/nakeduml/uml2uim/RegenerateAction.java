package org.nakeduml.uml2uim;

import java.io.IOException;


import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.diagrams.model.Diagrams;

public class RegenerateAction extends AbstractUIMGenerationAction implements IObjectActionDelegate {



	protected void runActionRecursively(NamedElement modelElement) {
		try {
			Model umlModel = modelElement.getModel();
			UserInteractionModel uimModel = getUimModel(umlModel);
			Diagrams diagrams = getRootDiagrams(uimModel);
			//TODO visit up with the hierarchy until all parents are guarranteed to be in place
			new FormFolderSynchronizer(uimModel, diagrams,true).visitSafelyRecursively(modelElement);
			new FormSynchronizer(uimModel, diagrams,true).visitRecursively(modelElement);
			uimModel.eResource().save(null);
			diagrams.eResource().save(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
