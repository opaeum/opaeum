package net.sf.nakeduml.obsolete.uimetamodel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nakeduml.annotation.UserInteractionKind;

//TODO read from the InstanceSpecification as per Stereotype
public abstract class ParticipationCalculator<T extends Enum> {
	private static final long serialVersionUID = 6810669608933976170L;
	protected static final String VIEW_REQUIRES_USER_OWNERSHIP = "viewRequiresUserOwnership";
	private Map<UserInteractionKind, T> participations = new HashMap<UserInteractionKind, T>();
	protected UIMMenuElement modelElement;
	protected void initialize(UIMMenuElement modelElement) {
		this.modelElement = modelElement;
		SecureUserAction securityOnVisibility = modelElement.getSecurityOnVisibility();
		UIMClassifier resultingType = modelElement.getResultingType();
		if (resultingType instanceof UIMEntity) {
			// Rule: the security constraints on the visibility of menuitem of a
			// feature is further restricted by the security constraints on the
			// visibility of the resulting type of the menu item
			UIMEntity resultingEntity = (UIMEntity) resultingType;
			SecureUserAction entitySecOnView = resultingEntity.getSecurityOnView();
			restrictSecureAction(securityOnVisibility, entitySecOnView);
		}
	}
	protected void restrictSecureAction(SecureUserAction originalSecurity, SecureUserAction restrictingSecurity) {
		if (!originalSecurity.requiresUserOwnership()) {
			originalSecurity.setRequiresUserOwnership(restrictingSecurity.requiresUserOwnership());
		}
		if (!originalSecurity.requiresGroupOwnership()) {
			originalSecurity.setRequiresGroupOwnership(restrictingSecurity.requiresGroupOwnership());
		}
		// get union
		Set<String> rr = new HashSet<String>(Arrays.asList(originalSecurity.getRequiredRoles()));
		if (rr.isEmpty()) {
			rr.addAll(Arrays.asList(restrictingSecurity.getRequiredRoles()));
		} else {
			rr.retainAll(Arrays.asList(restrictingSecurity.getRequiredRoles()));
		}
		if (!rr.isEmpty()) {
			originalSecurity.setRequiredRoles(rr.toArray(new String[rr.size()]));
		}
	}
	public void putParticipation(UserInteractionKind userInteractionKind, T participationKind) {
		this.participations.put(userInteractionKind, participationKind);
	}
	protected abstract T getParticipationIn(Class<T> c, String userInteraction);
	public T getParticipationIn(UserInteractionKind userInteractionKind) {
		return this.participations.get(userInteractionKind);
	}
	public void initParticipation(UserInteractionKind[] state, T defaultParticipation) {
		for (UserInteractionKind kind : state) {
			if (getParticipationIn(kind) == null) {
				putParticipation(kind, defaultParticipation);
			}
		}
	}
}
