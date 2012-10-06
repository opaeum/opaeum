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
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.impl.UserInterfaceRootImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.AbstractEditorImpl#getActionBar <em>Action Bar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractEditorImpl extends UserInterfaceRootImpl implements AbstractEditor {
	/**
	 * The cached value of the '{@link #getPages() <em>Pages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPages()
	 * @generated
	 * @ordered
	 */
	protected EList<EditorPage> pages;

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
	public EList<EditorPage> getPages() {
		if (pages == null) {
			pages = new EObjectContainmentWithInverseEList<EditorPage>(EditorPage.class, this, EditorPackage.ABSTRACT_EDITOR__PAGES, EditorPackage.EDITOR_PAGE__EDITOR);
		}
		return pages;
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
				msgs = ((InternalEObject)actionBar).eInverseRemove(this, EditorPackage.ACTION_BAR__EDITOR, ActionBar.class, msgs);
			if (newActionBar != null)
				msgs = ((InternalEObject)newActionBar).eInverseAdd(this, EditorPackage.ACTION_BAR__EDITOR, ActionBar.class, msgs);
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPages()).basicAdd(otherEnd, msgs);
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				if (actionBar != null)
					msgs = ((InternalEObject)actionBar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_EDITOR__ACTION_BAR, null, msgs);
				return basicSetActionBar((ActionBar)otherEnd, msgs);
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
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				return ((InternalEList<?>)getPages()).basicRemove(otherEnd, msgs);
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return basicSetActionBar(null, msgs);
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
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				return getPages();
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return getActionBar();
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
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				getPages().clear();
				getPages().addAll((Collection<? extends EditorPage>)newValue);
				return;
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				setActionBar((ActionBar)newValue);
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
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				getPages().clear();
				return;
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				setActionBar((ActionBar)null);
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
			case EditorPackage.ABSTRACT_EDITOR__PAGES:
				return pages != null && !pages.isEmpty();
			case EditorPackage.ABSTRACT_EDITOR__ACTION_BAR:
				return actionBar != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractEditorImpl
