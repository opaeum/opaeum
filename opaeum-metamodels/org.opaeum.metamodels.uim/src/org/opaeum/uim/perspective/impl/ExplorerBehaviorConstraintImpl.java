/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explorer Behavior Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl#getInvocationConstraint <em>Invocation Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplorerBehaviorConstraintImpl extends ExplorerConstraintImpl implements ExplorerBehaviorConstraint {
	/**
	 * The cached value of the '{@link #getInvocationConstraint() <em>Invocation Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocationConstraint()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint invocationConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerBehaviorConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.EXPLORER_BEHAVIOR_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getInvocationConstraint() {
		return invocationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInvocationConstraint(UserInteractionConstraint newInvocationConstraint, NotificationChain msgs) {
		UserInteractionConstraint oldInvocationConstraint = invocationConstraint;
		invocationConstraint = newInvocationConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT, oldInvocationConstraint, newInvocationConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocationConstraint(UserInteractionConstraint newInvocationConstraint) {
		if (newInvocationConstraint != invocationConstraint) {
			NotificationChain msgs = null;
			if (invocationConstraint != null)
				msgs = ((InternalEObject)invocationConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT, null, msgs);
			if (newInvocationConstraint != null)
				msgs = ((InternalEObject)newInvocationConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT, null, msgs);
			msgs = basicSetInvocationConstraint(newInvocationConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT, newInvocationConstraint, newInvocationConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerClassConstraint getOwner() {
		if (eContainerFeatureID() != PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER) return null;
		return (ExplorerClassConstraint)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(ExplorerClassConstraint newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(ExplorerClassConstraint newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS, ExplorerClassConstraint.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((ExplorerClassConstraint)otherEnd, msgs);
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT:
				return basicSetInvocationConstraint(null, msgs);
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS, ExplorerClassConstraint.class, msgs);
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT:
				return getInvocationConstraint();
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT:
				setInvocationConstraint((UserInteractionConstraint)newValue);
				return;
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
				setOwner((ExplorerClassConstraint)newValue);
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT:
				setInvocationConstraint((UserInteractionConstraint)null);
				return;
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
				setOwner((ExplorerClassConstraint)null);
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
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT:
				return invocationConstraint != null;
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER:
				return getOwner() != null;
		}
		return super.eIsSet(featureID);
	}

} //ExplorerBehaviorConstraintImpl
