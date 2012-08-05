package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForBehavioredClassifier extends EmulatedPropertyHolder{
	protected EmulatedPropertyHolderForBehavioredClassifier(BehavioredClassifier owner,IPropertyEmulation e,
			EList<? extends TypedElement>...typedElements){
		super(owner, e, typedElements);
	}
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForBehavioredClassifier(BehavioredClassifier owner,IPropertyEmulation e){
		super(owner, e);
		EList<Behavior> ownedBehaviors = owner.getOwnedBehaviors();
		for(Behavior behavior:ownedBehaviors){
			addEmulatedBehaviorProperty(behavior);
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(msg.getEventType() == Notification.ADD){
			if(msg.getNewValue() instanceof Behavior
					&& msg.getFeatureID(BehavioredClassifier.class) == UMLPackage.BEHAVIORED_CLASSIFIER__OWNED_BEHAVIOR){
				Behavior newValue = (Behavior) msg.getNewValue();
				addEmulatedBehaviorProperty(newValue);
			}
		}else if(msg.getEventType() == Notification.REMOVE){
			if(msg.getOldValue() instanceof Behavior
					&& msg.getFeatureID(BehavioredClassifier.class) == UMLPackage.BEHAVIORED_CLASSIFIER__OWNED_BEHAVIOR){
				removeEmulatedAttribute((Element) msg.getOldValue());
			}
		}
	}
	private void addEmulatedBehaviorProperty(Behavior newValue){
		InverseArtificialProperty iap = new InverseArtificialProperty((BehavioredClassifier) owner, newValue);
		iap.initialiseOtherEnd();
		Classifier otherType = iap.getOtherEnd().getOwner();
		if(!(otherType instanceof MessageType)){
			propertyEmulation.getEmulatedPropertyHolder(otherType).addEmulatedAttribute(iap.getOtherEnd());
		}
		getEmulatedAttributes().add(iap);
	}
}
