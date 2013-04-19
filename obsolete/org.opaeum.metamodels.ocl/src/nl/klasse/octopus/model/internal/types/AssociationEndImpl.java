/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.octopus.oclengine.IOclContext;


/** @author  Jos Warmer
 * @version $Id: AssociationEndImpl.java,v 1.2 2008/01/19 13:33:07 annekekleppe Exp $
 */
public class AssociationEndImpl extends StructuralFeatureImpl implements IAssociationEnd {
    private IClassifier 	baseType;          			
    private boolean    		isNavigable = true;		// whether this associationEnd is marked 'navigable'
	private IAssociation 	myAssoc 	= null;		
	
	/** Creates new AssociationImpl */
	public AssociationEndImpl(String name, IClassifier type, IMultiplicityKind multiplicity, boolean o) {
		super(name);
		this.baseType = type;
		if (MultiplicityKindImpl.isValid( multiplicity )) {
			setMultiplicity(multiplicity);
		} else {
			setMultiplicity(new MultiplicityKindImpl(0, 1));
		}
		this.isOrdered = o;
		// Test for empty rolename
		if( (name == null) || (name.equals(""))  ) {
			setName(type.getName());
		}
		this.myAssoc = null;
	}

	// XXX
	public AssociationEndImpl(String name) {
		super(name);
		setMultiplicity(new MultiplicityKindImpl(0, 1));
	}
	
	public void setMultiplicityKind(IMultiplicityKind multiplicity){
		if (MultiplicityKindImpl.isValid( multiplicity )) {
			setMultiplicity(multiplicity);
		} else {
			setMultiplicity(new MultiplicityKindImpl(0, 1));
		}
	}

	public IClassifier getBaseType() {
        return baseType;
    }

    public void setBaseType(IClassifier c){
        baseType = c;
		if( (this.getName() == null) || (this.getName().equals("")) ) {
			setName(c.getName());
		}

    }

    public void setNavigable(boolean nav){
        isNavigable = nav;
    }

    public boolean isNavigable() {
        return isNavigable;
    }

    public String toString() {
        String result = (getVisibility() == null ? "<no_visibility>" : getVisibility().toString()) + " "
        				 + (baseType == null ? "<no_basetype>" : baseType.toString()) + "." + getName();
		result = result + " [" + multiplicity.toString() + "]";
        return result;
    }

	public List<IOclContext> getAllOclExpressions() {
        List<IOclContext> result = new ArrayList<IOclContext>();
        if (init != null) result.add(init);
        if (derivationRule != null)result.add(derivationRule);
        return result;
    }
	/**
	 * @return
	 */
	public IAssociation getAssociation() {
		return myAssoc;
	}

	/**
	 * @param association
	 */
	public void setAssociation(IAssociation association) {
		myAssoc = association;
	}
	
	public PathName getPathName(){
		PathName path = myAssoc.getOtherEnd(this).getBaseType().getPathName();
		path.addString(this.getName());
		return path;
	}
}
