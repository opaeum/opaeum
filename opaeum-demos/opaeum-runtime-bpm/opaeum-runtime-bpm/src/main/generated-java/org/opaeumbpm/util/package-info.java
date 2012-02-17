@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@AnyMetaDefs(	{
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessActor"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessCollaboration"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessComponent"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessRole"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="Participant"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IRequestObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityInvocation"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityTaskObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="ITaskObject")})
@NamedQueries(value=
	@NamedQuery(name="ProcessInstancesWaitingForEvent",query="select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)"))
package org.opaeumbpm.util;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import javax.persistence.NamedQueries;
