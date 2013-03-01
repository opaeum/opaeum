package org.opaeum.uim.cube;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MeasurePropertyImpl extends EObjectImpl implements MeasureProperty {
	private AggregationFormula aggregationFormula;
	private String umlElementUid;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
		}
		if ( xml.getAttribute("aggregationFormula").length()==0 ) {
			setAggregationFormula(AggregationFormula.values()[0]);
		} else {
			setAggregationFormula(AggregationFormula.getByName(xml.getAttribute("aggregationFormula")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public AggregationFormula getAggregationFormula() {
		return this.aggregationFormula;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setAggregationFormula(AggregationFormula aggregationFormula) {
		this.aggregationFormula=aggregationFormula;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public String toString() {
		String result = null;
		
		return result;
	}

}