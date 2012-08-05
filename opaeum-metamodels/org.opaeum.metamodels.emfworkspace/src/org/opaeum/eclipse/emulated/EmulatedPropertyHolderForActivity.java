package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForActivity extends EmulatedPropertyHolderForBehavior{
	Activity owner;
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForActivity(Activity owner,IPropertyEmulation e){
		super(owner, e, owner.getOwnedParameters(), owner.getVariables(), EmfTimeUtil.buildObservationPropertiess(owner, e, owner));
		this.owner=owner;
		for(ActivityNode n:owner.getOwnedNodes()){
			addEmulatedProperty(n);
		}
	}
	protected void addEmulatedProperty(ActivityNode n){
		AbstractEmulatedMessageType msg = null;
		if(n instanceof StructuredActivityNode){
			msg = (AbstractEmulatedMessageType) propertyEmulation.getMessageStructure((Namespace) n);
		}else if(n instanceof OpaqueAction){
			msg = (AbstractEmulatedMessageType) propertyEmulation.getMessageStructure((OpaqueAction) n);
		}
		if(msg != null){
			InverseArtificialProperty iap = new InverseArtificialProperty(this.owner, msg);
			msg.addNonInverseArtificialProperty(iap.initialiseOtherEnd());
			getEmulatedAttributes().add(iap);
		}else if(n instanceof AcceptCallAction || n instanceof CallAction){
			getEmulatedAttributes().add(new ActionFeatureBridge(this.owner, (Action)n, super.propertyEmulation));
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(msg.getEventType() == Notification.ADD && msg.getNewValue() instanceof Action){
			addEmulatedAttribute((NonInverseArtificialProperty) msg.getNewValue());
		}else if(msg.getEventType() == Notification.REMOVE && msg.getOldValue() instanceof Action){
			removeEmulatedAttribute((Element) msg.getOldValue());
		}
	}
}
