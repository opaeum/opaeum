package net.sf.nakeduml.obsolete.uimetamodel;
public class UIMOperationParticipation extends UIMFeatureParticipation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5771634031193397100L;
	private UIMOperation operation;
	@Override
	public UIMElement getOwnerElement() {
		return getOperation().getOwner();
	}
	public UIMOperation getOperation() {
		return this.operation;
	}
	@Override
	public SecureUserAction getSecurityOnVisibility() {
		return getOperation().getSecurityOnVisibility();
	}
}
