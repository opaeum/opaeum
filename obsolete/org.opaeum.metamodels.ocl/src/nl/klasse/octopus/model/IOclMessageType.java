/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** 
 * @author  Jos Warmer
 * @version $Id: IOclMessageType.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IOclMessageType extends IClassifier {

	public IOperation getReferredOperation();

	// TODO When Signals are implemented in the model we need to add:
	// public ISignal getReferredSignal();
	
}
