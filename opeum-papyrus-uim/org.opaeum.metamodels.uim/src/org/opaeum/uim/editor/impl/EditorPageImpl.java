/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.impl.PageImpl;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.EditorPageImpl#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.EditorPageImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EditorPageImpl extends PageImpl implements EditorPage {
	/**
	 * The cached value of the '{@link #getEditor() <em>Editor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditor()
	 * @generated
	 * @ordered
	 */
	protected AbstractEditor editor;
	/**
	 * The cached value of the '{@link #getPanel() <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanel()
	 * @generated
	 * @ordered
	 */
	protected AbstractPanel panel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditorPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.EDITOR_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractEditor getEditor() {
		if (editor != null && editor.eIsProxy()) {
			InternalEObject oldEditor = (InternalEObject)editor;
			editor = (AbstractEditor)eResolveProxy(oldEditor);
			if (editor != oldEditor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EditorPackage.EDITOR_PAGE__EDITOR, oldEditor, editor));
			}
		}
		return editor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractEditor basicGetEditor() {
		return editor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditor(AbstractEditor newEditor) {
		AbstractEditor oldEditor = editor;
		editor = newEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.EDITOR_PAGE__EDITOR, oldEditor, editor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractPanel getPanel() {
		return panel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPanel(AbstractPanel newPanel, NotificationChain msgs) {
		AbstractPanel oldPanel = panel;
		panel = newPanel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.EDITOR_PAGE__PANEL, oldPanel, newPanel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanel(AbstractPanel newPanel) {
		if (newPanel != panel) {
			NotificationChain msgs = null;
			if (panel != null)
				msgs = ((InternalEObject)panel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.EDITOR_PAGE__PANEL, null, msgs);
			if (newPanel != null)
				msgs = ((InternalEObject)newPanel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EditorPackage.EDITOR_PAGE__PANEL, null, msgs);
			msgs = basicSetPanel(newPanel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.EDITOR_PAGE__PANEL, newPanel, newPanel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.EDITOR_PAGE__PANEL:
				return basicSetPanel(null, msgs);
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
			case EditorPackage.EDITOR_PAGE__EDITOR:
				if (resolve) return getEditor();
				return basicGetEditor();
			case EditorPackage.EDITOR_PAGE__PANEL:
				return getPanel();
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
			case EditorPackage.EDITOR_PAGE__EDITOR:
				setEditor((AbstractEditor)newValue);
				return;
			case EditorPackage.EDITOR_PAGE__PANEL:
				setPanel((AbstractPanel)newValue);
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
			case EditorPackage.EDITOR_PAGE__EDITOR:
				setEditor((AbstractEditor)null);
				return;
			case EditorPackage.EDITOR_PAGE__PANEL:
				setPanel((AbstractPanel)null);
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
			case EditorPackage.EDITOR_PAGE__EDITOR:
				return editor != null;
			case EditorPackage.EDITOR_PAGE__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //EditorPageImpl
