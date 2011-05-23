package net.sf.nakeduml.metamodel.mapping.internal;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MappingInfoImpl extends AbstractMappingInfo{
	private static final long serialVersionUID = -3340080927536252411L;
	private static final String DEL = "~";
	private String idInModel;
	private Integer sinceRevision;
	private Float sinceVersion;
	private Integer nakedUmlId;
	private String persitentNameString;
	private String qualifiedPersistentName;
	public String getIdInModel(){
		return idInModel;
	}
	public void setIdInModel(String idInModel){
		this.idInModel = idInModel;
	}
	public Integer getSinceRevision(){
		return sinceRevision;
	}
	public void setSinceRevision(Integer sinceRevision){
		this.sinceRevision = sinceRevision;
	}
	public Float getSinceVersion(){
		return sinceVersion;
	}
	public void setSinceVersion(Float sinceVersion){
		this.sinceVersion = sinceVersion;
	}
	public Integer getNakedUmlId(){
		return nakedUmlId;
	}
	public void setNakedUmlId(Integer nakedUmlId){
		this.nakedUmlId = nakedUmlId;
	}
	public String getSqlNameString(){
		return persitentNameString;
	}
	public void setSqlNameString(String sqlNameString){
		this.persitentNameString = sqlNameString;
	}
	public MappingInfoImpl(String idInModel,String values){
		this.idInModel = idInModel;
		Scanner scanner = new Scanner(values).useDelimiter(DEL);
		this.sinceRevision = scanner.nextInt();
		this.sinceVersion = scanner.nextFloat();
		this.nakedUmlId = scanner.nextInt();
		this.persitentNameString = scanner.next();
		this.qualifiedPersistentName = scanner.next();
	}
	public MappingInfoImpl(){
	}
	public String toString(){
		return "" + sinceRevision + DEL + new DecimalFormat("#0.0000000").format(sinceVersion) + DEL + nakedUmlId + DEL + persitentNameString + DEL + DEL
				+ qualifiedPersistentName;
	}
	public static void main(String[] args){
		String values = "12~1.3213~1~qualifiedJavaName~javaNameString~sqlNameString~SingularHumanName~pluralHumanName~qualifiedUmlName~qualifiedPersistentName";
		MappingInfoImpl m1 = new MappingInfoImpl("", values);
		System.out.println(new MappingInfoImpl("", m1.toString()).toString().equals(m1.toString()));
	}
	@Override
	protected AbstractMappingInfo createCopy(){
		return new MappingInfoImpl();
	}
	@Override
	public String getQualifiedPersistentName(){
		return qualifiedPersistentName;
	}
	@Override
	public void setQualifiedPersistentName(String string){
		this.qualifiedPersistentName = string;
	}
}
