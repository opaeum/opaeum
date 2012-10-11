package org.opaeum.eclipse.uml.propertysections.subsections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;

public abstract class SubsectionComposite<T extends EObject> implements IMultiPropertySection{
	protected EditingDomain mixedEditDomain;
	protected TabbedPropertySheetWidgetFactory widgetFactory;
	private List<AbstractTabbedPropertySubsection<?,?>> subsections = new ArrayList<AbstractTabbedPropertySubsection<?,?>>();
	protected T selectedObject;
	protected Composite contentPane;
	public SubsectionComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super();
		this.widgetFactory = widgetFactory;
		contentPane=widgetFactory.createComposite(parent,style);
		GridLayout layout = new GridLayout(getNumberOfColumns(), false);
		layout.marginHeight=0;
		layout.verticalSpacing=3;
		contentPane.setLayout(layout);
		addSubsections();
		createWidgets();
		hookControlListeners();
		setEnabled(false);
	}
	private void hookControlListeners(){
		for(AbstractTabbedPropertySubsection<?,?> ss:subsections){
			ss.hookControlListener();
		}
	}
	public void setEnabled(boolean enabled){
		contentPane.setEnabled(enabled);
		for(AbstractTabbedPropertySubsection<?,?> ss:subsections){
			ss.setEnabled(enabled);
		}
	}
	public void setSelection(T s){
		setEnabled(s != null);
		if(selectedObject != s){
			removeListener();
			this.selectedObject = s;
			for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
				ss.refresh();
			}
			addListener();
		}
	}
	protected int getNumberOfColumns(){
		return 2;
	}
	protected void removeListener(){
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			ss.removeModelListener();
		}
	}
	protected void addListener(){
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			ss.hookModelListener();
		}
	}
	protected void createWidgets(){
		for(AbstractTabbedPropertySubsection<?,?> ss:this.subsections){
			Composite ssc = new Composite(contentPane, SWT.NONE);
			getWidgetFactory().adapt(ssc);
			GridLayout gl = new GridLayout(2, false);
			gl.marginWidth = 5;
			gl.marginHeight = 0;
			gl.verticalSpacing = 0;
			gl.horizontalSpacing = 4;
			ssc.setLayout(gl);
			ss.createWidgets(ssc);
			ss.hookControlListener();
		}
	}
	public void setLayoutData(Object data){
		contentPane.setLayoutData(data);
		int maxHeight = 5;
		for(AbstractTabbedPropertySubsection<?,?> ss:subsections){
			ss.updateLayoutData();
			GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true);
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
			gd.minimumHeight = maxHeight;
			gd.heightHint=maxHeight;
			ss.getComposite().setLayoutData(gd);
		}
	}
	protected abstract void addSubsections();
	@Override
	public TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return widgetFactory;
	}
	@Override
	public EObject getFeatureOwner(EObject selection){
		return selectedObject;
	}
	@Override
	public EditingDomain getEditingDomain(){
		return mixedEditDomain;
	}
	@Override
	public Collection<EObject> getEObjectList(){
		return new BasicEList<EObject>(Arrays.asList(selectedObject));
	}
	@Override
	public EObject getEObject(){
		return selectedObject;
	}
	@Override
	public void addSubsection(AbstractTabbedPropertySubsection<?,?> ss){
		subsections.add(ss);
	}
	public BooleanSubsection createBoolean(EStructuralFeature feature,String labelText,int labelWidth){
		BooleanSubsection result = new BooleanSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, 40);
		result.setDefaultValue(false);
		return result;
	}
	public LiteralIntegerSubsection createLiteralInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		LiteralIntegerSubsection result = new LiteralIntegerSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public IntegerSubsection createInteger(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		IntegerSubsection result = new IntegerSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public StringSubsection createString(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth){
		StringSubsection result = new StringSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		return result;
	}
	public ChooserSubsection createChooser(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth,IChoiceProvider choiceProvider){
		ChooserSubsection result = new ChooserSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		result.setChoiceProvider(choiceProvider);
		return result;
	}
	public ComboSubsection createCombo(EStructuralFeature feature,String labelText,int labelWidth,int controlWidth,IChoiceProvider choiceProvider){
		ComboSubsection result = new ComboSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(result, feature, labelText, labelWidth, controlWidth);
		result.setChoiceProvider(choiceProvider);
		return result;
	}
}