package org.opeum.metamodel.activities.internal;

import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityVariable;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.internal.NakedTypedElementImpl;

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
