/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.impl.UmlReferenceImpl;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.HiddenCompositeProperty;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hidden Composite Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.HiddenCompositePropertyImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HiddenCompositePropertyImpl extends UmlReferenceImpl implements HiddenCompositeProperty {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HiddenCompositePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.HIDDEN_COMPOSITE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration getExplorerConfiguration() {
		if (eContainerFeatureID() != PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION) return null;
		return (ExplorerConfiguration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExplorerConfiguration, PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration) {
		if (newExplorerConfiguration != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION && newExplorerConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newExplorerConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_COMPOSITE_PROPERTIES, ExplorerConfiguration.class, msgs);
			msgs = basicSetExplorerConfiguration(newExplorerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION, newExplorerConfiguration, newExplorerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				return basicSetExplorerConfiguration(null, msgs);
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_COMPOSITE_PROPERTIES, ExplorerConfiguration.class, msgs);
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)newValue);
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)null);
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
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration() != null;
		}
		return super.eIsSet(featureID);
	}

} //HiddenCompositePropertyImpl
