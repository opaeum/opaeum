package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.octopus.model.VisibilityKind;

/**
 * This class is need to emulate attibutes in namespaces where other typed
 * elements should also function appear as attributes to Octopus
 * 
 * @author abarnard
 * 
 */
public class TypedElementPropertyBridge extends EmulatingElement implements INakedProperty {
	INakedClassifier owner;
	INakedTypedElement parameter;
	 boolean ensureLocallyUniqueName=true;
	public TypedElementPropertyBridge(INakedClassifier owner, INakedTypedElement parameter) {
		super(parameter);
		this.owner = owner;
		this.parameter = parameter;
	}
	public TypedElementPropertyBridge(INakedClassifier owner, INakedObjectNode pin, boolean ensureLocallyUniqueName) {
		super(pin);
		this.owner = owner;
		this.parameter =pin;
		this.ensureLocallyUniqueName=ensureLocallyUniqueName;
	}
	
	@Override
	public String getName() {
		if(parameter.getOwnerElement() instanceof INakedAction && ensureLocallyUniqueName){
			return parameter.getName() + "On" + parameter.getOwnerElement().getMappingInfo().getJavaName().getCapped();
		}
		return super.getName();
	}

	public IMultiplicityKind getMultiplicity() {
		return getNakedMultiplicity();
	}
	public IClassifier getBaseType() {
		return getNakedBaseType();
	}
	public INakedClassifier getNakedBaseType() {
		return parameter.getNakedBaseType();
	}

	public boolean isInverse() {
		return false;
	}

	public boolean isOrdered() {
		return parameter.isOrdered();
	}

	public boolean isRequired() {
		return getNakedMultiplicity().getLower() == 1;
	}

	public boolean isUnique() {
		return parameter.isUnique();
	}

	public INakedClassifier getOwner() {
		return owner;
	}

	public boolean isOclDef() {
		return false;
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return parameter.getNakedMultiplicity();
	}

	public String getSignature() {
		return owner.getPathName() + "::" + parameter.getName();
	}

	public IClassifier getType() {
		return parameter.getType();
	}

	public boolean hasClassScope() {
		return false;
	}

	public boolean isAggregate() {
		return false;
	}

	public boolean isComposite() {
		return false;
	}

	public boolean isDerived() {
		return false;
	}

	public VisibilityKind getVisibility() {
		return VisibilityKind.PUBLIC;
	}

	public boolean isReadOnly() {
		return false;
	}

	public void setVisibility(VisibilityKind kind) {
	}

	public void setBaseType(INakedClassifier nakedPeer) {
	}

	public void setIsOrdered(boolean ordered) {
	}

	public void setIsUnique(boolean unique) {
	}

	public void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl) {
	}

	public void setType(IClassifier type) {
	}

	public INakedValueSpecification getInitialValue() {
		return null;
	}

	public INakedProperty getOtherEnd() {
		return null;
	}

	public int getOwnedAttributeIndex() {
		return 0;
	}

	public String[] getQualifierNames() {
		return new String[0];
	}

	public List<INakedProperty> getQualifiers() {
		return Collections.emptyList();
	}

	public Collection<INakedProperty> getRedefinedProperties() {
		return Collections.emptyList();
	}

	public Collection<INakedProperty> getSubsettedProperties() {
		return Collections.emptyList();
	}

	public boolean hasQualifier(String name) {
		return false;
	}

	public boolean hasQualifiers() {
		return false;
	}

	public boolean isDerivedUnion() {
		return false;
	}

	public boolean isDiscriminator() {
		return false;
	}

	public boolean isQualifier() {
		return false;
	}

	public boolean isQualifierForComposite() {
		return false;
	}

	public boolean isStatic() {
		return false;
	}

	public void setAssociation(INakedAssociation impl) {
	}

	public void setComposite(boolean b) {
	}

	public void setDerived(boolean b) {
	}

	public void setDerivedUnion(boolean b) {
	}

	public void setInitialValue(INakedValueSpecification v) {
	}

	public void setInverse(boolean inverse) {
	}

	public void setIsQualifier(boolean isQualifier) {
	}

	public void setIsQualifierForComposite(boolean isQualifierForComposite) {
	}

	public void setNavigable(boolean b) {
	}

	public void setOtherEnd(INakedProperty end2) {
	}

	public void setOwnedAttributeIndex(int elementIndex) {
	}

	public void setQualifierNames(String[] qualifierNames) {
	}

	public void setQualifiers(List<INakedProperty> qualifiers) {
	}

	public void setReadOnly(boolean readOnly) {
	}

	public void setRedefinedProperties(Collection<INakedProperty> p) {
	}

	public void setSubsettedProperties(Collection<INakedProperty> p) {
	}

	public IAssociation getAssociation() {
		return null;
	}

	public boolean isNavigable() {
		return true;
	}
}
