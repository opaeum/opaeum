package org.nakeduml.nakeduml.tinker.runtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.util.NakedGraph;

import com.tinkerpop.blueprints.pgm.AutomaticIndex;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Element;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jEdge;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jVertex;

public class NakedNeo4jGraph implements NakedGraph {

	private Neo4jGraph neo4jGraph;
	private TransactionEventHandler transactionEventHandler;
	private boolean withschema;

	public NakedNeo4jGraph(Neo4jGraph orientGraph) {
		super();
		this.neo4jGraph = orientGraph;
	}

	public NakedNeo4jGraph(Neo4jGraph orientGraph, boolean withSchema) {
		super();
		this.neo4jGraph = orientGraph;
		this.withschema = withSchema;
	}

	@Override
	public void startTransaction() {
		neo4jGraph.startTransaction();
	}

	@Override
	public void stopTransaction(Conclusion conclusion) {
		neo4jGraph.stopTransaction(conclusion);
	}

	@Override
	public void setTransactionMode(Mode mode) {
		neo4jGraph.setTransactionMode(mode);
	}

	@Override
	public Mode getTransactionMode() {
		return neo4jGraph.getTransactionMode();
	}

	@Override
	public Vertex addVertex(Object id) {
		return neo4jGraph.addVertex(id);
	}

	@Override
	public Vertex getVertex(Object id) {
		return neo4jGraph.getVertex(id);
	}

	@Override
	public void removeVertex(Vertex vertex) {
		neo4jGraph.removeVertex(vertex);
	}

	@Override
	public Iterable<Vertex> getVertices() {
		return neo4jGraph.getVertices();
	}

	@Override
	public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
		return neo4jGraph.addEdge(id, outVertex, inVertex, label);
	}

	@Override
	public Edge getEdge(Object id) {
		return neo4jGraph.getEdge(id);
	}

	@Override
	public void removeEdge(Edge edge) {
		neo4jGraph.removeEdge(edge);
	}

	@Override
	public Iterable<Edge> getEdges() {
		return neo4jGraph.getEdges();
	}

	@Override
	public void clear() {
		neo4jGraph.clear();
	}

	@Override
	public void shutdown() {
		neo4jGraph.shutdown();
	}

	@Override
	public <T extends Element> Index<T> createManualIndex(String indexName, Class<T> indexClass) {
		return neo4jGraph.createManualIndex(indexName, indexClass);
	}

	@Override
	public <T extends Element> AutomaticIndex<T> createAutomaticIndex(String indexName, Class<T> indexClass, Set<String> indexKeys) {
		return neo4jGraph.createAutomaticIndex(indexName, indexClass, indexKeys);
	}

	@Override
	public <T extends Element> Index<T> getIndex(String indexName, Class<T> indexClass) {
		return neo4jGraph.getIndex(indexName, indexClass);
	}

	@Override
	public Iterable<Index<? extends Element>> getIndices() {
		return neo4jGraph.getIndices();
	}

	@Override
	public void dropIndex(String indexName) {
		neo4jGraph.dropIndex(indexName);
	}

	@Override
	public void incrementTransactionCount() {
		neo4jGraph.getRawGraph().getReferenceNode().setProperty("transactionCount", (Integer) getRoot().getProperty("transactionCount") + 1);
	}

	@Override
	public long getTransactionCount() {
		return (Integer) getRoot().getProperty("transactionCount");
	}

	@Override
	public Vertex getRoot() {
		return new Neo4jVertex(neo4jGraph.getRawGraph().getReferenceNode(), neo4jGraph);
	}

	@Override
	public Vertex addVertex(String className) {
		return neo4jGraph.addVertex(null);
	}

	@Override
	public Set<Edge> getEdgesBetween(Vertex v1, Vertex v2, String... labels) {
		Node n1 = ((Neo4jVertex) v1).getRawVertex();
		Node n2 = ((Neo4jVertex) v2).getRawVertex();
		List<DynamicRelationshipType> dynaRel = new ArrayList<DynamicRelationshipType>(labels.length);
		for (String label : labels) {
			dynaRel.add(DynamicRelationshipType.withName(label));
		}
		Set<Edge> result = new HashSet<Edge>(dynaRel.size());
		Iterable<Relationship> relationships = n1.getRelationships(dynaRel.toArray(new DynamicRelationshipType[] {}));
		for (Relationship relationship : relationships) {
			if ((relationship.getStartNode().equals(n1) && relationship.getEndNode().equals(n2))
					|| (relationship.getStartNode().equals(n2) && relationship.getEndNode().equals(n1))) {
				result.add(new Neo4jEdge(relationship, neo4jGraph));
			}
		}
		return result;
	}

	@Override
	public void addRoot() {
		((EmbeddedGraphDatabase)neo4jGraph.getRawGraph()).getConfig().getGraphDbModule().createNewReferenceNode();
		Vertex root = getRoot();
		root.setProperty("transactionCount", 1);
	}

	@Override
	public long countVertices() {
		return ((EmbeddedGraphDatabase) neo4jGraph.getRawGraph()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Node.class) - 1;
	}

	@Override
	public long countEdges() {
		return ((EmbeddedGraphDatabase) neo4jGraph.getRawGraph()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Relationship.class);
	}

	@Override
	public void registerListeners() {
		if (transactionEventHandler==null) {
			transactionEventHandler = new NakedTransactionEventHandler();
			neo4jGraph.getRawGraph().registerTransactionEventHandler(transactionEventHandler);
		}
	}

	@Override
	public void createSchema(List<String> classNames) {
	}
}
