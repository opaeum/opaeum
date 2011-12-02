package org.nakeduml.tinker.collection;

import java.util.Set;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TinkerSetImpl<E> extends BaseSet<E> implements TinkerSet<E> {

	public TinkerSetImpl(TinkerCompositionNode owner, String label, boolean isInverse, boolean isManyToMany, boolean composite) {
		super();
		this.owner = owner;
		this.vertex = owner.getVertex();
		this.label = label;
		this.parentClass = owner.getClass();
		this.inverse = isInverse;
		this.manyToMany = isManyToMany;
		this.composite = composite;
	}

	@Override
	public boolean add(E e) {
		maybeCallInit(e);
		maybeLoad();
		boolean result = this.internalSet.add(e);
		if (result) {
			addInternal(e);
		}
		return result;
	}

	@Override
	public boolean remove(Object o) {
		maybeLoad();
		boolean result = this.internalSet.remove(o);
		if (result) {
			Vertex v;
			if (o instanceof TinkerCompositionNode) {
				TinkerCompositionNode node = (TinkerCompositionNode) o;
				v = node.getVertex();
				Set<Edge> edges = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
				for (Edge edge : edges) {
					GraphDb.getDb().removeEdge(edge);
				}
			} else if (o.getClass().isEnum()) {
				v = this.internalVertexMap.get(((Enum<?>) o).name());
				GraphDb.getDb().removeVertex(v);
			} else {
				v = this.internalVertexMap.get(o);
				GraphDb.getDb().removeVertex(v);
			}
		}
		return result;
	}

}
