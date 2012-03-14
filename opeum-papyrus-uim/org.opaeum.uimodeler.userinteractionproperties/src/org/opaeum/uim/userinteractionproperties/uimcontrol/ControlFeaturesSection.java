package org.opaeum.uim.userinteractionproperties.uimcontrol;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uim.UimField;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimLookup;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class ControlFeaturesSection extends AbstractTabbedPropertySection{

	private Composite composite;
	private ControlFeaturesComposite<?> controlFeaturesComposite;
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}

	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	protected void createWidgets(Composite composite){
		this.composite=composite;
	}
	@Override
	public void refresh(){
		super.refresh();
		UimField f = (UimField) getEObject();
		if(f!=null){
			if(this.controlFeaturesComposite!=null){
				this.controlFeaturesComposite.dispose();
			}
			switch(f.getControlKind()){
			case LINK:
				this.controlFeaturesComposite= new UimLinkFeaturesComposite(composite, SWT.BORDER,getWidgetFactory());
				break;
			case DROPDOWN:
			case POPUP_SEARCH:
			case SELECTION_TABLE:
			case LIST_BOX:
			case TREE_VIEW:
				this.controlFeaturesComposite= new UimLookupFeaturesSection<UimLookup>(composite, SWT.BORDER);
				break;
			default:
				this.controlFeaturesComposite= new ControlFeaturesComposite<UimControl>(composite, SWT.BORDER);
				break;
			}
			this.controlFeaturesComposite.setControl(f.getControl(),getEditingDomain());
			this.controlFeaturesComposite.createContent();
			this.controlFeaturesComposite.refresh();
			FormData fd = new FormData();
			fd.top=new FormAttachment(0,0);
			fd.left=new FormAttachment(0,0);
			fd.bottom=new FormAttachment(100,0);
			fd.right=new FormAttachment(100,0);
			this.controlFeaturesComposite.setLayoutData(fd);
			controlFeaturesComposite.layout();
		}
		composite.layout();
		composite.getParent().getParent().layout();
	}
}
