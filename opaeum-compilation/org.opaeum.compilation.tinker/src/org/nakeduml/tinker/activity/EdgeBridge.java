package org.nakeduml.tinker.activity;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;

public class EdgeBridge extends AbstractEmulatedProperty implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedActivityEdge edge;
	boolean ensureLocallyUniqueName = true;

	public EdgeBridge(INakedClassifier owner,INakedActivityEdge edge){
		super(owner, edge);
		this.edge = edge;
		ensureLocallyUniqueName=false;
	}

	@Override
	public boolean isDerived(){
		return super.isDerived();
	}
	public boolean shouldEnsureLocallyUniqueName(){
		return this.ensureLocallyUniqueName;
	}
	public INakedClassifier getNakedBaseType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.edge.getNameSpace(), this.edge);
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
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.edge.getNameSpace(), this.edge);
		return jippo;
	}
	@Override
	public String getName(){
		return super.getName();
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
		if(other instanceof EdgeBridge){
			EdgeBridge o = (EdgeBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		}else{
			return false;
		}
	}
	public static String locallyUniqueName(INakedObjectNode pin){
		return pin.getName() + "On" + pin.getOwnerElement().getMappingInfo().getJavaName().getCapped();
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
