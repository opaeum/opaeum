/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/**
 * @author  Jos Warmer
 * @version $Id: OclStateLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class OclStateLiteralExp extends LiteralExp implements IOclStateLiteralExp {
    private IState referredState = null;
    
    public OclStateLiteralExp(IState ref) {
        referredState = ref;
    }
    
    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclStateTypeName);
    }
    
    public IState getReferredState() {
        return referredState;
    }
    
    public String toString() {
    	return referredState.getName();
    }

}
