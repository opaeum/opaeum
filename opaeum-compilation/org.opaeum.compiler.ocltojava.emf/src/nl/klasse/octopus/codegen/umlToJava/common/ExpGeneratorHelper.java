package nl.klasse.octopus.codegen.umlToJava.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.Variable;
import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class ExpGeneratorHelper{
	public OJUtil ojUtil;
	public ExpGeneratorHelper(OJUtil ojUtil){
		super();
		this.ojUtil=ojUtil;
	}
	/**
	 * The let variable needs to be added to any generated operation implementing the body expression. This method adds the let variable and
	 * replaces any variable that will be eclipsed by it in the scope of the body expression.
	 * 
	 * @param params
	 * @return
	 */
	public List<OJParameter> addVarToParams(Variable elem,List<OJParameter> params){
		List<OJParameter> result = new ArrayList<OJParameter>(params);
		Iterator<?> it = params.iterator();
		while(it.hasNext()){
			OJParameter par = (OJParameter) it.next();
			if(javaFieldName(elem).equals(par.getName())){
				result.remove(par);
			}
		}
		result.add(varDeclToOJPar(elem));
		return result;
	}
	public OJParameter varDeclToOJPar(Variable elem){
		OJParameter result = new OJParameter();
		result.setName(javaFieldName(elem));
		result.setType(ojUtil.buildClassifierMap(elem.getType()).javaTypePath());
		return result;
	}
	public static String javaFieldName(Variable elem){
		return NameConverter.decapitalize(elem.getName());
	}
	public OJPathName makeListType(Classifier elementType){
		OJPathName myType;
		ClassifierMap elementMap = ojUtil.buildClassifierMap(elementType);
		if(elementMap.isJavaPrimitive()){
			myType = elementMap.javaObjectTypePath();
		}else{
			myType = elementMap.javaTypePath();
		}
		return myType;
	}
	public String makeListElem(OJClass myClass,Classifier type,String argStr){
		ClassifierMap typeMap = ojUtil.buildClassifierMap(type);
		if(typeMap.isJavaPrimitive()){
			String myType = typeMap.javaObjectType();
			myClass.addToImports(typeMap.javaObjectTypePath());
			argStr = "new " + myType + "(" + argStr + ")";
		}else{
			myClass.addToImports(typeMap.javaTypePath());
			argStr = StringHelpers.addBrackets(argStr);
		}
		return argStr;
	}
}
