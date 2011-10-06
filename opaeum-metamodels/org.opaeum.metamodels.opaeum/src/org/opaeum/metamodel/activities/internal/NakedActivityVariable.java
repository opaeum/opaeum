package org.opaeum.metamodel.activities.internal;

import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.internal.NakedTypedElementImpl;

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
