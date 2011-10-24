package org.opaeum.runtime.domain;
import java.util.Collection;

import org.opaeum.runtime.domain.CompositionNode;
public interface IUserInRole extends CompositionNode{
	String getRoleNameForSecurity();
	Collection<? extends CompositionNode> getGroupsForSecurity();
}
