package org.nakeduml.runtime.domain;
import java.util.Collection;
public interface IUserInRole extends CompositionNode{
	String getRoleNameForSecurity();
	Collection<? extends CompositionNode> getGroupsForSecurity();
}
