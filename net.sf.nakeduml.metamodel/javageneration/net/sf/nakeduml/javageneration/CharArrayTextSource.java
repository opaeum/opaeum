package net.sf.nakeduml.javageneration;

import java.io.CharArrayWriter;

import net.sf.nakeduml.textmetamodel.TextSource;

public class CharArrayTextSource implements TextSource {
	public static final String TEST_RESOURCE = "test-resource";
	public static final String TEST_RESOURCE_JBOSSAS = "test-resource-jbossas";
	public static final String WEBAPP_RESOURCE = "webapp-resource";
	CharArrayWriter writer;

	public CharArrayTextSource(CharArrayWriter contentWriter) {
		this.writer = contentWriter;
	}

	public char[] toCharArray() {
		return writer.toCharArray();
	}
	public boolean hasContent(){
		return true;
	}

}
