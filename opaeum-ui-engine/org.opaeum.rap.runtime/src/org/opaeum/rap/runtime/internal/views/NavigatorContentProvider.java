package org.opaeum.rap.runtime.internal.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.ModelAdapter;
import org.eclipse.rap.rms.data.ModelEvent;
import org.eclipse.rap.rms.data.ModelListener;
import org.eclipse.swt.widgets.Display;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.organization.IPersonNode;

public class NavigatorContentProvider implements ITreeContentProvider {

	private static final long serialVersionUID = -2355313344107959671L;
	private StructuredViewer viewer;
	private OpaeumRapSession opaeumSession;

	public NavigatorContentProvider(OpaeumRapSession opaeumRapSession) {
		this.opaeumSession=opaeumRapSession;
	}

	public void inputChanged(final Viewer viewer, final Object oldInput,
			final Object newInput) {
		this.viewer = (StructuredViewer) viewer;
	}

	public void dispose() {
	}

	public Object[] getElements(final Object parent) {
		return getChildren(parent);
	}

	public Object getParent(final Object child) {
		if (child instanceof PersistentObjectTreeItem) {
			return ((PersistentObjectTreeItem) child).getParent();
		} else if (child instanceof PropertyTreeItem) {
			return ((PropertyTreeItem) child).getParent();
		} else {
			return null;
		}
	}

	public Object[] getChildren(final Object parent) {
		if (parent instanceof IPersonNode) {
			Collection<Object> result = new HashSet<Object>();
			for (IBusinessRoleBase br : ((IPersonNode) parent)
					.getBusinessRole()) {
				result.add(new PersistentObjectTreeItem(parent,
						(IPersistentObject) br.getOwningObject()));
			}
			return result.toArray();
		} else if (parent instanceof PersistentObjectTreeItem) {
			return ((PersistentObjectTreeItem) parent).getChildren();
		} else if (parent instanceof PropertyTreeItem) {
			return ((PropertyTreeItem) parent).getChildren();
		}
		return new Object[0];
	}

	public boolean hasChildren(final Object parent) {
		return getChildren(parent).length > 0;
	}

	public void doEntityCreated(final ModelEvent evt) {
		EntityAdapter.refreshAssociations(evt.getEntity(), viewer);
		if (viewer.getControl().getDisplay() == Display.getCurrent()) {
			ISelection selection = new StructuredSelection(evt.getEntity());
			viewer.setSelection(selection, true);
		}
	}
}