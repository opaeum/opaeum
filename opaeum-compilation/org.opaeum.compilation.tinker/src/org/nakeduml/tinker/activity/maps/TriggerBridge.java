package org.nakeduml.tinker.activity.maps;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class TriggerBridge extends AbstractEmulatedProperty implements INakedProperty {
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedTrigger trigger;
	boolean ensureLocallyUniqueName = true;

	public TriggerBridge(INakedClassifier owner, INakedTrigger trigger) {
		super(owner, trigger);
		this.trigger = trigger;
		ensureLocallyUniqueName = false;
		super.isComposite = true;
	}

	@Override
	public boolean isDerived() {
		return super.isDerived();
	}

	public boolean shouldEnsureLocallyUniqueName() {
		return this.ensureLocallyUniqueName;
	}

	public INakedClassifier getNakedBaseType() {
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.trigger.getNameSpace(), this.trigger);
		return jippo;
	}

	public boolean isOrdered() {
		return false;
	}

	public boolean isUnique() {
		return false;
	}

	public INakedMultiplicityElement getOriginal() {
		return null;
	}

	public IClassifier getType() {
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.trigger.getNameSpace(), this.trigger);
//		StdlibCollectionType stdlibCollectionType = new StdlibCollectionType(CollectionMetaType.BAG, jippo); 
		return jippo;
	}

	@Override
	public String getName() {
		return NameConverter.decapitalize(this.trigger.getName());
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return new NakedMultiplicityImpl(1, 1);
	}

	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd() {
		return Collections.emptySet();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof TriggerBridge) {
			TriggerBridge o = (TriggerBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		} else {
			return false;
		}
	}

	@Override
	public boolean isMeasure() {
		return false;
	}

	@Override
	public boolean isDimension() {
		return false;
	}
}
