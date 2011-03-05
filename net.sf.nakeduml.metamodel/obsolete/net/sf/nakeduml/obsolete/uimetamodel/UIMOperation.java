package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nakeduml.annotation.OperationParticipationKind;


public class UIMOperation extends UIMElement implements Serializable,UIMMenuElement{
	private static final long serialVersionUID = -1229455188894548493L;
	private String cappedJavaName;
	private List<UIMParameter> parameters;
	private UIMEntity owner;
	private UIMParameter returnParameter;
	private boolean isQuery;
	private SecureUserAction securityOnVisibility = new SecureUserAction();
	private Map<String,OperationParticipationKind> participations = new HashMap<String,OperationParticipationKind>();
	private Double displayIndex;
	private UIMEntityUserInteraction userInteractionForResults;
	private UIMEntityUserInteraction userInteractionForOwner;
	public boolean hasReturnParameter(){
		return this.returnParameter != null;
	}

	public UIMEntity getResultingType(){
		if(hasReturnParameter() && getReturnParameter().getBaseType() instanceof UIMEntity){
			return (UIMEntity) getReturnParameter().getBaseType();
		}
		return null;
	}
	public UIMEntityUserInteraction getResultingUserInteraction(){
		// TODO support name based UserInteraction
		return this.userInteractionForResults;
	}
	public Double getDisplayIndex(){
		return this.displayIndex;
	}

	public String getMetaClass(){
		return "operation";
	}

	public OperationParticipationKind getParticipationIn(String userInteraction){
		return this.participations.get(userInteraction);
	}
	public SecureUserAction getSecurityOnVisibility(){
		return this.securityOnVisibility;
	}
	public boolean isQuery(){
		return this.isQuery;
	}
	public UIMParameter getReturnParameter(){
		return this.returnParameter;
	}
	public UIMEntityUserInteraction getUserInteractionForOwner(){
		return this.userInteractionForOwner;
	}
	public UIMEntity getOwner(){
		return this.owner;
	}
	public List<UIMParameter> getParameters(){
		return this.parameters;
	}
	public String getCappedJavaName(){
		return this.cappedJavaName;
	}

	public Set<UIMState> getStatesLeavingFrom(){
		
		return null;
	}

	@Override
	public UIMElement getOwnerElement() {
		return getOwner();
	}
}
