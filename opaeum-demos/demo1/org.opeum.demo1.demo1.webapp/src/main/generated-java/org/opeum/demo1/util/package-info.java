@TypeDefs(value={
	@TypeDef(name="BusinessTimeUnitResolver",typeClass=BusinessTimeUnitResolver.class),
	@TypeDef(name="MonthResolver",typeClass=MonthResolver.class),
	@TypeDef(name="WorkDayKindResolver",typeClass=WorkDayKindResolver.class),
	@TypeDef(name="OrganizationEMailAddressTypeResolver",typeClass=OrganizationEMailAddressTypeResolver.class),
	@TypeDef(name="OrganizationPhoneNumberTypeResolver",typeClass=OrganizationPhoneNumberTypeResolver.class),
	@TypeDef(name="PersonEMailAddressTypeResolver",typeClass=PersonEMailAddressTypeResolver.class),
	@TypeDef(name="PersonPhoneNumberTypeResolver",typeClass=PersonPhoneNumberTypeResolver.class),
	@TypeDef(name="RequestParticipationKindResolver",typeClass=RequestParticipationKindResolver.class),
	@TypeDef(name="TaskDelegationResolver",typeClass=TaskDelegationResolver.class),
	@TypeDef(name="TaskParticipationKindResolver",typeClass=TaskParticipationKindResolver.class),
	@TypeDef(name="AbstractRequestStateResolver",typeClass=AbstractRequestStateResolver.class),
	@TypeDef(name="ProcessRequestStateResolver",typeClass=ProcessRequestStateResolver.class),
	@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)})
@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)
@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@AnyMetaDefs(	{
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusiness"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusinessActor"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusinessCollaboration"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusinessComponent"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=BusinessNetwork.class,value="2395627898464121473"),name="IBusinessNetwork"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusinessRole"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=OrganizationalNode.class,value="9636702410571466"),name="IOrganizationNode"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=Person.class,value="3517707551286497542"),name="IPerson"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="Participant"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IProcessObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IRequestObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityInvocation"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityProcessObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityTaskObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="ITaskObject")})
@NamedQueries(value=
	@NamedQuery(name="ProcessInstancesWaitingForEvent",query="select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)"))
package org.opeum.demo1.util;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeResolver;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressTypeResolver;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.FilterDef;
import org.opaeum.runtime.bpm.businesscalendar.BusinessTimeUnitResolver;
import org.opaeum.runtime.bpm.request.TaskRequestStateResolver;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressTypeResolver;
import org.hibernate.annotations.MetaValue;
import org.opaeum.runtime.bpm.request.TaskDelegationResolver;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.TypeDefs;
import org.opaeum.runtime.bpm.request.AbstractRequestStateResolver;
import org.opaeum.runtime.bpm.organization.OrganizationalNode;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberTypeResolver;
import org.opaeum.runtime.bpm.businesscalendar.WorkDayKindResolver;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.businesscalendar.MonthResolver;
import org.opaeum.runtime.bpm.request.TaskParticipationKindResolver;
import org.opaeum.runtime.bpm.request.ProcessRequestStateResolver;
import org.hibernate.annotations.AnyMetaDefs;
import org.opaeum.runtime.bpm.request.RequestParticipationKindResolver;
import javax.persistence.NamedQueries;
import org.hibernate.annotations.TypeDef;
import org.opaeum.runtime.bpm.organization.Person;
