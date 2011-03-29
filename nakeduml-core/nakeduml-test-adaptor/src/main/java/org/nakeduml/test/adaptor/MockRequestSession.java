package org.nakeduml.test.adaptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LobHelper;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TypeHelper;
import org.hibernate.UnknownProfileException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;
import org.nakeduml.runtime.domain.AbstractEntity;

@RequestScoped
public class MockRequestSession implements Session{
	
	private final class SexyList<T> extends ArrayList<T>{
		private SexyList(Collection c){
			super(c);
		}
		@Override
		public boolean add(T o){
			if(o instanceof AbstractEntity && ((AbstractEntity) o).getId() ==null){
				persist(o);
			}
			return super.add(o);
		}
	}
	private final class SexySet<T> extends HashSet<T>{
		private SexySet(Collection c){
			super(c);
		}
		@Override
		public boolean add(T o){
			if(o instanceof AbstractEntity && ((AbstractEntity) o).getId()==null){
				persist(o);
			}
			return super.add(o);
		}
	}
	static long currentId = 0;
	static Map<String,Map<Long,AbstractEntity>> extents = new HashMap<String,Map<Long,AbstractEntity>>();
	static Set<AbstractEntity> dirtyEntities = new HashSet<AbstractEntity>();
	static Collection<MockQuery> mockQueries = new ArrayList<MockQuery>();
	public static void addMockedQuery(MockQuery w){
		mockQueries.add(w);
	}
	public static void reset(){
		dirtyEntities.clear();
		extents.clear();
	}
	public Collection<AbstractEntity> getExtent(String entityName){
		Map<Long,AbstractEntity> map = extents.get(entityName);
		if(map == null){
			return Collections.emptyList();
		}else{
			return map.values();
		}
	}
	@Override
	public void persist(Object object) throws HibernateException{
		dirtyEntities.add((AbstractEntity) object);
		putEntityAndDescendants((AbstractEntity) object);
	}
	private void putEntityAndDescendants(AbstractEntity object){
		attachEntity(object);
		Class<?> class1 = object.getClass();
		while(AbstractEntity.class.isAssignableFrom(class1)){
			persistDeclaredFields(object, (Class<? extends AbstractEntity>) class1);
			class1 = class1.getSuperclass();
		}
	}
	private void persistDeclaredFields(AbstractEntity object,Class<? extends AbstractEntity> class1){
		Field[] fields = class1.getDeclaredFields();
		for(Field field:fields){
			if(isPersistCascaded(field)){
				persistField(object, field);
			}
		}
	}
	private boolean isPersistCascaded(Field field){
		boolean shouldPersistField = false;
		if(field.isAnnotationPresent(Cascade.class)){
			CascadeType[] values = field.getAnnotation(Cascade.class).value();
			if(shouldPersist(values)){
				shouldPersistField = true;
			}
		}else if(field.isAnnotationPresent(OneToMany.class)){
			if(shouldPersist(field.getAnnotation(OneToMany.class).cascade())){
				shouldPersistField = true;
			}
		}else if(field.isAnnotationPresent(ManyToMany.class)){
			if(shouldPersist(field.getAnnotation(ManyToMany.class).cascade())){
				shouldPersistField = true;
			}
		}else if(field.isAnnotationPresent(OneToOne.class)){
			if(shouldPersist(field.getAnnotation(OneToOne.class).cascade())){
				shouldPersistField = true;
			}
		}
		return shouldPersistField;
	}
	private void attachEntity(AbstractEntity e){
		if(e.getId() == null){
			e.setId(currentId++);
		}
		Map<Long,AbstractEntity> map = extents.get(getEntityName(e));
		if(map == null){
			map = new HashMap<Long,AbstractEntity>();
			extents.put(getEntityName(e), map);
		}
		map.put(e.getId(), e);
	}
	private boolean shouldPersist(javax.persistence.CascadeType[] cascade){
		for(javax.persistence.CascadeType cascadeType:cascade){
			if(cascadeType == javax.persistence.CascadeType.ALL || cascadeType == javax.persistence.CascadeType.PERSIST){
				return true;
			}
		}
		return false;
	}
	private boolean shouldPersist(CascadeType[] values){
		boolean persist = false;
		for(CascadeType cascadeType:values){
			if(cascadeType == CascadeType.ALL || cascadeType == CascadeType.DELETE_ORPHAN){
				persist = true;
				break;
			}
		}
		return persist;
	}
	private Object persistField(Object object,Field field){
		try{
			field.setAccessible(true);
			Object child = field.get(object);
			if(child instanceof AbstractEntity){
				putEntityAndDescendants((AbstractEntity) child);
			}
			if(child instanceof List){
				field.set(object, new SexyList((Collection) child));
			}else if(child instanceof Set){
				field.set(object, new SexySet((Collection) child));
			}
			return child;
		}catch(IllegalAccessException e1){
			throw new RuntimeException(e1);
		}
	}
	private String getEntityName(AbstractEntity e){
		Class<? extends AbstractEntity> class1 = e.getClass();
		return getEntityName(class1);
	}
	public String getEntityName(Class<?> class1){
		return class1.getAnnotation(Entity.class).name();
	}
	@Override
	public Query createQuery(String queryString) throws HibernateException{
		for(MockQuery mockQuery:mockQueries){
			if(mockQuery.useFor(queryString)){
				mockQuery.setSession(this);
				mockQuery.setQueryString(queryString);
				return mockQuery;
			}
		}
		throw new IllegalStateException("No mockQuery setup for query '" + queryString + "'");
	}
	@Override
	public EntityMode getEntityMode(){
		return EntityMode.POJO;
	}
	@Override
	public Session getSession(EntityMode entityMode){
		return this;
	}
	@Override
	public void flush() throws HibernateException{
		long start = System.currentTimeMillis();
		for(AbstractEntity abstractEntity:new HashSet<AbstractEntity>( dirtyEntities)){
			Class<?> class1 = abstractEntity.getClass();
			while(AbstractEntity.class.isAssignableFrom(class1)){
				updateOneToOnes(abstractEntity, (Class<? extends AbstractEntity>) class1);
				class1 = class1.getSuperclass();
			}
		}
		dirtyEntities.clear();
		System.out.println("MockRequestSession.flush() took " + (System.currentTimeMillis() - start) + "ms");
	}
	private void updateOneToOnes(AbstractEntity abstractEntity,Class<? extends AbstractEntity> class1){
		try{
			for(Field field:class1.getDeclaredFields()){
				if(AbstractEntity.class.isAssignableFrom(field.getType())){
					if(isPersistCascaded(field)){
						field.setAccessible(true);
						AbstractEntity child = (AbstractEntity) field.get(abstractEntity);
						if(child != null && child.getId() == null){
							persist(child);
						}
					}
				}
			}
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public void setFlushMode(FlushMode flushMode){
	}
	@Override
	public FlushMode getFlushMode(){
		return FlushMode.AUTO;
	}
	@Override
	public void setCacheMode(CacheMode cacheMode){
	}
	@Override
	public CacheMode getCacheMode(){
		return CacheMode.NORMAL;
	}
	@Override
	public SessionFactory getSessionFactory(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Connection connection() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Connection close() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void cancelQuery() throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public boolean isOpen(){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isConnected(){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isDirty() throws HibernateException{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Serializable getIdentifier(Object object) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean contains(Object object){
		return false;
	}
	@Override
	public void evict(Object object) throws HibernateException{
	}
	@Override
	public Object load(Class theClass,Serializable id,LockMode lockMode) throws HibernateException{
		return load(theClass, id);
	}
	@Override
	public Object load(String entityName,Serializable id,LockMode lockMode) throws HibernateException{
		return load(entityName, id);
	}
	@Override
	public Object load(Class theClass,Serializable id) throws HibernateException{
		String entityName = getEntityName(theClass);
		return load(entityName, id);
	}
	@Override
	public Object load(String entityName,Serializable id) throws HibernateException{
		Object object = get(entityName, id);
		if(object == null){
			throw new HibernateException(entityName + ": " + id + " not found");
		}
		return object;
	}
	@Override
	public void load(Object object,Serializable id) throws HibernateException{
	}
	@Override
	public void replicate(Object object,ReplicationMode replicationMode) throws HibernateException{
	}
	@Override
	public void replicate(String entityName,Object object,ReplicationMode replicationMode) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public Serializable save(Object object) throws HibernateException{
		persist(object);
		return ((AbstractEntity) object).getId();
	}
	@Override
	public Serializable save(String entityName,Object object) throws HibernateException{
		return save(object);
	}
	@Override
	public void saveOrUpdate(Object object) throws HibernateException{
		save(object);
	}
	@Override
	public void saveOrUpdate(String entityName,Object object) throws HibernateException{
		save(object);
	}
	@Override
	public void update(Object object) throws HibernateException{
		save(object);
	}
	@Override
	public void update(String entityName,Object object) throws HibernateException{
		save(object);
	}
	@Override
	public Object merge(Object object) throws HibernateException{
		save(object);
		return object;
	}
	@Override
	public Object merge(String entityName,Object object) throws HibernateException{
		return merge(object);
	}
	@Override
	public void persist(String entityName,Object object) throws HibernateException{
		persist(object);
	}
	@Override
	public void delete(Object object) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(String entityName,Object object) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public void lock(Object object,LockMode lockMode) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public void lock(String entityName,Object object,LockMode lockMode) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public void refresh(Object object) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public void refresh(Object object,LockMode lockMode) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public LockMode getCurrentLockMode(Object object) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transaction beginTransaction() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transaction getTransaction(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Criteria createCriteria(Class persistentClass){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Criteria createCriteria(Class persistentClass,String alias){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Criteria createCriteria(String entityName){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Criteria createCriteria(String entityName,String alias){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SQLQuery createSQLQuery(String queryString) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query createFilter(Object collection,String queryString) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query getNamedQuery(String queryName) throws HibernateException{
		return createQuery(queryName);
	}
	@Override
	public void clear(){
		dirtyEntities.clear();
	}
	@Override
	public Object get(Class clazz,Serializable id) throws HibernateException{
		return get(getEntityName(clazz), id);
	}
	@Override
	public Object get(Class clazz,Serializable id,LockMode lockMode) throws HibernateException{
		return get(clazz, id);
	}
	@Override
	public Object get(String entityName,Serializable id) throws HibernateException{
		Map<Long,AbstractEntity> map = extents.get(entityName);
		if(map != null){
			AbstractEntity abstractEntity = map.get(id);
			if(abstractEntity != null){
				dirtyEntities.add(abstractEntity);
			}
			return abstractEntity;
		}else{
			return null;
		}
	}
	@Override
	public Object get(String entityName,Serializable id,LockMode lockMode) throws HibernateException{
		return get(getEntityName(entityName), id);
	}
	@Override
	public String getEntityName(Object object) throws HibernateException{
		return getEntityName((AbstractEntity) object);
	}
	@Override
	public Filter enableFilter(String filterName){
		return null;
	}
	@Override
	public Filter getEnabledFilter(String filterName){
		return null;
	}
	@Override
	public void disableFilter(String filterName){
	}
	@Override
	public SessionStatistics getStatistics(){
		return null;
	}
	@Override
	public void setReadOnly(Object entity,boolean readOnly){
	}
	@Override
	public Connection disconnect() throws HibernateException{
		return null;
	}
	@Override
	public void reconnect() throws HibernateException{
	}
	@Override
	public void reconnect(Connection connection) throws HibernateException{
	}
	@Override
	public boolean isDefaultReadOnly(){
		return false;
	}
	@Override
	public void setDefaultReadOnly(boolean readOnly){
	}
	@Override
	public Object load(Class theClass,Serializable id,LockOptions lockOptions) throws HibernateException{
		return load(theClass, id);
	}
	@Override
	public Object load(String entityName,Serializable id,LockOptions lockOptions) throws HibernateException{
		return load(entityName, id);
	}
	@Override
	public LockRequest buildLockRequest(LockOptions lockOptions){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void refresh(Object object,LockOptions lockOptions) throws HibernateException{
		// TODO Auto-generated method stub
	}
	@Override
	public Object get(Class clazz,Serializable id,LockOptions lockOptions) throws HibernateException{
		return get(clazz, id);
	}
	@Override
	public Object get(String entityName,Serializable id,LockOptions lockOptions) throws HibernateException{
		return get(entityName, id);
	}
	@Override
	public boolean isReadOnly(Object entityOrProxy){
		return false;
	}
	@Override
	public void doWork(Work work) throws HibernateException{
	}
	@Override
	public boolean isFetchProfileEnabled(String name) throws UnknownProfileException{
		return false;
	}
	@Override
	public void enableFetchProfile(String name) throws UnknownProfileException{
	}
	@Override
	public void disableFetchProfile(String name) throws UnknownProfileException{
	}
	@Override
	public TypeHelper getTypeHelper(){
		return null;
	}
	@Override
	public LobHelper getLobHelper(){
		return null;
	}
	public Collection<AbstractEntity> getExtent(Class<?> class1){
		return getExtent(getEntityName(class1));
	}
}
