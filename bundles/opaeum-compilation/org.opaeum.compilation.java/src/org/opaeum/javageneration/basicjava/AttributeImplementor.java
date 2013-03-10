package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class,AssociationClassAttributeImplementor.class},after = {Java6ModelGenerator.class})
public class AttributeImplementor extends AbstractAttributeImplementer{
	@Override
	protected void visitProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
		Property p = map.getProperty();
		if(!OJUtil.isBuiltIn(p)){
			if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
				buildSetter(umlOwner, owner, map);
				buildField(owner, map).setTransient(true);
				OJOperation getter = buildGetter(umlOwner, owner, map, false);
				getter.setBody(new OJBlock());
				OJIfStatement ifNull = new OJIfStatement(OJAnnotatedOperation.RESULT + "==null", OJAnnotatedOperation.RESULT + "=" + map.fieldname() + "=("
						+ map.javaBaseType() + ")" + ojUtil.environmentPathname() + ".INSTANCE.getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				owner.addToImports(map.javaBaseTypePath());
			}else if(EmfPropertyUtil.isDerived(p)){
				OJAnnotatedOperation getter = buildGetter(umlOwner, owner, map, true);
				applyStereotypesAsAnnotations((p), getter);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	protected void implementAttributeFully(Classifier umlOwner,PropertyMap map){
		if(!isInvolvedInAnAssociationClass(map)){
			AttributeInJava a = new AttributeInJava();
			Property p = map.getProperty();
			OJAnnotatedClass owner = findJavaClass(umlOwner);
			OJAnnotatedField field = null;
			field = buildField(owner, map);
			a.field = field;
			// Association class properties should delegate to the internal removers of its emulated assocationProperty
			a.internalAdder = buildInternalAdder(owner, map);
			if(!p.isReadOnly()){
				a.internalRemover = buildInternalRemover(owner, map);
			}
			if(p.getOtherEnd() != null && isMap(p.getOtherEnd()) && EmfPropertyUtil.shouldImplementField(umlOwner, map.getProperty())){
				PropertyMap otherMAp = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
				ojUtil.addPersistentProperty(owner, otherMAp.qualifierProperty(), StdlibMap.javaStringType, true);
			}
			if(map.isMany()){
				if(field != null){
					buildInitExpression(owner, map, field);
				}
				buildAdder(owner, map);
				a.setter = buildSetter(umlOwner, owner, map);
				buildAddAll(owner, map);
				if(!p.isReadOnly()){
					buildRemoveAll(owner, map);
				}
				if(!p.isReadOnly()){
					buildRemover(owner, map);
					buildClear(owner, map);
				}
			}else{
				a.setter = buildSetter(umlOwner, owner, map);
			}
			a.getter = buildGetter(umlOwner, owner, map, false);
			if(field != null){
				applyStereotypesAsAnnotations((p), field);
				Classifier baseType = (Classifier) map.getBaseType();
				if(EmfClassifierUtil.isSimpleType(baseType)){
					applySimnpleTypesAnnotations(field, baseType);
				}
			}
			if(map.getBaseType() instanceof DataType){
				AttributeStrategy s = EmfClassifierUtil.getStrategy((DataType) map.getBaseType(), AttributeStrategy.class);
				if(s != null){
					s.applyTo(ojUtil, owner, a, map);
				}
			}
		}
	}
	private boolean isInvolvedInAnAssociationClass(PropertyMap map){
		boolean isInvolvedInAnAssociationClass = EmfAssociationUtil.isClass(map.getProperty().getAssociation()) || map.getBaseType() instanceof Association
				|| (map.getProperty().getOtherEnd() != null && map.getProperty().getOtherEnd().getType() instanceof Association);
		return isInvolvedInAnAssociationClass;
	}
	protected OJOperation buildAdder(OJAnnotatedClass owner,PropertyMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		if(isMap(map.getProperty())){
			addQualifierParameters(map, adder);
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			String internalAddStatement = map.internalAdder() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				String sb = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname());
				internalAddStatement = map.internalAdder() + "(" + sb + map.fieldname() + ")";
			}
			if(!(map.getProperty().getOtherEnd() == null || EmfPropertyUtil.isDerived(map.getProperty().getOtherEnd()))
					&& map.getProperty().getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((map.getProperty()).getOtherEnd());
				OJIfStatement ifNotNul2 = new OJIfStatement(map.fieldname() + "!=null");
				adder.getBody().addToStatements(ifNotNul2);
				if(otherMap.isMany()){
					if(!ojUtil.hasOJClass((Classifier) map.getProperty().getAssociation())){
						if(isMap(map.getProperty().getOtherEnd())){
							ifNotNul2.getThenPart().addToStatements(
									map.fieldname() + "." + otherMap.internalAdder() + "(" + ojUtil.addQualifierArguments(otherMap.getProperty().getQualifiers(), "this")
											+ "this)");
						}else if(map.getProperty().getAssociation() == null || !EmfAssociationUtil.isClass(map.getProperty().getAssociation())){
							ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
						}
					}
					ifNotNul2.getThenPart().addToStatements(internalAddStatement);
				}else{
					if(!otherMap.getProperty().isReadOnly()){
						ifNotNul2.getThenPart().addToStatements(
								map.fieldname() + "." + otherMap.internalRemover() + "(" + map.fieldname() + "." + otherMap.getter() + "())");
					}
					if(isMap(map.getProperty().getOtherEnd())){
						ifNotNul2.getThenPart()
								.addToStatements(
										map.fieldname() + "." + otherMap.internalAdder() + "(" + ojUtil.addQualifierArguments(otherMap.getProperty().getQualifiers(), "this")
												+ "this)");
					}else if(map.getProperty().getAssociation() == null || !EmfAssociationUtil.isClass(map.getProperty().getAssociation())){
						ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
					}
					ifNotNul2.getThenPart().addToStatements(internalAddStatement);
				}
			}else{
				adder.getBody().addToStatements(internalAddStatement);
			}
		}
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		owner.addToOperations(adder);
		return adder;
	}
	protected OJOperation buildRemover(OJAnnotatedClass owner,PropertyMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		Property p = map.getProperty();
		if(isMap(map.getProperty())){
			addQualifierParameters(map, remover);
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			remover.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifNotNull = new OJIfStatement(map.fieldname() + "!=null");
			ifNotNull.setName(IF_PARAM_NOT_NULL);
			String removeStatement = map.internalRemover() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				String sb = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname());
				removeStatement = map.internalRemover() + "(" + sb + map.fieldname() + ")";
			}
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((p).getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				if(!ojUtil.hasOJClass((Classifier) map.getProperty().getAssociation()) && !map.getProperty().getOtherEnd().isReadOnly()){
					ifNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(this)");
				}
				ifNotNull.getThenPart().addToStatements(removeStatement);
			}else{
				ifNotNull.getThenPart().addToStatements(removeStatement);
			}
		}
		owner.addToOperations(remover);
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
	}
}