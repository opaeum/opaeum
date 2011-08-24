package org.nakeduml.tinker.basicjava.tinker;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.apache.commons.lang.StringUtils;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.name.NameConverter;

public class TinkerTransformationActivities extends AbstractJavaProducingVisitor {

	public static final String INIT_VERTEX = "initVertex";

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedActivity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			ojClass.addToImports(TinkerUtil.graphDbPathName);
			ojClass.addToImports(new OJPathName("org.nakeduml.runtime.domain.IntrospectionUtil"));
			addPersistentConstructor(ojClass);
			if (c.getGeneralizations().isEmpty()) {
				addGetObjectVersion(ojClass);
				persistUid(ojClass);
				initialiseVertexInDefaultConstructor(c, ojClass);
				extendsBaseTinker(ojClass);
				addInitNullToDefaultConstructor(ojClass);
				implementGetSetId(ojClass);
				addSuperToDefaultConstructor(ojClass);
			} else {
				addSuperWithPersistenceToDefaultConstructor(ojClass);
			}
			addInitVertex(ojClass, c);
			addInitVertexToConstructorWithOwningObject(ojClass, c);
			implementIsRoot(ojClass, false);
			addContructorWithVertex(ojClass, c);
			implementTinkerNode(ojClass);
			implementAbstractEntity(ojClass);
			
			implementGetSetContextObject(ojClass, c);
		}
	}

	private void implementGetSetContextObject(OJAnnotatedClass ojClass, INakedActivity c) {
//		OJField owner = new OJField();
//		owner.setName(NameConverter.decapitalize(c.getOwnerElement().getName()));
//		owner.setType(OJUtil.classifierPathname((INakedClassifier)c.getOwnerElement()));
//		ojClass.addToFields(owner);
	}

	private void addPersistentConstructor(OJAnnotatedClass ojClass) {
		OJConstructor persistentConstructor = new OJConstructor();
		persistentConstructor.setName(TinkerUtil.PERSISTENT_CONSTRUCTOR_NAME);
		persistentConstructor.addParam(TinkerUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME, new OJPathName("java.lang.Boolean"));
		ojClass.addToConstructors(persistentConstructor);
	}

	private boolean hasSuperwithCompositeParent(ICompositionParticipant c) {
		if (c.getEndToComposite() != null) {
			return true;
		} else {
			if (c.getSupertype() != null) {
				return hasSuperwithCompositeParent((ICompositionParticipant) c.getSupertype());
			} else {
				return false;
			}
		}
	}

	private void attachCompositeRootToDbRoot(OJAnnotatedClass ojClass, INakedEntity c) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements(
				"Edge edge = " + TinkerUtil.graphDbAccess + ".addEdge(null, " + TinkerUtil.graphDbAccess + ".getRoot(), this.vertex, \"root\")");
		constructor.getBody().addToStatements("edge.setProperty(\"inClass\", "+TinkerUtil.TINKER_GET_CLASSNAME+")");
	}

	private void implementAbstractEntity(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.AbstractEntity"));
	}

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerNode"));
	}

	private void implementGetSetId(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getId = new OJAnnotatedOperation("getId");
		getId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getId.setReturnType(new OJPathName("java.lang.Long"));
		getId.getBody().addToStatements("return TinkerIdUtil.getId(this.vertex)");
		ojClass.addToImports(TinkerUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getId);

		OJAnnotatedOperation setId = new OJAnnotatedOperation("setId");
		setId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		setId.addParam("id", new OJPathName("java.lang.Long"));
		setId.getBody().addToStatements("TinkerIdUtil.setId(this.vertex, id)");
		ojClass.addToOperations(setId);
	}

	private void implementIsRoot(OJAnnotatedClass ojClass, boolean b) {
		OJAnnotatedOperation isRoot = new OJAnnotatedOperation();
		isRoot.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		isRoot.setName("isTinkerRoot");
		isRoot.setReturnType(new OJPathName("boolean"));
		isRoot.getBody().addToStatements("return " + b);
		ojClass.addToOperations(isRoot);
	}

	private void addInitNullToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("init(null)");
	}

	private void addSuperWithPersistenceToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("super( "+TinkerUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME+" )"));
	}

	private void addSuperToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("super()"));
	}

	private void extendsBaseTinker(OJAnnotatedClass ojClass) {
		ojClass.setSuperclass(TinkerUtil.BASE_TINKER);
	}

	private void persistUid(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getUid = (OJAnnotatedOperation) OJUtil.findOperation(ojClass, "getUid");
		getUid.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getUid.getBody().removeAllFromStatements();
		getUid.getBody().addToStatements("String uid = (String) this.vertex.getProperty(\"uid\")");
		OJIfStatement ifStatement = new OJIfStatement("uid==null || uid.trim().length()==0");
		ifStatement.setCondition("uid==null || uid.trim().length()==0");
		ifStatement.addToThenPart("uid=UUID.randomUUID().toString()");
		ifStatement.addToThenPart("this.vertex.setProperty(\"uid\", uid)");
		getUid.getBody().addToStatements(ifStatement);
		getUid.getBody().addToStatements("return uid");
	}

	private void addGetObjectVersion(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getObjectVersion = new OJAnnotatedOperation("getObjectVersion");
		getObjectVersion.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getObjectVersion.setReturnType(new OJPathName("int"));
		getObjectVersion.getBody().addToStatements("return TinkerIdUtil.getVersion(this.vertex)");
		ojClass.addToImports(TinkerUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getObjectVersion);
	}

	private void initialiseVertexInDefaultConstructor(INakedActivity c, OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("this.vertex = " + TinkerUtil.graphDbAccess + ".addVertex(\"" + StringUtils.replace(c.getMappingInfo().getQualifiedPersistentName(),".","_") + "\")");
		constructor.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		constructor.getBody().addToStatements("defaultCreate()");
		ojClass.addToImports(TinkerUtil.transactionThreadEntityVar);
	}

	private void addInitVertexToConstructorWithOwningObject(OJAnnotatedClass ojClass, INakedActivity c) {
		OJConstructor constructor = ojClass.findConstructor(OJUtil.classifierPathname((INakedClassifier)c.getOwnerElement()));
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().getStatements()
					.add(0, new OJSimpleStatement("this.vertex = " + TinkerUtil.graphDbAccess + ".addVertex(\"" + StringUtils.replace(c.getMappingInfo().getQualifiedPersistentName(),".","_") + "\")"));
			constructor.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
			constructor.getBody().addToStatements("defaultCreate()");
			ojClass.addToImports(TinkerUtil.transactionThreadEntityVar);
		} else {
			constructor.getBody().getStatements().add(0, new OJSimpleStatement("super(true)"));
		}
		OJSimpleStatement initVertexStatement = new OJSimpleStatement("initVertex(contextObject)");
		initVertexStatement.setName(INIT_VERTEX);
		constructor.getBody().getStatements().add(1, initVertexStatement);
	}

	private void addInitVertex(OJAnnotatedClass ojClass, INakedActivity c) {
		OJAnnotatedOperation initVertex = new OJAnnotatedOperation();
		initVertex.setName(INIT_VERTEX);
		initVertex.addParam("owningObject", OJUtil.classifierPathname((INakedClassifier)c.getOwnerElement()));
		initVertex.setVisibility(OJVisibilityKind.PRIVATE);
		String associationName = TinkerUtil.getEdgeName((INakedClassifier)c.getOwnerElement());
		initVertex.getBody().addToStatements(
				"Iterable<Edge> iter = owningObject.getVertex().getOutEdges" + "(\"" + associationName + "\")");
		OJIfStatement ifAllReadyHasOne = new OJIfStatement("iter.iterator().hasNext()");
		ifAllReadyHasOne.addToThenPart(TinkerUtil.graphDbAccess + ".removeVertex(this.vertex)");
		ifAllReadyHasOne.addToThenPart("throw new IllegalStateException(\""
				+ ((INakedClassifier)c.getOwnerElement()).getMappingInfo().getQualifiedJavaName() + " already has an association with "
				+ c.getMappingInfo().getQualifiedJavaName() + ", the relationship is one to one!\")");
		initVertex.getBody().addToStatements(ifAllReadyHasOne);

		// Add association meta information, i.e. classname of other end
		initVertex.getBody().addToStatements(
				"Edge edge = " + TinkerUtil.graphDbAccess + ".addEdge(null, owningObject.getVertex(), this.vertex, \"" + associationName + "\")");
		initVertex.getBody().addToStatements("edge.setProperty(\"outClass\", owningObject.getClass().getName())");
		initVertex.getBody().addToStatements("edge.setProperty(\"inClass\", "+TinkerUtil.TINKER_GET_CLASSNAME+")");

		OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
		ojClass.addToImports(edgePathName);
		ojClass.addToOperations(initVertex);
	}

	private void addContructorWithVertex(OJAnnotatedClass ojClass, INakedActivity c) {
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("vertex", new OJPathName("com.tinkerpop.blueprints.pgm.Vertex"));
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().addToStatements("this.vertex=vertex");
		} else {
			constructor.getBody().addToStatements("super(vertex)");
		}
		ojClass.addToConstructors(constructor);
	}

}
