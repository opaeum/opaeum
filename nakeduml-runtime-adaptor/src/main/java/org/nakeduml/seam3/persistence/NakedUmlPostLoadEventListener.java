package org.nakeduml.seam3.persistence;

import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.jboss.seam.persistence.InjectionEventListener;

public class NakedUmlPostLoadEventListener implements PostLoadEventListener {

	private static final long serialVersionUID = 7104726399040734182L;
	private InjectionEventListener injectionEventListener;
	
	public NakedUmlPostLoadEventListener() {
		super();
		injectionEventListener = new InjectionEventListener();
	}

	@Override
	public void onPostLoad(PostLoadEvent event) {
		injectionEventListener.load(event.getEntity());
	}

}
