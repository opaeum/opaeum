package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerCompositionNodeStrategy extends AbstractCompositionNodeStrategy implements CompositionNodeStrategy {

	@Override
	public void addConstructorForTests(OJAnnotatedClass ojClass, INakedEntity entity) {
		if (entity.hasComposite()) {
			INakedEntity owningType = (INakedEntity) entity.getEndToComposite().getNakedBaseType();
			OJPathName paramPath = OJUtil.classifierPathname(owningType);
			OJConstructor testConstructor = findConstructor(ojClass, paramPath);
			if (testConstructor == null) {
				testConstructor = new OJConstructor();
				ojClass.addToConstructors(testConstructor);
				testConstructor.addParam("owningObject", paramPath);
				testConstructor.getBody().addToStatements("init(owningObject)");
			}
		}
	}

	@Override
	public void addMarkDeleted(INakedEntity sc, OJClass ojClass) {
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation();
		markDeleted.setName("markDeleted");
		ojClass.addToOperations(markDeleted);
		if (sc.hasSupertype()) {
			markDeleted.getBody().addToStatements("super.markDeleted()");
		}
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
		if (!sc.hasSupertype()) {
			removeVertex(sc, ojClass, markDeleted);
		}
	}

	private void removeVertex(INakedEntity sc, OJClass ojClass, OJAnnotatedOperation markDeleted) {
		markDeleted.getBody().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeVertex(this.vertex)");
	}

	@Override
	public void addAddToOwningObject(INakedEntity entity, OJAnnotatedClass ojClass) {
	}

}
