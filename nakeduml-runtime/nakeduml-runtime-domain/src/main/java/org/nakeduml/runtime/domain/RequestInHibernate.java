package org.nakeduml.runtime.domain;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.Any;

public class RequestInHibernate{
	@Any(metaDef = "UserRoleMetaDef",metaColumn = @Column(name = "user_role_type"))
	private AbstractUserRole requestor;
	@Any(metaDef = "WorkUnitMetaDef",metaColumn = @Column(name = "work_unit_type"))
	private AbstractWorkUnit workUnit;
	private Date createdOn;
	private Date completedOn;

	public RequestInHibernate(){
		super();
	}
}