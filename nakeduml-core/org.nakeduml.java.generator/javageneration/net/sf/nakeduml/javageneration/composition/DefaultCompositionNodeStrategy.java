package net.sf.nakeduml.javageneration.composition;

import java.util.List;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;


public class DefaultCompositionNodeStrategy extends AbstractCompositionNodeStrategy implements CompositionNodeStrategy {

	@Override
	public void addConstructorForTests(OJAnnotatedClass ojClass,INakedBehavioredClassifier c){
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
		}else if(c instanceof INakedBehavior){
			
		}
	}
	@Override
	public void addMarkDeleted(INakedBehavioredClassifier sc, OJClass ojClass) {
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation();
		markDeleted.setName("markDeleted");
		ojClass.addToOperations(markDeleted);
		if (sc.hasSupertype()) {
			markDeleted.getBody().addToStatements("super.markDeleted()");
		} else if (AbstractJavaProducingVisitor.isPersistent(sc)) {
			if (ojClass.findField("deletedOn") != null) {
				ojClass.addToImports("java.util.Date");
				markDeleted.getBody().addToStatements("setDeletedOn(new Date(System.currentTimeMillis()))");
			} else {
				// is deletion relevant?
			}
		}
		markChildrenForDeletion(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
	}	
	
	@Override
	public void addAddToOwningObject(INakedBehavioredClassifier c, OJAnnotatedClass ojClass) {
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
		}else{
			//TODO add to the behavior's field in the context
		}
		ojClass.addToOperations(addToOwningObject);
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

}
