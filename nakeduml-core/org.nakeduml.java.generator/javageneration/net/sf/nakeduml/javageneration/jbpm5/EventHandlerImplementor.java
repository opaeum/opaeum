package net.sf.nakeduml.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.Java6ModelGenerator;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.maps.SignalMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSwitchCase;
import org.nakeduml.java.metamodel.OJSwitchStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.ICallEventHandler;
import org.nakeduml.runtime.event.IChangeEventHandler;
import org.nakeduml.runtime.event.ISignalEventHandler;
import org.nakeduml.runtime.event.ITimeEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

@StepDependency(after = Java6ModelGenerator.class,phase = JavaTransformationPhase.class)
public class EventHandlerImplementor extends AbstractJavaProducingVisitor{
	private static final String PROPERTY_ID_SWITCH = "propertyIdSwitch";
	@VisitBefore
	public void visitSignal(INakedSignal s){
		SignalMap map = new SignalMap(s);
		if(s.getMappingInfo().requiresJavaRename()){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, oldSignalHolderName(s));
		}
		List<? extends INakedTypedElement> effectiveAttributes = s.getEffectiveAttributes();
		OJPathName handlerTypePath = map.handlerTypePath();
		OJAnnotatedClass handler = new OJAnnotatedClass(handlerTypePath.getLast());
		handler.getDefaultConstructor();
		OJConstructor constr = new OJConstructor();
		constr.addParam("signal", OJUtil.classifierPathname(s));
		constr.getBody().addToStatements("this.signal=signal");
		constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000)");
		handler.addToConstructors(constr);
		handler.addToImplementedInterfaces(new OJPathName(ISignalEventHandler.class.getName()));
		findOrCreatePackage(handlerTypePath.getHead()).addToClasses(handler);
		createTextPath(handler, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addMarshallingImports(handler);
		OJPathName c = OJUtil.classifierPathname(s);
		OJAnnotatedOperation marshall = buildMarshall(s, "signal", effectiveAttributes, false);
		handler.addToOperations(marshall);
		OJAnnotatedOperation unmarshall = buildUnmarshall(s, "signal", effectiveAttributes, false);
		addIsEvent(handler, constr, marshall, unmarshall);
		OJAnnotatedField result = new OJAnnotatedField("signal", c);
		handler.addToFields(result);
		result.setInitExp("new " + c.getLast() + "()");
		handler.addToOperations(unmarshall);
		addCommonMethods(s, handler);
		Integer listenerPoolSize = s.getListenerPoolSize();
		addGetConsumerPoolSize(handler, listenerPoolSize);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		handleOn.addParam("target", new OJPathName("Object"));
		handler.addToOperations(handleOn);
		handler.addToImports(new OJPathName(IActiveObject.class.getName()));
		handler.addToImports(map.receiverContractTypePath());
		OJIfStatement ifIsEvent = new OJIfStatement("isEvent", "return ((" + map.receiverContractTypePath().getLast() + ")target)." + map.eventConsumerMethodName()
				+ "(signal)");
		handleOn.getBody().addToStatements(ifIsEvent);
		ifIsEvent.setElsePart(new OJBlock());
		ifIsEvent.getElsePart().addToStatements("((" + map.receiverContractTypePath().getLast() + ")target)." + map.receiveMethodName() + "(signal)");
		ifIsEvent.getElsePart().addToStatements("return true");
		OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
		handler.addToOperations(scheduleNextOccurrence);
		handler.addToImports(new OJPathName(Date.class.getName()));
		scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24)");
	}
	private void addGetConsumerPoolSize(OJAnnotatedClass marshaller,Integer listenerPoolSize){
		OJAnnotatedOperation getConsumerPoolSize = new OJAnnotatedOperation("getConsumerPoolSize", new OJPathName("int"));
		marshaller.addToOperations(getConsumerPoolSize);
		if(listenerPoolSize == null){
			getConsumerPoolSize.getBody().addToStatements("return 5");
		}else{
			getConsumerPoolSize.getBody().addToStatements("return " + listenerPoolSize);
		}
	}
	private static OJPathName oldSignalHolderName(INakedSignal s){
		return new OJPathName(s.getMappingInfo().getOldQualifiedJavaName() + "Handler");
	}
	@VisitBefore(matchSubclasses = true)
	public void visitTriggerContainer(INakedTriggerContainer c){
		for(INakedEvent event:c.getAllEvents()){
			if(event instanceof INakedChangeEvent){
				visitChangeEvent((INakedChangeEvent) event);
			}else if(event instanceof INakedTimer){
				visitTimer((INakedTimer) event);
			}
		}
	}
	private void visitChangeEvent(INakedChangeEvent e){
		OJPathName handlerPathName = EventUtil.handlerPathName(e);
		OJPackage pkg = findOrCreatePackage(handlerPathName.getHead());
		OJAnnotatedClass ojClass = new OJAnnotatedClass(handlerPathName.getLast());
		ojClass.addToImplementedInterfaces(new OJPathName(IChangeEventHandler.class.getName()));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addNodeId(ojClass);
		addCommonMethods(e, ojClass);
		OJAnnotatedOperation marshall = buildMarshall(null, "this", new ArrayList<INakedTypedElement>(), true);
		ojClass.addToOperations(marshall);
		OJAnnotatedOperation unMarshall = buildUnmarshall(null, "this", new ArrayList<INakedTypedElement>(), true);
		ojClass.addToOperations(unMarshall);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		ojClass.addToOperations(handleOn);
		handleOn.addParam("object", new OJPathName("Object"));
		OJPathName behaviorPath = OJUtil.classifierPathname(e.getBehaviorContext());
		ojClass.addToImports(behaviorPath);
		OJAnnotatedField target = new OJAnnotatedField("target", behaviorPath);
		target.setInitExp("(" + behaviorPath.getLast() + ")object");
		handleOn.getBody().addToLocals(target);
		handleOn.getBody().addToStatements(
				new OJIfStatement("target." + EventUtil.evaluatorName(e) + "()", "return target." + EventUtil.getEventConsumerName(e) + "(nodeId)"));
		handleOn.getBody().addToStatements("return false");
		addGetConsumerPoolSize(ojClass, 5);
		OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
		ojClass.addToOperations(scheduleNextOccurrence);
		ojClass.addToImports(new OJPathName(Date.class.getName()));
		scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*4)");// Every four hours
	}
	private void visitTimer(INakedTimer e){
		OJPathName handlerPathName = EventUtil.handlerPathName(e);
		OJPackage pkg = findOrCreatePackage(handlerPathName.getHead());
		OJAnnotatedClass ojClass = new OJAnnotatedClass(handlerPathName.getLast());
		ojClass.addToImplementedInterfaces(new OJPathName(ITimeEventHandler.class.getName()));
		ojClass.getDefaultConstructor();
		OJConstructor constr = new OJConstructor();
		ojClass.addToConstructors(constr);
		if(e.isRelative()){
			constr.addParam("delay", new OJPathName("int"));
			if(getLibrary().getBusinessRole() == null){
				constr.addParam("timeUnit", new OJPathName("org.nakeduml.runtime.domain.TimeUnit"));
				ojClass.addToImports(new OJPathName("org.nakeduml.runtime.domain.TimeUnit"));
				// TODO resolve the correct businessCalednar to use
				constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=timeUnit.addTimeTo(new Date(),delay)");
			}else{
				constr.addParam("timeUnit", new OJPathName("org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit"));
				ojClass.addToImports(new OJPathName("org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar"));
				ojClass.addToImports(new OJPathName("org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit"));
				// TODO resolve the correct businessCalednar to use
				constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=BusinessCalendar.getInstance().addTimeTo(new Date(), timeUnit,delay)");
			}
		}else{
			constr.addParam("time", new OJPathName("java.util.Date"));
			constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=time");
		}
		constr.addParam("nodeId", new OJPathName("String"));
		constr.getBody().addToStatements("this.nodeId=nodeId");
		addNodeId(ojClass);
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addCommonMethods(e, ojClass);
		OJAnnotatedOperation marshall = buildMarshall(null, "this", new ArrayList<INakedTypedElement>(), true);
		ojClass.addToOperations(marshall);
		OJAnnotatedOperation unMarshall = buildUnmarshall(null, "this", new ArrayList<INakedTypedElement>(), true);
		ojClass.addToOperations(unMarshall);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		ojClass.addToOperations(handleOn);
		handleOn.addParam("object", new OJPathName("Object"));
		OJPathName behaviorPath = OJUtil.classifierPathname(e.getContext());
		ojClass.addToImports(behaviorPath);
		OJAnnotatedField target = new OJAnnotatedField("target", behaviorPath);
		target.setInitExp("(" + behaviorPath.getLast() + ")object");
		handleOn.getBody().addToLocals(target);
		handleOn.getBody().addToStatements("return target." + EventUtil.getEventConsumerName(e) + "(nodeId,firstOccurrenceScheduledFor)");
		addGetConsumerPoolSize(ojClass, 5);
		OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
		ojClass.addToOperations(scheduleNextOccurrence);
		ojClass.addToImports(new OJPathName(Date.class.getName()));
		scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24*10)");
	}
	private void addNodeId(OJAnnotatedClass ojClass){
		ojClass.getDefaultConstructor();
		OJConstructor constr = new OJConstructor();
		constr.addParam("nodeId", new OJPathName("String"));
		constr.getBody().addToStatements("this.nodeId=nodeId");
		ojClass.addToConstructors(constr);
		ojClass.addToFields(new OJAnnotatedField("nodeId", new OJPathName("String")));
	}
	private void addCommonMethods(INakedElement e,OJAnnotatedClass ojClass){
		OJAnnotatedOperation getHandlerUuid = new OJAnnotatedOperation("getHandlerUuid", new OJPathName("String"));
		getHandlerUuid.getBody().addToStatements("return \"" + e.getMappingInfo().getIdInModel() + "\"");
		ojClass.addToOperations(getHandlerUuid);
		OJAnnotatedOperation getFirstOccurrence = new OJAnnotatedOperation("getFirstOccurrenceScheduledFor", new OJPathName("java.util.Date"));
		ojClass.addToFields(new OJAnnotatedField("firstOccurrenceScheduledFor", new OJPathName("java.util.Date")));
		getFirstOccurrence.getBody().addToStatements("return this.firstOccurrenceScheduledFor");
		ojClass.addToOperations(getFirstOccurrence);
		OJAnnotatedOperation getQueueName = new OJAnnotatedOperation("getQueueName", new OJPathName("String"));
		ojClass.addToOperations(getQueueName);
		getQueueName.getBody().addToStatements("return \"" + e.getMappingInfo().getQualifiedUmlName() + "\"");
		ojClass.addToImports(new OJPathName("java.util.List"));
		OJPathName propertyValuePath = new OJPathName(PropertyValue.class.getName());
		ojClass.addToImports(propertyValuePath.getCopy());
		new OJPathName("java.util.List").addToElementTypes(propertyValuePath);
		ojClass.addToImports(new OJPathName("java.util.ArrayList"));
		ojClass.addToImports(new OJPathName(Value.class.getName()));
	}
	@VisitBefore
	public void visitOperation(INakedOperation o){
		if(OJUtil.hasOJClass(o.getOwner()) && !o.isQuery()){
			NakedOperationMap map = new NakedOperationMap(o);
			if(o.getMappingInfo().requiresJavaRename()){
				String qualifiedJavaName = o.getMappingInfo().getOldQualifiedJavaName();
				qualifiedJavaName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf("."));
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(qualifiedJavaName.toLowerCase() + "." + getOldInvokerName(o)));
			}
			OJPathName handlerPathName = map.eventHandlerPath();
			OJAnnotatedClass handler = new OJAnnotatedClass(handlerPathName.getLast());
			handler.addToImplementedInterfaces(new OJPathName(ICallEventHandler.class.getName()));
			findOrCreatePackage(handlerPathName.getHead()).addToClasses(handler);
			handler.getDefaultConstructor();
			for(INakedTypedElement p:(List<? extends INakedTypedElement>) o.getOwnedParameters()){
				NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
				OJAnnotatedField field = new OJAnnotatedField(m.fieldname(), m.javaTypePath());
				handler.addToFields(field);
				OJAnnotatedOperation getter = new OJAnnotatedOperation(m.getter(), m.javaTypePath());
				getter.getBody().addToStatements("return this." + m.fieldname());
				handler.addToOperations(getter);
				OJAnnotatedOperation setter = new OJAnnotatedOperation(m.setter());
				setter.addParam(m.fieldname(), m.javaTypePath());
				setter.getBody().addToStatements("this." + m.fieldname() + "=" + m.fieldname());
				handler.addToOperations(setter);
			}
			List<? extends INakedParameter> args = o.getArgumentParameters();
			handler.getDefaultConstructor();
			OJConstructor argConstr = new OJConstructor();
			handler.addToConstructors(argConstr);
			argConstr.getBody().addToStatements("this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000)");
			for(INakedTypedElement p:(List<? extends INakedTypedElement>) args){
				NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
				argConstr.addParam(m.fieldname(), m.javaType());
				argConstr.getBody().addToStatements(m.setter() + "(" + m.fieldname() + ")");
			}
			createTextPath(handler, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			addMarshallingImports(handler);
			OJAnnotatedOperation marshall = buildMarshall(o.getOwner(), "this", (List<? extends INakedTypedElement>) o.getOwnedParameters(), false);
			handler.addToOperations(marshall);
			OJAnnotatedOperation unmarshall = buildUnmarshall(o.getOwner(), "this", (List<? extends INakedTypedElement>) o.getOwnedParameters(), false);
			handler.addToOperations(unmarshall);
			addIsEvent(handler, argConstr, marshall, unmarshall);
			addCommonMethods(o, handler);
			OJAnnotatedOperation invoke = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
			invoke.addParam("t", new OJPathName("Object"));
			OJPathName targetPathName = OJUtil.classifierPathname(o.getOwner());
			handler.addToImports(targetPathName);
			OJAnnotatedField target = new OJAnnotatedField("target", targetPathName);
			invoke.getBody().addToLocals(target);
			target.setInitExp("(" + targetPathName.getLast() + ")t");
			handler.addToOperations(invoke);
			OJIfStatement ifEvent = new OJIfStatement("isEvent", "return target." + map.eventConsumerMethodName() + "(" + argumentString(o) + ")");
			invoke.getBody().addToStatements(ifEvent);
			ifEvent.setElsePart(new OJBlock());
			String call = "target." + map.javaOperName() + "(" + argumentString(o) + ")";
			manageInvocation(o, invoke, ifEvent.getElsePart(), call);
			ifEvent.getElsePart().addToStatements("return true");
			addGetConsumerPoolSize(handler, 5);
			OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
			handler.addToOperations(scheduleNextOccurrence);
			handler.addToImports(new OJPathName(Date.class.getName()));
			scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24*10)");
		}
	}
	private void addIsEvent(OJAnnotatedClass handler,OJConstructor argConstr,OJAnnotatedOperation marshall,OJAnnotatedOperation unmarshall){
		OJUtil.addProperty(handler, "isEvent", new OJPathName("boolean"), true);
		addIsEventMarshal(marshall);
		addIsEventUnmarshall(unmarshall);
		argConstr.addParam("isEvent", new OJPathName("boolean"));
		argConstr.getBody().addToStatements("this.isEvent=isEvent");
	}
	private void addIsEventMarshal(OJAnnotatedOperation marshall){
		marshall.getBody().addToStatements(marshall.getBody().getStatements().size() - 1, "result.add(new PropertyValue(-6, Value.valueOf(isEvent)))");
	}
	private void addIsEventUnmarshall(OJAnnotatedOperation unmarshall){
		OJSwitchStatement sst = (OJSwitchStatement) unmarshall.getBody().findStatementRecursive(PROPERTY_ID_SWITCH);
		OJSwitchCase sc = new OJSwitchCase();
		sst.addToCases(sc);
		sc.setLabel("-6");
		sc.getBody().addToStatements("this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence)");
	}
	private void manageInvocation(INakedOperation o,OJAnnotatedOperation invoke,OJBlock b,String call){
		if(BehaviorUtil.hasExecutionInstance(o)){
			OJAnnotatedField result = new OJAnnotatedField("result", OJUtil.classifierPathname(o.getMessageStructure()));
			b.addToLocals(result);
			for(INakedParameter p:(List<? extends INakedParameter>) o.getResultParameters()){
				NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
				b.addToStatements(m.setter() + "(result." + m.getter() + "())");
			}
		}else if(o.getReturnParameter() != null){
			NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), o.getReturnParameter());
			b.addToStatements(m.setter() + "(" + call + ")");
		}else{
			b.addToStatements(call);
		}
	}
	private String argumentString(INakedOperation o){
		StringBuilder sb = new StringBuilder();
		for(INakedTypedElement p:(List<? extends INakedTypedElement>) o.getArgumentParameters()){
			NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
			sb.append(m.getter());
			sb.append("(),");
		}
		if(sb.length() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}
	private String getOldInvokerName(INakedOperation o){
		return o.getMappingInfo().getOldJavaName().getCapped() + "Handler" + o.getMappingInfo().getNakedUmlId();
	}
	private void addMarshallingImports(OJAnnotatedClass marshaller){
	}
	private OJAnnotatedOperation buildMarshall(INakedClassifier parent,String target,Collection<? extends INakedTypedElement> e,boolean includeNodeId){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation marshall = new OJAnnotatedOperation("marshall", collectionOfPropertyValues);
		OJAnnotatedField result = new OJAnnotatedField("result", collectionOfPropertyValues);
		OJBlock blo = marshall.getBody();
		blo.addToLocals(result);
		result.setInitExp("new ArrayList<PropertyValue>()");
		for(INakedTypedElement p:e){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parent, p);
			blo.addToStatements("result.add(new PropertyValue(" + p.getMappingInfo().getNakedUmlId() + ", Value.valueOf(" + target + "." + map.getter() + "())))");
		}
		if(includeNodeId){
			marshall.getBody().addToStatements("result.add(new PropertyValue(-5, Value.valueOf(nodeId)))");
		}
		blo.addToStatements("return result");
		return marshall;
	}
	private OJPathName getCollectionOfPropertyValues(){
		OJPathName collectionOfPropertyValues = new OJPathName("java.util.Collection");
		OJPathName propertyValuePath = new OJPathName(org.nakeduml.runtime.environment.marshall.PropertyValue.class.getName());
		collectionOfPropertyValues.addToElementTypes(propertyValuePath);
		return collectionOfPropertyValues;
	}
	private OJAnnotatedOperation buildUnmarshall(INakedClassifier parent,String target,Collection<? extends INakedTypedElement> e,boolean includeNodeId){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation unmarshall = new OJAnnotatedOperation("unmarshall");
		unmarshall.addParam("ps", collectionOfPropertyValues);
		unmarshall.addParam("persistence", new OJPathName(AbstractPersistence.class.getName()));
		OJForStatement foreachProperty = new OJForStatement("p", new OJPathName("PropertyValue"), "ps");
		unmarshall.getBody().addToStatements(foreachProperty);
		OJSwitchStatement sst = new OJSwitchStatement();
		sst.setName(PROPERTY_ID_SWITCH);
		sst.setCondition("p.getId()");
		foreachProperty.getBody().addToStatements(sst);
		for(INakedTypedElement p:e){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parent, p);
			OJSwitchCase sc = new OJSwitchCase();
			sst.addToCases(sc);
			sc.setLabel("" + p.getMappingInfo().getNakedUmlId());
			sc.getBody().addToStatements(target + "." + map.setter() + "((" + map.javaType() + ")Value.valueOf(p.getValue(),persistence))");
		}
		if(includeNodeId){
			OJSwitchCase sc5 = new OJSwitchCase();
			sc5.setLabel("-5");
			sc5.getBody().addToStatements("this.nodeId=(String)Value.valueOf(p.getValue(),persistence)");
			sst.addToCases(sc5);
		}
		return unmarshall;
	}
}
