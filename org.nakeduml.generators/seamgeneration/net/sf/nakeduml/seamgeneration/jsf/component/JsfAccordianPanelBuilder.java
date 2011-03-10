package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIViewRoot;

import net.sf.nakeduml.userinteractionmetamodel.ParticipationGroup;
import net.sf.nakeduml.userinteractionmetamodel.UserInteraction;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.tabview.Tab;

public class JsfAccordianPanelBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	
	public JsfAccordianPanelBuilder(UIViewRoot jsfMenu) {
	}

	public AccordionPanel getAccordion() {
		return new AccordionPanel();
	}
	
//	public Tab getAccordionTab(Map<ParticipationGroup, List<Tab>> tabs, UserInteraction ui) {
//		if (tabs.get(ui.getFolder().getName())!=null) {
//			return tabs.get(ui.getFolder().getName());
//		}
//		Tab accordionTab = new Tab();
//		accordionTab.setId(ui.getFolder().getName());
//		accordionTab.setTitle(ui.getFolder().getName());
//		tabs.put(ui.getFolder().getName(), accordionTab);
//		setSettedAttributes(accordionTab, "title");
//		return accordionTab;
//	}
	
	public Tab getAccordionTab(Map<ParticipationGroup, Tab> tabs, ParticipationGroup pg) {
		if (tabs.get(pg)!=null) {
			return tabs.get(pg);
		}
		Tab accordionTab = new Tab();
		accordionTab.setId(pg.getName());
		accordionTab.setTitle("#{messages['"+pg.getDisplayName()+"']}");
		tabs.put(pg, accordionTab);
		setSettedAttributes(accordionTab, "title");
		return accordionTab;
	}	
	
	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}	
	
}
