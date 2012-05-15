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
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;

public class TreeCube{
	List<CubeColumnNode> columnRoots = new ArrayList<CubeColumnNode>();
	List<CubeRowNode> rowRoots = new ArrayList<CubeRowNode>();
	Cube cube;
	public TreeCube(Cube cube2){
		this.cube=cube2;
	}
	public void populateExpandedRows(List<CubeRowNode> expandedRows) throws SQLException{
		populate(columnRoots, expandedRows);
		for(CubeColumnNode cubeRowNode:columnRoots){
			populateCellsRecursivelyIntoRows(cubeRowNode.getChildren(), expandedRows);
		}
	}
	public List<CubeColumnNode> getColumns(){
		List<CubeColumnNode> result= new ArrayList<CubeColumnNode>();
		List<CubeColumnNode> columnRoots2 = columnRoots;
		addChildren(result, columnRoots2);
		return result;
	}
	protected void addChildren(List<CubeColumnNode> result,List<CubeColumnNode> columnRoots2){
		for(CubeColumnNode cubeColumnNode:columnRoots2){
			result.add(cubeColumnNode);
			addChildren(result, cubeColumnNode.getChildren());
		}
	}
	public void populateExpandedColumns(List<CubeColumnNode> expandedColumns) throws SQLException{
		// recursively go through rowNodes and populate their cells with queries crosstabing the current list of rowNodes with the given
		// columnNodes
		// TODO skip levels not in the CubeQuery
		List<CubeRowNode> rowRoots2 = rowRoots;
		populateCellsRecursivelyIntoRows(expandedColumns, rowRoots2);
	}
	protected void populateCellsRecursivelyIntoRows(List<CubeColumnNode> expandedColumns,List<CubeRowNode> rowRoots2) throws SQLException,
			OlapException{
		populate(expandedColumns, rowRoots2);
		for(CubeRowNode cubeRowNode:rowRoots2){
			populateCellsRecursivelyIntoRows(expandedColumns, cubeRowNode.getChildren());
		}
	}
	protected void populate(List<CubeColumnNode> expandedColumns,List<CubeRowNode> rowRoots2) throws SQLException,OlapException{
		Query query = new Query("peter", cube);
		Set<Member> includedMembers = new HashSet<Member>();
		QueryAxis columnAxis = query.getAxis(Standard.COLUMNS);
		for(CubeColumnNode member:expandedColumns){
			member.include(query, columnAxis, includedMembers);
		}
		Set<Member> includedMeasures = new HashSet<Member>();
		includedMembers = new HashSet<Member>();
		QueryAxis rowAxis = query.getAxis(Standard.ROWS);
		for(AbstractCubeNode tree:rowRoots2){
			tree.include(query, rowAxis, includedMembers);
		}
		CellSet cellSet = query.execute();
		for(CubeRowNode cubeRowNode:rowRoots2){
			for(CubeColumnNode cubeColumnNode:expandedColumns){
				cubeRowNode.addCell(cubeColumnNode, cellSet);
			}
		}
	}
}
