package net.sf.nakeduml.audit;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AuditId;
import net.sf.nakeduml.util.Audited;
import net.sf.nakeduml.util.RevisionEntity;

import org.hibernate.Session;

@TransactionAttribute
public class AuditCapturer {

	@Inject
	private Session session;

	public void persistAudit(AbstractWorkUnit workUnit) {
		try {
			RevisionEntity revisionEntity = new RevisionEntity();
			session.persist(revisionEntity);
			LinkedList<Audited> entities = workUnit.getAuditedEntities();
			for (Audited audited : entities) {
				audited.setRevision(revisionEntity);
				session.persist(audited);
			}
			for (Audited audited : entities) {
				// Set the aboriginal
				AbstractEntity original = audited.getOriginal();
				AbstractEntity fetchedOriginal = (AbstractEntity) session.get(AnotherUtil.getOriginalClass(original.getClass()), original.getId());
				audited.setOriginal(fetchedOriginal);

				AuditId id = new AuditId(audited.getId().getOriginalId(), audited.getId().getObjectVersion() - 1);
				Audited fetchedPreviousVersion = (Audited) session.get(AnotherUtil.getOriginalClass(audited.getClass()), id);
				audited.setPreviousVersion(fetchedPreviousVersion);

				BeanInfo bi = Introspector.getBeanInfo(audited.getClass());
				PropertyDescriptor[] pds = bi.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if (pd.getReadMethod() != null && pd.getWriteMethod() != null && Audited.class.isAssignableFrom(pd.getPropertyType())
							&& !pd.getWriteMethod().getName().equals("setPreviousVersion")) {
						Audited one = (Audited) pd.getReadMethod().invoke(audited);
						if (one != null) {
							Audited fetchedOne = (Audited) session.get(AnotherUtil.getOriginalClass(one.getClass()), one.getId());
							String oneName = pd.getWriteMethod().getParameterTypes()[0].getSimpleName();
							try {
								Method setter = audited.getClass().getMethod("z_internalAddTo" + oneName.substring(0, oneName.length() - 6),
										pd.getWriteMethod().getParameterTypes()[0]);
								setter.invoke(audited, fetchedOne);
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			session.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
