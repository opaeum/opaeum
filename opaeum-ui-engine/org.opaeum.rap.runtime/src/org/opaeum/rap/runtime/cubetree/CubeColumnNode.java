package org.opaeum.rap.runtime.cubetree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;

public class CubeColumnNode extends AbstractCubeNode{
	private List<CubeColumnNode> children;
	private List<Measure> measures;
	public CubeColumnNode(LevelHolder level,Member member,CubeFilter filter,List<Measure> measures){
		super(level, member, filter);
		this.measures = measures;
	}
	private CubeColumnNode(AbstractCubeNode parent,Member member){
		super(parent, member);
	}
	@Override
	protected void addChild(Member childMember){
		children.add(new CubeColumnNode(this, childMember));
	}
	public List<Measure> getMeasures(){
		return parent == null ? measures : ((CubeColumnNode) parent).getMeasures();
	}
	
	public int getLevel(){
		if(parent == null){
			return 0;
		}
		return 1 + ((CubeColumnNode) parent).getLevel();
	}
	public List<CubeColumnNode> getExpandedChildren(){
		if(expanded){
			return maybeLoadChildren();
		}
		return Collections.emptyList();
	}
	private List<CubeColumnNode> maybeLoadChildren(){
		if(children == null){
			children = new ArrayList<CubeColumnNode>();
			populateChildren();
		}
		return children;
	}
}
