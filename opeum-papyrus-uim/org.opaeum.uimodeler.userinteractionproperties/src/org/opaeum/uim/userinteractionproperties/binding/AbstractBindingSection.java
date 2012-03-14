package org.opaeum.uim.userinteractionproperties.binding;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.userinteractionproperties.sections.TextControlAdapter;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

public abstract class AbstractBindingSection extends AbstractTextPropertySection{
	private BindingHelper bindingHelper;
	protected abstract Object getFeatureValue();
	protected abstract EClass getFeatureEClass();
	protected UimBinding getBinding(){
		return (UimBinding) getFeatureValue();
	}
	@Override
	protected Object getOldFeatureValue(){
		return getFeatureValue();
	}
	@Override
	protected void verifyField(Event e){
	}
	@Override
	public IText getTextWidget(Composite parent,int style){
		TextControlAdapter text = new TextControlAdapter(parent, style, getBindingHelper());
		text.setLayoutData(new FormData());
		return text;
	}
	@Override
	protected String getFeatureAsString(){
		return getBindingHelper().getFeatureAsString();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		getBindingHelper().setOwner((UimComponent) getEObject());
	}
	public BindingHelper getBindingHelper(){
		if(bindingHelper==null){
			bindingHelper=new BindingHelper((EReference) getFeature());
		}
		return bindingHelper;
	}
	@Override
	protected Object getNewFeatureValue(String newText){
		return getBindingHelper().getNewFeatureValue(newText);
	}
	

}
