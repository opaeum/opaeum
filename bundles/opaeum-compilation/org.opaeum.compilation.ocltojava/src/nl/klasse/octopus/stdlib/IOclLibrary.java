package nl.klasse.octopus.stdlib;

import java.util.Collection;

import org.eclipse.ocl.uml.TupleType;




public interface IOclLibrary {
	
	static public String StringTypeName 	= "String";
	static public String RealTypeName 		= "Real";
	static public String IntegerTypeName 	= "Integer";
	static public String BooleanTypeName 	= "Boolean";
	static public String OclVoidTypeName 	= "OclVoid";
	static public String OclAnyTypeName 	= "OclAny";
	static public String OclTypeTypeName 	= "OclType";
	static public String OclStateTypeName 	= "OclState";

	public Collection<? extends TupleType> getTupleTypes();

	
}
