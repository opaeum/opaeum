package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.Iterator;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class SignalSender extends SimpleActionBuilder<INakedSendSignalAction>{
	public SignalSender(IOclEngine oclEngine,INakedSendSignalAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		Iterator<INakedInputPin> args = node.getArguments().iterator();
		String signalName = "_signal" + node.getMappingInfo().getJavaName();
		ActionMap actionMap = new ActionMap(node);
		ClassifierMap cm = new NakedClassifierMap(node.getSignal());
		operation.getOwner().addToImports(cm.javaTypePath());
		while(args.hasNext()){
			INakedPin pin =  args.next();
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
		
		String source = "this";
		if(node.getActivity().getOwnerElement() instanceof INakedTransition){
			source="contextObject";//TODO won't work for standalone statemachines
		}
		block.addToStatements(signalName + ".send("+source+","+buildPinExpression(operation, block, node.getTarget())+")");
		signal.setType(cm.javaTypePath());
		signal.setInitExp("new " + node.getSignal().getMappingInfo().getJavaName() + "()");
	}
}
