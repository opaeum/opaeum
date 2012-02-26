package org.opaeum.runtime.domain;





public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(IOpaeumUser user);
	boolean canBeOwnedByUser(IOpaeumUser user);
	boolean isOwnedByUser(IOpaeumUser user);
	boolean isUserOwnershipValid(IOpaeumUser user);
}