package org.opaeum.rap.runtime.cubetree;

import java.util.ArrayList;
import java.util.List;

import org.olap4j.metadata.Member;
import org.olap4j.metadata.NamedList;

public class CubeLabelNode{
	Member member;
	private List<CubeLabelNode> children = new ArrayList<CubeLabelNode>();
	boolean expanded = false;
	public CubeLabelNode(Member member2){
		this.member = member2;
	}
	public void expand(){
		try{
			expanded = true;
			NamedList<? extends Member> childMembers = member.getChildMembers();
			for(Member member:childMembers){
				children.add(new CubeLabelNode(member));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
