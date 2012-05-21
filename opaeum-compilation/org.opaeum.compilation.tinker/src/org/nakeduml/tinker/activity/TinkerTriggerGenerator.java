package org.nakeduml.tinker.activity;

import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.EventBridge;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.HashcodeBuilder;
import org.opaeum.javageneration.basicjava.ToXmlStringBuilder;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerAttributeImplementor.class, PersistentNameGenerator.class, HashcodeBuilder.class }, after = {
		HashcodeBuilder.class, ToXmlStringBuilder.class, ExtendedCompositionSemantics.class, PersistentNameGenerator.class, CompositionNodeImplementor.class })
public class TinkerTriggerGenerator extends TinkerImplementNodeStep {

	@VisitAfter(matchSubclasses = true, match = { INakedTrigger.class })
	public void visitTrigger(INakedTrigger trigger) {
		OJPathName path = OJUtil.packagePathname(trigger.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass callEventClass = new OJAnnotatedClass(TinkerBehaviorUtil.triggerName(trigger));
		callEventClass.setMyPackage(pack);
		callEventClass.addToImports(TinkerGenerationUtil.graphDbPathName);
		callEventClass.addToImports(TinkerGenerationUtil.edgePathName);
		callEventClass.addToImports(TinkerGenerationUtil.introspectionUtilPathName);
		implementIsRoot(callEventClass, false);
		addPersistentConstructor(callEventClass);
		persistUid(callEventClass);
		extendsTrigger(callEventClass, null);
		addGetObjectVersion(callEventClass);
		addGetSetId(callEventClass);
		initialiseVertexInPersistentConstructor(callEventClass, null);
		addContructorWithVertex(callEventClass, null);
		super.createTextPath(callEventClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(trigger.getNameSpace(), trigger);
		// TODO clearCache is done incorrectly, need to clear emulated
		// properties
		addClearCache(callEventClass, concreteEmulatedClassifier);

		TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, new EventBridge(concreteEmulatedClassifier, trigger.getEvent()));
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
		tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
		addImplementITrigger(callEventClass, map);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitSignal(INakedSignal c) {
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(ICompositionParticipant c) {
	}

	private void addImplementITrigger(OJAnnotatedClass ojClass, NakedStructuralFeatureMap map) {
		ojClass.addToImplementedInterfaces(TinkerBehaviorUtil.tinkerITriggerPathName);
		OJAnnotatedOperation getEventName = new OJAnnotatedOperation("getEvent", TinkerBehaviorUtil.tinkerIEventPathName.getCopy());
		TinkerGenerationUtil.addOverrideAnnotation(getEventName);
		getEventName.getBody().addToStatements("return " + map.getter() + "()");
		ojClass.addToOperations(getEventName);
		ojClass.addToImports(TinkerBehaviorUtil.tinkerITriggerPathName.getCopy());
		OJAnnotatedOperation getEventClass = new OJAnnotatedOperation("getEventClass", new OJPathName("Class<? extends " + TinkerBehaviorUtil.tinkerIEventPathName.getLast() + ">"));
		TinkerGenerationUtil.addOverrideAnnotation(getEventClass);
		getEventClass.getBody().addToStatements("return " + map.javaBaseTypePath().getLast() + ".class");
		ojClass.addToOperations(getEventClass);
	}

	protected void persistUid(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getUid = new OJAnnotatedOperation("getUid", new OJPathName("java.lang.String"));
		ojClass.addToOperations(getUid);
		ojClass.addToImports(new OJPathName("java.util.UUID"));
		super.persistUid(ojClass);
	}

	protected void extendsTrigger(OJAnnotatedClass ojClass, INakedClassifier c) {
		ojClass.setSuperclass(TinkerBehaviorUtil.tinkerTriggerPathName.getCopy());
	}

	protected void initialiseVertexInPersistentConstructor(OJAnnotatedClass ojClass, INakedClassifier c) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("this.vertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"null\")");
		constructor.getBody().addToStatements("defaultCreate()");
		ojClass.addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
	}

	protected void addContructorWithVertex(OJAnnotatedClass ojClass, INakedClassifier c) {
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("vertex", new OJPathName("com.tinkerpop.blueprints.pgm.Vertex"));
		constructor.getBody().addToStatements("this.vertex=vertex");
		ojClass.addToConstructors(constructor);
	}

}
