package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@StepDependency(phase = JavaTransformationPhase.class, requires = { Java6ModelGenerator.class}, after = { Java6ModelGenerator.class })
public class AttributeImplementor extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	protected AttributeImplementorStrategy attributeImplementorStrategy;
	@Override
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,TransformationContext context){
		super.initialize(javaModel, config, textWorkspace, context);
		try{
			attributeImplementorStrategy = (AttributeImplementorStrategy) Class.forName(config.getAttributeImplementationStrategy()).newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		// Do nothing
	}
	@VisitBefore(matchSubclasses=true)
	public void visitInterface(INakedInterface i){
		for(INakedProperty p:i.getOwnedAttributes()){
			visitProperty(i, OJUtil.buildStructuralFeatureMap(p));
			
		}
	}
	protected void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p)){
			if(p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				buildSetter(umlOwner, owner, map);
				buildField(owner, map).setTransient(true);
				OJOperation getter = attributeImplementorStrategy.buildGetter(owner, map, false);
				getter.setBody(new OJBlock());
				OJIfStatement ifNull = new OJIfStatement(map.umlName() + "==null", map.umlName() + "=(" + map.javaBaseType()
						+ ")org.nakeduml.environment.Environment.getInstance().getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				getter.getBody().addToStatements("return " + map.umlName());
				owner.addToImports(map.javaBaseTypePath());
			}else if(p.isDerived() || p.isReadOnly()){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				OJOperation getter = attributeImplementorStrategy.buildGetter(owner, map, true);
				applyStereotypesAsAnnotations((p), getter);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	@VisitBefore
	public void visitAssociationClass(INakedAssociationClass ac){
		//
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap((INakedProperty) ac.getEnd1());
		if(map.isManyToMany()){
			new AssociationClassCreator().generateManyToMany(ac, findJavaClass(ac), findJavaClass(ac.getEnd1().getNakedBaseType()), findJavaClass(ac.getEnd2()
					.getNakedBaseType()));
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = null;
		field = buildField(owner, map);
		if(map.isMany()){
			if(field != null){
				buildInitExpression(owner, map, field);
			}
			buildAdder(owner, map);
			buildAddAll(owner, map);
			buildRemover(owner, map);
			buildRemoveAll(owner, map);
			buildClear(owner, map);
		}else if(map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface){
			buildInternalAdder(owner, map);
			buildInternalRemover(owner, map);
		}
		buildSetter(umlOwner, owner, map);
		attributeImplementorStrategy.buildGetter(owner, map, false);
		if(field != null){
			applyStereotypesAsAnnotations((p), field);
			INakedClassifier baseType = p.getNakedBaseType();
			if(baseType instanceof INakedSimpleType){
				applySimnpleTypesAnnotations(field, baseType);
			}
		}
	}
	OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField(map.umlName(),map.javaTypePath());
		if(map.isJavaPrimitive() || map.isCollection()){
			field.setInitExp(map.javaDefaultValue());
		}
		owner.addToFields(field);
		return field;
	}
	private void applySimnpleTypesAnnotations(OJAnnotatedField field,INakedClassifier baseType){
		applyStereotypesAsAnnotations(baseType, field);
		for(INakedGeneralization g:baseType.getNakedGeneralizations()){
			applySimnpleTypesAnnotations(field, g.getGeneral());
		}
		for(INakedInterfaceRealization g:baseType.getInterfaceRealizations()){
			applySimnpleTypesAnnotations(field, g.getContract());
		}
	}
	private void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
		owner.addToOperations(adder);
	}
	private void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.internalRemover());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		String remove;
		remove = "this." + map.umlName() + "=null";
		OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()!=null && " + map.getter() + "().equals(" + map.umlName() + ")", remove);
		adder.getBody().addToStatements(ifEquals);
		owner.addToOperations(adder);
	}
	private void buildInitExpression(OJAnnotatedClass owner,NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		field.setInitExp("new " + defaultValue.getCollectionTypeName() + "()");
	}
	private OJOperation buildAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		INakedProperty p = map.getProperty();
		if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())){
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if(otherMap.isMany()){
				attributeImplementorStrategy.buildManyAdder(map, otherMap, adder);
			}else{
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.setter() + "(this)");
				// if(p.getBaseType() instanceof INakedInterface){
				// adder.getBody().addToStatements(map.umlName() + "." +
				// otherMap.getter() + "().add(this)");
				// }
			}
		}else{
			attributeImplementorStrategy.addSimpleAdder(map, adder);
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.remover());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		INakedProperty p = map.getProperty();
		if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())){
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if(otherMap.isMany()){
				attributeImplementorStrategy.buildManyRemover(map, otherMap, adder);
			}else{
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.setter() + "(null)");
			}
		}else{
			attributeImplementorStrategy.buildSimpleRemover(map, adder);
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildRemoveAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.removeAll());
		adder.addParam(map.umlName(), map.javaTypePath());
		adder.setStatic(map.isStatic());
		OJAnnotatedField tmpList = new OJAnnotatedField("tmp",map.javaTypePath());
		tmpList.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + map.umlName() + ")");
		adder.getBody().addToLocals(tmpList);
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection("tmp");
		forAll.setElemName("o");
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		forAll.getBody().addToStatements(map.remover() + "(o)");
		adder.getBody().addToStatements(forAll);
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildClear(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.clearer());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildAddAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.allAdder());
		// TODO put in NakedUmlSFMap
		adder.addParam(map.umlName(), map.javaTypePath());
		adder.setStatic(map.isStatic());
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection(map.umlName());
		forAll.setElemName("o");
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		forAll.getBody().addToStatements(map.adder() + "(o)");
		adder.getBody().addToStatements(forAll);
		owner.addToOperations(adder);
		return adder;
	}
	protected OJOperation buildSetter(INakedClassifier umlOwner,OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.umlName(), map.javaTypePath());
		setter.setStatic(map.isStatic());
		owner.addToOperations(setter);
		if(owner instanceof OJAnnotatedInterface){
		}else{
			INakedProperty prop = map.getProperty();
			if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				if(map.isManyToOne()){
					attributeImplementorStrategy.buildManyToOneSetter(umlOwner, map, otherMap, owner, setter);
				}else if(map.isOneToMany()){
					attributeImplementorStrategy.buildOneToManySetter(map, otherMap, owner, setter);
				}else if(map.isManyToMany()){
					attributeImplementorStrategy.buildManyToManySetter(map, otherMap, owner, setter);
				}else if(map.isOneToOne()){
					attributeImplementorStrategy.buildOneToOneSetter(umlOwner, map, otherMap, owner, setter);
				}
			}else{
				attributeImplementorStrategy.addSimpleSetterBody(umlOwner, map, owner, setter);
			}
		}
		return setter;
	}
}
