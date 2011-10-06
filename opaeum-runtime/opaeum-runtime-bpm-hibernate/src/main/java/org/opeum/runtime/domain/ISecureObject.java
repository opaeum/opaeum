package org.opeum.runtime.domain;




public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(OpeumUser user);
	boolean canBeOwnedByUser(OpeumUser user);
	boolean isOwnedByUser(OpeumUser user);
	boolean isUserOwnershipValid(OpeumUser user);
}