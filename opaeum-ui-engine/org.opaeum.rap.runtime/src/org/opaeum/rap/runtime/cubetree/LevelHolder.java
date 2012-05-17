package org.opaeum.rap.runtime.cubetree;

import org.olap4j.metadata.Level;

public class LevelHolder{
	private Level level;
	private LevelHolder next;
	public LevelHolder(Level level){
		super();
		this.level = level;
	}
	public boolean areChildrenNewDimension(){
		return next != null && !next.level.getDimension().equals(level.getDimension());
	}
	public Level getLevel(){
		return level;
	}
	public LevelHolder getNext(){
		return next;
	}
	public void setNext(LevelHolder next){
		this.next = next;
	}
	public String toString(){
		return level.getUniqueName();
	}
}
