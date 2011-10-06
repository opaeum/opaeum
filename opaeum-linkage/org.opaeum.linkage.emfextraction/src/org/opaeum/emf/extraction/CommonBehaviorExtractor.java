package org.opaeum.emf.extraction;

import java.util.List;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedTypedElement;

public abstract class CommonBehaviorExtractor extends AbstractExtractorFromEmf{
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	protected List<INakedTypedElement> getEnvironment(Element node){
		Behavior activity = getActivity(node);
		// TODO add variables from containing StructuredNodes
		return getEnvironment(activity);
	}
	protected Activity getActivity(Element node){
		// MUST return activity
		Element e = node;
		while(!(e instanceof Activity) && e != null){
			e = e.getOwner();
		}
		return (Activity) e;
	}
	protected INakedClassifier getNearestContext(Behavior b){
		if(b != null && b.getContext() != null){
			return (INakedClassifier) getNakedPeer(b.getContext());
		}else{
			return null;
		}
	}
	protected INakedBehavior getOwnedBehavior(INakedElementOwner state,Behavior b){
		INakedBehavior nakedPeer = (INakedBehavior) getNakedPeer(b);
		if(nakedPeer != null){
			nakedPeer.setOwnerElement(state);
		}
		return nakedPeer;
	}
}
