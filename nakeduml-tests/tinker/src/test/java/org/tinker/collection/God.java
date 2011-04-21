package org.tinker.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.collection.LazySet;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class God implements Serializable, TinkerNode, TinkerCompositionNode {
	private Set<Universe> universe = new HashSet<Universe>();
	private String uid;
	protected Vertex vertex;

	/** Default constructor for 
	 */
	public God() {
		this.vertex = org.util.GraphDb.getDB().addVertex(null);;
	}
	
	/** Constructor for God
	 * 
	 * @param vertex 
	 */
	public God(Vertex vertex) {
		this.vertex=vertex;
	}

	public void addAllToUniverse(Set<Universe> universe) {
		for ( Universe o : universe ) {
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
		for ( Universe child : from.getUniverse() ) {
			to.addToUniverse(child.makeCopy());
		}
	}
	
	public Universe createUniverse() {
		Universe newInstance= new Universe();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof God ) {
			return other==this || ((God)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public String getName() {
		return (String) this.vertex.getProperty("org__tinker__God__name");
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
	
	public Set<Universe> getUniverse() {
		Iterable<Edge> iter = this.vertex.getOutEdges("A__god___universe_");
		return new LazySet(iter);
	}
	
	public Vertex getVertex() {
		return this.vertex;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(TinkerCompositionNode owner) {
	}
	
	public God makeCopy() {
		God result = new God();
		copyState((God)this,result);
		return result;
	}
	
	public God makeShallowCopy() {
		God result = new God();
		copyShallowState((God)this,result);
		return result;
	}
	
	public void markDeleted() {
		for ( Universe child : new ArrayList<Universe>(getUniverse()) ) {
			child.markDeleted();
		}
		org.util.GraphDb.getDB().removeVertex(this.vertex);
	}
	
	public void removeAllFromUniverse(Set<Universe> universe) {
		Set<Universe> tmp = new HashSet<Universe>(universe);
		for ( Universe o : tmp ) {
			removeFromUniverse(o);
		}
	}
	
	public void removeFromUniverse(Universe universe) {
		universe.setGod(null);
	}
	
	public void setName(String name) {
		this.vertex.setProperty("org__tinker__God__name", name);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUniverse(Set<Universe> universe) {
		for ( Universe o : new HashSet<Universe>(getUniverse()) ) {
			o.setGod(null);
		}
		for ( Universe o : universe ) {
			o.setGod((God)this);
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
		for ( Universe universe : getUniverse() ) {
			sb.append("<universe>");
			sb.append(universe.toXmlString());
			sb.append("</universe>");
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void removeFromOwningObject() {
		// TODO Auto-generated method stub
		
	}

}