package org.nakeduml.runtime.domain;



public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(NakedUmlUser user);
	boolean canBeOwnedByUser(NakedUmlUser user);
	boolean isOwnedByUser(NakedUmlUser user);
	boolean isUserOwnershipValid(NakedUmlUser user);
}