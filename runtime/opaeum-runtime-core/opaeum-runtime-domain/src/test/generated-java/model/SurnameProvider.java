package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA")
public interface SurnameProvider extends CompositionNode, Serializable {
	public void addToSurnameCarryingDaughter(Sister surnameCarryingDaughter);
	
	public void addToSurnameCarryingSon(Brother surnameCarryingSon);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearSurnameCarryingDaughter();
	
	public void clearSurnameCarryingSon();
	
	public SurnameProviderHasDaughter createSurnameProviderHasDaughter_surnameCarryingDaughter();
	
	public SurnameProviderHasSon createSurnameProviderHasSon_surnameCarryingSon();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4841945505933354426l,opposite="surnameProvider",uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
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
	
	public void removeFromSurnameCarryingDaughter(Sister surnameCarryingDaughter);
	
	public void removeFromSurnameCarryingSon(Brother surnameCarryingSon);
	
	public void setSpouse(Spouse spouse);
	
	public void setSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter);
	
	public void setSurnameCarryingSon(Set<Brother> surnameCarryingSon);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToSpouse(Spouse spouse);
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter);
	
	public void z_internalAddToSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon);
	
	public void z_internalRemoveFromSpouse(Spouse spouse);
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter);
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon);

}