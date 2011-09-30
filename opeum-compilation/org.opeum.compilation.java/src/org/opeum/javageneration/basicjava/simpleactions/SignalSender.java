package org.opeum.javageneration.basicjava.simpleactions;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.maps.SignalMap;
import org.opeum.metamodel.actions.INakedSendSignalAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedPin;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class SignalSender extends SimpleNodeBuilder<INakedSendSignalAction>{
	public SignalSender(OpeumLibrary oclEngine,INakedSendSignalAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		SignalMap signalMap = new SignalMap(node.getSignal());
		Iterator<INakedInputPin> args = node.getArguments().iterator();
		String signalName = "_signal" + node.getMappingInfo().getJavaName();
		ClassifierMap cm = new NakedClassifierMap(node.getSignal());
		operation.getOwner().addToImports(cm.javaTypePath());
		while(args.hasNext()){
			INakedPin pin = args.next();
			if(pin.getLinkedTypedElement() == null){
				block.addToStatements(signalName + "couldNotLinkPinToProperty!!!");
			}else{
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap((INakedProperty) pin.getLinkedTypedElement());
				block.addToStatements(signalName + "." + map.setter() + "(" + readPin(operation, block, pin) + ")");
			}
		}
		OJAnnotatedField signal = new OJAnnotatedField(signalName, cm.javaTypePath());
		block.addToLocals(signal);
		String source = "this";
		String targetExpression;
		if(node.getTarget() != null){
			targetExpression = readPin(operation, block, node.getTarget());
		}else{
			targetExpression = source;
		}
		OJPathName handlerPathName = signalMap.handlerTypePath();
		operation.getOwner().addToImports(handlerPathName);
		block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + ",new " +handlerPathName.getLast() + "("+ signalName + ",false)))");
		signal.setType(cm.javaTypePath());
		signal.setInitExp("new " + node.getSignal().getMappingInfo().getJavaName() + "()");
	}
}
