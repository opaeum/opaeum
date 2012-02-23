@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@AnyMetaDefs(	{
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusiness"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessActor"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessCollaboration"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessComponent"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=BusinessNetwork.class,value="org.opaeum.runtime.bpm.organization.BusinessNetwork"),name="IBusinessNetwork"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessRole"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=OrganizationalNode.class,value="org.opaeum.runtime.bpm.organization.OrganizationalNode"),name="IOrganizationNode"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=Person.class,value="org.opaeum.runtime.bpm.organization.Person"),name="IPerson"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="Participant"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IRequestObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityInvocation"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityTaskObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="ITaskObject")})
@NamedQueries(value=
	@NamedQuery(name="ProcessInstancesWaitingForEvent",query="select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)"))
package org.opeum.demo1.util;
import javax.persistence.NamedQuery;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.MetaValue;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import javax.persistence.NamedQueries;
import org.opaeum.runtime.bpm.organization.OrganizationalNode;
import org.opaeum.runtime.bpm.organization.Person;
