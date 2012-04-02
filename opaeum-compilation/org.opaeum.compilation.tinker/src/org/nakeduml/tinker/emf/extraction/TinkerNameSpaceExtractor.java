package org.nakeduml.tinker.emf.extraction;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Property;
import org.opaeum.emf.extraction.EmfExtractionPhase;
import org.opaeum.emf.extraction.NameSpaceExtractor;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.internal.NakedActivityImpl;
import org.opaeum.metamodel.core.internal.NakedAssociationImpl;

@StepDependency(phase = EmfExtractionPhase.class, replaces = NameSpaceExtractor.class)
public class TinkerNameSpaceExtractor extends NameSpaceExtractor {

	@VisitBefore(matchSubclasses = true)
	public void visitAssociation(Association a,NakedAssociationImpl na){
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
		EList<Property> memberEnds = a.getMemberEnds();
		if(a.getName() == null){
			// HACK!!! to avoid nullpointerexceptiosn in
			// NAkedParsedOclStringResolver
			// Something wrong with the phases
			na.setName(memberEnds.get(0).getName() + "To" + memberEnds.get(1).getName());
		}
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
		if(a instanceof AssociationClass){
			na.setClass(true);
		}
	}
	
	@VisitBefore
	public void visitActivity(Activity a,NakedActivityImpl na){
		ActivityKind kind = ActivityKind.PROCESS;
		na.setActivityKind(kind);
		initializeClassifier(na, a);
	}	
}
