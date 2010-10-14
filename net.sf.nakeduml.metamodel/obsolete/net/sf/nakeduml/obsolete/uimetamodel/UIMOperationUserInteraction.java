package net.sf.nakeduml.obsolete.uimetamodel;
import java.util.ArrayList;
import java.util.List;
public class UIMOperationUserInteraction extends UIMEntityUserInteraction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469246406567155215L;
	private UIMOperation operation;
	private List<UIMParameterParticipation> parameterParticipations = new ArrayList<UIMParameterParticipation>();
	public UIMOperation getOperation() {
		return this.operation;
	}
	public List<UIMParameterParticipation> getParameterParticipations() {
		return this.parameterParticipations;
	}
}
