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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.impl.UmlReferenceImpl;

import org.opaeum.uim.perspective.ExplorerClassConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerPropertyConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explorer Class Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl#getIsVisible <em>Is Visible</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl#getConfiguredProperties <em>Configured Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplorerClassConfigurationImpl extends UmlReferenceImpl implements ExplorerClassConfiguration {
	/**
	 * The default value of the '{@link #getIsVisible() <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVisible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_VISIBLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsVisible() <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVisible()
	 * @generated
	 * @ordered
	 */
	protected Boolean isVisible = IS_VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfiguredProperties() <em>Configured Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguredProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerPropertyConfiguration> configuredProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerClassConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.EXPLORER_CLASS_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration getExplorerConfiguration() {
		if (eContainerFeatureID() != PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION) return null;
		return (ExplorerConfiguration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExplorerConfiguration, PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration) {
		if (newExplorerConfiguration != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION && newExplorerConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newExplorerConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES, ExplorerConfiguration.class, msgs);
			msgs = basicSetExplorerConfiguration(newExplorerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION, newExplorerConfiguration, newExplorerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsVisible() {
		return isVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVisible(Boolean newIsVisible) {
		Boolean oldIsVisible = isVisible;
		isVisible = newIsVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE, oldIsVisible, isVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExplorerPropertyConfiguration> getConfiguredProperties() {
		if (configuredProperties == null) {
			configuredProperties = new EObjectContainmentEList<ExplorerPropertyConfiguration>(ExplorerPropertyConfiguration.class, this, PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES);
		}
		return configuredProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExplorerConfiguration((ExplorerConfiguration)otherEnd, msgs);
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
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				return basicSetExplorerConfiguration(null, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES:
				return ((InternalEList<?>)getConfiguredProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES, ExplorerConfiguration.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE:
				return getIsVisible();
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES:
				return getConfiguredProperties();
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
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE:
				setIsVisible((Boolean)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES:
				getConfiguredProperties().clear();
				getConfiguredProperties().addAll((Collection<? extends ExplorerPropertyConfiguration>)newValue);
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
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)null);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE:
				setIsVisible(IS_VISIBLE_EDEFAULT);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES:
				getConfiguredProperties().clear();
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
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration() != null;
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE:
				return IS_VISIBLE_EDEFAULT == null ? isVisible != null : !IS_VISIBLE_EDEFAULT.equals(isVisible);
			case PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES:
				return configuredProperties != null && !configuredProperties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isVisible: ");
		result.append(isVisible);
		result.append(')');
		return result.toString();
	}

} //ExplorerClassConfigurationImpl
