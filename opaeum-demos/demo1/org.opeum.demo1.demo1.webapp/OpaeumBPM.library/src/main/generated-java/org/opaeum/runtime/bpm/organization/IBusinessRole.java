package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_tH0fAIoVEeCLqpffVZYAlw")
public interface IBusinessRole extends IBusinessRoleBase, Participant, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(isComposite=true,opaeumId=742593574795479974,opposite="businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA252060@_3lcZgFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA252060@_3lcZgFYuEeGj5_I7bIwNoA")
	public Person_iBusinessRole_1 getPerson_iBusinessRole_1_representedPerson();
	
	@PropertyMetaInfo(isComposite=false,opaeumId=8923586012099856841,opposite="businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	public PersonNode getRepresentedPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 person_iBusinessRole_1_representedPerson);
	
	public void setRepresentedPerson(PersonNode representedPerson);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 val);
	
	public void z_internalAddToRepresentedPerson(PersonNode val);
	
	public void z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 val);
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode val);

}