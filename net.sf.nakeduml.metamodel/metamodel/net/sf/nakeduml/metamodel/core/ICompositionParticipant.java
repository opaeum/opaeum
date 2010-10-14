package net.sf.nakeduml.metamodel.core;

public interface ICompositionParticipant extends INakedClassifier {
	boolean hasComposite();

	INakedProperty getEndToComposite();

	/**
	 * Returns true if this Entity contains information that is directly related
	 * to a user/actor/role in the system
	 */
	boolean representsUser();

	void setRepresentsUser(boolean representsUser);
}
