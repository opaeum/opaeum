package org.opaeum.ecore;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.uim.UimInstantiator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UimReader{
	public static void main(String[] args) throws Exception{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(new File("/home/ampie/NewWorkspaces/opaeum/examples/org.opaeum.demo1.app/ui/perspective.uim"));
		Element xml = doc.getDocumentElement();
		EObject e = UimInstantiator.INSTANCE.newInstance(xml.getNodeName());
		HashMap<String,Object> map = new HashMap<String,Object>();
		System.out.println(xml.getAttributeNS("xmi", "id"));
		map.put(xml.getAttribute("xmi:id"), e);
		e.buildTreeFromXml(xml, map);
		e.populateReferencesFromXml(xml, map);
		Set<EObject> printed = new HashSet<EObject>();
		show("", e, printed);
	}
	protected static void show(String padding,EObject e,Set<EObject> printed){
		printed.add(e);
		for(PropertyDescriptor pd:IntrospectionUtil.getProperties(e.getClass())){
			Object object = IntrospectionUtil.get(pd, e);
			if(object instanceof EObject){
				if(printed.contains(object)){
					System.out.println(padding + pd.getName() + "=" + ((EObject) object).getUid());
				}else{
					System.out.println(padding + pd.getName() + "=");
					show(padding + "  ", (EObject) object, printed);
				}
			}else if(object instanceof Collection){
				System.out.println(padding + pd.getName() + "=");
				Collection<EObject> ch = (Collection<EObject>) object;
				for(EObject eObject:ch){
					show(padding + "  ", eObject, printed);
				}
			}else{
				System.out.println(padding + pd.getName() + "=" + object);
			}
		}
	}
}
