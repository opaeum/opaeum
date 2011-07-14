package com.rorotika.cm.core.dto;

import java.util.Date;

public class CmApplicationDto {

	private Long id;
	private Date deletedOn;
	private Date createdOn;
	private Date updatedOn;
	private String uid;
	private String name;
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public CmApplicationDto() {
		super();
	}
	public CmApplicationDto(Long id, Date deletedOn, Date createdOn, Date updatedOn, String uid, String name, Date date) {
		super();
		this.id = id;
		this.deletedOn = deletedOn;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.uid = uid;
		this.name = name;
		this.date = date;
	}
}
