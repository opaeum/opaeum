package nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.StdLibCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.ocl.TypeResolver;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.util.OJUtil;

public class OclUtilityCreator{
	static private OJPathName tuplesPath = new OJPathName("utilities.tuples");
	static private OJPathName stdlibPath = new OJPathName("utilities.Stdlib");
	static private OJPathName invErrorPath = new OJPathName("utilities.InvariantError");
	static private OJPathName invExceptionPath = new OJPathName("utilities.InvariantException");
	private OJPackage utilPack = null;
	private OJUtil ojUtil;
	public OclUtilityCreator(OJUtil ojUtil, OJWorkspace javamodel){
		super();
		this.ojUtil=ojUtil;
		OJPathName path = UtilityCreator.getUtilPathName();
		utilPack = javamodel.findPackage(path);
	}
	public void makeOclUtilities(TypeResolver<Classifier,Operation,Property> tr){
		makeTupleTypes(tr);
		makeStdlib();
	}
	private void makeTupleTypes(TypeResolver<Classifier,Operation,Property> tr){
		// get the tupletypes from the standlib and transform these
		TupleTypeAdder tupleTypeAdder = new TupleTypeAdder();
		OJPackage tuples = tupleTypeAdder.makeTupleTypes(ojUtil, tr);
		if(utilPack != null && tuples != null){
			utilPack.addToSubpackages(tuples);
			tuplesPath = tuples.getPathName();
		}
	}
	private void makeStdlib(){
		StdLibCreator generator = new StdLibCreator();
		OJClass lib = generator.makeStdLib(utilPack);
		stdlibPath = lib.getPathName();
	}
	public static OJPathName getStdlibPath(){
		return new OJPathName(UtilityCreator.getUtilPathName() + "." + stdlibPath.getLast());
	}
	public static OJPathName getTuplesPath(){
		return tuplesPath.getCopy();
	}
	public static OJPathName getInvErrorPath(){
		return invErrorPath;
	}
	public static OJPathName getInvExceptionPath(){
		return invExceptionPath;
	}
}
