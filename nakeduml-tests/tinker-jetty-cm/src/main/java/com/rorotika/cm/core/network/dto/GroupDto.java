package com.rorotika.cm.core.network.dto;

import java.util.Date;

public class GroupDto {

	private String uid;
	protected Long cmApplicationId;
	protected String name;
	private Long id;
	private Date deletedOn;
	private Date createdOn;
	private Date updatedOn;
	public String getUid() {
		return uid;
	}
	public Long getCmApplicationId() {
		return cmApplicationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDeletedOn() {
		return deletedOn;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
}
