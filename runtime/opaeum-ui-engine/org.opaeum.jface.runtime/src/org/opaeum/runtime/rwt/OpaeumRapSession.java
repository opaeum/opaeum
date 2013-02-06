package org.opaeum.runtime.rwt;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;

public class OpaeumRapSession{
	private IOpaeumApplication application;
	protected IPersonNode person;
	public static final String CLIENT_SECRET = "yRf8aLQhkqvCtINCDoCklgTM";
	public static final String ATTRIBUTE_NAME = OpaeumRapSession.class.getName() + ".sessionAttribute";
	protected ConversationalPersistence sessionPersistence;
	private MondrianSession mondrianSession;
	public OpaeumRapSession(IOpaeumApplication application2){
		this.application = application2;
		sessionPersistence = application.getEnvironment().createConversationalPersistence();
	}
	public OpaeumRapSession(IOpaeumApplication application2,IPersonNode person){
		this.application = application2;
		sessionPersistence = application.getEnvironment().createConversationalPersistence();
		this.person = sessionPersistence.find(IntrospectionUtil.getOriginalClass(person), person.getId());
	}
	public ConversationalPersistence getPersistence(){
		return sessionPersistence;
	}
	public IOpaeumApplication getApplication(){
		return application;
	}
	public IPersonNode getPersonNode(){
		return person;
	}
	public MondrianSession getMondrianSession(){
		if(this.mondrianSession == null){
			mondrianSession = new MondrianSession(application.getCubeUrl(), getApplication().getEnvironment());
		}
		return mondrianSession;
	}
}
