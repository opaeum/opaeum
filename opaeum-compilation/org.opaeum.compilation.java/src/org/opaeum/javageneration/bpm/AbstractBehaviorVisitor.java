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
	public void addReturnInfo(OJAnnotatedClass ojOperationClass){
		OJAnnotatedField returnInfo=new OJAnnotatedField("returnInfo", BpmUtil.RETURN_INFO);
		ojOperationClass.addToFields(returnInfo);
		returnInfo.setInitExp("new ReturnInfo()");
		OJAnnotatedOperation setReturnInfo=new OJAnnotatedOperation("setReturnInfo");
		ojOperationClass.addToOperations(setReturnInfo);
		setReturnInfo.addParam("token", BpmUtil.ABSTRACT_TOKEN);
		setReturnInfo.getBody().addToStatements("this.returnInfo.setValue(token)");
		OJAnnotatedOperation getReturnInfo=new OJAnnotatedOperation("getReturnInfo",new OJPathName("org.opaeum.runtime.domain.IToken"));
		ojOperationClass.addToOperations(getReturnInfo);
		getReturnInfo.initializeResultVariable("this.returnInfo.getValue(persistence)");
	}
	
}
