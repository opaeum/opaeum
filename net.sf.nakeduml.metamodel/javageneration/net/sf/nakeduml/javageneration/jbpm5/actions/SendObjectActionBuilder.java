package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedSendObjectAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class SendObjectActionBuilder extends PotentialTaskActionBuilder<INakedSendObjectAction>{

	public SendObjectActionBuilder(IOclEngine engine, INakedSendObjectAction node){
		super(engine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getObject());
		operation.getBody().addToStatements(
				map.javaType() + " " + map.umlName() + "=" + buildPinExpression(operation, operation.getBody(), node.getObject()));
	}

}
