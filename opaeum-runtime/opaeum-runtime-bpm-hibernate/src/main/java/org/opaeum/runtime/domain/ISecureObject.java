package org.opaeum.runtime.domain;

import org.opeum.runtime.domain.CompositionNode;




public interface ISecureObject extends CompositionNode {
	boolean isGroupOwnershipValid(OpeumUser user);
	boolean canBeOwnedByUser(OpeumUser user);
	boolean isOwnedByUser(OpeumUser user);
	boolean isUserOwnershipValid(OpeumUser user);
}