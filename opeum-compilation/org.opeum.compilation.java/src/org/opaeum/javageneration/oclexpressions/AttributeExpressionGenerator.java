package org.opeum.javageneration.oclexpressions;

import java.util.Collections;

import org.opeum.feature.StepDependency;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedValueSpecification;

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
