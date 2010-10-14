package net.sf.nakeduml.emf.extraction;

import java.util.Iterator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedEnumerationLiteralImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedInstanceSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPowerTypeInstanceImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedSlotImpl;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Builds all instance specifications and their slots. Enumeration literals are
 * a specialised form of instance specification and are also built here.
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = EmfExtractionPhase.class, requires = { ImportExtractor.class }, after = { ImportExtractor.class })
public class InstanceExtractor extends AbstractExtractorFromEmf {
	@VisitBefore
	public void visitEnumerationLiteral(EnumerationLiteral el) {
		INakedClassifier owner = (INakedClassifier) getNakedPeer(el.getEnumeration());
		INakedEnumerationLiteral elw =owner instanceof INakedPowerType? new NakedPowerTypeInstanceImpl(): new NakedEnumerationLiteralImpl();
		initialize(elw, el, el.getEnumeration());
		elw.setClassifier(owner);
	}

	@VisitBefore
	public void visitInstanceSpecification(InstanceSpecification emfInstanceSpec) {
		// Might have been created as part of another valueSpecification. Unlikely,
		// but possible that this could
		// have happened outside of a containing InstanceSpecification.
		NakedInstanceSpecificationImpl nakedInstanceSpec = (NakedInstanceSpecificationImpl) getNakedPeer(emfInstanceSpec);
		if (nakedInstanceSpec == null) {
			nakedInstanceSpec = new NakedInstanceSpecificationImpl();
			super.initialize(nakedInstanceSpec, emfInstanceSpec, emfInstanceSpec.getOwner());
		}
		if (emfInstanceSpec.getClassifiers().size() == 1) {
			nakedInstanceSpec.setClassifier((INakedClassifier) getNakedPeer(emfInstanceSpec.getClassifiers().get(0)));
		}
	}

	@VisitBefore
	public void visitSlot(Slot emfSlot, NakedSlotImpl nakedSlot) {
		InstanceSpecification owningInstance = emfSlot.getOwningInstance();
		if (owningInstance.getClassifiers().size() == 1) {
			INakedClassifier context = (INakedClassifier) getNakedPeer(owningInstance.getClassifiers().get(0));
			if (!(emfSlot.getDefiningFeature() == null || emfSlot.getDefiningFeature() instanceof ExtensionEnd)) {
				INakedProperty definingFeature = (INakedProperty) getNakedPeer(emfSlot.getDefiningFeature());
				nakedSlot.setDefiningFeature(definingFeature);
				Iterator iter = emfSlot.getValues().iterator();
				while (iter.hasNext()) {
					ValueSpecification emfValueSpec = (ValueSpecification) iter.next();
					getValueSpecification(context, emfValueSpec, OclUsageType.INIT);
				}
			}
		}
	}
}
