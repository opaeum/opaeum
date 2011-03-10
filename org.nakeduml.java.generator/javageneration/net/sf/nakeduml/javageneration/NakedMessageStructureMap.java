package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.nakeduml.java.metamodel.OJPathName;


public class NakedMessageStructureMap {
	INakedComplexStructure structure;
	ClassifierMap classifierMap;
	public NakedMessageStructureMap(IParameterOwner p) {
		super();
		if (p instanceof INakedBehavior) {
			this.structure = (INakedComplexStructure) p;
		} else if(p instanceof INakedOperation){
			this.structure=new OperationMessageStructureImpl((INakedOperation) p);
		}
		classifierMap=new NakedClassifierMap(structure);
	}
	public String fieldName(){
		return structure.getMappingInfo().getJavaName().getDecapped().toString();
	}
	public String getter() {
		return "get" + structure.getMappingInfo().getJavaName().getCapped();
	}

	public String setter() {
		return "set" + structure.getMappingInfo().getJavaName().getCapped();
	}

	public String adder() {
		return "addTo" + structure.getMappingInfo().getJavaName().getCapped();
	}
	public OJPathName javaTypePath() {
		OJPathName list = new OJPathName("java.util.List");
		list.addToElementTypes(javaBaseTypePath());
		return list;
	}
	public OJPathName javaDefaultTypePath() {
		OJPathName list = new OJPathName("java.util.ArrayList");
		list.addToElementTypes(javaBaseTypePath());
		return list;
	}
	public String javaDefaultValue() {
		return "new " + javaDefaultTypePath().getLast()+"<" +javaBaseTypePath().getLast()+ ">()";
	}
	public OJPathName javaBaseTypePath(){
		return classifierMap.javaTypePath();
	}
}
