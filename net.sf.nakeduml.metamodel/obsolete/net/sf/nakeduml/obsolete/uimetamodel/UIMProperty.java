package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
public class UIMProperty extends UIMTypedElement implements UIMMenuElement, Serializable {
	private static final long serialVersionUID = -8248319931437655617L;
	private UIMEntity owner;
	private boolean isDerived;
	private boolean isOneToOne;
	private boolean isInverse;
	private UIMProperty otherEnd;
	private boolean isDiscriminator;
	private boolean isReferenceToComposite;
	private boolean isOneToMany;
	private boolean readOnly;
	@Override
	public UIMEntity getOwner() {
		return this.owner;
	}
	public boolean isDerived() {
		return this.isDerived;
	}
	public boolean isOneToOne() {
		return this.isOneToOne;
	}
	public boolean isInverse() {
		return this.isInverse;
	}
	public boolean isBaseTypeSimple() {
		return getBaseType().isSimpleDataType();
	}
	public UIMEntity getResultingType() {
		return (UIMEntity) getBaseType();
	}
	public UIMEntityUserInteraction getResultingUserInteraction() {
		// TODO support UserInteractSpecifications with name based binding.
		return null;
	}
	public boolean isReadOnly() {
		return this.readOnly;
	}
	public UIMProperty getOtherEnd() {
		return this.otherEnd;
	}
	public boolean isComposite() {
		// Check the correct semantics
		
		return false;
	}
	@Override
	public String getMetaClass() {
		return "Property";
	}
	public boolean isDiscriminator() {
		return this.isDiscriminator;
	}
	public boolean isReferenceToComposite() {
		return this.isReferenceToComposite;
	}
	public boolean isOneToMany() {
		return this.isOneToMany;
	}
	public boolean hasTaggedValue(String stereotype, String action) {
		return super.getMetaData().containsKey(stereotype) && super.getMetaData().get(stereotype).hasAttribute(action);
	}
	@Override
	public UIMElement getOwnerElement() {
		return getOwner();
	}
}
