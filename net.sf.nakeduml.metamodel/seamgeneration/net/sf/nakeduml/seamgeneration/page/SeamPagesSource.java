package net.sf.nakeduml.seamgeneration.page;

import net.sf.nakeduml.textmetamodel.TextSource;

import org.jboss.seam.navigation.Page;

public class SeamPagesSource extends AbstractSeamPagesSource implements TextSource {
	
	public SeamPagesSource(Page page) {
		super(page);
	}

	protected void startComposition() {
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuilder.append("<page xmlns=\"http://jboss.com/products/seam/pages\"\n");
		stringBuilder.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		stringBuilder.append("    xsi:schemaLocation=\"http://jboss.com/products/seam/pages http://jboss.com/products/seam/pages-2.1.xsd\"\n");
		stringBuilder.append("    conversation=\"RoleConversation\">\n\n");
		
		stringBuilder.append("    <description>");
		stringBuilder.append(page.getDescription());
		stringBuilder.append("</description>\n\n");
	}

	protected void endComposition() {
		stringBuilder.append("</page>");
	}

	public char[] toCharArray() {
		startComposition();
		outEvents();
		outActions();
		outBeginConversation();
		outEndConversation();
		outNavigations();
		endComposition();
		return stringBuilder.toString().toCharArray();
	}
	public boolean hasContent(){
		return true;
	}
	
}
