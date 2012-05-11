package org.opaeum.rap.runtime.cubetree;

import java.util.Set;

import org.olap4j.CellSetAxis;
import org.olap4j.Position;
import org.olap4j.metadata.Member;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;

public abstract class AbstractCubeNode{
	public TreeCube cube;
	public Member member;
	public AbstractCubeNode parent;
	public AbstractCubeNode(TreeCube cube,AbstractCubeNode parent,Member member){
		super();
		this.cube = cube;
		this.member = member;
		this.parent = parent;
	}
	@Override
	public int hashCode(){
		return member.hashCode();
	}
	public void include(Query q,QueryAxis axis,Set<Member> includedMembers){
		Member currentMember = member;
		AbstractCubeNode node = this;
		while(node != null){
			if(!includedMembers.contains(currentMember)){
				QueryDimension dim = q.getDimension(member.getDimension().getName());
				if(!axis.getDimensions().contains(dim)){
					axis.addDimension(dim);
					
				}
				dim.include(currentMember);
				includedMembers.add(currentMember);
			}
			if(node.parent != null && !node.member.getDimension().equals(node.parent.member.getDimension())){
				currentMember = node.parent.member;
			}
			node = node.parent;
		}
	}
	public Position getPosition(CellSetAxis a){
		// Get the position for which every member is represented by this node or its ancestors
		for(Position position:a.getPositions()){
			for(Member member:position.getMembers()){
				if(!representsMember(member)){
					continue;
				}
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
