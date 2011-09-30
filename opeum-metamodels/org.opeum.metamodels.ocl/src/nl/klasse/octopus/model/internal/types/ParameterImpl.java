/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.ParameterDirectionKind;

/** @author  anneke
 * @version $Id: ParameterImpl.java,v 1.1 2006/03/01 19:13:34 jwarmer Exp $
 */
public class ParameterImpl extends ModelElementImpl implements IParameter {
    private IClassifier     type;        
    private OperationImpl  owner = null;
    private ParameterDirectionKind            direction  = ParameterDirectionKind.IN;

    /** Creates new ParameterImpl */
    public ParameterImpl(String n, IClassifier t) {
        super(n);
        type = t;
    }

    public IClassifier getType(){
        return type;
    }

    public void setType(IClassifier c) {
        type = c;
    }

    public String toString() {
        return getName() + ": " + type.getName();
    }

    /** Getter for property owner.
     * @return Value of property owner.
     */
    public IOperation getOwner() {
      return owner;
    }

    /** Setter for property owner.
     * @param owner New value of property owner.
     */
    public void setOwner(OperationImpl owner) {
      this.owner = owner;
    }
    
    public ParameterDirectionKind getDirection() {
        return direction;
    }

    public void setDirection(ParameterDirectionKind dir) {
        direction = dir;
    }

    
}
