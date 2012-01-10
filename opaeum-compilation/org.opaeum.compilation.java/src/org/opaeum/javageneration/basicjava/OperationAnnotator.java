package org.opaeum.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,SuperTypeGenerator.class
},after = {
		AttributeImplementor.class,SuperTypeGenerator.class
})
public class OperationAnnotator extends StereotypeAnnotator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehaviors(INakedBehavior o){
		if(o.getContext() == null){
			processBehavior(o);
		}
	}
	private void processBehavior(INakedBehavior o){
		if(OJUtil.hasOJClass(o.getContext()) && !o.isClassifierBehavior() && o.getOwnerElement() instanceof INakedClassifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			if(o.getSpecification() == null || !o.getName().equals(o.getSpecification().getName()) || !o.getContext().equals(o.getSpecification().getContext())){
				// if the specification has a different name to to behavior or the specificatoin is in a
				// superclass/interface,
				// we need to create a matching OJOperation
				OJAnnotatedOperation oper = createOperation(o.getContext(), o, findJavaClass(o.getContext()));
				if(BehaviorUtil.hasExecutionInstance(o)){
					NakedClassifierMap cmap = OJUtil.buildClassifierMap(o);
					oper.setReturnType(cmap.javaTypePath());
				}
			}
		}
		if(o.isProcess() && o.getSpecification() == null){
			createCallbackListener(o, o);
		}
		if(o instanceof INakedActivity){
			INakedActivity a = (INakedActivity) o;
			for(INakedActivityNode n:a.getActivityNodesRecursively()){
				if(n instanceof INakedEmbeddedTask){
					visitClass(((INakedEmbeddedTask) n).getMessageStructure());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			Set<INakedOperation> directlyImplementedOperations = c.getDirectlyImplementedOperations();
			for(INakedOperation o:directlyImplementedOperations){
				if(o.getOwner() == c){
					if(BehaviorUtil.hasExecutionInstance(o)){
						OJAnnotatedOperation getter = (OJAnnotatedOperation) findJavaClass(o.getMessageStructure()).getUniqueOperation("getSelf");
						getter.setVisibility(OJVisibilityKind.PRIVATE);
						getter.initializeResultVariable("getContextObject()");
						visitClass(o.getMessageStructure());// why???
					}
					createCallbackListener(o, o.getMessageStructure());
				}
				NakedOperationMap operationMap = OJUtil.buildOperationMap(o);
				OJAnnotatedOperation oper1 = findOrCreateOperation(c, ojClass, operationMap, o.isLongRunning());
				applyStereotypesAsAnnotations((o), oper1);
				if(!o.isQuery()){
					findOrCreateCallEventConsumer(c, ojClass, operationMap);
					findOrCreateEventGenerator(c, ojClass, operationMap);
				}
			}
			if(c instanceof INakedBehavioredClassifier){
				INakedBehavioredClassifier nbc = (INakedBehavioredClassifier) c;
				SortedSet<INakedSignal> signals = new TreeSet<INakedSignal>(new DefaultOpaeumComparator());
				for(INakedReception o:nbc.getDirectlyImplementedReceptions()){
					signals.add(o.getSignal());
				}
				for(INakedEvent e:nbc.getEventsInScopeForClassAsContext()){
					if(e instanceof INakedSignalEvent){
						signals.add(((INakedSignalEvent) e).getSignal());
					}
				}
				for(INakedSignal s:signals){
					SignalMap map = OJUtil.buildSignalMap(s);
					ojClass.addToImplementedInterfaces(map.receiverContractTypePath());
					findOrCreateJavaReception(ojClass, map);
					findOrCreateSignalEventConsumer(nbc, ojClass, map);
					findOrCreateEventGenerator(nbc, ojClass, map);
				}
				for(INakedBehavior b:nbc.getOwnedBehaviors()){
					processBehavior(b);
				}
			}
		}
	}
	public static OJAnnotatedOperation findOrCreateEventGenerator(INakedClassifier c,OJAnnotatedClass ojClass,NakedOperationMap operationMap){
		OJAnnotatedOperation eventGenerator = (OJAnnotatedOperation) ojClass.findOperation(operationMap.eventGeratorMethodName(), operationMap.javaParamTypePaths());
		if(eventGenerator == null){
			eventGenerator = new OJAnnotatedOperation(operationMap.eventGeratorMethodName());
			ojClass.addToOperations(eventGenerator);
			addParameters(c, eventGenerator, operationMap.getParameterOwner().getArgumentParameters());
		}
		return eventGenerator;
	}
	private static OJAnnotatedOperation findOrCreateCallEventConsumer(INakedClassifier c,OJAnnotatedClass ojClass,NakedOperationMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojClass.findOperation(map.eventConsumerMethodName(), map.javaParamTypePaths());
		if(oper == null){
			oper = new OJAnnotatedOperation(map.eventConsumerMethodName(), new OJPathName("boolean"));
			OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
			oper.getBody().addToLocals(consumed);
			consumed.setInitExp("false");
			ojClass.addToOperations(oper);
			addParameters(c, oper, map.getParameterOwner().getArgumentParameters());
			if(map.getParameterOwner() instanceof INakedOperation){
				INakedOperation no = (INakedOperation) map.getParameterOwner();
				if(c != null && c.getSupertype() != null && c.getSupertype().conformsTo(no.getOwner())){
					oper.getBody().addToStatements("consumed=super." + oper.getName() + "(" + delegateParameters(oper) + ")");
				}
			}
			oper.getBody().addToStatements("return consumed");
		}
		return oper;
	}
	private static OJAnnotatedOperation findOrCreateSignalEventConsumer(INakedClassifier c,OJAnnotatedClass ojClass,SignalMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojClass.findOperation(map.eventConsumerMethodName(), Arrays.asList(map.javaTypePath()));
		if(oper == null){
			oper = new OJAnnotatedOperation(map.eventConsumerMethodName(), new OJPathName("boolean"));
			ojClass.addToOperations(oper);
			OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
			oper.getBody().addToLocals(consumed);
			consumed.setInitExp("false");
			oper.addParam("signal", map.javaTypePath());
			if(c != null && c.getSupertype() instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c.getSupertype()).hasReceptionOrTriggerFor(map.getSignal())){
				oper.getBody().addToStatements("consumed=super." + oper.getName() + "(" + delegateParameters(oper) + ")");
			}
			oper.getBody().addToStatements("return consumed");
		}
		return oper;
	}
	public static OJAnnotatedOperation findOrCreateJavaReception(OJAnnotatedClass ojContext,SignalMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.findOperation(map.receiveMethodName(), Arrays.asList(map.javaTypePath()));
		if(oper == null){
			oper = new OJAnnotatedOperation(map.receiveMethodName());
			oper.addParam("signal", map.javaTypePath());
			ojContext.addToOperations(oper);
			oper.getBody().addToStatements(map.eventGeratorMethodName() + "(signal)");
		}
		return oper;
	}
	private OJAnnotatedOperation createOperation(INakedClassifier context,IParameterOwner o,OJAnnotatedClass owner){
		NakedOperationMap operationMap = OJUtil.buildOperationMap(o);
		OJAnnotatedOperation oper = findOrCreateOperation(context, owner, operationMap, o.isLongRunning());
		applyStereotypesAsAnnotations((o), oper);
		return oper;
	}
	public static OJAnnotatedOperation findOrCreateOperation(INakedClassifier context,OJAnnotatedClass owner,NakedOperationMap map,boolean withReturnInfo){
		IParameterOwner o = map.getParameterOwner();
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(map.javaOperName(), map.javaParamTypePathsWithReturnInfo());
		if(oper == null){
			oper = new OJAnnotatedOperation(map.javaOperName());
			owner.addToOperations(oper);
			if(map.hasMessageStructure()){
				oper.setReturnType(map.messageStructurePath());
			}else if(o.getReturnParameter() != null){
				oper.setReturnType(map.javaReturnTypePath());
				owner.addToImports(map.javaReturnTypePath());
			}
			List<? extends INakedParameter> argumentParameters = o.getArgumentParameters();
			if(withReturnInfo){
				oper.addParam("context", Jbpm5Util.getProcessContext());
			}
			addParameters(context, oper, argumentParameters);
			if(!(owner instanceof OJAnnotatedInterface)){
				if(map.hasMessageStructure()){
					oper.initializeResultVariable("new " + map.messageStructurePath().getLast() + "(this)");
					oper.getResultVariable().setType(map.messageStructurePath());
					List<? extends INakedParameter> args = o.getArgumentParameters();
					for(INakedParameter arg:args){
						NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap((INakedClassifier) o.getContext(), arg);
						oper.getBody().addToStatements("result." + argMap.setter() + "(" + argMap.fieldname() + ")");
					}
					if(withReturnInfo){
						oper.getBody().addToStatements("result.setReturnInfo(context)");
					}
					if(o.isLongRunning()){
						OJSimpleStatement executeStatement = new OJSimpleStatement("result.execute()");
						final String EXECUTE_STATEMENT = "executeStatement";
						executeStatement.setName(EXECUTE_STATEMENT);
						oper.getBody().addToStatements(executeStatement);
					}
					if(o instanceof INakedOperation && !((INakedOperation) o).isQuery()){
						oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					}
				}else if(o.getReturnParameter() != null){
					owner.addToImports(map.javaReturnDefaultTypePath());
					oper.initializeResultVariable(map.javaReturnDefaultValue());
					if(o instanceof INakedOperation && !((INakedOperation) o).isQuery()){
						oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					}
				}else if(o instanceof INakedOperation && !((INakedOperation) o).isQuery()){
					oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
				}
			}
			oper.setVisibility(map.javaVisibility());
			OJUtil.addMetaInfo(oper, o);
		}
		return oper;
	}
	private static void addParameters(INakedClassifier context,OJAnnotatedOperation oper,List<? extends INakedParameter> argumentParameters){
		for(INakedParameter elem:argumentParameters){
			OJParameter param = new OJParameter();
			NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(context, elem);
			param.setName(pMap.fieldname());
			param.setType(pMap.javaTypePath());
			oper.addToParameters(param);
			// applyStereotypesAsAnnotations(((INakedElement) elem), param);
			oper.getOwner().addToImports(pMap.javaTypePath());
		}
	}
	public static String delegateParameters(OJOperation ojOperation){
		StringBuilder statement = new StringBuilder();
		Iterator<OJParameter> parms = ojOperation.getParameters().iterator();
		while(parms.hasNext()){
			OJParameter parm = parms.next();
			if(!parm.getType().equals(Jbpm5Util.getProcessContext())){
				statement.append(parm.getName());
				if(parms.hasNext()){
					statement.append(",");
				}
			}
		}
		return statement.toString();
	}
	public static OJAnnotatedOperation findOrCreateEventGenerator(INakedBehavioredClassifier context,OJAnnotatedClass ojClass,SignalMap map){
		OJAnnotatedOperation eventGenerator = (OJAnnotatedOperation) ojClass.findOperation(map.eventGeratorMethodName(), Arrays.asList(map.javaTypePath()));
		if(eventGenerator == null){
			eventGenerator = new OJAnnotatedOperation(map.eventGeratorMethodName());
			eventGenerator.addParam("signal", map.javaDefaultTypePath());
			ojClass.addToOperations(eventGenerator);
		}
		return eventGenerator;
	}
	public static OJAnnotatedOperation findOrCreateEventConsumer(INakedBehavioredClassifier context,OJAnnotatedClass ojContext,IMessageMap map1){
		if(map1 instanceof SignalMap){
			return findOrCreateSignalEventConsumer(context, ojContext, (SignalMap) map1);
		}else{
			return findOrCreateCallEventConsumer(context, ojContext, (NakedOperationMap) map1);
		}
	}
	private void createCallbackListener(IParameterOwner no,IClassifier message){
		if(no.isLongRunning()){
			NakedOperationMap map = OJUtil.buildOperationMap(no);
			if(!map.hasContract()){
				OJAnnotatedInterface listener = new OJAnnotatedInterface(map.callbackListener());
				OJPackage pack = findOrCreatePackage(map.callbackListenerPath().getHead());
				listener.setMyPackage(pack);
				OJAnnotatedOperation callBackOper = new OJAnnotatedOperation(map.callbackOperName());
				callBackOper.addParam("nodeInstance", new OJPathName("String"));
				callBackOper.addParam("completedProcess", map.messageStructurePath());
				listener.addToOperations(callBackOper);
				List<? extends INakedParameter> exceptionParameters = no.getExceptionParameters();
				for(INakedParameter e:exceptionParameters){
					OJAnnotatedOperation exceptionOper = new OJAnnotatedOperation(map.exceptionOperName(e));
					exceptionOper.addParam("nodeInstance", new OJPathName("String"));
					exceptionOper.addParam("failedProcess", map.messageStructurePath());
					listener.addToOperations(exceptionOper);
				}
				if(no instanceof INakedOperation){
					Collection<INakedClassifier> raisedExceptions = ((INakedOperation) no).getRaisedExceptions();
					for(INakedClassifier e:raisedExceptions){
						OJAnnotatedOperation exceptionOper = new OJAnnotatedOperation(map.exceptionOperName(e));
						exceptionOper.addParam("nodeInstance", new OJPathName("String"));
						exceptionOper.addParam("exception", e.getMappingInfo().getJavaName().getAsIs());
						listener.addToImports(e.getMappingInfo().getQualifiedJavaName());
						exceptionOper.addParam("failedProcess", map.messageStructurePath());
						listener.addToOperations(exceptionOper);
					}
				}
				OJAnnotatedOperation unhandledExceptionHandler = new OJAnnotatedOperation(map.unhandledExceptionOperName());
				unhandledExceptionHandler.addParam("nodeInstance", new OJPathName("String"));
				unhandledExceptionHandler.addParam("exception", new OJPathName("Object"));
				unhandledExceptionHandler.addParam("completedProcess", map.messageStructurePath());
				listener.addToOperations(unhandledExceptionHandler);
				super.createTextPath(listener, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
		}
	}
}
