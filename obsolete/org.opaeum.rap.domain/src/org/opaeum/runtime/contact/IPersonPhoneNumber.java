package org.opaeum.runtime.contact;

import org.opaeum.runtime.domain.CompositionNode;

public interface IPersonPhoneNumber extends CompositionNode{
	void setPhoneNumber(String phoneNumber);
	String getPhoneNumber();
}
