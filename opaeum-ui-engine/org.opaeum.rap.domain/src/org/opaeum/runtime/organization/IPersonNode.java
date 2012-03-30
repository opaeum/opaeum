package org.opaeum.runtime.organization;

import java.util.Collection;
import java.util.Date;

import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IPersonNode extends CompositionNode,IPersistentObject{
	String getAuthenticationToken();
	void setUsername(String username);
	void setSurname(String surname);
	void setFirstName(String firstName);
	void setAuthenticationToken(String authenticationToken);
	IPersonEMailAddress createEMailAddress(PersonEMailAddressType type);
	IPersonPhoneNumber createPhoneNumber(PersonPhoneNumberType type);
	IPersonPhoneNumber getPhoneNumber(PersonPhoneNumberType type);
	IPersonEMailAddress getEMailAddress(PersonEMailAddressType cell);
	Collection<? extends IBusinessRoleBase> getBusinessRole();
	void setRefreshToken(String refreshToken);
	String getRefreshToken();
	Date getTokenExpiryDateTime();
	void setTokenExpiryDateTime(Date date);
}
