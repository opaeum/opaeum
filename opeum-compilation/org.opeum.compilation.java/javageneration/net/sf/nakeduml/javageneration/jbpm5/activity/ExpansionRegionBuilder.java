package org.opeum.javageneration.jbpm5.activity;

import java.util.List;

import org.opeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedExpansionRegion;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class ExpansionRegionBuilder extends Jbpm5ActionBuilder<INakedExpansionRegion> {
	public ExpansionRegionBuilder(OpeumLibrary oclEngine, INakedExpansionRegion node) {
		super(oclEngine, node);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation oper) {
		List<INakedExpansionNode> outputElements = node.getOutputElement();
		OJAnnotatedClass owner = (OJAnnotatedClass) oper.getOwner();
		INakedExpansionNode inputElement = node.getInputElement().get(0);
		OJAnnotatedOperation collectionExpression=new OJAnnotatedOperation(ActivityUtil.getCollectionExpression(inputElement));
//		oper.addParam("context", ActivityUtil.PROCESS_CONTEXT);
		owner.addToOperations(collectionExpression);
		collectionExpression.setReturnType(new OJPathName("java.util.Collection"));
		OJBlock collectionBody = collectionExpression.getBody();
		for (INakedExpansionNode expansionNode : outputElements) {
			NakedStructuralFeatureMap map=OJUtil.buildStructuralFeatureMap(expansionNode.getActivity(), expansionNode);
			expressor.buildResultVariable(oper, oper.getBody(), map);
			owner.addToImports(map.javaDefaultTypePath());
			owner.addToImports(map.javaTypePath());
		}
		collectionBody.addToStatements("return " + expressor.expressInputPinOrOutParamOrExpansionNode(collectionBody, inputElement));
	}
}
