/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.ParameterDirectionKind;


public class StdlibParameter implements IParameter {
    private String name;            // name of this element
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    private PathName pathName;		// path of this element as Part1::part2::part3::part4
    public PathName getPathName(){
        return pathName;
    }    
    
    public void setPathName(PathName pn){
        pathName = pn;
    }
 
    private IClassifier type;        // the type of this parameter
    private IOperation  owner = null;

    /** Creates new ParameterImpl */
    public StdlibParameter(String n, IClassifier t) {
        name = n;
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
    public void setOwner(IOperation owner) {
      this.owner = owner;
    }

    public ParameterDirectionKind getDirection() {
        return ParameterDirectionKind.IN;
    }


}
