/*
 * Created on Jun 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.TupleTypeCreator;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;

import org.opeum.java.metamodel.OJPackage;

/**
 * TupleTypeAdder : 
 */
public class TupleTypeAdder {
	
	private String TuplePackName = "tuples";

	/**
	 * 
	 */
	public TupleTypeAdder() {
		super();
	}

	/**
	 * Gets the tupletypes from the standard library and transforms these
	 * @param in: the model for which to generate the tupletypes
	 * @return the package containing the generated classes for the tupletypes
	 */
	public OJPackage makeTupleTypes(IOclLibrary lib) {
		OJPackage tuples = null;
		if (lib != null && lib instanceof OclLibraryImpl) {
			tuples = new OJPackage();
			tuples.setName(TuplePackName);
		
			Collection<?> types = ((OclLibraryImpl)lib).getTupleTypes();
			Iterator<?> it = types.iterator();
			while (it.hasNext()){
				ITupleType tupletype = (ITupleType) it.next();
				TupleTypeCreator tupleMaker = new TupleTypeCreator();
				tupleMaker.make(tupletype, tuples);
			}
		} else {
			System.out.println("No library found");
		}
		return tuples;
	}	
	
}
