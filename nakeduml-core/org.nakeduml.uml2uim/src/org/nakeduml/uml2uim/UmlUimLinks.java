package org.nakeduml.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.UserInteractionModel;

public class UmlUimLinks{
	Map<UIMForm,Map<String,UmlUimLink>> linksForForms = new HashMap<UIMForm,Map<String,UmlUimLink>>();
	Map<String,UmlUimLink> linksForTheUiModel = new HashMap<String,UmlUimLink>();
	private class UmlUimLink{
		private UmlUimLink(Element umlElement){
			super();
			this.umlElement = umlElement;
		}
		private UmlUimLink(UmlReference uimElement){
			super();
			this.uimElements.add(uimElement);
		}
		Element umlElement;
		Collection<UmlReference> uimElements=new HashSet<UmlReference>();
	}
	public Element getUmlElement(UIMForm form,UmlReference uimElement){
		UmlUimLink link = getLink(form, uimElement);
		return link.umlElement;
	}
	public void putLinkForForm(UIMForm form,UmlReference uimElement){
		getLink(form, uimElement).uimElements.add(uimElement);
	}
	public void putLinkForModel(Namespace umlElement){
		UmlUimLink link = getLinkForUiModel(umlElement);
		link.umlElement = umlElement;
	}
	private UmlUimLink getLinkForUiModel(Namespace umlElement){
		UmlUimLink link = linksForTheUiModel.get(getId(umlElement));
		if(link == null){
			link = new UmlUimLink(umlElement);
			linksForTheUiModel.put(getId(umlElement), link);
		}
		return link;
	}
	private UmlUimLink getLinkForUiModel(UmlReference umlElement){
		UmlUimLink link = linksForTheUiModel.get(getId(umlElement));
		if(link == null){
			link = new UmlUimLink(umlElement);
			linksForTheUiModel.put(getId(umlElement), link);
		}
		return link;
	}
	public Collection<UmlReference> getUimElements(UIMForm form,Element umlElement){
		UmlUimLink link = getLink(form, umlElement);
		return link.uimElements;
	}
	public Collection<UmlReference> getUimElementsForUiModel(Namespace umlElement){
		UmlUimLink link = getLinkForUiModel(umlElement);
		return link.uimElements;
	}
	private UmlUimLink getLink(UIMForm form,UmlReference uimElement){
		Map<String,UmlUimLink> elementMap = getLinksForForm(form);
		String id = getId(uimElement);
		UmlUimLink link = elementMap.get(id);
		if(link == null){
			link = new UmlUimLink(uimElement);
			elementMap.put(id, link);
		}
		return link;
	}
	private UmlUimLink getLink(UIMForm form,Element umlElement){
		Map<String,UmlUimLink> elementMap = getLinksForForm(form);
		String id = getId(umlElement);
		UmlUimLink link = elementMap.get(id);
		if(link == null){
			link = new UmlUimLink(umlElement);
			elementMap.put(id, link);
		}
		return link;
	}
	String getId(Element umlElement){
		String s = umlElement.eResource().getURIFragment(umlElement);
		char[] a = s.toCharArray();
		for(int i = 0;i < a.length;i++){
			if(!Character.isJavaIdentifierPart(a[i])){
				a[i]='_';
			}
			
		}
		return new String(a);
	}
	private String getId(UmlReference uimElement){
		return uimElement.getUmlElementUid();
	}
	private Map<String,UmlUimLink> getLinksForForm(UIMForm form){
		Map<String,UmlUimLink> elementMap = linksForForms.get(form);
		if(elementMap == null){
			elementMap = new HashMap<String,UmlUimLinks.UmlUimLink>();
			linksForForms.put(form, elementMap);
		}
		return elementMap;
	}
	public void putLinkForForm(UIMForm form,Element umlElement){
		getLink(form, umlElement).umlElement = umlElement;
	}
	public Collection<UmlReference> getBrokenLinks(UIMForm form){
		Collection<UmlUimLink> values = getLinksForForm(form).values();
		Collection<UmlReference> result = new ArrayList<UmlReference>();
		for(UmlUimLink umlUimLink:values){
			if(umlUimLink.umlElement == null){
				result.addAll(umlUimLink.uimElements);
			}
		}
		return result;
	}
	public void putLinkForModel(UIMForm sf){
		getLinkForUiModel((UmlReference)sf).uimElements.add((UmlReference) sf);
		
	}
}
