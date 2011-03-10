package net.sf.nakeduml.obsolete.uimetamodel;
public abstract class UIMTypedElementParticipation extends UIMFeatureParticipation {
	public abstract boolean canReceiveInput();
	public abstract boolean isRequired();
	public abstract boolean isReadOnly();
	public String getInputComponentAttributes() {
		
		return null;
	}
	public String getConverterClassName() {
		
		return null;
	}
	public String getInputComponentClassName() {
		
		return null;
	}
	public boolean isEditable() {
		
		return false;
	}
	public final UIMTypedElement getTypedElement() {
		return (UIMTypedElement) getParticipatingElement();
	}
	@Override
	public String getQualifiedJavaName() {
		return getTypedElement().getQualifiedJavaName();
	}
	@Override
	public String getJavaName() {
		return getTypedElement().getJavaName();
	}
	public boolean isMany() {
		return getTypedElement().isMany();
	}
	public boolean isOne() {
		return getTypedElement().isOne();
	}
	public UIMClassifier getBaseType() {
		return getTypedElement().getBaseType();
	}
	@Override
	public SecureUserAction getSecurityOnVisibility() {
		return getTypedElement().getSecurityOnVisibility();
	}
	public String getMetaClass() {
		return getTypedElement().getMetaClass();
	}
}
