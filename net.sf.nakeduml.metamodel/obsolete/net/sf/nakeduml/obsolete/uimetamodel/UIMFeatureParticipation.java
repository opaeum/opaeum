package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
public abstract class UIMFeatureParticipation extends UIMElement implements Serializable {
	private static final long serialVersionUID = -2843419015918531295L;
	private double displayIndex;
	private String userInteractionName;
	private UIMElement element;
	public final double getDisplayIndex() {
		return this.displayIndex;
	}
	public final String getUserInteractionName() {
		return this.userInteractionName;
	}
	public boolean isMenuItem() {
		return false;
	}
	public UIMElement getParticipatingElement() {
		return this.element;
	}
	public abstract SecureUserAction getSecurityOnVisibility();
}
