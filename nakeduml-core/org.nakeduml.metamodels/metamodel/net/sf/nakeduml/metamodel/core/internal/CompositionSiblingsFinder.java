package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedProperty;

/**
 * 
 * This is a helper class that, for any two entities 'owner' and 'type': 1. finds ancestor along the composition dimension that is common to
 * both, 2. finds sequence of properties from ancestor this ancestor to the type that will allow such navigation down the composition
 * dimension
 * 
 */
public class CompositionSiblingsFinder{
	public static boolean isCompositionAncestorOf(ICompositionParticipant self,ICompositionParticipant other){
		boolean hasEncounteredMany = false;
		return isCompositionAncestorOf(self, other, other, hasEncounteredMany,10);
	}
	private static boolean isCompositionAncestorOf(ICompositionParticipant self,ICompositionParticipant other,ICompositionParticipant startingPoint, boolean hasEncounteredMany, int count){
		if(other.hasComposite() && count>0){
			INakedProperty othersEndToComposite = other.getEndToComposite();
			ICompositionParticipant othersComposite = (ICompositionParticipant) othersEndToComposite.getNakedBaseType();
			if(othersComposite.equals(startingPoint) || othersComposite.equals(other)){
				//TODO
				//circularity - modelling error
				return false;
			}
			// Test for multiplicity of more than one TODO verify this logic,
			// its a hack to ensure sourcePopulation comes back with more than
			// one
			hasEncounteredMany = hasEncounteredMany
					|| (othersEndToComposite.getOtherEnd().getNakedMultiplicity().isMany() || othersEndToComposite.getOtherEnd().hasQualifiers());
			boolean foundRootClass = !othersComposite.hasComposite();
			if(self.equals(othersComposite) && (hasEncounteredMany || foundRootClass)){
				//Try best to find a many, but still end with the root object
				return true;
			}else if(isCompositionAncestorOf(self, othersComposite, startingPoint, hasEncounteredMany , --count)){
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
}
