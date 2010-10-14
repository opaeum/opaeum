package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.annotation.UserInteractionKind;
public class UIMEntity extends UIMClassifier implements Serializable {
	private static final long serialVersionUID = 3937749529683117688L;
	private SecureUserAction securityOnAudit = new SecureUserAction();
	private SecureUserAction securityOnAdd = new SecureUserAction();
	private SecureUserAction securityOnDelete = new SecureUserAction();
	private SecureUserAction securityOnEdit = new SecureUserAction();
	private SecureUserAction securityOnView = new SecureUserAction();
	private SecureUserAction securityOnForceStateChange = new SecureUserAction();
	private boolean isRootEntity;
	private boolean representsUser;
	private List<UIMProperty> properties = new ArrayList<UIMProperty>();
	private List<UIMOperation> operations = new ArrayList<UIMOperation>();
	private UIMPackage facesPackage;
	private Map<String, UIMState> facesStates = new HashMap<String, UIMState>();
	public UIMEntity() {
	}
	public String getPath(){
		return this.facesPackage.getPath() + "/" + getJavaName();
	}
	public void consolidateSecurity() {
		// RULE If you can edit or add, you must be allowed to view too, thus
		// relaxing the security
		if (getSecurityOnView().isConstrained()) {
			Set<String> view = new HashSet<String>();
			view.addAll(Arrays.asList(getSecurityOnView().getRequiredRoles()));
			view.addAll(Arrays.asList(getSecurityOnEdit().getRequiredRoles()));
			view.addAll(Arrays.asList(getSecurityOnAdd().getRequiredRoles()));
			getSecurityOnView().setRequiredRoles(view.toArray(new String[view.size()]));
		}
	}
	public List<UIMProperty> getProperties() {
		return this.properties;
	}
	public boolean isRootEntity() {
		return this.isRootEntity;
	}
	public boolean representsUser() {
		return this.representsUser;
	}
	public List<UIMOperation> getOperations() {
		return this.operations;
	}
	public String getPackageName() {
		return this.facesPackage.getJavaName();
	}
	public boolean hasComposite() {
		return getEndToComposite() != null;
	}
	public UIMProperty getEndToComposite() {
		for (UIMProperty fp : this.properties) {
			if (fp.isReferenceToComposite()) {
				return fp;
			}
		}
		return null;
	}
	public UIMProperty getAttribute(String name) {
		for (UIMProperty fp : this.properties) {
			if (fp.getJavaName().equals(name)) {
				return fp;
			}
		}
		return null;
	}
	public UserInteractionKind getEditUserInteractionForState(String sw) {
		if (this.facesStates.containsKey(sw)) {
			return this.facesStates.get(sw).getUserInteractionKind();
		} else {
			return null;
		}
	}
	public SecureUserAction getSecurityOnView() {
		return this.securityOnView;
	}
	public SecureUserAction getSecurityOnEdit() {
		return this.securityOnEdit;
	}
	public SecureUserAction getSecurityOnDelete() {
		return this.securityOnDelete;
	}
	public SecureUserAction getSecurityOnAdd() {
		return this.securityOnAdd;
	}
	public SecureUserAction getSecurityOnAudit() {
		return this.securityOnAudit;
	}
	public UIMState getSecurityOnState(String n) {
		return this.facesStates.get(n);
	}
	public final UIMPackage getFacesPackage() {
		return this.facesPackage;
	}
	public final void setFacesPackage(UIMPackage facesPackage) {
		this.facesPackage = facesPackage;
	}
	public final Map<String, UIMState> getFacesStates() {
		return this.facesStates;
	}
	public final void setRootEntity(boolean isRootEntity) {
		this.isRootEntity = isRootEntity;
	}
	public final void setRepresentsUser(boolean representsUser) {
		this.representsUser = representsUser;
	}
	public boolean isProcess() {
		
		return false;
	}
	protected SecureUserAction getSecurityOnForceStateChange() {
		return this.securityOnForceStateChange;
	}
}
