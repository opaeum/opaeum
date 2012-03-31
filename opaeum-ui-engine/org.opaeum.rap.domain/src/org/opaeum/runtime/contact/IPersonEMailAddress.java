package org.opaeum.runtime.contact;

import org.opaeum.runtime.domain.CompositionNode;

public interface IPersonEMailAddress extends CompositionNode{

	void setEmailAddress(String address);

	String getEmailAddress();
}
