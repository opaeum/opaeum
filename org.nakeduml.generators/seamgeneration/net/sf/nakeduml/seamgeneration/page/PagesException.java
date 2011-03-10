package net.sf.nakeduml.seamgeneration.page;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public class PagesException {
	private String clazz;
	private String viewId;
	private Severity severity;
	private String message;
	public PagesException(String clazz, String viewId, Severity severity, String message) {
		super();
		this.clazz = clazz;
		this.viewId = viewId;
		this.severity = severity;
		this.message = message;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void addTo(StringBuilder sb, int indent) {
		indent(sb,indent++);
		sb.append("<exception class=\"");
		sb.append(getClazz());
		sb.append("\">\n");
		indent(sb,indent++);
		sb.append("<redirect view-id=\"");
		sb.append(getViewId());
		sb.append("\">\n");
		indent(sb,indent--);
		sb.append("<message severity=\"");
		
		if (getSeverity().getOrdinal()==FacesMessage.SEVERITY_ERROR.getOrdinal()) {
			sb.append("error");
		} else if (getSeverity().getOrdinal()==FacesMessage.SEVERITY_FATAL.getOrdinal()) {
			sb.append("fatal");
		} else if (getSeverity().getOrdinal()==FacesMessage.SEVERITY_INFO.getOrdinal()) {
			sb.append("info");
		} else if (getSeverity().getOrdinal()==FacesMessage.SEVERITY_WARN.getOrdinal()) {
			sb.append("warn");
		} else {
			throw new IllegalStateException(getSeverity() + " not handled");
		}
		
		sb.append("\">");
		sb.append(getMessage());
		sb.append("</message>\n");
		indent(sb,indent--);
		sb.append("</redirect>\n");
		indent(sb,indent--);
		sb.append("</exception>\n");
	}
	
	private void indent(StringBuilder sb, Integer indent) {
		for (int i = 0; i < indent; i++) {
			sb.append("    ");
		}
	}	
}
