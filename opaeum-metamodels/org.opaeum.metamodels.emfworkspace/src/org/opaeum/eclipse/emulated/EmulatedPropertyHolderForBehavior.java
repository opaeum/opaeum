package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.StereotypeAttachable;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForBehavior extends EmulatedPropertyHolderForBehavioredClassifier implements StereotypeAttachable{
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForBehavior(Behavior owner,IPropertyEmulation e){
		super(owner, e, owner.getOwnedParameters(),EmfTimeUtil.buildObservationPropertiess(owner,e,owner));
	}
	protected EmulatedPropertyHolderForBehavior(Behavior owner,IPropertyEmulation e,EList<? extends TypedElement>...typedElements){
		super(owner, e, typedElements);
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(msg.getNotifier() instanceof DynamicEObjectImpl){
			if(msg.getEventType() == Notification.ADD && msg.getNewValue() instanceof Observation){
				Observation obs = (Observation) msg.getNewValue();
				if(obs instanceof TimeObservation && propertyEmulation.getDateTimeType()!=null){
					getEmulatedAttributes().add(new ObservationPropertyBridge(owner, obs, propertyEmulation.getDateTimeType()));
				}else if(obs instanceof TimeObservation && propertyEmulation.getDurationType()!=null){
					getEmulatedAttributes().add(new ObservationPropertyBridge(owner, obs, propertyEmulation.getDurationType()));
				}
			}else if(msg.getEventType() == Notification.REMOVE && msg.getOldValue() instanceof Observation){
				removeEmulatedAttribute((Observation) msg.getOldValue());
			}
		}
	}
}
