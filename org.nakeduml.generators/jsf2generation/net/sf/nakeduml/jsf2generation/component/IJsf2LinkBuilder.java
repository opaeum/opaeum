package net.sf.nakeduml.jsf2generation.component;

import javax.faces.component.UICommand;

import org.jboss.seam.ui.component.html.HtmlFragment;

public interface IJsf2LinkBuilder {
	HtmlFragment createUILink();
	UICommand createComponent();
}
