package org.opaeum.runtime.costing;

import java.util.Date;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Entity;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Entity
@Table(name = "duration_based_cost_entry")
public class DurationBasedCostEntry implements IPersistentObject{
	private static final long serialVersionUID = 6535252690177290628L;
	@Version
	@Column(name = "object_version")
	int objectVersion;
	String uid = UUID.randomUUID().toString();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	@AttributeOverrides({@AttributeOverride(name = "identifier",column = @Column(name = "resource_identifier")),
			@AttributeOverride(name = "class_identifier",column = @Column(name = "resource_type"))})
	private InterfaceValue resource;
	private boolean newMeasurement;
	@Transient
	AbstractPersistence persistence;
	public DurationBasedCostEntry(){
	}
	public DurationBasedCostEntry(Date fromDate,Date toDate,ITimedResourceBase resource,boolean newMeasurement){
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.newMeasurement = newMeasurement;
		this.resource = new InterfaceValue(resource);
	}
	public Date getFromDate(){
		return fromDate;
	}
	public Date getToDate(){
		return toDate;
	}
	public ITimedResourceBase getResource(){
		return (ITimedResourceBase) resource.getValue(persistence);
	}
	public boolean isNewMeasurement(){
		return newMeasurement;
	}
	@Override
	public Long getId(){
		return id;
	}
	@Override
	public void setId(Long id){
		this.id = id;
	}
	@Override
	public String getName(){
		return null;
	}
	@Override
	public String getUid(){
		return uid;
	}
	@Override
	public int getObjectVersion(){
		return objectVersion;
	}
}
