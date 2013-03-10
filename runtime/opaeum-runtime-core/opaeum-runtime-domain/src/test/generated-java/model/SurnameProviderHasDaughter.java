package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
public class SurnameProviderHasDaughter implements CompositionNode, Serializable {
	static final private long serialVersionUID = 7081196718495376073l;
	protected Sister surnameCarryingDaughter;
	protected SurnameProvider surnameProvider;
	private String uid;

	/** Constructor for SurnameProviderHasDaughter
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SurnameProviderHasDaughter(Sister end1, SurnameProvider end2) {
		this.z_internalAddToSurnameCarryingDaughter(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Constructor for SurnameProviderHasDaughter
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SurnameProviderHasDaughter(SurnameProvider end2, Sister end1) {
		this.z_internalAddToSurnameCarryingDaughter(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Default constructor for SurnameProviderHasDaughter
	 */
	public SurnameProviderHasDaughter() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromSurnameProvider(getSurnameProvider());
		this.z_internalRemoveFromSurnameCarryingDaughter(getSurnameCarryingDaughter());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SurnameProviderHasDaughter ) {
			return other==this || ((SurnameProviderHasDaughter)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public CompositionNode getOwningObject() {
		return getSurnameProvider();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7054433913514256040l,opposite="surnameProviderHasDaughter_surnameProvider",uuid="Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA@Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	public Sister getSurnameCarryingDaughter() {
		Sister result = this.surnameCarryingDaughter;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8484398849814184104l,opposite="surnameProviderHasDaughter_surnameCarryingDaughter",uuid="Structures.uml@_gtKIkIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA@Structures.uml@_gtKIkIhrEeK4s7QGypAJBA")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = this.surnameProvider;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToSurnameProvider((SurnameProvider)owner);
	}
	
	public void markDeleted() {
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameCarryingDaughter") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7054433913514256040")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Sister surnameCarryingDaughter = (Sister)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameCarryingDaughter!=null ) {
							z_internalAddToSurnameCarryingDaughter(surnameCarryingDaughter);
							surnameCarryingDaughter.z_internalAddToSurnameProviderHasDaughter_surnameProvider(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8484398849814184104")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProvider surnameProvider = (SurnameProvider)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameProvider!=null ) {
							z_internalAddToSurnameProvider(surnameProvider);
							surnameProvider.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(this);
						}
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<SurnameProviderHasDaughter uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SurnameProviderHasDaughter ");
		sb.append("classUuid=\"Structures.uml@_gtNy8IhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.SurnameProviderHasDaughter\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getSurnameCarryingDaughter()==null ) {
			sb.append("\n<surnameCarryingDaughter/>");
		} else {
			sb.append("\n<surnameCarryingDaughter propertyId=\"7054433913514256040\">");
			sb.append("\n" + getSurnameCarryingDaughter().toXmlReferenceString());
			sb.append("\n</surnameCarryingDaughter>");
		}
		if ( getSurnameProvider()==null ) {
			sb.append("\n<surnameProvider/>");
		} else {
			sb.append("\n<surnameProvider propertyId=\"8484398849814184104\">");
			sb.append("\n" + getSurnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProvider>");
		}
		sb.append("\n</SurnameProviderHasDaughter>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter.equals(getSurnameCarryingDaughter()) ) {
			return;
		}
		this.surnameCarryingDaughter=surnameCarryingDaughter;
	}
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider) {
		if ( surnameProvider.equals(getSurnameProvider()) ) {
			return;
		}
		this.surnameProvider=surnameProvider;
	}
	
	public void z_internalRemoveFromSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( getSurnameCarryingDaughter()!=null && surnameCarryingDaughter!=null && surnameCarryingDaughter.equals(getSurnameCarryingDaughter()) ) {
			this.surnameCarryingDaughter=null;
			this.surnameCarryingDaughter=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider=null;
			this.surnameProvider=null;
		}
	}

}