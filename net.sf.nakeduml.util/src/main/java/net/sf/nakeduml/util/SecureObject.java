package net.sf.nakeduml.util;


public interface SecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(AbstractUser user);
	boolean canBeOwnedByUser(AbstractUser user);
	boolean isOwnedByUser(AbstractUser user);
	boolean isUserOwnershipValid(AbstractUser user);
}