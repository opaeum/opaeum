package org.audittest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.util.TransactionThreadVar;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class God implements Serializable, TinkerNode, TinkerCompositionNode {
	private String name;
	private Set<Universe> universe = new HashSet<Universe>();
	private String uid;
	protected Vertex vertex;
	protected Vertex auditVertex;

	/**
	 * Default constructor for
	 */
	public God() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);
		createAuditVertex();
	}

	/**
	 * Constructor for God
	 * 
	 * @param vertex
	 */
	public God(Vertex vertex) {
		this.vertex = vertex;
	}

	public void addAllToUniverse(Set<Universe> universe) {
		for (Universe o : universe) {
			addToUniverse(o);
		}
	}

	public void addToUniverse(Universe universe) {
		universe.setGod(this);
	}

	public void clearUniverse() {
		removeAllFromUniverse(getUniverse());
	}

	public void copyShallowState(God from, God to) {
		to.setName(from.getName());
	}

	public void copyState(God from, God to) {
		to.setName(from.getName());
		for (Universe child : from.getUniverse()) {
			to.addToUniverse(child.makeCopy());
		}
	}

	public void createComponents() {
	}

	public Universe createUniverse() {
		Universe newInstance = new Universe();
		newInstance.init(this);
		return newInstance;
	}

	public boolean equals(Object other) {
		if (other instanceof God) {
			return other == this || ((God) other).getUid().equals(this.getUid());
		}
		return false;
	}

	public String getName() {
		return (String) this.vertex.getProperty("org__auditTest__God__name");
	}

	public TinkerCompositionNode getOwningObject() {
		return null;
	}

	public String getUid() {
		if (this.uid == null || this.uid.trim().length() == 0) {
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}

	public Set<Universe> getUniverse() {
		Set<Universe> result = new HashSet<Universe>();
		Iterable<Edge> iter = this.vertex.getOutEdges("A__god___universe_");
		for (Edge edge : iter) {
			try {
				Class<?> c = Class.forName((String) edge.getProperty("inClass"));
				result.add((Universe) c.getConstructor(Vertex.class).newInstance(edge.getInVertex()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	public Set<GodAudit> getAudits() {
		Set<GodAudit> result = new HashSet<GodAudit>();
		Iterable<Edge> iter = this.vertex.getOutEdges();
		for (Edge edge : iter) {
			if (edge.getLabel().startsWith("A__god___audit")) {
				try {
					Class<?> c = Class.forName((String) edge.getProperty("inClass"));
					result.add((GodAudit) c.getConstructor(Vertex.class).newInstance(edge.getInVertex()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}

	public Vertex getVertex() {
		return this.vertex;
	}

	public int hashCode() {
		return getUid().hashCode();
	}

	public void init(TinkerCompositionNode owner) {
		createComponents();
	}

	public God makeCopy() {
		God result = new God();
		copyState((God) this, result);
		return result;
	}

	public God makeShallowCopy() {
		God result = new God();
		copyShallowState((God) this, result);
		return result;
	}

	public void markDeleted() {
		for (Universe child : new ArrayList<Universe>(getUniverse())) {
			child.markDeleted();
		}
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}

	public void removeAllFromUniverse(Set<Universe> universe) {
		Set<Universe> tmp = new HashSet<Universe>(universe);
		for (Universe o : tmp) {
			removeFromUniverse(o);
		}
	}

	public void removeFromOwningObject() {
		this.markDeleted();
	}

	public void removeFromUniverse(Universe universe) {
		universe.setGod(null);
	}

	public void setName(String name) {
		this.vertex.setProperty("org__auditTest__God__name", name);
		if (TransactionThreadVar.getNewVertex(this.getClass().getName() + getUid())) {
			createAuditVertex();
		}
		this.auditVertex.setProperty("org__auditTest__God__name", name);
	}

	public void setUid(String newUid) {
		this.uid = newUid;
	}

	public void setUniverse(Set<Universe> universe) {
		for (Universe o : new HashSet<Universe>(getUniverse())) {
			o.setGod(null);
		}
		for (Universe o : universe) {
			o.setGod((God) this);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
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
		for (Universe universe : getUniverse()) {
			sb.append("<universe>");
			sb.append(universe.toXmlString());
			sb.append("</universe>");
			sb.append("\n");
		}
		return sb.toString();
	}

	public Vertex createAuditVertex() {
		if (TransactionThreadVar.getNewVertex(getClass().getName() + getUid())) {
			this.auditVertex = org.util.GraphDb.getDB().addVertex(null);
			TransactionThreadVar.setNewVertexFalse(getClass().getName() + getUid());

			Edge edge = org.util.GraphDb.getDB().addEdge(null, this.vertex, this.auditVertex, "A__god___audit" + org.util.GraphDb.getTransactionCount());
			edge.setProperty("outClass", this.getClass().getName());
			edge.setProperty("inClass", this.getClass().getName() + "Audit");

			// Attach to all previous audit vertex's outEdges
			attachPreviousAuditsOutEdges();

		}
		return this.auditVertex;
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
}