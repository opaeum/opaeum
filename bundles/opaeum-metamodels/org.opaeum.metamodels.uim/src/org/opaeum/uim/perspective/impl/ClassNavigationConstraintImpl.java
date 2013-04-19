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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.perspective.BehaviorNavigationConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl#getBehaviors <em>Behaviors</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassNavigationConstraintImpl extends NavigationConstraintImpl implements ClassNavigationConstraint {
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyNavigationConstraint> properties;

	/**
	 * The cached value of the '{@link #getBehaviors() <em>Behaviors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviors()
	 * @generated
	 * @ordered
	 */
	protected EList<BehaviorNavigationConstraint> behaviors;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationNavigationConstraint> operations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassNavigationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.CLASS_NAVIGATION_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigatorConfiguration getExplorerConfiguration() {
		if (eContainerFeatureID() != PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION) return null;
		return (NavigatorConfiguration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorerConfiguration(NavigatorConfiguration newExplorerConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExplorerConfiguration, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorerConfiguration(NavigatorConfiguration newExplorerConfiguration) {
		if (newExplorerConfiguration != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION && newExplorerConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newExplorerConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, PerspectivePackage.NAVIGATOR_CONFIGURATION__CLASSES, NavigatorConfiguration.class, msgs);
			msgs = basicSetExplorerConfiguration(newExplorerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION, newExplorerConfiguration, newExplorerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyNavigationConstraint> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentWithInverseEList<PropertyNavigationConstraint>(PropertyNavigationConstraint.class, this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BehaviorNavigationConstraint> getBehaviors() {
		if (behaviors == null) {
			behaviors = new EObjectContainmentWithInverseEList<BehaviorNavigationConstraint>(BehaviorNavigationConstraint.class, this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS, PerspectivePackage.BEHAVIOR_NAVIGATION_CONSTRAINT__OWNER);
		}
		return behaviors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationNavigationConstraint> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentWithInverseEList<OperationNavigationConstraint>(OperationNavigationConstraint.class, this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER);
		}
		return operations;
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExplorerConfiguration((NavigatorConfiguration)otherEnd, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProperties()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBehaviors()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperations()).basicAdd(otherEnd, msgs);
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				return basicSetExplorerConfiguration(null, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				return ((InternalEList<?>)getBehaviors()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.NAVIGATOR_CONFIGURATION__CLASSES, NavigatorConfiguration.class, msgs);
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				return getProperties();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				return getBehaviors();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				return getOperations();
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((NavigatorConfiguration)newValue);
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends PropertyNavigationConstraint>)newValue);
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				getBehaviors().clear();
				getBehaviors().addAll((Collection<? extends BehaviorNavigationConstraint>)newValue);
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends OperationNavigationConstraint>)newValue);
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((NavigatorConfiguration)null);
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				getProperties().clear();
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				getBehaviors().clear();
				return;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				getOperations().clear();
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
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration() != null;
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS:
				return behaviors != null && !behaviors.isEmpty();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS:
				return operations != null && !operations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ClassNavigationConstraintImpl
