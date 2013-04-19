package org.opaeum.runtime.jface.cubetree;

import org.olap4j.metadata.Member;

public class CellKey{
	CubeColumnNode node;
	Member measure;
	@Override
	public boolean equals(Object obj){
		return toString().equals(obj.toString());
	}
	public String toString(){
		return node.levelHolder.toString() + node.member.getName() + measure.getName();
	}
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	public CellKey(CubeColumnNode node,Member measure){
		super();
		this.node = node;
		this.measure = measure;
	}
}
