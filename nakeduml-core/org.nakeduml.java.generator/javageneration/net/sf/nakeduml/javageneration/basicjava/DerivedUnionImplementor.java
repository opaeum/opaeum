package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,after = {
		OperationAnnotator.class,AttributeImplementor.class
})
public class DerivedUnionImplementor extends AbstractStructureVisitor{
	@Override
	protected int getThreadPoolSize(){
		return 1;// Does work across multiple models
	}
	public void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(p.isNavigable()){
			OJClass c = findJavaClass(owner);
			for(INakedProperty derivedUnion:p.getSubsettedProperties()){
				addSubsetToUnion(owner, map, c, derivedUnion);
			}
		}
	}
	private OJAnnotatedOperation findOrCreateDerivedUnionGetter(INakedClassifier owner,NakedStructuralFeatureMap derivedUnionMap,OJClass c){
		// just a default implementation so that all subclasses are not required
		// to implement it. Remember this getter may have been implemented in the superclass only.
		OJAnnotatedOperation getter = (OJAnnotatedOperation) OJUtil.findOperation(c, derivedUnionMap.getter());
		if(getter == null){
			getter = new OJAnnotatedOperation(derivedUnionMap.getter());
			getter.setReturnType(derivedUnionMap.javaTypePath());
			c.addToOperations(getter);
			c.addToImports(derivedUnionMap.javaDefaultTypePath());
			getter.initializeResultVariable(derivedUnionMap.javaDefaultValue());
			if(!(derivedUnionMap.getProperty().getOwner() instanceof INakedInterface)){
				if(derivedUnionMap.isMany()){
					getter.getBody().addToStatements(OJAnnotatedOperation.RESULT + ".addAll(super." + derivedUnionMap.getter() + "())");
				}else{
					getter.getBody().addToStatements(OJAnnotatedOperation.RESULT + "=super." + derivedUnionMap.getter() + "()");
				}
			}
		}
		return getter;
	}
	private void addSubsetToUnion(INakedClassifier owner,NakedStructuralFeatureMap subsettingMap,OJClass c,INakedProperty derivedUnion){
		NakedStructuralFeatureMap derivedUnionMap = new NakedStructuralFeatureMap(derivedUnion);
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
	protected boolean isNormalPropertyOverride(NakedStructuralFeatureMap subsettingMap,NakedStructuralFeatureMap derivedUnionMap){
		return(subsettingMap.getter().equals(derivedUnionMap.getter()) && subsettingMap.isOne() && derivedUnionMap.isOne());
	}
	private String buildExpression(NakedStructuralFeatureMap mapOfSubsettingProperty,INakedProperty derivedUnionProperty){
		String expression = "this." + mapOfSubsettingProperty.getter() + "()";
		if(derivedUnionProperty.getName().equals(mapOfSubsettingProperty.umlName())){
			// we need to avoid recursion now
			if(mapOfSubsettingProperty.getFeature().isDerived()){
				// do nothing the derivation rule will apply to the init
				// expression
			}else if(((INakedProperty) mapOfSubsettingProperty.getFeature()).isDerivedUnion()){
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
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
	}
}
