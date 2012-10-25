package org.opaeum.runtime.event;

import javax.activation.DataSource;

public class AttachmentHolder {

	private String name;
	private DataSource dataSource;
	public AttachmentHolder(String name, DataSource dataSource) {
		super();
		this.name = name;
		this.dataSource = dataSource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
