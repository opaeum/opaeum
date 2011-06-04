package net.sf.nakeduml.javageneration.composition;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
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

public class AbstractCompositionNodeStrategy {
	
	protected static OJConstructor findConstructor(OJAnnotatedClass c,OJPathName parameter1){
		return c.findConstructor(parameter1);
	}
	
	protected void markChildrenForDeletion(ICompositionParticipant sc, OJClass ojClass, OJAnnotatedOperation markDeleted) {
		for (INakedProperty np : sc.getEffectiveAttributes()) {
			if (np.getOtherEnd() != null) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if (map.isManyToMany()) {
					markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
				} else if (map.isManyToOne() && np.getOtherEnd().isNavigable()) {
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.getter()
							+ "().remove((" + ojClass.getName() + ")this)");
					markDeleted.getBody().addToStatements(ifNotNull);
				} else if (map.isOneToOne() && !np.isInverse() && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.isDerivedUnion()) {
					// TODO this may have unwanted results such as removing the
					// owner from "this" too
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.setter()
							+ "(null)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}
			}
		}
	}
	
	public static void invokeOperationRecursively(INakedBehavioredClassifier ew, OJOperation markDeleted, String operationName) {
		List<? extends INakedProperty> awss = ew.getOwnedAttributes();
		for (int i = 0; i < awss.size(); i++) {
			IModelElement a = (IModelElement) awss.get(i);
			if (a instanceof INakedProperty) {
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
				if (np.isComposite() && np.getNakedBaseType() instanceof INakedClassifier && !np.isDerived()) {
					INakedClassifier type = (INakedClassifier) np.getNakedBaseType();
					if (map.isMany()) {
						markDeleted.getOwner().addToImports("java.util.ArrayList");
						OJForStatement forEach = new OJForStatement();
						forEach.setCollection("new ArrayList<" + map.javaBaseDefaultType() + ">(" + map.getter() + "())");
						forEach.setElemType(OJUtil.classifierPathname(type));
						forEach.setElemName("child");
						forEach.setBody(new OJBlock());
						forEach.getBody().addToStatements("child." + operationName);
						markDeleted.getBody().addToStatements(forEach);
					} else if (map.isOne()) {
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + operationName);
						markDeleted.getBody().addToStatements(ifNotNull);
					}
				}
			}
		}
	}

}
