package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,CodeCleanup.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class AttributeExpressionGenerator extends AbstractStructureVisitor{
	@Override
	protected void visitProperty(Classifier owner,PropertyMap mapper){
		Property attr = mapper.getProperty();
		ValueSpecification cont = attr.getDefaultValue();
		if(cont != null){
			if(attr.isDerived()){
				OJPathName path = ojUtil.classifierPathname(owner);
				OJClass myOwner = javaModel.findClass(path);
				addDerivationRule(owner, myOwner, mapper, cont);
			}else{
				Classifier owningElem = owner;
				OJPathName path = ojUtil.classifierPathname(owningElem);
				OJClass myOwner = javaModel.findClass(path);
				if(attr.isStatic()){
					addInitToStaticField(myOwner, mapper, cont);
				}else{
					addInitToConstructor(myOwner, mapper, cont);
				}
			}
		}
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
	}
	private void addDerivationRule(Classifier c,OJClass myClass,PropertyMap mapper,ValueSpecification vs){
		String getterName = mapper.getter();
		OJAnnotatedOperation getterOp = (OJAnnotatedOperation) myClass.findOperation(getterName, new ArrayList<OJPathName>());
		getterOp.initializeResultVariable(valueSpecificationUtil.expressValue(getterOp, vs, mapper.getDefiningClassifier(), getLibrary().getActualType( mapper.getProperty())));
	}
	private void addInitToStaticField(OJClass myClass,PropertyMap mapper,ValueSpecification vs){
		String initStr = valueSpecificationUtil.expressValue(myClass, vs,  getLibrary().getActualType( mapper.getProperty()), true);
		if(initStr.length() > 0){
			OJAnnotatedField myField = (OJAnnotatedField) myClass.findField(mapper.fieldname());
			if(myField != null){
				myField.setInitExp(initStr);
			}
		}
	}
	private void addInitToConstructor(OJClass myClass,PropertyMap mapper,ValueSpecification vs){
		String initStr = valueSpecificationUtil.expressValue(myClass.getDefaultConstructor(), vs, mapper.getDefiningClassifier(), getLibrary().getActualType( mapper.getProperty()));
		if(initStr.length() > 0){
			String statement = "this." + mapper.setter() + "( " + initStr + " )";
			for(OJConstructor constr:myClass.getConstructors()){
				constr.getBody().addToStatements(statement);
			}
		}
	}
}
