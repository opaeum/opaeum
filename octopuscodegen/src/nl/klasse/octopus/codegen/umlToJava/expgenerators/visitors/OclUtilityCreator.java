/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors;


import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.InvHelpersCreator;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.StdLibCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.stdlib.IOclLibrary;

/**
 * ModelTransformer : 
 */
public class OclUtilityCreator {
	static private OJPathName tuplesPath 		= new OJPathName("utilities.tuples"); 
	static private OJPathName stdlibPath		= new OJPathName("utilities.Stdlib");
	static private OJPathName invErrorPath		= new OJPathName("utilities.InvariantError");
	static private OJPathName invExceptionPath	= new OJPathName("utilities.InvariantException");
	private OJPackage utilPack					= null;

	/**
	 * 
	 */
	public OclUtilityCreator(OJPackage javamodel) {
		super();
		OJPathName path = UtilityCreator.getUtilPathName();
		utilPack = javamodel.findPackage(path);
	}
	
	public void makeOclUtilities(IPackage in, IOclLibrary lib) {
		makeTupleTypes(in, lib);
		makeStdlib();
		makeInvHelperClasses();
	}

	private void makeTupleTypes(IPackage in, IOclLibrary lib) {
		// get the tupletypes from the standlib and transform these
		TupleTypeAdder tupleTypeAdder = new TupleTypeAdder();
		OJPackage tuples = tupleTypeAdder.makeTupleTypes(lib);
		if (utilPack != null && tuples != null) {
			utilPack.addToSubpackages(tuples);
			tuplesPath = tuples.getPathName();
		}		
	}

	private void makeStdlib() {
		StdLibCreator generator = new StdLibCreator();
		OJClass lib = generator.makeStdLib(utilPack);
		stdlibPath = lib.getPathName();
	}

	public void makeInvHelperClasses() {
		InvHelpersCreator generator = new InvHelpersCreator();
		if (utilPack != null) {
			if (utilPack.findClass(invErrorPath) == null) { // these classes have not been generated earlier
				OJClass invError = generator.makeInvariantError();
				utilPack.addToClasses(invError);
				invErrorPath = invError.getPathName();
				OJClass invException = generator.makeInvariantException();
				utilPack.addToClasses(invException);
				invExceptionPath = invException.getPathName();
			}
		}
	}

	/**
	 * @return
	 */
	public static OJPathName getStdlibPath() {
		return new OJPathName( UtilityCreator.getUtilPathName()+"."+stdlibPath.getLast());
	}

	/**
	 * @return
	 */
	public static OJPathName getTuplesPath() {
		return tuplesPath.getCopy();
	}
	/**
	 * @return
	 */
	public static OJPathName getInvErrorPath() {
		return invErrorPath;
	}

	/**
	 * @return
	 */
	public static OJPathName getInvExceptionPath() {
		return invExceptionPath;
	}

}
