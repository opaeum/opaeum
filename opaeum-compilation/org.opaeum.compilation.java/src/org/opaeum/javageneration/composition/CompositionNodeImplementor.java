package org.opaeum.javageneration.composition;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IModelElement;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.runtime.domain.CompositionNode;

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
	private void visitClass(ICompositionParticipant c){

		if(OJUtil.hasOJClass(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findClass(path);
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
				INakedProperty endToComposite = entity.getEndToComposite();
				ICompositionParticipant owningType = (ICompositionParticipant) endToComposite.getNakedBaseType();
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
				if(isMap(endToComposite.getOtherEnd())){
					for(INakedProperty p:endToComposite.getOtherEnd().getQualifiers()){
						NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(p);
						testConstructor.addParam(qMap.fieldname(), qMap.javaTypePath());
						testConstructor.getBody().addToStatements(qMap.setter() + "(" + qMap.fieldname() + ")");
					}
				}
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
					NakedStructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
					NakedStructuralFeatureMap otherFeatureMap = new NakedStructuralFeatureMap(endToComposite.getOtherEnd());
					if(isMap(otherFeatureMap.getProperty())){
						String qArgs = OJUtil.addQualifierArguments(otherFeatureMap.getProperty().getQualifiers(), "this");
						addToOwningObject.getBody().addToStatements(
								featureMap.getter() + "()." + otherFeatureMap.internalAdder() + "(" + qArgs + "(" + ojClass.getName() + ")this)");
					}else{
						addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.internalAdder() + "((" + ojClass.getName() + ")this)");
					}
				}
			}
			
		}
		if(c instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c).getClassifierBehavior()!=null){
			addToOwningObject.getBody().addToStatements("startClassifierBehavior()");

		}
		ojClass.addToOperations(addToOwningObject);
	}
	private boolean isMap(INakedProperty p){
		return p.getName().equals("updateChangeLog");
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
	protected void markChildrenForDeletion(INakedClassifier sc,OJClass ojClass,OJAnnotatedOperation markDeleted){
		for(INakedProperty np:sc.getEffectiveAttributes()){
			if(np.getOtherEnd() != null && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.getOtherEnd().isDerived()
					&& (isPersistent(np.getNakedBaseType()) || np.getNakedBaseType() instanceof INakedInterface)){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if(map.isManyToMany()){
					markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
				}else if(map.isManyToOne()){
					OJIfStatement ifNotNull;
					if(isMap(np.getOtherEnd())){
						ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "("
								+ OJUtil.addQualifierArguments(np.getOtherEnd().getQualifiers(), "this") + "this)");
					}else{
						ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "(this)");
					}
					markDeleted.getBody().addToStatements(ifNotNull);
				}else if(map.isOneToOne()){
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
			OJClassifier ojClassifier = this.javaModel.findClass(path);
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
		if(c.isPersistent()){
			init.addParam("owner", COMPOSITION_NODE);
		}else if(c.hasComposite()){
			StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(c.getEndToComposite());
			init.addParam("owner", compositeFeatureMap.javaBaseTypePath());
		}
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
	protected void addGetOwningObject(ICompositionParticipant c,OJClass ojClass){
		OJOperation getOwner = new OJAnnotatedOperation(GET_OWNING_OBJECT);
		if(c.isPersistent()){
			getOwner.setReturnType(COMPOSITION_NODE);
		}else if(c.hasComposite()){
			StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(c.getEndToComposite());
			getOwner.setReturnType(compositeFeatureMap.javaBaseTypePath());
		}
		getOwner.setBody(new OJBlock());
		if(c.hasComposite()){
			INakedProperty ce = c.getEndToComposite();
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