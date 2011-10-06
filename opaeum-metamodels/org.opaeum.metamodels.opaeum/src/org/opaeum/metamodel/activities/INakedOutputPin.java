package org.opaeum.metamodel.activities;


/**
 * From Octopus' perspective an outputpin is an attribute of the Activity
 * 
 * @author barnar_a
 * 
 */
public interface INakedOutputPin extends INakedPin{
	boolean isException();
}
