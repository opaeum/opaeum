package org.opaeum.runtime.organization;

import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;


public interface IPersonNode extends CompositionNode{

	public abstract void setUsername(String username);

	public abstract void setSurname(String surname);

	public abstract void setFirstName(String firstName);

	public abstract void setAuthenticationToken(String authenticationToken);

	public abstract IPersonEMailAddress createEMailAddress(PersonEMailAddressType type);

	public abstract IPersonPhoneNumber createPhoneNumber(PersonPhoneNumberType type);
	public abstract IPersonPhoneNumber getPhoneNumber(PersonPhoneNumberType type);

	public abstract IPersonEMailAddress getEMailAddress(PersonEMailAddressType cell);
	
}
