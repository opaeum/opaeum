package org.nakeduml.test.adaptor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

public abstract class MockQuery implements Query{
	private List<Object> parametersByIndex=new ArrayList<Object>();
	private Map<String,Object> parameterMap=new HashMap<String,Object>(); 
	protected MockRequestSession session;
	protected String queryString;
	public abstract boolean useFor(String query);
	@Override
	public abstract List<Object> list() throws HibernateException;
	public void setQueryString(String s){
		this.queryString=s;
	}
	public void setSession(MockRequestSession session){
		this.session=session;
	}
	@Override
	public String getQueryString(){
		return queryString;
	}

	@Override
	public Type[] getReturnTypes() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getReturnAliases() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamedParameters() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate() throws HibernateException{
		return list().iterator();
	}

	@Override
	public ScrollableResults scroll() throws HibernateException{
		return null;
	}

	@Override
	public ScrollableResults scroll(ScrollMode scrollMode) throws HibernateException{
		return null;
	}


	@Override
	public Object uniqueResult() throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate() throws HibernateException{
		return 0;
	}

	@Override
	public Query setMaxResults(int maxResults){
		return null;
	}

	@Override
	public Query setFirstResult(int firstResult){
		return null;
	}

	@Override
	public boolean isReadOnly(){
		return false;
	}

	@Override
	public Query setReadOnly(boolean readOnly){
		return this;
	}

	@Override
	public Query setCacheable(boolean cacheable){
		return this;
	}

	@Override
	public Query setCacheRegion(String cacheRegion){
		return this;
	}

	@Override
	public Query setTimeout(int timeout){
		return this;
	}

	@Override
	public Query setFetchSize(int fetchSize){
		return null;
	}

	@Override
	public Query setLockOptions(LockOptions lockOptions){
		return null;
	}

	@Override
	public Query setLockMode(String alias,LockMode lockMode){
		return null;
	}

	@Override
	public Query setComment(String comment){
		return null;
	}

	@Override
	public Query setFlushMode(FlushMode flushMode){
		return null;
	}

	@Override
	public Query setCacheMode(CacheMode cacheMode){
		return null;
	}

	@Override
	public Query setParameter(int position,Object val,Type type){
		while(parametersByIndex.size()<=position){
			parametersByIndex.add(null);
		}
		parametersByIndex.set(position, val);
		return this;
	}

	@Override
	public Query setParameter(String name,Object val,Type type){
		parameterMap.put(name,val);
		return this;
	}

	@Override
	public Query setParameter(int position,Object val) throws HibernateException{
		return setParameter(position, val,null);
	}

	@Override
	public Query setParameter(String name,Object val) throws HibernateException{
		return setParameter(name, val,null);
	}

	@Override
	public Query setParameters(Object[] values,Type[] types) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name,Collection vals,Type type) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name,Collection vals) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name,Object[] vals,Type type) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name,Object[] vals) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setProperties(Object bean) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setProperties(Map bean) throws HibernateException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setString(int position,String val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setCharacter(int position,char val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setBoolean(int position,boolean val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setByte(int position,byte val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setShort(int position,short val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setInteger(int position,int val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setLong(int position,long val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setFloat(int position,float val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setDouble(int position,double val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setBinary(int position,byte[] val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setText(int position,String val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setSerializable(int position,Serializable val){
		return setParameter(position, val,null);
	}

	@Override
	public Query setLocale(int position,Locale locale){
		return setParameter(position, locale,null);
	}

	@Override
	public Query setBigDecimal(int position,BigDecimal number){
		return setParameter(position, number,null);
	}

	@Override
	public Query setBigInteger(int position,BigInteger number){
		return setParameter(position, number,null);
	}

	@Override
	public Query setDate(int position,Date date){
		return setParameter(position, date,null);
	}

	@Override
	public Query setTime(int position,Date date){
		return setParameter(position, date,null);
	}

	@Override
	public Query setTimestamp(int position,Date date){
		return setParameter(position, date,null);
	}

	@Override
	public Query setCalendar(int position,Calendar calendar){
		return setParameter(position, calendar,null);
	}

	@Override
	public Query setCalendarDate(int position,Calendar calendar){
		return setParameter(position, calendar,null);
	}

	@Override
	public Query setString(String name,String val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setCharacter(String name,char val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setBoolean(String name,boolean val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setByte(String name,byte val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setShort(String name,short val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setInteger(String name,int val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setLong(String name,long val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setFloat(String name,float val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setDouble(String name,double val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setBinary(String name,byte[] val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setText(String name,String val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setSerializable(String name,Serializable val){
		return setParameter(name, val,null);
	}

	@Override
	public Query setLocale(String name,Locale locale){
		return setParameter(name, locale,null);
	}

	@Override
	public Query setBigDecimal(String name,BigDecimal number){
		return setParameter(name, number,null);
	}

	@Override
	public Query setBigInteger(String name,BigInteger number){
		return setParameter(name, number,null);
	}

	@Override
	public Query setDate(String name,Date date){
		return setParameter(name, date,null);
	}

	@Override
	public Query setTime(String name,Date date){
		return setParameter(name, date,null);
	}

	@Override
	public Query setTimestamp(String name,Date date){
		return setParameter(name, date,null);
	}

	@Override
	public Query setCalendar(String name,Calendar calendar){
		return setParameter(name, calendar,null);
	}

	@Override
	public Query setCalendarDate(String name,Calendar calendar){
		return setParameter(name, calendar,null);
	}

	@Override
	public Query setEntity(int position,Object val){
		return setParameter(position,val,null);
	}

	@Override
	public Query setEntity(String name,Object val){
		return setParameter(name,val,null);
	}

	@Override
	public Query setResultTransformer(ResultTransformer transformer){
		return this;
	}
	
}