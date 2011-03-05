package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.annotation.UserInteractionKind;


public class UIMEntityUserInteraction extends UIMElement implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<UIMPropertyParticipation> propertyParicipations = new ArrayList<UIMPropertyParticipation>();
	private List<UIMOperationParticipation> operationParicipations = new ArrayList<UIMOperationParticipation>();
	private UIMEntity entity;
	private UserInteractionKind userInteractionKind;	
	
	public final UIMEntity getEntity(){
		return this.entity;
	}
	public final void setEntity(UIMEntity entity){
		this.entity = entity;
	}
	public final UserInteractionKind getUserInteractionKind(){
		return this.userInteractionKind;
	}
	public final void setUserInteractionKind(UserInteractionKind userInteractionKind){
		this.userInteractionKind = userInteractionKind;
	}
	public final List<UIMPropertyParticipation> getPropertyParticipations(){
		return this.propertyParicipations;
	}
	public final String getPath(){
		return getEntity().getPath() + "/" + getJavaName();
	}
	public UIMFeatureParticipation getFeatureParticipation(String name){
		Set<UIMFeatureParticipation> s = new HashSet<UIMFeatureParticipation>(getOperationParticipations());
		s.addAll(getPropertyParticipations());
		for(UIMFeatureParticipation p:s){
			if(p.getJavaName().equals(name)){
				return p;
			}
		}
		return null;
	}

	public final List<UIMOperationParticipation> getOperationParticipations(){
		return this.operationParicipations;
	}
	@Override
	public UIMElement getOwnerElement() {
		return getEntity();
	}

}
