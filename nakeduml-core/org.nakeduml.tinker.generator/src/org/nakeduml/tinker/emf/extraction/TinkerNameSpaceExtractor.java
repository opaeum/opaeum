package org.nakeduml.tinker.emf.extraction;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.NameSpaceExtractor;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationImpl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Property;

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
}
