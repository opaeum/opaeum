package org.opaeum.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class EmbeddedSingleScreenTaskBuilder extends PotentialTaskActionBuilder<OpaqueAction>{
	EmbeddedSingleScreenTaskCaller delegate;
	public EmbeddedSingleScreenTaskBuilder(OJUtil ojUtil,OpaqueAction node){
		super(ojUtil, node);
		delegate = new EmbeddedSingleScreenTaskCaller(node, new Jbpm5ObjectNodeExpressor(ojUtil));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!EmfActionUtil.isSynchronous( node)){
			// TODO think of exception pins perhaps
			delegate.implementActionOn(operation, operation.getBody());
		} // build task variable
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	public void implementCallbackMethods(OJClass activityClass){
		implementCompleteMethod(activityClass);
	}
	private void implementCompleteMethod(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		String completeMethodName = null;
		Classifier message = getLibrary().getMessageStructure(node);
		completeMethodName = "on" + NameConverter.capitalize(node.getName()) + "Completed";
		implementCallbackOnComplete(activityClass, completeMethodName, message);
	}
	@Override
	protected Collection<Type> getRaisedExceptions(){
		return Collections.emptySet();
	}
}
