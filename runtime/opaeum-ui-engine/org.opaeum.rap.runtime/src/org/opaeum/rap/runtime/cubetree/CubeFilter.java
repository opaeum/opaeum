package org.opaeum.rap.runtime.cubetree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.olap4j.metadata.Member;

public class CubeFilter{
	private Collection<Member> membersToFilterBy;
	public CubeFilter(Member selectedMember){
		super();
		if(selectedMember == null){
			membersToFilterBy = Collections.emptyList();
		}else{
			this.membersToFilterBy = Arrays.asList(selectedMember);
		}
	}
	public CubeFilter(Collection<Member> membersToFilterBy){
		super();
		this.membersToFilterBy = membersToFilterBy;
	}
	public boolean compliesToFilter(Member member){
		for(Member memberToFilterBy:membersToFilterBy){
			if(memberToFilterBy.getDimension().equals(member.getDimension())){
				if(memberToFilterBy.getDepth() == member.getDepth()){
					return memberToFilterBy.equals(member);
				}else if(memberToFilterBy.getDepth() > member.getDepth()){
					return isAncestorOf(member, memberToFilterBy);
				}else{
					return isAncestorOf(memberToFilterBy, member);
				}
			}
		}
		return true;// Dimension not filtered
	}
	private boolean isAncestorOf(Member ancestor,Member descendant){
		if(descendant.getParentMember() == null){
			return false;
		}else if(descendant.getParentMember().equals(ancestor)){
			return true;
		}else{
			return isAncestorOf(ancestor, descendant.getParentMember());
		}
	}
}
