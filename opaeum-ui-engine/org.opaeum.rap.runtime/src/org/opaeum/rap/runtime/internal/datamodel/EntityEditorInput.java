/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.ConversationalPersistence;

public class EntityEditorInput implements IEditorInput , IValueChangeListener{
	private final String name;
	private final ImageDescriptor imageDescriptor;
	private final IPersistentObject entity;
	private OpaeumRapSession opaeumSession;
	private DataBindingContext dataBindingContext;
	private ConversationalPersistence persistence;
	private boolean dirty;

	public EntityEditorInput(final IPersistentObject entity,
			final String name, final ImageDescriptor imageDescriptor, OpaeumRapSession opaeumSession) {
		this.dataBindingContext=PageUtil.createBindingContext();
		persistence=opaeumSession.getApplication().getEnvironment().createConversationalPersistence();
		this.entity = persistence.find(IntrospectionUtil.getOriginalClass(entity), entity.getId());
		this.name = name;
		this.imageDescriptor = imageDescriptor;
		this.opaeumSession=opaeumSession;
	}
	public boolean isDirty(){
		return dirty;
	}
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	public String getName() {
		return name;
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	@SuppressWarnings("rawtypes")//$NON-NLS-1$
	public Object getAdapter(final Class adapter) {
		Object result = null;
		if (adapter == IPersistentObject.class) {
			result = entity;
		}
		return result;
	}

	public OpaeumRapSession getOpaeumSession(){
		return opaeumSession;
	}

	public IPersistentObject getPersistentObject(){
		return entity;
	}

	public DataBindingContext getDataBindingContext(){
		return dataBindingContext;
	}
	public ConversationalPersistence getPersistence(){
		return persistence;
	}
	public void handleValueChange(ValueChangeEvent event){
		dirty=true;
	}

}