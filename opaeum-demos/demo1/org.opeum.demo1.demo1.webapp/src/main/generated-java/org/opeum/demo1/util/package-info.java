@TypeDefs(value={
	@TypeDef(name="BusinessTimeUnitResolver",typeClass=BusinessTimeUnitResolver.class),
	@TypeDef(name="DocumentTypeResolver",typeClass=DocumentTypeResolver.class),
	@TypeDef(name="TaskDelegationResolver",typeClass=TaskDelegationResolver.class),
	@TypeDef(name="MonthResolver",typeClass=MonthResolver.class),
	@TypeDef(name="WorkDayKindResolver",typeClass=WorkDayKindResolver.class),
	@TypeDef(name="OrganizationEMailAddressTypeResolver",typeClass=OrganizationEMailAddressTypeResolver.class),
	@TypeDef(name="OrganizationPhoneNumberTypeResolver",typeClass=OrganizationPhoneNumberTypeResolver.class),
	@TypeDef(name="PersonEMailAddressTypeResolver",typeClass=PersonEMailAddressTypeResolver.class),
	@TypeDef(name="PersonPhoneNumberTypeResolver",typeClass=PersonPhoneNumberTypeResolver.class),
	@TypeDef(name="RequestParticipationKindResolver",typeClass=RequestParticipationKindResolver.class),
	@TypeDef(name="TaskParticipationKindResolver",typeClass=TaskParticipationKindResolver.class),
	@TypeDef(name="VendorResolver",typeClass=VendorResolver.class),
	@TypeDef(name="AbstractRequestStateResolver",typeClass=AbstractRequestStateResolver.class),
	@TypeDef(name="ProcessRequestStateResolver",typeClass=ProcessRequestStateResolver.class),
	@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)})
@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)
@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@AnyMetaDefs(	{
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=PersonEMailAddress.class,value="2460027813273694992"),name="IPersonEMailAddress"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=PersonPhoneNumber.class,value="11888058762954742"),name="IPersonPhoneNumber"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=PhysicalAddress.class,value="3794601824342079784"),name="IPersonPhysicalAddress"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=PostalAddress.class,value="1723129922807887446"),name="IPersonPostalAddress"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=IdBook.class,value="7267980829799356539"),name="IBusinessDocument"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="INotification"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=DishwashersInc.class,value="8415961198448241003"),name="IBusiness"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510")},name="IBusinessActor"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510")},name="IBusinessActorBase"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=DishwashersInc.class,value="8415961198448241003"),name="IBusinessBase"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=Structuredbusiness.class,value="7737100568581358598"),name="IBusinessCollaboration"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=Structuredbusiness.class,value="7737100568581358598"),name="IBusinessCollaborationBase"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=DishwashersInc.class,value="8415961198448241003"),name="IBusinessComponent"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=BusinessNetwork.class,value="2395627898464121473"),name="IBusinessNetwork"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={
		@MetaValue(targetEntity=Accountant.class,value="3718403143352034683"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123")},name="IBusinessRole"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={
		@MetaValue(targetEntity=Accountant.class,value="3718403143352034683"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123")},name="IBusinessRoleBase"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IBusinessService"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=OrganizationNode.class,value="9636702410571466"),name="IOrganizationNode"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues=
		@MetaValue(targetEntity=PersonNode.class,value="3517707551286497542"),name="IPersonNode"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=DishwashersInc.class,value="8415961198448241003"),
		@MetaValue(targetEntity=Accountant.class,value="3718403143352034683"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510")},name="Participant"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IProcessObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IRequestObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityInvocation"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityProcessObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="IResponsibilityTaskObject"),
	@AnyMetaDef(idType="long",metaType="integer",metaValues={},name="ITaskObject")})
@NamedQueries(value=
	@NamedQuery(name="ProcessInstancesWaitingForEvent",query="select processInstanceInfo.processInstanceId from org.jbpm.persistence.processinstance.ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)"))
package org.opeum.demo1.util;
import org.opaeum.runtime.contact.PersonEMailAddressTypeResolver;
import structuredbusiness.IdBook;
import org.hibernate.annotations.FilterDef;
import org.opaeum.runtime.bpm.request.TaskRequestStateResolver;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressTypeResolver;
import org.hibernate.annotations.MetaValue;
import org.hibernate.annotations.AnyMetaDef;
import org.opaeum.runtime.bpm.contact.PersonEMailAddress;
import structuredbusiness.Online_Customer;
import structuredbusiness.Structuredbusiness;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.organization.PersonNode;
import structuredbusiness.Manager;
import org.opaeum.runtime.bpm.request.TaskParticipationKindResolver;
import org.opaeum.runtime.bpm.request.ProcessRequestStateResolver;
import org.hibernate.annotations.AnyMetaDefs;
import org.opaeum.runtime.bpm.request.RequestParticipationKindResolver;
import org.hibernate.annotations.TypeDef;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeResolver;
import javax.persistence.NamedQuery;
import org.opaeum.runtime.domain.DocumentTypeResolver;
import org.opaeum.runtime.domain.BusinessTimeUnitResolver;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumber;
import org.opaeum.runtime.domain.TaskDelegationResolver;
import org.hibernate.annotations.TypeDefs;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.bpm.request.AbstractRequestStateResolver;
import structuredbusiness.DishwashersInc;
import org.opaeum.runtime.contact.PersonPhoneNumberTypeResolver;
import org.opaeum.runtime.bpm.businesscalendar.WorkDayKindResolver;
import structuredbusiness.Supplier;
import org.opaeum.runtime.bpm.businesscalendar.MonthResolver;
import structuredbusiness.VendorResolver;
import javax.persistence.NamedQueries;
import structuredbusiness.Accountant;
