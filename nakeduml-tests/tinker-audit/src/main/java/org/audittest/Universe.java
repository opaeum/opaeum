package org.audittest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.util.GraphDb;
import org.util.TransactionThreadVar;

public class Universe implements Serializable, TinkerNode, TinkerCompositionNode {
	protected God god;
	private String name;
	private String uid;
	protected Vertex vertex;
	protected Vertex auditVertex;

	/**
	 * Constructor for Universe
	 * 
	 * @param vertex
	 */
	public Universe(Vertex vertex) {
		this.vertex = vertex;
	}

	/**
	 * Default constructor for
	 */
	public Universe() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);
		;
	}

	/**
	 * Constructor for Universe
	 * 
	 * @param owningObject
	 */
	public Universe(God owningObject) {
		this();
		init(owningObject);
		initVertex(owningObject);
	}

	public void copyShallowState(Universe from, Universe to) {
		to.setName(from.getName());
	}

	public void copyState(Universe from, Universe to) {
		to.setName(from.getName());
	}

	public boolean equals(Object other) {
		if (other instanceof Universe) {
			return other == this || ((Universe) other).getUid().equals(this.getUid());
		}
		return false;
	}

	public God getGod() {
		Iterable<Edge> iter1 = this.vertex.getInEdges("A__god___universe_");
		if (iter1.iterator().hasNext()) {
			try {
				Edge edge = iter1.iterator().next();
				Class<?> c = Class.forName((String) edge.getProperty("outClass"));
				return (God) c.getConstructor(Vertex.class).newInstance(edge.getOutVertex());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	public String getName() {
		return (String) this.vertex.getProperty("org__auditTest__Universe__name");
	}

	public TinkerCompositionNode getOwningObject() {
		return getGod();
	}

	public String getUid() {
		if (this.uid == null || this.uid.trim().length() == 0) {
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}

	public Vertex getVertex() {
		return this.vertex;
	}

	public int hashCode() {
		return getUid().hashCode();
	}

	public void init(TinkerCompositionNode owner) {
		internalSetOwner((God) owner);
		createComponents();
	}

	public Universe makeCopy() {
		Universe result = new Universe();
		copyState((Universe) this, result);
		return result;
	}

	public Universe makeShallowCopy() {
		Universe result = new Universe();
		copyShallowState((Universe) this, result);
		return result;
	}

	public void markDeleted() {
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}

	public void removeFromOwningObject() {
		this.markDeleted();
	}

	public void setGod(God god) {
		Iterable<Edge> iter = this.vertex.getInEdges("A__god___universe_");
		if (iter.iterator().hasNext()) {
			org.util.GraphDb.getDB().removeEdge(iter.iterator().next());
		}
		if (god != null) {
			Edge edge = org.util.GraphDb.getDB().addEdge(null, ((TinkerNode) god).getVertex(), this.vertex, "A__god___universe_");
			edge.setProperty("outClass", god.getClass().getName());
			edge.setProperty("inClass", this.getClass().getName());
		}
	}

	public void setName(String name) {
		this.vertex.setProperty("org__auditTest__Universe__name", name);
		if (TransactionThreadVar.getNewVertex(this.getClass().getName() + getUid())) {
			createAuditVertex(getGod());
		}
		this.auditVertex.setProperty("org__auditTest__Universe__name", name);
	}

	public void setUid(String newUid) {
		this.uid = newUid;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		if (getGod() == null) {
			sb.append("god=null;");
		} else {
			sb.append("god=" + getGod().getClass().getSimpleName() + "[");
			sb.append(getGod().getName());
			sb.append("];");
		}
		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if (getName() == null) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if (getGod() == null) {
			sb.append("<god/>");
		} else {
			sb.append("<god>");
			sb.append(getGod().getClass().getSimpleName());
			sb.append("[");
			sb.append(getGod().getName());
			sb.append("]");
			sb.append("</god>");
			sb.append("\n");
		}
		return sb.toString();
	}

	public void z_internalAddToGod(God god) {
		this.god = god;
	}

	public void z_internalRemoveFromGod(God god) {
		if (getGod() != null && getGod().equals(god)) {
			this.god = null;
		}
	}

	/**
	 * Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner
	 */
	protected void internalSetOwner(God newOwner) {
		this.god = newOwner;
	}

	public void createComponents() {
	}

	private void initVertex(God owningObject) {
		createAuditVertex(owningObject);
		Edge edge = org.util.GraphDb.getDB().addEdge(null, owningObject.getVertex(), this.vertex, "A__god___universe_");
		edge.setProperty("outClass", owningObject.getClass().getName());
		edge.setProperty("inClass", this.getClass().getName());
	}

	private void createAuditVertex(God owningObject) {
		if (TransactionThreadVar.getNewVertex(getClass().getName() + getUid())) {
			this.auditVertex = org.util.GraphDb.getDB().addVertex(null);
			TransactionThreadVar.setNewVertexFalse(getClass().getName() + getUid());

			Edge auditEdge = org.util.GraphDb.getDB().addEdge(null, this.vertex, this.auditVertex,
					"A__universe___audit" + org.util.GraphDb.getTransactionCount());
			auditEdge.setProperty("outClass", this.getClass().getName());
			auditEdge.setProperty("inClass", this.getClass().getName() + "Audit");

			Vertex owningAuditVertex = owningObject.createAuditVertex();

			attachPreviousAuditsOutEdges();
			
			Edge auditParentEdge = org.util.GraphDb.getDB().addEdge(null, owningAuditVertex, this.auditVertex, "A__god___universe_");
			auditParentEdge.setProperty("outClass", owningObject.getClass().getName() + "Audit");
			auditParentEdge.setProperty("inClass", this.getClass().getName() + "Audit");
		}
	}

	private void attachPreviousAuditsOutEdges() {
		TreeMap<Integer, Edge> previousTransactions = new TreeMap<Integer, Edge>();
		for (Edge auditOwnerOriginalOutEdges : this.vertex.getOutEdges()) {
			String label = auditOwnerOriginalOutEdges.getLabel();
			if (label.startsWith("A__god___audit")) {
				Integer transaction = Integer.valueOf(label.substring(label.length() - 1, label.length()));
				if (!transaction.equals(org.util.GraphDb.getTransactionCount())) {
					previousTransactions.put(transaction, auditOwnerOriginalOutEdges);
				}
			}
		}
		if (!previousTransactions.isEmpty()) {
			Vertex previousAuditVertex = previousTransactions.lastEntry().getValue().getInVertex();
			for (Edge previousAuditOutEdges : previousAuditVertex.getOutEdges()) {
				Vertex previousAuditInVertex = previousAuditOutEdges.getInVertex();

				Edge e = org.util.GraphDb.getDB().addEdge(null, this.auditVertex, previousAuditInVertex,
						previousAuditOutEdges.getLabel() + org.util.GraphDb.getTransactionCount());
				e.setProperty("outClass", this.getClass().getName());
				e.setProperty("inClass", this.getClass().getName() + "Audit");

			}
		}
	}


	public Set<UniverseAudit> getAudits() {
		Set<UniverseAudit> result = new HashSet<UniverseAudit>();
		Iterable<Edge> iter = this.vertex.getOutEdges();
		for (Edge edge : iter) {
			if (edge.getLabel().startsWith("A__god___audit")) {
				try {
					Class<?> c = Class.forName((String) edge.getProperty("inClass"));
					result.add((UniverseAudit) c.getConstructor(Vertex.class).newInstance(edge.getInVertex()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}

}