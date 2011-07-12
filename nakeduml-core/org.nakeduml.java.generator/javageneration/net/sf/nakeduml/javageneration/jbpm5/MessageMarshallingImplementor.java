package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.Java5ModelGenerationStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

import org.nakeduml.environment.AbstractPersistence;
import org.nakeduml.environment.MethodInvoker;
import org.nakeduml.environment.SignalMarshaller;
import org.nakeduml.environment.marshall.PropertyValue;
import org.nakeduml.environment.marshall.Value;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSwitchCase;
import org.nakeduml.java.metamodel.OJSwitchStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedParameter;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;
import org.nakeduml.runtime.domain.AbstractSignal;

@StepDependency(after = Java5ModelGenerationStep.class,phase = JavaTransformationPhase.class)
public class MessageMarshallingImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitSignal(INakedSignal s){
		implementListener(s);
		implementMarshaller(s);
	}
	@VisitBefore
	public void visitOperation(INakedOperation o){
		OJAnnotatedClass i = implementInvoker(o);
		implementListener(o, i.getPathName(), new OJPathName("org.nakeduml.environment.adaptor.AbstractAsyncInvoker"));
	}
	private OJAnnotatedClass implementInvoker(INakedOperation o){
		OJAnnotatedClass invoker = new OJAnnotatedClass(getInvokerName(o));
		invoker.addToImplementedInterfaces(new OJPathName(MethodInvoker.class.getName()));
		findOrCreatePackage(OJUtil.packagePathname(o.getOwner())).addToClasses(invoker);
		invoker.getDefaultConstructor();
		for(INakedTypedElement p:(List<? extends INakedTypedElement>) o.getOwnedParameters()){
			NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
			OJUtil.addProperty(invoker, m.umlName(), m.javaTypePath(), true);
		}
		List<? extends INakedParameter> args = o.getArgumentParameters();
		if(args.size() > 0){
			OJConstructor argConstr = invoker.getDefaultConstructor().getDeepConstructorCopy();
			invoker.addToConstructors(argConstr);
			for(INakedTypedElement p:(List<? extends INakedTypedElement>) args){
				NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), p);
				argConstr.addParam(m.umlName(), m.javaType());
				argConstr.getBody().addToStatements(m.setter() + "(" + m.umlName() + ")");
			}
		}
		createTextPath(invoker, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		addMarshallingImports(invoker);
		invoker.addToOperations(buildMarshall(o.getOwner(), "this", (List<? extends INakedTypedElement>) o.getOwnedParameters()));
		invoker.addToOperations(buildUnmarshall(o.getOwner(), "this", (List<? extends INakedTypedElement>) o.getOwnedParameters()));
		OJAnnotatedOperation getUuid = new OJAnnotatedOperation("getUuid", new OJPathName("String"));
		invoker.addToOperations(getUuid);
		getUuid.getBody().addToStatements("return \"" + o.getMappingInfo().getIdInModel() + "\"");
		OJAnnotatedOperation getQueueName = new OJAnnotatedOperation("getQueueName", new OJPathName("String"));
		invoker.addToOperations(getQueueName);
		invoker.addToImports(OJUtil.classifierPathname(o.getOwner()));
		getQueueName.getBody().addToStatements("return \"" + invoker.getPathName() + "\"");
		OJAnnotatedOperation invoke = new OJAnnotatedOperation("invoke");
		invoke.addParam("t", new OJPathName("Object"));
		OJAnnotatedField target = new OJAnnotatedField("target", OJUtil.classifierPathname(o.getOwner()));
		invoke.getBody().addToLocals(target);
		target.setInitExp("(" + o.getOwner().getMappingInfo().getJavaName() + ")t");
		invoker.addToOperations(invoke);
		String call = "target." + o.getMappingInfo().getJavaName() + "(" + argumentString(o) + ")";
		if(BehaviorUtil.hasExecutionInstance(o)){
			OJBlock b= new OJBlock();
			invoke.getBody().addToStatements(b);
			OJAnnotatedField result = new OJAnnotatedField("result", OJUtil.classifierPathname(o.getMessageStructure(getOclEngine().getOclLibrary())));
			b.addToLocals(result);
			for(INakedParameter p:(List<? extends INakedParameter>) o.getResultParameters()){
				NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(),p);
				b.addToStatements(m.setter() + "(result." + m.getter() + "())");
			}
			
		}else if(o.getReturnParameter() != null){
			NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(o.getOwner(), o.getReturnParameter());
			invoke.getBody().addToStatements(m.setter() + "(" + call + ")");
		}else{
			invoke.getBody().addToStatements(call);
		}
		return invoker;
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
	private String getInvokerName(INakedOperation o){
		return o.getOwner().getMappingInfo().getJavaName().getAsIs() + o.getMappingInfo().getJavaName().getCapped() + o.getMappingInfo().getNakedUmlId() + "Invoker";
	}
	private void implementMarshaller(INakedSignal s){
		List<? extends INakedTypedElement> effectiveAttributes = s.getEffectiveAttributes();
		OJAnnotatedClass marshaller = new OJAnnotatedClass(s.getMappingInfo().getJavaName() + "Marshaller");
		marshaller.addToImplementedInterfaces(new OJPathName(SignalMarshaller.class.getName()));
		findOrCreatePackage(OJUtil.packagePathname(s.getNameSpace())).addToClasses(marshaller);
		createTextPath(marshaller, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		addMarshallingImports(marshaller);
		OJPathName c = OJUtil.classifierPathname(s);
		OJAnnotatedOperation marshall = buildMarshall(s, "signal", effectiveAttributes);
		marshaller.addToOperations(marshall);
		OJAnnotatedField signal = new OJAnnotatedField("signal", c);
		signal.setInitExp("(" + s.getName() + ") s");
		marshall.addParam("s", new OJPathName(AbstractSignal.class.getName()));
		marshall.getBody().addToLocals(signal);
		OJAnnotatedOperation unmarshall = buildUnmarshall(s, "result", effectiveAttributes);
		OJAnnotatedField result = new OJAnnotatedField("result", c);
		unmarshall.getBody().addToLocals(result);
		result.setInitExp("new " + c.getLast() + "()");
		marshaller.addToOperations(unmarshall);
		unmarshall.setReturnType(new OJPathName(AbstractSignal.class.getName()));
		unmarshall.getBody().addToStatements("return result");
	}
	private void addMarshallingImports(OJAnnotatedClass marshaller){
		marshaller.addToImports(new OJPathName("java.util.List"));
		OJPathName propertyValuePath = new OJPathName(PropertyValue.class.getName());
		marshaller.addToImports(propertyValuePath.getCopy());
		new OJPathName("java.util.List").addToElementTypes(propertyValuePath);
		marshaller.addToImports(new OJPathName("java.util.ArrayList"));
		marshaller.addToImports(new OJPathName(Value.class.getName()));
	}
	private OJAnnotatedOperation buildMarshall(INakedClassifier parent,String target,Collection<? extends INakedTypedElement> e){
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
		blo.addToStatements("return result");
		return marshall;
	}
	private OJPathName getCollectionOfPropertyValues(){
		OJPathName collectionOfPropertyValues = new OJPathName("java.util.Collection");
		OJPathName propertyValuePath = new OJPathName(PropertyValue.class.getName());
		collectionOfPropertyValues.addToElementTypes(propertyValuePath);
		return collectionOfPropertyValues;
	}
	private OJAnnotatedOperation buildUnmarshall(INakedClassifier parent,String target,Collection<? extends INakedTypedElement> e){
		OJPathName collectionOfPropertyValues = getCollectionOfPropertyValues();
		OJAnnotatedOperation unmarshall = new OJAnnotatedOperation("unmarshall");
		unmarshall.addParam("ps", collectionOfPropertyValues);
		unmarshall.addParam("persistence", new OJPathName(AbstractPersistence.class.getName()));
		OJForStatement foreachProperty = new OJForStatement("p", new OJPathName("PropertyValue"), "ps");
		unmarshall.getBody().addToStatements(foreachProperty);
		OJSwitchStatement sst = new OJSwitchStatement();
		sst.setCondition("p.getId()");
		foreachProperty.getBody().addToStatements(sst);
		for(INakedTypedElement p:e){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parent, p);
			OJSwitchCase sc = new OJSwitchCase();
			sst.addToCases(sc);
			sc.setLabel("" + p.getMappingInfo().getNakedUmlId());
			sc.getBody().addToStatements(target + "." + map.setter() + "((" + map.javaType() + ")Value.valueOf(p.getValue(),persistence))");
		}
		return unmarshall;
	}
	private void implementListener(INakedSignal ss){
		INakedElement e = ss;
		OJPathName classifierPathname = OJUtil.classifierPathname(ss);
		OJPathName superclass = new OJPathName("org.nakeduml.environment.adaptor.AbstractSignalMdb");
		implementListener(e, classifierPathname, superclass);
	}
	private void implementListener(INakedElement e,OJPathName classifierPathname,OJPathName superclass){
		OJAnnotatedClass mdb = new OJAnnotatedClass(classifierPathname.getLast() + "Mdb");
		mdb.setSuperclass(superclass);
		findOrCreatePackage(OJUtil.packagePathname(e.getNameSpace())).addToClasses(mdb);
		createTextPath(mdb, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		mdb.addToImplementedInterfaces(new OJPathName("javax.jms.MessageListener"));
		OJAnnotatedOperation onMessage = new OJAnnotatedOperation("onMessage");
		onMessage.addToParameters(new OJAnnotatedParameter("msg", new OJPathName("javax.jms.Message")));
		mdb.addToOperations(onMessage);
		onMessage.getBody().addToStatements("super.onMessage(msg)");
		OJAnnotationValue messageDriven = new OJAnnotationValue(new OJPathName("javax.ejb.MessageDriven"));
		mdb.putAnnotation(messageDriven);
		OJAnnotationAttributeValue activationConfig = new OJAnnotationAttributeValue("activationConfig");
		messageDriven.putAttribute(activationConfig);
		addActivationProperty(activationConfig, "destinationType", "javax.jms.Queue");
		addActivationProperty(activationConfig, "destination", "queue/" + classifierPathname);
		int timeout = 30 * 60 * 1000;// 30 minutes
		addActivationProperty(activationConfig, "TransactionTimeout", "" + timeout);
		OJEnumValue beanManaged = new OJEnumValue(new OJPathName("javax.ejb.TransactionManagementType"), "BEAN");
		mdb.putAnnotation(new OJAnnotationValue(new OJPathName("javax.ejb.TransactionManagement"), beanManaged));
		OJAnnotationValue pool = new OJAnnotationValue(new OJPathName("org.jboss.ejb3.annotation.Pool"));
		pool.putAttribute("value", "StrictMaxPool");
		mdb.putAnnotation(pool);
		OJAnnotatedOperation getQueueName = new OJAnnotatedOperation("getQueueName", new OJPathName("String"));
		mdb.addToOperations(getQueueName);
		getQueueName.getBody().addToStatements("return \"queue/" + classifierPathname + "\"");
		OJAnnotatedOperation getDlqQueueName = new OJAnnotatedOperation("getDlqName", new OJPathName("String"));
		mdb.addToOperations(getDlqQueueName);
		getDlqQueueName.getBody().addToStatements("return \"queue/" + classifierPathname + "DLQ\"");
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		mdb.addToOperations(main);
		main.addToParameters(new OJAnnotatedParameter("args", new OJPathName("String[]")));
		main.getBody().addToStatements("new " + mdb.getName() + "().redeliverDeadMessages(args)");
		main.addToThrows(new OJPathName("Exception"));
		if(e instanceof INakedSignal && ((INakedSignal) e).getListenerPoolSize() != null){
			pool.putAttribute("maxSize", ((INakedSignal) e).getListenerPoolSize());
			addActivationProperty(activationConfig, "maxSession", "" + ((INakedSignal) e).getListenerPoolSize());
		}else{
			pool.putAttribute("maxSize", 5);
			addActivationProperty(activationConfig, "maxSession", "5");
		}
	}
	private void addActivationProperty(OJAnnotationAttributeValue activationConfig,String name,String value){
		OJAnnotationValue destinationType = new OJAnnotationValue(new OJPathName("javax.ejb.ActivationConfigProperty"));
		destinationType.putAttribute("propertyName", name);
		destinationType.putAttribute("propertyValue", value);
		activationConfig.addAnnotationValue(destinationType);
	}
}
