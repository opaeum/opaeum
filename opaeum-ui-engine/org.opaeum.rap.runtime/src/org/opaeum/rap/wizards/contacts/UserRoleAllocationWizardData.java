package org.opaeum.rap.wizards.contacts;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IBusinessActorBase;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessComponent;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.organization.IPersonNode;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.GivenName;
import com.google.gdata.data.extensions.PhoneNumber;

public class UserRoleAllocationWizardData{
	ContactsService service;
	IBusinessCollaborationBase businessCollaboration;
	private Map<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>> multiUserRoleComponentMap = new TreeMap<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>>(getClassNameComparator());
	private Map<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>> singleUserRoleComponentMap = new TreeMap<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>>(getClassNameComparator());
	private Collection<IBusinessActorBase> businessActors = new HashSet<IBusinessActorBase>();
	private Map<Class<? extends IBusinessComponent>,IBusinessComponent> businessComponentMap = new HashMap<Class<? extends IBusinessComponent>,IBusinessComponent>();
	private IBusinessNetwork businessNetwork;
	private Map<String,IPersonNode> people = new HashMap<String,IPersonNode>();
	private IOpaeumApplication application;
	private ArrayList<ContactEntry> entries;
	public UserRoleAllocationWizardData(ContactsService service,IOpaeumApplication application){
		super();
		this.service = service;
		this.setApplication(application);
		this.businessCollaboration = application.createRootBusinessCollaboration();
		this.businessNetwork=businessCollaboration.getBusinessNetwork();
	}
	@SuppressWarnings({"unchecked"})
	protected void populateBusinessRoleClasses(){
		for(Class<?> bc:getApplication().getEnvironment().getMetaInfoMap().getClassesByAnnotation(BusinessComponent.class)){
			if(bc.getAnnotation(BusinessComponent.class).isRoot()){
				PropertyDescriptor[] properties = IntrospectionUtil.getProperties(bc);
				for(PropertyDescriptor pd:properties){
					if(pd.getWriteMethod() != null && pd.getReadMethod().isAnnotationPresent(PropertyMetaInfo.class)){
						if(pd.getReadMethod().getAnnotation(PropertyMetaInfo.class).isComposite()){
							if(isMany(pd)){
								Class<?> type = (Class<?>) ((ParameterizedType) pd.getReadMethod().getGenericReturnType()).getActualTypeArguments()[0];
								if(IBusinessRoleBase.class.isAssignableFrom(type)){
									Class<? extends IBusinessRoleBase> brClass = (Class<? extends IBusinessRoleBase>) type;
									multiUserRoleComponentMap.put(brClass, (Class<? extends IBusinessComponent>) bc);
								}
							}else{
								if(IBusinessRoleBase.class.isAssignableFrom(pd.getReadMethod().getReturnType())){
									Class<? extends IBusinessRoleBase> brClass = (Class<? extends IBusinessRoleBase>) pd.getReadMethod().getReturnType();
									singleUserRoleComponentMap.put(brClass, (Class<? extends IBusinessComponent>) bc);
								}
							}
						}
					}
				}
			}
		}
	}
	protected void instantiateBusinessRole(IPersonNode person,Class<?> c) throws InstantiationException,IllegalAccessException{
		IBusinessRoleBase ba = (IBusinessRoleBase) c.newInstance();
		Class<? extends IBusinessComponent> bcClass = multiUserRoleComponentMap.get(c);
		if(bcClass==null){
			bcClass=singleUserRoleComponentMap.get(c);
		}
		IBusinessComponent owner = businessComponentMap.get(bcClass);
		if(owner == null){
			owner = bcClass.newInstance();
			owner.init(businessCollaboration);
			// TODO
			// owner.setRepresentedOrganization();
			businessComponentMap.put(bcClass, owner);
		}
		ba.init(owner);
		ba.addToOwningObject();
		ba.setRepresentedPerson(person);
	}
	protected IPersonNode createPerson(ContactEntry contact){
		List<Email> emailAddresses = contact.getEmailAddresses();
		String userName = calcUserName(contact);
		IPersonNode person = people.get(userName);
		if(person == null){
			person = businessNetwork.createPerson(userName);
			if(contact.getName() != null){
				GivenName givenName = contact.getName().getGivenName();
				if(givenName != null){
					person.setFirstName(givenName.getValue());
				}
				FamilyName familyName = contact.getName().getFamilyName();
				if(familyName != null){
					person.setSurname(familyName.getValue());
				}
			}
			for(Email email:emailAddresses){
				PersonEMailAddressType type = calcType(email);
				IPersonEMailAddress e = person.createEMailAddress(type);
				e.setEmailAddress(email.getAddress());
				e.addToOwningObject();
			}
			for(PhoneNumber phoneNumber:contact.getPhoneNumbers()){
				PersonPhoneNumberType type = calcType(phoneNumber);
				IPersonPhoneNumber pn = person.createPhoneNumber(type);
				pn.setPhoneNumber(phoneNumber.getPhoneNumber());
				pn.addToOwningObject();
			}
			people.put(userName, person);
		}
		return person;
	}
	public static Comparator<Class<?>> getClassNameComparator(){
		return new Comparator<Class<?>>(){
			public int compare(Class<?> o1,Class<?> o2){
				return o1.getSimpleName().compareTo(o2.getSimpleName());
			}
		};
	}
	protected List<ContactEntry> getContactEntries(){
		if(entries == null){
			ContactFeed resultFeed;
			this.entries = new ArrayList<ContactEntry>();
			try{
				if(service != null){
					resultFeed = service.getFeed(new URL("https://www.google.com/m8/feeds/contacts/default/full"), ContactFeed.class);
					while(resultFeed.getNextLink() != null){
						entries.addAll(resultFeed.getEntries());
						resultFeed = service.getFeed(new URL(resultFeed.getNextLink().getHref()), ContactFeed.class);
					}
					Iterator<ContactEntry> it = entries.iterator();
					while(it.hasNext()){
						ContactEntry contactEntry = (ContactEntry) it.next();
						if(calcUserName(contactEntry) == null){
							it.remove();
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return entries;
	}
	String calcUserName(ContactEntry contactEntry){
		List<Email> emailAddresses = contactEntry.getEmailAddresses();
		if(emailAddresses.size() == 0){
			if(contactEntry.getName().getFullName() != null){
				String value = contactEntry.getName().getFullName().getValue();
				return value.replace(' ', '.');
			}else{
				return null;
			}
		}else{
			return emailAddresses.get(0).getAddress();
		}
	}
	private PersonPhoneNumberType calcType(PhoneNumber phoneNumber){
		return PersonPhoneNumberType.CELL;
	}
	private PersonEMailAddressType calcType(Email email){
		return PersonEMailAddressType.HOME;
	}
	protected boolean isMany(PropertyDescriptor pd){
		return pd.getReadMethod() != null && Collection.class.isAssignableFrom(pd.getReadMethod().getReturnType());
	}
	public Map<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>> getMultiUserRoleComponentMap(){
		return multiUserRoleComponentMap;
	}
	public Map<Class<? extends IBusinessRoleBase>,Class<? extends IBusinessComponent>> getSingleUserRoleComponentMap(){
		return singleUserRoleComponentMap;
	}
	public Collection<IBusinessActorBase> getBusinessActors(){
		return businessActors;
	}
	public Map<Class<? extends IBusinessComponent>,IBusinessComponent> getBusinessComponentMap(){
		return businessComponentMap;
	}
	public void flush(){
		businessCollaboration.addToOwningObject();
		for(IPersonNode p:people.values()){
			p.addToOwningObject();
		}
		for(IBusinessComponent b:this.businessComponentMap.values()){
			b.addToOwningObject();
		}
		for(IBusinessActorBase a:this.businessActors){
			a.addToOwningObject();
		}
		getApplication().getEnvironment().createConversationalPersistence().flush();
	}
	public IOpaeumApplication getApplication() {
		return application;
	}
	public void setApplication(IOpaeumApplication application) {
		this.application = application;
	}

}