package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.emf.common.util.EList;
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
	protected OJOperation buildAdder(OJAnnotatedClass owner,PropertyMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		if(isMap(map.getProperty())){
			addQualifierParameters(map, adder);
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			if(isOtherEndModifiable(map)){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((map.getProperty()).getOtherEnd());
				OJIfStatement ifNotNul2 = new OJIfStatement(map.fieldname() + "!=null");
				adder.getBody().addToStatements(ifNotNul2);
				String args = "this";
				if(otherMap.isMany()){
					if(isMap(map.getProperty().getOtherEnd())){
						args = ojUtil.addQualifierArguments(otherMap.getProperty().getQualifiers(),"this") + "this";
						addCheckForNullQualifiers(map, otherMap, ifNotNul2.getThenPart());
					}
				}else{
					OJIfStatement ifOthersOldValueNotNull = new OJIfStatement(map.fieldname() + "." + otherMap.getter() + "()!=null");
					String otherArgs = map.fieldname();
					if(isMap(map.getProperty())){
						otherArgs = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname()) + map.fieldname();
					}
					ifOthersOldValueNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.getter() + "()." + map.remover() + "(" + otherArgs + ")");
					ifNotNul2.getThenPart().addToStatements(ifOthersOldValueNotNull);
				}
				ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(" + args + ")");
			}
			String internalAddStatement = map.internalAdder() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				String sb = ojUtil.delegateQualifierArguments(map.getProperty().getQualifiers());
				internalAddStatement = map.internalAdder() + "(" + sb + map.fieldname() + ")";
			}
			adder.getBody().addToStatements(internalAddStatement);
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
			if(isOtherEndModifiable(map)){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				String args = "this";
				if(isMap(p.getOtherEnd())){
					args = ojUtil.addQualifierArguments(p.getOtherEnd().getQualifiers(), "this") + "this";
				}
				ifNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(" + args + ")");
			}
			String removeStatement = map.internalRemover() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				String sb = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname());
				removeStatement = map.internalRemover() + "(" + sb + map.fieldname() + ")";
			}
			ifNotNull.getThenPart().addToStatements(removeStatement);
		}
		owner.addToOperations(remover);
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
	}
	protected OJAnnotatedOperation buildSetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.fieldname(), map.javaTypePath());
		Property prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			removeFromPropertiesQualifiedByThisProperty(map, setter);
			if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
				setter.getBody().addToStatements("this." + map.fieldname() + "=" + map.fieldname());
			}else if(isOtherEndModifiable(prop)){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(prop.getOtherEnd());
				if(map.isManyToOne()){
					String args = buildQualifiedArgumentStringForOtherEnd(prop);
					// remove "this" from existing reference
					OJIfStatement ifCurrentValueNotNull = new OJIfStatement();
					ifCurrentValueNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
					ifCurrentValueNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.getter() + "()." + otherMap.internalRemover() + args);
					setter.getBody().addToStatements(ifCurrentValueNotNull);
					OJIfStatement ifNewValueNull = new OJIfStatement(map.fieldname() + " == null", getReferencePrefix(owner, map) + map.internalRemover() + "("
							+ getReferencePrefix(owner, map) + map.getter() + "())");
					setter.getBody().addToStatements(ifNewValueNull);
					ifNewValueNull.setElsePart(new OJBlock());
					if(isMap(otherMap.getProperty())){
						addCheckForNullQualifiers(map, otherMap, ifNewValueNull.getElsePart());
					}

					ifNewValueNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					// add "this" to new reference
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + args);
					setter.getBody().addToStatements(ifParamNotNull);
				}else if(map.isMany()){
					if(!map.getProperty().isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					}
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else if(map.isOneToOne()){
					OJAnnotatedField oldValue = new OJAnnotatedField("oldValue", map.javaTypePath());
					oldValue.setInitExp(getReferencePrefix(owner, map) + map.getter() + "()");
					setter.getBody().addToLocals(oldValue);
					// If oldValue==null then set the new Value unconditionally
					OJIfStatement ifNull = new OJIfStatement();
					ifNull.setName(AttributeImplementor.IF_OLD_VALUE_NULL);
					ifNull.setCondition("oldValue==null");// && );
					setter.getBody().addToStatements(ifNull);
					OJIfStatement ifNotSame = new OJIfStatement();
					ifNotSame.setCondition("!oldValue.equals(" + map.fieldname() + ")");
					ifNull.addToElsePart(ifNotSame);
					// remove "this" from existing reference
					ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
					ifNotSame.getThenPart().addToStatements(map.internalRemover() + "(oldValue)");
					// add "this" to new reference\
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(
							owner.getName() + " oldOther = (" + owner.getName() + ")" + map.fieldname() + "." + otherMap.getter() + "()");
					if(!prop.isReadOnly()){
						ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(oldOther)");
					}
					ifParamNotNull.getThenPart().addToStatements(
							new OJIfStatement("oldOther != null", "oldOther" + "." + map.internalRemover() + "(" + map.fieldname() + ")"));
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
					ifNotSame.getThenPart().addToStatements(ifParamNotNull);
					ifNotSame.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					ifNull.getThenPart().addToStatements(ifParamNotNull);
					ifNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
				}
			}else{
				if(map.isMany()){
					if(!map.getProperty().isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					}
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else{
					if(prop.isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					}else{
						OJIfStatement ifNull = new OJIfStatement(map.fieldname() + " == null", getReferencePrefix(owner, map) + map.internalRemover() + "(" + map.getter()
								+ "())");
						setter.getBody().addToStatements(ifNull);
						ifNull.setElsePart(new OJBlock());
						ifNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					}
				}
			}
			addToPropertiesQualifiedByThisProperty(map, setter);
		}
		return setter;
	}
}