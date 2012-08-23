package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;

@StepDependency(phase = JavaTransformationPhase.class,after = {
		OperationAnnotator.class,AttributeImplementor.class
})
public class DerivedUnionImplementor extends AbstractStructureVisitor{
	@Override
	protected int getThreadPoolSize(){
		return 1;// Does work across multiple models
	}
	public void visitProperty(Classifier owner,PropertyMap map){
		Property p = map.getProperty();
		if(p.isNavigable()){
			OJClass c = findJavaClass(owner);
			for(Property derivedUnion:p.getSubsettedProperties()){

				addSubsetToUnion(owner, map, c, derivedUnion);
			}
		}
	}
	private OJAnnotatedOperation findOrCreateDerivedUnionGetter(Classifier owner,PropertyMap derivedUnionMap,OJClass c){
		// just a default implementation so that all subclasses are not required
		// to implement it. Remember this getter may have been implemented in the superclass only.
		OJAnnotatedOperation getter = (OJAnnotatedOperation) c.findOperation(derivedUnionMap.getter(), new ArrayList<OJPathName>());
		if(getter == null){
			getter = new OJAnnotatedOperation(derivedUnionMap.getter());
			getter.setReturnType(derivedUnionMap.javaTypePath());
			c.addToOperations(getter);
			c.addToImports(derivedUnionMap.javaDefaultTypePath());
			getter.initializeResultVariable(derivedUnionMap.javaDefaultValue());
			if(!(derivedUnionMap.getProperty().getOwner() instanceof Interface)){
				if(derivedUnionMap.isMany()){
					getter.getBody().addToStatements(OJAnnotatedOperation.RESULT + ".addAll(super." + derivedUnionMap.getter() + "())");
				}else{
					getter.getBody().addToStatements(OJAnnotatedOperation.RESULT + "=super." + derivedUnionMap.getter() + "()");
				}
			}
		}
		return getter;
	}
	private void addSubsetToUnion(Classifier owner,PropertyMap subsettingMap,OJClass c,Property derivedUnion){
		PropertyMap derivedUnionMap = ojUtil.buildStructuralFeatureMap(derivedUnion);
		if(!isNormalPropertyOverride(subsettingMap, derivedUnionMap)){
			OJAnnotatedOperation sgetter = findOrCreateDerivedUnionGetter(owner, derivedUnionMap, c);
			// TODO when property override occurs on a many property, the getter needs to be changed
			// This would require us to us z_internalAdd again as the derived set would
			String expression = buildExpression(subsettingMap, derivedUnion);
			if(subsettingMap.isOne()){
				if(derivedUnionMap.isOne()){
					// TODO this could be problematic if multiple subsetting
					// properties are not null
					OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", OJAnnotatedOperation.RESULT + "=" + expression);
					sgetter.getBody().addToStatements(ifNotNull);
				}else{
					OJIfStatement ifNotNull = new OJIfStatement(expression + "!=null", OJAnnotatedOperation.RESULT + ".add(" + expression + ")");
					sgetter.getBody().addToStatements(ifNotNull);
				}
			}else{
				if(derivedUnionMap.isOne()){
					// TODO implement validation. This should not be allowed
				}else{
					sgetter.getBody().addToStatements(OJAnnotatedOperation.RESULT + ".addAll(" + expression + ")");
				}
			}
		}
	}
	protected boolean isNormalPropertyOverride(PropertyMap subsettingMap,PropertyMap derivedUnionMap){
		return(subsettingMap.getter().equals(derivedUnionMap.getter()) && subsettingMap.isOne() && derivedUnionMap.isOne());
	}
	private String buildExpression(PropertyMap mapOfSubsettingProperty,Property derivedUnionProperty){
		String expression = "this." + mapOfSubsettingProperty.getter() + "()";
		if(derivedUnionProperty.getName().equals(mapOfSubsettingProperty.fieldname())){
			// we need to avoid recursion now
			if(mapOfSubsettingProperty.getProperty().isDerived()){
				// do nothing the derivation rule will apply to the init
				// expression
			}else if( mapOfSubsettingProperty.getProperty().isDerivedUnion()){
				// TODO leverage the derivation here
				throw new IllegalStateException("A DerivedUnion subsetting another DerivedUnion must have a different name as the subsetted DerivedUnion");
			}else{
				//TODO For TINKER:::  !!!! implement some kind of z_internalGet
				expression="this." + mapOfSubsettingProperty.fieldname();
			}
		}
		return expression;
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
	}
}
