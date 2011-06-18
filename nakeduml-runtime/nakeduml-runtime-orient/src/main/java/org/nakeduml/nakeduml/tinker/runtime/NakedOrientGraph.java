package org.nakeduml.nakeduml.tinker.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.util.NakedGraph;

import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.storage.OStorage;
import com.tinkerpop.blueprints.pgm.AutomaticIndex;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Element;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientEdge;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class NakedOrientGraph implements NakedGraph {

	private OrientGraph orientGraph;
	private boolean withschema;

	public NakedOrientGraph(OrientGraph orientGraph, boolean withSchema) {
		super();
		this.orientGraph = orientGraph;
		this.withschema = withSchema;
	}

	@Override
	public void startTransaction() {
		orientGraph.startTransaction();
	}

	@Override
	public void stopTransaction(Conclusion conclusion) {
		orientGraph.stopTransaction(conclusion);
	}

	@Override
	public void setTransactionMode(Mode mode) {
		orientGraph.setTransactionMode(mode);
	}

	@Override
	public Mode getTransactionMode() {
		return orientGraph.getTransactionMode();
	}

	@Override
	public Vertex addVertex(Object id) {
		return orientGraph.addVertex(id);
	}

	@Override
	public Vertex getVertex(Object id) {
		return orientGraph.getVertex(id);
	}

	@Override
	public void removeVertex(Vertex vertex) {
		orientGraph.removeVertex(vertex);
	}

	@Override
	public Iterable<Vertex> getVertices() {
		return orientGraph.getVertices();
	}

	@Override
	public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
		return orientGraph.addEdge(id, outVertex, inVertex, label);
	}

	@Override
	public Edge getEdge(Object id) {
		return orientGraph.getEdge(id);
	}

	@Override
	public void removeEdge(Edge edge) {
		orientGraph.removeEdge(edge);
	}

	@Override
	public Iterable<Edge> getEdges() {
		return orientGraph.getEdges();
	}

	@Override
	public void clear() {
		orientGraph.clear();
	}

	@Override
	public void shutdown() {
		orientGraph.shutdown();
	}

	@Override
	public <T extends Element> Index<T> createManualIndex(String indexName, Class<T> indexClass) {
		return orientGraph.createManualIndex(indexName, indexClass);
	}

	@Override
	public <T extends Element> AutomaticIndex<T> createAutomaticIndex(String indexName, Class<T> indexClass, Set<String> indexKeys) {
		return orientGraph.createAutomaticIndex(indexName, indexClass, indexKeys);
	}

	@Override
	public <T extends Element> Index<T> getIndex(String indexName, Class<T> indexClass) {
		return orientGraph.getIndex(indexName, indexClass);
	}

	@Override
	public Iterable<Index<? extends Element>> getIndices() {
		return orientGraph.getIndices();
	}

	@Override
	public void dropIndex(String indexName) {
		orientGraph.dropIndex(indexName);
	}

	@Override
	public void incrementTransactionCount() {
		orientGraph.getRawGraph().getRoot("root").field("transactionCount", (Integer) getRoot().getProperty("transactionCount") + 1);
	}

	@Override
	public long getTransactionCount() {
		return (Integer) getRoot().getProperty("transactionCount");
	}

	@Override
	public Vertex getRoot() {
		return new OrientVertex(orientGraph, orientGraph.getRawGraph().getRoot("root"));
	}

	@Override
	public Vertex addVertex(String className) {
		ODocument oDocument;
		if (this.withschema) {
			oDocument = orientGraph.getRawGraph().createVertex(className);
		} else {
			oDocument = orientGraph.getRawGraph().createVertex();
		}
		return new OrientVertex(orientGraph, oDocument);
	}

	@Override
	public Set<Edge> getEdgesBetween(Vertex v1, Vertex v2, String... labels) {
		Set<Edge> result = new HashSet<Edge>();
		Set<ODocument> rawEdges = orientGraph.getRawGraph().getEdgesBetweenVertexes(getRawDocument(v1), getRawDocument(v2), labels);
		for (ODocument rawEdge : rawEdges) {
			result.add(new OrientEdge(orientGraph, rawEdge));
		}
		return result;
	}

	private static ODocument getRawDocument(Vertex v1) {
		return ((OrientVertex) v1).getRawElement();
	}

	@Override
	public void addRoot() {
		Vertex root = orientGraph.addVertex(null);
		root.setProperty("transactionCount", 0);
		orientGraph.getRawGraph().setRoot("root", ((OrientVertex) root).getRawElement());
		((OrientVertex) root).getRawElement().save();
	}

	@Override
	public long countVertices() {
		// Exclude the root node
		return orientGraph.getRawGraph().countVertexes() - 1;
	}

	@Override
	public long countEdges() {
		return orientGraph.getRawGraph().countEdges();
	}

	@Override
	public void registerListeners() {
		orientGraph.getRawGraph().registerListener(new OrientDbListener());
	}

	@Override
	public void createSchema(List<String> classNames) {
		for (String className : classNames) {
			OSchema schema = orientGraph.getRawGraph().getMetadata().getSchema();
			if (!schema.existsClass(className)) {
				schema.createClass(className, schema.getClass("OGraphVertex"),
						orientGraph.getRawGraph().getStorage().addCluster(className, OStorage.CLUSTER_TYPE.PHYSICAL));
			}
		}
	}
}
