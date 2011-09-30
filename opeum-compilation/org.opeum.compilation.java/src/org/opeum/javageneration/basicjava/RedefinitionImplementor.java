package org.opeum.javageneration.basicjava;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.hibernate.HibernateAnnotator;
import org.opeum.javageneration.maps.AssociationClassEndMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.persistence.JpaAnnotator;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.NakedAssociationImpl;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;

@StepDependency(phase = JavaTransformationPhase.class,after = {
		DerivedUnionImplementor.class,JpaAnnotator.class,HibernateAnnotator.class
})
// NB!!! needs to execute after all Steps that expect the OJField's presence since it removes the redefined field
public class RedefinitionImplementor extends AbstractStructureVisitor{
	@Override
	protected int getThreadPoolSize(){
		return 1;// Works across models
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(p.isNavigable()){
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
		}
		if(!(redefiningMap.getProperty().isReadOnly() || redefiningMap.getProperty().isDerived() || redefinedProperty.isReadOnly() || redefinedProperty.isDerived())){
			redefineOperation(c, redefinedMap.setter(), redefiningMap.setter(), redefinedMap.javaTypePath());
			redefineOperation(c, redefinedMap.adder(), redefiningMap.adder(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.remover(), redefiningMap.remover(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.internalAdder(), redefiningMap.internalAdder(), redefinedMap.javaBaseTypePath());
			redefineOperation(c, redefinedMap.internalRemover(), redefiningMap.internalRemover(), redefinedMap.javaBaseTypePath());
		}
		OJAnnotatedOperation o = (OJAnnotatedOperation) OJUtil.findOperation(c, redefinedMap.getter());
		// might exist if the modeler defined an
		// attribute with the same name
		if(o == null){
			o = new OJAnnotatedOperation(redefinedMap.getter());
			o.setReturnType(redefinedMap.javaTypePath());
			c.addToOperations(o);
		}
		if(redefinedProperty.getAssociation()!=null &&  redefinedProperty.getAssociation().isClass()){
			//TODO implement validation - AssociationClasss end cannot be redefined			
			AssociationClassEndMap assocEndMap = new AssociationClassEndMap(redefinedProperty);
			 ((OJAnnotatedClass)c).removeFromFields(assocEndMap.getEndToAssocationClassMap().fieldname());
		}
		// force implementation of redefinition
		if(redefinedMap.isMany()){
			if(redefiningMap.isOne()){
				wrapInCollection(c, o, redefiningMap, redefinedMap);
			}else{
				convertToCorrectCollectionType(c, o, redefiningMap, redefinedMap);
			}
		}else{
			o.initializeResultVariable(redefiningMap.getter() + "()");
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
			OJPathName collectionType = getRawType(redefiningMap.javaTypePath());
			o.initializeResultVariable("(" + collectionType + ")" + redefiningMap.getter() + "()");
			c.addToImports(redefinedMap.javaTypePath());
			suppressTypeChecks(o);
		}else{
			o.initializeResultVariable(redefiningMap.getter() + "()");
		}
	}
	private void suppressTypeChecks(OJAnnotatedOperation o){
		OJAnnotationValue suppress = new OJAnnotationValue(new OJPathName("SuppressWarnings"), "unchecked");
		suppress.addStringValue("rawtypes");
		o.putAnnotation(suppress);
	}
	private void wrapInCollection(OJClass c,OJAnnotatedOperation o,NakedStructuralFeatureMap redefiningMap,NakedStructuralFeatureMap redefinedMap){
		o.initializeResultVariable(redefinedMap.javaDefaultValue());
		OJIfStatement ifNotNull = new OJIfStatement((redefiningMap.getter() + "()") + "!=null", "result.add(" + (redefiningMap.getter() + "()") + ")");
		o.getBody().addToStatements(ifNotNull);
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
	}
}