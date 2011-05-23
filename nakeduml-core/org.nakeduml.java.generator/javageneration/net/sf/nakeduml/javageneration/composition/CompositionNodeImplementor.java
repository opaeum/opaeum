package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.composition.tinker.TinkerCompositionNodeStrategy;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.generated.OJVisibilityKindGEN;
import org.nakeduml.runtime.domain.CompositionNode;

/**
 * This class implements the CompositionNode semantics which enriches the Java model with ideas on how compositions should ideally be
 * implemented.
 * 
 * @author ampie
 * 
 */
public class CompositionNodeImplementor extends AbstractJavaProducingVisitor{
	private static OJPathName COMPOSITION_NODE = null;
	public static final String GET_OWNING_OBJECT = "getOwningObject";
	private CompositionNodeStrategy compositionNodeStrategy;
	@Override
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,TransformationContext context){
		super.initialize(javaModel, config, textWorkspace, context);
		if (transformationContext.isFeatureSelected(TinkerExtendedCompositionSemanticsJavaStep.class)) {
			compositionNodeStrategy = new TinkerCompositionNodeStrategy();
			COMPOSITION_NODE = new OJPathName("org.nakeduml.runtime.domain.TinkerCompositionNode");
		} else {
			compositionNodeStrategy = new DefaultCompositionNodeStrategy();
			COMPOSITION_NODE = new OJPathName(CompositionNode.class.getName());
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedBehavioredClassifier c){
		if(isPersistent(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			if(ojClassifier instanceof OJAnnotatedClass){
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
				addGetOwningObject(c, ojClass);
				addRemoveFromOwner(c, ojClass);
				compositionNodeStrategy.addMarkDeleted(c, ojClass);
				compositionNodeStrategy.addAddToOwningObject(c, ojClass);
				addInit(c, ojClass);
				compositionNodeStrategy.addConstructorForTests(ojClass, c);
				addInternalSetOwner(c, ojClass);
			}
		}
	}
	@VisitAfter
	public void visitInterface(INakedInterface i){
		if(!i.hasStereotype(StereotypeNames.HELPER) && OJUtil.hasOJClass(i)){
			OJPathName path = OJUtil.classifierPathname(i);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(COMPOSITION_NODE);
			ojClassifier.addToImports(COMPOSITION_NODE);
		}
	}
	private void addInternalSetOwner(INakedBehavioredClassifier c,OJClass ojClass){
		if(c instanceof INakedEntity){
			INakedProperty endToComposite = ((INakedEntity) c).getEndToComposite();
			// Composition is declared in this type, not supertype
			if(endToComposite != null){
				OJOperation oper = new OJOperation();
				oper.setVisibility(OJVisibilityKindGEN.PROTECTED);
				oper.setComment("Used to set the owner internally in extended composition semantics");
				oper.setName("internalSetOwner");
				NakedStructuralFeatureMap toOwnerMap = new NakedStructuralFeatureMap(endToComposite);
				oper.addParam("newOwner", toOwnerMap.javaBaseTypePath());
				// Leave body empty for derived feature
				if(!endToComposite.isDerived()){
					oper.getBody().addToStatements("this." + toOwnerMap.umlName() + "=newOwner");
				}
				ojClass.addToOperations(oper);
				OJField fieldToOwner = ojClass.findField(toOwnerMap.umlName());
				if(fieldToOwner != null){
					fieldToOwner.setVisibility(OJVisibilityKind.PROTECTED);
				}
			}
		}else if(c instanceof INakedBehavior){
			// TODO
		}
	}
	protected void addRemoveFromOwner(INakedBehavioredClassifier sc,OJClass ojClass){
		OJAnnotatedOperation remove = new OJAnnotatedOperation();
		remove.setName("removeFromOwningObject");
		remove.getBody().addToStatements("this.markDeleted()");
		ojClass.addToOperations(remove);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	protected void addInit(INakedBehavioredClassifier c,OJClass ojClass){
		OJOperation init = new OJAnnotatedOperation();
		init.addParam("owner", COMPOSITION_NODE);
		init.setName("init");
		init.setBody(ojClass.getDefaultConstructor().getBody());
		ojClass.getDefaultConstructor().setBody(new OJBlock());
		int start = 0;
		if(c.getSupertype() != null){
			OJSimpleStatement simpleStatement = new OJSimpleStatement("super.init(owner)");
			if(init.getBody().getStatements().isEmpty()){
				init.getBody().getStatements().add(simpleStatement);
			}else{
				init.getBody().getStatements().set(0, simpleStatement);
			}
			start++;
		}
		if(c instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) c;
			if(entity.hasComposite()){
				StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(entity.getEndToComposite());
				ojClass.addToImports(compositeFeatureMap.javaBaseTypePath());
				init.getBody().getStatements().add(start, new OJSimpleStatement("internalSetOwner((" + compositeFeatureMap.javaBaseType() + ")owner)"));
			}
		}else if(c instanceof INakedBehavior){
		}
		ojClass.addToOperations(init);
	}
	protected void addGetOwningObject(INakedBehavioredClassifier bc,OJClass ojClass){
		OJOperation getOwner = new OJAnnotatedOperation();
		getOwner.setName(GET_OWNING_OBJECT);
		// TODO this needs to become a uml library
		// getOwner.setReturnType(ReflectionUtil.getUtilInterface(CompositionNode.class));
		getOwner.setReturnType(this.COMPOSITION_NODE);
		getOwner.setBody(new OJBlock());
		if(bc instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) bc;
			if(entity.hasComposite()){
				INakedProperty ce = entity.getEndToComposite();
				getOwner.getBody().addToStatements("return get" + ce.getMappingInfo().getJavaName().getCapped() + "()");
			}else{
				getOwner.getBody().addToStatements("return null");
			}
		}else if(bc instanceof INakedBehavior){
			INakedBehavior b = (INakedBehavior) bc;
			if(b.getContext() != null){
				getOwner.getBody().addToStatements("return getContextObject()");
			}else{
				getOwner.getBody().addToStatements("return null");
			}
		}
		ojClass.addToOperations(getOwner);
	}
}