package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.persistence.JpaAnnotator;

@StepDependency(phase = JavaTransformationPhase.class,after = {DerivedUnionImplementor.class,JpaAnnotator.class,HibernateAnnotator.class})
// NB!!! needs to execute after all Steps that expect the OJField's presence since it removes the redefined field
public class RedefinitionImplementor extends AbstractStructureVisitor{
	@Override
	protected int getThreadPoolSize(){
		return 1;// Works across models
	}
	@Override
	protected void visitProperty(Classifier owner,PropertyMap map){
		Property p = map.getProperty();
		if(p.isNavigable()){
			OJClass c = findJavaClass(owner);
			for(Property redefinedProperty:p.getRedefinedProperties()){
				implementRedefinition(owner, map, c, redefinedProperty);
			}
		}
	}
	public void implementRedefinition(Classifier owner,PropertyMap redefiningMap,OJClass c,Property redefinedProperty){
		PropertyMap redefinedMap = ojUtil.buildStructuralFeatureMap(redefinedProperty);
		if(!(redefiningMap.getProperty().isReadOnly() || EmfPropertyUtil.isDerived(redefiningMap.getProperty())
				|| redefinedProperty.isReadOnly() || EmfPropertyUtil.isDerived(redefinedProperty))){
			if(!shouldRealiseField(owner, redefinedProperty)){
				c.removeFromFields(c.findField(redefinedMap.fieldname()));
			}
			copyeStatements(redefiningMap, c, redefinedMap, redefiningMap.internalAdder(), redefinedMap.internalAdder());
			copyeStatements(redefiningMap, c, redefinedMap, redefiningMap.internalRemover(), redefinedMap.internalRemover());
		}
		OJAnnotatedOperation o = (OJAnnotatedOperation) c.findOperation(redefinedMap.getter(), new ArrayList<OJPathName>());
		if(isMap(redefiningMap.getProperty())){
			OJAnnotatedOperation qualifiedGetter = (OJAnnotatedOperation) c.findOperation(redefinedMap.getter(),
					redefinedMap.qualifiedArgsForReader());
			qualifiedGetter.setBody(new OJBlock());
			StringBuilder sb = new StringBuilder(redefiningMap.getter());
			sb.append('(');
			Iterator<OJParameter> it = qualifiedGetter.getParameters().iterator();
			while(it.hasNext()){
				OJParameter ojParameter = it.next();
				sb.append(ojParameter.getName());
				if(it.hasNext()){
					sb.append(',');
				}
			}
			sb.append(')');
			qualifiedGetter.initializeResultVariable(sb.toString());
		}
		if(redefinedProperty.getAssociation() != null && EmfAssociationUtil.isClass(redefinedProperty.getAssociation())){
			// TODO implement validation - AssociationClasss end cannot be redefined
			AssociationClassEndMap assocEndMap = ojUtil.buildAssociationClassEndMap(redefinedProperty);
			((OJAnnotatedClass) c).removeFromFields(assocEndMap.getEndToAssocationClassMap().fieldname());
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
	protected void copyeStatements(PropertyMap redefiningMap,OJClass c,PropertyMap redefinedMap,String redefiningWriter,String redefinedWriter){
		OJOperation redefiningOper = c.findOperation(redefiningWriter, redefiningMap.qualifiedArgumentsForWriter());
		OJOperation redefinedOper = c.findOperation(redefinedWriter, redefinedMap.qualifiedArgumentsForWriter());
		if(redefinedOper != null && redefinedOper != null){
			ArrayList<OJStatement> redefinedStatements = new ArrayList<OJStatement>(redefinedOper.getBody().getStatements());
			ArrayList<OJStatement> redefiningStatements = new ArrayList<OJStatement>(redefiningOper.getBody().getStatements());
			OJAnnotatedField redefiningValue = new OJAnnotatedField(redefiningMap.fieldname(), redefiningMap.javaBaseTypePath());
			redefinedOper.getBody().addToLocals(redefiningValue);
			redefiningValue.setInitExp("(" + redefiningMap.javaBaseType() + ")" + redefinedMap.fieldname());
			OJAnnotatedField redefinedValue = new OJAnnotatedField(redefinedMap.fieldname(), redefinedMap.javaBaseTypePath());
			redefiningOper.getBody().addToLocals(redefinedValue);
			redefinedValue.setInitExp(redefiningMap.fieldname());
			redefiningOper.getBody().addToStatements(redefinedStatements);
			redefinedOper.getBody().addToStatements(redefiningStatements);
		}
	}
	protected boolean shouldRealiseField(Classifier owner,Property redefinedProperty){
		Classifier redefinedOwner = EmfPropertyUtil.getOwningClassifier(redefinedProperty);
		if(redefinedOwner == owner){
			return true;
		}else if(redefinedOwner instanceof Interface){
			if(owner.getGenerals().isEmpty()){
				return true;
			}else{
				return !EmfClassifierUtil.conformsTo(owner.getGenerals().get(0), redefinedOwner);
			}
		}
		return false;
	}
	private OJPathName getRawType(OJPathName pathName){
		OJPathName collectionType = pathName.getDeepCopy();
		collectionType.removeAllFromElementTypes();
		return collectionType;
	}
	private void convertToCorrectCollectionType(OJClass c,OJAnnotatedOperation o,PropertyMap redefiningMap,PropertyMap redefinedMap){
		if(getLibrary().getActualType(redefinedMap.getProperty()) != getLibrary().getActualType(redefiningMap.getProperty())){
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
	private void wrapInCollection(OJClass c,OJAnnotatedOperation o,PropertyMap redefiningMap,PropertyMap redefinedMap){
		o.initializeResultVariable(redefinedMap.javaDefaultValue());
		OJIfStatement ifNotNull = new OJIfStatement((redefiningMap.getter() + "()") + "!=null", "result.add(" + (redefiningMap.getter() + "()")
				+ ")");
		o.getBody().addToStatements(ifNotNull);
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
	}
}
