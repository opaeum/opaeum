/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.model.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.internal.types.ModelElementImpl;


/** An ImportedElementReference is an unresolves reference to some IModelElement that
 * is imported in a package. The reference is a PathName instance. All ImportedElementReferences
 * should be replaced by ModelElementImpl instances before the UML model is used. 
 * This is done by the class ModelAnalyzer.
 * <p>
 * This class implements IImportedElement, but most of the operations are empty.
 *  * 
 * @author  Jos Warmer
 * @version $Id: ImportedElementReference.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ImportedElementReference extends ModelElementImpl implements IImportedElement {

    private PathName referredName = null;
    /** Creates a new instance of ImportedElementReference
      */
    public ImportedElementReference(PathName pathName) {
        super(pathName.getLast());
        referredName = pathName;
    }

    public IModelElement getElement() {
        return null;
    }
    
    public boolean isReference() {
        return true;
    }
    
    public PathName getPathname(){
        return referredName;
    }
    
    public String toString() {
        return referredName.toString() + " -- a reference";
    }  
}
