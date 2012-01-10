package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.IActionWithTargetPin;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.bpm.INakedBusinessComponent;
import org.opaeum.metamodel.bpm.INakedBusinessRole;
import org.opaeum.metamodel.bpm.INakedBusinessService;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class SignalSender extends SimpleNodeBuilder<INakedSendSignalAction>{
	private ActionMap actionMap;
	public SignalSender(OpaeumLibrary oclEngine,INakedSendSignalAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
		this.actionMap = new ActionMap(node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		SignalMap signalMap = OJUtil.buildSignalMap(node.getSignal());
		Iterator<INakedInputPin> args = node.getArguments().iterator();
		String signalName = "_signal" + node.getMappingInfo().getJavaName();
		ClassifierMap cm = OJUtil.buildClassifierMap(node.getSignal());
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
		signal.setType(cm.javaTypePath());
		signal.setInitExp("new " + node.getSignal().getMappingInfo().getJavaName() + "()");
		String targetExpression = expressTarget(operation, block);
		// Could be a notification only in which case
		OJPathName handlerPathName = signalMap.handlerTypePath();
		OJAnnotatedField handler = new OJAnnotatedField(signalName + "Handler", handlerPathName);
		block.addToLocals(handler);
		handler.setInitExp("new " + handlerPathName.getLast() + "(" + signalName + ",false)");
		operation.getOwner().addToImports(handlerPathName);
		block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + "," + signalName + "Handler))");
		if(node.getTargetElement() != null && node.getTargetElement().getNakedBaseType() instanceof INakedBehavioredClassifier){
			INakedBehavioredClassifier target = (INakedBehavioredClassifier) node.getTargetElement().getNakedBaseType();
			if(node.getSignal().isNotification() && isNotificationReceiver(target)){
				if(node.getFromExpression() != null){
					block.addToStatements(handler.getName() + ".setFrom(new HashSet<INotificationReceiver>(" + expressDestination(operation, node.getFromExpression())
							+ ")");
				}
				if(node.getCcExpression() != null){
					block.addToStatements(handler.getName() + ".setCc(new HashSet<INotificationReceiver>(" + expressDestination(operation, node.getCcExpression()) + ")");
				}
				if(node.getBccExpression() != null){
					block.addToStatements(handler.getName() + ".setBcc(new HashSet<INotificationReceiver>(" + expressDestination(operation, node.getBccExpression())
							+ ")");
				}
			}
		}
	}
	protected String expressTarget(OJAnnotatedOperation operationContext,OJBlock block){
		String expression = null;
		if(node.getInPartition() != null){
			if(node.getInPartition().getRepresents() instanceof INakedProperty){
				expression = actionMap.targetMap().getter() + "()";
			}else if(node.getInPartition().getRepresents() instanceof INakedClassifier){
				expression = "NOT IMPLEMENTED";
				// TODO use group assignment here
			}
		}else if(node.getTarget() != null){
			expression = readPin(operationContext, block, ((IActionWithTargetPin) node).getTarget());
		}else{
			expression = "this";
		}
		//NB!!! signals are always sent to a collection of targets
		if(actionMap.targetMap().isOne()){
			return "Stdlib.objectAsSet(" + expression + ")";
		}else if(!actionMap.targetMap().isUnique()){
			return "new HashSet<INotificationReceiver>(" + expression + ")";
		}else{
			return expression;
		}
	}
	protected String expressDestination(OJAnnotatedOperation operation,INakedValueSpecification fromExpression){
		if(fromExpression.isValidOclValue()){
			if(fromExpression.getOclValue().getExpression().getExpressionType().isCollectionKind()){
				return ValueSpecificationUtil.expressValue(operation, fromExpression, node.getActivity(), null);
			}else{
				return "Stdlib.objectAsSet("  + ValueSpecificationUtil.expressValue(operation, fromExpression, node.getActivity(), null) + ")";
			}
		}else{
			return "";
		}
	}
	private boolean isNotificationReceiver(INakedBehavioredClassifier target){
		if(target instanceof INakedBusinessComponent || target instanceof INakedBusinessRole || target instanceof INakedBusinessService){
			for(INakedProperty p:target.getEffectiveAttributes()){
				if(getLibrary().getEmailAddressType().equals(p.getNakedBaseType())){
					return true;
				}
			}
		}
		return false;
	}
}
