package net.sf.nakeduml.seamgeneration.jsf.source;

import java.util.Properties;

import javax.faces.component.UIViewRoot;

public class JsfMenuSource extends AbstractJsfSource {

	public JsfMenuSource(UIViewRoot uiViewRoot, int depthFromRoot, Properties namespaceProperties) {
		super(uiViewRoot, depthFromRoot, namespaceProperties);
	}

	@Override
	protected void startComposition() {
		stringBuilder.append("<f:view xmlns=\"http://www.w3.org/1999/xhtml\"\n");
		stringBuilder.append("    xmlns:s=\"http://jboss.com/products/seam/taglib\"\n");
		stringBuilder.append("    xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n");
		stringBuilder.append("    xmlns:f=\"http://java.sun.com/jsf/core\"\n");
		stringBuilder.append("    xmlns:h=\"http://java.sun.com/jsf/html\"\n");
		stringBuilder.append("    xmlns:rich=\"http://richfaces.org/rich\"\n");
		stringBuilder.append("    xmlns:p=\"http://primefaces.prime.com.tr/ui\"\n");
		stringBuilder.append("    xmlns:a=\"https://ajax4jsf.dev.java.net/ajax\">\n\n");
	}

	@Override
	protected void endComposition() {
		stringBuilder.append("</f:view>");
	}
}
