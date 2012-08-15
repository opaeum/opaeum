package org.opaeum.metamodel.core.internal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedSlot;
import org.eclipse.uml2.uml.INakedValueSpecification;
public class NakedInstanceSpecificationImpl extends NakedPackageableElementImpl implements INakedInstanceSpecification {

	private static final long serialVersionUID = 4321923923124197567L;
	private INakedClassifier classifier;
	private List<INakedSlot> slots = new ArrayList<INakedSlot>();
	private INakedValueSpecification specification;
	public List<INakedSlot> getSlots() {
		return this.slots;
	}
	public INakedValueSpecification getFirstValueFor(String attribute) {
		INakedSlot slot = getSlotForFeature(attribute);
		if (slot == null) {
			return null;
		} else {
			return slot.getFirstValue();
		}
	}
	public INakedSlot getSlotForFeature(String attribute) {
		for(INakedSlot slot:slots){
			if (slot.getDefiningFeature() != null && slot.getDefiningFeature().getName().equals(attribute)) {
				return slot;
			}
		}
		return null;
	}
	@Override
	public String getMetaClass() {
		return "instanceSpecification";
	}
	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
		if (element instanceof INakedSlot) {
			this.slots.add((INakedSlot) element);
		}
	}

	public boolean hasValueForFeature(String attribute) {
		return getFirstValueFor(attribute) != null;
	}
	public INakedClassifier getClassifier() {
		return this.classifier;
	}
	public void setClassifier(INakedClassifier classifier) {
		this.classifier = classifier;
	}
	public INakedValueSpecification getSpecification() {
		return specification;
	}
	protected void setSpecification(INakedValueSpecification specification) {
		this.specification = specification;
	}
}
