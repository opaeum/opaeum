package net.sf.nakeduml.uigeneration;

import java.util.Collection;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

@StepDependency(phase = UserInteractionTransformationPhase.class, requires = { FeatureBuilder.class }, after = FeatureBuilder.class)
public class UserInteractionCreator extends GeneratingUserInteractionTransformationStep {
	private INakedInterface hierarchy;

	@Override
	public void initialize(INakedPackage workspace, TextWorkspace textWorkspace, UserInteractionWorkspace uiModel, OJPackage javaModel,
			NakedUmlConfig config) {
		// TODO Auto-generated method stub
		super.initialize(workspace, textWorkspace, uiModel, javaModel, config);
		hierarchy = findInterface(this.entryModel, "Hierarchy");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier o) {
		if (hasUserInteractions(o)) {
			UserInteractionFolder folder = new UserInteractionFolder();
			folder.setName(getDomainNameOf(o));
			AbstractUserInteractionFolder parentFolder = findFolderFor(o.getNameSpace());
			folder.setRepresentedElement(findDomainClassifierFor(o));
			parentFolder.addToChildFolder(folder);
			createUserInteraction(o, folder, UserInteractionKind.CREATE);
			createUserInteraction(o, folder, UserInteractionKind.EDIT);
			createUserInteraction(o, folder, UserInteractionKind.LIST);
			createUserInteraction(o, folder, UserInteractionKind.VIEW);
		}
	}

	private ClassifierUserInteraction createUserInteraction(INakedClassifier c, UserInteractionFolder folder, UserInteractionKind kind) {
		// TODO make the user messages more clever
		ClassifierUserInteraction eui = new ClassifierUserInteraction(folder);
		DomainClassifier dc = findDomainClassifierFor(c);
		eui.setRepresentedElement(dc);
		eui.setUserInteractionKind(kind);
		eui.setName(getUserInteractionNameOf(c, kind));
		eui.setInstructionToUser("Please " + kind.name().toLowerCase() + " the " + dc.getHumanName());
		eui.setSuccessMessage("The " + dc.getHumanName() + " was " + kind.name().toLowerCase() + "ed successfully");
		eui.setInHierarchy(c.conformsTo(hierarchy));
		return eui;
	}

	private static INakedInterface findInterface(INakedElement element, String name) {
		if ((element instanceof INakedInterface) && ((INakedInterface) element).getName().equals(name)) {
			return (INakedInterface) element;
		} else {
			Collection<? extends INakedElement> elements = element.getOwnedElements();
			for (INakedElement element2 : elements) {
				INakedInterface findInterface = findInterface(element2, name);
				if (findInterface != null) {
					return findInterface;
				}
			}
		}
		return null;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p) {
		UserInteractionFolder childFolder = new UserInteractionFolder();
		childFolder.setName(getDomainNameOfPackage(p));
		childFolder.setRepresentedElement(findDomainPackageFor(p));
		if (p.getParent() == null) {
			super.uiModel.addToChildFolder(childFolder);
		} else {
			findFolderFor(p.getParent()).addToChildFolder(childFolder);
		}
	}
}
