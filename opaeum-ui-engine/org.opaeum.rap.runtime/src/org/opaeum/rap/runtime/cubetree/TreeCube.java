package org.opaeum.rap.runtime.cubetree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.olap4j.Axis.Standard;
import org.olap4j.CellSet;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;

public class TreeCube{
	private List<CubeColumnNode> columnRoots = new ArrayList<CubeColumnNode>();
	private List<CubeRowNode> rowRoots = new ArrayList<CubeRowNode>();
	private Cube cube;
	public TreeCube(Cube cube2){
		this.cube = cube2;
	}
	public void addRowNode(LevelHolder level,Member member,CubeFilter filter){
		rowRoots.add(new CubeRowNode(level, member, filter));
	}
	public List<Object> getRows(){
		List<Object> result = new ArrayList<Object>();
		int level = 0;
		List<CubeColumnNode> allExpandedColumns = getAllExpandedColumns();
		for(CubeColumnNode cubeColumnNode:allExpandedColumns){
			if(cubeColumnNode.getLevel() > level){
				level = cubeColumnNode.getLevel();
			}
		}
		for(int i = 0;i <= level;i++){
			result.add(new ColumnHeaderRow(i));
		}
		result.addAll(getRowRoots());
		return result;
	}
	public void addColumnNode(LevelHolder level,Member member,CubeFilter filter,List<Measure> measures){
		columnRoots.add(new CubeColumnNode(level, member, filter, measures));
	}
	public void populateExpandedRows(List<CubeRowNode> expandedRows) throws SQLException{
		populateCubeCells(columnRoots, expandedRows);
		for(CubeColumnNode columnNode:columnRoots){
			populateCellsRecursivelyIntoRows(columnNode.getExpandedChildren(), expandedRows);
		}
	}
	public List<CubeColumnNode> getAllExpandedColumns(){
		List<CubeColumnNode> result = new ArrayList<CubeColumnNode>();
		List<CubeColumnNode> columnRoots2 = columnRoots;
		addChildren(result, columnRoots2);
		return result;
	}
	protected void addChildren(List<CubeColumnNode> result,List<CubeColumnNode> columnRoots2){
		for(CubeColumnNode cubeColumnNode:columnRoots2){
			result.add(cubeColumnNode);
			addChildren(result, cubeColumnNode.getExpandedChildren());
		}
	}
	public void populateExpandedColumns(List<CubeColumnNode> expandedColumns) throws SQLException{
		// recursively go through rowNodes and populate their cells with queries crosstabing the current list of rowNodes with the given
		// columnNodes
		populateCellsRecursivelyIntoRows(expandedColumns, rowRoots);
	}
	protected void populateCellsRecursivelyIntoRows(List<CubeColumnNode> expandedColumns,List<CubeRowNode> rowNodes) throws SQLException,
			OlapException{
		populateCubeCells(expandedColumns, rowNodes);
		for(CubeRowNode cubeRowNode:rowNodes){
			populateCellsRecursivelyIntoRows(expandedColumns, cubeRowNode.getExpandedChildren());
		}
	}
	private void populateCubeCells(List<CubeColumnNode> columnNodes,List<CubeRowNode> rowNodes) throws SQLException,OlapException{
		// TODO optimise by checking if the cells are present or not
		Query query = new Query("peter", cube);
		Set<Member> includedMembers = new HashSet<Member>();
		QueryAxis columnAxis = query.getAxis(Standard.COLUMNS);
		boolean measuresIncluded = false;
		QueryDimension measuresDim = query.getDimension("Measures");
		columnAxis.addDimension(measuresDim);
		for(CubeColumnNode member:columnNodes){
			if(!measuresIncluded){
				List<Measure> measures = member.getMeasures();
				for(Measure measure:measures){
					measuresDim.include(measure);
				}
			}
			member.includeInQuery(query, columnAxis, includedMembers);
		}
		includedMembers = new HashSet<Member>();
		QueryAxis rowAxis = query.getAxis(Standard.ROWS);
		for(AbstractCubeNode tree:rowNodes){
			tree.includeInQuery(query, rowAxis, includedMembers);
		}
		CellSet cellSet = query.execute();
		for(CubeRowNode cubeRowNode:rowNodes){
			for(CubeColumnNode cubeColumnNode:columnNodes){
				cubeRowNode.addCells(cubeColumnNode, cellSet);
			}
		}
	}
	public List<CubeRowNode> getRowRoots(){
		return rowRoots;
	}
}
