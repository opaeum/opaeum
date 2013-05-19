package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.costing.ITimedResource;
import org.opaeum.runtime.costing.IRatePerTimeUnit;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.INotificationReceiver;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_tH0fAIoVEeCLqpffVZYAlw")
public interface IBusinessRole extends IBusinessRoleBase, ITimedResource, HibernateEntity, CompositionNode, INotificationReceiver, Serializable, IPersistentObject, IParticipant {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6594897030343926470l,opposite="businessRole",uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_tH0fAIoVEeCLqpffVZYAlw@252060@_3lcZgFYuEeGj5_I7bIwNoA")
	public PersonInBusinessRole getPersonInBusinessRole_representedPerson();
	
	public PersonInBusinessRole getPersonInBusinessRole_representedPersonFor(PersonNode match);
	
	@NumlMetaInfo(uuid="252060@_6Co6gO0lEeGN-aZ7URyUbw")
	public PersonNode getPersonNode();
	
	@NumlMetaInfo(uuid="252060@_V3n1EPjyEeGEN6Fz86uBYA")
	public IRatePerTimeUnit getRateEffectiveOn(@ParameterMetaInfo(name="date",opaeumId=818066613415140745l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nQHK4PjyEeGEN6Fz86uBYA") Date date);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8923586012099856841l,opposite="businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	public PersonNode getRepresentedPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setRepresentedPerson(PersonNode representedPerson);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson);
	
	public void z_internalRemoveFromPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson);

}