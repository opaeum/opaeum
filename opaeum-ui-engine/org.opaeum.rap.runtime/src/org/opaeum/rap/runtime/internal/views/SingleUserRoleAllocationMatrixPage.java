/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.views;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SingleUserRoleAllocationMatrixPage extends WizardPage{
	private UserRoleAllocationWizardData model ;

	protected SingleUserRoleAllocationMatrixPage(UserRoleAllocationWizardData model){
		super("Allocate single user roles");
		this.model =model;
	}

	public void createControl(Composite parent){
		setControl(		new SingleUserRoleAllocationTable(parent, SWT.BORDER,model));

	}
}
