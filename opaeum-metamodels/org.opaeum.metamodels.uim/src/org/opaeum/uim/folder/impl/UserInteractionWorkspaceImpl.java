/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.folder.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.folder.FolderPackage;
import org.opaeum.uim.folder.UserInteractionWorkspace;
import org.opaeum.uim.security.WorkspaceSecurityConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interaction Workspace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl#getEditability <em>Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInteractionWorkspaceImpl extends AbstractFolderImpl implements UserInteractionWorkspace {
	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected WorkspaceSecurityConstraint visibility;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected WorkspaceSecurityConstraint editability;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInteractionWorkspaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FolderPackage.Literals.USER_INTERACTION_WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceSecurityConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(WorkspaceSecurityConstraint newVisibility, NotificationChain msgs) {
		WorkspaceSecurityConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(WorkspaceSecurityConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceSecurityConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(WorkspaceSecurityConstraint newEditability, NotificationChain msgs) {
		WorkspaceSecurityConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(WorkspaceSecurityConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY:
				return basicSetEditability(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY:
				return getVisibility();
			case FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY:
				return getEditability();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY:
				setVisibility((WorkspaceSecurityConstraint)newValue);
				return;
			case FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY:
				setEditability((WorkspaceSecurityConstraint)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY:
				setVisibility((WorkspaceSecurityConstraint)null);
				return;
			case FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY:
				setEditability((WorkspaceSecurityConstraint)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FolderPackage.USER_INTERACTION_WORKSPACE__VISIBILITY:
				return visibility != null;
			case FolderPackage.USER_INTERACTION_WORKSPACE__EDITABILITY:
				return editability != null;
		}
		return super.eIsSet(featureID);
	}

} //UserInteractionWorkspaceImpl
