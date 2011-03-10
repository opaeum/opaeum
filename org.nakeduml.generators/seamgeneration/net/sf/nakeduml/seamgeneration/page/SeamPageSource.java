package net.sf.nakeduml.seamgeneration.page;

import java.util.List;

import net.sf.nakeduml.textmetamodel.TextSource;

import org.jboss.seam.navigation.Page;

public class SeamPageSource extends AbstractSeamPagesSource implements TextSource{
	
	private StringBuffer applicationPages = new StringBuffer();
	List<PagesException> exceptions;
	
	public SeamPageSource(Page page,List<PagesException> exceptions){
		super(page);
		this.exceptions = exceptions;
	}
	
	public StringBuffer getApplicationPages() {
		return applicationPages;
	}
	
	public void setApplicationPages(StringBuffer applicationPages) {
		this.applicationPages = applicationPages;
	}
	
	protected void startComposition(){
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuilder.append("<pages xmlns=\"http://jboss.com/products/seam/pages\"\n");
		stringBuilder.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		stringBuilder.append("    xsi:schemaLocation=\"http://jboss.com/products/seam/pages http://jboss.com/products/seam/pages-2.1.xsd\"\n");
		stringBuilder.append("    no-conversation-view-id=\"/home.xhtml\"\n");
		stringBuilder.append("    login-view-id=\"/login.xhtml\">\n\n");
		
		stringBuilder.append("    <conversation name=\"RoleConversation\"\n");
		stringBuilder.append("         parameter-name=\"roleName\"\n");
		stringBuilder.append("         parameter-value=\"#{role.roleNameForSecurity}\"/>\n\n");		
		
		stringBuilder.append("    <page view-id=\"*\">\n");
	}
	protected void endComposition(){
		composeExceptions();
		stringBuilder.append("        <exception class=\"org.jboss.seam.ConcurrentRequestTimeoutException\" log-level=\"trace\">\n");
		stringBuilder.append("            <http-error error-code=\"503\" />\n");
		stringBuilder.append("        </exception>\n");
		stringBuilder.append("        <exception>\n");
		stringBuilder.append("            <redirect view-id=\"/error.xhtml\">\n");
		stringBuilder.append("                <message severity=\"error\">Unexpected error, please try again</message>\n");
		stringBuilder.append("            </redirect>\n");
		stringBuilder.append("        </exception>\n");
		stringBuilder.append("</pages>");
	}
	private void composeExceptions(){
		for(PagesException pagesException:exceptions){
			pagesException.addTo(stringBuilder, 2);
		}
	}
	public char[] toCharArray(){
		startComposition();
		outEvents();
		outActions();
		outBeginConversation();
		outEndConversation();
		outNavigations();
		outApplicationPageXml();
		stringBuilder.append("    </page>\n");
		endComposition();
		return stringBuilder.toString().toCharArray();
	}
	
	private void outApplicationPageXml() {
		stringBuilder.append(applicationPages);
	}

	public boolean hasContent(){
		return true;
	}
}
