package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateAnnotator;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

@StepDependency(phase = JavaTransformationPhase.class,after = {
		DerivedUnionImplementor.class,JpaAnnotator.class,HibernateAnnotator.class
})
//NB!!! needs to execute after all Steps that expect the OJField's presence since it removes the redefined field
public class RedefinitionImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void property(INakedProperty p){
		if(!(p.getOwner() instanceof INakedInterface)){
			visitProperty(p.getOwner(), p);
		}
	}
	@VisitBefore(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class
	})
	public void classifier(INakedClassifier c){
		for(INakedProperty p:c.getEffectiveAttributes()){
			if(p.getOwner() instanceof INakedInterface){
				visitProperty(c, p);
			}
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;//Works across models
	}


	private void visitProperty(INakedClassifier owner,INakedProperty p){
		if(p.isNavigable()){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			OJClass c = findJavaClass(owner);
			for(INakedProperty redefinedProperty:p.getRedefinedProperties()){
				implementRedefinition(map, c, redefinedProperty);
			}
		}
	}
	public void implementRedefinition(NakedStructuralFeatureMap redefiningMap,OJClass c,INakedProperty redefinedProperty){
		NakedStructuralFeatureMap redefinedMap = new NakedStructuralFeatureMap(redefinedProperty);
		OJField f = c.findField(redefinedMap.umlName());
		if(f != null){
			c.removeFromFields(f);
			redefineOperation(c, redefinedMap.setter(), redefiningMap.setter(), redefinedMap.javaTypePath());
			redefineOperation(c, redefinedMap.adder(), redefiningMap.adder(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.remover(), redefiningMap.remover(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.internalAdder(), redefiningMap.internalAdder(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.internalRemover(), redefiningMap.internalRemover(), redefinedMap.javaBaseTypePath());
		}
		OJAnnotatedOperation o = (OJAnnotatedOperation) OJUtil.findOperation(c, redefinedMap.getter());
		// might exist if the modeler defined an
		// attribute with the same name or if it was inherited from an interface
		if(o == null){
			o = new OJAnnotatedOperation(redefinedMap.getter());
			o.setReturnType(redefinedMap.javaTypePath());
			c.addToOperations(o);
			o.setBody(null);
		}
		if(o.getBody() == null || redefinedProperty.getOwner() instanceof INakedInterface){
			// force implementation of interface redefinition
			o.setBody(new OJBlock());
			if(redefinedMap.isMany()){
				if(redefiningMap.isOne()){
					wrapInCollection(c, o, redefiningMap, redefinedMap);
				}else{
					convertToCorrectCollectionType(c, o, redefiningMap, redefinedMap);
				}
			}else{
				o.getBody().addToStatements("return " + (redefiningMap.getter() + "()"));
			}
		}
	}
	private void redefineOperation(OJClass c,String redefinedOperationName,String redefiningOperationName,OJPathName paramType){
		OJAnnotatedOperation redefinedOperation = (OJAnnotatedOperation) OJUtil.findOperation(c, redefinedOperationName);
		OJAnnotatedOperation redefiningOperation = (OJAnnotatedOperation) OJUtil.findOperation(c, redefiningOperationName);
		if(redefiningOperation != null){
			if(redefinedOperation == null){
				redefinedOperation = new OJAnnotatedOperation(redefinedOperationName);
				redefinedOperation.addParam("in", paramType);
				c.addToOperations(redefinedOperation);
			}
			redefinedOperation.setBody(new OJBlock());
			OJParameter parm = redefinedOperation.getParameters().get(0);
			OJAnnotatedField tmpField = new OJAnnotatedField(parm.getName(), redefiningOperation.getParameters().get(0).getType());
			parm.setName(parm.getName() + "New");
			OJPathName collectionType = getRawType(tmpField.getType());
			if(!collectionType.equals(tmpField.getType())){
				suppressTypeChecks(redefinedOperation);
			}
			tmpField.setInitExp("(" + collectionType.getLast() + ")" + parm.getName());
			redefinedOperation.getBody().addToLocals(tmpField);
			redefinedOperation.getBody().addToStatements(redefiningOperationName + "(" + tmpField.getName() + ")");
		}
	}
	private OJPathName getRawType(OJPathName pathName){
		OJPathName collectionType = pathName.getDeepCopy();
		collectionType.removeAllFromElementTypes();
		return collectionType;
	}
	private void convertToCorrectCollectionType(OJClass c,OJAnnotatedOperation o,NakedStructuralFeatureMap redefiningMap,NakedStructuralFeatureMap redefinedMap){
		if(redefinedMap.getFeature().getType() != redefiningMap.getFeature().getType()){
			c.addToImports(redefinedMap.javaTypePath());
			OJPathName collectionType = getRawType(redefiningMap.javaTypePath());
			suppressTypeChecks(o);
			o.getBody().addToStatements("return " + "(" + collectionType + ")" + (redefiningMap.getter() + "()"));
		}else{
			o.getBody().addToStatements("return " + (redefiningMap.getter() + "()"));
		}
	}
	private void suppressTypeChecks(OJAnnotatedOperation o){
		OJAnnotationValue suppress = new OJAnnotationValue(new OJPathName("SuppressWarnings"), "unchecked");
		suppress.addStringValue("rawtypes");
		o.putAnnotation(suppress);
	}
	private void wrapInCollection(OJClass c,OJOperation o,NakedStructuralFeatureMap redefiningMap,NakedStructuralFeatureMap redefinedMap){
		OJAnnotatedField result = new OJAnnotatedField("result", redefinedMap.javaTypePath());
		c.addToImports(redefinedMap.javaTypePath());
		c.addToImports(redefinedMap.javaDefaultTypePath());
		result.setInitExp(redefinedMap.javaDefaultValue());
		o.getBody().addToLocals(result);
		OJIfStatement ifNotNull = new OJIfStatement((redefiningMap.getter() + "()") + "!=null", "result.add(" + (redefiningMap.getter() + "()") + ")");
		o.getBody().addToStatements(ifNotNull);
		o.getBody().addToStatements("return result");
	}
}
