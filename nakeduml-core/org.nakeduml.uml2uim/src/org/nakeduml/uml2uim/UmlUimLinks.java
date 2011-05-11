package org.nakeduml.uml2uim;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMForm;
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
		private UmlUimLink(UserInteractionElement uimElement){
			super();
			this.uimElement = uimElement;
		}
		Element umlElement;
		UserInteractionElement uimElement;
	}
	public Element getUmlElement(UIMForm form,UserInteractionElement uimElement){
		UmlUimLink link = getLink(form, uimElement);
		return link.umlElement;
	}
	public void putLinkForForm(UIMForm form,UserInteractionElement uimElement){
		getLink(form, uimElement).uimElement=uimElement;
	}
	public void putLinkForModel(Namespace umlElement){
		UmlUimLink link = getLinkForUiModel(umlElement);
		link.umlElement=umlElement;
	}
	private UmlUimLink getLinkForUiModel(Namespace umlElement){
		UmlUimLink link = linksForTheUiModel.get(getId(umlElement));
		if(link==null){
			link=new UmlUimLink(umlElement);
			linksForTheUiModel.put(getId(umlElement),link);
		}
		return link;
	}
	public  UserInteractionElement getUimElement(UIMForm form,Element umlElement){
		UmlUimLink link = getLink(form, umlElement);
		return link.uimElement;
	}
	private  UmlUimLink getLink(UIMForm form,UserInteractionElement uimElement){
		Map<String,UmlUimLink> elementMap = getLinksForForm(form);
		String id = getId(uimElement);
		UmlUimLink link = elementMap.get(id);
		if(link==null){
			link=new UmlUimLink(uimElement);
			elementMap.put(id, link);
		}
		return link;
	}
	private  UmlUimLink getLink(UIMForm form,Element umlElement){
		Map<String,UmlUimLink> elementMap = getLinksForForm(form);
		String id = getId(umlElement);
		UmlUimLink link = elementMap.get(id);
		if(link==null){
			link=new UmlUimLink(umlElement);
			elementMap.put(id, link);
		}
		return link;
	}
	private  String getId(Element umlElement){
		return umlElement.eResource().getURIFragment(umlElement);
	}
	private  String getId(UserInteractionElement uimElement){
		if(uimElement instanceof UIMBinding){
			return ((UIMBinding) uimElement).getElement().toString();
		}
		return uimElement.getName();
	}
	private  Map<String,UmlUimLink> getLinksForForm(UIMForm form){
		Map<String,UmlUimLink> elementMap = linksForForms.get(form);
		if(elementMap==null){
			elementMap = new HashMap<String,UmlUimLinks.UmlUimLink>();
			linksForForms.put(form, elementMap);			
		}
		return elementMap;
	}
	public void putLinkForForm(UIMForm form,Element umlElement){
		getLink(form, umlElement).umlElement=umlElement;
		
	}
}
