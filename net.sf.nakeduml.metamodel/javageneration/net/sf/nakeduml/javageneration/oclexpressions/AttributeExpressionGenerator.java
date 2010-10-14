package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public class AttributeExpressionGenerator extends AbstractJavaProducingVisitor {
	@VisitAfter(matchSubclasses = true)
	public void implementInterfaces(INakedClassifier c) {
		if (hasOJClass(c) && !(c instanceof INakedInterface)) {
			for (INakedProperty p : c.getEffectiveAttributes()) {
				if (p.getOwner() instanceof INakedInterface) {
					implementAttributeExpressions(c, p);
				}
			}
		}
	}

//	@VisitAfter()
//	public void visitValuePin(INakedValuePin valuePin) {
//		if (valuePin.getValue() != null) {
//			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(valuePin.getActivity(), valuePin);
//			addDerivationRule(valuePin.getActivity(), findJavaClass(valuePin.getActivity()), map, valuePin.getValue());
//		}
//	}

	@VisitAfter(matchSubclasses = true)
	public void property(INakedProperty attr) {
		implementAttributeExpressions(attr.getOwner(), attr);
	}

	private void implementAttributeExpressions(INakedClassifier owner, INakedProperty attr) {
		NakedStructuralFeatureMap mapper = new NakedStructuralFeatureMap(attr);
		INakedValueSpecification cont = attr.getInitialValue();
		if (cont != null) {
			if (attr.isDerived()) {
				OJPathName path = OJUtil.classifierPathname(owner);
				OJClass myOwner = javaModel.findClass(path);
				addDerivationRule(owner, myOwner, mapper, cont);
			} else {
				INakedClassifier owningElem = owner;
				OJPathName path = OJUtil.classifierPathname(owningElem);
				OJClass myOwner = javaModel.findClass(path);
				if (attr.hasClassScope()) {
					addInitToField(myOwner, mapper, cont);
				} else {
					addInitToConstructor(myOwner, mapper, cont);
				}
			}
		}
	}

	private void addDerivationRule(INakedClassifier c, OJClass myClass, NakedStructuralFeatureMap mapper, INakedValueSpecification vs) {
		String getterName = mapper.getter();
		OJOperation getterOp = myClass.findOperation(getterName, Collections.emptyList());
		getterOp.setBody(new OJBlock());
		OJField field = new OJField();
		field.setName(mapper.umlName());
		field.setType(mapper.javaTypePath());
		field.setInitExp(ValueSpecificationUtil.expressValue(getterOp, vs, mapper.getProperty().getOwner(), mapper.getProperty().getType()));
		getterOp.getBody().addToLocals(field);
		getterOp.getBody().addToStatements("return " + mapper.umlName());
	}

	private void addInitToField(OJClass myClass, NakedStructuralFeatureMap mapper, INakedValueSpecification vs) {
		if (!(myClass instanceof OJClass))
			return;
		String initStr = ValueSpecificationUtil.expressValue(myClass, vs, true);
		if (initStr.length() > 0) {
			OJAnnotatedField myField = (OJAnnotatedField) myClass.findField(mapper.fieldname());
			if (myField != null) {
				myField.setInitExp(initStr);
			}
		}
	}

	private void addInitToConstructor(OJClass myClass, NakedStructuralFeatureMap mapper, INakedValueSpecification vs) {
		String initStr = ValueSpecificationUtil.expressValue(myClass.getDefaultConstructor(), vs, mapper.getProperty().getOwner(), mapper.getProperty().getType());
		if (initStr.length() > 0) {
			String statement = "this." + mapper.setter() + "( " + initStr + " )";
			for (OJConstructor constr : myClass.getConstructors()) {
				constr.getBody().addToStatements(statement);
			}
		}
	}
}
