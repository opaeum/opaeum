import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.opaeum.annotation.BusinessActor;
import org.opaeum.rap.runtime.SelectionForContact;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opaeum.runtime.organization.IBusinessActorBase;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;

import structuredbusiness.Structuredbusiness;

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.FullName;
import com.google.gdata.data.extensions.GivenName;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;

public class JpaTest extends TestCase{
	public void testIt() throws Exception{
		IBusinessNetwork bn = new BusinessNetwork();
		ConversationalPersistence p = StandaloneJpaEnvironment.getInstance().getPersistence();
		IBusinessCollaborationBase sb = new Structuredbusiness();
		bn.addToBusinessCollaboration(sb);
		Set<ContactEntry> singleton = getContactEntries();
		Collection<SelectionForContact> result = builSelectionsForContacts(singleton);
		for(SelectionForContact s:result){
			List<Email> emailAddresses = s.getContact().getEmailAddresses();
			IPersonNode person = bn.createPerson(getMainEMailAddress(emailAddresses));
			person.addToOwningObject();
			person.setFirstName(s.getContact().getName().getGivenName().getValue());
			person.setSurname(s.getContact().getName().getFamilyName().getValue());
			for(Email email:emailAddresses){
				PersonEMailAddressType type  = calcType(email);
				IPersonEMailAddress e  =person.createEMailAddress(type);
				e.setAddress(email.getAddress());
				e.addToOwningObject();
			}
			for(PhoneNumber phoneNumber:s.getContact().getPhoneNumbers()){
				PersonPhoneNumberType type = calcType(phoneNumber);
				IPersonPhoneNumber pn = person.createPhoneNumber(type);
				pn.setNumber(phoneNumber.getPhoneNumber());
				pn.addToOwningObject();
			}
			s.getSelection().set(0, Boolean.TRUE);
			for(Class<?> c:s.getSelectedClasses()){
				IBusinessActorBase ba = (IBusinessActorBase) c.newInstance();
				ba.init(sb);
				ba.addToOwningObject();
				ba.setRepresentedPerson(person);
			}
			p.persist(bn);
			p.flush();
			Assert.assertEquals("ampieb@gmail.com", person.getEMailAddress(PersonEMailAddressType.HOME).getAddress());
			Assert.assertEquals("0722413191", person.getPhoneNumber(PersonPhoneNumberType.CELL).getNumber());
		}
	}
	public String getMainEMailAddress(List<Email> emailAddresses){
		return emailAddresses.get(0).getAddress();
	}
	private PersonPhoneNumberType calcType(PhoneNumber phoneNumber){
		return PersonPhoneNumberType.CELL;
	}
	private PersonEMailAddressType calcType(Email email){
		return PersonEMailAddressType.HOME;
	}
	public Set<ContactEntry> getContactEntries(){
		ContactEntry o = new ContactEntry();
		Name name = new Name();
		name.setFullName(new FullName("John Smith", ""));
		name.setGivenName(new GivenName("John",""));
		name.setFamilyName(new FamilyName("Smith", ""));
		o.setName(name);
		Email email = new Email();
		email.setAddress("ampieb@gmail.com");
		o.addEmailAddress(email);
		PhoneNumber pn = new PhoneNumber();
		pn.setPhoneNumber("0722413191");
		o.addPhoneNumber(pn);
		Set<ContactEntry> singleton = Collections.singleton(o);
		return singleton;
	}
	public Collection<SelectionForContact> builSelectionsForContacts(Collection<ContactEntry> contactEntries){
		Collection<SelectionForContact> result = new ArrayList<SelectionForContact>();
		Collection<Class<?>> businessActors = StandaloneJpaEnvironment.getInstance().getMetaInfoMap().getClassesByAnnotation(BusinessActor.class);
		businessActors = new ArrayList<Class<?>>(businessActors);
		Collections.sort((List<Class<?>>) businessActors, new Comparator<Class<?>>(){
			@Override
			public int compare(Class<?> o1,Class<?> o2){
				return o1.getSimpleName().compareTo(o2.getName());
			}
		});
		for(ContactEntry contactEntry:contactEntries){
			result.add(new SelectionForContact(contactEntry, (List<Class<?>>) businessActors));
		}
		return result;
	}
}
