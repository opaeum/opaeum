package org.opaeum.runtime.domain;
import java.util.Collection;
public interface IBusinessRole extends CompositionNode{
	String getRoleNameForSecurity();
	Collection<? extends CompositionNode> getGroupsForSecurity();
	IOpaeumUser getOpaeumUser();
}
