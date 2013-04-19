package org.opaeum.papyrus.editor;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.papyrus.editor.PapyrusActionBarContributor;
import org.eclipse.papyrus.infra.core.editor.BackboneException;
import org.eclipse.ui.IEditorActionBarContributor;

public class OpaeumActionBarContributor extends PapyrusActionBarContributor  implements IMenuListener {


	public OpaeumActionBarContributor () throws BackboneException {
		super();
	}

	public void menuAboutToShow(IMenuManager manager) {
		IEditorActionBarContributor contributor = getActiveContributor();
		if(contributor != this && contributor instanceof IMenuListener) {
			((IMenuListener)contributor).menuAboutToShow(manager);
			return;
		}
	}

}
