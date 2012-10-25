package org.opaeum.eclipse.emulated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolder extends AdapterImpl implements IEmulatedPropertyHolder{
	private EList<AbstractEmulatedProperty> emulatedAttributes = new BasicEList<AbstractEmulatedProperty>();
	protected Classifier owner;
	protected IPropertyEmulation propertyEmulation;
	protected Map<Element,OCLExpression> queries = new HashMap<Element, OCLExpression>();
	public EmulatedPropertyHolder(Classifier owner,IPropertyEmulation e,EList<? extends TypedElement>...typedElements){
		this.owner = owner;
		this.propertyEmulation = e;
		owner.eAdapters().add(this);
		for(EObject sa:owner.getStereotypeApplications()){
			sa.eAdapters().add(this);
		}
		for(EList<? extends TypedElement> eList:typedElements){
			for(TypedElement typedElement:eList){
				addTypedElementBridge(typedElement);
			}
		}
		for(Operation operation:owner.getOperations()){
			addOperationMessageProperty(operation);
		}

	}
	@Override
	public void putQuery(Element e, OCLExpression exp){
		queries.put(e, exp);
	}
	private void addOperationMessageProperty(Operation operation){
		OperationMessageType msg = (OperationMessageType) propertyEmulation.getMessageStructure(operation);
		InverseArtificialProperty iap = new InverseArtificialProperty((Classifier)operation.getOwner(), msg);
		NonInverseArtificialProperty oe = iap.initialiseOtherEnd();
		msg.addNonInverseArtificialProperty(oe);
		propertyEmulation.getEmulatedPropertyHolder(oe.getOwner()).addEmulatedAttribute(oe);
		getEmulatedAttributes().add(iap);
	}
	@Override
	public void notifyChanged(Notification msg){
		if(msg.getEventType() == Notification.ADD){
			Object newValue = msg.getNewValue();
			if(newValue instanceof Element && ((Element) newValue).getOwner() == owner){
				if(newValue instanceof TypedElement && !(newValue instanceof Property)){
					addTypedElementBridge((TypedElement) newValue);
				}else if(newValue instanceof Operation){
					addOperationMessageProperty((Operation) newValue);
				}
			}
		}else if(msg.getEventType() == Notification.REMOVE){
			if((msg.getOldValue() instanceof Operation || msg.getOldValue() instanceof TypedElement) && !(msg.getOldValue() instanceof Property)){
				removeEmulatedAttribute((NamedElement) msg.getOldValue());
			}
		}
	}
	protected void addTypedElementBridge(TypedElement te){
		emulatedAttributes.add(new TypedElementPropertyBridge(owner, te,this.propertyEmulation));
	}
	protected void removeEmulatedAttribute(Element original){
		Iterator<AbstractEmulatedProperty> iterator = emulatedAttributes.iterator();
		while(iterator.hasNext()){
			if(iterator.next().getOriginalElement() == original){
				iterator.remove();
				break;
			}
		}
	}
	@Override
	public Property getEmulatedAttribute(Element originalElement){
		for(Property p:emulatedAttributes){
			if(((IEmulatedElement) p).getOriginalElement() == originalElement){
				return p;
			}
		}
		return null;
	}
	@Override
	public EList<AbstractEmulatedProperty> getEmulatedAttributes(){
		return emulatedAttributes;
	}
	@Override
	public void addEmulatedAttribute(AbstractEmulatedProperty otherEnd){
		getEmulatedAttributes().add(otherEnd);
	}

}
