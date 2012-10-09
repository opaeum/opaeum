package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class AbstractMultiFeaturePropertySection extends AbstractTabbedPropertySection implements IMultiPropertySection{
	private List<AbstractTabbedPropertySubsection<?,?>> subsections = new ArrayList<AbstractTabbedPropertySubsection<?,?>>();
	@Override
	public List<EObject> getEObjectList(){
		return super.getEObjectList();
	}
	public BooleanSubSection createBoolean(EStructuralFeature feature,String labelText,int labelWidth){
		BooleanSubSection result = new BooleanSubSection(this);
		result.setLabelWidth(labelWidth);
		result.setLabelText(labelText);
		result.setFeature(feature);
		result.setDefaultValue(false);
		return result;
	}
	public LiteralIntegerSubsection createLiteralInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		LiteralIntegerSubsection result = new LiteralIntegerSubsection(this);
		result.setLabelWidth(labelWidth);
		result.setLabelText(labelText);
		result.setFeature(feature);
		return result;
	}
	public IntegerSubsection createInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		IntegerSubsection result = new IntegerSubsection(this);
		result.setLabelWidth(labelWidth);
		result.setLabelText(labelText);
		result.setFeature(feature);
		return result;
	}
	public void refresh(){
		super.refresh();
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			ss.refresh();
		}
	}
	@Override
	public EditingDomain getEditingDomain(){
		return super.getEditingDomain();
	}
	@Override
	protected void removeListener(){
		super.removeListener();
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			ss.removeModelListener();
		}
	}
	@Override
	protected void addListener(){
		super.addListener();
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			ss.hookModelListener();
		}
	}
	@Override
	protected void setSectionData(Composite composite){
		if(!isDisposed()){
			
			Collection<AbstractTabbedPropertySubsection<?,?>> subsections2 = this.subsections;
			int maxHeight=0;
			for(AbstractTabbedPropertySubsection<?,?> ss:subsections2){
				ss.updateLayoutData();
				maxHeight=Math.max(maxHeight, ss.getComposite().getSize().y);
			}
			Composite prev = null;
			for(AbstractTabbedPropertySubsection<?,?> ss:subsections2){
				FormData fd = new FormData();
				if(prev != null){
					fd.left = new FormAttachment(prev);
				}
				fd.height=maxHeight;
				ss.getComposite().setLayoutData(fd);
				prev = ss.getComposite();
			}
		}
	}
	private boolean isDisposed(){
		for(AbstractTabbedPropertySubsection<?,?> c:subsections){
			if(c.getComposite().isDisposed()){
				return true;
			}
		}
		return false;
	}
	@Override
	public EObject getEObject(){
		return super.getEObject();
	}
	@Override
	protected void createWidgets(org.eclipse.swt.widgets.Composite composite){
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			Composite ssc = getWidgetFactory().createComposite(composite, SWT.BORDER);
			GridLayout gl = new GridLayout(2, false);
			gl.marginWidth=1;
			gl.marginHeight=2;
			gl.verticalSpacing=0;
			gl.horizontalSpacing=4;
			ssc.setLayout(gl);
			ss.createWidgets(ssc);
			ss.hookControlListener();
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public EObject getFeatureOwner(EObject selection){
		return selection;
	}
	@Override
	public void addSubsection(AbstractTabbedPropertySubsection<?,?> ss){
		this.subsections.add(ss);
	}
	@Override
	protected String getLabelText(){
		return "";
	}
}
