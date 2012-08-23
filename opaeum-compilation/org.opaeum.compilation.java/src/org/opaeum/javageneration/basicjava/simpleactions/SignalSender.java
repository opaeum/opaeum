package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.event.INotificationReceiver;

public class SignalSender extends SimpleNodeBuilder<SendSignalAction>{
	private ActionMap actionMap;
	public SignalSender(SendSignalAction action,AbstractObjectNodeExpressor expressor){
		super(action, expressor);
		this.actionMap = ojUtil.buildActionMap(node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		SignalMap signalMap = ojUtil.buildSignalMap(node.getSignal());
		Iterator<InputPin> args = node.getArguments().iterator();
		String signalName = "_signal" + node.getName();
		ClassifierMap cm = ojUtil.buildClassifierMap(node.getSignal(), (CollectionKind) null);
		operation.getOwner().addToImports(cm.javaTypePath());
		while(args.hasNext()){
			Pin pin = args.next();
			if(EmfActionUtil.getLinkedTypedElement(pin) == null){
				block.addToStatements(signalName + "couldNotLinkPinToProperty!!!");
			}else{
				PropertyMap map = ojUtil.buildStructuralFeatureMap((Property) EmfActionUtil.getLinkedTypedElement(pin));
				block.addToStatements(signalName + "." + map.setter() + "(" + readPin(operation, block, pin) + ")");
			}
		}
		OJAnnotatedField signal = new OJAnnotatedField(signalName, cm.javaTypePath());
		block.addToLocals(signal);
		signal.setType(cm.javaTypePath());
		signal.setInitExp("new " + node.getSignal().getName() + "()");
		String targetExpression = expressTarget(operation, block);
		// Could be a notification only in which case
		OJPathName handlerPathName = signalMap.handlerTypePath();
		OJAnnotatedField handler = new OJAnnotatedField(signalName + "Handler", handlerPathName);
		block.addToLocals(handler);
		handler.setInitExp("new " + handlerPathName.getLast() + "(" + signalName + ",false)");
		operation.getOwner().addToImports(handlerPathName);
		block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + "," + signalName + "Handler))");
		Classifier targetType = getLibrary().getTargetType(node);
		if(targetType instanceof BehavioredClassifier){
			BehavioredClassifier target = (BehavioredClassifier) targetType;
			if(EmfClassifierUtil.isNotification(node.getSignal()) && isNotificationReceiver(target)){
				if(EmfActionUtil.getFromExpression(node) != null){
					OpaqueExpressionContext fromExp = getLibrary().getOclExpressionContext(EmfActionUtil.getFromExpression(node));
					if(!fromExp.hasErrors()){
						block.addToStatements(handler.getName() + ".setFrom(" + valueSpecificationUtil.expressOcl(fromExp, operation, null) + ")");
					}
				}
				if(EmfActionUtil.getCcExpression(node) != null){
					block.addToStatements(handler.getName() + ".setCc("
							+ expressDestination(operation, EmfActionUtil.getCcExpression(node)) + ")");
				}
				if(EmfActionUtil.getBccExpression(node) != null){
					block.addToStatements(handler.getName() + ".setBcc("
							+ expressDestination(operation, EmfActionUtil.getBccExpression(node)) + ")");
				}
			}
		}
	}
	protected String expressTarget(OJAnnotatedOperation operationContext,OJBlock block){
		String expression = null;
		if(node.getInPartitions().size() == 1){
			if(node.getInPartitions().get(0).getRepresents() instanceof Property){
				expression = actionMap.targetMap().getter() + "()";
			}else if(node.getInPartitions().get(0).getRepresents() instanceof Classifier){
				expression = "NOT IMPLEMENTED";
				// TODO use group assignment here
			}
		}else if(node.getTarget() != null){
			expression = readPin(operationContext, block, node.getTarget());
		}else{
			expression = "this";
		}
		// NB!!! signals are always sent to a collection of targets
		if(actionMap.targetMap().isOne()){
			OJPathName copy = ojUtil.utilPackagePath(EmfElementFinder.getRootObject(node)).getCopy();
			copy.addToNames("Stdlib");
			operationContext.getOwner().addToImports(copy);
			return "Stdlib.objectAsSet(" + expression + ")";
		}else if(!actionMap.targetMap().isUnique()){
			return "new HashSet<INotificationReceiver>(" + expression + ")";
		}else{
			return expression;
		}
	}
	protected String expressDestination(OJAnnotatedOperation operation,OpaqueExpression fromExpression){
		OpaqueExpressionContext oclExpressionContext = getLibrary().getOclExpressionContext(fromExpression);
		if(!oclExpressionContext.hasErrors()){
			operation.getOwner().addToImports("java.util.HashSet");
			operation.getOwner().addToImports(INotificationReceiver.class.getName());
			if(oclExpressionContext.getExpression().getType() instanceof CollectionType){
				return "new HashSet<INotificationReceiver>("+valueSpecificationUtil.expressOcl(oclExpressionContext, operation, null) +")";
			}else{
				OJPathName copy = ojUtil.utilPackagePath(EmfElementFinder.getRootObject(node)).getCopy();
				copy.addToNames("Stdlib");
				operation.getOwner().addToImports(copy);
				return "new HashSet<INotificationReceiver>(Stdlib.objectAsSet(" + valueSpecificationUtil.expressOcl(oclExpressionContext, operation, null) + "))";
			}
		}else{
			return "";
		}
	}
	private boolean isNotificationReceiver(BehavioredClassifier target){
		if(StereotypesHelper.hasStereotype(target, StereotypeNames.BUSINESS_COMPONENT, StereotypeNames.BUSINESS_ROLE,
				StereotypeNames.BUSINESS_SERVICE) || EmfClassifierUtil.conformsTo(target, getLibrary().getPersonNode())){
			for(Property p:getLibrary().getEffectiveAttributes(target)){
				if(EmfClassifierUtil.conformsTo((Classifier) p.getType(), getLibrary().getEmailAddressType())){
					return true;
				}
			}
		}
		return false;
	}
}
