package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.auditing.TinkerImplementAttributeCacheStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
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

public class AttributeImplementor extends StereotypeAnnotator{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	private AttributeImplementorStrategy attributeImplementorStrategy; 
	public final static String ATRTIBUTE_STRATEGY_TINKER = "TINKER";
	public final static String ATRTIBUTE_STRATEGY_HIBERNATE = "HIBERNATE";
	@Override
	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
		if (config.getAttributeImplementationStrategy().equals(ATRTIBUTE_STRATEGY_HIBERNATE)) {
			attributeImplementorStrategy = new HibernateAttributeImplementorStrategy();
		} else if (config.getAttributeImplementationStrategy().equals(ATRTIBUTE_STRATEGY_TINKER)) {
			attributeImplementorStrategy = new TinkerAttributeImplementorStrategy();
		}
	}
	@VisitAfter(matchSubclasses = true,match = {INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class})
	public void visitFeature(INakedClassifier entity){
		for(INakedProperty p:entity.getEffectiveAttributes()){
			if(p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)){
				if(p.getAssociation() instanceof INakedAssociationClass){
					// TODO test this may create duplicates
					// buildAssociationClassLogic(entity,
					// (INakedAssociationClass) p.getAssociation());
				}else{
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}
	@VisitBefore
	public void visitAssociationClass(INakedAssociationClass ac){
		//
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap((INakedProperty) ac.getEnd1());
		if(map.isManyToMany()){
			new AssocClassCreator().generateManyToMany(ac, findJavaClass(ac), findJavaClass(ac.getEnd1().getNakedBaseType()), findJavaClass(ac.getEnd2()
					.getNakedBaseType()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p){
		if(OJUtil.hasOJClass(p.getOwner())){
			if(p.getAssociation() instanceof INakedAssociationClass){
				// visitProperty(p.getOwner(),
				// OJUtil.buildAssociationClassMap(p,getOclEngine().getOclLibrary()));
			}else{
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitVariable(INakedActivityVariable var){
		if(BehaviorUtil.hasExecutionInstance(var.getActivity()) && var.getOwnerElement() instanceof INakedActivity){
			// Variables contained by StructuredActivityNodes require
			// contextable variables which will be delegated to BPM engine
			implementAttributeFully(var.getActivity(), OJUtil.buildStructuralFeatureMap(var.getActivity(), var));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOutputPin(INakedOutputPin node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			implementAttributeFully(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionNode(INakedExpansionNode node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			implementAttributeFully(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}
	@VisitBefore()
	public void visitOperation(INakedOperation o){
		if(o.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(o)){
			OperationMessageStructureImpl umlOwner = new OperationMessageStructureImpl(o);
			for(INakedParameter parm:o.getOwnedParameters()){
				implementAttributeFully(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
			}
		}
	}
	@VisitBefore()
	public void visitOpaqueAction(INakedOpaqueAction oa){
		if(oa.isTask()){
			OpaqueActionMessageStructureImpl umlOwner = new OpaqueActionMessageStructureImpl(oa);
			for(INakedPin pin:oa.getPins()){
				implementAttributeFully(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, pin, false));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node){
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			implementAttributeFully(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParameter(INakedParameter node){
		if(node.getOwnerElement() instanceof INakedBehavior){
			// Activity Parameters will be done through ParameterNodes
			INakedBehavior sm = (INakedBehavior) node.getOwnerElement();
			if(BehaviorUtil.hasExecutionInstance(sm) && sm.getSpecification() == null){
				implementAttributeFully(sm, OJUtil.buildStructuralFeatureMap(sm, node));
			}
		}
	}
	private void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
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
	private void implementAttributeFully(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = buildField(owner, map);
		if(map.isMany()){
			buildInitExpression(owner, map, field);
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
		applyStereotypesAsAnnotations((p), field);
		INakedClassifier baseType = p.getNakedBaseType();
		if(baseType instanceof INakedSimpleType){
			applySimnpleTypesAnnotations(field, baseType);
		}
	}
	private OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField();
		field.setType(map.javaTypePath());
		field.setName(map.umlName());
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
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
		
		owner.addToOperations(adder);
	}
	private void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalRemover());
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
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.adder());
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
			adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.remover());
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
			adder.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildRemoveAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.removeAll());
		adder.addParam(map.umlName(), map.javaTypePath());
		adder.setStatic(map.isStatic());
		OJAnnotatedField tmpList = new OJAnnotatedField();
		tmpList.setName("tmp");
		tmpList.setType(map.javaTypePath());
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
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.clearer());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildAddAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		// TODO put in NakedUmlSFMap
		adder.setName(map.allAdder());
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
	
	protected OJOperation buildSetter(INakedClassifier umlOwner, OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName(map.setter());
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
				attributeImplementorStrategy.addSimpleSetterBody(setter, map);
			}
		}
		return setter;
	}
}
