package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;

@StepDependency(phase = JavaTransformationPhase.class,after = {OperationAnnotator.class,AttributeImplementor.class})
public class DerivedUnionImplementor extends AbstractStructureVisitor{
	@Override
	protected int getThreadPoolSize(){
		return 1;// Does work across multiple models
	}
	@Override
	public void visitProperty(OJAnnotatedClass c,Classifier owner,PropertyMap derivedUnionMap){
		if(derivedUnionMap.getProperty().isNavigable() && derivedUnionMap.getProperty().isDerivedUnion()){
			Collection<Property> directlyImplementedProperties = getLibrary().getDirectlyImplementedAttributes(owner);
			OJAnnotatedOperation getter = (OJAnnotatedOperation) c.findOperation(derivedUnionMap.getter(), new ArrayList<OJPathName>());
			getter.getResultVariable().setType(derivedUnionMap.javaDefaultTypePath());
			if(derivedUnionMap.isMany()){
				// ENsure the result variable is initialized with the right type and keep the initExpression's value in tact in case something
				// meaningful was put in there (unlikely, but possible if the subsetting prop has the same name as the derived union and it has a
				// derivation rule)
				String initExp = getter.getResultVariable().getInitExp();
				String javaDefaultValue1 = derivedUnionMap.javaDefaultValue();
				javaDefaultValue1 = javaDefaultValue1.substring(0, javaDefaultValue1.length() - 2);
				if(!initExp.startsWith(javaDefaultValue1)){
					getter.getResultVariable().setInitExp(javaDefaultValue1 + initExp + ")");
				}
			}
			if(owner.getGenerals().size() > 0
					&& EmfClassifierUtil.conformsTo(owner.getGenerals().get(0), EmfPropertyUtil.getOwningClassifier(derivedUnionMap.getProperty()))){
				// Derived union would be initialized by parent class
				if(derivedUnionMap.isMany()){
					getter.getBody().addToStatements(OJAnnotatedOperation.RESULT + "addAll(super." + derivedUnionMap.getter() + "())");
				}else{
					// Overwrite an existing value = last in the subsetting hierarchy always wins
					String condition = "result==null && super." + derivedUnionMap.getter() + "()!=null";
					OJIfStatement ifNotNull = new OJIfStatement(condition, OJAnnotatedOperation.RESULT + "=super." + derivedUnionMap.getter() + "()");
					// Add it to to top of the body as we do not want to overwrite and existing value: last
					// in the subsetting hierarchy always wins.
					getter.getBody().getStatements().add(0, ifNotNull);
				}
			}
			for(Property subsettingProperty:EmfPropertyUtil.getSubsettingProperties(derivedUnionMap.getProperty())){
				if(directlyImplementedProperties.contains(subsettingProperty)){
					addSubsetToUnion(owner, ojUtil.buildStructuralFeatureMap(subsettingProperty), c, derivedUnionMap, getter);
				}
			}
		}
	}
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
		visitProperty(findJavaClass(c), c, map.getMap());
	}
	private void addSubsetToUnion(Classifier owner,PropertyMap subsettingMap,OJClass c,PropertyMap derivedUnionMap,OJAnnotatedOperation getter){
		if(isNormalPropertyOverride(subsettingMap, derivedUnionMap)){
			// Do nothing. Remember that previously we kept the init expression of the getter's result variable in tact, so this value would be
			// there
		}else{
			OJAnnotatedOperation sgetter = getter;
			// TODO when property override occurs on a many property, the getter needs to be changed
			// This would require us to us z_internalAdd again as the derived set would
			String expression = "this." + subsettingMap.getter() + "()";
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
					if(derivedUnionMap.umlName().equals(subsettingMap.umlName())){
						// We will already be in the getter
						if(subsettingMap.getProperty().getAssociation() != null
								&& EmfAssociationUtil.isClass(subsettingMap.getProperty().getAssociation())){
							// Result would already be extracted from the AssociationClass objects
						}else{
							String javaDefaultValue = subsettingMap.javaDefaultValue();
							sgetter.initializeResultVariable(javaDefaultValue.substring(0, javaDefaultValue.length() - 2) + subsettingMap.fieldname()
									+ ")");
						}
					}else{
						sgetter.getBody().addToStatements(OJAnnotatedOperation.RESULT + ".addAll(" + expression + ")");
					}
				}
			}
		}
	}
	protected boolean isNormalPropertyOverride(PropertyMap subsettingMap,PropertyMap derivedUnionMap){
		return subsettingMap.getter().equals(derivedUnionMap.getter());
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass c,Classifier umlOwner){
		return true;
	}
}
