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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.ViewAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Perspective</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl#getViewAllocations <em>View Allocations</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimPerspectiveImpl extends EObjectImpl implements UimPerspective {
	/**
	 * The cached value of the '{@link #getViewAllocations() <em>View Allocations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<ViewAllocation> viewAllocations;

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
	public EList<ViewAllocation> getViewAllocations() {
		if (viewAllocations == null) {
			viewAllocations = new EObjectContainmentWithInverseEList<ViewAllocation>(ViewAllocation.class, this, PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS, PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE);
		}
		return viewAllocations;
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
				msgs = ((InternalEObject)explorerConfiguration).eInverseRemove(this, PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE, ExplorerConfiguration.class, msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE, ExplorerConfiguration.class, msgs);
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getViewAllocations()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				if (explorerConfiguration != null)
					msgs = ((InternalEObject)explorerConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, null, msgs);
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
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				return ((InternalEList<?>)getViewAllocations()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				return getViewAllocations();
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
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
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				getViewAllocations().clear();
				getViewAllocations().addAll((Collection<? extends ViewAllocation>)newValue);
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
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
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				getViewAllocations().clear();
				return;
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
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
			case PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS:
				return viewAllocations != null && !viewAllocations.isEmpty();
			case PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION:
				return explorerConfiguration != null;
		}
		return super.eIsSet(featureID);
	}

} //UimPerspectiveImpl
