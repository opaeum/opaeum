package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class CreateObjectActionBuilder extends Jbpm5ActionBuilder<INakedCreateObjectAction>{

	public CreateObjectActionBuilder(IOclEngine oclEngine,INakedCreateObjectAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		ClassifierMap classMap = new NakedClassifierMap(node.getClassifier());
		NakedStructuralFeatureMap fieldMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getResult());
		operation.getOwner().addToImports(classMap.javaTypePath());
		operation.getBody().addToStatements("this." + fieldMap.setter() + "(new " + classMap.javaType() + "())");
	}
}
