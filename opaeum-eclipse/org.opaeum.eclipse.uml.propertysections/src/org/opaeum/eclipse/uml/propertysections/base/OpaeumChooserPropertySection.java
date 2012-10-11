package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumSingleObjectChooser;
import org.opaeum.eclipse.uml.propertysections.core.EObjectNavigationSource;
import org.opaeum.eclipse.uml.propertysections.core.NavigationDecorator;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public abstract class OpaeumChooserPropertySection extends AbstractOpaeumPropertySection implements EObjectNavigationSource,IChoiceProvider{
	public NavigationDecorator decorator = new NavigationDecorator(this);
	private boolean isRefreshing = false;
	protected OpaeumSingleObjectChooser cSingleObjectChooser;
	@Override
	public Object[] getChoices(){
		return getComboFeatureValues();
	}
	protected Object[] getChoices(EObject object,EClassifier type){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		choices.addAll(typeCacheAdapter.getReachableObjectsOfType(object, type));
		return choices.toArray();
	}
	@Override
	public EObject getEObjectToGoTo(){
		return (EObject) getFeatureValue();
	}
	@Override
	public Control getPrimaryInput(){
		return cSingleObjectChooser.getContentPane();
	}
	protected abstract Object[] getComboFeatureValues();
	public void refresh(){
		if(getEObject().eContainer() != null){// Hack - eclipse calls refresh even if the object was deleted
			isRefreshing = true;
			cSingleObjectChooser.setSelection(getFeatureValue());
			decorator.refresh();
			super.refresh();
			isRefreshing = false;
		}
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	@Override
	protected void createWidgets(Composite composite){
		cSingleObjectChooser = new OpaeumSingleObjectChooser(composite, getWidgetFactory(), SWT.NONE);
		if(getFeature() != null){
			cSingleObjectChooser.setChangeable(getFeature().isChangeable());
		}
		cSingleObjectChooser.setChoiceProvider(this);
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(labelCombo, 0, SWT.CENTER);
		cSingleObjectChooser.getContentPane().setLayoutData(data);
	}
	@Override
	protected void hookListeners(){
		cSingleObjectChooser.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				handleComboModified();
			}
		});
	}
	protected void handleComboModified(){
		if(!isRefreshing){
			createCommand(getFeatureValue(), cSingleObjectChooser.getSelection());
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(cSingleObjectChooser != null){
			cSingleObjectChooser.setEnabled(enabled);
		}
	}
	protected boolean isRefreshing(){
		return isRefreshing;
	}
	protected abstract Object getFeatureValue();
}
