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
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.ParameterNavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl#getInvocationConstraint <em>Invocation Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationNavigationConstraintImpl extends NavigationConstraintImpl implements OperationNavigationConstraint {
	/**
	 * The cached value of the '{@link #getInvocationConstraint() <em>Invocation Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocationConstraint()
	 * @generated
	 * @ordered
	 */
	protected NavigationConstraint invocationConstraint;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterNavigationConstraint> parameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationNavigationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.OPERATION_NAVIGATION_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassNavigationConstraint getOwner() {
		if (eContainerFeatureID() != PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER) return null;
		return (ClassNavigationConstraint)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(ClassNavigationConstraint newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(ClassNavigationConstraint newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS, ClassNavigationConstraint.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationConstraint getInvocationConstraint() {
		return invocationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInvocationConstraint(NavigationConstraint newInvocationConstraint, NotificationChain msgs) {
		NavigationConstraint oldInvocationConstraint = invocationConstraint;
		invocationConstraint = newInvocationConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT, oldInvocationConstraint, newInvocationConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocationConstraint(NavigationConstraint newInvocationConstraint) {
		if (newInvocationConstraint != invocationConstraint) {
			NotificationChain msgs = null;
			if (invocationConstraint != null)
				msgs = ((InternalEObject)invocationConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT, null, msgs);
			if (newInvocationConstraint != null)
				msgs = ((InternalEObject)newInvocationConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT, null, msgs);
			msgs = basicSetInvocationConstraint(newInvocationConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT, newInvocationConstraint, newInvocationConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterNavigationConstraint> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<ParameterNavigationConstraint>(ParameterNavigationConstraint.class, this, PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				return basicSetOwner(null, msgs);
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT:
				return basicSetInvocationConstraint(null, msgs);
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT__OPERATIONS, ClassNavigationConstraint.class, msgs);
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				return getOwner();
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT:
				return getInvocationConstraint();
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS:
				return getParameters();
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				setOwner((ClassNavigationConstraint)newValue);
				return;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT:
				setInvocationConstraint((NavigationConstraint)newValue);
				return;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends ParameterNavigationConstraint>)newValue);
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				setOwner((ClassNavigationConstraint)null);
				return;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT:
				setInvocationConstraint((NavigationConstraint)null);
				return;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS:
				getParameters().clear();
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
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__OWNER:
				return getOwner() != null;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT:
				return invocationConstraint != null;
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationNavigationConstraintImpl
