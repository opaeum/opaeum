/*******************************************************************************
 * Copyright (c) 2006 ANYWARE TECHNOLOGIES. All rights reserved. 
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 *               implementation
 *               Jose Alfredo Serrano (Anyware Technologies) - updated API
 ******************************************************************************/
package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractEnumerationPropertySection extends AbstractTabbedPropertySection{
	private Group combo;
	@Override
	protected void createWidgets(Composite composite){
		combo = getWidgetFactory().createGroup(composite, "");
		if(getFeature() != null){
			boolean isChangeable = getFeature().isChangeable();
			combo.setEnabled(isChangeable);
		}
	}
	/**
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		combo.setLayoutData(data);
		CLabel nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(combo, 0, SWT.CENTER);
		nameLabel.setLayoutData(data);
		GridLayout gd = new GridLayout(20,false);
		combo.setLayout(gd);
		gd.horizontalSpacing=0;
		gd.verticalSpacing=0;
		gd.marginWidth=0;
		gd.marginHeight=0;
	}
	@Override
	protected void hookListeners(){
	}
	protected void handleComboModified(String name){
		createCommand(getOldFeatureValue(), getFeatureValue(name));
	}
	@Override
	public void refresh(){
		super.refresh();
		for(Control control:combo.getChildren()){
			control.dispose();
		}
		String[] items = getEnumerationFeatureValues();
		for(final String string:items){
			Button createButton = getWidgetFactory().createButton(combo, string, SWT.RADIO);
			if(string.equals(getFeatureAsText())){
				createButton.setSelection(true);
			}
			createButton.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent event){
					handleComboModified(string);
				}
			});
			createButton.setLayoutData(new GridData(GridData.BEGINNING,GridData.BEGINNING,false,false));
		}
		combo.layout();
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(combo != null){
			combo.setEnabled(enabled);
		}
	}
	protected abstract String[] getEnumerationFeatureValues();
	protected abstract String getFeatureAsText();
	protected abstract Object getFeatureValue(String name);
	protected abstract Object getOldFeatureValue();
}