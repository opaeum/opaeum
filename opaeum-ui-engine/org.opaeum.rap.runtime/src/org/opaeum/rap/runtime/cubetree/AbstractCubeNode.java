package org.opaeum.rap.runtime.cubetree;

import java.util.Set;

import org.olap4j.CellSetAxis;
import org.olap4j.OlapException;
import org.olap4j.Position;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.NamedList;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;

public abstract class AbstractCubeNode{
	protected boolean expanded;
	public Member member;
	public AbstractCubeNode parent;
	public LevelHolder levelHolder;
	private CubeFilter filter;
	public AbstractCubeNode(LevelHolder level,Member member,CubeFilter filter){
		this.levelHolder = level;
		this.member = member;
		this.filter = filter;
	}
	public AbstractCubeNode(AbstractCubeNode parent,Member member){
		super();
		levelHolder = parent.levelHolder.getNext();
		this.member = member;
		this.parent = parent;
	}
	public CubeFilter getFilter(){
		return parent == null ? filter : parent.getFilter();
	}
	public void expand(){
		expanded = true;
	}
	public void collapse(){
		expanded = false;
	}
	protected abstract void addChild(Member childMember);
	protected void populateChildren(){
		try{
			if(levelHolder.getNext() == null){
				// Don't populate any children - end of tree
			}else if(levelHolder.areChildrenNewDimension()){
				for(Member childMember:levelHolder.getNext().getLevel().getMembers()){
					addChild(childMember);
				}
			}else{
				addChildren(member.getChildMembers());
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	protected void addChildren(NamedList<? extends Member> childMembers) throws OlapException{
		for(Member childMember:childMembers){
			if(getFilter().compliesToFilter(childMember)){
				if(levelHolder.getNext().getLevel().equals(childMember.getLevel())){
					addChild(childMember);
				}else{
					addChildren(childMember.getChildMembers());
				}
			}
		}
	}
	public void includeInQuery(Query q,QueryAxis axis,Set<Member> includedMembers){
		// Includes this and all its parents that are on a new dimension in the query
		Member currentMember = member;
		AbstractCubeNode node = this;
		boolean newDimension = true;
		while(node != null){
			if(newDimension && !includedMembers.contains(currentMember)){
				QueryDimension dim = q.getDimension(currentMember.getDimension().getName());
				if(!axis.getDimensions().contains(dim)){
					axis.addDimension(dim);
				}
				dim.include(currentMember);
				includedMembers.add(currentMember);
			}
			if(node.parent != null && !node.member.getDimension().equals(node.parent.member.getDimension())){
				// Not in same dimension so add the currentMember and its dimension
				currentMember = node.parent.member;
				newDimension = true;
			}else{
				newDimension = false;
			}
			node = node.parent;
		}
	}
	public Position getPosition(CellSetAxis a,Member measure){
		// Get the position for which every member is represented by this node or its ancestors
		outer:for(Position position:a.getPositions()){
			
			for(Member member:position.getMembers()){
				System.out.println(member.getName());
				if(!(representsMember(member)|| member instanceof Measure)){
					continue outer;
				}
			}
			
			if(measure == null || position.getMembers().contains(measure)){
				return position;
			}
		}
		throw new IllegalArgumentException("Member " + member.getName() + " does not feature on the give axis");
	}
	private boolean representsMember(Member member2){
		if(this.member.equals(member2)){
			return true;
		}else if(parent != null){
			return parent.representsMember(member2);
		}else{
			return false;
		}
	}
}
