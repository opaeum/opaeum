package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
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
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public class TinkerAuditAttributeImplementor extends StereotypeAnnotator{
	
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_IF = "buildPolymorphicGetterForToOneIf";
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_TRY = "buildPolymorphicGetterForToOneTry";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_FOR = "buildPolymorphicGetterForToManyFor";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_TRY = "buildPolymorphicGetterForToManyTry";
	
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	private AttributeImplementorStrategy attributeImplementorStrategy; 
	public final static String ATRTIBUTE_STRATEGY_TINKER = "TINKER";
	@Override
	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
		attributeImplementorStrategy = new TinkerAttributeImplementorStrategy(true);
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
	private void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p)){
			if(p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
				OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
				OJOperation getter = attributeImplementorStrategy.buildGetter(owner, map, false);
				getter.setBody(new OJBlock());
				OJIfStatement ifNull = new OJIfStatement(map.umlName() + "==null", map.umlName() + "=(" + map.javaBaseType()
						+ ")org.nakeduml.environment.Environment.getInstance().getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				getter.getBody().addToStatements("return " + map.umlName());
				owner.addToImports(map.javaBaseTypePath());
			}else if(p.isDerived() || p.isReadOnly()){
				OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
				OJOperation getter = buildGetter(owner, map, true);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	private void implementAttributeFully(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
		buildGetter(owner, map, false);
	}
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaAuditTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			INakedProperty prop = map.getProperty();
			owner.addToImports(TinkerUtil.edgePathName);
			owner.addToImports(TinkerUtil.vertexPathName);
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne() && map.getProperty().getSubsettedProperties().isEmpty()) {
					buildPolymorphicGetterForToOne(map, getter);
				} else if (map.isOneToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isManyToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isOneToOne()) {
					buildPolymorphicGetterForToOne(map, getter);
				}
			} else {
				getter.getBody().addToStatements(
						"return (" + map.javaAuditBaseType() + ") this.vertex.getProperty(\""
								+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}
	
	private void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		INakedClassifier otherClassifier = map.getProperty().getOtherEnd().getOwner();
		INakedClassifier otherSuperClassifier = otherClassifier.getSupertype();
		String otherClassName = otherSuperClassifier == null ? otherClassifier.getMappingInfo().getJavaName().getAsIs() : otherSuperClassifier.getMappingInfo()
				.getJavaName().getAsIs();
		String otherAssociationName = map.getProperty().getAssociation().getName();
		if (isComposite) {
			getter.getBody().addToStatements("Iterable<Edge> iter1 = this.vertex.getOutEdges(\"" + otherAssociationName + "\")");
		} else {
			getter.getBody().addToStatements("Iterable<Edge> iter1 = this.vertex.getInEdges(\"" + otherAssociationName + "\")");
		}
		OJIfStatement ifStatement = new OJIfStatement("iter1.iterator().hasNext()");
		ifStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		ifStatement.addToThenPart("Edge edge = iter1.iterator().next()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName + TinkerAuditCreator.AUDIT
							+ ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName + TinkerAuditCreator.AUDIT
							+ ") c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");

		}
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		getter.getBody().addToStatements(ifStatement);
		getter.getBody().addToStatements("return null");
	}
	
	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		OJField result = new OJField();
		result.setType(map.javaAuditTypePath());
		result.setName("result");
		result.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 3) + TinkerAuditCreator.AUDIT + ">()" );
		OJPathName defaultValue = map.javaDefaultTypePath();
		getter.getOwner().addToImports(defaultValue);

		getter.getBody().addToLocals(result);
		INakedClassifier manyClassifier = map.getProperty().getOtherEnd().getOwner();

		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		if (isComposite) {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		} else {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		forStatement.getBody().addToStatements(ojTryStatement);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()+TinkerAuditCreator.AUDIT + ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()+TinkerAuditCreator.AUDIT+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex()))");
		}

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getter.getBody().addToStatements(forStatement);
		getter.getBody().addToStatements("return result");
	}

	private boolean calculateDirection(NakedStructuralFeatureMap map, boolean isComposite) {
		if (map.isOneToOne() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
		} else if (map.isOneToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
		} else if (map.isManyToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = 0 > map.getProperty().getName().compareTo(map.getProperty().getOtherEnd().getName());
		}
		return isComposite;
	}

}
