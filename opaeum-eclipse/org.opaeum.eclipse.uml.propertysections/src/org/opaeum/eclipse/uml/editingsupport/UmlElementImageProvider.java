package org.opaeum.eclipse.uml.editingsupport;

import java.io.IOException;
import java.net.URL;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.edit.UMLEditPlugin;
import org.opaeum.eclipse.ImageManager;

public final class UmlElementImageProvider extends ColumnLabelProvider{
	@Override
	public String getText(Object element){
		return null;
	}
	@Override
	public Image getImage(Object element){
		URL url=null;
		if(element instanceof Element){
			Element e = (Element) element;
			url = (URL) UMLEditPlugin.getPlugin().getImage("full/obj16/" + e.eClass().getName() + ".gif");
		}else if(element instanceof EObject){
			EObject e = (EObject) element;
			url = (URL) EcoreEditPlugin.getPlugin().getImage("full/obj16/" + e.eClass().getName() + ".gif");
		}
		try{
			return new Image(Display.getDefault(), url.openStream());
		}catch(IOException ioe){
			return ImageManager.IMG_UNKNOWN;
		}
	}
}