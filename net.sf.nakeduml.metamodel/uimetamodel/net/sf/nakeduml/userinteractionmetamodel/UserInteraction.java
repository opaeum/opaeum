package net.sf.nakeduml.userinteractionmetamodel;

import org.nakeduml.runtime.domain.CompositionNode;

public class UserInteraction extends UserInteractionElement implements CompositionNode {
	private Boolean inHierarchy = false;
	private String successMessage;
	private String instructionToUser;

	/** Default constructor for 
	 */
	public UserInteraction() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(UserInteraction from, UserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public AbstractUserInteractionFolder getFolder() {
		return null;
	}
	
	public Boolean getInHierarchy() {
		return inHierarchy;
	}
	
	public String getInstructionToUser() {
		return instructionToUser;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public String getSuccessMessage() {
		return successMessage;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public UserInteraction makeCopy() {
		UserInteraction result = new UserInteraction();
		copyState((UserInteraction)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setInHierarchy(Boolean inHierarchy) {
		this.inHierarchy=inHierarchy;
	}
	
	public void setInstructionToUser(String instructionToUser) {
		this.instructionToUser=instructionToUser;
	}
	
	public void setSuccessMessage(String successMessage) {
		this.successMessage=successMessage;
	}
	
	public void shallowCopyState(UserInteraction from, UserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().getName());
			sb.append("];");
		}
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("additionalHumanName=");
		sb.append(getAdditionalHumanName());
		sb.append(";");
		sb.append("humanName=");
		sb.append(getHumanName());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		if ( getRepresentedElement()==null ) {
			sb.append("representedElement=null;");
		} else {
			sb.append("representedElement="+getRepresentedElement().getClass().getSimpleName()+"[");
			sb.append(getRepresentedElement().getName());
			sb.append("];");
		}
		sb.append("successMessage=");
		sb.append(getSuccessMessage());
		sb.append(";");
		sb.append("instructionToUser=");
		sb.append(getInstructionToUser());
		sb.append(";");
		if ( getFolder()==null ) {
			sb.append("folder=null;");
		} else {
			sb.append("folder="+getFolder().getClass().getSimpleName()+"[");
			sb.append(getFolder().getName());
			sb.append("];");
		}
		sb.append("inHierarchy=");
		sb.append(getInHierarchy());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getAdditionalHumanName()==null ) {
			sb.append("<additionalHumanName/>");
		} else {
			sb.append("<additionalHumanName>");
			sb.append(getAdditionalHumanName());
			sb.append("</additionalHumanName>");
			sb.append("\n");
		}
		if ( getRepresentedElement()==null ) {
			sb.append("<representedElement/>");
		} else {
			sb.append("<representedElement>");
			sb.append(getRepresentedElement().getClass().getSimpleName());
			sb.append("[");
			sb.append(getRepresentedElement().getName());
			sb.append("]");
			sb.append("</representedElement>");
			sb.append("\n");
		}
		if ( getSuccessMessage()==null ) {
			sb.append("<successMessage/>");
		} else {
			sb.append("<successMessage>");
			sb.append(getSuccessMessage());
			sb.append("</successMessage>");
			sb.append("\n");
		}
		if ( getInstructionToUser()==null ) {
			sb.append("<instructionToUser/>");
		} else {
			sb.append("<instructionToUser>");
			sb.append(getInstructionToUser());
			sb.append("</instructionToUser>");
			sb.append("\n");
		}
		if ( getInHierarchy()==null ) {
			sb.append("<inHierarchy/>");
		} else {
			sb.append("<inHierarchy>");
			sb.append(getInHierarchy());
			sb.append("</inHierarchy>");
			sb.append("\n");
		}
		return sb.toString();
	}

}