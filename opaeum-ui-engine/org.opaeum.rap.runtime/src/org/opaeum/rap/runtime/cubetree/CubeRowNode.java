package org.opaeum.rap.runtime.cubetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.olap4j.Axis.Standard;
import org.olap4j.CellSet;
import org.olap4j.Position;
import org.olap4j.metadata.Member;

public class CubeRowNode extends AbstractCubeNode{
	public Map<CellKey,TreeCubeCell> cells = new HashMap<CellKey,TreeCubeCell>();
	protected List<CubeRowNode> children;
	public CubeRowNode(LevelHolder level,Member member,CubeFilter filter){
		super(level, member, filter);
	}
	private CubeRowNode(AbstractCubeNode parent,Member member){
		super(parent, member);
	}
	public List<CubeRowNode> getExpandedChildren(){
		if(expanded){
			return maybeLoadChildren();
		}else{
			return Collections.emptyList();
		}
	}
	public List<CubeRowNode> maybeLoadChildren(){
		if(children == null){
			children = new ArrayList<CubeRowNode>();
			populateChildren();
		}
		return children;
	}
	protected void addChild(Member childMember){
		children.add(new CubeRowNode(this, childMember));
	}
	public void addCells(CubeColumnNode column,CellSet cellSet){
		Position rowPosition = getPosition(cellSet.getAxes().get(Standard.ROWS.axisOrdinal()),null);
		for(Member measure:column.getMeasures()){
			Position columnPosition = column.getPosition(cellSet.getAxes().get(Standard.COLUMNS.axisOrdinal()),measure);
			cells.put(new CellKey(column,measure), new TreeCubeCell(cellSet, rowPosition, columnPosition));
		}
	}
	public TreeCubeCell getCell(CubeColumnNode node, Member measure){
		return cells.get(new CellKey(node,measure));
	}
}
