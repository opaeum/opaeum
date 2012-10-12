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
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerOperationConstraint;
import org.opaeum.uim.perspective.ExplorerPropertyConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explorer Class Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl#getNewObjectConstraint <em>New Object Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl#getBehaviors <em>Behaviors</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplorerClassConstraintImpl extends ExplorerConstraintImpl implements ExplorerClassConstraint {
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerPropertyConstraint> properties;

	/**
	 * The cached value of the '{@link #getNewObjectConstraint() <em>New Object Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewObjectConstraint()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint newObjectConstraint;

	/**
	 * The cached value of the '{@link #getBehaviors() <em>Behaviors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviors()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerBehaviorConstraint> behaviors;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerOperationConstraint> operations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerClassConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.EXPLORER_CLASS_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration getExplorerConfiguration() {
		if (eContainerFeatureID() != PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION) return null;
		return (ExplorerConfiguration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExplorerConfiguration, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorerConfiguration(ExplorerConfiguration newExplorerConfiguration) {
		if (newExplorerConfiguration != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION && newExplorerConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newExplorerConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExplorerConfiguration != null)
				msgs = ((InternalEObject)newExplorerConfiguration).eInverseAdd(this, PerspectivePackage.EXPLORER_CONFIGURATION__CLASSES, ExplorerConfiguration.class, msgs);
			msgs = basicSetExplorerConfiguration(newExplorerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION, newExplorerConfiguration, newExplorerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExplorerPropertyConstraint> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentWithInverseEList<ExplorerPropertyConstraint>(ExplorerPropertyConstraint.class, this, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES, PerspectivePackage.EXPLORER_PROPERTY_CONSTRAINT__OWNER);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getNewObjectConstraint() {
		return newObjectConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewObjectConstraint(UserInteractionConstraint newNewObjectConstraint, NotificationChain msgs) {
		UserInteractionConstraint oldNewObjectConstraint = newObjectConstraint;
		newObjectConstraint = newNewObjectConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT, oldNewObjectConstraint, newNewObjectConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewObjectConstraint(UserInteractionConstraint newNewObjectConstraint) {
		if (newNewObjectConstraint != newObjectConstraint) {
			NotificationChain msgs = null;
			if (newObjectConstraint != null)
				msgs = ((InternalEObject)newObjectConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT, null, msgs);
			if (newNewObjectConstraint != null)
				msgs = ((InternalEObject)newNewObjectConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT, null, msgs);
			msgs = basicSetNewObjectConstraint(newNewObjectConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT, newNewObjectConstraint, newNewObjectConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExplorerBehaviorConstraint> getBehaviors() {
		if (behaviors == null) {
			behaviors = new EObjectContainmentWithInverseEList<ExplorerBehaviorConstraint>(ExplorerBehaviorConstraint.class, this, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS, PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT__OWNER);
		}
		return behaviors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExplorerOperationConstraint> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentWithInverseEList<ExplorerOperationConstraint>(ExplorerOperationConstraint.class, this, PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS, PerspectivePackage.EXPLORER_OPERATION_CONSTRAINT__OWNER);
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExplorerConfiguration((ExplorerConfiguration)otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProperties()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBehaviors()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				return basicSetExplorerConfiguration(null, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT:
				return basicSetNewObjectConstraint(null, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				return ((InternalEList<?>)getBehaviors()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.EXPLORER_CONFIGURATION__CLASSES, ExplorerConfiguration.class, msgs);
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				return getProperties();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT:
				return getNewObjectConstraint();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				return getBehaviors();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ExplorerPropertyConstraint>)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT:
				setNewObjectConstraint((UserInteractionConstraint)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				getBehaviors().clear();
				getBehaviors().addAll((Collection<? extends ExplorerBehaviorConstraint>)newValue);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends ExplorerOperationConstraint>)newValue);
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				setExplorerConfiguration((ExplorerConfiguration)null);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				getProperties().clear();
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT:
				setNewObjectConstraint((UserInteractionConstraint)null);
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				getBehaviors().clear();
				return;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
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
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION:
				return getExplorerConfiguration() != null;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT:
				return newObjectConstraint != null;
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__BEHAVIORS:
				return behaviors != null && !behaviors.isEmpty();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT__OPERATIONS:
				return operations != null && !operations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExplorerClassConstraintImpl
