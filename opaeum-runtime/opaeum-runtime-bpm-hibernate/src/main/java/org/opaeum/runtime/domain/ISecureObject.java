package org.opaeum.runtime.domain;

import org.opaeum.runtime.domain.CompositionNode;




public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(IOpaeumUser user);
	boolean canBeOwnedByUser(IOpaeumUser user);
	boolean isOwnedByUser(IOpaeumUser user);
	boolean isUserOwnershipValid(IOpaeumUser user);
}