package org.opaeum.javageneration.bpm.actions;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.ReplyAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;

public class ReplyActionBuilder extends Jbpm5ActionBuilder<ReplyAction>{
	public ReplyActionBuilder(OJUtil ojUtil,ReplyAction node){
		super(ojUtil,node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		List<InputPin> replyValues = node.getReplyValues();
		AcceptCallAction cause = EmfActionUtil.getCause(node);
		if(cause != null){
			Operation oper = EmfActionUtil.getOperation(cause);
			if(EmfBehaviorUtil.hasExecutionInstance(oper)){
				OJAnnotatedField invocation = new OJAnnotatedField("invocation", ojUtil.classifierPathname(oper));
				operation.getBody().addToLocals(invocation);
				StructuralFeatureMap actionMap = ojUtil.buildStructuralFeatureMap(cause);
				invocation.setInitExp(actionMap.getter() + "()");
				for(InputPin pin:replyValues){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(EmfActionUtil.getLinkedTypedElement(pin));
					String pinExpression = expressor.expressInputPinOrOutParamOrExpansionNode(operation.getBody(), pin);
					operation.getBody().addToStatements("invocation." + map.setter() + "(" + pinExpression + ")");
				}
				operation.getBody().addToStatements("invocation.completed()");
			}
		}
	}
}
