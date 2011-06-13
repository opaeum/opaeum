/*
 * Created on Jul 17, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import org.nakeduml.java.metamodel.OJPathName;


/**
 * StdlibMap : 
 */
public class StdlibMap {
	// TODO check the values of these attributes
	public static final OJPathName javaCollectionType	= new OJPathName("java.util.Collection");
	public static final OJPathName javaSetType 			= new OJPathName("java.util.Set");
	public static final OJPathName javaSequenceType 	= new OJPathName("java.util.List");
	public static final OJPathName javaOrderedSetType 	= new OJPathName("java.util.List");
	public static final OJPathName javaBagType 			= new OJPathName("java.util.Collection");
	public static final OJPathName javaStringType 		= new OJPathName("java.lang.String");
	public static final OJPathName javaIntegerType		= new OJPathName("java.lang.int");
	public static final OJPathName javaBooleanType 		= new OJPathName("java.lang.boolean");
	public static final OJPathName javaRealType 		= new OJPathName("java.lang.float");
	public static final OJPathName javaIntegerObjectType	= new OJPathName("java.lang.Integer");
	public static final OJPathName javaBooleanObjectType 	= new OJPathName("java.lang.Boolean");
	public static final OJPathName javaRealObjectType 		= new OJPathName("java.lang.Float");

	public static final OJPathName javaSetImplType		= new OJPathName("java.util.HashSet");
	public static final OJPathName javaSequenceImplType	= new OJPathName("java.util.ArrayList");
	public static final OJPathName javaOrderedSetImplType= new OJPathName("java.util.ArrayList");
	public static final OJPathName javaBagImplType		= new OJPathName("java.util.ArrayList");


	/**
	 * 
	 */
	public StdlibMap() {
		super();
		// TODO Auto-generated constructor stub
	}

}
