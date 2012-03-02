package org.opaeum.runtime.contact;

import org.opaeum.runtime.domain.CompositionNode;

public interface IPersonPhoneNumber extends CompositionNode{

	void setNumber(String phoneNumber);

	String getNumber();
}
