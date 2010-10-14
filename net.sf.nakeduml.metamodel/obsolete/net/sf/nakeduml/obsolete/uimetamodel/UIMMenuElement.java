package net.sf.nakeduml.obsolete.uimetamodel;


public abstract interface UIMMenuElement{
	public abstract UIMEntity getResultingType();
	public abstract UIMEntityUserInteraction getResultingUserInteraction();
	public abstract Double getDisplayIndex();
	public abstract String getJavaName();
	public abstract boolean getHasDocumentation();
	public abstract int getNakedUmlId();
	public abstract String getMetaClass();
	public abstract String getIdInModel();
	public abstract SecureUserAction getSecurityOnVisibility();
	public abstract String getTaggedValue(String string,String string2);
}
