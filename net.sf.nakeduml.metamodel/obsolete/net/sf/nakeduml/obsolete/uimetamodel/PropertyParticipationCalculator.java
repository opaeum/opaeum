package net.sf.nakeduml.obsolete.uimetamodel;

import net.sf.nakeduml.annotation.PropertyParticipationKind;
import net.sf.nakeduml.annotation.UserInteractionKind;

public class PropertyParticipationCalculator extends ParticipationCalculator<PropertyParticipationKind>{
	private UIMProperty property;
	public void initialize(UIMProperty property){
		this.property = property;
		super.initialize(property);
		initParticipationInCreate();
		initParticipationInEdit();
		initParticipationInList();
		initParticipationInCriteria();
		initParticipationInView();
		PropertyParticipationKind participationInList = initParticipationInList();
		if(participationInList == PropertyParticipationKind.READWRITE || participationInList == PropertyParticipationKind.REQUIRED){
			participationInList = PropertyParticipationKind.READONLY;
		}
	}
	private void initParticipationInCreate(){
		if(this.property.isComposite() || this.property.isDiscriminator()){
			// Rule: cannot change position in containment or inheritance hierarchies, we therefere enforce defaults
			if(!PropertyParticipationKind.HIDDEN.equals(super.getParticipationIn(UserInteractionKind.CREATE))){
				super.putParticipation(UserInteractionKind.CREATE, PropertyParticipationKind.READONLY);
			}
		}else{
			PropertyParticipationKind participationInCreate = super.getParticipationIn(UserInteractionKind.CREATE);
			if(participationInCreate == null){
				if(this.property.isReadOnly() || this.property.isDerived() || this.property.isInverse()){
					participationInCreate = PropertyParticipationKind.HIDDEN;
				}else if(this.property.getBaseType() instanceof UIMEntity && ((UIMEntity) this.property.getBaseType()).isRootEntity()
						|| this.property.isComposite()){
					// Root Entity attributes are never displayed or edited
					participationInCreate = PropertyParticipationKind.HIDDEN;
				}else if(this.property.isComposite() || this.property.isDiscriminator()){
					participationInCreate = PropertyParticipationKind.READONLY;
				}else if(this.property.isOneToMany()){
					participationInCreate = PropertyParticipationKind.HIDDEN;
				}else{
					participationInCreate = PropertyParticipationKind.READWRITE;
				}
				super.putParticipation(UserInteractionKind.CREATE, participationInCreate);
			}
		}
	}
	@Override
	protected PropertyParticipationKind getParticipationIn(Class<PropertyParticipationKind> c,String userInteraction){
		//TODO fix this
		if(!this.property.hasTaggedValue("PropertyParticipation", userInteraction) && this.property.getBaseType() instanceof UIMClassifier){
			String name = Character.toUpperCase(userInteraction.charAt(0)) + userInteraction.substring(1);
			UIMClassifier type = this.property.getBaseType();
			return PropertyParticipationKind.valueOf(type.getTaggedValue("??", "default" + name).toString());
		}else{
			return PropertyParticipationKind.valueOf(this.modelElement.getTaggedValue("??", userInteraction).toString());
		}
	}
	private PropertyParticipationKind initParticipationInEdit(){
		if(this.property.isComposite() || this.property.isDiscriminator()){
			if(!PropertyParticipationKind.HIDDEN.equals(super.getParticipationIn(UserInteractionKind.EDIT))){
				super.putParticipation(UserInteractionKind.EDIT, PropertyParticipationKind.READONLY);
			}
		}
		PropertyParticipationKind participationInEdit = super.getParticipationIn(UserInteractionKind.EDIT);
		if(participationInEdit == null){
			if((this.property.getBaseType() instanceof UIMEntity && ((UIMEntity) this.property.getBaseType()).isRootEntity()) || this.property.isComposite()){
				participationInEdit = PropertyParticipationKind.HIDDEN;
			}else if(this.property.isComposite()){
				participationInEdit = PropertyParticipationKind.MENUITEM;
			}else if(this.property.isOneToMany()){
				participationInEdit = PropertyParticipationKind.HIDDEN;
			}else if(this.property.isDerived() || this.property.isReadOnly() || this.property.isInverse() || this.property.isDiscriminator()){
				participationInEdit = PropertyParticipationKind.READONLY;
			}else if(this.property.isRequired()){
				participationInEdit = PropertyParticipationKind.REQUIRED;
			}else{
				participationInEdit = PropertyParticipationKind.READWRITE;
			}
			super.putParticipation(UserInteractionKind.EDIT, participationInEdit);
		}
		return participationInEdit;
	}
	private PropertyParticipationKind initParticipationInList(){
		PropertyParticipationKind participationInList = super.getParticipationIn(UserInteractionKind.LIST);
		if(participationInList == null){
			if(this.property.getBaseType() instanceof UIMEntity && ((UIMEntity) this.property.getBaseType()).isRootEntity()){
				participationInList = PropertyParticipationKind.HIDDEN;
			}else if(this.property.isComposite()){
				participationInList = PropertyParticipationKind.HIDDEN;
			}else if(this.property.isOneToOne() && this.property.isInverse()){
				participationInList = PropertyParticipationKind.HIDDEN;
			}else if(this.property.isMany()){
				participationInList = PropertyParticipationKind.HIDDEN;
			}else if(initParticipationInEdit() == PropertyParticipationKind.HIDDEN){
				participationInList = PropertyParticipationKind.HIDDEN;
			}else{
				participationInList = PropertyParticipationKind.READONLY;
			}
			super.putParticipation(UserInteractionKind.LIST, participationInList);
		}
		return participationInList;
	}
	private PropertyParticipationKind initParticipationInCriteria(){
		PropertyParticipationKind participationInCriteria = super.getParticipationIn(UserInteractionKind.CRITERIA);
		if(participationInCriteria == null){
			if(this.property.getBaseType() instanceof UIMEntity && ((UIMEntity) this.property.getBaseType()).isRootEntity()){
				participationInCriteria = PropertyParticipationKind.HIDDEN;
			}else if(initParticipationInList() == PropertyParticipationKind.HIDDEN){
				participationInCriteria = PropertyParticipationKind.HIDDEN;
			}else{
				participationInCriteria = PropertyParticipationKind.READWRITE;
			}
			super.putParticipation(UserInteractionKind.CRITERIA, participationInCriteria);
		}
		return participationInCriteria;
	}
	private PropertyParticipationKind initParticipationInView(){
		PropertyParticipationKind participationInView = super.getParticipationIn(UserInteractionKind.VIEW);
		if(participationInView == null){
			if(initParticipationInEdit() == PropertyParticipationKind.HIDDEN){
				participationInView = PropertyParticipationKind.HIDDEN;
			}else if(initParticipationInEdit() == PropertyParticipationKind.MENUITEM){
				participationInView = PropertyParticipationKind.MENUITEM;
			}else{
				participationInView = PropertyParticipationKind.READONLY;
			}
			super.putParticipation(UserInteractionKind.VIEW, participationInView);
		}
		return participationInView;
	}
}
