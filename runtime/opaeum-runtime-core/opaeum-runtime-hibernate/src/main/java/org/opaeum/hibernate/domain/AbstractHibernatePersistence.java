package org.opaeum.hibernate.domain;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IPersistentStringEnum;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.organization.IParticipantBase;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.Query;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernatePersistence implements AbstractPersistence{
	private Session session;
	private boolean filterDeletedObjects;
	protected Environment environment;
	public static int COUNT;
	public static int SESSION_COUNT;
	private IParticipantBase currentRole;
	private IPersonNode currentUser;
	public AbstractHibernatePersistence(Session session ,Environment environment){
		this(environment);
		this.setSession(session);
		setFilterDeletedObjects(true);
	}
	public AbstractHibernatePersistence(Environment e){
		if(e==null){
			System.out.println();
		}
		COUNT++;
		SESSION_COUNT++;
		this.environment=e;
	}
	@Override
	public IParticipantBase getCurrentRole(){
		if(environment.getCurrentRole()!=null && (currentRole==null || (environment.getCurrentRole().getValue()!=null && !environment.getCurrentRole().getValue().equals(currentRole)))){
			currentRole=(IParticipantBase) environment.getCurrentRole().retrieveValue(this);
		}
		return currentRole;
	}
	@Override
	public IPersonNode getCurrentUser(){
		if(currentUser==null){
			currentUser=(IPersonNode) environment.getCurrentUser().retrieveValue(this);
		}
		return currentUser;
	}
	@Override
	public boolean isOpen(){
		return getSession().isOpen();
	}
	public JavaMetaInfoMap getMetaInfoMap(){
		return environment.getMetaInfoMap();
	}
	@Override
	public String getApplicationId(){
		return environment.getApplicationIdentifier();
	}

	public void refresh(IPersistentObject...ps){
		for(IPersistentObject p:ps){
			getSession().refresh(p);
		}
	}
	public boolean isClosed(){
		return !session.isOpen();
	}
	@Override
	public <T extends IPersistentStringEnum>T find(Class<T> t,String id){
		return (T) getSession().get(t, id);
	}
	@Override
	public <T extends IPersistentStringEnum>T getReference(Class<T> t,String id){
		return (T) getSession().load(t, id);
	}
	@Override
	public <T>T getReference(Class<T> t,Long id){
		return (T) getSession().load(t, id);
	}
	@Override
	public <T>T find(Class<T> t,Long id){
		return (T) getSession().get(t, id);
	}
	@Override
	public void persist(Object object){
		getSession().persist(object);
	}
	@Override
	public Query createQuery(String q){
		org.hibernate.Query createQuery = getSession().createQuery(q);
		return new HibernateQuery(createQuery);
	}
	@Override
	public <T>List<T> readAll(Class<T> c){
		return getSession().createCriteria(c).list();
	}
	@Override
	public void remove(IPersistentObject o){
		getSession().delete(o);
	}
	protected Session getSession(){
		return session;
	}
	public boolean isFilterDeletedObjects(){
		return filterDeletedObjects;
	}
	public void setFilterDeletedObjects(boolean filterDeletedObjects){
		if(filterDeletedObjects){
			session.enableFilter("noDeletedObjects");
		}else{
			session.disableFilter("noDeletedObjects");
		}
		this.filterDeletedObjects = filterDeletedObjects;
	}
	protected void setSession(Session session){
		this.session = session;
	}
	public void cleanUp() {
		session=null;
		SESSION_COUNT--;
	}
	@Override
	public boolean contains(IPersistentObject p){
		return session.contains(p);
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		COUNT--;
	}
	
}
