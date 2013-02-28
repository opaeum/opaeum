package org.opaeum.runtime.jface.navigator;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.rwt.Activator;

public class ConfigurableLabelProvider extends LabelProvider{
	private static final long serialVersionUID = -4725556006185541567L;
	public String getText(final Object element){
		if(element instanceof IPersonNode){
			return ((IPersonNode) element).getName();
		}else if(element instanceof PersistentObjectTreeItem){
			IPersistentObject po = ((PersistentObjectTreeItem) element).getEntity();
			return "<" + IntrospectionUtil.getOriginalClass(po).getSimpleName() + ">" + po.getName();
		}else if(element instanceof PropertyTreeItem){
			return NameConverter.capitalize(NameConverter.toPlural(((PropertyTreeItem) element).getTypedElement().getName()));
		}
		return "";
	}
	public Image getImage(final Object element){
		return Activator.getDefault().getImage(Activator.IMG_PROJECT);
	}
}