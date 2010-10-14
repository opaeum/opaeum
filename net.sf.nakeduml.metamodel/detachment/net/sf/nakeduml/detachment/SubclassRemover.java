package net.sf.nakeduml.detachment;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.profiles.INakedProfile;

/**
 * Removes classes from its superclass and removes implementations from their
 * interfaces. <br>
 * The former would have been added to the superclass' 'subClasses' collection
 * and the latter would have been added to the 'implementingClassifier'
 * colllection on INakedInterface
 */
@StepDependency(phase = DetachmentPhase.class)
public class SubclassRemover extends NakedElementDetachor {
	@VisitBefore(matchSubclasses = true)
	public void remove(INakedElement e) {
		// INakedElementOwner owner = e;
		// while (owner instanceof INakedElement) {
		// if (owner.equals(workspace.getSourcePackage())) {
		workspace.removeElementById(e.getId());
		// return;
		// } else {
		// owner = ((INakedElement) owner).getOwnerElement();
		// }
		// }
		// TODO remove from parent
	}

	@VisitBefore(matchSubclasses = true)
	public void remove(INakedModel e) {
		workspace.removeOwnedElement(e);
	}
	@VisitBefore(matchSubclasses = true)
	public void remove(INakedProfile e) {
		workspace.removeOwnedElement(e);
	}

	@VisitBefore()
	public void generalization(INakedGeneralization ng) {
		INakedClassifier nc = (INakedClassifier) workspace.getModelElement(ng.getGeneral());
		if (nc != null) {
			nc.removeSubClass(ng.getSpecific());
		}
	}

	@VisitBefore()
	public void realization(INakedInterfaceRealization r) {
		INakedInterface i = (INakedInterface) workspace.getModelElement(r.getContract());
		if (i != null) {
			i.removeImplementingClassifier(r.getImplementingClassifier());
		}
	}
}