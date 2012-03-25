package org.nakeduml.tinker.activity;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

/**
 * This class is need to emulate attibutes in namespaces where other typed elements should also function appear as attributes to Octopus
 * 
 * @author abarnard
 * 
 */
public class ActionBridge extends AbstractEmulatedProperty implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedAction action;
	boolean ensureLocallyUniqueName = true;

	public ActionBridge(INakedClassifier owner,INakedAction action){
		super(owner, action);
		this.action = action;
		ensureLocallyUniqueName=true;
	}

	@Override
	public boolean isDerived(){
		return super.isDerived();
	}
	public boolean shouldEnsureLocallyUniqueName(){
		return this.ensureLocallyUniqueName;
	}
	public INakedClassifier getNakedBaseType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.action.getNameSpace(), this.action);
		return jippo;
	}
	public boolean isOrdered(){
		return false;
	}
	public boolean isUnique(){
		return false;
	}
	public INakedMultiplicityElement getOriginal(){
		return null;
	}
	public IClassifier getType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.action.getNameSpace(), this.action);
		return jippo;
	}
	@Override
	public String getName(){
		return locallyUniqueName(this.action);
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return new NakedMultiplicityImpl(1, 1);
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other instanceof ActionBridge){
			ActionBridge o = (ActionBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		}else{
			return false;
		}
	}
	public static String locallyUniqueName(INakedAction action){
		return NameConverter.capitalize(action.getName());
	}
	@Override
	public boolean isMeasure(){
		return false;
	}
	@Override
	public boolean isDimension(){
		return false;
	}
}
