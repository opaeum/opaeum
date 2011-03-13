package net.sf.nakeduml.metamodel.workspace;


public class MappedType{
	private String qualifiedJavaName;
	public MappedType(String qualifiedJavaName){
		super();
		this.qualifiedJavaName = qualifiedJavaName;
	}
	public String getQualifiedJavaName(){
		return qualifiedJavaName;
	}
	public void setQualifiedJavaName(String qualifiedJavaName){
		this.qualifiedJavaName = qualifiedJavaName;
	}


}
