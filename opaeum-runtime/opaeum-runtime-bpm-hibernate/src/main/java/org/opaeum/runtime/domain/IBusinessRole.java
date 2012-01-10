package org.opaeum.runtime.domain;
import java.util.Collection;

import org.opaeum.runtime.domain.CompositionNode;
public interface IBusinessRole extends CompositionNode{
	String getRoleNameForSecurity();
	Collection<? extends CompositionNode> getGroupsForSecurity();
	IOpaeumUser getOpaeumUser();
}
