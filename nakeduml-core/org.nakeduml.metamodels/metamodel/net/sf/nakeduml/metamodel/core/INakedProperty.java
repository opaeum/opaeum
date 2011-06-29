package net.sf.nakeduml.metamodel.core;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;

/**
 * Common superclass for properties, objectNodes and call actions that require persistence in activities Typically one would like any
 * implementors of this interface to be accessible from OCL, hence the implementation of the IAttribute interface
 * 
 */
// TODO refactore all this out to the NakedUmlStructuralFeatureMap
public interface INakedProperty extends INakedPackageableElement,IModifiableTypedElement,IAttribute,IAssociationEnd{
	INakedProperty getOtherEnd();
	/**
	 * Returns true if this property has one or more qualifiers
	 */
	boolean hasQualifiers();
	/**
	 * Returns true if this property has a qualifier with the specified name
	 */
	boolean hasQualifier(String name);
	/**
	 * NakedUml deviates from the UML spec in that it considers qualifiers to be properties of the baseType of the property. Since values of
	 * the qualifier property are assumed to vary along with instances of the baseType, they are treated as part of baseType. UML considers
	 * qualifiers to belong to the property itself, and expects qualifier values to vary along with the instances of the association. In
	 * compositional relationships this distinction becomes immaterial, but NakedUml has extended this interpretation to all associations.
	 * 
	 */
	List<INakedProperty> getQualifiers();
	String[] getQualifierNames();
	/**
	 * A discriminator in Hibernate is used to determine the actual type of an instance represented by a row in the database. NakedUml uses
	 * its PowerType logic to determine whether this property is actually a discriminator type column
	 */
	boolean isDiscriminator();
	void setReadOnly(boolean readOnly);
	// TODO duplicates derivationRule and init
	INakedValueSpecification getInitialValue();
	void setInitialValue(INakedValueSpecification v);
	boolean isDerivedUnion();
	void setDerivedUnion(boolean b);
	Collection<INakedProperty> getSubsettedProperties();
	void setSubsettedProperties(Collection<INakedProperty> p);
	Collection<INakedProperty> getRedefinedProperties();
	void setRedefinedProperties(Collection<INakedProperty> p);
	/**
	 * Returns true if this property is used as a backing property for a qualifier specified on one the associations the owner of this
	 * property participates in NB! NakedUml does not apply qualifier semantics in the generation of OCL code. It rather allows OCL code to
	 * access the property as if it is a normal Set,Bag,OrderedSet or Sequence. They are also not represented as "Map" relationships in
	 * Hibernate. Qualifiers are used primarily in establishing uniqueness constraints.
	 */
	boolean isQualifier();
	void setIsQualifier(boolean isQualifier);
	/**
	 * Returns true if this property is the backing property for a qualifier in a compositional relationship where the owner of this property
	 * plays the role of the child.
	 */
	boolean isQualifierForComposite();
	void setIsQualifierForComposite(boolean isQualifierForComposite);
	void setNavigable(boolean b);
	void setComposite(boolean b);
	void setDerived(boolean b);
	void setQualifierNames(String[] qualifierNames);
	void setAssociation(INakedAssociation impl);
	void setOtherEnd(INakedProperty end2);
	/**
	 * 
	 * This property is for linkage of argument pins to properties of Signals.
	 */
	int getOwnedAttributeIndex();
	void setOwnedAttributeIndex(int elementIndex);
	void setQualifiers(List<INakedProperty> qualifiers);
	void setInverse(boolean inverse);
	boolean isStatic();
	void setBaseType(INakedClassifier nakedPeer);
	void setIsOrdered(boolean ordered);
	void setIsUnique(boolean unique);
	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);
	INakedClassifier getOwner();
	public boolean isRequired();
	boolean isInverse();
	public boolean isReadOnly();
	Collection<INakedConnectorEnd> getConnectorEnd();
}
