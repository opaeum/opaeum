package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedPowerType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedEnumerationLiteralImpl;
import org.opaeum.metamodel.core.internal.NakedInstanceSpecificationImpl;
import org.opaeum.metamodel.core.internal.NakedPowerTypeInstanceImpl;
import org.opaeum.metamodel.core.internal.NakedSlotImpl;

/**
 * Builds all instance specifications and their slots. Enumeration literals are a specialised form of instance specification and are also
 * built here.
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
	FeatureExtractor.class
},after = {
	FeatureExtractor.class
})
public class InstanceExtractor extends AbstractExtractorFromEmf{
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}

	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof EnumerationLiteral){
			EnumerationLiteral el = (EnumerationLiteral) e;
			INakedClassifier owner = (INakedClassifier) getNakedPeer(el.getEnumeration());
			return owner instanceof INakedPowerType ? new NakedPowerTypeInstanceImpl() : new NakedEnumerationLiteralImpl();
		}else{
			return super.createElementFor(e, peerClass);
		}
	}
	@VisitBefore
	public void visitEnumerationLiteral(EnumerationLiteral el,NakedEnumerationLiteralImpl nel){
		Enumeration enumeration = el.getEnumeration();
		if(enumeration==null){
			System.out.println();
		}
		nel.setClassifier((INakedClassifier) getNakedPeer(enumeration));
		nel.setOrdinal(enumeration.getOwnedLiterals().indexOf(el));
	}
	@VisitBefore
	public void visitInstanceSpecification(InstanceSpecification emfInstanceSpec,NakedInstanceSpecificationImpl nakedInstanceSpec){
		if(emfInstanceSpec.getClassifiers().size() == 1){
			nakedInstanceSpec.setClassifier((INakedClassifier) getNakedPeer(emfInstanceSpec.getClassifiers().get(0)));
		}
	}
	@VisitBefore
	public void visitSlot(Slot emfSlot,NakedSlotImpl nakedSlot){
		InstanceSpecification owningInstance = emfSlot.getOwningInstance();
		if(owningInstance.getClassifiers().size() == 1 || owningInstance instanceof EnumerationLiteral){
			if(!(emfSlot.getDefiningFeature() == null || emfSlot.getDefiningFeature() instanceof ExtensionEnd)){
				INakedProperty definingFeature = (INakedProperty) getNakedPeer(emfSlot.getDefiningFeature());
				nakedSlot.setDefiningFeature(definingFeature);
			}
		}
	}
}
