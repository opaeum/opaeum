package org.audittest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;

public class Hand implements Serializable, TinkerNode, TinkerCompositionNode {
	private Set<Finger> universe = new HashSet<Finger>();
	private String name;
	private String uid;
	protected Vertex vertex;

	/** Default constructor for 
	 */
	public Hand() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);;
	}
	
	/** Constructor for Hand
	 * 
	 * @param vertex 
	 */
	public Hand(Vertex vertex) {
		this.vertex=vertex;
	}

	public void addAllToUniverse(Set<Finger> universe) {
		for ( Finger o : universe ) {
			addToUniverse(o);
		}
	}
	
	public void addToUniverse(Finger universe) {
		universe.setGod(this);
	}
	
	public void clearUniverse() {
		removeAllFromUniverse(getUniverse());
	}
	
	public void copyShallowState(Hand from, Hand to) {
		to.setName(from.getName());
	}
	
	public void copyState(Hand from, Hand to) {
		to.setName(from.getName());
		for ( Finger child : from.getUniverse() ) {
			to.addToUniverse(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public Finger createUniverse() {
		Finger newInstance= new Finger();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Hand ) {
			return other==this || ((Hand)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public String getName() {
		return (String) this.vertex.getProperty("org__auditTest__Hand__name");
	}
	
	public TinkerCompositionNode getOwningObject() {
		return null;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public Set<Finger> getUniverse() {
		Set<Finger> result = new HashSet<Finger>();
		Iterable<Edge> iter = this.vertex.getOutEdges("A__god___universe_");
		for ( Edge edge : iter ) {
			try {
				Class<?> c = Class.forName((String) edge.getProperty("inClass"));
				result.add((Finger)c.getConstructor(Vertex.class).newInstance(edge.getInVertex()));
			} catch (Exception e) {
				throw new RuntimeException(e);
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
	
	public Hand makeCopy() {
		Hand result = new Hand();
		copyState((Hand)this,result);
		return result;
	}
	
	public Hand makeShallowCopy() {
		Hand result = new Hand();
		copyShallowState((Hand)this,result);
		return result;
	}
	
	public void markDeleted() {
		for ( Finger child : new ArrayList<Finger>(getUniverse()) ) {
			child.markDeleted();
		}
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}
	
	public void removeAllFromUniverse(Set<Finger> universe) {
		Set<Finger> tmp = new HashSet<Finger>(universe);
		for ( Finger o : tmp ) {
			removeFromUniverse(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromUniverse(Finger universe) {
		universe.setGod(null);
	}
	
	public void setName(String name) {
		this.vertex.setProperty("org__auditTest__Hand__name", name);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUniverse(Set<Finger> universe) {
		for ( Finger o : new HashSet<Finger>(getUniverse()) ) {
			o.setGod(null);
		}
		for ( Finger o : universe ) {
			o.setGod((Hand)this);
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
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		for ( Finger universe : getUniverse() ) {
			sb.append("<universe>");
			sb.append(universe.toXmlString());
			sb.append("</universe>");
			sb.append("\n");
		}
		return sb.toString();
	}

}