package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UimInstantiator;
import org.opaeum.uim.UserInteractionElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class AbstractUimResourceWrapper{
	private EObject root;
	protected abstract boolean shouldReload();
	protected abstract InputStream createInputStream();
	public EObject getRoot(){
		if(root==null || shouldReload()){
			root= readUimObject(createInputStream());
		}
		return root;
	}
	private EObject readUimObject(InputStream stream) {
		try{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(stream);
			Element xml = doc.getDocumentElement();
			EObject e = UimInstantiator.INSTANCE.newInstance(xml.getNodeName());
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put(xml.getAttribute("xmi:id"), e);
			e.buildTreeFromXml(xml, map);
			e.populateReferencesFromXml(xml, map);
			return e;
		}catch(ParserConfigurationException e){
			throw new RuntimeException(e);
		}catch(SAXException e){
			throw new RuntimeException(e);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}

}
