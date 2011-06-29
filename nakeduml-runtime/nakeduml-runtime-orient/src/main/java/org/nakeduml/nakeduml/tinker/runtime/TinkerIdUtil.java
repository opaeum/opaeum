package org.nakeduml.nakeduml.tinker.runtime;

import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerIdUtil {

	public static Long getId(Vertex v) {
		return ((ORecordId)((OrientVertex)v).getId()).getClusterPosition();
	}

	public static void setId(Vertex v, Long id) {
		throw new IllegalStateException("Id can not be set using OrientDb");
	}

	public static int getVersion(Vertex v) {
		return ((OrientVertex)v).getRawElement().getVersion();
	}

}
