package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.commands.SetOclBodyCommand;
import org.nakeduml.topcased.propertysections.ocl.OclValueComposite;
import org.nakeduml.topcased.propertysections.ocl.OclValueComposite.OclChangeListener;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractOpaqueBodySection extends AbstractTabbedPropertySection{
	protected OclValueComposite oclComposite;
	protected CLabel label;
	protected void handleOclChanged(String oclText){
		if(oclText.trim().length() > 0){
			SetOclBodyCommand cmd = SetOclBodyCommand.create(getEditingDomain(), getOclContext(), getBodiesFeature(), getLanguagesFeature(), oclText);
			getEditingDomain().getCommandStack().execute(cmd);
		}
	}
	public abstract EAttribute getLanguagesFeature();
	public abstract EAttribute getBodiesFeature();
	private String getOclBody(){
		EList<String> bodies = getBodies();
		EList<String> languages = getLanguages();
		for(int i = 0;i < languages.size();i++){
			if(languages.get(i).equalsIgnoreCase("ocl")){
				if(bodies.size() > i){
					return bodies.get(i);
				}
			}
		}
		return OclValueComposite.DEFAULT_TEXT;
	}
	protected EList<String> getBodies(){
		return getCollectionValue(getBodiesFeature());
	}
	protected EList<String> getLanguages(){
		return getCollectionValue(getLanguagesFeature());
	}
	private EList<String> getCollectionValue(EAttribute fe){
		NamedElement ctx = getOclContext();
		if(ctx == null){
			return new BasicEList<String>();
		}else{
			return (EList<String>) ctx.eGet(fe);
		}
	}
	@Override
	protected String getLabelText(){
		return "Body";
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		oclComposite.setValueElement(getOclContext());
	}
	protected NamedElement getOclContext(){
		return (NamedElement) getEObject();
	}
	protected void createWidgets(Composite composite){
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = new OclValueComposite(composite, getWidgetFactory(), new OclChangeListener(){
			@Override
			public void oclChanged(String oclText){
				handleOclChanged(oclText);
			}
		});
		oclComposite.setBackground(composite.getBackground());
	}
	protected String getExpressionLabel(){
		return "Body expression";
	}
	protected void setSectionData(Composite composite){
		FormData labelFd = new FormData();
		labelFd.left = new FormAttachment(0, 0);
		this.label.setLayoutData(labelFd);
		FormData fd = new FormData(400, 400);
		fd.right = new FormAttachment(100, 0);
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		fd.height = 50;
		this.oclComposite.setLayoutData(fd);
	}
	public void refresh(){
		super.refresh();
		oclComposite.getTextControl().setText(getOclBody());
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(oclComposite.getTextControl() != null){
			oclComposite.getTextControl().setEnabled(enabled);
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
}
