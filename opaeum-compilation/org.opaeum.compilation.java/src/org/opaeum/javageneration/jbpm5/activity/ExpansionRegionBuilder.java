package org.opaeum.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.Collections;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.AbstractProtectedNodeBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.StructuredActivityNodeMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ExpansionRegionBuilder extends AbstractProtectedNodeBuilder<INakedExpansionRegion>{
	public ExpansionRegionBuilder(OpaeumLibrary oclEngine,INakedExpansionRegion node){
		super(oclEngine, node, OJUtil.buildStructuralFeatureMap(node.getMessageStructure().getEndToComposite().getOtherEnd()));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper){
		OJAnnotatedField count = new OJAnnotatedField("count", new OJPathName("int"));
		count.setInitExp("Integer.MAX_VALUE");
		oper.getBody().addToLocals(count);
		OJForStatement forEach = new OJForStatement("i", new OJPathName("int"), "new int[count]");
		NakedStructuralFeatureMap mapTo = OJUtil.buildStructuralFeatureMap(node.getMessageStructure().getEndToComposite().getOtherEnd());
		OJAnnotatedField cur = new OJAnnotatedField("cur", mapTo.javaBaseTypePath());
		cur.setInitExp("new " + mapTo.javaBaseType() + "(this)");
		forEach.getBody().addToLocals(cur);
		forEach.getBody().addToStatements("cur.setCallingNodeInstanceUniqueId(((NodeInstanceImpl)context.getNodeInstance()).getUniqueId())");
		for(INakedExpansionNode n:node.getInputElement()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getNearestStructuredElementAsClassifier(), n, false);
			OJPathName collectionPath = new OJPathName("java.util.Collection");
			collectionPath.addToElementTypes(map.javaBaseTypePath());
			OJAnnotatedField coll = new OJAnnotatedField(map.fieldname(), collectionPath);
			coll.setInitExp(expressor.expressInputPinOrOutParamOrExpansionNode(oper.getBody(), n));
			oper.getBody().addToLocals(coll);
			OJPathName iteratorPath = new OJPathName("java.util.Iterator");
			iteratorPath.addToElementTypes(map.javaBaseTypePath());
			OJAnnotatedField iterator = new OJAnnotatedField(map.fieldname() + "Iterator", iteratorPath);
			iterator.setInitExp(coll.getName() + ".iterator()");
			oper.getBody().addToLocals(iterator);
			oper.getBody().addToStatements("count=Math.min(count," + coll.getName() + ".size())");
			forEach.getBody().addToStatements("cur." + map.setter() + "(" + iterator.getName() + ".next())");
		}
		oper.getBody().addToStatements(forEach);
		OJForStatement forEach2 = new OJForStatement("cur", mapTo.javaBaseTypePath(), mapTo.getter() + "()");
		oper.getBody().addToStatements(forEach2);
		forEach2.getBody().addToStatements("cur.execute()");
		if(!node.getActivity().isProcess()){
			OJIfStatement ifException = new OJIfStatement("cur.getCurrentException()!=null");
			forEach2.getBody().addToStatements(ifException);
			for(INakedExceptionHandler p:node.getHandlers()){
				implementHandler(oper, p, ifException.getThenPart(), "cur.getCurrentException()");
			}
			ifException.getThenPart().addToStatements(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD+"=cur.getCurrentException()");
			ifException.getThenPart().addToStatements("getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED)");
			ifException.getThenPart().addToStatements("return");
			OJAnnotatedField waitingNode = new OJAnnotatedField("waitingNode", Jbpm5Util.UML_NODE_INSTANCE.getCopy());
			oper.getBody().addToLocals(waitingNode);
			waitingNode.setInitExp("("+Jbpm5Util.UML_NODE_INSTANCE.getLast() + ")context.getNodeInstance()");
			implementConditionalFlows(oper, oper.getBody());
		}
	}
	public void implementCallbackMethods(OJClass owner){
		if(node.getActivity().isProcess()){
			StructuredActivityNodeMap map = new StructuredActivityNodeMap(node);
			implementCallbackOnComplete(owner, map.completeMethodName(), node.getMessageStructure());
			implementExceptionHandlers(owner, map);
		}
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	protected Collection<INakedClassifier> getRaisedExceptions(){
		return Collections.emptySet();
	}
}
