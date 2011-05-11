package net.sf.nakeduml.javageneration.composition;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
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
	private static final OJPathName COMPOSITION_NODE = new OJPathName(CompositionNode.class.getName());
	public static final String GET_OWNING_OBJECT = "getOwningObject";
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedBehavioredClassifier c){
		if(isPersistent(c)){
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
			if(ojClassifier instanceof OJAnnotatedClass){
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				if(c instanceof INakedStructuredDataType){
					// TODO implement this as "correct" as possible
				}else{
					ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
					addGetOwningObject(c, ojClass);
					addRemoveFromOwner(c, ojClass);
					addMarkDeleted(c, ojClass);
					addAddToOwningObject(c, ojClass);
					addInit(c, ojClass);
					addConstructorForTests(ojClass, c);
					addInternalSetOwner(c, ojClass);
				}
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
		}else{
			// TODO
		}
	}
	protected void addConstructorForTests(OJAnnotatedClass ojClass,INakedBehavioredClassifier c){
		if(c instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) c;
			if(entity.hasComposite()){
				INakedEntity owningType = (INakedEntity) entity.getEndToComposite().getNakedBaseType();
				OJPathName paramPath = OJUtil.classifierPathname(owningType);
				OJConstructor testConstructor = findConstructor(ojClass, paramPath);
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
		}else{
		}
	}
	protected void addRemoveFromOwner(INakedBehavioredClassifier sc,OJClass ojClass){
		OJAnnotatedOperation remove = new OJAnnotatedOperation();
		remove.setName("removeFromOwningObject");
		remove.getBody().addToStatements("this.markDeleted()");
		ojClass.addToOperations(remove);
	}
	public void addMarkDeleted(INakedBehavioredClassifier sc,OJClass ojClass){
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation();
		markDeleted.setName("markDeleted");
		ojClass.addToOperations(markDeleted);
		if(sc.hasSupertype()){
			markDeleted.getBody().addToStatements("super.markDeleted()");
		}else if(isPersistent(sc)){
			if(ojClass.findField("deletedOn") != null){
				ojClass.addToImports("java.util.Date");
				markDeleted.getBody().addToStatements("setDeletedOn(new Date(System.currentTimeMillis()))");
			}else{
				// is deletion relevant?
			}
		}
		for(INakedProperty np:sc.getEffectiveAttributes()){
			if(np.getOtherEnd() != null){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if(map.isManyToMany()){
					markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
				}else if(map.isManyToOne() && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.isDerivedUnion()){
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.getter() + "().remove((" + ojClass.getName()
							+ ")this)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}else if(map.isOneToOne() && !np.isInverse() && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.isDerivedUnion()){
					// TODO this may have unwanted results such as removing the
					// owner from "this" too
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.setter() + "(null)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}
			}
		}
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
	}
	public static void invokeOperationRecursively(INakedBehavioredClassifier ew,OJOperation markDeleted,String operationName){
		List<? extends INakedProperty> awss = ew.getOwnedAttributes();
		for(int i = 0;i < awss.size();i++){
			IModelElement a = (IModelElement) awss.get(i);
			if(a instanceof INakedProperty){
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
				if(np.isComposite() && np.getNakedBaseType() instanceof INakedEntity && !np.isDerived()){
					INakedEntity type = (INakedEntity) np.getNakedBaseType();
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
	protected void addAddToOwningObject(INakedBehavioredClassifier c,OJClass ojClass){
		OJOperation addToOwningObject = new OJAnnotatedOperation();
		addToOwningObject.setComment("Call this method when you want to attach this object to the containment tree. Useful with transitive persistence");
		addToOwningObject.setName("addToOwningObject");
		if(c instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) c;
			if(entity.hasComposite()){
				INakedProperty endToComposite = entity.getEndToComposite();
				StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
				StructuralFeatureMap otherFeatureMap = new NakedStructuralFeatureMap(endToComposite.getOtherEnd());
				if(otherFeatureMap.isCollection()){
					addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.getter() + "().add((" + ojClass.getName() + ")this)");
				}else{
					addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.setter() + "((" + ojClass.getName() + ")this)");
				}
			}
		}
		ojClass.addToOperations(addToOwningObject);
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
			INakedEntity entity = (INakedEntity)c;
			if(entity.hasComposite()){
				StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(entity.getEndToComposite());
				ojClass.addToImports(compositeFeatureMap.javaBaseTypePath());
				init.getBody().getStatements().add(start, new OJSimpleStatement("internalSetOwner((" + compositeFeatureMap.javaBaseType() + ")owner)"));
			}
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