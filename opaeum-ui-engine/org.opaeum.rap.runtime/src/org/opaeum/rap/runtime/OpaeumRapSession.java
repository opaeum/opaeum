package org.opaeum.rap.runtime;

import java.net.URL;
import java.util.Date;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.contacts.ContactQuery;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Person;
import com.google.gdata.data.contacts.ContactFeed;

public class OpaeumRapSession{
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private IOpaeumApplication application;
	private IPersonNode person;
	private ContactsService contactsService;
	public static final String CLIENT_SECRET = "yRf8aLQhkqvCtINCDoCklgTM";
	private long expiryDate;
	private ConversationalPersistence sessionPersistence;
	private MondrianSession mondrianSession;
	private String accessToken;
	private String refreshToken;
	public OpaeumRapSession(IOpaeumApplication application2,String accessToken2,String refreshToken2, long expiryDate){
		this.application = application2;
		this.accessToken= accessToken2;
		this.refreshToken=refreshToken2;
		sessionPersistence= application.getEnvironment().createConversationalPersistence();
	}
	public OpaeumRapSession(IOpaeumApplication application2,IPersonNode person){
		this.application = application2;
		this.accessToken= person.getAuthenticationToken();
		this.refreshToken=person.getRefreshToken();
		sessionPersistence= application.getEnvironment().createConversationalPersistence();
		this.person=sessionPersistence.find(IntrospectionUtil.getOriginalClass(person), person.getId());
	}
	public ConversationalPersistence getPersistence(){
		return sessionPersistence;
	}
	public IOpaeumApplication getApplication(){
		return application;
	}
	public void associatePerson(){
		try{
			ContactQuery query = new ContactQuery(new URL("https://www.google.com/m8/feeds/contacts/default/full"));
			query.setMaxResults(1);
			ContactFeed resultFeed = getContactsService().getFeed(query, ContactFeed.class);
			Person author = resultFeed.getAuthors().iterator().next();
			person=application.findOrCreatePersonByEMailAddress(author.getEmail());
			person=sessionPersistence.getReference(IntrospectionUtil.getOriginalClass(person), person.getId());
			person.setAuthenticationToken(accessToken);
			person.setRefreshToken(refreshToken);
			person.setTokenExpiryDateTime(new Date(expiryDate));
			sessionPersistence.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public ContactsService getContactsService(){
		if(contactsService == null){
			contactsService = new ContactsService("asf");
			contactsService.setHeader("Authorization", "Bearer " + accessToken);
		}
		return this.contactsService;
	}
	public IPersonNode getPersonNode(){
		return person;
	}
	public MondrianSession getMondrianSession(){
		if(this.mondrianSession==null){
			mondrianSession=new MondrianSession(application.getCubeUrl());
		}
		return mondrianSession;
	}
}
