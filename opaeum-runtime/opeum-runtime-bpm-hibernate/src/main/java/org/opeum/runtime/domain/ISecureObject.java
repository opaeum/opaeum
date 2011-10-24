package org.opaeum.runtime.domain;

import org.opaeum.runtime.domain.CompositionNode;




public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(OpaeumUser user);
	boolean canBeOwnedByUser(OpaeumUser user);
	boolean isOwnedByUser(OpaeumUser user);
	boolean isUserOwnershipValid(OpaeumUser user);
}