package org.opaeum.rap.runtime.cubetree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.olap4j.Axis.Standard;
import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;
import org.olap4j.Position;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;

public abstract class AbstractCubeNode{
	public Cube cube;
	public Member member;
	public Member measure;
	public Position position;
	public AbstractCubeNode parent;
	public AbstractCubeNode nodeOnOtherAxis;
	private ArrayList<AbstractCubeNode> children = new ArrayList<AbstractCubeNode>();
	private boolean expanded;
	public void expand() throws SQLException{
		if(!expanded){
			expanded = true;
			// TODO if datamembers.isEmpty(), then switch to the first level of the next dimension in CubeQuery
			// TODO skip levels not in the CubeQuery
			Query query = new Query("peter", cube);
			QueryDimension dim = query.getDimension(member.getDimension().getName());
			query.getAxis(getAxis()).addDimension(dim);
			Set<Member> includedMembers = new HashSet<Member>();
			for(Member member:this.member.getChildMembers()){
				if(!includedMembers.contains(member)){
					dim.include(member);
					includedMembers.add(member);
				}
			}
			QueryAxis columnAxis = query.getAxis(getOtherAxis());
			QueryDimension columnDim = query.getDimension(nodeOnOtherAxis.member.getDimension().getName());
			QueryDimension measuresDim = query.getDimension(nodeOnOtherAxis.measure.getDimension().getName());
			columnAxis.addDimension(columnDim);
			Set<Member> includedMeasures = new HashSet<Member>();
			includedMembers = new HashSet<Member>();
			for(AbstractCubeNode tree:nodeOnOtherAxis.children){
				if(!includedMembers.contains(tree.member)){
					columnDim.include(tree.member);
					includedMembers.add(tree.member);
				}
				if(!includedMeasures.contains(tree.measure)){
					measuresDim.include(tree.measure);
					includedMeasures.add(tree.measure);
				}
			}
			CellSet cellSet = query.execute();
			CellSetAxis columnCellSetAxis = cellSet.getAxes().get(getOtherAxis().axisOrdinal());
			CellSetAxis rowCellSetAxis = cellSet.getAxes().get(getAxis().axisOrdinal());
			for(Position rowPosition:rowCellSetAxis.getPositions()){
				AbstractCubeNode child = createChild();
				children.add(child);
				for(Member rowMember:rowPosition.getMembers()){
					if(rowMember.getParentMember().equals(this.member)){
						// Will be in this dimension
						child.member = rowMember;
						child.cube = cube;
						child.parent = this;
						child.nodeOnOtherAxis = nodeOnOtherAxis;
						for(Position positionOnOtherAxis:columnCellSetAxis.getPositions()){
							List<Member> members = positionOnOtherAxis.getMembers();
							for(AbstractCubeNode tree:nodeOnOtherAxis.children){
								if(members.contains(tree.member)){
									CubeColumnNode newRoot = new CubeColumnNode();
									newRoot.member = tree.member;
									newRoot.measure = tree.measure;
									newRoot.nodeOnOtherAxis = child;
									newRoot.position = positionOnOtherAxis;
									newRoot.value = cellSet.getCell(positionOnOtherAxis, rowPosition).getFormattedValue();
									child.rootTrees.add(newRoot);
								}
							}
						}
					}
				}
			}
		}
	}
	protected abstract AbstractCubeNode createChild();
	protected abstract Standard getOtherAxis();
	protected abstract Standard getAxis();
	public List<AbstractCubeNode> getChildren(){
		return children;
	}
	public CubeColumnNode findRowTreeNode(Member member){
		for(CubeColumnNode cubeRowTree:rootTrees){
			if(cubeRowTree.member.equals(member)){
				return cubeRowTree;
			}
		}
		return null;
	}
}
