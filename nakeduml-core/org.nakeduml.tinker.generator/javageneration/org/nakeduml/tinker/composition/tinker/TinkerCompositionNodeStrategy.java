package org.nakeduml.tinker.composition.tinker;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.composition.AbstractCompositionNodeStrategy;
import net.sf.nakeduml.javageneration.composition.CompositionNodeStrategy;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerCompositionNodeStrategy extends AbstractCompositionNodeStrategy implements CompositionNodeStrategy{
	@Override
	public void addConstructorForTests(OJAnnotatedClass ojClass,ICompositionParticipant c){
		if(c.hasComposite()){
			ICompositionParticipant owningType = (ICompositionParticipant) c.getEndToComposite().getNakedBaseType();
			OJPathName paramPath = OJUtil.classifierPathname(owningType);
			OJConstructor testConstructor = findConstructor(ojClass, paramPath);
			if(testConstructor == null){
				testConstructor = new OJConstructor();
				ojClass.addToConstructors(testConstructor);
				testConstructor.addParam("owningObject", paramPath);
				testConstructor.getBody().addToStatements("init(owningObject)");
			}
		}
	}
	@Override
	public void addMarkDeleted(ICompositionParticipant sc,OJClass ojClass){
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation();
		markDeleted.setName("markDeleted");
		ojClass.addToOperations(markDeleted);
		if(sc.hasSupertype()){
			markDeleted.getBody().addToStatements("super.markDeleted()");
		}
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
		if(!sc.hasSupertype()){
			removeVertex(sc, ojClass, markDeleted);
		}
	}
	private void removeVertex(ICompositionParticipant sc,OJClass ojClass,OJAnnotatedOperation markDeleted){
		markDeleted.getBody().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeVertex(this.vertex)");
	}
	@Override
	public void addAddToOwningObject(ICompositionParticipant c,OJAnnotatedClass ojClass){
		OJOperation addToOwningObject = new OJAnnotatedOperation();
		addToOwningObject.setComment("Call this method when you want to attach this object to the containment tree. Useful with transitive persistence");
		addToOwningObject.setName("addToOwningObject");
		if(c.getIsAbstract()){
			addToOwningObject.setAbstract(true);
		}else{
			if(c.hasComposite()){
				INakedProperty endToComposite = c.getEndToComposite();
				StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(endToComposite);
				addToOwningObject.getBody().addToStatements("initVertex(this." + featureMap.umlName() + ")");
			}
		}
		ojClass.addToOperations(addToOwningObject);
	}
}
