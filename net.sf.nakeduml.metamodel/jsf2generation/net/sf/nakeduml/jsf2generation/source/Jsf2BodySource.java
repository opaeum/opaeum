package net.sf.nakeduml.jsf2generation.source;

import java.util.Properties;

import javax.faces.component.UIViewRoot;

public class Jsf2BodySource extends AbstractJsf2Source {

	public Jsf2BodySource(UIViewRoot uiViewRoot, int depthFromRoot, Properties namespaceProperties) {
		super(uiViewRoot, depthFromRoot, namespaceProperties);
	}

	@Override
	protected void startComposition() {
		stringBuilder.append("<f:view xmlns=\"http://www.w3.org/1999/xhtml\"\n");
		stringBuilder.append("	xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n");
		stringBuilder.append("	xmlns:h=\"http://java.sun.com/jsf/html\"\n");
		stringBuilder.append("	xmlns:f=\"http://java.sun.com/jsf/core\"\n");
		stringBuilder.append("	xmlns:a4j=\"http://richfaces.org/a4j\">\n");
	}

	@Override
	protected void endComposition() {
		stringBuilder.append("</f:view>");
	}

}
