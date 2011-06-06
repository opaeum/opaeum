package org.nakeduml.audit;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.AuditId;
import org.nakeduml.runtime.domain.Auditable;
import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.domain.RevisionEntity;
import org.nakeduml.runtime.domain.RevisionType;
import org.nakeduml.seam3.persistence.TransactionScopedSession;

@TransactionAttribute
public class AuditCapturer{
	@Inject
	@TransactionScopedSession
	private Session session;
	public void persistAudit(AbstractWorkUnit workUnit){
		session.setFlushMode(FlushMode.COMMIT);
		try{
			RevisionEntity revisionEntity = new RevisionEntity();
			session.persist(revisionEntity);
			LinkedList<Audited> entities = workUnit.getAuditedEntities();
			for(Audited audited:entities){
				audited.setRevision(revisionEntity);
				session.persist(audited);
			}
			for(Audited audited:entities){
				// Set the aboriginal
				Auditable original = audited.getOriginal();
				IPersistentObject fetchedOriginal = (IPersistentObject) session.load(IntrospectionUtil.getOriginalClass(original.getClass()), original.getId());
				audited.setOriginal(fetchedOriginal);
//				if(audited.getPreviousVersion()!=null){
				if(original.getObjectVersion()>0){
//					AuditId id = audited.getPreviousVersion().getId();
					AuditId id = new AuditId(original.getId(), original.getObjectVersion()-1);
					Audited fetchedPreviousVersion = (Audited) session.load(IntrospectionUtil.getOriginalClass(audited.getClass()), id);
					audited.setPreviousVersion(fetchedPreviousVersion);
				}
				BeanInfo bi = Introspector.getBeanInfo(audited.getClass());
				PropertyDescriptor[] pds = bi.getPropertyDescriptors();
				for(PropertyDescriptor pd:pds){
					if(pd.getReadMethod() != null && pd.getWriteMethod() != null && Audited.class.isAssignableFrom(pd.getPropertyType())
							&& !pd.getWriteMethod().getName().equals("setPreviousVersion")){
						Audited one = (Audited) pd.getReadMethod().invoke(audited);
						if(one != null){
							// Use load because it must return a value
							Audited fetchedOne = (Audited) session.load(IntrospectionUtil.getOriginalClass(one.getClass()), one.getId());
							String oneName =NameConverter.capitalize(pd.getName());
							try{
								Method setter = audited.getClass().getMethod("z_internalAddTo" +oneName,
										pd.getWriteMethod().getParameterTypes()[0]);
								setter.invoke(audited, fetchedOne);
							}catch(NoSuchMethodException e){
								e.printStackTrace();
							}
							// TODO check if wanted
							// setOtherSideOfOneToOne(audited, fetchedOne, oneName);
						}
					}
				}
			}
			session.flush();
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			ea.throwRootCause();
		}
	}
	private void setOtherSideOfOneToOne(Audited audited,Audited fetchedOne,String oneName) throws IntrospectionException,IllegalAccessException,InvocationTargetException{
		BeanInfo bi;
		bi = Introspector.getBeanInfo(fetchedOne.getClass());
		PropertyDescriptor[] oneToOnePds = bi.getPropertyDescriptors();
		for(PropertyDescriptor oneToOnePd:oneToOnePds){
			if(oneToOnePd.getReadMethod() != null && oneToOnePd.getWriteMethod() != null && audited.getClass().isAssignableFrom(oneToOnePd.getPropertyType())
					&& !oneToOnePd.getWriteMethod().getName().equals("setPreviousVersion")){
				String oneToOneName = oneToOnePd.getWriteMethod().getParameterTypes()[0].getSimpleName();
				try{
					Method setter = fetchedOne.getClass().getMethod("z_internalAddTo" + oneToOneName.substring(0, oneName.length() - 6),
							oneToOnePd.getWriteMethod().getParameterTypes()[0]);
					setter.invoke(fetchedOne, audited);
					break;
				}catch(NoSuchMethodException e){
					e.printStackTrace();
				}
			}
		}
	}
}
