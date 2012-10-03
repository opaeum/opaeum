package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.StereotypeAttachable;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForBehavior extends EmulatedPropertyHolderForBehavioredClassifier implements StereotypeAttachable{
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForBehavior(Behavior owner,IPropertyEmulation e){
		super(owner, e, owner.getOwnedParameters());
		getEmulatedAttributes().addAll(EmfTimeUtil.buildObservationPropertiess(owner, e, owner));
	}
	protected EmulatedPropertyHolderForBehavior(Behavior owner,IPropertyEmulation e,EList<? extends TypedElement>...typedElements){
		super(owner, e, typedElements);
		getEmulatedAttributes().addAll(EmfTimeUtil.buildObservationPropertiess(owner, e, owner));
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(msg.getNotifier() instanceof DynamicEObjectImpl){
			if(msg.getEventType() == Notification.ADD && msg.getNewValue() instanceof Observation){
				Observation obs = (Observation) msg.getNewValue();
				getEmulatedAttributes().add(new ObservationPropertyBridge(owner, obs, propertyEmulation));
			}else if(msg.getEventType() == Notification.REMOVE && msg.getOldValue() instanceof Observation){
				removeEmulatedAttribute((Observation) msg.getOldValue());
			}
		}
	}
}
