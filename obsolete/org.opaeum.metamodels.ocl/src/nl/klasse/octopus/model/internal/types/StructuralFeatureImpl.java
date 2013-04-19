/*
 * Created on Dec 26, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.octopus.model.IStructuralFeature;
import nl.klasse.octopus.oclengine.IOclContext;


/**
 * StructuralFeatureImpl : 
 */
public class StructuralFeatureImpl extends ModelElementImpl implements IStructuralFeature {
	protected IClassifier owner = null;
	protected IClassifier type  = null;
	protected boolean isOrdered = false;
	protected boolean isDerived = false;
	protected boolean isUnique = true;
	protected boolean isComposite = false;
	protected boolean isAggregate = false;
	protected IMultiplicityKind multiplicity = MultiplicityKindImpl.UNKNOWN;
	protected IOclContext init = null;
	protected IOclContext derivationRule = null;

	/**
	 * 
	 */
	public StructuralFeatureImpl(String name) {
		super(name);
	}

	public String getSignature() {
		String classScope = " ";
		if (this.hasClassScope()) {
			classScope = " $ ";
		}
	    return getVisibility() + classScope + getName() + " : " + (getType() == null ? "<null>" : getType().getName());
	}

	/**
		 * Returns the isOrdered.
		 * @return boolean
		 */
	public boolean isOrdered() {
		return isOrdered;
	}

	/**
		 * Sets the isOrdered.
		 * @param isOrdered The isOrdered to set
		 */
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	/**
		 * Returns the init.
		 * @return OclContext
		 */
	public IOclContext getInit() {
		return init;
	}

	/**
		 * Sets the init.
		 * @param init The init to set
		 */
	public void setInit(IOclContext init) {
		this.init = init;
	}

	/**
		 * Sets the init.
		 * @param init The init to set
		 */
	public void removeInit(IOclContext init) {
		if (this.init == init) this.init  = null;
	}
	
	public IClassifier getType() {
	    return type;
	}

	public void setType(IClassifier c) {
	    type = c;
	}

	public void setIsDerived(boolean derived) {
	    isDerived = derived;
	}

	public boolean isDerived() {
	    return isDerived;
	}

	/** Getter for property multiplicity.
	     * @return Value of property multiplicity.
	     */
	public IMultiplicityKind getMultiplicity() {
	    return multiplicity;
	}

	/** Setter for property multiplicity.
	     * @param multiplicity New value of property multiplicity.
	     */
	public void setMultiplicity(IMultiplicityKind multiplicity) {
	    this.multiplicity = multiplicity;
	}

	/**
		 * Returns the derivationRule.
		 * @return OclContext
		 */
	public IOclContext getDerivationRule() {
		return derivationRule;
	}
	/**
		 * Sets the derivationRule.
		 * @param derivationRule The derivationRule to set
		 */
	public void setDerivationRule(IOclContext derivationRule) {
		this.derivationRule = derivationRule;
		this.isDerived = true;
	}

	/**
		 * Sets the derivationRule.
		 * @param derivationRule The derivationRule to set
		 */
	public void removeDerivationRule(IOclContext derivationRule) {
		if (this.derivationRule == derivationRule) this.derivationRule = null;
		this.isDerived = false;
	}

	/**
	 * @return
	 */
	public boolean isComposite() {
		return isComposite;
	}

	/**
	 * @return
	 */
	public boolean isUnique() {
		return isUnique;
	}

	/**
	 * @param b
	 */
	public void setIsComposite(boolean b) {
		isComposite = b;
	}

	/**
	 * @param b
	 */
	public void setIsAggregate(boolean b) {
		isAggregate = b;
	}

	/**
	 * @param b
	 */
	public void setIsUnique(boolean b) {
		isUnique = b;
	}

	/** Getter for property owner.
	     * @return Value of property owner.
	     */
	public IClassifier getOwner() {
	  return owner;
	}

	/** Setter for property owner.
	     * @param owner New value of property owner.
	     */
	public void setOwner(IClassifier owner) {
	  this.owner = owner;
	}

	public PathName getPathName(){
		PathName newPath = new PathName();
		if (owner != null) {
			newPath = owner.getPathName().getCopy();
			newPath.addString(getName());
		} else {
			newPath = new PathName(getName());
		}
		return newPath;
	}

	protected boolean hasClassScope = false;

	/**
	 * Returns the isClassAttribute.
	 * @return boolean
	 */
	public boolean hasClassScope() {
	    return hasClassScope;
	}

	/**
	 * Sets the isClassAttribute.
	 * @param hasClassScope The isClassAttribute to set
	 */
	public void setHasClassScope(boolean hasClassScope) {
	    this.hasClassScope = hasClassScope;
	}    

	/**
	 * @return Returns the isAggregate.
	 */
	public boolean isAggregate() {
		return isAggregate;
	}
}
