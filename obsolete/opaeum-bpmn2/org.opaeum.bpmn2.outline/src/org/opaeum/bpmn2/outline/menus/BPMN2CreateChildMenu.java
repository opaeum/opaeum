/*******************************************************************************
 * 
 * Copyright AIRBUS FRANCE, 2005. All rights reserved.
 * 
 * This document and all information contained herein is the sole property of
 * AIRBUS FRANCE. No intellectual property rights are granted by the delivery of
 * this document or the disclosure of its content.
 * 
 ******************************************************************************/
package org.opaeum.bpmn2.outline.menus;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.topcased.modeler.actions.CreateChildAction;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.outline.ICreateChildMenu;

/**
 * A customized 'Create child' menu manager.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BPMN2CreateChildMenu extends MenuManager implements ICreateChildMenu{
	/**
	 * @generated
	 */
	private static final String UNAPPLIED_ACTIONS_GROUP = "unappliedActions";
	/**
	 * @generated
	 */
	private MixedEditDomain domain;
	/**
	 * @generated
	 */
	private EObject selectedObject;
	/**
	 * @generated
	 */
	private Collection descriptors;
	/**
	 * @generated
	 */
	private MenuManager thisMenu;
	/**
	 * Constructor.
	 * @generated
	 */
	public BPMN2CreateChildMenu(){
		super("Create child");
		thisMenu = this;
	}
	/**
	 * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setMixedEditDomain(org.topcased.modeler.editor.MixedEditDomain)
	 */
	public void setMixedEditDomain(MixedEditDomain domain){
		this.domain = domain;
	}
	/**
	 * @see org.topcased.modeler.editor.outline.ICreateChildMenu#setSelectedEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setSelectedEObject(EObject object){
		selectedObject = object;
	}
	/**
	 * Creates this menu contents.<br>
	 * It creates the menu structure and add all the create child actions.<br> 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void createMenuContents(){
		this.descriptors = domain.getEMFEditingDomain().getNewChildDescriptors(selectedObject, null);
		createMenus(this);
		for(Iterator it = descriptors.iterator();it.hasNext();){
			CommandParameter descriptor = (CommandParameter) it.next();
			EObject child = (EObject) descriptor.getValue();
			CreateChildAction action = new CreateChildAction(domain, selectedObject, descriptor);
			boolean added = false;
			if(!added){
				appendToGroup(UNAPPLIED_ACTIONS_GROUP, action);
			}
		}
	}
	/**
	 * Create this menu structure. <br>
	 * It creates all the menus and groups.<br>
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param menu the create child menu manager 
	 * @generated
	 */
	protected void createMenus(MenuManager menu){
		// Group containing non sorted actions. 
		menu.add(new Separator(UNAPPLIED_ACTIONS_GROUP));
	}
}
