package org.opaeum.test;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA")
public interface SurnameProvider extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToSurnameCarryingDaughter(String name, Sister surnameCarryingDaughter);
	
	public void addToSurnameCarryingSon(Brother surnameCarryingSon);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearSurnameCarryingDaughter();
	
	public void clearSurnameCarryingSon();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=599591342263275344l,opposite="surnameProvider",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	public Marriage getMarriage_spouse();
	
	public Marriage getMarriage_spouseFor(Spouse match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=112985228842363744l,opposite="surnameProvider",uuid="Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	public Spouse getSpouse();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3588699929451751767l,opposite="surnameProvider",uuid="Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	public Set<Sister> getSurnameCarryingDaughter();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8908457503420876049l,opposite="surnameProvider",uuid="Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	public Set<Brother> getSurnameCarryingSon();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3102093816030840167l,opposite="surnameProvider",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	public Set<SurnameProviderHasDaughter> getSurnameProviderHasDaughter_surnameCarryingDaughter();
	
	public SurnameProviderHasDaughter getSurnameProviderHasDaughter_surnameCarryingDaughterFor(Sister match);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=9051680456867763967l,opposite="surnameProvider",uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	public Set<SurnameProviderHasSon> getSurnameProviderHasSon_surnameCarryingSon();
	
	public SurnameProviderHasSon getSurnameProviderHasSon_surnameCarryingSonFor(Brother match);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromSurnameCarryingDaughter(String name, Sister surnameCarryingDaughter);
	
	public void removeFromSurnameCarryingSon(Brother surnameCarryingSon);
	
	public void setSpouse(Spouse spouse);
	
	public void setSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter);
	
	public void setSurnameCarryingSon(Set<Brother> surnameCarryingSon);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToMarriage_spouse(Marriage marriage_spouse);
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(String name, SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter);
	
	public void z_internalAddToSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon);
	
	public void z_internalRemoveFromMarriage_spouse(Marriage marriage_spouse);
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(String name, SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter);
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon);

}