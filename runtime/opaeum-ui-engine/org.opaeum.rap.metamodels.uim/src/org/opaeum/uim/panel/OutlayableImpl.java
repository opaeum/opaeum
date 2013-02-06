package org.opaeum.uim.panel;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OutlayableImpl extends EObjectImpl implements Outlayable {
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private Integer preferredHeight;
	private Integer preferredWidth;
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("preferredWidth").length()>0 ) {
			setPreferredWidth(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredWidth")));
		}
		if ( xml.getAttribute("preferredHeight").length()>0 ) {
			setPreferredHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredHeight")));
		}
		if ( xml.getAttribute("fillHorizontally").length()>0 ) {
			setFillHorizontally(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillHorizontally")));
		}
		if ( xml.getAttribute("fillVertically").length()>0 ) {
			setFillVertically(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillVertically")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public Boolean getFillHorizontally() {
		return this.fillHorizontally;
	}
	
	public Boolean getFillVertically() {
		return this.fillVertically;
	}
	
	public Integer getPreferredHeight() {
		return this.preferredHeight;
	}
	
	public Integer getPreferredWidth() {
		return this.preferredWidth;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setFillHorizontally(Boolean fillHorizontally) {
		this.fillHorizontally=fillHorizontally;
	}
	
	public void setFillVertically(Boolean fillVertically) {
		this.fillVertically=fillVertically;
	}
	
	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight=preferredHeight;
	}
	
	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth=preferredWidth;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}