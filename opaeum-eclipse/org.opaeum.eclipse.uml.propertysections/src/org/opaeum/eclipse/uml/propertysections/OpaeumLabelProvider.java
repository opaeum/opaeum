package org.opaeum.eclipse.uml.propertysections;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.UMLPlugin;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumLabelProvider extends LabelProvider{
	OpaeumItemProviderAdapterFactory factory = new OpaeumItemProviderAdapterFactory();
	@Override
	public String getText(Object element){
		EObject eo = findEObject(element);
		IItemLabelProvider p = getProvider(eo);
		if(p == null){
			return super.getText(eo);
		}else{
			return p.getText(eo);
		}
	}
	@Override
	public Image getImage(Object element){
		EObject eo = findEObject(element);
		IItemLabelProvider p = getProvider(eo);
		if(p == null){
			return super.getImage(eo);
		}else{
			ComposedImage composedImage = (ComposedImage) p.getImage(eo);
			URL url = (URL) composedImage.getImages().get(0);
			try{
				return new Image(Display.getDefault(), url.openStream());
			}catch(IOException e){
				return null;
			}
		}
	}
	protected IItemLabelProvider getProvider(EObject eo){
		IItemLabelProvider p = null;
		if(eo != null){
			Adapter ad = factory.createAdapter(eo);
			p = (IItemLabelProvider) ad;
		}
		return p;
	}
	protected EObject findEObject(Object element){
		EObject eo = null;
		if(element instanceof IStructuredSelection){
			element = ((IStructuredSelection) element).getFirstElement();
		}
		if(element instanceof EObject){
			eo = (EObject) element;
		}else if(element instanceof IAdaptable){
			IAdaptable adaptable = (IAdaptable) element;
			eo = (EObject) adaptable.getAdapter(EObject.class);
		}
		return eo;
	}
}
