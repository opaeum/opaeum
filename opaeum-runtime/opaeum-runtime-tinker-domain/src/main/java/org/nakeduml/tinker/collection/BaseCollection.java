package org.nakeduml.tinker.collection;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerAuditableNode;
import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.util.TinkerFormatter;
import org.util.TransactionThreadVar;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseCollection<E> implements Collection<E> {

	protected Collection<E> internalCollection;
	protected boolean composite;
	protected boolean inverse;
	protected boolean manyToMany;
	protected boolean loaded = false;
	protected TinkerCompositionNode owner;
	protected Vertex vertex;
	protected String label;
	protected Class<?> parentClass;
	protected Map<Object, Vertex> internalVertexMap = new HashMap<Object, Vertex>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void loadFromVertex() {
		for (Edge edge : getEdges()) {
			E node = null;
			try {
				Class<?> c = this.getClassToInstantiate(edge);
				Object value = this.getVertexForDirection(edge).getProperty("value");
				if (c.isEnum()) {
					node = (E) Enum.valueOf((Class<? extends Enum>) c, (String) value);
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				} else if (TinkerCompositionNode.class.isAssignableFrom(c)) {
					node = (E) c.getConstructor(Vertex.class).newInstance(this.getVertexForDirection(edge));
				} else {
					node = (E) value;
					this.internalVertexMap.put(value, this.getVertexForDirection(edge));
				}
				this.internalCollection.add(node);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	protected Iterable<Edge> getEdges() {
		if (this.inverse) {
			return this.vertex.getOutEdges(this.label);
		} else {
			return this.vertex.getInEdges(this.label);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		maybeLoad();
		boolean result = true;
		for (E e : c) {
			if (!this.add(e)) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public boolean add(E e) {
		maybeCallInit(e);
		maybeLoad();
		boolean result = this.internalCollection.add(e);
		if (result) {
			addInternal(e);
		}
		return result;
	}

	@Override
	public boolean remove(Object o) {
		maybeLoad();
		boolean result = this.internalCollection.remove(o);
		if (result) {
			@SuppressWarnings("unchecked")
			E e = (E)o;
			Vertex v;
			if (o instanceof TinkerCompositionNode) {
				TinkerCompositionNode node = (TinkerCompositionNode) o;
				v = node.getVertex();
				Set<Edge> edges = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
				for (Edge edge : edges) {
					if (o instanceof TinkerAuditableNode) {
						createAudit(e, v, true);
					}
					GraphDb.getDb().removeEdge(edge);
					break;
				}
			} else if (o.getClass().isEnum()) {
				v = this.internalVertexMap.get(((Enum<?>) o).name());
				GraphDb.getDb().removeVertex(v);
			} else {
				v = this.internalVertexMap.get(o);
				if (o instanceof TinkerAuditableNode) {
					createAudit(e, v, true);
				}
				GraphDb.getDb().removeVertex(v);
			}
		}
		return result;
	}

	protected Edge addInternal(E e) {
		Vertex v;
		if (e instanceof TinkerCompositionNode) {
			TinkerCompositionNode node = (TinkerCompositionNode) e;
			TransactionThreadEntityVar.setNewEntity((TinkerCompositionNode) node);
			v = node.getVertex();
		} else if (e.getClass().isEnum()) {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", ((Enum<?>) e).name());
			this.internalVertexMap.put(((Enum<?>) e).name(), v);
		} else {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", e);
			this.internalVertexMap.put(e, v);
		}
		Edge edge = null;
		// See if edge already added, this can only happen with a manyToMany
		if (this.manyToMany) {
			edge = addCorrelationForManyToMany(v, edge);
		}
		boolean createdEdge = false;
		if (edge == null) {
			createdEdge = true;
			edge = createEdge(e, v);
		}
		if (createdEdge && e instanceof TinkerAuditableNode) {
			createAudit(e, v, false);
		}
		return edge;
	}

	private Edge addCorrelationForManyToMany(Vertex v, Edge edge) {
		Set<Edge> edgesBetween = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
		// Only a sequence can have duplicates
		if (this instanceof TinkerSequence || this instanceof TinkerBag) {
			for (Edge edgeBetween : edgesBetween) {

				if (edgeBetween.getProperty("manyToManyCorrelationInverseTRUE") != null && edgeBetween.getProperty("manyToManyCorrelationInverseFALSE") != null) {
					// A new edge must be created as this is a duplicate
				} else if (edgeBetween.getProperty("manyToManyCorrelationInverseTRUE") == null && edgeBetween.getProperty("manyToManyCorrelationInverseFALSE") == null) {
					throw new IllegalStateException();
				} else if (edgeBetween.getProperty("manyToManyCorrelationInverseTRUE") == null && edgeBetween.getProperty("manyToManyCorrelationInverseFALSE") != null) {
					edge = edgeBetween;
					if (!this.inverse) {
						throw new IllegalStateException();
					}
					edge.setProperty("manyToManyCorrelationInverseTRUE", "SETTED");
					break;
				} else if (edgeBetween.getProperty("manyToManyCorrelationInverseTRUE") != null && edgeBetween.getProperty("manyToManyCorrelationInverseFALSE") == null) {
					edge = edgeBetween;
					if (this.inverse) {
						throw new IllegalStateException();
					}
					edge.setProperty("manyToManyCorrelationInverseFALSE", "SETTED");
					break;
				}

			}
		} else {
			if (!edgesBetween.isEmpty()) {
				if (edgesBetween.size() != 1) {
					throw new IllegalStateException("A set can only have one edge between the two ends");
				}
				edge = edgesBetween.iterator().next();
			}
		}
		return edge;
	}

	private Edge createEdge(E e, Vertex v) {
		Edge edge;
		if (this.inverse) {
			edge = GraphDb.getDb().addEdge(null, this.vertex, v, this.label);
			edge.setProperty("outClass", this.parentClass.getName());
			edge.setProperty("inClass", e.getClass().getName());
			if (this.manyToMany) {
				edge.setProperty("manyToManyCorrelationInverseTRUE", "SETTED");
			}
		} else {
			// Inverse is only false on many to manies
			if (!this.manyToMany) {
				throw new IllegalStateException("Inverse can not be false if the inverse is false");
			}
			edge = GraphDb.getDb().addEdge(null, v, this.vertex, this.label);
			edge.setProperty("outClass", e.getClass().getName());
			edge.setProperty("inClass", this.parentClass.getName());
			edge.setProperty("manyToManyCorrelationInverseFALSE", "SETTED");
		}
		return edge;
	}

	protected void createAudit(E e, Vertex v, boolean deletion) {
		if (!(owner instanceof TinkerAuditableNode)) {
			throw new IllegalStateException("if the collection member is an TinkerAuditableNode, then the owner must be a TinkerAuditableNode!");
		}
		TinkerAuditableNode auditOwner = (TinkerAuditableNode) owner;
		if (TransactionThreadVar.hasNoAuditEntry(owner.getClass().getName() + owner.getUid())) {
			auditOwner.createAuditVertex(false);
		}
		if (e instanceof TinkerAuditableNode) {
			TinkerAuditableNode node = (TinkerAuditableNode) e;
			if (TransactionThreadVar.hasNoAuditEntry(node.getClass().getName() + node.getUid())) {
				node.createAuditVertex(false);
			}
			Edge auditEdge = GraphDb.getDb().addEdge(null, auditOwner.getAuditVertex(), node.getAuditVertex(), this.label);
			auditEdge.setProperty("outClass", auditOwner.getClass().getName() + "Audit");
			auditEdge.setProperty("inClass", IntrospectionUtil.getOriginalClass(node.getClass()).getName() + "Audit");
			if (deletion) {
				auditEdge.setProperty("deletedOn", TinkerFormatter.format(new Date()));
			}
		} else if (e.getClass().isEnum()) {

		} else {
			if (TransactionThreadVar.hasNoAuditEntry(owner.getClass().getName() + e.getClass().getName() + e.toString())) {
				Vertex auditVertex = GraphDb.getDb().addVertex(null);
				auditVertex.setProperty("value", e);
				TransactionThreadVar.putAuditVertexFalse(owner.getClass().getName() + e.getClass().getName() + e.toString(), auditVertex);
				auditVertex.setProperty("transactionNo", GraphDb.getDb().getTransactionCount());
				Edge auditEdge = GraphDb.getDb().addEdge(null, auditOwner.getAuditVertex(), auditVertex, this.label);
				auditEdge.setProperty("transactionNo", GraphDb.getDb().getTransactionCount());
				auditEdge.setProperty("outClass", this.parentClass.getName());
				auditEdge.setProperty("inClass", e.getClass().getName() + "Audit");
				if (deletion) {
					auditEdge.setProperty("deletedOn", TinkerFormatter.format(new Date()));
				}
			}
		}
	}

	protected void maybeCallInit(E e) {
		if (this.composite && e instanceof TinkerCompositionNode && !((TinkerCompositionNode) e).hasInitBeenCalled()) {
			((CompositionNode) e).init(this.owner);
		}
	}

	protected void maybeLoad() {
		if (!this.loaded) {
			loadFromVertex();
		}
	}

	protected Vertex getVertexForDirection(Edge edge) {
		if (this.inverse) {
			return edge.getInVertex();
		} else {
			return edge.getOutVertex();
		}
	}

	protected Class<?> getClassToInstantiate(Edge edge) {
		try {
			if (this.inverse) {
				return Class.forName((String) edge.getProperty("inClass"));
			} else {
				return Class.forName((String) edge.getProperty("outClass"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int size() {
		maybeLoad();
		return this.internalCollection.size();
	}

	@Override
	public boolean isEmpty() {
		maybeLoad();
		return this.internalCollection.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		maybeLoad();
		return this.internalCollection.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		maybeLoad();
		return this.internalCollection.iterator();
	}

	@Override
	public Object[] toArray() {
		maybeLoad();
		return this.internalCollection.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		maybeLoad();
		return this.internalCollection.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		maybeLoad();
		return this.internalCollection.containsAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!this.loaded) {
			loadFromVertex();
		}
		boolean result = true;
		for (E e : this.internalCollection) {
			if (!c.contains(e)) {
				if (!this.remove(e)) {
					result = false;
				}
			}
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		maybeLoad();
		boolean result = true;
		for (Object object : c) {
			if (!this.remove(object)) {
				result = false;
			}
		}
		return result;
	}

}
