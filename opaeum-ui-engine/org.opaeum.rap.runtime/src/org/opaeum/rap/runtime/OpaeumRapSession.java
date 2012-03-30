package org.opaeum.rap.runtime;

import java.net.URL;
import java.util.Date;

import org.opaeum.rap.login.LoginView;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
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
	AccessTokenResponse accessTokenResponse;
	private ContactsService contactsService;
	public static final String CLIENT_SECRET = "yRf8aLQhkqvCtINCDoCklgTM";
	private long expiryDate;
	private ConversationalPersistence sessionPersistence;
	public OpaeumRapSession(IOpaeumApplication application,AccessTokenResponse accessToken){
		super();
		expiryDate=(accessToken.expiresIn*1000 - 60)+System.currentTimeMillis();
		this.application = application;
		this.accessTokenResponse = accessToken;
		sessionPersistence= application.getEnvironment().createConversationalPersistence();
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
			person.setAuthenticationToken(accessTokenResponse.accessToken);
			person.setRefreshToken(accessTokenResponse.refreshToken);
			person.setTokenExpiryDateTime(new Date(expiryDate));
			sessionPersistence.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public ContactsService getContactsService(){
		if(contactsService == null){
			//TODO figure out when to refresh the accessToken
			GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(accessTokenResponse.accessToken, TRANSPORT, JSON_FACTORY,
					LoginView.CLIENT_ID, CLIENT_SECRET, accessTokenResponse.refreshToken){
				@Override
				protected void onAccessToken(String accessToken){
					super.onAccessToken(accessToken);
				}
			};
//			try{
//				access.refreshToken();
//			}catch(IOException e){
//				e.printStackTrace();
//			}
			contactsService = new ContactsService("asf");
			contactsService.setHeader("Authorization", "Bearer " + accessTokenResponse.accessToken);
		}
		return this.contactsService;
	}
	public IPersonNode getPersonNode(){
		return person;
	}
}
