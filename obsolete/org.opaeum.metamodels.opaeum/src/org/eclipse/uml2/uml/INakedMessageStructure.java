package org.eclipse.uml2.uml;


/**
 * An interface for adapter classes that allow non-class elements to emulate  class elements
 * @author abarnard
 *
 */
public interface INakedMessageStructure extends ICompositionParticipant{

	public void setEndToComposite(INakedProperty artificialProperty);

	public void reinitialize();
}
