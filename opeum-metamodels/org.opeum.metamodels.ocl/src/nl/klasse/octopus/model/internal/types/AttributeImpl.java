/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.octopus.oclengine.IOclContext;


/**
 * @author  anneke
 * @version $Id: AttributeImpl.java,v 1.2 2008/01/19 13:42:11 annekekleppe Exp $
 */
public class AttributeImpl extends StructuralFeatureImpl implements IAttribute {
    private boolean	   isOclDef			= false;
    
    /** Creates new AttributeImpl */
    public AttributeImpl(String n, IClassifier c, IMultiplicityKind multiplicity) {
        super(n);
        type = c;
        hasClassScope = false;
        if (MultiplicityKindImpl.isValid( multiplicity )) {
            setMultiplicity(multiplicity);
        } else {
			setMultiplicity(MultiplicityKindImpl.UNKNOWN);
        }
        this.setIsComposite(true);
    }

    /** Creates new AttributeImpl
     *  If clsAttr is true the newly created attribute is a class attribute */
    public AttributeImpl(String n, IClassifier c, boolean clsAttr) {
        super(n);
        type = c;
        hasClassScope = clsAttr;
    }

    public String toString() {
    	String prefix = "";
    	if (this.hasClassScope()) {
    		prefix = "$ ";
    	}
        return prefix + getName() + ": " + type.getName();
    }

    /**
	 * Returns the isOclDef.
	 * @return boolean
	 */
	public boolean isOclDef() {
		return isOclDef;
	}

	/**
	 * Sets the isOclDef.
	 * @param isOclDef The isOclDef to set
	 */
	public void setIsOclDef(boolean isOclDef) {
		this.isOclDef = isOclDef;
	}
	
	public boolean isUnique(){
		if( this.getType().isCollectionKind() ){
			ICollectionType type = (ICollectionType) this.getType();
			return type.isSet() || type.isOrderedSet();
		} else {
			return true;
		}
	}
	
	public List<IOclContext> getAllOclExpressions() {
        List<IOclContext> result = new ArrayList<IOclContext>();
        if (init != null) result.add(init);
        if (derivationRule != null)result.add(derivationRule);
        return result;
    }

}
