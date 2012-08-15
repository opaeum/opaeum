package org.nakeduml.tinker.generator;


import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class TinkerGenerationUtil {

	public static String tinkeriseUmlName(String umlName) {
		return umlName.replace("::", "__");
	}

	public static final String INIT_VERTEX = "initVertex";

	public static final String TINKER_DB_NULL = "__NULL__";
	public static final OJPathName tinkerConclusionPathName = new OJPathName("com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion");
	public static final OJPathName tinkerSetClosableSequenceImplPathName = new OJPathName("org.nakeduml.tinker.collection.TinkerSetClosableSequenceImpl");
	public static final OJPathName tinkerIndexPathName = new OJPathName("com.tinkerpop.blueprints.pgm.Index");
	public static final OJPathName tinkerCloseableSequencePathName = new OJPathName("com.tinkerpop.blueprints.pgm.CloseableSequence");
	public static final OJPathName tinkerCompositionNodePathName = new OJPathName("org.nakeduml.runtime.domain.TinkerCompositionNode");
	public static final OJPathName compositionNodePathName = new OJPathName("org.opaeum.runtime.domain.CompositionNode");
	public static final OJPathName tinkerIdUtilPathName = new OJPathName("org.nakeduml.nakeduml.tinker.runtime.TinkerIdUtil");
	public static final OJPathName BASE_AUDIT_SOFT_DELETE_TINKER = new OJPathName("org.nakeduml.runtime.domain.BaseTinkerSoftDelete");
	public static final OJPathName BASE_BEHAVIORED_CLASSIFIER = new OJPathName("org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier");
	public static final OJPathName BASE_TINKER = new OJPathName("org.nakeduml.runtime.domain.BaseTinker");
	public static final String BASE_AUDIT_TINKER = "org.nakeduml.runtime.domain.BaseTinkerAuditable";
	public static final String PERSISTENT_CONSTRUCTOR_NAME = "persistentConstructor";
	public static final String PERSISTENT_CONSTRUCTOR_PARAM_NAME = "persistent";

	public static final String ORIGINAL_UID = "originalUid";
	public static OJPathName oGraphDatabase = new OJPathName("com.orientechnologies.orient.core.db.graph.OGraphDatabase");
	public static OJPathName schemaPathName = new OJPathName("com.orientechnologies.orient.core.metadata.schema.OSchema");
	public static OJPathName vertexPathName = new OJPathName("com.tinkerpop.blueprints.pgm.Vertex");
	public static OJPathName tinkerFormatter = new OJPathName("org.util.TinkerFormatter");
	public static OJPathName transactionThreadVar = new OJPathName("org.util.TransactionThreadVar");
	public static OJPathName transactionThreadEntityVar = new OJPathName("org.nakeduml.tinker.runtime.TransactionThreadEntityVar");
	public static OJPathName graphDbPathName = new OJPathName("org.nakeduml.tinker.runtime.GraphDb");
	public static OJPathName tinkerAuditNodePathName = new OJPathName("org.nakeduml.runtime.domain.TinkerAuditNode");
	public static OJPathName tinkerUtil = new OJPathName("org.util.TinkerUtil");
	public static OJPathName tinkerHashSetImpl = new OJPathName("org.util.TinkerSet");
	public static OJPathName tinkerJsfHashSetImpl = new OJPathName("org.util.TinkerJsfHashSet");
	public static OJPathName tinkerArrayListImpl = new OJPathName("org.util.TinkerArrayList");
	public static OJPathName tinkerEmbeddedHashSetImpl = new OJPathName("org.util.TinkerEmbeddedHashSet");
	public static OJPathName tinkerEmbeddedArrayListImpl = new OJPathName("org.util.TinkerEmbeddedArrayList");

	public static OJPathName tinkerSet = new OJPathName("org.nakeduml.tinker.collection.TinkerSet");
	public static OJPathName tinkerSetImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerSetImpl");
	public static OJPathName tinkerQualifiedSet = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedSet");
	public static OJPathName tinkerQualifiedSetImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedSetImpl");
	public static OJPathName tinkerSequence = new OJPathName("org.nakeduml.tinker.collection.TinkerSequence");
	public static OJPathName tinkerSequenceImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerSequenceImpl");
	public static OJPathName tinkerQualifiedSequence = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedSequence");
	public static OJPathName tinkerQualifiedSequenceImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedSequenceImpl");
	public static OJPathName tinkerOrderedSet = new OJPathName("org.nakeduml.tinker.collection.TinkerOrderedSet");
	public static OJPathName tinkerOrderedSetImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerOrderedSetImpl");
	public static OJPathName tinkerQualifiedOrderedSet = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedOrderedSet");
	public static OJPathName tinkerQualifiedOrderedSetImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedOrderedSetImpl");
	public static OJPathName tinkerBag = new OJPathName("org.nakeduml.tinker.collection.TinkerBag");
	public static OJPathName tinkerBagImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerBagImpl");

	public static OJPathName tinkerQualifiedBag = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedBag");
	public static OJPathName tinkerQualifiedBagImpl = new OJPathName("org.nakeduml.tinker.collection.TinkerQualifiedBagImpl");

	public static OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
	public static OJPathName storagePathName = new OJPathName("com.orientechnologies.orient.core.storage.OStorage");
	public static String graphDbAccess = "GraphDb.getDb()";
	public static OJPathName tinkerSchemaHelperPathName = new OJPathName("org.nakeduml.tinker.runtime.TinkerSchemaHelper");
	public static String TINKER_GET_CLASSNAME = "IntrospectionUtil.getOriginalClass(this.getClass()).getName()";
	public static OJPathName introspectionUtilPathName = new OJPathName("org.opaeum.runtime.domain.IntrospectionUtil");
	public static OJPathName TINKER_NODE = new OJPathName("org.nakeduml.runtime.domain.TinkerNode");

	public static OJPathName TINKER_QUALIFIER_PATHNAME = new OJPathName("org.nakeduml.tinker.collection.Qualifier");
	public static OJPathName tinkerMultiplicityPathName = new OJPathName("org.nakeduml.tinker.collection.Multiplicity");


	public static String contructNameForQualifiedGetter(PropertyMap map) {
		return "getQualifierFor" + NameConverter.capitalize(map.fieldname());
	}

	// public static boolean calculateDirection(NakedStructuralFeatureMap map,
	// boolean isComposite) {
	// if (map.getProperty().getOtherEnd() == null) {
	// return isComposite = true;
	// } else if (map.isOneToOne() && !isComposite &&
	// !map.getProperty().getOtherEnd().isComposite()) {
	// isComposite = map.getProperty().getMultiplicity().getLower() == 1 &&
	// map.getProperty().getMultiplicity().getUpper() == 1;
	// } else if (map.isOneToMany() && !isComposite &&
	// !map.getProperty().getOtherEnd().isComposite()) {
	// isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
	// } else if (map.isManyToMany() && !isComposite &&
	// !map.getProperty().getOtherEnd().isComposite()) {
	// isComposite = 0 >
	// map.getProperty().getName().compareTo(map.getProperty().getOtherEnd().getName());
	// }
	// return isComposite;
	// }

	public static String constructTinkerCollectionInit(OJAnnotatedClass owner, PropertyMap map, boolean jsf) {
		if (map.getProperty().getOtherEnd() != null) {
			return map.umlName() + " = new " + (map.getProperty().isOrdered() ? "TinkerArrayList" : jsf ? "TinkerJsfHashSet" : "TinkerHashSet") + "<"
					+ map.javaBaseTypePath().getLast() + ">(" + map.javaBaseTypePath().getLast() + ".class, this, " + owner.getName() + ".class.getMethod(\"addTo"
					+ NameConverter.capitalize(map.umlName()) + "\", new Class[]{" + map.javaBaseTypePath().getLast() + ".class}), " + owner.getName()
					+ ".class.getMethod(\"removeFrom" + NameConverter.capitalize(map.umlName()) + "\", new Class[]{" + map.javaBaseTypePath().getLast() + ".class}))";
		} else {
			return map.umlName() + " = new " + (map.getProperty().isOrdered() ? "TinkerEmbeddedArrayList" : "TinkerEmbeddedHashSet") + "<" + map.javaBaseTypePath().getLast()
					+ ">(this, " + owner.getName() + ".class.getMethod(\"addTo" + NameConverter.capitalize(map.umlName()) + "\", new Class[]{" + map.javaBaseTypePath().getLast()
					+ ".class}), " + owner.getName() + ".class.getMethod(\"removeFrom" + NameConverter.capitalize(map.umlName()) + "\", new Class[]{"
					+ map.javaBaseTypePath().getLast() + ".class}))";
		}
	}

	public static String getEdgeName(PropertyMap map) {
		if (map.getProperty().getAssociation() != null) {
			return map.getProperty().getAssociation().getName();
		} else {
			return tinkeriseUmlName(map.getProperty().getQualifiedName());
		}
	}

	public static String getEdgeName(Classifier c) {
		return tinkeriseUmlName(c.getQualifiedName());
	}

	public static String constructNameForInternalCreateAuditToOne(PropertyMap map) {
		return "z_internalCreateAuditToOne" + NameConverter.capitalize(map.umlName());
	}

	public static String constructNameForInternalCreateAuditManies(PropertyMap map) {
		return "z_internalCreateAuditToMany" + NameConverter.capitalize(map.umlName()) + "s";
	}

	public static String constructNameForInternalCreateAuditMany(PropertyMap map) {
		return "z_internalCreateAuditToMany" + NameConverter.capitalize(map.umlName());
	}

	public static String constructNameForInternalManiesRemoval(PropertyMap map) {
		return "z_internalRemoveAllFrom" + NameConverter.capitalize(map.umlName());
	}

	public static OJBlock instantiateClassifier(OJBlock block, PropertyMap map) {
		OJField field = new OJField();
		field.setName(map.umlName());
		field.setType(map.javaBaseTypePath());
		field.setInitExp("null");
		block.addToLocals(field);
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("Vertex v = db.getVertex(" + map.umlName() + "Id)");
		ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) v.getProperty(\"className\"))");
		ojTryStatement.getTryPart().addToStatements(map.umlName() + " = (" + map.javaBaseTypePath().getLast() + ") c.getConstructor(Vertex.class).newInstance(v)");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		block.addToStatements(ojTryStatement);
		return null;
	}

	public static String getClassMetaId(OJAnnotatedClass ojClass) {
		OJAnnotationValue numlMetaInfo = ojClass.findAnnotation(new OJPathName("org.opaeum.annotation.NumlMetaInfo"));
		return numlMetaInfo.findAttribute("uuid").getValues().get(0).toString();
	}



	public static String calculateMultiplcity(MultiplicityElement multiplicityKind) {
		if (multiplicityKind.getLower() == 1 && multiplicityKind.getUpper() == Integer.MAX_VALUE) {
			return "Multiplicity.ONE_TO_MANY";
		} else if (multiplicityKind.getLower() == 0 && multiplicityKind.getUpper() == Integer.MAX_VALUE) {
			return "Multiplicity.ZERO_TO_MANY";
		} else if (multiplicityKind.getLower() == 0 && multiplicityKind.getUpper() == 1) {
			return "Multiplicity.ZERO_TO_ONE";
		} else if (multiplicityKind.getLower() == 1 && multiplicityKind.getUpper() == 1) {
			return "Multiplicity.ONE_TO_ONE";
		} else {
			throw new IllegalStateException("wtf");
		}
	}

	public static String addSetterForSimpleType(PropertyMap map) {
		return addSetterForSimpleType(map, false);
	}
	
	public static String addSetterForSimpleType(PropertyMap map, boolean audit) {
		if (map.getBaseType() instanceof Enumeration) {
			return "this."+(audit?"auditVertex":"vertex")+".setProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(map.getProperty().getQualifiedName())
					+ "\", val!=null?val.name():null)";
		} else {
			return "this."+(audit?"auditVertex":"vertex")+".setProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(map.getProperty().getQualifiedName()) + "\", val==null?\""
					+ TINKER_DB_NULL + "\":val)";
		}
	}

	public static void addOverrideAnnotation(OJAnnotatedOperation oper) {
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));		
	}
}
