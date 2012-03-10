package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class ObjectToken<O> extends Token {

	public ObjectToken(Vertex vertex) {
		super(vertex);
	}

	public ObjectToken(String edgeName, O object) {
		super(edgeName);
		addEdgeToObject(object);
	}

	protected void addEdgeToObject(O object) {
		Vertex v;
		if (object instanceof TinkerNode) {
			TinkerNode node = (TinkerNode) object;
			v = node.getVertex();
		} else if (object.getClass().isEnum()) {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", ((Enum<?>) object).name());
		} else {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", object);
		}
		Edge edge = GraphDb.getDb().addEdge(null, this.vertex, v, TOKEN + "toObject");
		edge.setProperty("inClass", object.getClass().getName());
	}

	protected void removeEdgeToObject() {
		O object = getObject();
		Edge edge = this.vertex.getOutEdges(TOKEN + "toObject").iterator().next(); 
		if (object instanceof TinkerNode) {
			GraphDb.getDb().removeEdge(edge);
		} else if (object.getClass().isEnum()) {
			GraphDb.getDb().removeVertex(edge.getInVertex());
		} else {
			GraphDb.getDb().removeVertex(edge.getInVertex());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public O getObject() {
		Edge edge = this.vertex.getOutEdges(TOKEN + "toObject").iterator().next();
		Class<?> c = getClassToInstantiate(edge);
		Vertex v = edge.getInVertex();
		O node = null;
		try {
			if (c.isEnum()) {
				Object value = v.getProperty("value");
				node = (O) Enum.valueOf((Class<? extends Enum>) c, (String) value);
			} else if (TinkerCompositionNode.class.isAssignableFrom(c)) {
				node = (O) c.getConstructor(Vertex.class).newInstance(v);
			} else {
				Object value = v.getProperty("value");
				node = (O) value;
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return node;
	}
	
	private Class<?> getClassToInstantiate(Edge edge) {
		try {
			return Class.forName((String) edge.getProperty("inClass"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}	

	@Override
	protected void addEdgeToActivityNode(ActivityNode<? extends Token, ? extends Token> node) {
		GraphDb.getDb().addEdge(null, node.vertex, getVertex(), TOKEN + getEdgeName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObjectToken<O> duplicate(String flowName) {
		ObjectToken<O> objectToken = new ObjectToken<O>(flowName, getObject());
		return objectToken;
	}

	@Override
	public void remove() {
		removeEdgeToObject();
		GraphDb.getDb().removeVertex(getVertex());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Object Token value = ");
		sb.append(getObject());
		return sb.toString();
	}

}
