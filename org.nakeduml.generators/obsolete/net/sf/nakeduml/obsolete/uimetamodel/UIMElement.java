package net.sf.nakeduml.obsolete.uimetamodel;
import java.util.HashMap;
import java.util.Map;
public abstract class UIMElement {
	private String javaName;
	private boolean hasDocumentation;
	private int nakedUmlId;
	private String idInModel;
	private Map<String, UIMMetaElement> metaData = new HashMap<String, UIMMetaElement>();
	public abstract UIMElement getOwnerElement();
	public String getJavaName() {
		return this.javaName;
	}
	public final boolean getHasDocumentation() {
		return this.hasDocumentation;
	}
	public final int getNakedUmlId() {
		return this.nakedUmlId;
	}
	public final Map<String, UIMMetaElement> getMetaData() {
		return this.metaData;
	}
	public final String getTaggedValue(String string, String string2) {
		return null;
	}
	public String getQualifiedJavaName() {
		return getOwnerElement().getQualifiedJavaName() + "." + getJavaName();
	}
	public final String getIdInModel() {
		return this.idInModel;
	}
}
