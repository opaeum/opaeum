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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
public class SurnameProviderHasSon implements CompositionNode, Serializable {
	static final private long serialVersionUID = 1131610077658452273l;
	protected Brother surnameCarryingSon;
	protected SurnameProvider surnameProvider;
	private String uid;

	/** Constructor for SurnameProviderHasSon
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SurnameProviderHasSon(Brother end1, SurnameProvider end2) {
		this.z_internalAddToSurnameCarryingSon(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Constructor for SurnameProviderHasSon
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SurnameProviderHasSon(SurnameProvider end2, Brother end1) {
		this.z_internalAddToSurnameCarryingSon(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Default constructor for SurnameProviderHasSon
	 */
	public SurnameProviderHasSon() {
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
		this.z_internalRemoveFromSurnameCarryingSon(getSurnameCarryingSon());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SurnameProviderHasSon ) {
			return other==this || ((SurnameProviderHasSon)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public CompositionNode getOwningObject() {
		return getSurnameProvider();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984688315664840852l,opposite="surnameProviderHasSon_surnameProvider",uuid="Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA@Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	public Brother getSurnameCarryingSon() {
		Brother result = this.surnameCarryingSon;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4434103865371600604l,opposite="surnameProviderHasSon_surnameCarryingSon",uuid="Structures.uml@_g-UxEIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA@Structures.uml@_g-UxEIhrEeK4s7QGypAJBA")
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameCarryingSon") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("984688315664840852")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Brother surnameCarryingSon = (Brother)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameCarryingSon!=null ) {
							z_internalAddToSurnameCarryingSon(surnameCarryingSon);
							surnameCarryingSon.z_internalAddToSurnameProviderHasSon_surnameProvider(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4434103865371600604")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProvider surnameProvider = (SurnameProvider)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameProvider!=null ) {
							z_internalAddToSurnameProvider(surnameProvider);
							surnameProvider.z_internalAddToSurnameProviderHasSon_surnameCarryingSon(this);
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
		return "<SurnameProviderHasSon uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SurnameProviderHasSon ");
		sb.append("classUuid=\"Structures.uml@_g-YbcIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.SurnameProviderHasSon\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getSurnameCarryingSon()==null ) {
			sb.append("\n<surnameCarryingSon/>");
		} else {
			sb.append("\n<surnameCarryingSon propertyId=\"984688315664840852\">");
			sb.append("\n" + getSurnameCarryingSon().toXmlReferenceString());
			sb.append("\n</surnameCarryingSon>");
		}
		if ( getSurnameProvider()==null ) {
			sb.append("\n<surnameProvider/>");
		} else {
			sb.append("\n<surnameProvider propertyId=\"4434103865371600604\">");
			sb.append("\n" + getSurnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProvider>");
		}
		sb.append("\n</SurnameProviderHasSon>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( surnameCarryingSon.equals(getSurnameCarryingSon()) ) {
			return;
		}
		this.surnameCarryingSon=surnameCarryingSon;
	}
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider) {
		if ( surnameProvider.equals(getSurnameProvider()) ) {
			return;
		}
		this.surnameProvider=surnameProvider;
	}
	
	public void z_internalRemoveFromSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( getSurnameCarryingSon()!=null && surnameCarryingSon!=null && surnameCarryingSon.equals(getSurnameCarryingSon()) ) {
			this.surnameCarryingSon=null;
			this.surnameCarryingSon=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider=null;
			this.surnameProvider=null;
		}
	}

}