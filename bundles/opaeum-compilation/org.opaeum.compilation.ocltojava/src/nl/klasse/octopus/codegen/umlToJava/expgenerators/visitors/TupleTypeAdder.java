package nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.TupleTypeCreator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.javageneration.util.OJUtil;

public class TupleTypeAdder{
	private String TuplePackName = "tuples";
	public TupleTypeAdder(){
		super();
	}
	public OJPackage makeTupleTypes(OJUtil ojUtil,TypeResolver<Classifier,Operation,Property> tr){
		EList<Type> types = null;
		for(EObject o:tr.getResource().getContents()){
			if(o instanceof Package){
				Package pkg = (Package) o;
				if("tuples".equals(pkg.getName())){
					types = pkg.getOwnedTypes();
					break;
				}
			}
		}
		OJPackage tuples = null;
		tuples = new OJPackage(TuplePackName);
		Iterator<?> it = types.iterator();
		while(it.hasNext()){
			TupleType tupletype = (TupleType) it.next();
			TupleTypeCreator tupleMaker = new TupleTypeCreator(ojUtil);
			tupleMaker.make(tupletype, tuples);
		}
		return tuples;
	}
}
