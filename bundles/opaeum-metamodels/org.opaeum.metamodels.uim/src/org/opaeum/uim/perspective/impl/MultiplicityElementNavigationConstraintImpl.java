/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multiplicity Element Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl#getRemoveConstraint <em>Remove Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl#getAddConstraint <em>Add Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MultiplicityElementNavigationConstraintImpl extends NavigationConstraintImpl implements MultiplicityElementNavigationConstraint {
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
	protected MultiplicityElementNavigationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, oldRemoveConstraint, newRemoveConstraint);
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
				msgs = ((InternalEObject)removeConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, null, msgs);
			if (newRemoveConstraint != null)
				msgs = ((InternalEObject)newRemoveConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, null, msgs);
			msgs = basicSetRemoveConstraint(newRemoveConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT, newRemoveConstraint, newRemoveConstraint));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, oldAddConstraint, newAddConstraint);
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
				msgs = ((InternalEObject)addConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, null, msgs);
			if (newAddConstraint != null)
				msgs = ((InternalEObject)newAddConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, null, msgs);
			msgs = basicSetAddConstraint(newAddConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT, newAddConstraint, newAddConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return basicSetRemoveConstraint(null, msgs);
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return basicSetAddConstraint(null, msgs);
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
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return getRemoveConstraint();
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return getAddConstraint();
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
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				setRemoveConstraint((NavigationConstraint)newValue);
				return;
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				setAddConstraint((NavigationConstraint)newValue);
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
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				setRemoveConstraint((NavigationConstraint)null);
				return;
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				setAddConstraint((NavigationConstraint)null);
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
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT:
				return removeConstraint != null;
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT:
				return addConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //MultiplicityElementNavigationConstraintImpl
