package net.sf.nakeduml.javageneration;

import java.io.CharArrayWriter;

import net.sf.nakeduml.textmetamodel.TextSource;

public class CharArrayTextSource implements TextSource {
	public static final String WAR_RESOURCE="war-resource";
	public static final String EJB_JAR_RESOURCE="jar-resource";
	public static final String EAR_RESOURCE="ear-resource";
	public static final String WEB_RESOURCE="web-resource";
	public static final String TEST_RESOURCE = "test-resource";
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
