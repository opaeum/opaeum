/**
 */
package org.opaeum.uim.editor.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.impl.UmlReferenceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getActionBar <em>Action Bar</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractEditorImpl extends UmlReferenceImpl implements AbstractEditor {
	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint editability;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint visibility;

	/**
	 * The cached value of the '{@link #getActionBar() <em>Action Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionBar()
	 * @generated
	 * @ordered
	 */
	protected ActionBar actionBar;

	/**
	 * The cached value of the '{@link #getMenuConfiguration() <em>Menu Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMenuConfiguration()
	 * @generated
	 * @ordered
	 */
	protected MenuConfiguration menuConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractEditorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.ABSTRACT_EDITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(RootUserInteractionConstraint newEditability, NotificationChain msgs) {
		RootUserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(RootUserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(RootUserInteractionConstraint newVisibility, NotificationChain msgs) {
		RootUserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(RootUserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionBar getActionBar() {
		return actionBar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionBar(ActionBar newActionBar, NotificationChain msgs) {
		ActionBar oldActionBar = actionBar;
		actionBar = newActionBar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__ACTION_BAR, oldActionBar, newActionBar);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionBar(ActionBar newActionBar) {
		if (newActionBar != actionBar) {
			NotificationChain msgs = null;
			if (actionBar != null)
				msgs = ((InternalEObject)actionBar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__ACTION_BAR, null, msgs);
			if (newActionBar != null)
				msgs = ((InternalEObject)newActionBar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__ACTION_BAR, null, msgs);
			msgs = basicSetActionBar(newActionBar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__ACTION_BAR, newActionBar, newActionBar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuConfiguration getMenuConfiguration() {
		return menuConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMenuConfiguration(MenuConfiguration newMenuConfiguration, NotificationChain msgs) {
		MenuConfiguration oldMenuConfiguration = menuConfiguration;
		menuConfiguration = newMenuConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION, oldMenuConfiguration, newMenuConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMenuConfiguration(MenuConfiguration newMenuConfiguration) {
		if (newMenuConfiguration != menuConfiguration) {
			NotificationChain msgs = null;
			if (menuConfiguration != null)
				msgs = ((InternalEObject)menuConfiguration).eInverseRemove(this, EditorPackage.MENU_CONFIGURATION__EDITOR, MenuConfiguration.class, msgs);
			if (newMenuConfiguration != null)
				msgs = ((InternalEObject)newMenuConfiguration).eInverseAdd(this, EditorPackage.MENU_CONFIGURATION__EDITOR, MenuConfiguration.class, msgs);
			msgs = basicSetMenuConfiguration(newMenuConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION, newMenuConfiguration, newMenuConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				if (menuConfiguration != null)
					msgs = ((InternalEObject)menuConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION, null, msgs);
				return basicSetMenuConfiguration((MenuConfiguration)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_EDITOR__EDITABILITY:
				return basicSetEditability(null, msgs);
			case EditorPackage.ABSTRACT_EDITOR__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return basicSetActionBar(null, msgs);
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				return basicSetMenuConfiguration(null, msgs);
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
			case EditorPackage.ABSTRACT_EDITOR__EDITABILITY:
				return getEditability();
			case EditorPackage.ABSTRACT_EDITOR__VISIBILITY:
				return getVisibility();
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return getActionBar();
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				return getMenuConfiguration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_EDITOR__EDITABILITY:
				setEditability((RootUserInteractionConstraint)newValue);
				return;
			case EditorPackage.ABSTRACT_EDITOR__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)newValue);
				return;
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				setActionBar((ActionBar)newValue);
				return;
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				setMenuConfiguration((MenuConfiguration)newValue);
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
			case EditorPackage.ABSTRACT_EDITOR__EDITABILITY:
				setEditability((RootUserInteractionConstraint)null);
				return;
			case EditorPackage.ABSTRACT_EDITOR__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)null);
				return;
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				setActionBar((ActionBar)null);
				return;
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				setMenuConfiguration((MenuConfiguration)null);
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
			case EditorPackage.ABSTRACT_EDITOR__EDITABILITY:
				return editability != null;
			case EditorPackage.ABSTRACT_EDITOR__VISIBILITY:
				return visibility != null;
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return actionBar != null;
			case EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION:
				return menuConfiguration != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractEditorImpl
