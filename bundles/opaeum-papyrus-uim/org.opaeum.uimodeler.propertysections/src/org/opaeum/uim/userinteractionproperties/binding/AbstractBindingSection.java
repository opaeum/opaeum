package org.opaeum.uim.userinteractionproperties.binding;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.userinteractionproperties.sections.TextControlAdapter;

public abstract class AbstractBindingSection extends AbstractStringPropertySection{
	private BindingHelper bindingHelper;
	protected abstract Object getFeatureValue();
	protected abstract EClass getFeatureEClass();
	protected UimBinding getBinding(){
		return (UimBinding) getFeatureValue();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		new TextControlAdapter(text, getBindingHelper());
	}
	@Override
	protected String getFeatureAsString(EObject e){
		getBindingHelper().setOwner((UimComponent) e);
		return getBindingHelper().getFeatureAsString();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		getBindingHelper().setOwner((UimComponent) getSelectedObject());
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
