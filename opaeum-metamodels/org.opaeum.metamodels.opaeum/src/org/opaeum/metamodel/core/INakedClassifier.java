package org.opaeum.metamodel.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.VisibilityKind;

//MOve most of this logic to INakedComplexStructure


/**
 * for Ericsson Umts you can do the following objects 11:26
UtranCell should implement IUmtsCell 11:27
UtranRelation should implement IUmtsRelation 11:27
GsmRelation should implement IGsmRelation 11:27

 */
public interface INakedClassifier extends INakedNameSpace,IClassifier{
	void reorderSequences();
	INakedProperty getNameProperty();
	/**
	 * Use Carefully, could return null in the case of INakedBehaviours that are not owned by Classes due to IOperation.getOwner()
	 * For use from Octopus only. Use getNameSpace() instead 
	 */
	@Deprecated
	INakedNameSpace getOwner(); 
	INakedClassifier getNestingClassifier();
	CodeGenerationStrategy getCodeGenerationStrategy();
	void setCodeGenerationStrategy(CodeGenerationStrategy none);
	String getMappedImplementationType();
	void setMappedImplementationType(String class1);
	boolean isPowerTypeInstance();
	void addSubClass(INakedClassifier c);
	void removeSubClass(INakedClassifier specific);
	Collection<? extends INakedGeneralization> getNakedGeneralizations();
	/**
	 * Returns true if this classifier has a generalization
	 */
	boolean hasSupertype();
	/**
	 * Returns the general classifier from this classifier's generalization
	 */
	INakedClassifier getSupertype();
	/**
	 * Returns a list containing the non-oclDef properties of the this class. As per UML2 it also returns the navigable association ends that
	 * this classifier participates in. Equivalent to the Class::ownedAttribute property in UML2
	 */
	List<? extends INakedProperty> getOwnedAttributes();
	/**
	 * Returns a list containing the effective properties of the this class. As per UML2 it also returns the navigable association ends that
	 * this classifier participates in. It takes redefinition of properties by name into account and filters out oclDef properties generated
	 * by Octopus <BR>
	 * Equivalent to the derived Class::attribute property in UML2, but could not use this name due to different semantics in Octopus
	 */
	List<? extends INakedProperty> getEffectiveAttributes();
	/**
	 * Returns a property from the effectiveAttributes collection that has a matching name
	 */
	INakedProperty findEffectiveAttribute(String name);
	/**
	 * Returns a list containing the operations of this class and its superclasses. <BR>
	 * Equivalent of UMl2 Class::operations, but could not use this name due to different semantics in Octopus
	 */
	Collection<? extends INakedOperation> getEffectiveOperations();
	boolean hasPowerType();
	/**
	 * Returns the PowerType which is an EnumerationType that has an enumeration literal for every subclass of this class
	 */
	INakedPowerType getPowerType();
	void setPowerType(INakedPowerType impl);
	/**
	 * Returns a package representation of the current class. This is to allow Octopus to only see the package features of a class that
	 * contains nested classes and/or behavior.
	 * 
	 * @return
	 */
	Collection<IImportedElement> getImports();
	void setVisibility(VisibilityKind public1);
	List<? extends INakedConstraint> getOwnedRules();
	void setOwnedRules(List<INakedConstraint> loopResults);
	public abstract void removeObsoleteArtificialProperties();
	public abstract String getImplementationCode();
	Set<INakedProperty> getDirectlyImplementedAttributes();
	Set<INakedOperation> getDirectlyImplementedOperations();
}
