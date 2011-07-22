package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractStructureVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
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
public class CompositionNodeImplementor extends AbstractStructureVisitor{
	private static OJPathName COMPOSITION_NODE = null;
	public static final String GET_OWNING_OBJECT = "getOwningObject";
	private CompositionNodeStrategy compositionNodeStrategy;
	@Override
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,TransformationContext context){
		super.initialize(javaModel, config, textWorkspace, context);
		try{
			compositionNodeStrategy = (CompositionNodeStrategy) Class.forName(config.getCompositionNodeImplementationStrategy()).newInstance();
			if(config.getCompositionNodeImplementationStrategy().contains("Tinker")){
				COMPOSITION_NODE = new OJPathName("org.nakeduml.runtime.domain.TinkerCompositionNode");
			}else{
				COMPOSITION_NODE = new OJPathName(CompositionNode.class.getName());
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private void visitClass(ICompositionParticipant c){
		if(isPersistent(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			if(ojClassifier instanceof OJAnnotatedClass){
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
				addGetOwningObject(c, ojClass);
				addRemoveFromOwner(ojClass);
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
	private void addInternalSetOwner(ICompositionParticipant c,OJClass ojClass){
		INakedProperty endToComposite = ((ICompositionParticipant) c).getEndToComposite();
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
	}
	protected void addRemoveFromOwner(OJClass ojClass){
		OJAnnotatedOperation remove = new OJAnnotatedOperation();
		remove.setName("removeFromOwningObject");
		remove.getBody().addToStatements("this.markDeleted()");
		ojClass.addToOperations(remove);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	protected void addInit(ICompositionParticipant c,OJClass ojClass){
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
		if(c.hasComposite()){
			StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(c.getEndToComposite());
			ojClass.addToImports(compositeFeatureMap.javaBaseTypePath());
			init.getBody().getStatements().add(start, new OJSimpleStatement("internalSetOwner((" + compositeFeatureMap.javaBaseType() + ")owner)"));
		}
		ojClass.addToOperations(init);
	}
	protected void addGetOwningObject(ICompositionParticipant bc,OJClass ojClass){
		OJOperation getOwner = new OJAnnotatedOperation();
		getOwner.setName(GET_OWNING_OBJECT);
		getOwner.setReturnType(COMPOSITION_NODE);
		getOwner.setBody(new OJBlock());
		if(bc.hasComposite()){
			INakedProperty ce = bc.getEndToComposite();
			getOwner.getBody().addToStatements("return get" + ce.getMappingInfo().getJavaName().getCapped() + "()");
		}else{
			getOwner.getBody().addToStatements("return null");
		}
		ojClass.addToOperations(getOwner);
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(umlOwner instanceof ICompositionParticipant){
			visitClass((ICompositionParticipant) umlOwner);
		}
	}
}