package net.sf.nakeduml.util;
import java.util.Collection;
public interface AbstractUserRole extends CompositionNode{
	String getRoleNameForSecurity();
	Collection<? extends CompositionNode> getGroupsForSecurity();
}
