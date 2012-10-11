package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.IntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.LiteralIntegerSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;

public abstract class AbstractMultiFeaturePropertySection extends AbstractOpaeumPropertySection implements IMultiPropertySection{
	private List<AbstractTabbedPropertySubsection<?,?>> subsections = new ArrayList<AbstractTabbedPropertySubsection<?,?>>();
	private Composite multiFeatureComposite;
	@Override
	public List<EObject> getEObjectList(){
		return super.getEObjectList();
	}
	@Override
	public Control getPrimaryInput(){
		return multiFeatureComposite;
	}
	public BooleanSubsection createBoolean(EStructuralFeature feature,String labelText,int labelWidth){
		BooleanSubsection result = new BooleanSubsection(this);
		populateSubsection(result, feature, labelText, labelWidth, 40);
		result.setDefaultValue(false);
		return result;
	}
	public LiteralIntegerSubsection createLiteralInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		LiteralIntegerSubsection result = new LiteralIntegerSubsection(this);
		populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public IntegerSubsection createInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		IntegerSubsection result = new IntegerSubsection(this);
		populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public StringSubsection createString(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		StringSubsection result = new StringSubsection(this);
		populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public ChooserSubsection createChooser(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth,IChoiceProvider choiceProvider){
		ChooserSubsection result = new ChooserSubsection(this);
		populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		result.setChoiceProvider(choiceProvider);
		return result;
	}
	public static void populateSubsection(AbstractTabbedPropertySubsection<?,?> ss,EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		ss.setLabelWidth(labelWidth);
		ss.setLabelText(labelText);
		ss.setFeature(feature);
		ss.setControlWidth(controlWidth);
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
			FormData layoutData = new FormData();
			layoutData.left = new FormAttachment(labelCombo, -6);
			layoutData.right = new FormAttachment(100, 0);
			multiFeatureComposite.setLayoutData(layoutData);
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
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		for(AbstractTabbedPropertySubsection<?,?> ss:subsections){
			ss.setEnabled(enabled);
		}
	}
	@Override
	protected void createWidgets(org.eclipse.swt.widgets.Composite parent){
		this.multiFeatureComposite = new Composite(parent, SWT.NONE);
		getWidgetFactory().adapt(multiFeatureComposite);
		GridLayout rl = new GridLayout(subsections.size(), false);
		rl.marginHeight= 0;
//		rl.horizontalSpacing=4;
//		rl.verticalSpacing=0;
		multiFeatureComposite.setLayout(rl);
		int maxHeight = 0;
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			Composite ssc = new Composite(multiFeatureComposite, SWT.NONE);
			getWidgetFactory().adapt(ssc);
			GridLayout gl = new GridLayout(2, false);
			gl.marginWidth = 5;
			gl.marginHeight = 0;
			gl.verticalSpacing = 0;
			gl.horizontalSpacing = 4;
			ssc.setLayout(gl);
			ss.createWidgets(ssc);
			ss.hookControlListener();
			ss.updateLayoutData();
			maxHeight = Math.max(maxHeight, ss.getComposite().getSize().y);
			GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			if(ss.getRowSpan() != null){
				gd.verticalSpan = ss.getRowSpan();
			}
			if(ss.getColumnSpan() != null){
				gd.horizontalSpan = ss.getColumnSpan();
			}
			ss.getComposite().setLayoutData(gd);
			maxHeight = Math.max(maxHeight, ss.getComposite().getSize().y);
		}
		for(AbstractTabbedPropertySubsection<?,?> ss:subsections){
			GridData gd = (GridData) ss.getComposite().getLayoutData();
			gd.minimumHeight = 25;//maxHeight;
			gd.heightHint=25;
			ss.getComposite().setLayoutData(gd);
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
}
