package org.opaeum.javageneration.bpm;

import java.util.Date;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.runtime.domain.IActiveEntity;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class AbstractJavaProcessVisitor extends AbstractBehaviorVisitor{
	public static final OJPathName ABSTRACT_PROCESS_STEP = new OJPathName(IProcessStep.class.getName());
	public static final OJPathName ACTIVE_ENTITY = new OJPathName(IActiveEntity.class.getName());
	protected OperationAnnotator operationAnnotator = new OperationAnnotator();
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		operationAnnotator.initialize(pac, config, textWorkspace, workspace, ojUtil);
	}
	protected OJOperation implementExecute(OJAnnotatedClass ojClass,Classifier oc){
		OJOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		// add executedOn property for sorting purposes
		OJAnnotatedField f = OJUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		if(isPersistent(oc)){
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
			f.putAnnotation(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
					"javax.persistence.TemporalType"), "TIMESTAMP"));
			f.putAnnotation(temporal);
		}
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		if(oc instanceof Behavior && ((Behavior) oc).getPreconditions().size() > 0){
			execute.getBody().addToStatements("evaluatePreConditions()");
			OJUtil.addFailedConstraints(execute);
		}
		return execute;
	}
	protected OJAnnotatedOperation createEventConsumerSignature(Behavior behavior,OJAnnotatedClass activityClass,Event event){
		if(event instanceof MessageEvent){
			IMessageMap map1;
			if(event instanceof CallEvent){
				map1 = ojUtil.buildOperationMap(((CallEvent) event).getOperation());
			}else{
				map1 = ojUtil.buildSignalMap(((SignalEvent) event).getSignal());
			}
			OJAnnotatedOperation listener = operationAnnotator.findOrCreateEventConsumer(behavior, activityClass, map1);
			return listener;
		}else{
			String methodName = eventUtil.getEventConsumerName(event);
			OJAnnotatedOperation listener = (OJAnnotatedOperation) activityClass.getUniqueOperation(methodName);
			if(listener == null){
				listener = new OJAnnotatedOperation(methodName);
				listener.setReturnType(new OJPathName("boolean"));
				activityClass.addToOperations(listener);
				if(event instanceof TimeEvent && EmfTimeUtil.isDeadline((Event) event)){
					Element origin = EmfEventUtil.getDeadlineOrigin((TimeEvent) event);
					OJPathName pn = null;
					if(origin instanceof Operation){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure(((Operation) origin)));
					}else if(origin instanceof OpaqueAction && EmfActionUtil.isSingleScreenTask((ActivityNode) origin)){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure(((OpaqueAction) origin)));
					}else{
						pn = ojUtil.classifierPathname((CallBehaviorAction) origin);
					}
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType(), (CollectionKind) null)
							.javaTypePath());
					listener.addParam("source", pn);
				}else if(event instanceof ChangeEvent){
					listener.addParam("callingToken", BpmUtil.ABSTRACT_TOKEN);
				}else if(event instanceof TimeEvent){
					listener.addParam("callingToken", BpmUtil.ABSTRACT_TOKEN);
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType(), (CollectionKind) null)
							.javaTypePath());
				}
			}
			return listener;
		}
	}
}