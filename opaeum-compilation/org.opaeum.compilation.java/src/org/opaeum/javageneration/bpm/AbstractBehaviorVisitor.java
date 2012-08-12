package org.opaeum.javageneration.bpm;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextWorkspace;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor{
	protected TaskUtil taskUtil;
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace, OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		taskUtil=new TaskUtil(ojUtil);
	}
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
