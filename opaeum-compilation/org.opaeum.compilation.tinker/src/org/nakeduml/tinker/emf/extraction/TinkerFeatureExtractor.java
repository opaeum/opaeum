package org.nakeduml.tinker.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.emf.extraction.EmfExtractionPhase;
import org.opaeum.emf.extraction.EmfValidationRule;
import org.opaeum.emf.extraction.FeatureExtractor;
import org.opaeum.emf.extraction.GeneralizationExtractor;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.metamodel.bpm.internal.NakedResponsibilityImpl;
import org.opaeum.metamodel.components.internal.NakedPortImpl;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = EmfExtractionPhase.class,requires = GeneralizationExtractor.class,after = GeneralizationExtractor.class, replaces = FeatureExtractor.class)
public class TinkerFeatureExtractor extends FeatureExtractor {

	protected void populateProperty(NakedPropertyImpl np,Property p){
		super.populateProperty(np, p);
		
		if (p.getOwner() instanceof Property) {
			if (((Property)p.getOwner()).getQualifiers().contains(p)) {
				np.setIsQualifier(true);
				INakedProperty owner = (INakedProperty) getNakedPeer(p.getOwner());
				List<INakedProperty> qualifiers = new ArrayList<INakedProperty>(owner.getQualifiers());
				qualifiers.add(np);
				owner.setQualifiers(qualifiers);
				np.setOwnerElement(owner);
				owner.addOwnedElement(np);
			}
		}
	}

	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof Port){
			return new NakedPortImpl();
		}else if(e instanceof Property){
			Property p = (Property) e;
			if(p.getAssociation() != null){
				for(Property property:p.getAssociation().getMemberEnds()){
					if(property.getType() == null){
						getErrorMap().putError(getId(e), EmfValidationRule.BROKEN_ASSOCIATION);
						// broken association a'la topcased
						return null;
					}
				}
			}
			if(p.getAssociation() instanceof Extension){
				return null;
			}else{
				return new NakedPropertyImpl();
			}
		}else if(e instanceof Operation){
			Stereotype responsibility = StereotypesHelper.getStereotype(e, StereotypeNames.RESPONSIBILITY);
			if(responsibility != null){
				return new NakedResponsibilityImpl();
			}else{
				return new NakedOperationImpl();
			}
		}else{
			return super.createElementFor(e, peerClass);
		}
	}

}
