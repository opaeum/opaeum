package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerOpaqueActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedOclAction.class })
	public void visitOpaqueAction(INakedOclAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 		
		// Calc one or many
		if (oa.getReturnPin() != null && oa.getReturnPin().getNakedMultiplicity().isMany()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyOpaqueActionPathName.getCopy());
		} else if (oa.getReturnPin() != null && oa.getReturnPin().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneOpaqueActionPathName.getCopy());
		} else {
			// Nada
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOpaqueActionPathName.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		if (oa.getReturnPin() != null) {
			addGetBodyExpression(actionClass, oa);
		}
		addAddToInputPinVariable(actionClass, (INakedAction) oa);
		if (((INakedOclAction) oa).getReturnPin() != null) {
			addReturnPinGenericType(actionClass, (INakedOclAction) oa);
		}
	}

	private void addGetBodyExpression(OJAnnotatedClass actionClass, INakedOclAction oa) {
		OJAnnotatedOperation getBodyExpression = new OJAnnotatedOperation("getBodyExpression");
		TinkerGenerationUtil.addOverrideAnnotation(getBodyExpression);
		actionClass.addToOperations(getBodyExpression);
		if (oa.getBodyExpression() != null) {
			String expressValue = ValueSpecificationUtil.expressValue(getBodyExpression, new NakedValueSpecificationImpl(oa.getBodyExpression()), oa.getActivity(), oa
					.getReturnPin().getType());
			getBodyExpression.getBody().addToStatements("return " + expressValue);
			if (oa.getReturnPin().getNakedMultiplicity().isMany()) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), oa.getReturnPin(), false));
				OJPathName collectionPathName = new OJPathName("java.util.Collection");
				collectionPathName.addToElementTypes(map.javaBaseTypePath());
				getBodyExpression.setReturnType(collectionPathName);
			} else {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), oa.getReturnPin(), false));
				getBodyExpression.setReturnType(map.javaBaseTypePath());
			}
		} else {
			getBodyExpression.getBody().addToStatements("return null");
			OJPathName collectionPathName = new OJPathName("java.util.Collection");
			collectionPathName.addToElementTypes(new OJPathName("java.lang.Object"));
			getBodyExpression.setReturnType(collectionPathName);
		}
	}
	
	private void addReturnPinGenericType(OJClass actionClass, INakedOclAction oa) {
		OJPathName superPathName = actionClass.getSuperclass().getCopy();
		superPathName.addToGenerics(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		// OJPathName objectToken = null;
		// if (oa.getReturnPin().getNakedMultiplicity().isOne()) {
		// objectToken = TinkerBehaviorUtil.tinkerSingleObjectToken.getCopy();
		// } else {
		// objectToken =
		// TinkerBehaviorUtil.tinkerCollectionObjectToken.getCopy();
		// }
		// actionClass.addToImports(objectToken);
		// objectToken.addToGenerics(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		// superPathName.addToGenerics(objectToken);
		actionClass.setSuperclass(superPathName);
	}


}
