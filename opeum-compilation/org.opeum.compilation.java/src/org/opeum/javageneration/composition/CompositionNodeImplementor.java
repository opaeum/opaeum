package org.opeum.javageneration.composition;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IModelElement;

import org.opeum.feature.OpeumConfig;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.maps.AssociationClassEndMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedStructuredDataType;
import org.opeum.metamodel.core.internal.StereotypeNames;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.textmetamodel.TextWorkspace;

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
	public void initialize(OJPackage javaModel,OpeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		super.initialize(javaModel, config, textWorkspace, workspace);
	}
	private void visitClass(ICompositionParticipant c){
		if(OJUtil.hasOJClass(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			if(ojClassifier instanceof OJAnnotatedClass){
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				boolean isTransientMessageStructure = c instanceof INakedMessageStructure && !(((INakedMessageStructure) c).isPersistent());
				if(!isTransientMessageStructure){
					ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
					addGetOwningObject(c, ojClass);
					addRemoveFromOwner(ojClass);
					addMarkDeleted(ojClass, c);
				}
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
				if(endToComposite.getAssociation() != null && endToComposite.getAssociation().isClass()){
					AssociationClassEndMap aMap = new AssociationClassEndMap(endToComposite);
					addToOwningObject.getBody().addToStatements(
							aMap.getMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalAdder() + "(" + aMap.getEndToAssocationClassMap().getter()
									+ "())");
				}else{
					StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
					StructuralFeatureMap otherFeatureMap = new NakedStructuralFeatureMap(endToComposite.getOtherEnd());
					addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.internalAdder() + "((" + ojClass.getName() + ")this)");
				}
			}
		}
		ojClass.addToOperations(addToOwningObject);
	}
	public boolean isInterfaceOrAssociationClass(ICompositionParticipant c){
		return c instanceof INakedInterface || c instanceof INakedAssociation;
	}
	public void addMarkDeleted(OJAnnotatedClass ojClass,INakedClassifier sc){
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
	private void markChildrenForDeletion(INakedClassifier sc,OJClass ojClass,OJAnnotatedOperation markDeleted){
		for(INakedProperty np:sc.getEffectiveAttributes()){
			if(np.getOtherEnd() != null && !np.isDerived() && !np.getOtherEnd().isDerived()
					&& (isPersistent(np.getNakedBaseType()) || np.getNakedBaseType() instanceof INakedInterface)){
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
	@VisitAfter(matchSubclasses = true)
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
			if(ce.getMappingInfo().getJavaName() == null){
				System.out.println();
			}
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
		}else if(umlOwner instanceof INakedStructuredDataType){
			addMarkDeleted(findJavaClass(umlOwner), umlOwner);
		}
	}
	public static void invokeOperationRecursively(INakedClassifier ew,OJOperation markDeleted,String operationName){
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