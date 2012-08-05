package org.opaeum.eclipse.emulated;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.emf.workspace.StereotypeAttachable;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class StructuredActivityNodeMessageType extends AbstractEmulatedMessageType implements StereotypeAttachable{
	private StructuredActivityNode node;
	@SuppressWarnings("unchecked")
	public StructuredActivityNodeMessageType(StructuredActivityNode node,IPropertyEmulation e){
		super(node, e, node.getVariables());
		this.node = node;
		for(ObservationPropertyBridge b:EmfTimeUtil.buildObservationPropertiess(this, e, node)){
			getOwnedAttributes().add(b);
		}
	}
	
	protected StructuredActivityNodeMessageType(NamedElement node,IPropertyEmulation e,Collection<? extends TypedElement>...typedElements){
		super(node, e, typedElements);
	}
	protected boolean couldBeEmulated(Object o){
		return o instanceof Variable;
	}

	protected void addEmulatedProperty(ActivityNode n){
		AbstractEmulatedMessageType msg = null;
		if(n instanceof StructuredActivityNode){
			msg = (AbstractEmulatedMessageType) propertyEmulation.getMessageStructure((Namespace) n);
		}else if(n instanceof OpaqueAction){
			msg = (AbstractEmulatedMessageType) propertyEmulation.getMessageStructure((OpaqueAction) n);
		}
		if(msg != null){
			InverseArtificialProperty iap = new InverseArtificialProperty(this, msg);
			msg.addNonInverseArtificialProperty(iap.initialiseOtherEnd());
			getOwnedAttributes().add(iap);
		}else if(n instanceof AcceptCallAction || n instanceof CallAction){
			getOwnedAttributes().add(new ActionFeatureBridge(this, (Action)n, super.propertyEmulation));
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(msg.getNotifier() instanceof DynamicEObjectImpl){
			if(msg.getEventType() == Notification.ADD && msg.getNewValue() instanceof Observation){
				Observation obs = (Observation) msg.getNewValue();
				if(obs instanceof TimeObservation && propertyEmulation.getDateTimeType() != null){
					getOwnedAttributes().add (new ObservationPropertyBridge(this, obs, propertyEmulation.getDateTimeType()));
				}else if(obs instanceof TimeObservation && propertyEmulation.getDurationType() != null){
					getOwnedAttributes().add(new ObservationPropertyBridge(this, obs, propertyEmulation.getDurationType()));
				}
			}else if(msg.getEventType() == Notification.REMOVE && msg.getOldValue() instanceof Observation){
				removeEmulatedProperty((Observation) msg.getOldValue());
			}
		}else if(msg.getEventType() ==Notification.ADD && msg.getNewValue() instanceof Action){
			addEmulatedProperty((ActivityNode) msg.getNewValue());
		}else if(msg.getEventType()==Notification.REMOVE && msg.getOldValue() instanceof Action){
			removeEmulatedProperty((Element) msg.getOldValue());
		}
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(node) + "@MT";
	}
	@Override
	public boolean shouldEmulate(){
		Activity a = EmfActivityUtil.getContainingActivity(node);
		return EmfBehaviorUtil.isProcess(a);
	}
}
