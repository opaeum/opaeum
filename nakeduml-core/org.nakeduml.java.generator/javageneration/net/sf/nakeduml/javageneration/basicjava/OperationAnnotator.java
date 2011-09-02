package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.maps.IMessageMap;
import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.maps.SignalMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.IParameterOwner;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,SuperTypeGenerator.class
},after = {
		AttributeImplementor.class,SuperTypeGenerator.class
})
public class OperationAnnotator extends StereotypeAnnotator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior o){
		if(OJUtil.hasOJClass(o.getContext()) && !o.isClassifierBehavior() && o.getOwnerElement() instanceof INakedClassifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			if(o.getSpecification() == null || !o.getName().equals(o.getSpecification().getName()) || !o.getContext().equals(o.getSpecification().getContext())){
				// if the specification has a different name to to behavior or the specificatoin is in a
				// superclass/interface,
				// we need to create a matching OJOperation
				OJAnnotatedOperation oper = createOperation(o.getContext(), o, findJavaClass(o.getContext()));
				if(BehaviorUtil.hasExecutionInstance(o)){
					NakedClassifierMap cmap = new NakedClassifierMap(o);
					oper.setReturnType(cmap.javaTypePath());
				}
			}
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
			for(INakedOperation o:c.getEffectiveOperations()){
				if(o.getOwner() == c){
					if(BehaviorUtil.hasExecutionInstance(o)){
						visitClass(o.getMessageStructure());
					}
				}
				if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
					NakedOperationMap operationMap = new NakedOperationMap(o);
					OJAnnotatedOperation oper1 = findOrCreateOperation(c, ojClass, operationMap, false);
					applyStereotypesAsAnnotations((o), oper1);
					findOrCreateCallEventConsumer(c, ojClass, operationMap);
					findOrCreateEventGenerator(c, ojClass, operationMap);
					if(o.isLongRunning()){
						oper1 = findOrCreateOperation(c, ojClass, operationMap, true);
						applyStereotypesAsAnnotations((o), oper1);
					}
				}
			}
			if(c instanceof INakedBehavioredClassifier){
				INakedBehavioredClassifier nbc = (INakedBehavioredClassifier) c;
				Collection<? extends INakedReception> effectiveReceptions = nbc.getEffectiveReceptions();
				for(INakedReception o:effectiveReceptions){
					if(o.getOwner() == nbc || o.getOwner() instanceof INakedInterface){
						SignalMap map = new SignalMap(o.getSignal());
						ojClass.addToImplementedInterfaces(map.receiverContractTypePath());
						findOrCreateJavaReception(ojClass, map);
						findOrCreateSignalEventConsumer(nbc, ojClass, map);
						findOrCreateEventGenerator(nbc, ojClass, map);
					}
				}
			}
		}
	}
	public static OJAnnotatedOperation findOrCreateEventGenerator(INakedClassifier c,OJAnnotatedClass ojClass,NakedOperationMap operationMap){
		OJAnnotatedOperation eventGenerator = (OJAnnotatedOperation) ojClass.findOperation(operationMap.eventGeratorMethodName(), operationMap.javaParamTypePaths());
		if(eventGenerator == null){
			eventGenerator = new OJAnnotatedOperation(operationMap.eventGeratorMethodName());
			ojClass.addToOperations(eventGenerator);
			addParameters(c, eventGenerator, operationMap.getOperation().getArgumentParameters());
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
			addParameters(c, oper, map.getOperation().getArgumentParameters());
			if(map.getOperation() instanceof INakedOperation){
				INakedOperation no = (INakedOperation) map.getOperation();
				if(c!=null && c.getSupertype() != null && c.getSupertype().conformsTo(no.getOwner())){
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
			if(c!=null && c.getSupertype() instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c.getSupertype()).hasReceptionFor(map.getSignal())){
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
		NakedOperationMap operationMap = new NakedOperationMap(o);
		OJAnnotatedOperation oper = findOrCreateOperation(context, owner, operationMap, false);
		applyStereotypesAsAnnotations((o), oper);
		return oper;
	}
	public static OJAnnotatedOperation findOrCreateOperation(INakedClassifier context,OJAnnotatedClass owner,NakedOperationMap map,boolean withReturnInfo){
		IParameterOwner o = map.getOperation();
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(map.javaOperName(), map.javaParamTypePaths());
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
			addParameters(context, oper, argumentParameters);
			if(!(owner instanceof OJAnnotatedInterface)){
				if(map.hasMessageStructure()){
					OJAnnotatedField result = new OJAnnotatedField("result", map.messageStructurePath());
					result.setInitExp("new " + map.messageStructurePath().getLast() + "(this)");
					oper.getBody().addToLocals(result);
					List<? extends INakedParameter> args = o.getArgumentParameters();
					for(INakedParameter arg:args){
						NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap((INakedClassifier) arg.getOwner().getOwner(), arg);
						oper.getBody().addToStatements("result." + argMap.setter() + "(" + argMap.umlName() + ")");
					}
					if(withReturnInfo){
						oper.getBody().addToStatements("result.setReturnInfo(context)");
					}
					if(map.getOperation() instanceof INakedOperation){
						oper.getBody().addToStatements("result.execute()");
						oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					}
					oper.getBody().addToStatements("return result");
				}else if(o.getReturnParameter() != null){
					owner.addToImports(map.javaReturnDefaultTypePath());
					OJAnnotatedField result = new OJAnnotatedField("result", map.javaReturnDefaultTypePath());
					result.setInitExp(map.javaReturnDefaultValue());
					oper.getBody().addToLocals(result);
					oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					oper.getBody().addToStatements("return result");
				}else{
					oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
				}
			}
			if(withReturnInfo){
				oper.addParam("context", Jbpm5Util.getProcessContext());
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
			param.setName(pMap.umlName());
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
			statement.append(parm.getName());
			if(parms.hasNext()){
				statement.append(",");
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
}
