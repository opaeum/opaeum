package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
public class UIMClassifier extends UIMElement implements Serializable {
	private static final long serialVersionUID = 3006745944074671221L;
	private String styleClassName;
	private boolean isSimpleDataType;
	private boolean isEnum;
	private UIMPackage uimPackage;
	public UIMClassifier() {
	}
	public String getStyleClassName() {
		return this.styleClassName;
	}
	public boolean isNumeric() {
		return getQualifiedJavaName().equals("int") || getQualifiedJavaName().indexOf("Integer") > -1 || isDecimal();
	}
	public boolean isDecimal() {
		return getQualifiedJavaName().equals("double") || getQualifiedJavaName().indexOf("Double") > -1
				|| getQualifiedJavaName().indexOf("Decimal") > -1;
	}
	public boolean isSimpleDataType() {
		return this.isSimpleDataType;
	}
	public boolean isEnum() {
		return this.isEnum;
	}
	public final void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}
	public final void setSimpleDataType(boolean isSimpleDataType) {
		this.isSimpleDataType = isSimpleDataType;
	}
	@Override
	public UIMElement getOwnerElement() {
		return this.uimPackage;
	}
}
