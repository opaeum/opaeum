package org.opaeum.audit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.event.EventSource;
import org.hibernate.jdbc.Work;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditWorkUnit {
	private static Map<Class<? extends IPersistentObject>, AuditEntryFactory<? extends IPersistentObject>> factories = new HashMap<Class<? extends IPersistentObject>, AuditEntryFactory<? extends IPersistentObject>>();
	Logger logger = LoggerFactory.getLogger(getClass());

	private static class EntityId {
		private Class<?> c;
		private Long id;

		public EntityId(Class<?> c, Long id) {
			super();
			this.c = IntrospectionUtil.getOriginalClass(c);
			this.id = id;
		}

		@Override
		public boolean equals(Object arg0) {
			if (arg0 == this) {
				return true;
			} else if (arg0 instanceof EntityId) {
				EntityId entityId = (EntityId) arg0;
				return id.equals(entityId.id) && c == entityId.c;
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return (int) (c.hashCode() * 10000 + id);
		}
	}

	private SessionFactory sessionFactory;
	Map<EntityId, AuditEntry> entriesByEntityId = new HashMap<EntityId, AuditEntry>();
	List<AuditEntry> auditEntriesToBeFlushed = new ArrayList<AuditEntry>();
	private Map<AuditEntryFactory<? extends IPersistentObject>, StringBuilder> auditEntryInserts = new HashMap<AuditEntryFactory<? extends IPersistentObject>, StringBuilder>();
	private StringBuilder propertyChangeInsert;
	private boolean firstPropertyChangeProcessed;
	private StringBuilder auditEntryInsert;

	public AuditWorkUnit(EventSource session) {
		sessionFactory = session.getSessionFactory();
	}

	public void flush() {
		Session auditSession=sessionFactory.openSession();
		Transaction tx = auditSession.beginTransaction();
		propertyChangeInsert = new StringBuilder(
				"insert into property_change (property_change_type,audit_entry_id,property_name,string_value,old_string_value) values ");
		firstPropertyChangeProcessed = false;
		for (AuditEntry auditEntry : entriesByEntityId.values()) {
			prepareAuditEntry(auditEntry);
			if (auditEntriesToBeFlushed.isEmpty()) {
				initializeAuditEntryInsert();
			} else {
				this.auditEntryInsert.append(",");
			}
			appendAuditEntryValues(auditEntry);
			auditEntriesToBeFlushed.add(auditEntry);
			appendCustomAuditEntryInsert(auditEntry);
			for (PropertyChange<?> change : auditEntry.getChanges().values()) {
				appendPropertyChangeValues(change);
			}
			if (auditEntriesToBeFlushed.size() == 150) {
				flushAuditEntries(auditSession);
			}
		}
		flushAuditEntries(auditSession);
		fushPropertyChangesAndCustomAuditEntries(auditSession);
		auditSession.flush();//Probably Superfluous
		tx.commit();
	}

	private void fushPropertyChangesAndCustomAuditEntries(Session auditSession) {
		auditSession.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				if (firstPropertyChangeProcessed) {
					try {
						connection.prepareStatement(propertyChangeInsert.toString()).execute();
					} catch (SQLException e) {
						e.printStackTrace();
						throw e;
					}
				}
				for (StringBuilder sb : auditEntryInserts.values()) {
					String sql = sb.toString();
					if (sql.trim().length() > 0) {
						try {
							connection.prepareStatement(sql).execute();
						} catch (SQLException e) {
							e.printStackTrace();
							throw e;
						}
					}
				}

			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void appendCustomAuditEntryInsert(AuditEntry auditEntry) {
		AuditEntryFactory factory = factories.get(auditEntry.getOriginalClass());
		StringBuilder sb = auditEntryInserts.get(factory);
		if (sb == null) {
			sb = factory.getInsertClause();
			auditEntryInserts.put(factory, sb);
		}
		factory.appendToValuesClause(sb, auditEntry);
	}

	private void flushAuditEntries(Session auditSession) {
		if (auditEntriesToBeFlushed.size() > 0) {
			auditSession.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					try {
						connection.prepareStatement(auditEntryInsert.toString()).execute();
					} catch (SQLException e) {
						logger.error(e.getMessage(),e);
						throw e;
					}

				}
			});
			auditEntriesToBeFlushed.clear();
			auditEntryInsert = null;
		}
	}

	private void initializeAuditEntryInsert() {
		this.auditEntryInsert = new StringBuilder(
				"insert into audit_entry (id,object_version,original_id,original_type,previous_version_id, audit_date_time,action) values ");
	}

	private void appendAuditEntryValues(AuditEntry auditEntry) {
		auditEntryInsert.append("\n('");
		auditEntryInsert.append(auditEntry.getId());
		auditEntryInsert.append("',");
		auditEntryInsert.append(auditEntry.getObjectVersion());
		auditEntryInsert.append(",");
		auditEntryInsert.append(auditEntry.getOriginalId());
		auditEntryInsert.append(",'");
		auditEntryInsert.append(auditEntry.getOriginalType());
		auditEntryInsert.append("',");
		if (auditEntry.getPreviousVersionId().getObjectVersion() >= 1) {
			auditEntryInsert.append("'");
			auditEntryInsert.append(auditEntry.getPreviousVersionId().getId());
			auditEntryInsert.append("'");
		} else {
			auditEntryInsert.append("null");
		}
		auditEntryInsert.append(",'");
		auditEntryInsert.append(new Timestamp(auditEntry.getAuditDateTime().getTime()).toString());
		auditEntryInsert.append("','");
		auditEntryInsert.append(auditEntry.getAction().name());
		auditEntryInsert.append("')");
	}

	private void appendPropertyChangeValues(PropertyChange<?> change) {
		if (firstPropertyChangeProcessed) {
			propertyChangeInsert.append(",");
		}
		firstPropertyChangeProcessed = true;
		propertyChangeInsert.append("\n('");
		propertyChangeInsert.append(change.getPropertyChangeType());
		propertyChangeInsert.append("','");
		propertyChangeInsert.append(change.getAuditEntry().getId());
		propertyChangeInsert.append("','");
		propertyChangeInsert.append(change.getPropertyName());
		propertyChangeInsert.append("',");
		appendStringValue(change.getStringValue());
		propertyChangeInsert.append(",");
		appendStringValue(change.getOldStringValue());
		propertyChangeInsert.append(")");
	}

	private void appendStringValue(String stringValue) {
		if (stringValue == null) {
			propertyChangeInsert.append("null");
		} else {
			propertyChangeInsert.append("'");
			propertyChangeInsert.append(stringValue.replace("'", "''"));
			propertyChangeInsert.append("'");
		}
	}

	private void prepareAuditEntry(AuditEntry auditEntry) {
		ensureLatestValuesForAuditEntryChanges(auditEntry);
		logChangesToManyToOnes(auditEntry);
	}

	private void ensureLatestValuesForAuditEntryChanges(AuditEntry auditEntry) {
		for (PropertyChange<?> change : auditEntry.getChanges().values()) {
			if (change instanceof AuditEntryPropertyChange) {
				AuditEntryPropertyChange aepc = (AuditEntryPropertyChange) change;
				if (aepc.getValue() != null) {
					AuditEntry latest = entriesByEntityId.get(toEntityId(aepc.getValue().getOriginalClass(), aepc.getValue().getOriginalId()));
					if (latest != null) {
						aepc.setLatestValue(latest);
					}
				}
			}
		}
	}

	private void logChangesToManyToOnes(AuditEntry auditEntry) {
		Set<Entry<String, IPersistentObject>> manyToOnes = auditEntry.getManyToOnes();
		for (Entry<String, IPersistentObject> entry : manyToOnes) {
			AuditEntry latest = entriesByEntityId.get(toEntityId(entry.getValue()));
			if (latest != null) {
				auditEntry.putPropertyChange(entry.getKey(), null, latest);
			}
		}
	}

	@SuppressWarnings({ "rawtypes"})
	public void logPropertyChanges(Object[] oldState, Object[] newState, int[] dirtyProperties, IPersistentObject entity, String[] propertyNames, int version) {
		EntityId ei = toEntityId(entity);
		AuditEntry entry = entriesByEntityId.get(ei);
		if (entry == null) {
			AuditEntryFactory factory = getFactory(entity);
			entry = factory.createAuditEntry(entity, version);
			entry.setAction(AuditedAction.UPDATE);
			for (int i = 0; i < newState.length; i++) {
				Object object = newState[i];
				if(object instanceof Date && propertyNames[i].endsWith("deletedOn") && !((Date) object).after(new Date())){
					entry.setAction(AuditedAction.DELETE);
				}
			}
			entriesByEntityId.put(ei, entry);
		} else {
			// Ensure one entry per flush
			entry.updateVersion(version);
		}
		for (int i = 0; i < dirtyProperties.length; i++) {
			int propIndex = dirtyProperties[i];
			entry.putPropertyChange(propertyNames[propIndex], oldState[propIndex], newState[propIndex]);
		}
		for (int i = 0; i < newState.length; i++) {
			if (newState[i] instanceof IPersistentObject && ((Class<?>) IntrospectionUtil.getOriginalClass(newState[i])).isAnnotationPresent(AuditMe.class)) {
				entry.addManyToOne(propertyNames[i], (IPersistentObject) newState[i]);
				getFactory((IPersistentObject) newState[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private AuditEntryFactory<? extends IPersistentObject> getFactory(IPersistentObject entity) {
		Class<? extends IPersistentObject> clz = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entity);
		AuditEntryFactory<? extends IPersistentObject> factory = factories.get(clz);
		if (factory == null) {
			AuditMe ann = clz.getAnnotation(AuditMe.class);
			try {
				factory = ann.factory().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			factories.put(clz, factory);
		}
		return factory;
	}

	private EntityId toEntityId(IPersistentObject entity) {
		Class<?> originalClass = IntrospectionUtil.getOriginalClass(entity);
		return toEntityId(originalClass, entity.getId());
	}

	private EntityId toEntityId(Class<?> originalClass, Long id) {
		return new EntityId(originalClass, id);
	}

	public void logInsertedProperties(Object[] newState, String[] propertyNames, IPersistentObject entity, int version) {
		AuditEntryFactory<?> factory = getFactory(entity);
		AuditEntry entry = factory.createAuditEntry(entity, version);
		entry.setAction(AuditedAction.CREATE);
		entriesByEntityId.put(toEntityId(entity), entry);
		for (int i = 0; i < newState.length; i++) {
			entry.putPropertyChange(propertyNames[i], null, newState[i]);
		}
	}
	public StringBuilder getPropertyChangeInsert(){
		return propertyChangeInsert;
	}
	public StringBuilder getAuditEntryInsert(){
		return auditEntryInsert;
	}

}
