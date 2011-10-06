package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class SignalSender extends SimpleNodeBuilder<INakedSendSignalAction>{
	public SignalSender(OpaeumLibrary oclEngine,INakedSendSignalAction action,AbstractObjectNodeExpressor expressor){
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
