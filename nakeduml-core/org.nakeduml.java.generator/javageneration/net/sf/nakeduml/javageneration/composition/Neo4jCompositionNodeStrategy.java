package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.javageneration.basicjava.Neo4jUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;


public class Neo4jCompositionNodeStrategy extends AbstractCompositionNodeStrategy implements CompositionNodeStrategy {
	
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
//		markChildrenForDeletion(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
		removeFromNeo(sc, ojClass, markDeleted);
	}

	private void removeFromNeo(INakedEntity sc, OJClass ojClass, OJAnnotatedOperation markDeleted) {
		Neo4jUtil.addNeoToImports(ojClass);
		if (sc.getEndToComposite()==null) {
			markDeleted.getBody().addToStatements(Neo4jUtil.constructSingleRelationshipToReferenceNode(sc));
			markDeleted.getBody().addToStatements("rel.delete()");
			markDeleted.getBody().addToStatements("this.underlyingNode.delete()");
		} else if (!sc.getEndToComposite().isDerived()) {
			markDeleted.getBody().addToStatements(Neo4jUtil.constructSingleRelationshipToCompositeParent(sc.getEndToComposite(), true));
			markDeleted.getBody().addToStatements("rel.delete()");
			markDeleted.getBody().addToStatements("this.underlyingNode.delete()");
		}
		
	}

	@Override
	public void addAddToOwningObject(INakedEntity entity, OJAnnotatedClass ojClass) {
	}

}
