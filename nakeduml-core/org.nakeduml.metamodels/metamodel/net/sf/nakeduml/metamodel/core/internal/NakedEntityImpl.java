package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedBehavioredClassifierImpl;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.model.IModelElement;

public class NakedEntityImpl extends NakedBehavioredClassifierImpl implements INakedEntity {
	private static final long serialVersionUID = -257231836042506513L;
	public static final String META_CLASS = "entity";
	private boolean representsUser;
	private List<INakedProperty> uniquenessConstraints;
	private INakedProperty endToComposite;

	public NakedEntityImpl() {
	}

	public boolean isPersistent() {
		return true;
	}

	public boolean hasComposite() {
		return getEndToComposite() != null;
	}

	public INakedProperty getEndToComposite() {
		if (this.endToComposite == null) {
			for (INakedProperty np : getEffectiveAttributes()) {
				if (np.getOtherEnd() != null && np.getOtherEnd().isComposite()) {
					// Give preference to non-derived or local endsToComposite
					if (this.endToComposite == null || (endToComposite.isDerived() && !np.isDerived()) || np.getOwner() == this) {
						if (this.endToComposite == null || this.endToComposite.getOwner() != this) {
							//Always find the local attribute first
							this.endToComposite = np;
						}
						this.endToComposite = np;
					}
				}
			}
		}
		return this.endToComposite;
	}

	/**
	 * Includes all appropriately qualified relationships and one-to-one
	 * relationships
	 * 
	 * @param entity
	 * @return
	 */
	public List<INakedProperty> getUniquenessConstraints() {
		if (this.uniquenessConstraints == null) {
			List<INakedProperty> list = new ArrayList<INakedProperty>();
			List attributes = getOwnedAttributes();
			for (int i = 0; i < attributes.size(); i++) {
				IModelElement a = (IModelElement) attributes.get(i);
				if (a instanceof INakedProperty) {
					INakedProperty attribute = (INakedProperty) a;
					// REMEMBER that appropriately qualified relationships would
					// have multiplicity of 0..1 or 1..1
					boolean bothEndsSingleObjects = attribute.getNakedMultiplicity().isSingleObject() && attribute.getOtherEnd() != null
							&& attribute.getOtherEnd().getNakedMultiplicity().isSingleObject();
					if (bothEndsSingleObjects && (!attribute.isInverse() || attribute.getOtherEnd().getQualifierNames().length > 0)
							&& !attribute.isDerived()) {
						list.add(attribute);
					}
				}
			}
			this.uniquenessConstraints = list;
		}
		return this.uniquenessConstraints;
	}

	@Override
	public void addStereotype(INakedInstanceSpecification stereotype) {
		if (StereotypeNames.ENTITY.equals(stereotype.getClassifier().getName())) {
			if (stereotype.hasValueForFeature(TagNames.REPRESENTS_USER)) {
				this.representsUser = stereotype.getFirstValueFor(TagNames.REPRESENTS_USER).booleanValue();
			}
		}
		super.addStereotype(stereotype);
	}



	@Override
	public String getMetaClass() {
		return META_CLASS;
	}

	public boolean representsUser() {
		if (this.representsUser) {
			return true;
		} else if (hasSupertype()) {
			return ((INakedEntity) getSupertype()).representsUser();
		} else {
			return false;
		}
	}


	public void setRepresentsUser(boolean representsUser) {
		this.representsUser = representsUser;
	}
}