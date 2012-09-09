package org.opaeum.runtime.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IPersonEMailAddress extends CompositionNode, IPersistentObject{

	void setEmailAddress(String address);

	String getEmailAddress();
}
