package net.sf.nakeduml.name;
import junit.framework.TestCase;
public class NameConverterTest extends TestCase {
	public NameConverterTest(String name) {
		super(name);
	}
	public void testIt() throws Exception {
		assertEquals("abc_dfg", NameConverter.toUnderscoreStyle("abcDfg"));
		assertEquals("abc_dfg", NameConverter.toUnderscoreStyle("ABCDfg"));
		assertEquals("abc_dfg", NameConverter.toUnderscoreStyle("abcDFG"));
		assertEquals("abc_dfg", NameConverter.toUnderscoreStyle("AbcDFG"));
		assertEquals("abcDfg", NameConverter.underscoredToCamelCase("abc_dfg"));
		assertEquals("abcDfg", NameConverter.underscoredToCamelCase("abc_dfg_"));
		assertEquals("abcDfg", NameConverter.underscoredToCamelCase("_abc_dfg"));
		assertEquals("abcDfg", NameConverter.underscoredToCamelCase("____abc___dfg"));
		assertEquals("abcDfg", NameConverter.underscoredToCamelCase("AbcDfg"));
		assertEquals("Abc dfg", NameConverter.separateWords("AbcDfg"));
		assertEquals("Abc dfg", NameConverter.separateWords("Abc_dfg"));
	}
}
