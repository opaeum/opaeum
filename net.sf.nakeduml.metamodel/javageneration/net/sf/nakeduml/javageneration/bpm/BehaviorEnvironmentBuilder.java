package net.sf.nakeduml.javageneration.bpm;

import java.util.Iterator;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

public class BehaviorEnvironmentBuilder extends AbstractBehaviorVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(INakedActivity activity){
		if(activity.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			addDelegations(activity);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine c){
		addDelegations(c);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(o.shouldEmulateClass()){
			addDelegations(findJavaClass(new OperationMessageStructureImpl(o)), o.getContext());
		}
	}
	
	private void addDelegation(OJClass activityClass,INakedProperty p){
		StructuralFeatureMap map = new NakedStructuralFeatureMap(p);
		if(p.getName().equals("name")){
			return;
		}else{
			for(OJOperation other:activityClass.getOperations()){
				if(other.getName().equals(map.getter()) && other.getParameters().isEmpty()){
					return;
				}
			}
		}
		if(OJUtil.findOperation(activityClass, map.getter()) == null){
			OJOperation o = new OJAnnotatedOperation();
			o.setName(map.getter());
			o.setReturnType(map.javaTypePath());
			o.getBody().addToStatements("return getContextObject()." + map.getter() + "()");
			activityClass.addToOperations(o);
		}
	}
	private void addDelegation(OJClass activityClass,INakedOperation no){
		NakedOperationMap map = new NakedOperationMap(no);
		OJOperation o = new OJAnnotatedOperation();
		o.setName(map.javaOperName());
		if(shouldReturnMethodStructure(no)){
			o.setReturnType(OJUtil.classifierPathname(no.getMethods().iterator().next()));
		}else if(BehaviorUtil.hasExecutionInstance(no)){
			o.setReturnType(OJUtil.classifierPathname(new OperationMessageStructureImpl(no.getOwner(), no)));
		}else{
			o.setReturnType(map.javaReturnTypePath());
		}
		StringBuilder args = new StringBuilder();
		Iterator<? extends INakedParameter> iter = no.getArgumentParameters().iterator();
		while(iter.hasNext()){
			INakedParameter p = iter.next();
			args.append(p.getName());
			o.addParam(p.getName(), map.javaParamTypePath(p));
			if(iter.hasNext()){
				args.append(",");
			}
		}
		if(o.getReturnType() == null || o.getReturnType().getLast().equals("void")){
			o.getBody().addToStatements("getContextObject()." + map.javaOperName() + "(" + args + ")");
		}else{
			o.getBody().addToStatements("return getContextObject()." + map.javaOperName() + "(" + args + ")");
		}
		activityClass.addToOperations(o);
	}
	private boolean shouldReturnMethodStructure(INakedOperation no){
		if(no.getMethods().size() == 1){
			// Multiple methods cannot be returned,
			// TODO support through behaviour inheritance
			INakedBehavior b = no.getMethods().iterator().next();
			if(BehaviorUtil.hasExecutionInstance(b)){
				return true;
			}
		}
		return false;
	}
	private void addDelegation(OJClass activityClass,INakedBehavior no){
		if(!no.isClassifierBehavior()){
			NakedOperationMap map = new NakedOperationMap(no);
			OJOperation o = new OJAnnotatedOperation();
			o.setName(no.getMappingInfo().getJavaName().toString());
			if(BehaviorUtil.hasExecutionInstance(no)){
				o.setReturnType(OJUtil.classifierPathname(no));
			}else if(no.getReturnParameter() != null){
				o.setReturnType(map.javaReturnTypePath());
			}
			StringBuilder args = new StringBuilder();
			Iterator<? extends INakedParameter> iter = no.getArgumentParameters().iterator();
			while(iter.hasNext()){
				INakedParameter p = iter.next();
				NakedStructuralFeatureMap paramMap = OJUtil.buildStructuralFeatureMap(no, p);
				args.append(p.getName());
				o.addParam(p.getName(), paramMap.javaTypePath());
				if(iter.hasNext()){
					args.append(",");
				}
			}
			if(o.getReturnType() == null || o.getReturnType().getLast().equals("void")){
				o.getBody().addToStatements("getContextObject()." + o.getName() + "(" + args + ")");
			}else{
				o.getBody().addToStatements("return getContextObject()." + o.getName() + "(" + args + ")");
			}
			activityClass.addToOperations(o);
		}
	}
	private void addDelegations(INakedBehavior activity){
		OJClass activityClass = findJavaClass(activity);
		INakedBehavioredClassifier context = activity.getContext();
		if(context != null){
			addDelegations(activityClass, context);
		}
	}
	public void addDelegations(OJClass activityClass, INakedBehavioredClassifier context) {
		for(INakedProperty p:context.getEffectiveAttributes()){
			addDelegation(activityClass, p);
		}
		for(INakedOperation o:context.getEffectiveOperations()){
			addDelegation(activityClass, o);
		}
		for(INakedBehavior o:context.getOwnedBehaviors()){
			if(o.getSpecification() == null){
				addDelegation(activityClass, o);
			}
		}
	}
}
