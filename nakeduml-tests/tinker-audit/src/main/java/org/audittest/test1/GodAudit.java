package org.audittest.test1;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;

public class GodAudit implements Serializable, TinkerNode, TinkerCompositionNode {
	private String name;
	private Set<UniverseAudit> universe = new HashSet<UniverseAudit>();
	private String uid;
	protected Vertex vertex;

	/** Default constructor for 
	 */
	public GodAudit() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);;
	}
	
	/** Constructor for God
	 * 
	 * @param vertex 
	 */
	public GodAudit(Vertex vertex) {
		this.vertex=vertex;
	}

	public void addAllToUniverse(Set<UniverseAudit> universe) {
		for ( UniverseAudit o : universe ) {
			addToUniverse(o);
		}
	}
	
	public void addToUniverse(UniverseAudit universe) {
		universe.setGod(this);
	}
	
	public void clearUniverse() {
		removeAllFromUniverse(getUniverse());
	}
	
	public void copyShallowState(GodAudit from, GodAudit to) {
		to.setName(from.getName());
	}
	
	public void copyState(GodAudit from, GodAudit to) {
		to.setName(from.getName());
		for ( UniverseAudit child : from.getUniverse() ) {
			to.addToUniverse(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public Universe createUniverse() {
		Universe newInstance= new Universe();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof GodAudit ) {
			return other==this || ((GodAudit)other).getUid().equals(this.getUid());
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
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public Set<UniverseAudit> getUniverse() {
		Set<UniverseAudit> result = new HashSet<UniverseAudit>();
		Iterable<Edge> iter = this.vertex.getOutEdges("A__god___universe_");
		for ( Edge edge : iter ) {
			try {
				Class<?> c = Class.forName((String) edge.getProperty("inClass"));
				result.add((UniverseAudit)c.getConstructor(Vertex.class).newInstance(edge.getInVertex()));
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
	
	public GodAudit makeCopy() {
		GodAudit result = new GodAudit();
		copyState((GodAudit)this,result);
		return result;
	}
	
	public GodAudit makeShallowCopy() {
		GodAudit result = new GodAudit();
		copyShallowState((GodAudit)this,result);
		return result;
	}
	
	public void markDeleted() {
		for ( UniverseAudit child : new ArrayList<UniverseAudit>(getUniverse()) ) {
			child.markDeleted();
		}
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}
	
	public void removeAllFromUniverse(Set<UniverseAudit> universe) {
		Set<UniverseAudit> tmp = new HashSet<UniverseAudit>(universe);
		for ( UniverseAudit o : tmp ) {
			removeFromUniverse(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromUniverse(UniverseAudit universe) {
		universe.setGod(null);
	}
	
	public void setName(String name) {
		this.vertex.setProperty("org__auditTest__God__name", name);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUniverse(Set<UniverseAudit> universe) {
		for ( UniverseAudit o : new HashSet<UniverseAudit>(getUniverse()) ) {
			o.setGod(null);
		}
		for ( UniverseAudit o : universe ) {
			o.setGod((GodAudit)this);
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
		for ( UniverseAudit universe : getUniverse() ) {
			sb.append("<universe>");
			sb.append(universe.toXmlString());
			sb.append("</universe>");
			sb.append("\n");
		}
		return sb.toString();
	}

}