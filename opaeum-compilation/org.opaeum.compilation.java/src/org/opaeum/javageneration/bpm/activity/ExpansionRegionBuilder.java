package org.opaeum.javageneration.bpm.activity;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.bpm.actions.AbstractProtectedNodeBuilder;
import org.opaeum.javageneration.bpm.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.maps.StructuredActivityNodeMap;
import org.opaeum.javageneration.util.OJUtil;

public class ExpansionRegionBuilder extends AbstractProtectedNodeBuilder<ExpansionRegion>{
	public ExpansionRegionBuilder(OJUtil ojUtil,ExpansionRegion node){
		super(ojUtil, node, ojUtil.buildStructuralFeatureMap(ojUtil.getLibrary().getEndToComposite(ojUtil.getLibrary().getMessageStructure( node)).getOtherEnd()));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper){
		OJAnnotatedField count = new OJAnnotatedField("count", new OJPathName("int"));
		count.setInitExp("Integer.MAX_VALUE");
		oper.getBody().addToLocals(count);
		OJForStatement forEach = new OJForStatement("i", new OJPathName("int"), "new int[count]");
		OJAnnotatedField cur = new OJAnnotatedField("cur", callMap.javaBaseTypePath());
		cur.setInitExp("new " + callMap.javaBaseType() + "(this)");
		forEach.getBody().addToLocals(cur);
		forEach.getBody().addToStatements("cur.setReturnInfo(callingToken)");
		for(ExpansionNode n:node.getInputElements()){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(n);
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
		OJForStatement forEach2 = new OJForStatement("cur", callMap.javaBaseTypePath(), callMap.getter() + "()");
		oper.getBody().addToStatements(forEach2);
		forEach2.getBody().addToStatements("cur.execute()");
		if(!EmfBehaviorUtil.isProcess(getContainingActivity())){
			OJIfStatement ifException = new OJIfStatement("cur.getCurrentException()!=null");
			forEach2.getBody().addToStatements(ifException);
			for(ExceptionHandler p:node.getHandlers()){
				implementHandler(oper, p, ifException.getThenPart(), "cur.getCurrentException()");
			}
			ifException.getThenPart().addToStatements(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "=cur.getCurrentException()");
			ifException.getThenPart().addToStatements("return");
			implementConditionalFlows(oper, oper.getBody());
		}
	}
	public void implementCallbackMethods(OJClass owner){
		if(EmfBehaviorUtil.isProcess(getContainingActivity())){
			StructuredActivityNodeMap map  = ojUtil.buildStructuredActivityNodeMap(node);
			implementCallbackOnComplete(owner, map.completeMethodName(), getLibrary().getMessageStructure(node));
			implementExceptionHandlers(owner, map);
		}
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	protected Collection<Type> getRaisedExceptions(){
		return Collections.emptySet();
	}
}
