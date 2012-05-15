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
		if(parent == null){
			return 1;
		}else{
			return 1 + ((CubeColumnNode) parent).getLevel();
		}
	}
	public void expand(){
		if(!expanded){
			expanded = true;
		}
	}
	public List<CubeColumnNode> getChildren(){
		if(expanded){
			if(children == null){
				children = new ArrayList<CubeColumnNode>();
				populateChildren();
			}
			return children;
		}else{
			return Collections.emptyList();
		}
	}
	@Override
	protected void addChild(Member childMember){
		children.add(new CubeColumnNode(cube, this, childMember));
	}
}
