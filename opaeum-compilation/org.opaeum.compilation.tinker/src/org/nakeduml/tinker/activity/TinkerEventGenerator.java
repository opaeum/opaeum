package org.nakeduml.tinker.activity;

import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
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
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerAttributeImplementor.class, PersistentNameGenerator.class, HashcodeBuilder.class }, after = {
		HashcodeBuilder.class, ToXmlStringBuilder.class, ExtendedCompositionSemantics.class, PersistentNameGenerator.class, CompositionNodeImplementor.class })
public class TinkerEventGenerator extends TinkerImplementNodeStep {

	@VisitAfter(matchSubclasses = true, match = { INakedSignalEvent.class, INakedCallEvent.class })
	public void visitEvent(INakedMessageEvent event) {
		OJPathName path = OJUtil.packagePathname(event.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass callEventClass = new OJAnnotatedClass(TinkerBehaviorUtil.eventName(event));
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
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(event.getNameSpace(), event);
		// TODO clearCache is done incorrectly, need to clear emulated
		// properties
		addClearCache(callEventClass, concreteEmulatedClassifier);
		if (event instanceof INakedCallEvent) {
			addImplementICallEvent(callEventClass, event);
			INakedCallEvent callEvent = (INakedCallEvent) event;
			if (callEvent.getOperation() == null) {
				throw new IllegalStateException("CallEvent " + callEvent.getName() + " has no operation defined!");
			}
			for (INakedParameter p : callEvent.getOperation().getArgumentParameters()) {
				TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, p);
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
				tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
			}
		} else {
			INakedSignalEvent signalEvent = (INakedSignalEvent) event;
			INakedSignal signal = signalEvent.getSignal();
			TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, new NonInverseArtificialProperty(NameConverter.decapitalize(signal
					.getName()), signal, false));
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
			tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
			addImplementISignalEvent(callEventClass, (INakedSignalEvent) event, map);
		}
	}

	private void addImplementISignalEvent(OJAnnotatedClass callEventClass, INakedSignalEvent event, NakedStructuralFeatureMap map) {
		callEventClass.addToImplementedInterfaces(TinkerBehaviorUtil.tinkerISignalEventPathName);
		OJAnnotatedOperation getEventName = new OJAnnotatedOperation("getEventName", new OJPathName("java.lang.String"));
		getEventName.getBody().addToStatements("return \"" + event.getName() + "\"");
		callEventClass.addToOperations(getEventName);
		OJAnnotatedOperation getSignal = new OJAnnotatedOperation("getSignal", TinkerBehaviorUtil.signalPathName);
		getSignal.getBody().addToStatements("return this." + map.fieldname());
		callEventClass.addToOperations(getSignal);
	}

	private void addImplementICallEvent(OJAnnotatedClass ojClass, INakedMessageEvent c) {
		ojClass.addToImplementedInterfaces(TinkerBehaviorUtil.tinkerICallEventPathName);
		OJAnnotatedOperation getEventName = new OJAnnotatedOperation("getEventName", new OJPathName("java.lang.String"));
		getEventName.getBody().addToStatements("return \"" + c.getName() + "\"");
		ojClass.addToOperations(getEventName);
	}

	protected void persistUid(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getUid = new OJAnnotatedOperation("getUid", new OJPathName("java.lang.String"));
		ojClass.addToOperations(getUid);
		ojClass.addToImports(new OJPathName("java.util.UUID"));
		super.persistUid(ojClass);
	}

	protected void extendsTrigger(OJAnnotatedClass ojClass, INakedClassifier c) {
		ojClass.setSuperclass(TinkerGenerationUtil.BASE_AUDIT_SOFT_DELETE_TINKER);
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
