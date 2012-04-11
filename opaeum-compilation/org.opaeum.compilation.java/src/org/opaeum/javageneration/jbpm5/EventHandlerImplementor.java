package org.opaeum.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.Java6ModelGenerator;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedTimer;
import org.opaeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.event.IChangeEventHandler;
import org.opaeum.runtime.event.ISignalEventHandler;
import org.opaeum.runtime.event.ITimeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(after = Java6ModelGenerator.class,phase = JavaTransformationPhase.class)
public class EventHandlerImplementor extends AbstractJavaProducingVisitor{
	private static final String PROPERTY_ID_SWITCH = "propertyIdSwitch";
	@VisitBefore
	public void visitSignal(INakedSignal s){
		SignalMap map = OJUtil.buildSignalMap(s);
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
		handler.addToImports("org.opaeum.runtime.environment.Environment");
		findOrCreatePackage(handlerTypePath.getHead()).addToClasses(handler);
		createTextPath(handler, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addMarshallingImports(handler);
		OJPathName c = OJUtil.classifierPathname(s);
		OJAnnotatedOperation marshall = buildMarshall(s, "signal", effectiveAttributes, false);
		handler.addToOperations(marshall);
		if(s.isNotification()){
			marshall.getBody().addToStatements("result.add(new PropertyValue(-20l, Value.valueOf(from)))");
			OJPathName setOfReceiver = new OJPathName("java.util.HashSet");
			setOfReceiver.addToElementTypes(new OJPathName("org.opaeum.runtime.event.INotificationReceiver"));
			OJUtil.addTransientProperty(handler, "from", new OJPathName("org.opaeum.runtime.event.INotificationReceiver"), true);
			marshall.getBody().addToStatements("result.add(new PropertyValue(-21l, Value.valueOf(cc)))");
			OJUtil.addTransientProperty(handler, "cc", setOfReceiver, true);
			marshall.getBody().addToStatements("result.add(new PropertyValue(-22l, Value.valueOf(bcc)))");
			OJUtil.addTransientProperty(handler, "bcc", setOfReceiver, true);
			marshall.getBody().addToStatements("result.add(new PropertyValue(-23l, Value.valueOf(to)))");
			OJUtil.addTransientProperty(handler, "to", setOfReceiver, true);
		}
		OJAnnotatedOperation unmarshall = buildUnmarshall(s, "signal", effectiveAttributes, false);
		if(s.isNotification()){
			OJForStatement ps = (OJForStatement) unmarshall.getBody().findStatementRecursive(PROPERTY_ID_SWITCH);
			ps.getBody().addToStatements(new OJIfStatement("p.getId()==-20l", "this.from=(INotificationReceiver)Value.valueOf(p.getValue(),persistence)"));
			ps.getBody().addToStatements(new OJIfStatement("p.getId()==-21l", "this.cc=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
			ps.getBody().addToStatements(new OJIfStatement("p.getId()==-22l", "this.bcc=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
			ps.getBody().addToStatements(new OJIfStatement("p.getId()==-23l", "this.to=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
		}
		addIsEvent(handler, constr, marshall, unmarshall);
		OJAnnotatedField result = new OJAnnotatedField("signal", c);
		handler.addToFields(result);
		result.setInitExp("new " + c.getLast() + "()");
		handler.addToOperations(unmarshall);
		addCommonMethods(s, handler);
		Integer listenerPoolSize = s.getListenerPoolSize();
		addGetConsumerPoolSize(handler, listenerPoolSize);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		handleOn.addParam("targets", new OJPathName("Object"));
		handler.addToOperations(handleOn);
		handler.addToImports(new OJPathName(IActiveObject.class.getName()));
		handler.addToImports(map.receiverContractTypePath());
		OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
		handleOn.getBody().addToLocals(consumed);
		consumed.setInitExp("false");
		// NB!!! signals are always sent to a collection of targets
		OJForStatement forEachTarget = new OJForStatement("target", new OJPathName("Object"), "(Collection<?>)targets");
		handleOn.getBody().addToStatements(forEachTarget);
		OJIfStatement hasReceptionsOrTrigger = new OJIfStatement("target instanceof " + map.receiverContractTypePath().getLast());
		forEachTarget.getBody().addToStatements(hasReceptionsOrTrigger);
		OJIfStatement ifIsEvent = new OJIfStatement("isEvent", "consumed |=((" + map.receiverContractTypePath().getLast() + ")target)." + map.eventConsumerMethodName()
				+ "(signal)");
		hasReceptionsOrTrigger.getThenPart().addToStatements(ifIsEvent);
		ifIsEvent.setElsePart(new OJBlock());
		ifIsEvent.getElsePart().addToStatements("((" + map.receiverContractTypePath().getLast() + ")target)." + map.receiveMethodName() + "(signal)");
		ifIsEvent.getElsePart().addToStatements("consumed = true");
		if(s.isNotification()){
			handleOn.getBody()
					.addToStatements(
							new OJIfStatement("isEvent",
									"Environment.getInstance().getNotificationService().sendNotification(signal, from,(Collection<? extends INotificationReceiver>)targets,cc,bcc)"));
		}
		handleOn.getBody().addToStatements("return consumed");
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
		for(INakedEvent event:c.getEventsInScopeForClassAsBehavior()){
			if(event instanceof INakedChangeEvent){
				visitChangeEvent((INakedChangeEvent) event);
			}else if(event instanceof INakedTimer){
				visitTimer((INakedTimer) event);
			}
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
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
				constr.addParam("timeUnit", new OJPathName("org.opaeum.runtime.domain.TimeUnit"));
				ojClass.addToImports(new OJPathName("org.opaeum.runtime.domain.TimeUnit"));
				// TODO resolve the correct businessCalednar to use
				constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=timeUnit.addTimeTo(new Date(),delay)");
			}else{
				constr.addParam("timeUnit", new OJPathName("org.opaeum.runtime.bpm.businesscalendar.BusinessTimeUnit"));
				ojClass.addToImports(new OJPathName("org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar"));
				ojClass.addToImports(new OJPathName("org.opaeum.runtime.bpm.businesscalendar.BusinessTimeUnit"));
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
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		// TODO implement async handler for behaviors
		if(OJUtil.hasOJClass(o.getOwner()) && !o.isQuery()){
			NakedOperationMap map = OJUtil.buildOperationMap(o);
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
				// TODO move thisS
				AttributeImplementor.addPropertyMetaInfo(o.getOwner(), getter, m.getProperty());
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
				argConstr.addParam(m.fieldname(), m.javaTypePath());
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
			String arg1 = "";
			if(o.isLongRunning()){
				if(o.getArgumentParameters().size() > 0){
					arg1 = "null,";
				}else{
					arg1 = "null";
				}
			}
			String call = "target." + map.javaOperName() + "(" + arg1 + argumentString(o) + ")";
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
		OJUtil.addPersistentProperty(handler, "isEvent", new OJPathName("boolean"), true);
		addIsEventMarshal(marshall);
		addIsEventUnmarshall(unmarshall);
		argConstr.addParam("isEvent", new OJPathName("boolean"));
		argConstr.getBody().addToStatements("this.isEvent=isEvent");
	}
	private void addIsEventMarshal(OJAnnotatedOperation marshall){
		marshall.getBody().addToStatements(Math.max(0,marshall.getBody().getStatements().size() - 1), "result.add(new PropertyValue(-6l, Value.valueOf(isEvent)))");
	}
	private void addIsEventUnmarshall(OJAnnotatedOperation unmarshall){
		OJForStatement sst = (OJForStatement) unmarshall.getBody().findStatementRecursive(PROPERTY_ID_SWITCH);
		OJIfStatement sc = new OJIfStatement("p.getId()==-6l", "this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence)");
		sst.getBody().addToStatements(sc);
	}
	private void manageInvocation(INakedOperation o,OJAnnotatedOperation invoke,OJBlock b,String call){
		if(BehaviorUtil.hasExecutionInstance(o)){
			OJAnnotatedField result = new OJAnnotatedField("result", OJUtil.classifierPathname(o.getMessageStructure()));
			result.setInitExp(call);
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
		return o.getMappingInfo().getOldJavaName().getCapped() + "Handler" + o.getMappingInfo().getOpaeumId();
	}
	private void addMarshallingImports(OJAnnotatedClass marshaller){
	}
	private OJAnnotatedOperation buildMarshall(INakedClassifier parent,String target,Collection<? extends INakedTypedElement> e,boolean includeNodeId){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation marshall = new OJAnnotatedOperation("marshall", collectionOfPropertyValues);
		OJBlock blo = marshall.getBody();
		marshall.initializeResultVariable("new ArrayList<PropertyValue>()");
		for(INakedTypedElement p:e){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parent, p);
			blo.addToStatements("result.add(new PropertyValue(" + p.getMappingInfo().getOpaeumId() + "l, Value.valueOf(" + target + "." + map.getter() + "())))");
		}
		if(includeNodeId){
			marshall.getBody().addToStatements("result.add(new PropertyValue(-5l, Value.valueOf(nodeId)))");
		}
		return marshall;
	}
	private OJPathName getCollectionOfPropertyValues(){
		OJPathName collectionOfPropertyValues = new OJPathName("java.util.Collection");
		OJPathName propertyValuePath = new OJPathName(org.opaeum.runtime.environment.marshall.PropertyValue.class.getName());
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
		foreachProperty.setName(PROPERTY_ID_SWITCH);
		OJBlock elseBlock = foreachProperty.getBody();
		for(INakedTypedElement p:e){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parent, p);
			String setValue = target + "." + map.setter() + "((" + map.javaType() + ")Value.valueOf(p.getValue(),persistence))";
			OJIfStatement sst = new OJIfStatement("p.getId()==" + p.getMappingInfo().getOpaeumId() + "l", setValue);
			elseBlock.addToStatements(sst);
			sst.setElsePart(new OJBlock());
			elseBlock = sst.getElsePart();
		}
		if(includeNodeId){
			OJIfStatement sc5 = new OJIfStatement("p.getId()==-5", "this.nodeId=(String)Value.valueOf(p.getValue(),persistence)");
			elseBlock.addToStatements(sc5);
		}
		return unmarshall;
	}
}
