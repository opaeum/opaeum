/**
 */
package org.opaeum.uim.perspective.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.ViewAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Perspective</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl#getEditorConfiguration <em>Editor Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl#getPropertiesConfiguration <em>Properties Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimPerspectiveImpl extends EObjectImpl implements UimPerspective {
	/**
	 * The cached value of the '{@link #getExplorerConfiguration() <em>Explorer Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplorerConfiguration()
	 * @generated
	 * @ordered
	 */
	protected ExplorerConfiguration explorerConfiguration;

	/**
	 * The cached value of the '{@link #getEditorConfiguration() <em>Editor Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorConfiguration()
	 * @generated
	 * @ordered
	 */
	protected EditorConfiguration editorConfiguration;

	/**
	 * The cached value of the '{@link #getPropertiesConfiguration() <em>Properties Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertiesConfiguration()
	 * @generated
	 * @ordered
	 */
	protected PropertiesConfiguration propertiesConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimPerspectiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.UIM_PERSPECTIVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration getExplorerConfiguration() {
		return explorerConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration, NotificationChain msgs) {
		ExplorerConfiguration oldExplorerConfiguration = explorerConfiguration;
		explorerConfiguration = newExplorerConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, oldExplorerConfiguration, newExplorerConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration) {
		if (newExplorerConfiguration != explorerConfiguration) {
			NotificationChain msgs = null;
			if (explorerConfiguration != null)
				msgs = ((InternalEObject)explorerConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, null, msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, null, msgs);
			msgs = basicSetExplorerConfiguration(newExplorerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, newExplorerConfiguration, newExplorerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorConfiguration getEditorConfiguration() {
		return editorConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditorConfiguration(EditorConfiguration newEditorConfiguration, NotificationChain msgs) {
		EditorConfiguration oldEditorConfiguration = editorConfiguration;
		editorConfiguration = newEditorConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION, oldEditorConfiguration, newEditorConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorConfiguration(EditorConfiguration newEditorConfiguration) {
		if (newEditorConfiguration != editorConfiguration) {
			NotificationChain msgs = null;
			if (editorConfiguration != null)
				msgs = ((InternalEObject)editorConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION, null, msgs);
			if (newEditorConfiguration != null)
				msgs = ((InternalEObject)newEditorConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION, null, msgs);
			msgs = basicSetEditorConfiguration(newEditorConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION, newEditorConfiguration, newEditorConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesConfiguration getPropertiesConfiguration() {
		return propertiesConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertiesConfiguration(PropertiesConfiguration newPropertiesConfiguration, NotificationChain msgs) {
		PropertiesConfiguration oldPropertiesConfiguration = propertiesConfiguration;
		propertiesConfiguration = newPropertiesConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION, oldPropertiesConfiguration, newPropertiesConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertiesConfiguration(PropertiesConfiguration newPropertiesConfiguration) {
		if (newPropertiesConfiguration != propertiesConfiguration) {
			NotificationChain msgs = null;
			if (propertiesConfiguration != null)
				msgs = ((InternalEObject)propertiesConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION, null, msgs);
			if (newPropertiesConfiguration != null)
				msgs = ((InternalEObject)newPropertiesConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION, null, msgs);
			msgs = basicSetPropertiesConfiguration(newPropertiesConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION, newPropertiesConfiguration, newPropertiesConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				return basicSetExplorerConfiguration(null, msgs);
			case PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION:
				return basicSetEditorConfiguration(null, msgs);
			case PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION:
				return basicSetPropertiesConfiguration(null, msgs);
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
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
			case PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION:
				return getEditorConfiguration();
			case PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION:
				return getPropertiesConfiguration();
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
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)newValue);
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION:
				setEditorConfiguration((EditorConfiguration)newValue);
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION:
				setPropertiesConfiguration((PropertiesConfiguration)newValue);
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
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)null);
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION:
				setEditorConfiguration((EditorConfiguration)null);
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION:
				setPropertiesConfiguration((PropertiesConfiguration)null);
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
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				return explorerConfiguration != null;
			case PerspectivePackage.UIM_PERSPECTIVE__EDITOR_CONFIGURATION:
				return editorConfiguration != null;
			case PerspectivePackage.UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION:
				return propertiesConfiguration != null;
		}
		return super.eIsSet(featureID);
	}

} //UimPerspectiveImpl
