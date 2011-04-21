package org.audittest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;

public class Finger implements Serializable, TinkerNode, TinkerCompositionNode {
	protected Hand god;
	private String name;
	private String uid;
	protected Vertex vertex;

	/** Constructor for Finger
	 * 
	 * @param vertex 
	 */
	public Finger(Vertex vertex) {
		this.vertex=vertex;
	}
	
	/** Default constructor for 
	 */
	public Finger() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);;
	}
	
	/** Constructor for Finger
	 * 
	 * @param owningObject 
	 */
	public Finger(Hand owningObject) {
		this();
		init(owningObject);
		initVertex(owningObject);
	}

	public void copyShallowState(Finger from, Finger to) {
		to.setName(from.getName());
	}
	
	public void copyState(Finger from, Finger to) {
		to.setName(from.getName());
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Finger ) {
			return other==this || ((Finger)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Hand getGod() {
		Iterable<Edge> iter1 = this.vertex.getInEdges("A__god___universe_");
		if ( iter1.iterator().hasNext() ) {
			try {
				Edge edge = iter1.iterator().next();
				Class<?> c = Class.forName((String) edge.getProperty("outClass"));
				return (Hand) c.getConstructor(Vertex.class).newInstance(edge.getOutVertex());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	public String getName() {
		return (String) this.vertex.getProperty("org__auditTest__Finger__name");
	}
	
	public TinkerCompositionNode getOwningObject() {
		return getGod();
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
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
		internalSetOwner((Hand)owner);
		createComponents();
	}
	
	public Finger makeCopy() {
		Finger result = new Finger();
		copyState((Finger)this,result);
		return result;
	}
	
	public Finger makeShallowCopy() {
		Finger result = new Finger();
		copyShallowState((Finger)this,result);
		return result;
	}
	
	public void markDeleted() {
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setGod(Hand god) {
		Iterable<Edge> iter = this.vertex.getInEdges("A__god___universe_");
		if ( iter.iterator().hasNext() ) {
			org.util.GraphDb.getDB().removeEdge(iter.iterator().next());
		}
		if ( god!=null ) {
			Edge edge = org.util.GraphDb.getDB().addEdge(null, ((TinkerNode)god).getVertex(), this.vertex,"A__god___universe_");
			edge.setProperty("outClass", god.getClass().getName());
			edge.setProperty("inClass", this.getClass().getName());
		}
	}
	
	public void setName(String name) {
		this.vertex.setProperty("org__auditTest__Finger__name", name);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		if ( getGod()==null ) {
			sb.append("god=null;");
		} else {
			sb.append("god="+getGod().getClass().getSimpleName()+"[");
			sb.append(getGod().getName());
			sb.append("];");
		}
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getGod()==null ) {
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
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToGod(Hand god) {
		this.god=god;
	}
	
	public void z_internalRemoveFromGod(Hand god) {
		if ( getGod()!=null && getGod().equals(god) ) {
			this.god=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(Hand newOwner) {
		this.god=newOwner;
	}
	
	public void createComponents() {
	}
	
	private void initVertex(Hand owningObject) {
		Edge edge = org.util.GraphDb.getDB().addEdge(null, owningObject.getVertex(), this.vertex, "A__god___universe_");
		edge.setProperty("outClass", owningObject.getClass().getName());
		edge.setProperty("inClass", this.getClass().getName());
	}

}