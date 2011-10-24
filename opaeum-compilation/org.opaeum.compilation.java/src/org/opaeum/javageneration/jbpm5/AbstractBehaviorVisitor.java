package org.opaeum.javageneration.jbpm5;

import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor{
	protected OJAnnotatedOperation addGetCallingProcessObject(OJAnnotatedClass ojOperationClass,OJPathName type){
		// getCAllbackLister
		OJAnnotatedOperation getCallbackListener = new OJAnnotatedOperation("getCallingProcessObject", type);
		ojOperationClass.addToOperations(getCallbackListener);
		OJIfStatement processInstanceNotNull = new OJIfStatement("getCallingProcessInstance()!=null ");
		getCallbackListener.getBody().addToStatements(processInstanceNotNull);
		OJAnnotatedField processObject = new OJAnnotatedField("processObject", type);
		processInstanceNotNull.getThenPart().addToLocals(processObject);
		processObject.setInitExp("(" + type.getLast() + ")getCallingProcessInstance().getVariable(\"processObject\")");
		processInstanceNotNull.getThenPart().addToStatements("return processObject");
		getCallbackListener.getBody().addToStatements("return null");
		return getCallbackListener;
	}
}
