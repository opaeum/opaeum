package net.sf.nakeduml.javageneration;

import java.io.CharArrayWriter;

import net.sf.nakeduml.textmetamodel.TextSource;

public class CharArrayTextSource implements TextSource {
	public enum OutputRootId {
		DOMAIN_GEN_RESOURCE,
		DOMAIN_TEST_RESOURCE,
		DOMAIN_GEN_TEST_RESOURCE,
		WEBAPP_RESOURCE,
		WEB_TEST_RESOURCE_JBOSSAS,
		WEB_TEST_RESOURCE,
		INTEGRATED_ADAPTOR_GEN_RESOURCE,
		ADAPTOR_GEN_RESOURCE,
		ADAPTOR_TEST_RESOURCE,
		ADAPTOR_TEST_RESOURCE_JBOSSAS,
		ADAPTOR_RESOURCE,
		INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS,
		INTEGRATED_ADAPTOR_RESOURCE,
		INTEGRATED_ADAPTOR_TEST_RESOURCE, ADAPTOR_GEN_TEST_RESOURCE, INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE
	};

	CharArrayWriter writer;

	public CharArrayTextSource(CharArrayWriter contentWriter) {
		this.writer = contentWriter;
	}

	public char[] toCharArray() {
		return writer.toCharArray();
	}

	public boolean hasContent() {
		return true;
	}
}
