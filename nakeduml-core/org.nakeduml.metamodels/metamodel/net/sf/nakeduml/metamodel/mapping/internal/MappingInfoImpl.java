package net.sf.nakeduml.metamodel.mapping.internal;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MappingInfoImpl extends AbstractMappingInfo {
	private static final long serialVersionUID = -3340080927536252411L;
	private static final String DEL = "~";
	private String idInModel;
	private Integer sinceRevision;
	private Float sinceVersion;
	private Integer nakedUmlId;
	private String qualifiedJavaName;
	private String javaNameString;
	private String sqlNameString;
	private String singularHumanName;
	private String pluralHumanName;
	private String qualifiedUmlName;

	public String getIdInModel() {
		return idInModel;
	}

	public void setIdInModel(String idInModel) {
		this.idInModel = idInModel;
	}

	public Integer getSinceRevision() {
		return sinceRevision;
	}

	public void setSinceRevision(Integer sinceRevision) {
		this.sinceRevision = sinceRevision;
	}

	public Float getSinceVersion() {
		return sinceVersion;
	}

	public void setSinceVersion(Float sinceVersion) {
		this.sinceVersion = sinceVersion;
	}

	public Integer getNakedUmlId() {
		return nakedUmlId;
	}

	public void setNakedUmlId(Integer nakedUmlId) {
		this.nakedUmlId = nakedUmlId;
	}

	public String getQualifiedJavaName() {
		return qualifiedJavaName;
	}
	public void setQualifiedJavaName(String qualifiedJavaName) {
		this.qualifiedJavaName = qualifiedJavaName;
	}

	public String getJavaNameString() {
		return javaNameString;
	}

	public void setJavaNameString(String javaNameString) {
		this.javaNameString = javaNameString;
	}

	public String getSqlNameString() {
		return sqlNameString;
	}

	public void setSqlNameString(String sqlNameString) {
		this.sqlNameString = sqlNameString;
	}

	public String getSingularHumanName() {
		return singularHumanName;
	}

	public void setSingularHumanName(String singularHumanName) {
		this.singularHumanName = singularHumanName;
	}

	public String getPluralHumanName() {
		return pluralHumanName;
	}

	public void setPluralHumanName(String pluralHumanName) {
		this.pluralHumanName = pluralHumanName;
	}

	public String getQualifiedUmlName() {
		return qualifiedUmlName;
	}

	public void setQualifiedUmlName(String qualifiedUmlName) {
		this.qualifiedUmlName = qualifiedUmlName;
	}

	public MappingInfoImpl(String idInModel, String values) {
		this.idInModel = idInModel;
		Scanner scanner = new Scanner(values).useDelimiter(DEL);
		this.sinceRevision = scanner.nextInt();
		this.sinceVersion = scanner.nextFloat();
		this.nakedUmlId = scanner.nextInt();
		this.qualifiedJavaName = scanner.next();
		this.javaNameString = scanner.next();
		this.sqlNameString = scanner.next();
		this.singularHumanName = scanner.next();
		this.pluralHumanName = scanner.next();
		this.qualifiedUmlName = scanner.next();

	}

	public MappingInfoImpl() {
	}



	public String toString() {
		return "" + sinceRevision + DEL + new DecimalFormat("#0.0000000").format(sinceVersion) + DEL + nakedUmlId + DEL + qualifiedJavaName
				+ DEL + javaNameString + DEL + sqlNameString + DEL + singularHumanName + DEL + pluralHumanName + DEL + qualifiedUmlName
				+ DEL;
	}
	public static void main(String[] args) {
		String values = "12~1.3213~1~qualifiedJavaName~javaNameString~sqlNameString~SingularHumanName~pluralHumanName~qualifiedUmlName";
		MappingInfoImpl m1 = new MappingInfoImpl("",values);
		System.out.println(new MappingInfoImpl("", m1.toString()).toString().equals(m1.toString()));
	}

	@Override
	protected AbstractMappingInfo createCopy() {
		return new MappingInfoImpl();
	}

}
