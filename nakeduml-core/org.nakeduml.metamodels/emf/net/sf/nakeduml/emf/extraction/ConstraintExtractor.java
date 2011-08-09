package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;

@StepDependency(phase = EmfExtractionPhase.class,requires = InstanceExtractor.class,after = InstanceExtractor.class)
public class ConstraintExtractor extends AbstractExtractorFromEmf{
	@VisitAfter(matchSubclasses = true)
	public void initializeClassifier(Classifier emfClassifier){
		INakedClassifier classifier = (INakedClassifier) getNakedPeer(emfClassifier);
		if(classifier != null){
			// Some classifiers are not supported in NakedUML
			EList<Constraint> ownedRules = emfClassifier.getOwnedRules();
			for(Constraint c:ownedRules){
				NakedConstraintImpl nc = new NakedConstraintImpl();
				initialize(nc, c, emfClassifier);
				nc.setSpecification(getValueSpecification(nc, c.getSpecification(), OclUsageType.INV));
				if(c.getConstrainedElements().size() == 1){
					Element constrained = c.getConstrainedElements().get(0);
					nc.setConstrainedElement(getNakedPeer(constrained));
				}
			}
		}
	}
}
