package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.metamodel.core.INakedEntity;

import org.nakeduml.java.metamodel.OJPathName;

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
	
	public static String constructSelfToAuditEdgeLabel(INakedEntity entity) {
		return "audit";
	}
	
	public static long getId(Vertex vertex) {
		return ((ORecordId)vertex.getId()).getClusterPosition();
	}
	
	public static int getVersion(Vertex vertex) {
		return ((OrientVertex)vertex).getRawElement().getVersion();
	}
	
}
