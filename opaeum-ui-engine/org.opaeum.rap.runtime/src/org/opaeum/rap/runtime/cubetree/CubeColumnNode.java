package org.opaeum.rap.runtime.cubetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.olap4j.metadata.Member;

public class CubeColumnNode extends AbstractCubeNode{
	protected boolean expanded;
	private List<CubeColumnNode> children;
	public CubeColumnNode(TreeCube cube,AbstractCubeNode parent,Member member){
		super(cube, parent, member);
	}
	public int getLevel(){
		if(parent==null){
			return 1;
		}else{
			return 1+((CubeColumnNode) parent).getLevel();
		}
	}
	public void expand(){
		if(!expanded){
			expanded = true;
		}
		if(children == null){
			try{
				children = new ArrayList<CubeColumnNode>();
				for(Member childMember:member.getChildMembers()){
					children.add(new CubeColumnNode(cube, this, childMember));
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	public List<CubeColumnNode> getChildren(){
		if(expanded){
			return children;
		}else{
			return Collections.emptyList();
		}
	}
}
