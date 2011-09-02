package net.sf.nakeduml.javageneration.composition;

import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AbstractStructureVisitor;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.oclexpressions.AttributeExpressionGenerator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.runtime.domain.CompositionNode;

/**
 * This class implements the CompositionNode semantics which enriches the Java model with ideas on how compositions should ideally be
 * implemented.
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,OperationAnnotator.class
},after = {
	OperationAnnotator.class,AttributeExpressionGenerator.class
})
public class CompositionNodeImplementor extends AbstractStructureVisitor{
	private static OJPathName COMPOSITION_NODE = new OJPathName(CompositionNode.class.getName());
	public static final String GET_OWNING_OBJECT = "getOwningObject";
	@Override
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		super.initialize(javaModel, config, textWorkspace, workspace);
	}
	private void visitClass(ICompositionParticipant c){
		if(OJUtil.hasOJClass(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			if(ojClassifier instanceof OJAnnotatedClass){
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
				addGetOwningObject(c, ojClass);
				addRemoveFromOwner(ojClass);
				addMarkDeleted(ojClass, c);
				addAddToOwningObject(ojClass, c);
				addInit(c, ojClass);
				addConstructorForTests(c, ojClass);
			}
		}
	}
	public void addConstructorForTests(ICompositionParticipant c,OJAnnotatedClass ojClass){
		if(!isInterfaceOrAssociationClass(c)){
			ICompositionParticipant entity = (ICompositionParticipant) c;
			if(entity.hasComposite()){
				ICompositionParticipant owningType = (ICompositionParticipant) entity.getEndToComposite().getNakedBaseType();
				OJPathName paramPath = OJUtil.classifierPathname(owningType);
				OJConstructor testConstructor = ojClass.findConstructor(paramPath);
				if(testConstructor == null){
					testConstructor = new OJConstructor();
					ojClass.addToConstructors(testConstructor);
					testConstructor.addParam("owningObject", new OJPathName(owningType.getMappingInfo().getQualifiedJavaName()));
					testConstructor.getBody().addToStatements("init(owningObject)");
				}else{
				}
				testConstructor.setComment("This constructor is intended for easy initialization in unit tests");
				testConstructor.getBody().addToStatements("addToOwningObject()");
			}
		}
	}
	public void addAddToOwningObject(OJAnnotatedClass ojClass,ICompositionParticipant c){
		OJOperation addToOwningObject = new OJAnnotatedOperation("addToOwningObject");
		addToOwningObject.setComment("Call this method when you want to attach this object to the containment tree. Useful with transitive persistence");
		if(!isInterfaceOrAssociationClass(c)){
			ICompositionParticipant entity = (ICompositionParticipant) c;
			if(entity.hasComposite() && !entity.getEndToComposite().isDerived()){
				INakedProperty endToComposite = entity.getEndToComposite();
				StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
				StructuralFeatureMap otherFeatureMap = new NakedStructuralFeatureMap(endToComposite.getOtherEnd());
				addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.internalAdder() + "((" + ojClass.getName() + ")this)");
			}
		}
		ojClass.addToOperations(addToOwningObject);
	}
	public boolean isInterfaceOrAssociationClass(ICompositionParticipant c){
		return c instanceof INakedInterface || c instanceof INakedAssociationClass;
	}
	public void addMarkDeleted(OJAnnotatedClass ojClass,ICompositionParticipant sc){
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation("markDeleted");
		ojClass.addToOperations(markDeleted);
		if(sc.hasSupertype()){
			markDeleted.getBody().addToStatements("super.markDeleted()");
		}else if(AbstractJavaProducingVisitor.isPersistent(sc)){
			if(ojClass.findField("deletedOn") != null){
				ojClass.addToImports("java.util.Date");
				markDeleted.getBody().addToStatements("setDeletedOn(new Date(System.currentTimeMillis()))");
			}else{
				// is deletion relevant?
			}
		}
		markChildrenForDeletion(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
	}
	private void markChildrenForDeletion(ICompositionParticipant sc,OJClass ojClass,OJAnnotatedOperation markDeleted){
		for(INakedProperty np:sc.getEffectiveAttributes()){
			if(np.getOtherEnd() != null && !np.isDerived() && !np.getOtherEnd().isDerived() && (isPersistent(np.getNakedBaseType()) || np.getNakedBaseType() instanceof INakedInterface)){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if(map.isManyToMany()){
					markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
				}else if(map.isManyToOne() && np.getOtherEnd().isNavigable()){
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "((" + ojClass.getName()
							+ ")this)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}else if(map.isOneToOne() && !np.isInverse() && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.isDerivedUnion()){
					// TODO this may have unwanted results such as removing the
					// owner from "this" too
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "(this)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}
			}
		}
	}
	@VisitAfter(matchSubclasses=true)
	public void visitInterface(INakedInterface i){
		if(!i.hasStereotype(StereotypeNames.HELPER) && OJUtil.hasOJClass(i)){
			OJPathName path = OJUtil.classifierPathname(i);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(COMPOSITION_NODE);
			ojClassifier.addToImports(COMPOSITION_NODE);
		}
	}
	protected void addRemoveFromOwner(OJClass ojClass){
		OJAnnotatedOperation remove = new OJAnnotatedOperation("removeFromOwningObject");
		remove.getBody().addToStatements("this.markDeleted()");
		ojClass.addToOperations(remove);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	protected void addInit(ICompositionParticipant c,OJClass ojClass){
		OJOperation init = new OJAnnotatedOperation("init");
		init.addParam("owner", COMPOSITION_NODE);
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
		if(c.hasComposite() && !c.getEndToComposite().isDerived()){
			StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(c.getEndToComposite());
			ojClass.addToImports(compositeFeatureMap.javaBaseTypePath());
			init.getBody().getStatements()
					.add(start, new OJSimpleStatement("this." + compositeFeatureMap.internalAdder() + "((" + compositeFeatureMap.javaBaseType() + ")owner)"));
		}
		ojClass.addToOperations(init);
	}
	protected void addGetOwningObject(ICompositionParticipant bc,OJClass ojClass){
		OJOperation getOwner = new OJAnnotatedOperation(GET_OWNING_OBJECT);
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
	public static void invokeOperationRecursively(ICompositionParticipant ew,OJOperation markDeleted,String operationName){
		List<? extends INakedProperty> awss = ew.getOwnedAttributes();
		for(int i = 0;i < awss.size();i++){
			IModelElement a = (IModelElement) awss.get(i);
			if(a instanceof INakedProperty){
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
				if(np.isComposite() && (isPersistent(np.getNakedBaseType()) || np.getNakedBaseType() instanceof INakedInterface) && !np.isDerived()){
					INakedClassifier type = (INakedClassifier) np.getNakedBaseType();
					if(map.isMany()){
						markDeleted.getOwner().addToImports("java.util.ArrayList");
						OJForStatement forEach = new OJForStatement();
						forEach.setCollection("new ArrayList<" + map.javaBaseDefaultType() + ">(" + map.getter() + "())");
						forEach.setElemType(OJUtil.classifierPathname(type));
						forEach.setElemName("child");
						forEach.setBody(new OJBlock());
						forEach.getBody().addToStatements("child." + operationName);
						markDeleted.getBody().addToStatements(forEach);
					}else if(map.isOne()){
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + operationName);
						markDeleted.getBody().addToStatements(ifNotNull);
					}
				}
			}
		}
	}
}