package net.sf.nakeduml.domainmetamodel;

import java.util.HashSet;
import java.util.Set;

public class SecurityOnUserAction {
	volatile private int hashCode = 0;	// See Item 8 from 'Effective Java'
	private Boolean requiresGroupOwnership = false;
	private Boolean requiresUserOwnership = false;
	private Set<String> requiredRoles = new HashSet<String>();

	/** Default constructor for 
	 */
	public SecurityOnUserAction() {
		this.setRequiresGroupOwnership( true );
		this.setRequiresUserOwnership( false );
	}

	public void addAllToRequiredRoles(Set<String> requiredRoles) {
		for ( String o : requiredRoles ) {
			addToRequiredRoles(o);
		}
	}
	
	public void addToRequiredRoles(String requiredRoles) {
		getRequiredRoles().add(requiredRoles);
	}
	
	public void clearRequiredRoles() {
		removeAllFromRequiredRoles(getRequiredRoles());
	}
	
	public void copyState(SecurityOnUserAction from, SecurityOnUserAction to) {
		to.setRequiresGroupOwnership(from.getRequiresGroupOwnership());
		to.setRequiresUserOwnership(from.getRequiresUserOwnership());
		to.getRequiredRoles().addAll(from.getRequiredRoles());
	}
	
	public boolean equals(Object securityOnUserAction) {
		if ( !(securityOnUserAction instanceof SecurityOnUserAction) ) {
			return false;
		}
		SecurityOnUserAction par = (SecurityOnUserAction) securityOnUserAction;
		if ( this.getRequiresGroupOwnership() != par.getRequiresGroupOwnership() ) {
			return false;
		}
		if ( this.getRequiresUserOwnership() != par.getRequiresUserOwnership() ) {
			return false;
		}
		if ( !(this.getRequiredRoles() == null ? par.getRequiredRoles() == null : this.getRequiredRoles().equals( par.getRequiredRoles() )) ) {
			return false;
		}
		return true;
	}
	
	public Set<String> getRequiredRoles() {
		return requiredRoles;
	}
	
	public Boolean getRequiresGroupOwnership() {
		return requiresGroupOwnership;
	}
	
	public Boolean getRequiresUserOwnership() {
		return requiresUserOwnership;
	}
	
	public SecurityOnUserAction makeCopy() {
		SecurityOnUserAction result = new SecurityOnUserAction();
		copyState((SecurityOnUserAction)this,result);
		return result;
	}
	
	public void removeAllFromRequiredRoles(Set<String> requiredRoles) {
		for ( String o : requiredRoles ) {
			removeFromRequiredRoles(o);
		}
	}
	
	public void removeFromRequiredRoles(String requiredRoles) {
		getRequiredRoles().remove(requiredRoles);
	}
	
	public void setRequiredRoles(Set<String> requiredRoles) {
		this.requiredRoles=requiredRoles;
	}
	
	public void setRequiresGroupOwnership(Boolean requiresGroupOwnership) {
		this.requiresGroupOwnership=requiresGroupOwnership;
	}
	
	public void setRequiresUserOwnership(Boolean requiresUserOwnership) {
		this.requiresUserOwnership=requiresUserOwnership;
	}
	
	public void shallowCopyState(SecurityOnUserAction from, SecurityOnUserAction to) {
		to.setRequiresGroupOwnership(from.getRequiresGroupOwnership());
		to.setRequiresUserOwnership(from.getRequiresUserOwnership());
		to.getRequiredRoles().addAll(from.getRequiredRoles());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("requiresGroupOwnership=");
		sb.append(getRequiresGroupOwnership());
		sb.append(";");
		sb.append("requiresUserOwnership=");
		sb.append(getRequiresUserOwnership());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getRequiresGroupOwnership()==null ) {
			sb.append("<requiresGroupOwnership/>");
		} else {
			sb.append("<requiresGroupOwnership>");
			sb.append(getRequiresGroupOwnership());
			sb.append("</requiresGroupOwnership>");
			sb.append("\n");
		}
		if ( getRequiresUserOwnership()==null ) {
			sb.append("<requiresUserOwnership/>");
		} else {
			sb.append("<requiresUserOwnership>");
			sb.append(getRequiresUserOwnership());
			sb.append("</requiresUserOwnership>");
			sb.append("\n");
		}
		for ( String requiredRoles : getRequiredRoles() ) {
			sb.append("<requiredRoles>");
			sb.append(requiredRoles);
			sb.append("</requiredRoles>");
			sb.append("\n");
		}
		return sb.toString();
	}

}