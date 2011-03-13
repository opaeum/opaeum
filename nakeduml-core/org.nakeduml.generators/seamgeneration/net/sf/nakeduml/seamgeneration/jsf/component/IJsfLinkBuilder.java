package net.sf.nakeduml.seamgeneration.jsf.component;

import org.ajax4jsf.component.AjaxActionComponent;
import org.jboss.seam.ui.component.html.HtmlFragment;

public interface IJsfLinkBuilder {
	HtmlFragment createUILink();
	AjaxActionComponent createComponent();
}
