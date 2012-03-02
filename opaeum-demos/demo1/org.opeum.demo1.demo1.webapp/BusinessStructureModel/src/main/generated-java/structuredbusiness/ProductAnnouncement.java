package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_o9aQgGCfEeG6xvYqJACneg")
public class ProductAnnouncement implements ISignal, Serializable {
	static final private long serialVersionUID = 4518636647403222403l;
	private String uid;

	/** Default constructor for ProductAnnouncement
	 */
	public ProductAnnouncement() {
	}

	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ProductAnnouncement ) {
			return other==this || ((ProductAnnouncement)other).getUid().equals(this.getUid());
		}
		return false;
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
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<ProductAnnouncement uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ProductAnnouncement ");
		sb.append("classUuid=\"914890@_o9aQgGCfEeG6xvYqJACneg\" ");
		sb.append("className=\"structuredbusiness.ProductAnnouncement\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</ProductAnnouncement>");
		return sb.toString();
	}

}