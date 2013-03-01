package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opaeum.ecore.EObject;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class AbstractUimResource{
	private EObject root;
	private String fileName;
	private UimResourceSet resourceSet;
	private HashMap<String,EObject> elementMap=new HashMap<String,EObject>();
	public AbstractUimResource(String fileName){
		this.fileName=fileName;
	}
	protected abstract boolean shouldReload();
	protected abstract InputStream createInputStream();
	public EObject getRoot(){
		checkInitialized();
		return root;
	}
	private void checkInitialized(){
		if(root==null || shouldReload()){
			root= readUimObject(createInputStream());
		}
	}
	public void putElement(Element e,EObject object){
		elementMap.put(e.getAttribute("xmi:id"), object);
	}
	public EObject getElement(String id){
		checkInitialized();
		return elementMap.get(id);
	}
	public EObject getElement(Element e){
		return elementMap.get(e.getAttribute("xmi:id"));
	}

	private EObject readUimObject(InputStream stream) {
		try{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(stream);
			doc.setDocumentURI(fileName);
			Element xml = doc.getDocumentElement();
			EObject e = UimInstantiator.INSTANCE.newInstance(xml.getNodeName());
			e.init(null, this, xml);
			e.buildTreeFromXml(xml);
			e.populateReferencesFromXml(xml);
			return e;
		}catch(ParserConfigurationException e){
			throw new RuntimeException(e);
		}catch(SAXException e){
			throw new RuntimeException(e);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public UimResourceSet getResourceSet(){
		return resourceSet;
	}
	public void setResourceSet(UimResourceSet resourceSet){
		this.resourceSet = resourceSet;
	}

}
