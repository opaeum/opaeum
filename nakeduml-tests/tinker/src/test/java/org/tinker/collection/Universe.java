package org.tinker.collection;

import java.io.Serializable;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class Universe implements Serializable, TinkerNode, TinkerCompositionNode {
	private String name;
	protected God god;
	private String uid;
	protected Vertex vertex;

	/** Constructor for Universe
	 * 
	 * @param vertex 
	 */
	public Universe(Vertex vertex) {
		this.vertex=vertex;
	}
	
	/** Constructor for Universe
	 * 
	 * @param owningObject 
	 */
	public Universe(God owningObject) {
		this();
		init(owningObject);
		initVertex(owningObject);
	}
	
	/** Default constructor for 
	 */
	public Universe() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);;
	}

	public void copyShallowState(Universe from, Universe to) {
		to.setName(from.getName());
	}
	
	public void copyState(Universe from, Universe to) {
		to.setName(from.getName());
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Universe ) {
			return other==this || ((Universe)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public God getGod() {
		Iterable<Edge> iter1 = this.vertex.getInEdges("A__god___universe_");
		if ( iter1.iterator().hasNext() ) {
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
		return (String) this.vertex.getProperty("org__tinker__Universe__name");
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
		internalSetOwner((God)owner);
		createComponents();
	}
	
	public Universe makeShallowCopy() {
		Universe result = new Universe();
		copyShallowState((Universe)this,result);
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
		this.vertex.setProperty("org__tinker__Universe__name", name);
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
	
	public void z_internalAddToGod(God god) {
		this.god=god;
	}
	
	public void z_internalRemoveFromGod(God god) {
		if ( getGod()!=null && getGod().equals(god) ) {
			this.god=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(God newOwner) {
		this.god=newOwner;
	}
	
	public void createComponents() {
	}
	
	public Universe makeCopy() {
		Universe result = new Universe();
		copyState((Universe)this,result);
		return result;
	}
	
	private void initVertex(God owningObject) {
		Edge edge = org.util.GraphDb.getDB().addEdge(null, owningObject.getVertex(), this.vertex, "A__god___universe_");
		edge.setProperty("outClass", owningObject.getClass().getName());
		edge.setProperty("inClass", this.getClass().getName());
	}

}