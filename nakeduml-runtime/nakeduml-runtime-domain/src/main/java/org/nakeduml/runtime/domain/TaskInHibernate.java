package org.nakeduml.runtime.domain;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.Any;

public class TaskInHibernate extends RequestInHibernate{
	@Any(metaDef="UserRoleMetaDef",metaColumn=@Column(name="fulfiller_type"))
	private AbstractUserRole fulfiller;
	@Any(metaDef="OrganizationUnitMetaDef",metaColumn=@Column(name="organization_unit_type"))
	private OrganizationUnit organizationUnit;
	private Date acceptedOn;
}
