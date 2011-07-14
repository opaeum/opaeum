package org.nakeduml.jsf.pagebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.config.view.View;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PreRenderView;

@Named("jsfPageBean")
@ViewAccessScoped
@View(Pagebeans.PageBeans.class)
public class JsfPageBean implements Serializable {

	private static final long serialVersionUID = -4569536260426836660L;
	List<String> names;
	String nameToAdd;

	@PostConstruct
	public void init() {
		System.out.println("JsfPageBean.PostConstruct");
		names = new ArrayList<String>();
		names.add("first");
		names.add("second");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("JsfPageBean.PreDestroy");
	}

	public void addNameToList() {
		names.add(nameToAdd);
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getNameToAdd() {
		return nameToAdd;
	}

	public void setNameToAdd(String nameToAdd) {
		this.nameToAdd = nameToAdd;
	}
	
	@PreRenderView
	protected void preRenderView() {
		System.out.println("preRenderView");
	}
}
