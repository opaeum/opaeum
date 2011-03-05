package net.sf.nakeduml.obsolete.uimetamodel;

import org.nakeduml.annotation.PropertyParticipationKind;

public class UIMPropertyParticipation extends UIMTypedElementParticipation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1757597420922204058L;
	private UIMEntity owner;
	private PropertyParticipationKind participationKind;
	private String styleClassName;
	@Override
	public UIMElement getOwnerElement(){
		return this.owner;
	}
	public final UIMProperty getProperty(){
		return (UIMProperty) getTypedElement();
	}
	@Override
	public boolean canReceiveInput(){
		return this.participationKind == PropertyParticipationKind.READWRITE || this.participationKind == PropertyParticipationKind.REQUIRED;
	}
	@Override
	public boolean isRequired(){
		return this.participationKind == PropertyParticipationKind.REQUIRED;
	}
	public boolean isPropertyReadOnly(){
		return getProperty().isInverse() || getProperty().isReadOnly() || getProperty().isDerived() || getProperty().isDiscriminator();
	}
	public boolean shouldAddComponent(){
		return this.participationKind == PropertyParticipationKind.HIDDEN || this.participationKind == PropertyParticipationKind.MENUITEM;
	}
	public String getStyleClassName(){
		// TODO 1. Get from participation,
		initStyleClassName();
		return this.styleClassName;
	}
	private void initStyleClassName(){
		if(this.styleClassName == null){
			if(getProperty().getStyleClassName() == null){
				if(getBaseType().getStyleClassName() != null){
					this.styleClassName = getBaseType().getStyleClassName();
				}
			}else{
				this.styleClassName = getProperty().getStyleClassName();
			}
		}
	}
	@Override
	public boolean isReadOnly(){
		UIMProperty p = getProperty();
		return p.isReadOnly() || (p.isOneToOne() && p.isInverse()) || p.isDiscriminator()
				|| (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
	}
	public PropertyParticipationKind getParticipationKind() {
		return this.participationKind;
	}
	@Override
	public boolean isMenuItem() {
		return getParticipationKind()==PropertyParticipationKind.MENUITEM;
	}
	
}
