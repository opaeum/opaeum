package org.opaeum.uimodeler;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.papyrus.infra.core.editor.BackboneException;
import org.eclipse.papyrus.infra.core.multidiagram.actionbarcontributor.CoreComposedActionBarContributor;
import org.eclipse.ui.IEditorActionBarContributor;


public class OpaeumActionBarContributor extends CoreComposedActionBarContributor implements IMenuListener {


	public OpaeumActionBarContributor() throws BackboneException {
		super();
	}

	/**
	 * Methods requested by EMF nested diagrams.
	 * Propagate the call to the currently active contributor.
	 * 
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 * @param manager
	 * 
	 */
	public void menuAboutToShow(IMenuManager manager) {
		IEditorActionBarContributor contributor = getActiveContributor();
		if(contributor != this && contributor instanceof IMenuListener) {
			((IMenuListener)contributor).menuAboutToShow(manager);
			return;
		}

	}

}
