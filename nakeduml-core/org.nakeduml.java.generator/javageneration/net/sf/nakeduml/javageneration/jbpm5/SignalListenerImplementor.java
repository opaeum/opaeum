package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.Java5ModelGenerationStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedParameter;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

@StepDependency(after = Java5ModelGenerationStep.class,phase = JavaTransformationPhase.class)
public class SignalListenerImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitSignal(INakedSignal s){
		OJAnnotatedClass mdb = new OJAnnotatedClass(s.getMappingInfo().getJavaName() + "Mdb");
		findOrCreatePackage(OJUtil.packagePathname(s.getNameSpace())).addToClasses(mdb);
		createTextPath(mdb, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		mdb.addToImplementedInterfaces(new OJPathName("javax.jms.MessageListener"));
		mdb.setSuperclass(new OJPathName("org.nakeduml.environment.adaptor.AbstractSignalMdb"));
		OJAnnotatedOperation onMessage = new OJAnnotatedOperation("onMessage");
		onMessage.addToParameters(new OJAnnotatedParameter("msg", new OJPathName("javax.jms.Message")));
		mdb.addToOperations(onMessage);
		onMessage.getBody().addToStatements("super.onMessage(msg)");
		OJAnnotationValue messageDriven = new OJAnnotationValue(new OJPathName("javax.ejb.MessageDriven"));
		mdb.putAnnotation(messageDriven);
		OJAnnotationAttributeValue activationConfig = new OJAnnotationAttributeValue("activationConfig");
		messageDriven.putAttribute(activationConfig);
		addActivationProperty(activationConfig, "destinationType", "javax.jms.Queue");
		addActivationProperty(activationConfig, "destination", "queue/" + s.getMappingInfo().getQualifiedJavaName());
		int timeout = 30*60*1000;//30 minutes
		addActivationProperty(activationConfig, "TransactionTimeout", ""+timeout);
		OJEnumValue beanManaged = new OJEnumValue(new OJPathName("javax.ejb.TransactionManagementType"), "BEAN");
		mdb.putAnnotation(new OJAnnotationValue(new OJPathName("javax.ejb.TransactionManagement"), beanManaged));
		OJAnnotationValue pool = new OJAnnotationValue(new OJPathName("org.jboss.ejb3.annotation.Pool"));
		if(s.getListenerPoolSize() == null){
			pool.putAttribute("maxSize", 5);
			addActivationProperty(activationConfig, "maxSession", "5");
		}else{
			pool.putAttribute("maxSize", s.getListenerPoolSize());
			addActivationProperty(activationConfig, "maxSession", ""+s.getListenerPoolSize());
		}
		pool.putAttribute("value","StrictMaxPool");
		mdb.putAnnotation(pool);
		OJAnnotatedOperation getQueueName = new OJAnnotatedOperation("getQueueName", new OJPathName("String"));
		mdb.addToOperations(getQueueName);
		getQueueName.getBody().addToStatements("return \"queue/" + OJUtil.classifierPathname(s) + "\"");
		OJAnnotatedOperation getDlqQueueName = new OJAnnotatedOperation("getDlqName", new OJPathName("String"));
		mdb.addToOperations(getDlqQueueName);
		getDlqQueueName.getBody().addToStatements("return \"queue/" + OJUtil.classifierPathname(s) + "DLQ\"");
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		mdb.addToOperations(main);
		main.addToParameters(new OJAnnotatedParameter("args" ,new OJPathName( "String[]")));
		main.getBody().addToStatements("new " + mdb.getName() +"().redeliverDeadMessages(args)");
		main.addToThrows(new OJPathName("Exception"));
	}

	private void addActivationProperty(OJAnnotationAttributeValue activationConfig,String name,String value){
		OJAnnotationValue destinationType = new OJAnnotationValue(new OJPathName("javax.ejb.ActivationConfigProperty"));
		destinationType.putAttribute("propertyName",name);
		destinationType.putAttribute("propertyValue",value);
		activationConfig.addAnnotationValue(destinationType);
	}
}
