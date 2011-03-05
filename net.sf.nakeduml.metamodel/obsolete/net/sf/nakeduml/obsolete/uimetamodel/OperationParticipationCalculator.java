package net.sf.nakeduml.obsolete.uimetamodel;
import org.nakeduml.annotation.OperationParticipationKind;
import org.nakeduml.annotation.UserInteractionKind;
public class OperationParticipationCalculator extends ParticipationCalculator<OperationParticipationKind> {
	UIMOperation operation;
	public void initialize(UIMOperation operation) {
		this.operation = operation;
		super.initialize(operation);
		OperationParticipationKind participationInEdit = super.getParticipationIn(UserInteractionKind.EDIT);
		if (participationInEdit == null) {
			participationInEdit = OperationParticipationKind.VISIBLE;
			super.putParticipation(UserInteractionKind.EDIT, participationInEdit);
		}
		OperationParticipationKind participationInView = super.getParticipationIn(UserInteractionKind.VIEW);
		if (participationInView == null) {
			participationInView = OperationParticipationKind.VISIBLE;
			super.putParticipation(UserInteractionKind.VIEW, participationInView);
		}
		if (!operation.isQuery()) {
			SecureUserAction securityOnVisibility = operation.getSecurityOnVisibility();
			// Rule: the security constraints on the visibility of menuitem of
			// an operation that is
			// not a query, i.e. an operation that could potentially update the
			// state of the entity, is the
			// superset of security constraints on the editability of the owning
			// entity
			restrictSecureAction(securityOnVisibility, operation.getOwner().getSecurityOnEdit());
		}
	}
	@Override
	protected OperationParticipationKind getParticipationIn(Class<OperationParticipationKind> c, String userInteraction) {
		return this.operation.getParticipationIn(userInteraction);
	}
}
