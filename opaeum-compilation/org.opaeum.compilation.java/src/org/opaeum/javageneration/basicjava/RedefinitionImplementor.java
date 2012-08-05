package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.persistence.JpaAnnotator;

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
	protected void visitProperty(Classifier owner,StructuralFeatureMap map){
		Property p = map.getProperty();
		if(p.isNavigable()){
			OJClass c = findJavaClass(owner);
			for(Property redefinedProperty:p.getRedefinedProperties()){
				implementRedefinition(map, c, redefinedProperty);
			}
		}
	}
	public void implementRedefinition(StructuralFeatureMap redefiningMap,OJClass c,Property redefinedProperty){
		StructuralFeatureMap redefinedMap = ojUtil.buildStructuralFeatureMap(redefinedProperty);
		OJField f = c.findField(redefinedMap.fieldname());
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
		OJAnnotatedOperation o = (OJAnnotatedOperation) c.getUniqueOperation(redefinedMap.getter());
		// might exist if the modeler defined an
		// attribute with the same name
		if(o == null){
			o = new OJAnnotatedOperation(redefinedMap.getter());
			o.setReturnType(redefinedMap.javaTypePath());
			c.addToOperations(o);
		}
		if(redefinedProperty.getAssociation()!=null &&  EmfAssociationUtil.isClass(redefinedProperty.getAssociation())){
			//TODO implement validation - AssociationClasss end cannot be redefined			
			AssociationClassEndMap assocEndMap = ojUtil.buildAssociationClassEndMap(redefinedProperty);
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
		OJAnnotatedOperation redefinedOperation = (OJAnnotatedOperation) c.getUniqueOperation(redefinedOperationName);
		OJAnnotatedOperation redefiningOperation = (OJAnnotatedOperation) c.getUniqueOperation(redefiningOperationName);
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
	private void convertToCorrectCollectionType(OJClass c,OJAnnotatedOperation o,StructuralFeatureMap redefiningMap,StructuralFeatureMap redefinedMap){
		if(getLibrary().getActualType( redefinedMap.getFeature()) != getLibrary().getActualType( redefiningMap.getFeature())){
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
	private void wrapInCollection(OJClass c,OJAnnotatedOperation o,StructuralFeatureMap redefiningMap,StructuralFeatureMap redefinedMap){
		o.initializeResultVariable(redefinedMap.javaDefaultValue());
		OJIfStatement ifNotNull = new OJIfStatement((redefiningMap.getter() + "()") + "!=null", "result.add(" + (redefiningMap.getter() + "()") + ")");
		o.getBody().addToStatements(ifNotNull);
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
	}
}
