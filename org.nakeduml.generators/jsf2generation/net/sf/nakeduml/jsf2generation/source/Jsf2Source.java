package net.sf.nakeduml.jsf2generation.source;

import java.util.Properties;

public class Jsf2Source extends AbstractJsf2Source {

	private String menu;
	private String body;

	public Jsf2Source(String menu, String body, int depthFromRoot, Properties namespaceProperties) {
		super(depthFromRoot, namespaceProperties);
		this.menu = menu;
		this.body = body;
	}

	@Override
	public char[] toCharArray() {
		startComposition();
		return stringBuilder.toString().toCharArray();
	}

	@Override
	protected void startComposition() {
		
		stringBuilder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		stringBuilder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n");
		stringBuilder.append("	xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n");
		stringBuilder.append("	xmlns:h=\"http://java.sun.com/jsf/html\"\n");
		stringBuilder.append("	xmlns:f=\"http://java.sun.com/jsf/core\">\n");
		
		stringBuilder.append("	<ui:composition template=\"/crud-template.xhtml\">\n");
		stringBuilder.append("    <ui:define name=\"menu\">\n");
		stringBuilder.append("        <ui:include src=\"");
		stringBuilder.append(this.menu);
		stringBuilder.append(".menu.xhtml\" />\n");
		stringBuilder.append("    </ui:define>\n");

		stringBuilder.append("    <ui:define name=\"body\">\n");
		stringBuilder.append("        <ui:include src=\"");
		stringBuilder.append(this.body);
		stringBuilder.append(".body.xhtml\" />\n");
		stringBuilder.append("    </ui:define>\n");
		stringBuilder.append("	</ui:composition>\n");
		
		stringBuilder.append("</html>");
	}

	@Override
	protected void endComposition() {
	}
}
