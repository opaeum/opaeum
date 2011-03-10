/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis;

import nl.klasse.octopus.model.IModelElement;


/** A NamedElement is an elements that resides within an Environemnt.
 * See OCL 2.0 Specification.
 * 
 * @author  Jos Warmer
 * @version $Id: NamedElement.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class NamedElement {

    private String        name          = null;
    private boolean       mayBeImplicit = false;
    private IModelElement  element       = null;
    
    /** Creates a new instance of NamedElement
      */
    public NamedElement(String name, IModelElement elem, boolean implicit) {
        this.name          = name;
        this.mayBeImplicit = implicit;
        this.element       = elem;
    }
    
    /** Creates a new instance of NamedElement
      */
    public NamedElement(String name, IModelElement elem) {
        this.name    = name;
        this.element = elem;
    }
    
    protected String getName() {
        return name;
    }
    
    public IModelElement getElement() {
        return element;
    }
    
    protected boolean mayBeImplicit() {
        return mayBeImplicit;
    }

    
    public String toString() {
        return "NamedElement " + getName() + " : " + element.toString();
    }
}
