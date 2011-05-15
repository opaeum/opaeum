package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.name.NameConverter;

import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerUtil {

	public static String tinkeriseUmlName(String umlName) {
		return umlName.replace("::", "__");
	}

	public static OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
	public static OJPathName vertexPathName = new OJPathName("com.tinkerpop.blueprints.pgm.Vertex");
	public static OJPathName orientVertexPathName = new OJPathName("com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex");
	public static OJPathName tinkerFormatter = new OJPathName("org.util.TinkerFormatter");
	public static OJPathName transactionAuditThreadVar = new OJPathName("org.util.TransactionAuditThreadVar");
	public static OJPathName transactionThreadEntityVar = new OJPathName("org.util.TransactionThreadEntityVar");
	public static OJPathName graphDbPathName = new OJPathName("org.util.GraphDb");
	public static OJPathName tinkerAuditNodePathName = new OJPathName("org.nakeduml.runtime.domain.TinkerAuditNode");
	public static OJPathName tinkerUtil = new OJPathName("org.util.TinkerUtil");
	public static OJPathName tinkerHashSetImpl = new OJPathName("org.util.TinkerHashSet");
	public static OJPathName tinkerArrayListImpl = new OJPathName("org.util.TinkerArrayList");
	public static OJPathName tinkerSet = new OJPathName("org.util.TinkerSet");
	public static OJPathName tinkerList = new OJPathName("org.util.TinkerList");

	public static String constructSelfToAuditEdgeLabel(INakedEntity entity) {
		return "audit";
	}

	public static long getId(Vertex vertex) {
		return ((ORecordId) vertex.getId()).getClusterPosition();
	}

	public static int getVersion(Vertex vertex) {
		return ((OrientVertex) vertex).getRawElement().getVersion();
	}

	public static boolean calculateDirection(NakedStructuralFeatureMap map, boolean isComposite) {
		if (map.isOneToOne() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
		} else if (map.isOneToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
		} else if (map.isManyToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = 0 > map.getProperty().getName().compareTo(map.getProperty().getOtherEnd().getName());
		}
		return isComposite;
	}

	public static String constructTinkerCollectionInit(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		return map.umlName() + " = new "+ (map.getProperty().isOrdered()?"TinkerArrayList":"TinkerHashSet") + 
		"<"+map.javaBaseTypePath().getLast()+">("+map.javaBaseTypePath().getLast() +".class, this, " + owner.getName() + ".class.getMethod(\"addTo" + NameConverter.capitalize(map.umlName())	+ "\", new Class[]{" + map.javaBaseTypePath().getLast() + ".class}), " + owner.getName() + ".class.getMethod(\"removeFrom" + NameConverter.capitalize(map.umlName())	+ "\", new Class[]{" + map.javaBaseTypePath().getLast() + ".class}))";
	}

}
