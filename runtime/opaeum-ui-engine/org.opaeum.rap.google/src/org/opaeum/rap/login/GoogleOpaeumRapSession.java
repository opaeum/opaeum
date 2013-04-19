package org.opaeum.rap.login;

import java.net.URL;
import java.util.Date;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.rwt.IOpaeumApplication;
import org.opaeum.runtime.rwt.OpaeumRapSession;

import com.google.gdata.client.contacts.ContactQuery;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Person;
import com.google.gdata.data.contacts.ContactFeed;

public class GoogleOpaeumRapSession extends OpaeumRapSession {
	private ContactsService contactsService;
	public static final String CLIENT_SECRET = "yRf8aLQhkqvCtINCDoCklgTM";
	private long expiryDate;
	private String accessToken;
	private String refreshToken;

	public GoogleOpaeumRapSession(IOpaeumApplication application2,
			String accessToken2, String refreshToken2, long expiryDate) {
		super(application2);
		this.accessToken = accessToken2;
		this.refreshToken = refreshToken2;
	}

	public GoogleOpaeumRapSession(IOpaeumApplication application2, IPersonNode person) {
		super(application2,person);
		this.accessToken = person.getAuthenticationToken();
		this.refreshToken = person.getRefreshToken();
	}

	public void associatePerson() {
		try {
			ContactQuery query = new ContactQuery(new URL(
					"https://www.google.com/m8/feeds/contacts/default/full"));
			query.setMaxResults(1);
			ContactFeed resultFeed = getContactsService().getFeed(query,
					ContactFeed.class);
			Person author = resultFeed.getAuthors().iterator().next();
			person = getApplication().findOrCreatePersonByEMailAddress(author
					.getEmail());
			person = sessionPersistence.getReference(
					IntrospectionUtil.getOriginalClass(person), person.getId());
			person.setAuthenticationToken(accessToken);
			person.setRefreshToken(refreshToken);
			person.setTokenExpiryDateTime(new Date(expiryDate));
			sessionPersistence.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ContactsService getContactsService() {
		if (contactsService == null) {
			contactsService = new ContactsService("asf");
			contactsService.setHeader("Authorization", "Bearer " + accessToken);
		}
		return this.contactsService;
	}
}