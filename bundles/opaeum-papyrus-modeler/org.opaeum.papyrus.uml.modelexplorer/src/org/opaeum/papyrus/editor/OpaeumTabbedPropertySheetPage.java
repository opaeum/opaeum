package org.opaeum.papyrus.editor;


import org.eclipse.emf.facet.infra.browser.uicore.internal.model.LinkItem;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.opaeum.eclipse.uml.propertysections.AbstractOpaeumTabbedPropertySheetPage;
import org.opaeum.papyrus.uml.modelexplorer.OpaeumModelExplorerPageBookView;

@SuppressWarnings("restriction")
public final class OpaeumTabbedPropertySheetPage extends AbstractOpaeumTabbedPropertySheetPage{
	public OpaeumTabbedPropertySheetPage(ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor){
		super(tabbedPropertySheetPageContributor);
	}
	@Override
	protected void handlePartActivated(IWorkbenchPart part){
		if(part instanceof OpaeumModelExplorerPageBookView){
			// substitute for the Editor to:
			// 1. avoid unnecessary calculations
			// 2. avoid incorrect refreshing of Sections
			part= EditorUtils.getMultiDiagramEditor();
		}
		if(part !=null && part.getSite()!=null){
			super.handlePartActivated(part);
		}
	}
	@Override
	protected Object resolveEObject(Object object){
		if(object instanceof ModelElementItem){
			return ((ModelElementItem) object).getEObject();
		}else if(object instanceof LinkItem){
			return ((LinkItem) object).getParent();
		}else if(object instanceof EditPart){
			Object model = ((EditPart) object).getModel();
			if(model instanceof View){
				return ((View) model).getElement();
			}
		}
		return object;
	}
}