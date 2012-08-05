package org.opaeum.eclipse.emulated;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.ocl.uml.impl.MessageTypeImpl;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.internal.impl.BehavioredClassifierImpl;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

@SuppressWarnings("restriction")
public abstract class AbstractEmulatedMessageType extends BehavioredClassifierImpl implements MessageType,Adapter,IEmulatedElement,BehavioredClassifier{
	NamedElement originalElement;
	private Notifier target;
	protected IPropertyEmulation propertyEmulation;
	protected EList<Property> ownedAttributes = new BasicEList<Property>();
	public AbstractEmulatedMessageType(NamedElement node,IPropertyEmulation e,Collection<? extends TypedElement>...typedElements){
		super();
		this.originalElement = node;
		originalElement.eAdapters().add(this);
		this.propertyEmulation = e;
		for(Collection<? extends TypedElement> collection:typedElements){
			for(TypedElement typedElement:collection){
				addTypedElementPropertyBridge(typedElement);
			}
		}
	}
	@Override
	public EList<Property> getOwnedAttributes(){
		return ownedAttributes;
	}
	protected void addTypedElementPropertyBridge(TypedElement typedElement){
		ownedAttributes.add(new TypedElementPropertyBridge(this, typedElement));
	}
	public void addNonInverseArtificialProperty(NonInverseArtificialProperty oe){
		ownedAttributes.add(oe);
	}
	@Override
	public void notifyChanged(Notification msg){
		if(msg.getEventType() == Notification.ADD){
			if(couldBeEmulated(msg.getNewValue()) && ((TypedElement)msg.getNewValue()).getOwner()==originalElement){
				addTypedElementPropertyBridge((TypedElement) msg.getNewValue());
			}
		}else if(msg.getEventType() == Notification.REMOVE){
			if(couldBeEmulated(msg.getOldValue())){
				Element te= (Element) msg.getOldValue();
				removeEmulatedProperty(te);
			}
		}
	}
	protected void removeEmulatedProperty(Element te){
		Iterator<Property> iterator = ownedAttributes.iterator();
		while(iterator.hasNext()){
			AbstractEmulatedProperty property = (AbstractEmulatedProperty) iterator.next();
			if(property.getOriginalElement() == te){
				iterator.remove();
				break;
			}
		}
	}
	protected void addImplementedInterface(Interface taskObject){
	}
	protected void addGeneral(Classifier taskObject){
		createGeneralization(taskObject);
	}
	protected abstract boolean couldBeEmulated(Object o);
	@Override
	public Notifier getTarget(){
		return this.target;
	}
	@Override
	public void setTarget(Notifier newTarget){
		this.target = newTarget;
	}
	@Override
	public boolean isAdapterForType(Object type){
		return false;
	}
	public Element getOriginalElement(){
		return originalElement;
	}
	public Property getEmulatedAttribute(Element o){
		// TODO Auto-generated method stub
		for(Property property:getOwnedAttributes()){
			if(((IEmulatedElement) property).getOriginalElement()==o){
				return property;
			}
		}
		return null;
	}
	@Override
	public EList<Operation> getOwnedOperations(){
		return new BasicEList<Operation>();
	}
	@Override
	public Operation getOwnedOperation(String name,EList<String> ownedParameterNames,EList<Type> ownedParameterTypes){
		return null;
	}
	@Override
	public Operation getOwnedOperation(String name,EList<String> ownedParameterNames,EList<Type> ownedParameterTypes,boolean ignoreCase){
		return null;
	}
	@Override
	public Property getOwnedAttribute(String name,Type type){
		return null;
	}
	@Override
	public Property getOwnedAttribute(String name,Type type,boolean ignoreCase,EClass eClass){
		return null;
	}
	@Override
	public Operation getReferredOperation(){
		return null;
	}
	@Override
	public void setReferredOperation(Operation value){
		
	}
	@Override
	public Classifier getReferredSignal(){
		return null;
	}
	@Override
	public void setReferredSignal(Classifier value){
	}
	@Override
	public boolean checkExclusiveSignature(DiagnosticChain diagnostics,Map<Object,Object> context){
		return false;
	}
	@Override
	public boolean checkOperationParameters(DiagnosticChain diagnostics,Map<Object,Object> context){
		return false;
	}
	@Override
	public boolean checkSignalAttributes(DiagnosticChain diagnostics,Map<Object,Object> context){
		return false;
	}
	@Override
	public EList<Property> oclProperties(){
		return getOwnedAttributes();
	}
	@Override
	public EList<Operation> oclOperations(){
		return new BasicEList<Operation>();
	}

}
