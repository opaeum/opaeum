package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class AssociationClassAttributeImplementor extends AbstractAttributeImplementer{
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojOwner,Classifier umlOwner){
		if(umlOwner instanceof Association){
			OJConstructor constr1 = new OJConstructor();
			Association assocClass = (Association) umlOwner;
			EmulatedPropertyHolderForAssociation holder = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(assocClass);
			Property end1 = assocClass.getMemberEnds().get(0);
			PropertyMap mapToEnd1 = ojUtil.buildStructuralFeatureMap(holder.getEmulatedAttribute(end1));
			Property end2 = assocClass.getMemberEnds().get(1);
			PropertyMap mapToEnd2 = ojUtil.buildStructuralFeatureMap(holder.getEmulatedAttribute(end2));
			constr1.addParam("end1", mapToEnd1.javaTypePath());
			constr1.addParam("end2", mapToEnd2.javaTypePath());
			constr1.getBody().addToStatements("this." + mapToEnd1.internalAdder() + "(end1)");
			constr1.getBody().addToStatements("this." + mapToEnd2.internalAdder() + "(end2)");
			ojOwner.addToConstructors(constr1);
			OJConstructor constr2 = new OJConstructor();
			ojOwner.addToConstructors(constr2);
			constr2.addParam("end2", mapToEnd2.javaTypePath());
			constr2.addParam("end1", mapToEnd1.javaTypePath());
			constr2.getBody().addToStatements("this." + mapToEnd1.internalAdder() + "(end1)");
			constr2.getBody().addToStatements("this." + mapToEnd2.internalAdder() + "(end2)");
			OJAnnotatedOperation clear = new OJAnnotatedOperation("clear");
			ojOwner.addToOperations(clear);
			if(end1.isNavigable()){
				if(end1.isNavigable()){
					clear.getBody().addToStatements("this." + mapToEnd2.internalRemover() + "(" + mapToEnd2.getter() + "())");
				}
			}
			if(end2.isNavigable()){
				if(end2.isNavigable()){
					clear.getBody().addToStatements("this." + mapToEnd1.internalRemover() + "(" + mapToEnd1.getter() + "())");
				}
			}
			buildField(ojOwner, mapToEnd1);
			buildField(ojOwner, mapToEnd2);
			buildInternalAdder(ojOwner, mapToEnd1);
			buildInternalAdder(ojOwner, mapToEnd2);
			buildInternalRemover(ojOwner, mapToEnd1);
			buildInternalRemover(ojOwner, mapToEnd2);
			buildGetter(umlOwner, ojOwner, mapToEnd1, false);
			buildGetter(umlOwner, ojOwner, mapToEnd2, false);
			if(end1 != null && isMap(end1)){
				PropertyMap otherMAp = ojUtil.buildStructuralFeatureMap(end1);
				ojUtil.addPersistentProperty(ojOwner, otherMAp.qualifierProperty(), StdlibMap.javaStringType, true);
			}
			if(end2 != null && isMap(end2)){
				PropertyMap otherMAp = ojUtil.buildStructuralFeatureMap(end2);
				ojUtil.addPersistentProperty(ojOwner, otherMAp.qualifierProperty(), StdlibMap.javaStringType, true);
			}
		}
		return true;
	}
	@Override
	protected void visitProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
	}
	@Override
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJAnnotatedClass owner = findJavaClass(c);
		if(EmfPropertyUtil.isDerived(map.getProperty())){
			buildGetter(c, owner, aMap);
		}else{
			if(map.isMany()){
				buildAddAll(owner, map);
				buildRemoveAll(owner, map);
				// These are all the same as for normal attributes
				buildClear(owner, map);
				buildAdder(owner, aMap);
				buildRemover(owner, aMap);
			}
			buildInternalAdder(owner, aMap.getEndToAssocationClassMap());
			buildInternalRemover(owner, aMap.getEndToAssocationClassMap());
			buildField(owner, aMap.getEndToAssocationClassMap());
			buildGetter(c, owner, aMap.getEndToAssocationClassMap(), false);
			buildSetter(c, owner, aMap);
			// Here are the deviations from normal attributes
			buildGetter(c, owner, aMap);
			buildGetterFor(owner, aMap);
		}
	}
	protected OJOperation buildAdder(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		if(isMap(map.getProperty())){
			addQualifierParameters(map, adder);
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			String thisEndsQualifierArgs = "";
			if(isMap(map.getProperty())){
				thisEndsQualifierArgs = ojUtil.delegateQualifierArguments(map.getProperty().getQualifiers());
			}
			OJIfStatement ifNotNull = new OJIfStatement(map.fieldname() + "!=null && !" + getReferencePrefix(owner, map) + map.getter() + "().contains("
					+ map.fieldname() + ")");
			adder.getBody().addToStatements(ifNotNull);
			PropertyMap mapToAssociation = aMap.getEndToAssocationClassMap();
			OJAnnotatedField link = new OJAnnotatedField("newLink", mapToAssociation.javaBaseTypePath());
			ifNotNull.getThenPart().addToLocals(link);
			link.setInitExp("new " + mapToAssociation.javaBaseType() + "((" + aMap.getAssociationClassToThisEndMap().javaType() + ")this,("
					+ aMap.getAssocationClassToOtherEndMap().javaType() + ")" + map.fieldname() + ")");
			if(isOtherEndModifiable(map)){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(map.getProperty().getOtherEnd());
				String otherEndsQualifierArgs = "";
				if(isMap(map.getProperty().getOtherEnd())){
					otherEndsQualifierArgs = ojUtil.addQualifierArguments(map.getProperty().getOtherEnd().getQualifiers(), "this");
					addCheckForNullQualifiers(map, otherMap, ifNotNull.getThenPart());

				}
				if(map.isOneToMany()){
					OJIfStatement ifOthersOldValueNotNull = new OJIfStatement(map.fieldname() + "." + otherMap.getter() + "()!=null");
					String otherArgs = map.fieldname();
					if(isMap(map.getProperty())){
						otherArgs = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname()) + map.fieldname();
					}
					ifOthersOldValueNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.getter() + "()." + map.remover() + "(" + otherArgs + ")");
					ifNotNull.getThenPart().addToStatements(ifOthersOldValueNotNull);
				}
				PropertyMap otherMapToAssociation = aMap.getOtherEndToAssocationClassMap();
				ifNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + mapToAssociation.internalAdder() + "(" + thisEndsQualifierArgs + "newLink)");
				ifNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMapToAssociation.internalAdder() + "(" + otherEndsQualifierArgs + "newLink)");
			}
		}
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		owner.addToOperations(adder);
		return adder;
	}
	protected OJOperation buildRemover(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
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
			remover.getBody().addToStatements(ifNotNull);
			PropertyMap mapToAssociation = aMap.getEndToAssocationClassMap();
			OJAnnotatedField link = new OJAnnotatedField("oldLink", mapToAssociation.javaBaseTypePath());
			ifNotNull.getThenPart().addToLocals(link);
			OJIfStatement ifExisting = new OJIfStatement("oldLink!=null");
			ifNotNull.getThenPart().addToStatements(ifExisting);
			link.setInitExp(mapToAssociation.getter() + "For(" + map.fieldname() + ")");
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				PropertyMap mapFromOtherEndToAssociation = aMap.getOtherEndToAssocationClassMap();
				String removeArgs = "oldLink";
				if(isMap(map.getProperty().getOtherEnd())){
					removeArgs = ojUtil.addQualifierArguments(map.getProperty().getOtherEnd().getQualifiers(), "this") + "oldLink";
				}
				ifExisting.getThenPart().addToStatements(map.fieldname() + "." + mapFromOtherEndToAssociation.internalRemover() + "(" + removeArgs + ")");
			}
			String removeArgs = "oldLink";
			if(isMap(map.getProperty())){
				removeArgs = ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), map.fieldname()) + "oldLink";
			}
			ifExisting.getThenPart().addToStatements("oldLink.clear()");
			ifExisting.getThenPart().addToStatements(mapToAssociation.internalRemover() + "(" + removeArgs + ")");
		}
		owner.addToOperations(remover);
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
	}
	private void buildGetterFor(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap mapToAssocationClass = aMap.getEndToAssocationClassMap();
		OJAnnotatedOperation getter = new OJAnnotatedOperation(mapToAssocationClass.getter() + "For", mapToAssocationClass.javaBaseTypePath());
		getter.addParam("match", aMap.getAssocationClassToOtherEndMap().javaBaseTypePath());
		owner.addToOperations(getter);
		if(mapToAssocationClass.isMany()){
			OJForStatement forEach = new OJForStatement("var", mapToAssocationClass.javaBaseTypePath(), mapToAssocationClass.getter() + "()");
			getter.getBody().addToStatements(forEach);
			forEach.getBody().addToStatements(new OJIfStatement("var." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(match)", "return var"));
			getter.getBody().addToStatements("return null");
		}else{
			OJIfStatement ifEquals = new OJIfStatement(mapToAssocationClass.fieldname() + "." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(match)");
			getter.getBody().addToStatements(ifEquals);
			ifEquals.getThenPart().addToStatements("return " + mapToAssocationClass.fieldname());
			ifEquals.setElsePart(new OJBlock());
			ifEquals.getElsePart().addToStatements("return null");
		}
	}
	private void buildGetter(Classifier umlOwner,OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			getter.setStatic(map.isStatic());
			getter.initializeResultVariable(map.javaDefaultValue());
			PropertyMap mapToAssClass = aMap.getEndToAssocationClassMap();
			Association assc = (Association) map.getProperty().getAssociation();
			EmulatedPropertyHolderForAssociation holder = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(assc);
			Property fromAssToOtherEnd = holder.getEmulatedAttribute(map.getProperty());
			PropertyMap mapFromAssClassToOtherEnd = ojUtil.buildStructuralFeatureMap(fromAssToOtherEnd);
			if(map.isMany()){
				OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), getReferencePrefix(owner, map) + mapToAssClass.getter() + "()");
				getter.getBody().addToStatements(foreach);
				foreach.getBody().addToStatements("result.add(cur." + mapFromAssClassToOtherEnd.getter() + "())");
			}else{
				OJIfStatement ifNotNull = new OJIfStatement(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "!=null");
				getter.getBody().addToStatements(ifNotNull);
				ifNotNull.getThenPart().addToStatements(
						"result = " + getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "." + aMap.getAssocationClassToOtherEndMap().getter() + "()");
			}
		}
		addPropertyMetaInfo(umlOwner, getter, map.getProperty(), getLibrary());
		owner.addToOperations(getter);
	}
	private OJAnnotatedOperation buildSetter(Classifier umlOwner,OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJAnnotatedOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.fieldname(), map.javaTypePath());
		Property prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			removeFromPropertiesQualifiedByThisProperty(map, setter);
			PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(prop.getOtherEnd());
			if(map.isMany()){
				if(isOtherEndModifiable(map.getProperty())){
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
				}
				setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				// Delegates to adders and clearers
			}else if(map.isManyToOne()){
				String args = "";
				PropertyMap mapToAssociation = aMap.getEndToAssocationClassMap();
				OJIfStatement ifNewValueNull = new OJIfStatement(map.fieldname() + " == null", getReferencePrefix(owner, map) + mapToAssociation.internalRemover()
						+ "(" + getReferencePrefix(owner, map) + mapToAssociation.getter() + "())");
				ifNewValueNull.setElsePart(new OJBlock());
				if(isMap(prop.getOtherEnd())){
					args = ojUtil.addQualifierArguments(prop.getOtherEnd().getQualifiers(), "this");
					addCheckForNullQualifiers(map, otherMap, ifNewValueNull.getElsePart());
				}
				// remove "this" from existing reference
				OJIfStatement ifCurrentValueNotNull = new OJIfStatement();
				ifCurrentValueNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
				setter.getBody().addToStatements(ifCurrentValueNotNull);
				setter.getBody().addToStatements(ifNewValueNull);
				OJAnnotatedField link = new OJAnnotatedField(mapToAssociation.fieldname(), mapToAssociation.javaBaseTypePath());
				ifNewValueNull.getElsePart().addToLocals(link);
				link.setInitExp("new " + mapToAssociation.javaBaseType() + "((" + aMap.getAssociationClassToThisEndMap().javaType() + ")this,("
						+ aMap.getAssocationClassToOtherEndMap().javaType() + ")" + map.fieldname() + ")");
				ifNewValueNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + mapToAssociation.internalAdder() + "(" + link.getName() + ")");
				if(isModifiable(prop.getOtherEnd())){
					ifCurrentValueNotNull.getThenPart().addToStatements(
							getReferencePrefix(owner, map) + map.getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalRemover() + "(" + args
									+ aMap.getEndToAssocationClassMap().getter() + "())");
					String otherArgs = link.getName();
					if(isMap(prop.getOtherEnd())){
						otherArgs = ojUtil.addQualifierArguments(prop.getOtherEnd().getQualifiers(), "this") + link.getName();
					}
					ifNewValueNull.getElsePart().addToStatements(map.fieldname() + "." + aMap.getOtherEndToAssocationClassMap().internalAdder() + "(" + otherArgs + ")");
				}
			}else if(map.isOneToOne()){
				OJAnnotatedField oldValue = new OJAnnotatedField("oldValue", map.javaTypePath());
				oldValue.setInitExp(getReferencePrefix(owner, map) + map.getter() + "()");
				setter.getBody().addToLocals(oldValue);
				// If oldValue==null then set the new Value unconditionally
				OJIfStatement ifNull = new OJIfStatement();
				ifNull.setName(AssociationClassAttributeImplementor.IF_OLD_VALUE_NULL);
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
		}
		addToPropertiesQualifiedByThisProperty(map, setter);
		return setter;
	}
}