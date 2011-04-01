package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;


public class HibernateCompositionNodeStrategy extends AbstractCompositionNodeStrategy implements CompositionNodeStrategy {

	@Override
	public void addMarkDeleted(INakedEntity sc, OJClass ojClass) {
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
	public void addAddToOwningObject(INakedEntity entity, OJAnnotatedClass ojClass) {
		OJOperation addToOwningObject = new OJAnnotatedOperation();
		addToOwningObject
				.setComment("Call this method when you want to attach this object to the containment tree. Useful with transitive persistence");
		addToOwningObject.setName("addToOwningObject");
		if (entity.hasComposite()) {
			INakedProperty endToComposite = entity.getEndToComposite();
			StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
			StructuralFeatureMap otherFeatureMap = new NakedStructuralFeatureMap(endToComposite.getOtherEnd());
			if (otherFeatureMap.isCollection()) {
				addToOwningObject.getBody().addToStatements(
						featureMap.getter() + "()." + otherFeatureMap.getter() + "().add((" + ojClass.getName() + ")this)");
			} else {
				addToOwningObject.getBody().addToStatements(
						featureMap.getter() + "()." + otherFeatureMap.setter() + "((" + ojClass.getName() + ")this)");
			}
		}
		ojClass.addToOperations(addToOwningObject);
	}

	@Override
	public void addConstructorForTests(OJAnnotatedClass ojClass, INakedEntity entity) {
		if (entity.hasComposite()) {
			INakedEntity owningType = (INakedEntity) entity.getEndToComposite().getNakedBaseType();
			OJPathName paramPath = OJUtil.classifierPathname(owningType);
			OJConstructor testConstructor = findConstructor(ojClass, paramPath);
			if (testConstructor == null) {
				testConstructor = new OJConstructor();
				ojClass.addToConstructors(testConstructor);
				testConstructor.addParam("owningObject", new OJPathName(owningType.getMappingInfo().getQualifiedJavaName()));
				testConstructor.getBody().addToStatements("init(owningObject)");
			} else {
			}
			testConstructor.setComment("This constructor is intended for easy initialization in unit tests");
			testConstructor.getBody().addToStatements("addToOwningObject()");
		}
	}
	
}
