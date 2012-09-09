package org.opaeum.javageneration.bpm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfSignalUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.Java6ModelGenerator;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.event.IChangeEventHandler;
import org.opaeum.runtime.event.ISignalEventHandler;
import org.opaeum.runtime.event.ITimeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.textmetamodel.CharArrayTextSource;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

@StepDependency(after = Java6ModelGenerator.class,phase = JavaTransformationPhase.class)
public class EventHandlerImplementor extends AbstractJavaProducingVisitor{
	private static final String PROPERTY_ID_SWITCH = "propertyIdSwitch";
	@VisitBefore
	public void visitSignal(Signal s){
		SignalMap map = ojUtil.buildSignalMap(s);
		if(EmfElementUtil.isMarkedForDeletion(s)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, oldSignalHolderName(s));
		}else{
			if(ojUtil.requiresJavaRename(s)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, oldSignalHolderName(s));
			}
			List<? extends TypedElement> effectiveAttributes = getLibrary().getEffectiveAttributes(s);
			OJPathName handlerTypePath = map.handlerTypePath();
			OJAnnotatedClass handler = new OJAnnotatedClass(handlerTypePath.getLast());
			handler.getDefaultConstructor();
			OJConstructor constr = new OJConstructor();
			constr.addParam("signal", ojUtil.classifierPathname(s));
			constr.getBody().addToStatements("this.signal=signal");
			constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000)");
			handler.addToConstructors(constr);
			handler.addToImplementedInterfaces(new OJPathName(ISignalEventHandler.class.getName()));
			handler.addToImports("org.opaeum.runtime.environment.Environment");
			findOrCreatePackage(handlerTypePath.getHead()).addToClasses(handler);
			createTextPath(handler, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			OJPathName c = ojUtil.classifierPathname(s);
			OJAnnotatedOperation marshall = buildMarshall(s, "signal", effectiveAttributes, false);
			handler.addToOperations(marshall);
			if(EmfClassifierUtil.isNotification(s)){
				Stereotype st = StereotypesHelper.getStereotype(s, StereotypeNames.NOTIFICATION);
				String template = (String) s.getValue(st, "template");
				if(template != null && template.trim().length() > 0){
					List<String> names = new ArrayList<String>(handler.getPathName().getHead().getNames());
					names.add(map.javaType() + "Default.ftl");
					TextFile templateFile = createTextPath(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, names);
					templateFile.setTextSource(new CharArrayTextSource(template.toCharArray()));
				}
				handler.addToImplementedInterfaces(new OJPathName("org.opaeum.runtime.event.INotificationHandler"));
				marshall.getBody().addToStatements("result.add(new PropertyValue(-20l, Value.valueOf(from)))");
				OJPathName setOfReceiver = new OJPathName("java.util.Set");
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
			if(EmfClassifierUtil.isNotification(s)){
				OJForStatement ps = (OJForStatement) unmarshall.getBody().findStatementRecursive(PROPERTY_ID_SWITCH);
				ps.getBody().addToStatements(
						new OJIfStatement("p.getId()==-20l", "this.from=(INotificationReceiver)Value.valueOf(p.getValue(),persistence)"));
				ps.getBody().addToStatements(
						new OJIfStatement("p.getId()==-21l", "this.cc=(Set<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
				ps.getBody().addToStatements(
						new OJIfStatement("p.getId()==-22l", "this.bcc=(Set<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
				ps.getBody().addToStatements(
						new OJIfStatement("p.getId()==-23l", "this.to=(Set<INotificationReceiver>)Value.valueOf(p.getValue(),persistence)"));
			}
			addIsEvent(handler, constr, marshall, unmarshall);
			OJAnnotatedField result = new OJAnnotatedField("signal", c);
			handler.addToFields(result);
			result.setInitExp("new " + c.getLast() + "()");
			handler.addToOperations(unmarshall);
			addCommonMethods(s, handler);
			Integer listenerPoolSize = EmfSignalUtil.getListenerPoolSize(s);
			addGetConsumerPoolSize(handler, listenerPoolSize);
			OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
			handleOn.addParam("targets", new OJPathName("Object"));
			handler.addToOperations(handleOn);
			handleOn.initializeResultVariable("false");
			handler.addToImports(new OJPathName(IActiveObject.class.getName()));
			handler.addToImports(map.receiverContractTypePath());
			// NB!!! signals are always sent to a collection of targets
			OJForStatement forEachTarget = new OJForStatement("target", new OJPathName("Object"), "(Collection<?>)targets");
			handleOn.getBody().addToStatements(forEachTarget);
			OJIfStatement hasReceptionsOrTrigger = new OJIfStatement("target instanceof " + map.receiverContractTypePath().getLast());
			forEachTarget.getBody().addToStatements(hasReceptionsOrTrigger);
			OJIfStatement ifIsEvent = new OJIfStatement("isEvent", "result |=((" + map.receiverContractTypePath().getLast() + ")target)."
					+ map.eventConsumerMethodName() + "(signal)");
			hasReceptionsOrTrigger.getThenPart().addToStatements(ifIsEvent);
			ifIsEvent.setElsePart(new OJBlock());
			ifIsEvent.getElsePart().addToStatements(
					"((" + map.receiverContractTypePath().getLast() + ")target)." + map.receiveMethodName() + "(signal)");
			ifIsEvent.getElsePart().addToStatements("result = true");
			OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
			handler.addToOperations(scheduleNextOccurrence);
			handler.addToImports(new OJPathName(Date.class.getName()));
			scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24)");
		}
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
	private OJPathName oldSignalHolderName(Signal s){
		return new OJPathName(ojUtil.getOldClassifierPathname(s) + "Handler");
	}
	@VisitBefore(matchSubclasses = true)
	public void visitTriggerContainer(Behavior c){
		if(c.getName().equals("ApplyUserWorkspaceTask")){
			System.out.println();
		}
		ResponsibilityDefinition rd = getLibrary().getResponsibilityDefinition(c, StereotypeNames.STANDALONE_SCREENFLOW_TASK,
				StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
		implementDeadlinesAndEscalations(rd);
		for(Event event:EmfEventUtil.getEventsInScopeForClassAsBehavior(c)){
			if(event instanceof ChangeEvent){
				visitChangeEvent((ChangeEvent) event);
			}else if(event instanceof TimeEvent){
				visitTimer((TimeEvent) event);
			}
		}
	}
	protected void implementDeadlinesAndEscalations(ResponsibilityDefinition rd){
		for(TimeEvent timeEvent:rd.getDeadlines()){
			visitTimer(timeEvent);
		}
		for(Constraint constraint:rd.getConditionEscalations()){
			visitChangeEvent(constraint);
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	private void visitChangeEvent(NamedElement e){
		OJPathName handlerPathName = eventUtil.handlerPathName(e);
		OJPackage pkg = findOrCreatePackage(handlerPathName.getHead());
		OJAnnotatedClass ojClass = new OJAnnotatedClass(handlerPathName.getLast());
		ojClass.addToImplementedInterfaces(new OJPathName(IChangeEventHandler.class.getName()));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addNodeId(ojClass);
		addCommonMethods(e, ojClass);
		OJAnnotatedOperation marshall = buildMarshall(null, "this", new ArrayList<TypedElement>(), true);
		ojClass.addToOperations(marshall);
		OJAnnotatedOperation unMarshall = buildUnmarshall(null, "this", new ArrayList<TypedElement>(), true);
		ojClass.addToOperations(unMarshall);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		ojClass.addToOperations(handleOn);
		handleOn.addParam("object", new OJPathName("Object"));
		OJPathName eventTargetPath = ojUtil.classifierPathname(getLibrary().getEventGeneratingClassifier(e));
		ojClass.addToImports(eventTargetPath);
		OJAnnotatedField target = new OJAnnotatedField("target", eventTargetPath);
		target.setInitExp("(" + eventTargetPath.getLast() + ")object");
		handleOn.getBody().addToLocals(target);
		handleOn.getBody().addToStatements(
				new OJIfStatement("target." + EventUtil.evaluatorName(e) + "()", "return target." + eventUtil.getEventConsumerName(e)
						+ "(returnInfo.getValue(persistence)"));
		handleOn.getBody().addToStatements("return false");
		addGetConsumerPoolSize(ojClass, 5);
		OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
		ojClass.addToOperations(scheduleNextOccurrence);
		scheduleNextOccurrence.addParam("object", new OJPathName("Object"));
		ojClass.addToImports(new OJPathName(Date.class.getName()));
		scheduleNextOccurrence.getBody().addToStatements(
				"return ((" + eventTargetPath.getLast() + ")object)." + EventUtil.intervalCalculatorName(e) + "()");
	}
	private void visitTimer(TimeEvent e){
		OJPathName handlerPathName = eventUtil.handlerPathName(e);
		OJPackage pkg = findOrCreatePackage(handlerPathName.getHead());
		OJAnnotatedClass ojClass = new OJAnnotatedClass(handlerPathName.getLast());
		ojClass.addToImplementedInterfaces(new OJPathName(ITimeEventHandler.class.getName()));
		ojClass.getDefaultConstructor();
		OJConstructor constr = new OJConstructor();
		ojClass.addToConstructors(constr);
		constr.addParam("time", new OJPathName("java.util.Date"));
		constr.getBody().addToStatements("this.firstOccurrenceScheduledFor=time");
		constr.addParam("returnInfo", BpmUtil.ITOKEN);
		constr.getBody().addToStatements("this.returnInfo=returnInfo");
		addNodeId(ojClass);
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addCommonMethods(e, ojClass);
		OJAnnotatedOperation marshall = buildMarshall(null, "this", new ArrayList<TypedElement>(), true);
		ojClass.addToOperations(marshall);
		OJAnnotatedOperation unMarshall = buildUnmarshall(null, "this", new ArrayList<TypedElement>(), true);
		ojClass.addToOperations(unMarshall);
		OJAnnotatedOperation handleOn = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
		ojClass.addToOperations(handleOn);
		handleOn.addParam("object", new OJPathName("Object"));
		OJPathName behaviorPath = ojUtil.classifierPathname(getLibrary().getEventGeneratingClassifier(e));
		ojClass.addToImports(behaviorPath);
		OJAnnotatedField target = new OJAnnotatedField("target", behaviorPath);
		target.setInitExp("(" + behaviorPath.getLast() + ")object");
		handleOn.getBody().addToLocals(target);
		handleOn.getBody().addToStatements("return target." + eventUtil.getEventConsumerName(e) + "(returnInfo,firstOccurrenceScheduledFor)");
		addGetConsumerPoolSize(ojClass, 5);
		OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(Date.class.getName()));
		ojClass.addToOperations(scheduleNextOccurrence);
		ojClass.addToImports(new OJPathName(Date.class.getName()));
		scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24*10)");
	}
	private void addNodeId(OJAnnotatedClass ojClass){
		ojClass.getDefaultConstructor();
		ojClass.addToFields(new OJAnnotatedField("returnInfo", BpmUtil.ITOKEN));
	}
	private void addCommonMethods(NamedElement e,OJAnnotatedClass ojClass){
		OJAnnotatedOperation getHandlerUuid = new OJAnnotatedOperation("getHandlerUuid", new OJPathName("String"));
		getHandlerUuid.getBody().addToStatements("return \"" + EmfWorkspace.getId(e) + "\"");
		ojClass.addToOperations(getHandlerUuid);
		OJAnnotatedOperation getFirstOccurrence = new OJAnnotatedOperation("getFirstOccurrenceScheduledFor", new OJPathName("java.util.Date"));
		ojClass.addToFields(new OJAnnotatedField("firstOccurrenceScheduledFor", new OJPathName("java.util.Date")));
		getFirstOccurrence.getBody().addToStatements("return this.firstOccurrenceScheduledFor");
		ojClass.addToOperations(getFirstOccurrence);
		OJAnnotatedOperation getQueueName = new OJAnnotatedOperation("getQueueName", new OJPathName("String"));
		ojClass.addToOperations(getQueueName);
		getQueueName.getBody().addToStatements("return \"" + e.getQualifiedName() + "\"");
		ojClass.addToImports(new OJPathName("java.util.List"));
		OJPathName propertyValuePath = new OJPathName(PropertyValue.class.getName());
		ojClass.addToImports(propertyValuePath.getCopy());
		new OJPathName("java.util.List").addToElementTypes(propertyValuePath);
		ojClass.addToImports(new OJPathName("java.util.ArrayList"));
		ojClass.addToImports(new OJPathName(Value.class.getName()));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation o){
		// TODO implement async handler for behaviors
		if(ojUtil.hasOJClass((Classifier) o.getOwner()) && !o.isQuery()){
			if(EmfElementUtil.isMarkedForDeletion(o)){
				String qualifiedJavaName = ojUtil.getOldClassifierPathname(o).toJavaString();
				qualifiedJavaName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf("."));
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(qualifiedJavaName.toLowerCase() + "." + getOldInvokerName(o)));
			}else{
				OperationMap map = ojUtil.buildOperationMap(o);
				if(ojUtil.requiresJavaRename(o)){
					String qualifiedJavaName = ojUtil.getOldClassifierPathname(o).toJavaString();
					qualifiedJavaName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf("."));
					deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(qualifiedJavaName.toLowerCase() + "."
							+ getOldInvokerName(o)));
				}
				OJPathName handlerPathName = map.eventHandlerPath();
				OJAnnotatedClass handler = new OJAnnotatedClass(handlerPathName.getLast());
				handler.addToImplementedInterfaces(new OJPathName(ICallEventHandler.class.getName()));
				findOrCreatePackage(handlerPathName.getHead()).addToClasses(handler);
				handler.getDefaultConstructor();
				for(TypedElement p:(List<? extends TypedElement>) o.getOwnedParameters()){
					PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
					OJAnnotatedField field = new OJAnnotatedField(m.fieldname(), m.javaTypePath());
					handler.addToFields(field);
					OJAnnotatedOperation getter = new OJAnnotatedOperation(m.getter(), m.javaTypePath());
					getter.getBody().addToStatements("return this." + m.fieldname());
					handler.addToOperations(getter);
					AttributeImplementor.addPropertyMetaInfo(map.getPreConditions(), getter, m.getProperty(), getLibrary());
					OJAnnotatedOperation setter = new OJAnnotatedOperation(m.setter());
					setter.addParam(m.fieldname(), m.javaTypePath());
					setter.getBody().addToStatements("this." + m.fieldname() + "=" + m.fieldname());
					handler.addToOperations(setter);
				}
				for(Constraint c:map.getPreConditions()){
					//TODO Reevaluate this - the ocl/java context may not be valid
					//TODO remember lookup operations too
					OJAnnotatedOperation oper = new OJAnnotatedOperation(getter(c));
					oper.setReturnType(new OJPathName("boolean"));
					if(c.getSpecification() instanceof OpaqueExpression){
						OpaqueExpression oe = (OpaqueExpression) c.getSpecification();
						OpaqueExpressionContext oclExpressionContext = getLibrary().getOclExpressionContext(oe);
						if(!oclExpressionContext.hasErrors()){
							ValueSpecificationUtil.addExtendedKeywords(oper, oclExpressionContext);
							oper.initializeResultVariable("false");
							ExpressionCreator ec = new ExpressionCreator(ojUtil, handler);
							oper.getBody().addToStatements("result = " + ec.makeExpression(oclExpressionContext, false, new ArrayList<OJParameter>()));
						}
					}
					handler.addToOperations(oper);
				}
				List<? extends Parameter> args = map.getArgumentParameters();
				handler.getDefaultConstructor();
				OJConstructor argConstr = new OJConstructor();
				handler.addToConstructors(argConstr);
				argConstr.getBody().addToStatements("this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000)");
				for(TypedElement p:(List<? extends TypedElement>) args){
					PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
					argConstr.addParam(m.fieldname(), m.javaTypePath());
					argConstr.getBody().addToStatements(m.setter() + "(" + m.fieldname() + ")");
				}
				createTextPath(handler, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
				OJAnnotatedOperation marshall = buildMarshall((Classifier) o.getOwner(), "this",
						(List<? extends TypedElement>) o.getOwnedParameters(), false);
				handler.addToOperations(marshall);
				OJAnnotatedOperation unmarshall = buildUnmarshall((Classifier) o.getOwner(), "this",
						(List<? extends TypedElement>) o.getOwnedParameters(), false);
				handler.addToOperations(unmarshall);
				addIsEvent(handler, argConstr, marshall, unmarshall);
				addCommonMethods(o, handler);
				OJAnnotatedOperation invoke = new OJAnnotatedOperation("handleOn", new OJPathName("boolean"));
				invoke.addParam("t", new OJPathName("Object"));
				OJPathName targetPathName = ojUtil.classifierPathname((Classifier) o.getOwner());
				handler.addToImports(targetPathName);
				OJAnnotatedField target = new OJAnnotatedField("target", targetPathName);
				invoke.getBody().addToLocals(target);
				target.setInitExp("(" + targetPathName.getLast() + ")t");
				handler.addToOperations(invoke);
				OJIfStatement ifEvent = new OJIfStatement("isEvent", "return target." + map.eventConsumerMethodName() + "(" + argumentString(map)
						+ ")");
				invoke.getBody().addToStatements(ifEvent);
				ifEvent.setElsePart(new OJBlock());
				String arg1 = "";
				if(map.isLongRunning()){
					// Async call, returninfo irrelevant
					if(map.getArgumentParameters().size() > 0){
						arg1 = "null,";
					}else{
						arg1 = "null";
					}
				}
				String call = "target." + map.javaOperName() + "(" + arg1 + argumentString(map) + ")";
				manageInvocation(o, map, invoke, ifEvent.getElsePart(), call);
				ifEvent.getElsePart().addToStatements("return true");
				addGetConsumerPoolSize(handler, 5);
				OJAnnotatedOperation scheduleNextOccurrence = new OJAnnotatedOperation("scheduleNextOccurrence", new OJPathName(
						Date.class.getName()));
				handler.addToOperations(scheduleNextOccurrence);
				handler.addToImports(new OJPathName(Date.class.getName()));
				scheduleNextOccurrence.getBody().addToStatements("return new Date(System.currentTimeMillis() + 1000*60*60*24*10)");
				ResponsibilityDefinition rd = getLibrary().getResponsibilityDefinition(o, StereotypeNames.RESPONSIBILITY);
				implementDeadlinesAndEscalations(rd);
			}
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
		marshall.getBody().addToStatements(Math.max(0, marshall.getBody().getStatements().size() - 1),
				"result.add(new PropertyValue(-6l, Value.valueOf(isEvent)))");
	}
	private void addIsEventUnmarshall(OJAnnotatedOperation unmarshall){
		OJForStatement sst = (OJForStatement) unmarshall.getBody().findStatementRecursive(PROPERTY_ID_SWITCH);
		OJIfStatement sc = new OJIfStatement("p.getId()==-6l", "this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence)");
		sst.getBody().addToStatements(sc);
	}
	private void manageInvocation(Operation oper,OperationMap map,OJAnnotatedOperation invoke,OJBlock b,String call){
		if(map.hasMessageStructure()){
			OJAnnotatedField result = new OJAnnotatedField("result", ojUtil.classifierPathname(getLibrary().getMessageStructure(oper)));
			result.setInitExp(call);
			b.addToLocals(result);
			for(Parameter p:(List<? extends Parameter>) map.getResultParameters()){
				PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
				b.addToStatements(m.setter() + "(result." + m.getter() + "())");
			}
		}else if(map.getReturnParameter() != null){
			PropertyMap m = ojUtil.buildStructuralFeatureMap(map.getReturnParameter());
			b.addToStatements(m.setter() + "(" + call + ")");
		}else{
			b.addToStatements(call);
		}
	}
	private String argumentString(OperationMap o){
		StringBuilder sb = new StringBuilder();
		for(TypedElement p:(List<? extends TypedElement>) o.getArgumentParameters()){
			PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
			sb.append(m.getter());
			sb.append("(),");
		}
		if(sb.length() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}
	private String getOldInvokerName(Operation o){
		ojUtil.getOldClassifierPathname(o).getLast();
		return ojUtil.getOldClassifierPathname(o).getLast() + "Handler" + EmfWorkspace.getOpaeumId(o);
	}
	private OJAnnotatedOperation buildMarshall(Classifier parent,String target,Collection<? extends TypedElement> e,boolean includeReturnInfo){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation marshall = new OJAnnotatedOperation("marshall", collectionOfPropertyValues);
		OJBlock blo = marshall.getBody();
		marshall.initializeResultVariable("new ArrayList<PropertyValue>()");
		for(TypedElement p:e){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
			blo.addToStatements("result.add(new PropertyValue(" + EmfWorkspace.getOpaeumId(p) + "l, Value.valueOf(" + target + "." + map.getter()
					+ "())))");
		}
		if(includeReturnInfo){
			marshall.getBody().addToStatements("result.add(new PropertyValue(-5l, Value.valueOf(returnInfo)))");
		}
		return marshall;
	}
	private OJPathName getCollectionOfPropertyValues(){
		OJPathName collectionOfPropertyValues = new OJPathName("java.util.Collection");
		OJPathName propertyValuePath = new OJPathName(org.opaeum.runtime.environment.marshall.PropertyValue.class.getName());
		collectionOfPropertyValues.addToElementTypes(propertyValuePath);
		return collectionOfPropertyValues;
	}
	private OJAnnotatedOperation buildUnmarshall(Classifier parent,String target,Collection<? extends TypedElement> e,
			boolean includeReturnInfo){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation unmarshall = new OJAnnotatedOperation("unmarshall");
		unmarshall.addParam("ps", collectionOfPropertyValues);
		unmarshall.addParam("persistence", new OJPathName(AbstractPersistence.class.getName()));
		OJForStatement foreachProperty = new OJForStatement("p", new OJPathName("PropertyValue"), "ps");
		unmarshall.getBody().addToStatements(foreachProperty);
		foreachProperty.setName(PROPERTY_ID_SWITCH);
		OJBlock elseBlock = foreachProperty.getBody();
		for(TypedElement p:e){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
			String setValue = target + "." + map.setter() + "((" + map.javaType() + ")Value.valueOf(p.getValue(),persistence))";
			OJIfStatement sst = new OJIfStatement("p.getId()==" + EmfWorkspace.getOpaeumId(p) + "l", setValue);
			elseBlock.addToStatements(sst);
			sst.setElsePart(new OJBlock());
			elseBlock = sst.getElsePart();
		}
		if(includeReturnInfo){
			OJIfStatement sc5 = new OJIfStatement("p.getId()==-5", "this.returnInfo=(" + BpmUtil.ITOKEN.getLast()
					+ ")Value.valueOf(p.getValue(),persistence)");
			elseBlock.addToStatements(sc5);
		}
		return unmarshall;
	}
	private String getter(Constraint rule){
		return "is" + NameConverter.capitalize(rule.getName());
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavioredClassifier(BehavioredClassifier s){
		if(ojUtil.hasOJClass(s)){
			OJAnnotatedClass ojClass = findJavaClass(s);
			if(ojClass==null){
				System.out.println();
			}
			EventUtil.addOutgoingEventManagement(ojClass);
			if(s instanceof Activity){
				for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively(((Activity) s))){
					if(n instanceof StructuredActivityNode){
						ojClass = findJavaClass(getLibrary().getMessageStructure(((StructuredActivityNode) n)));
						EventUtil.addOutgoingEventManagement(ojClass);
					}
				}
			}
		}
	}
}
