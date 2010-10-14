package net.sf.nakeduml.seamgeneration.jsf.source;

import java.util.Properties;

public class JsfSource extends AbstractJsfSource {

	private String menu;
	private String body;

	public JsfSource(String menu, String body, int depthFromRoot, Properties namespaceProperties) {
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
		stringBuilder.append("<!DOCTYPE composition PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n");
		stringBuilder.append("    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		stringBuilder.append("<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"\n");
		stringBuilder.append("    xmlns:s=\"http://jboss.com/products/seam/taglib\"\n");
		stringBuilder.append("    xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n");
		stringBuilder.append("    xmlns:f=\"http://java.sun.com/jsf/core\"\n");
		stringBuilder.append("    xmlns:h=\"http://java.sun.com/jsf/html\"\n");
		stringBuilder.append("    xmlns:rich=\"http://richfaces.org/rich\"\n");
		stringBuilder.append("    xmlns:a=\"https://ajax4jsf.dev.java.net/ajax\"\n");
		stringBuilder.append("    xmlns:p=\"http://primefaces.prime.com.tr/ui\"\n");
		stringBuilder.append("    template=\"");
		stringBuilder.append("/layout/crud-template.xhtml\">\n\n");

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
		stringBuilder.append("</ui:composition>");
	}

	@Override
	protected void endComposition() {
	}
}
