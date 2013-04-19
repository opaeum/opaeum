/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl#getRemoveConstraint <em>Remove Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl#getAddConstraint <em>Add Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyNavigationConstraintImpl extends NavigationConstraintImpl implements PropertyNavigationConstraint {
	/**
	 * The cached value of the '{@link #getRemoveConstraint() <em>Remove Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoveConstraint()
	 * @generated
	 * @ordered
	 */
	protected NavigationConstraint removeConstraint;

	/**
	 * The cached value of the '{@link #getAddConstraint() <em>Add Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddConstraint()
	 * @generated
	 * @ordered
	 */
	protected NavigationConstraint addConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyNavigationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.PROPERTY_NAVIGATION_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationConstraint getRemoveConstraint() {
		return removeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRemoveConstraint(NavigationConstraint newRemoveConstraint, NotificationChain msgs) {
		NavigationConstraint oldRemoveConstraint = removeConstraint;
		removeConstraint = newRemoveConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, oldRemoveConstraint, newRemoveConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoveConstraint(NavigationConstraint newRemoveConstraint) {
		if (newRemoveConstraint != removeConstraint) {
			NotificationChain msgs = null;
			if (removeConstraint != null)
				msgs = ((InternalEObject)removeConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, null, msgs);
			if (newRemoveConstraint != null)
				msgs = ((InternalEObject)newRemoveConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, null, msgs);
			msgs = basicSetRemoveConstraint(newRemoveConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, newRemoveConstraint, newRemoveConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationConstraint getAddConstraint() {
		return addConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAddConstraint(NavigationConstraint newAddConstraint, NotificationChain msgs) {
		NavigationConstraint oldAddConstraint = addConstraint;
		addConstraint = newAddConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, oldAddConstraint, newAddConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddConstraint(NavigationConstraint newAddConstraint) {
		if (newAddConstraint != addConstraint) {
			NotificationChain msgs = null;
			if (addConstraint != null)
				msgs = ((InternalEObject)addConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, null, msgs);
			if (newAddConstraint != null)
				msgs = ((InternalEObject)newAddConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, null, msgs);
			msgs = basicSetAddConstraint(newAddConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, newAddConstraint, newAddConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassNavigationConstraint getOwner() {
		if (eContainerFeatureID() != PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER) return null;
		return (ClassNavigationConstraint)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(ClassNavigationConstraint newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(ClassNavigationConstraint newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES, ClassNavigationConstraint.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((ClassNavigationConstraint)otherEnd, msgs);
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return basicSetRemoveConstraint(null, msgs);
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return basicSetAddConstraint(null, msgs);
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				return basicSetOwner(null, msgs);
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__PROPERTIES, ClassNavigationConstraint.class, msgs);
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return getRemoveConstraint();
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return getAddConstraint();
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				return getOwner();
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				setRemoveConstraint((NavigationConstraint)newValue);
				return;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				setAddConstraint((NavigationConstraint)newValue);
				return;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				setOwner((ClassNavigationConstraint)newValue);
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				setRemoveConstraint((NavigationConstraint)null);
				return;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				setAddConstraint((NavigationConstraint)null);
				return;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				setOwner((ClassNavigationConstraint)null);
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
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return removeConstraint != null;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return addConstraint != null;
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__OWNER:
				return getOwner() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MultiplicityElementNavigationConstraint.class) {
			switch (derivedFeatureID) {
				case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT: return PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT;
				case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT: return PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MultiplicityElementNavigationConstraint.class) {
			switch (baseFeatureID) {
				case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT: return PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT;
				case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT: return PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //PropertyNavigationConstraintImpl
