package org.opaeum.rap.runtime.cubetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.olap4j.Axis.Standard;
import org.olap4j.CellSet;
import org.olap4j.Position;
import org.olap4j.metadata.Member;

public class CubeRowNode extends AbstractCubeNode{
	public Map<CubeColumnNode,TreeCubeCell> cells = new HashMap<CubeColumnNode,TreeCubeCell>();
	protected List<CubeRowNode> children;
	public CubeRowNode(TreeCube cube,AbstractCubeNode parent,Member member){
		super(cube, parent, member);
	}
	public List<CubeRowNode> getChildren(){
		if(children == null){
			try{
				children = new ArrayList<CubeRowNode>();
				for(Member childMember:member.getChildMembers()){
					children.add(new CubeRowNode(cube, this, childMember));
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		return children;
	}
	public void addCell(CubeColumnNode column, CellSet cellSet){
		Position rowPosition = getPosition(cellSet.getAxes().get(Standard.ROWS.axisOrdinal()));
		Position columnPosition = column.getPosition(cellSet.getAxes().get(Standard.ROWS.axisOrdinal()));
		cells.put(column, new TreeCubeCell(cellSet, rowPosition, columnPosition));
	}
	public TreeCubeCell getCell(CubeColumnNode node){
		return cells.get(node);
	}
}
