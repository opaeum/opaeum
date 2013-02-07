@TypeDefs(value={
	@TypeDef(name="BusinessTimeUnitResolver",typeClass=BusinessTimeUnitResolver.class),
	@TypeDef(name="DocumentTypeResolver",typeClass=DocumentTypeResolver.class),
	@TypeDef(name="TaskDelegationResolver",typeClass=TaskDelegationResolver.class),
	@TypeDef(name="WorkDayKindResolver",typeClass=WorkDayKindResolver.class),
	@TypeDef(name="OrganizationEMailAddressTypeResolver",typeClass=OrganizationEMailAddressTypeResolver.class),
	@TypeDef(name="OrganizationPhoneNumberTypeResolver",typeClass=OrganizationPhoneNumberTypeResolver.class),
	@TypeDef(name="PersonEMailAddressTypeResolver",typeClass=PersonEMailAddressTypeResolver.class),
	@TypeDef(name="PersonPhoneNumberTypeResolver",typeClass=PersonPhoneNumberTypeResolver.class),
	@TypeDef(name="RequestParticipationKindResolver",typeClass=RequestParticipationKindResolver.class),
	@TypeDef(name="TaskParticipationKindResolver",typeClass=TaskParticipationKindResolver.class),
	@TypeDef(name="DayOfWeekResolver",typeClass=DayOfWeekResolver.class),
	@TypeDef(name="MonthResolver",typeClass=MonthResolver.class),
	@TypeDef(name="SailPositionResolver",typeClass=SailPositionResolver.class),
	@TypeDef(name="ApplianceTypeResolver",typeClass=ApplianceTypeResolver.class),
	@TypeDef(name="CityResolver",typeClass=CityResolver.class),
	@TypeDef(name="ProvinceResolver",typeClass=ProvinceResolver.class),
	@TypeDef(name="VendorResolver",typeClass=VendorResolver.class),
	@TypeDef(name="AbstractRequestStateResolver",typeClass=AbstractRequestStateResolver.class),
	@TypeDef(name="ProcessRequestStateResolver",typeClass=ProcessRequestStateResolver.class),
	@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)})
@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class)
@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@AnyMetaDefs(	{
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=PersonEMailAddress.class,value="2460027813273694992"),name="IPersonEMailAddress"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=PersonPhoneNumber.class,value="11888058762954742"),name="IPersonPhoneNumber"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=PhysicalAddress.class,value="3794601824342079784"),name="IPersonPhysicalAddress"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=PostalAddress.class,value="1723129922807887446"),name="IPersonPostalAddress"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=IdBook.class,value="7267980829799356539"),name="IBusinessDocument"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="INotification"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusiness"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510")},name="IBusinessActor"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IBusinessActor.class,value="4309796489693165358"),
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510")},name="IBusinessActorBase"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=IBusiness.class,value="644495856821958501"),name="IBusinessBase"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessCollaboration"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=IBusinessCollaboration.class,value="3202743149082685603"),name="IBusinessCollaborationBase"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IBusiness.class,value="644495856821958501"),
		@MetaValue(targetEntity=Branch.class,value="128937784137359716"),
		@MetaValue(targetEntity=ApplianceDoctor.class,value="8415961198448241003")},name="IBusinessComponent"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IBusiness.class,value="644495856821958501"),
		@MetaValue(targetEntity=IBusinessComponent.class,value="7082015034007497275"),
		@MetaValue(targetEntity=Branch.class,value="128937784137359716"),
		@MetaValue(targetEntity=ApplianceDoctor.class,value="8415961198448241003")},name="IBusinessComponentBase"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=BusinessNetwork.class,value="2395627898464121473"),name="IBusinessNetwork"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=CustomerAssistant.class,value="8190330448809072926"),
		@MetaValue(targetEntity=Technician.class,value="8797403277162081281"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123"),
		@MetaValue(targetEntity=BusinessRole1.class,value="9197527870131921346")},name="IBusinessRole"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IBusinessRole.class,value="3505313999245589241"),
		@MetaValue(targetEntity=CustomerAssistant.class,value="8190330448809072926"),
		@MetaValue(targetEntity=Technician.class,value="8797403277162081281"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123"),
		@MetaValue(targetEntity=BusinessRole1.class,value="9197527870131921346")},name="IBusinessRoleBase"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IBusinessService"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=OrganizationNode.class,value="9636702410571466"),name="IOrganizationNode"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=PersonNode.class,value="3517707551286497542"),name="IPersonNode"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IBusinessActor.class,value="4309796489693165358"),
		@MetaValue(targetEntity=IBusiness.class,value="644495856821958501"),
		@MetaValue(targetEntity=IBusinessRole.class,value="3505313999245589241"),
		@MetaValue(targetEntity=IBusinessComponent.class,value="7082015034007497275"),
		@MetaValue(targetEntity=Supplier.class,value="6592718997733823438"),
		@MetaValue(targetEntity=Branch.class,value="128937784137359716"),
		@MetaValue(targetEntity=ApplianceDoctor.class,value="8415961198448241003"),
		@MetaValue(targetEntity=CustomerAssistant.class,value="8190330448809072926"),
		@MetaValue(targetEntity=Technician.class,value="8797403277162081281"),
		@MetaValue(targetEntity=Manager.class,value="3586662115628447123"),
		@MetaValue(targetEntity=Online_Customer.class,value="3937437504061981510"),
		@MetaValue(targetEntity=BusinessRole1.class,value="9197527870131921346")},name="Participant"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=IResponsibilityProcessObject.class,value="725181053057904736"),name="IProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IResponsibilityInvocation.class,value="909032701460639790"),
		@MetaValue(targetEntity=ITaskObject.class,value="6680281817557132015"),
		@MetaValue(targetEntity=IProcessObject.class,value="4995639096998105275"),
		@MetaValue(targetEntity=IResponsibilityProcessObject.class,value="725181053057904736"),
		@MetaValue(targetEntity=IResponsibilityTaskObject.class,value="663791456725138204")},name="IRequestObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={
		@MetaValue(targetEntity=IResponsibilityProcessObject.class,value="725181053057904736"),
		@MetaValue(targetEntity=IResponsibilityTaskObject.class,value="663791456725138204")},name="IResponsibilityInvocation"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityProcessObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues={},name="IResponsibilityTaskObject"),
	@AnyMetaDef(idType="long",metaType="string",metaValues=
		@MetaValue(targetEntity=IResponsibilityTaskObject.class,value="663791456725138204"),name="ITaskObject")})
package org.opeum.demo1.util;
import ocltests.BusinessRole1;
import ocltests.SailPositionResolver;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.MetaValue;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.opaeum.runtime.bpm.businesscalendar.WorkDayKindResolver;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressTypeResolver;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeResolver;
import org.opaeum.runtime.bpm.contact.PersonEMailAddress;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumber;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.bpm.opaeumsimpletypes.DayOfWeekResolver;
import org.opaeum.runtime.bpm.opaeumsimpletypes.MonthResolver;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.AbstractRequestStateResolver;
import org.opaeum.runtime.bpm.request.ProcessRequestStateResolver;
import org.opaeum.runtime.bpm.request.RequestParticipationKindResolver;
import org.opaeum.runtime.bpm.request.TaskParticipationKindResolver;
import org.opaeum.runtime.bpm.request.TaskRequestStateResolver;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.bpm.requestobject.IResponsibilityInvocation;
import org.opaeum.runtime.bpm.requestobject.IResponsibilityProcessObject;
import org.opaeum.runtime.bpm.requestobject.IResponsibilityTaskObject;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.contact.PersonEMailAddressTypeResolver;
import org.opaeum.runtime.contact.PersonPhoneNumberTypeResolver;

import structuredbusiness.ApplianceDoctor;
import structuredbusiness.ApplianceTypeResolver;
import structuredbusiness.Branch;
import structuredbusiness.CityResolver;
import structuredbusiness.CustomerAssistant;
import structuredbusiness.IdBook;
import structuredbusiness.Manager;
import structuredbusiness.Online_Customer;
import structuredbusiness.ProvinceResolver;
import structuredbusiness.Supplier;
import structuredbusiness.Technician;
import structuredbusiness.VendorResolver;

