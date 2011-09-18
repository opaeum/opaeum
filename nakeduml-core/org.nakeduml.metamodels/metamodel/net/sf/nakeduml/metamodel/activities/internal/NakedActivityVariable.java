package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;

public class NakedActivityVariable extends NakedTypedElementImpl implements INakedActivityVariable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296648779971906917L;

	@Override
	public INakedActivity getActivity() {
		INakedElementOwner element = getOwnerElement();
		while(!(element instanceof INakedActivity)){
			element=((INakedElement)element).getOwnerElement();
		}
		return (INakedActivity) element;
	}
}
