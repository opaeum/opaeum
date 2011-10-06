package org.opaeum.javageneration.jbpm5.activity;

import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ExpansionRegionBuilder extends Jbpm5ActionBuilder<INakedExpansionRegion> {
	public ExpansionRegionBuilder(OpaeumLibrary oclEngine, INakedExpansionRegion node) {
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
