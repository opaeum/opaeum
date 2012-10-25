package org.opaeum.audit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;


public class AuditHistory {
	private EntityManager entityManager;
	private IPersistentObject object;
	private String datePropertyName;

	public AuditHistory(EntityManager entityManager, IPersistentObject object, String datePropertyName) {
		super();
		this.entityManager = entityManager;
		this.object = object;
		this.datePropertyName = datePropertyName;
	}

	public SortedSet<AuditEntry> getHistory(Date fromDate, Date toDate){
		SortedSet<AuditEntry> result=new TreeSet<AuditEntry>();
		for (DateTimePropertyChange d : getHistoryFromDateProperty(fromDate, toDate)) {
			result.add(d.getAuditEntry());
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public SortedSet<PropertyChange<?>> getPropertyHistory(String propertyName, Date fromDate, Date toDate) {
		List<DateTimePropertyChange> list = getHistoryFromDateProperty(fromDate, toDate);
		TreeSet<PropertyChange<?>> result = new TreeSet<PropertyChange<?>>();
		if (list.size() > 0) {
			AuditEntry from = list.get(0).getAuditEntry();
			AuditEntry to = list.get(list.size() - 1).getAuditEntry();
			Criteria crit2 = getSession().createCriteria(PropertyChange.class);
			crit2=crit2.createAlias("auditEntry", "ae");
			crit2.add(Restrictions.eq("ae.originalId", object.getId()));
			crit2.add(Restrictions.eq("ae.originalType", IntrospectionUtil.getOriginalClass(object).getName()));
			crit2.add(Restrictions.ge("ae.objectVersion", from.getObjectVersion()));
			crit2.add(Restrictions.le("ae.objectVersion", to.getObjectVersion()));
			crit2.add(Restrictions.eq("propertyName", propertyName));
			result.addAll(crit2.list());
		}
		return result;

	}

	private List<DateTimePropertyChange> getHistoryFromDateProperty(Date fromDate, Date toDate) {
		Criteria crit = getSession().createCriteria(DateTimePropertyChange.class);
		crit=crit.createAlias("auditEntry", "ae");
		crit.add(Restrictions.eq("ae.originalType", IntrospectionUtil.getOriginalClass(object).getName()));
		crit.add(Restrictions.eq("ae.originalId", object.getId()));
		crit.add(Restrictions.eq("propertyName", datePropertyName));
		String fromString = new Timestamp( fromDate.getTime()).toString();
		crit.add(Restrictions.ge("stringValue", fromString));
		String toString = new Timestamp( toDate.getTime()).toString();
		crit.add(Restrictions.le("stringValue", toString));
		crit.addOrder(Order.asc("stringValue"));
		@SuppressWarnings("unchecked")
		List<DateTimePropertyChange> list = crit.list();
		return list;
	}

	private Session getSession() {
		Session session = (Session) entityManager.getDelegate();
		return session;
	}


}