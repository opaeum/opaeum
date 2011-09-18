package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;

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
	public TypedElementPropertyBridge(INakedClassifier owner,INakedObjectNode pin,boolean ensureLocallyUniqueName){
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
