package org.opaeum.javageneration.oclexpressions;

import java.util.Collections;

import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedValueSpecification;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,NakedParsedOclStringResolver.class,CodeCleanup.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class AttributeExpressionGenerator extends AbstractStructureVisitor{
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap mapper){
		INakedProperty attr = mapper.getProperty();
		INakedValueSpecification cont = attr.getInitialValue();
		if(cont != null){
			if(attr.isDerived()){
				OJPathName path = OJUtil.classifierPathname(owner);
				OJClass myOwner = javaModel.findClass(path);
				addDerivationRule(owner, myOwner, mapper, cont);
			}else{
				INakedClassifier owningElem = owner;
				OJPathName path = OJUtil.classifierPathname(owningElem);
				OJClass myOwner = javaModel.findClass(path);
				if(attr.hasClassScope()){
					addInitToStaticField(myOwner, mapper, cont);
				}else{
					addInitToConstructor(myOwner, mapper, cont);
				}
			}
		}
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
	}
	private void addDerivationRule(INakedClassifier c,OJClass myClass,NakedStructuralFeatureMap mapper,INakedValueSpecification vs){
		String getterName = mapper.getter();
		OJAnnotatedOperation getterOp = (OJAnnotatedOperation) myClass.findOperation(getterName, Collections.emptyList());
		getterOp.initializeResultVariable(ValueSpecificationUtil.expressValue(getterOp, vs, mapper.getProperty().getOwner(), mapper.getProperty().getType()));
	}
	private void addInitToStaticField(OJClass myClass,NakedStructuralFeatureMap mapper,INakedValueSpecification vs){
		String initStr = ValueSpecificationUtil.expressValue(myClass, vs, mapper.getProperty().getType(), true);
		if(initStr.length() > 0){
			OJAnnotatedField myField = (OJAnnotatedField) myClass.findField(mapper.umlName());
			if(myField != null){
				myField.setInitExp(initStr);
			}
		}
	}
	private void addInitToConstructor(OJClass myClass,NakedStructuralFeatureMap mapper,INakedValueSpecification vs){
		String initStr = ValueSpecificationUtil.expressValue(myClass.getDefaultConstructor(), vs, mapper.getProperty().getOwner(), mapper.getProperty().getType());
		if(initStr.length() > 0){
			String statement = "this." + mapper.setter() + "( " + initStr + " )";
			for(OJConstructor constr:myClass.getConstructors()){
				constr.getBody().addToStatements(statement);
			}
		}
	}
}
