package org.nakeduml.tinker.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.FeatureExtractor;
import net.sf.nakeduml.emf.extraction.GeneralizationExtractor;
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.metamodel.bpm.internal.NakedResponsibilityImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedPortImpl;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.validation.EmfValidationRule;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

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
