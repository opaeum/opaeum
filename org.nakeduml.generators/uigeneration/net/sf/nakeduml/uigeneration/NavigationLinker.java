package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.NAVIGATION_TOO_MANY;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.RESULTING_USER_INTERACTION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationIn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionElement;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

@StepDependency(phase = UserInteractionTransformationPhase.class, requires = { SlotParticipationCreator.class }, after = SlotParticipationCreator.class)
public class NavigationLinker extends GeneratingUserInteractionTransformationStep {
	private HashMap<ClassifierUserInteraction, ClassifierUserInteraction> userInteractionMap = new HashMap<ClassifierUserInteraction, ClassifierUserInteraction>();

	@VisitBefore
	public void visitSlot(INakedSlot s) {
		INakedProperty property = s.getDefiningFeature();
		if (hasUserInteractions(s.getOwningInstance().getClassifier())) {
			INakedInstanceSpecification tag = (INakedInstanceSpecification) getTag(s, PARTICIPATION, RESULTING_USER_INTERACTION);
			PropertyNavigation origin = findPropertyNavigation(s);
			copyAndLinkUserInteraction(tag, property, origin);
		}
	}

	@VisitBefore
	public void visitEntity(INakedEntity c) {
		for (INakedProperty property : c.getEffectiveAttributes()) {
			visitProperty(c, property);
		}
	}

	@VisitBefore
	public void beforeModel(INakedModel iNakedModel) {

		try {
			FileWriter fstream = new FileWriter("uimodelBefore.xml");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write((uiModel.toXmlString()));
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	@VisitAfter
	public void afterModel(INakedModel iNakedModel) {

		try {
			FileWriter fstream = new FileWriter("uimodelAfter.xml");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write((uiModel.toXmlString()));
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}





	private void copyAndLinkUserInteraction(INakedInstanceSpecification tag, INakedProperty p, PropertyNavigation origin) {
		if (origin != null) {
			ClassifierUserInteraction originalResultingUi = null;
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			if (map.isMany()) {
				if (tag == null) {
					AbstractUserInteractionFolder classifierFolder = findFolderFor(p.getNakedBaseType());
					originalResultingUi = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(p.getNakedBaseType(),
							UserInteractionKind.LIST));
				} else {
					AbstractUserInteractionFolder classifierFolder = findFolderFor(tag.getClassifier());
					originalResultingUi = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(tag));
				}

				originalResultingUi.setTooMany(p.hasStereotype(NAVIGATION_TOO_MANY));
				ClassifierUserInteraction newResultingUi = originalResultingUi.makeCopy();
				Set<PropertyNavigation> toBeRemoved = new HashSet<PropertyNavigation>(newResultingUi.getOwnedPropertyNavigation());
				newResultingUi.removeAllFromOwnedPropertyNavigation(toBeRemoved);

				Set<PropertyField> fieldsToBeRemoved = new HashSet<PropertyField>(newResultingUi.getOwnedPropertyField());
				newResultingUi.removeAllFromOwnedPropertyField(fieldsToBeRemoved);

				Set<OperationNavigation> operationNavigationsToBeRemoved = new HashSet<OperationNavigation>(newResultingUi.getOwnedOperationNavigation());
				newResultingUi.removeAllFromOwnedOperationNavigation(operationNavigationsToBeRemoved);
				
				// link in map for a delayed copy of the then correct state
				this.userInteractionMap.put(newResultingUi, originalResultingUi);

				origin.getClassifierUserInteraction().getFolder().addToEntityUserInteraction(newResultingUi);
				newResultingUi.setOriginatingPropertyNavigation(origin);
				origin.setResultingUserInteraction(newResultingUi);
			}
			if (p.isComposite()) {

				// AbstractUserInteractionFolder originatingFolder =
				// findFolderFor(p.getOwner());

				AbstractUserInteractionFolder classifierFolder = findFolderFor(p.getNakedBaseType());
				ClassifierUserInteraction createUi = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(p.getNakedBaseType(),
						UserInteractionKind.CREATE));
				createUi.setOriginatingPropertyNavigation(origin);

				ClassifierUserInteraction originUi = origin.getClassifierUserInteraction();
				if (originUi.getUserInteractionKind() == UserInteractionKind.EDIT) {
					ClassifierUserInteraction newCreateUi = createUi.makeCopy();
					newCreateUi.setName(newCreateUi.getName() + "_" + originUi.getName());
					classifierFolder.addToEntityUserInteraction(newCreateUi);
					newCreateUi.setOriginatingPropertyNavigation(origin);
				}

				ClassifierUserInteraction editUi = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(p.getNakedBaseType(),
						UserInteractionKind.EDIT));
				editUi.setOriginatingPropertyNavigation(origin);
			}
		}
	}

	private PropertyNavigation findPropertyNavigation(INakedSlot s) {
		AbstractUserInteractionFolder classifierFolder = findFolderFor(s.getOwningInstance().getClassifier());
		ClassifierUserInteraction ui = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(s.getOwningInstance()));
		return (PropertyNavigation) ui.findOwnedElement(getDomainFQNameOf(ui, s.getDefiningFeature()));
	}

	// @VisitBefore
	private void visitProperty(INakedEntity c, INakedProperty p) {
		if (hasUserInteractions(c)) {
			linkUserInteraction(c, p, UserInteractionKind.EDIT);
			
//This results in 3 list userinteractions in same folder			
//			linkUserInteraction(c, p, UserInteractionKind.VIEW);
//			linkUserInteraction(c, p, UserInteractionKind.LIST);
		}
	}

	private void linkUserInteraction(INakedEntity c, INakedProperty p, UserInteractionKind uiKind) {
		INakedInstanceSpecification tag = (INakedInstanceSpecification) getTag(p, participationIn(uiKind), RESULTING_USER_INTERACTION);
		PropertyNavigation propertyNavigation = findPropertyNavigation(c, p, uiKind);
		copyAndLinkUserInteraction(tag, p, propertyNavigation);
	}

	private PropertyNavigation findPropertyNavigation(INakedEntity c, INakedProperty p, UserInteractionKind uiKind) {
		AbstractUserInteractionFolder classifierFolder = findFolderFor((INakedClassifier) c);
		ClassifierUserInteraction originatingUi = (ClassifierUserInteraction) classifierFolder.findOwnedElement(getUserInteractionFQNameOf(c, uiKind));
		UserInteractionElement propertyParticipation = originatingUi.findOwnedElement(getDomainFQNameOf(originatingUi, c, p));
		if (propertyParticipation instanceof PropertyNavigation) {
			return (PropertyNavigation) propertyParticipation;
		} else {
			return null;
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel model) {
		Set<Entry<ClassifierUserInteraction, ClassifierUserInteraction>> entrySet = userInteractionMap.entrySet();
		for (Entry<ClassifierUserInteraction, ClassifierUserInteraction> entry : entrySet) {
			doCopy(entry.getValue(), entry.getKey());
		}
		new UserInteractionCleanup().startVisiting(this.uiModel);
	}

	void doCopy(ClassifierUserInteraction origin, ClassifierUserInteraction copy) {
		// save the originatingPropertyInteraction in a temp variable to avoid
		// it being overwritten
		PropertyNavigation opn = copy.getOriginatingPropertyNavigation();
		origin.copyState(origin, copy);
		copy.setName(opn.getName() + "List");
		// @TODO check if necessary
		copy.setOriginatingPropertyNavigation(opn);
	}
}
