/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.cubetree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.olap4j.Axis;
import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;
import org.olap4j.OlapConnection;
import org.olap4j.Position;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;

public class CubeRow{
	public Cube cube;
	public Member member;
	public List<CubeRowTree> rootTrees = new ArrayList<CubeRowTree>();
	public CubeRow parent;
	private ArrayList<CubeRow> children;
	public ArrayList<CubeRow> expand() throws SQLException{
		if(children == null){
			children = new ArrayList<CubeRow>();
			// TODO if datamembers.isEmpty(), then switch to the first level of the next dimension in CubeQuery
			// TODO skip levels not in the CubeQuery
			Query query = new Query("peter", cube);
			QueryDimension dim = query.getDimension(member.getDimension().getName());
			query.getAxis(Axis.ROWS).addDimension(dim);
			Set<Member> includedMembers = new HashSet<Member>();
			for(Member member:this.member.getChildMembers()){
				if(!includedMembers.contains(member)){
					dim.include(member);
					includedMembers.add(member);
				}
			}
			QueryAxis columnAxis = query.getAxis(Axis.COLUMNS);
			if(rootTrees.size() > 0){
				QueryDimension columnDim = query.getDimension(rootTrees.get(0).member.getDimension().getName());
				QueryDimension measuresDim = query.getDimension(rootTrees.get(0).measure.getDimension().getName());
				columnAxis.addDimension(columnDim);
				Set<Member> includedMeasures = new HashSet<Member>();
				includedMembers = new HashSet<Member>();
				for(CubeRowTree tree:rootTrees){
					if(!includedMembers.contains(tree.member)){
						columnDim.include(tree.member);
						includedMembers.add(tree.member);
					}
					if(!includedMeasures.contains(tree.measure)){
						measuresDim.include(tree.measure);
						includedMeasures.add(tree.measure);
					}
				}
			}
			CellSet cellSet = query.execute();
			CellSetAxis columnCellSetAxis = cellSet.getAxes().get(Axis.COLUMNS.axisOrdinal());
			CellSetAxis rowCellSetAxis = cellSet.getAxes().get(Axis.ROWS.axisOrdinal());
			for(Position rowPosition:rowCellSetAxis.getPositions()){
				CubeRow child = new CubeRow();
				children.add(child);
				for(Member rowMember:rowPosition.getMembers()){
					if(rowMember.getParentMember().equals(this.member)){
						// Will be in this dimension
						child.member = rowMember;
						child.cube = cube;
						child.parent = this;
						child.rootTrees = new ArrayList<CubeRowTree>();
						for(Position colPosition:columnCellSetAxis.getPositions()){
							List<Member> members = colPosition.getMembers();
							for(CubeRowTree tree:rootTrees){
								if(members.contains(tree.member)){
									CubeRowTree newRoot = new CubeRowTree();
									newRoot.member = tree.member;
									newRoot.measure = tree.measure;
									newRoot.row = child;
									newRoot.value = cellSet.getCell(colPosition, rowPosition).getFormattedValue();
									child.rootTrees.add(newRoot);
								}
							}
						}
					}
				}
			}
		}
		return children;
	}
	public CubeRowTree findRowTreeNode(Member member){
		for(CubeRowTree cubeRowTree:rootTrees){
			if(cubeRowTree.member.equals(member)){
				return cubeRowTree;
			}
		}
		return null;
	}
}
