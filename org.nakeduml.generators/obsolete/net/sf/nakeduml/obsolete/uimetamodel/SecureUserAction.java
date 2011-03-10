package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
public class SecureUserAction implements Serializable, Cloneable {
	private static final long serialVersionUID = -1405448442618802181L;
	// Initialise with minimal security.
	private String[] requiredRoles = null;
	private boolean requiresUserOwnership = false;
	private boolean requiresGroupOwnership = false;
	private String requiredRoleString = null;
	public boolean isConstrained() {
		return getRequiredRoles().length > 0;
	}
	public boolean requiresUserOwnership() {
		return this.requiresUserOwnership;
	}
	public boolean requiresGroupOwnership() {
		return this.requiresGroupOwnership;
	}
	public String getRequiredRoleString() {
		return this.requiredRoleString;
	}
	public String[] getRequiredRoles() {
		if (this.requiredRoles == null) {
			this.requiredRoles = new String[0];
		}
		return this.requiredRoles;
	}
	public final void setRequiredRoles(String[] requiredRoles) {
		this.requiredRoles = requiredRoles;
		if (requiredRoles != null && requiredRoles.length > 0) {
			// Sfer to leave it null if there are no roles
			StringBuilder sb = new StringBuilder();
			for (String role : requiredRoles) {
				sb.append(role);
				sb.append(',');
			}
			this.requiredRoleString = sb.substring(0, sb.length()-1);
		}
	}
	public final void setRequiresGroupOwnership(boolean requiresGroupOwnership) {
		this.requiresGroupOwnership = requiresGroupOwnership;
	}
	public final void setRequiresUserOwnership(boolean requiresUserOwnership) {
		this.requiresUserOwnership = requiresUserOwnership;
	}
}
