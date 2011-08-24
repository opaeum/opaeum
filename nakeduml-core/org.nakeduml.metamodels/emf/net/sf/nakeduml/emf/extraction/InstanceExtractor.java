package net.sf.nakeduml.emf.extraction;

import java.util.Iterator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEnumerationLiteralImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedInstanceSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPowerTypeInstanceImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedSlotImpl;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Builds all instance specifications and their slots. Enumeration literals are a specialised form of instance specification and are also
 * built here.
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
	ImportExtractor.class
},after = {
	ImportExtractor.class
})
public class InstanceExtractor extends AbstractExtractorFromEmf{
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
	public void visitEnumerationLiteral(EnumerationLiteral el, NakedEnumerationLiteralImpl nel){
		nel.setClassifier((INakedClassifier) getNakedPeer(el.getEnumeration()));
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
		if(owningInstance.getClassifiers().size() == 1){
			if(!(emfSlot.getDefiningFeature() == null || emfSlot.getDefiningFeature() instanceof ExtensionEnd)){
				INakedProperty definingFeature = (INakedProperty) getNakedPeer(emfSlot.getDefiningFeature());
				nakedSlot.setDefiningFeature(definingFeature);
			}
		}
	}
}
