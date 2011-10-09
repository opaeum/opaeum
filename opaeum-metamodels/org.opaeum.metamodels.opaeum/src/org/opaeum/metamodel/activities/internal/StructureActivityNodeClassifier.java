package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IAttribute;

import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public class StructureActivityNodeClassifier extends EmulatedCompositionMessageStructure{
	private static final long serialVersionUID = 1712898994833631446L;
	private INakedStructuredActivityNode node;
	private Set<TypedElementPropertyBridge> emulatedAttributes;
	public StructureActivityNodeClassifier(INakedStructuredActivityNode element){
		super(getOwner(element), element);
		this.node = element;
	}
	@Override
	public boolean isPersistent(){
		return true;
	}
	public Collection<TypedElementPropertyBridge> getEmulatedAttributes(){
		if(this.emulatedAttributes == null){
			this.emulatedAttributes = new HashSet<TypedElementPropertyBridge>();
			for(INakedActivityVariable v:node.getVariables()){
				emulatedAttributes.add(new TypedElementPropertyBridge(this, v));
			}
		}
		return emulatedAttributes;
	}
	@Override
	public IAttribute findAttribute(String attName){
		IAttribute findAttribute = super.findAttribute(attName);
		if(findAttribute == null){
			for(TypedElementPropertyBridge te:this.getEmulatedAttributes()){
				if(te.getName().equals(attName)){
					return te;
				}
			}
		}
		return findAttribute;
	}
	@Override
	public List<INakedProperty> getOwnedAttributes(){
		if(attributes == null){
			attributes = new ArrayList<INakedProperty>();
			attributes.add(getEndToComposite());
		}
		return attributes;
	}
	public INakedClassifier getNearestStructuredParent(){
		return getOwner(node);
	}
	private static INakedClassifier getOwner(INakedElement originalElement2){
		INakedClassifier result = null;
		if(originalElement2.getOwnerElement() instanceof INakedActivity){
			result = (INakedClassifier) originalElement2.getOwnerElement();
		}else if(originalElement2.getOwnerElement() instanceof INakedStructuredActivityNode){
			INakedStructuredActivityNode owner = (INakedStructuredActivityNode) originalElement2.getOwnerElement();
			result = ((StructureActivityNodeClassifier) owner.getMessageStructure()).getNearestStructuredParent();
		}
		return result;
	}
}
