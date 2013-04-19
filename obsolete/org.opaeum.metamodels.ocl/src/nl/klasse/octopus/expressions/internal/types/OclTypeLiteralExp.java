/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/**
 * @author  Jos Warmer
 * @version $Id: OclTypeLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class OclTypeLiteralExp extends LiteralExp implements IOclTypeLiteralExp {
    private IClassifier referredClassifier = null;
    
    public OclTypeLiteralExp(IClassifier ref) {
        referredClassifier = ref;
    }
    
    public IClassifier getNodeType() {
      return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclTypeTypeName);
    }
    
    public IClassifier getReferredClassifier() {
        return referredClassifier;
    }
    
	public String toString() {
		return referredClassifier.getName();
	}

}
