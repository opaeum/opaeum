package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_KIND;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationIn;
import static net.sf.nakeduml.uigeneration.StereotypeNames.resolve;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class PropertyParticipationKindMap{
	private INakedProperty property;
	private ClassifierUserInteraction eui;
	private INakedEnumerationLiteral tag;
	public boolean isTagged() {
		return tag!=null;
	}
	private NakedStructuralFeatureMap map;
	public PropertyParticipationKindMap(INakedProperty property,ClassifierUserInteraction eui){
		super();
		this.property = property;
		this.map=new NakedStructuralFeatureMap(property);
		this.eui = eui;
		
		//Not sure why this is here??
//		if (!this.property.getOwner().getMappingInfo().getJavaName().toString().equals(eui.getClassifier().getName())) {
//			
//		} else {
			tag = getTag(property, participationIn(eui.getUserInteractionKind()), PARTICIPATION_KIND);
//		}
		
	}
	public PropertyParticipationKindMap(INakedSlot slot,ClassifierUserInteraction eui){
		super();
		this.property = slot.getDefiningFeature();
		this.eui = eui;
		tag = getTag(slot, participationIn(eui.getUserInteractionKind()), PARTICIPATION_KIND);
	}
	public TypedElementParticipationKind calculateParticipationKind(){
		TypedElementParticipationKind kind = null;
		if(tag != null){
			kind = resolve(tag, TypedElementParticipationKind.class);
		}else{
			kind = calculateDefault();
		}
		kind = override(kind);
		return kind;
	}
	private TypedElementParticipationKind calculateDefault(){
		TypedElementParticipationKind kind = null;
		// Calculate default
		boolean navigation = !(property.getNakedBaseType() instanceof INakedInterface) && property.getOtherEnd() != null && !property.getOtherEnd().isComposite();
		if(eui.getUserInteractionKind() == UserInteractionKind.LIST){
			
			if((property.isComposite() || map.isOneToMany())){
				kind = TypedElementParticipationKind.NAVIGATION;
			} else if (!property.isDerived() && navigation){
				kind = TypedElementParticipationKind.NAVIGATION;
			} else if(property.getOtherEnd() != null && property.getOtherEnd().isComposite() && map.isManyToOne()){
				kind = TypedElementParticipationKind.HIDDEN;
			}else if(!property.isReadOnly() && !property.isDerived()){
				if(property.getOtherEnd() != null && property.getOtherEnd().isComposite()){
					kind = TypedElementParticipationKind.READONLY;
				}else if(property.isRequired()){
					kind = TypedElementParticipationKind.REQUIRED;
				}else{
					kind = TypedElementParticipationKind.READWRITE;
				}
			} else if(!navigation && property.isDerived() && !property.getName().equals("now")){
				kind = TypedElementParticipationKind.READONLY;
			} else {
				kind = TypedElementParticipationKind.HIDDEN;
			}
			
		}else if(eui.getUserInteractionKind() == UserInteractionKind.EDIT){
			if(property.isComposite() || map.isOneToMany()){
				kind = TypedElementParticipationKind.NAVIGATION;
			} else if (!property.isDerived() && navigation){
				kind = TypedElementParticipationKind.NAVIGATION;
			} else if(property.getOtherEnd() != null && property.getOtherEnd().isComposite() && map.isManyToOne()){
				kind = TypedElementParticipationKind.HIDDEN;
			}else if(!property.isReadOnly() && !property.isDerived()){
				if(property.getOtherEnd() != null && property.getOtherEnd().isComposite()){
					kind = TypedElementParticipationKind.READONLY;
				}else if(property.isRequired()){
					kind = TypedElementParticipationKind.REQUIRED;
				}else{
					kind = TypedElementParticipationKind.READWRITE;
				}
			} else if(!navigation && property.isDerived() && !property.getName().equals("now")){
				kind = TypedElementParticipationKind.READONLY;
			} else {
				kind = TypedElementParticipationKind.HIDDEN;
			}
		}else if(eui.getUserInteractionKind() == UserInteractionKind.CREATE){
			if(property.isComposite() || map.isOneToMany()){
				kind = TypedElementParticipationKind.HIDDEN;
			} else if(property.getOtherEnd() != null && property.getOtherEnd().isComposite() && map.isManyToOne()){
				kind = TypedElementParticipationKind.HIDDEN;
			} else if (property.getOtherEnd() != null && !property.getOtherEnd().isComposite() && !property.isRequired()){
				kind = TypedElementParticipationKind.HIDDEN;
			}else if(!property.isReadOnly() && !property.isDerived()){
				if(property.getOtherEnd() != null && property.getOtherEnd().isComposite()){
					kind = TypedElementParticipationKind.READONLY;
				}else if(property.isRequired()){
					kind = TypedElementParticipationKind.REQUIRED;
				}else{
					kind = TypedElementParticipationKind.READWRITE;
				}
			} else if(property.isDerived() && !property.getName().equals("now")){
				kind = TypedElementParticipationKind.READONLY;
			} else {
				kind = TypedElementParticipationKind.HIDDEN;
			}
		}else if(eui.getUserInteractionKind() == UserInteractionKind.VIEW){
			if(property.isComposite() || map.isOneToMany()){
				kind = TypedElementParticipationKind.NAVIGATION;
			} else if(property.getOtherEnd() != null && property.getOtherEnd().isComposite() && map.isManyToOne()){
				kind = TypedElementParticipationKind.HIDDEN;
			}else{
				kind = TypedElementParticipationKind.READONLY;
			}
		}
		// TODO consider interaction between participations from different userInteractions, defaultParticipation for type, etc
		return kind;
	}
	private TypedElementParticipationKind override(TypedElementParticipationKind kind){
		if(kind == TypedElementParticipationKind.READWRITE || kind == TypedElementParticipationKind.REQUIRED){
			// READONLY
			if(property.isReadOnly() || property.isInverse() || property.isDerived() || property.isDerivedUnion() || property.isDiscriminator()
					|| eui.getUserInteractionKind() == UserInteractionKind.VIEW
					|| (property.getOtherEnd() != null && property.getOtherEnd().isComposite())){
				kind = TypedElementParticipationKind.READONLY;
			}
			if(property.isComposite()){
				// Navigation
				kind = TypedElementParticipationKind.NAVIGATION;
			}
			if (property.getNakedMultiplicity().getLower() == 1 || property.isRequired()) {
				kind = TypedElementParticipationKind.REQUIRED;
			}
		}
		return kind;
	}
}
