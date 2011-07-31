package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Collections;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,NakedParsedOclStringResolver.class,CodeCleanup.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class AttributeExpressionGenerator extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void implementInterfaces(INakedClassifier c){
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)){
			for(INakedProperty p:c.getEffectiveAttributes()){
				if(p.getOwner() instanceof INakedInterface || p.getOwner() == c){
					implementAttributeExpressions(c, p);
				}
			}
		}
	}
	private void implementAttributeExpressions(INakedClassifier owner,INakedProperty attr){
		NakedStructuralFeatureMap mapper = new NakedStructuralFeatureMap(attr);
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
					addInitToField(myOwner, mapper, cont);
				}else{
					addInitToConstructor(myOwner, mapper, cont);
				}
			}
		}
	}
	private void addDerivationRule(INakedClassifier c,OJClass myClass,NakedStructuralFeatureMap mapper,INakedValueSpecification vs){
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
	private void addInitToField(OJClass myClass,NakedStructuralFeatureMap mapper,INakedValueSpecification vs){
		if(!(myClass instanceof OJClass))
			return;
		String initStr = ValueSpecificationUtil.expressValue(myClass, vs, true);
		if(initStr.length() > 0){
			OJAnnotatedField myField = (OJAnnotatedField) myClass.findField(mapper.fieldname());
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
