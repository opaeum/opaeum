/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;


/** This class represents an import of a single element.
 * 
 * @author  Jos Warmer
 * @version $Id: IImportedElement.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IImportedElement extends IModelElement {

    public IModelElement getElement();
    
    public boolean isReference();
}
