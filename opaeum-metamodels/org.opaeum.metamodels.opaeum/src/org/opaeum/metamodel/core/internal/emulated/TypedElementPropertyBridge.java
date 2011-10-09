package org.opaeum.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedTypedElement;

/**
 * This class is need to emulate attibutes in namespaces where other typed elements should also function appear as attributes to Octopus
 * 
 * @author abarnard
 * 
 */
public class TypedElementPropertyBridge extends AbstractPropertyBridge implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedTypedElement parameter;
	boolean ensureLocallyUniqueName = true;
	public TypedElementPropertyBridge(INakedClassifier owner,INakedTypedElement parameter){
		super(owner, parameter);
		this.parameter = parameter;
	}
	public TypedElementPropertyBridge(INakedClassifier owner,INakedObjectNode pin,boolean ensureLocallyUniqueName,boolean isDerived){
		super(owner, pin);
		this.parameter = pin;
		this.ensureLocallyUniqueName = ensureLocallyUniqueName;
	}
	public boolean shouldEnsureLocallyUniqueName(){
		return this.ensureLocallyUniqueName;
	}
	public INakedClassifier getNakedBaseType(){
		return parameter.getNakedBaseType();
	}
	public boolean isOrdered(){
		return parameter.isOrdered();
	}
	public boolean isUnique(){
		return parameter.isUnique();
	}
	public INakedMultiplicityElement getOriginal(){
		return parameter;
	}
	public IClassifier getType(){
		return parameter.getType();
	}
	@Override
	public String getName(){
		if(parameter instanceof INakedPin && ensureLocallyUniqueName){
			return locallyUniqueName((INakedObjectNode) parameter);
		}
		return super.getName();
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return parameter.getNakedMultiplicity();
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other instanceof TypedElementPropertyBridge){
			TypedElementPropertyBridge o = (TypedElementPropertyBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		}else{
			return false;
		}
	}
	public static String locallyUniqueName(INakedObjectNode pin){
		return pin.getName() + "On" + pin.getOwnerElement().getMappingInfo().getJavaName().getCapped();
	}
}
