package net.sf.nakeduml.jsf2generation.component.dummy;

import javax.faces.component.UIComponentBase;

public class AjaxSupport extends UIComponentBase {

	private String event;
	private String render;
	private String oncomplete;
	
	public String getOncomplete() {
		return oncomplete;
	}

	public void setOncomplete(String oncomplete) {
		this.oncomplete = oncomplete;
	}

	public String getRender() {
		return render;
	}

	public void setRender(String render) {
		this.render = render;
	}

	@Override
	public String getFamily() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
