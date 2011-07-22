package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ExpansionRegionBuilder extends Jbpm5ActionBuilder<INakedExpansionRegion> {
	public ExpansionRegionBuilder(NakedUmlLibrary oclEngine, INakedExpansionRegion node) {
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
