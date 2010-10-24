package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Iterator;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class SendSignalActionBuilder extends Jbpm5ActionBuilder<INakedSendSignalAction>{
	public SendSignalActionBuilder(IOclEngine oclEngine,INakedSendSignalAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		Iterator args = node.getArguments().iterator();
		String signalName = "_signal";
		ActionMap actionMap = new ActionMap(node);
		OJBlock block = buildLoopThroughTarget(operation, operation.getBody(), actionMap);
		ClassifierMap cm = new NakedClassifierMap(node.getSignal());
		operation.getOwner().addToImports(cm.javaTypePath());
		while(args.hasNext()){
			INakedPin pin = (INakedPin) args.next();
			if(pin.getLinkedTypedElement() == null){
				block.addToStatements(signalName + ".couldNotLinkPinToProperty(" + buildPinExpression(operation, block, pin) + ")");
			}else{
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap((INakedProperty) pin.getLinkedTypedElement());
				block.addToStatements(signalName + "." + map.setter() + "(" + buildPinExpression(operation, block, pin) + ")");
			}
		}
		OJAnnotatedField signal = new OJAnnotatedField();
		signal.setName(signalName);
		block.addToLocals(signal);
		String source = node.getActivity().isProcess() ? "this" : "getContextObject()";
		String pinExpression = node.getTarget()==null? source:buildPinExpression(operation, block, node.getTarget());
		block.addToStatements(signalName + ".send(" + source + ","+ pinExpression+")");
		signal.setType(cm.javaTypePath());
		signal.setInitExp("new " + node.getSignal().getMappingInfo().getJavaName() + "()");
	}
}
