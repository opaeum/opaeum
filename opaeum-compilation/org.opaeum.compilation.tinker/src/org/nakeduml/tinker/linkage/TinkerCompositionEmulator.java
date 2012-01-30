package org.nakeduml.tinker.linkage;

import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.linkage.MappedTypeLinker;
import org.opaeum.linkage.ParameterLinker;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.linkage.TypeResolver;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;

@StepDependency(phase = LinkagePhase.class, after = { ProcessIdentifier.class, MappedTypeLinker.class, ParameterLinker.class }, before = { TypeResolver.class }, requires = {
		ProcessIdentifier.class, MappedTypeLinker.class, ParameterLinker.class }, replaces = CompositionEmulator.class)
public class TinkerCompositionEmulator extends CompositionEmulator {

	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp){
		//TODO maybe find a better place for this
		cp.reorderSequences();
		if(cp instanceof INakedAssociation){
			// do nothing
		}else{
			cp.removeObsoleteArtificialProperties();
			INakedProperty endToComposite = cp.getEndToComposite();
			if(endToComposite == null && !cp.getIsAbstract()){
				// In case of composite structures, the composition may not have
				// been modeled as an association but as a part
				INakedProperty endFromComposite = null;
				if(cp.getNestingClassifier() != null){
					for(INakedProperty p:(List<? extends INakedProperty>) cp.getNestingClassifier().getEffectiveAttributes()){
						if(p.isComposite() && p.getNakedBaseType() == cp){
							endFromComposite = p;
						}
					}
				}
				if(cp instanceof INakedBehavior){
					INakedBehavior b = (INakedBehavior) cp;
					if(b.getContext() != null /*&& BehaviorUtil.hasExecutionInstance(b)*/){
						if(endFromComposite != null){
							NonInverseArtificialProperty inverseArtificialProperty = new NonInverseArtificialProperty(endFromComposite, "contextObject");
							addAffectedElement(inverseArtificialProperty);
							cp.setEndToComposite(inverseArtificialProperty);
						}else{
							InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(b.getContext(), (INakedBehavior) cp);
							b.getContext().addOwnedElement(inverseArtificialProperty);
							b.addOwnedElement(inverseArtificialProperty.getOtherEnd());
							b.setEndToComposite(inverseArtificialProperty.getOtherEnd());
							addAffectedElement(inverseArtificialProperty);
							addAffectedElement(inverseArtificialProperty.getOtherEnd());
						}
					}
				}else if(cp.getNestingClassifier() != null){
					if(endFromComposite != null){
						NonInverseArtificialProperty inverseArtificialProperty = new NonInverseArtificialProperty(endFromComposite, "ownerObject");
						addAffectedElement(inverseArtificialProperty);
						cp.setEndToComposite(inverseArtificialProperty);
					}else{
						InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(cp.getNestingClassifier(), cp);
						cp.getNestingClassifier().addOwnedElement(inverseArtificialProperty);
						cp.setEndToComposite(inverseArtificialProperty.getOtherEnd());
						addAffectedElement(inverseArtificialProperty);
						addAffectedElement(inverseArtificialProperty.getOtherEnd());
					}
				}
				if(cp.getEndToComposite() != null){
					addAffectedElement(cp);
					if(cp.getEndToComposite().getNakedBaseType() != null){
						// Is null when being deleted
						addAffectedElement(cp.getEndToComposite().getNakedBaseType());
					}
				}
			}else if(cp.getEndToComposite() != null){
				cp.removeObsoleteArtificialProperties();
				if(cp.getEndToComposite().getNakedBaseType() != null){
					// Is null when being deleted
					cp.getEndToComposite().getNakedBaseType().removeObsoleteArtificialProperties();
				}
			}
		}
	}

}
