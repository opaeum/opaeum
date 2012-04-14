package org.opaeum.rap.runtime.internal.views;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.organization.IPersonNode;

public class NavigatorContentProvider implements ITreeContentProvider {

	private static final long serialVersionUID = -2355313344107959671L;
	private OpaeumRapSession opaeumSession;
	Map<IPersistentObject,PersistentObjectTreeItem> objectItemMap=new HashMap<IPersistentObject,PersistentObjectTreeItem>();
	
	public NavigatorContentProvider(OpaeumRapSession opaeumRapSession) {
		this.opaeumSession=opaeumRapSession;
	}

	public void inputChanged(final Viewer viewer, final Object oldInput,
			final Object newInput) {
	}

	public void dispose() {
	}
	public PersistentObjectTreeItem getTreeItemFor(IPersistentObject p){
		return objectItemMap.get(p);
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
						(IPersistentObject) br.getOwningObject(),objectItemMap));
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
}