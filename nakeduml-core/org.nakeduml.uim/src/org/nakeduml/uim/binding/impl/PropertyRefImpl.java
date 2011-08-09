/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.binding.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.UimBinding;
import org.nakeduml.uim.impl.UmlReferenceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.binding.impl.PropertyRefImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nakeduml.uim.binding.impl.PropertyRefImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link org.nakeduml.uim.binding.impl.PropertyRefImpl#getNext <em>Next</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyRefImpl extends UmlReferenceImpl implements PropertyRef {
	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected PropertyRef next;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BindingPackage.Literals.PROPERTY_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimBinding getBinding() {
		if (eContainerFeatureID() != BindingPackage.PROPERTY_REF__BINDING) return null;
		return (UimBinding)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(UimBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, BindingPackage.PROPERTY_REF__BINDING, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(UimBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != BindingPackage.PROPERTY_REF__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.UIM_BINDING__NEXT, UimBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.PROPERTY_REF__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRef getPrevious() {
		if (eContainerFeatureID() != BindingPackage.PROPERTY_REF__PREVIOUS) return null;
		return (PropertyRef)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrevious(PropertyRef newPrevious, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPrevious, BindingPackage.PROPERTY_REF__PREVIOUS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrevious(PropertyRef newPrevious) {
		if (newPrevious != eInternalContainer() || (eContainerFeatureID() != BindingPackage.PROPERTY_REF__PREVIOUS && newPrevious != null)) {
			if (EcoreUtil.isAncestor(this, newPrevious))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPrevious != null)
				msgs = ((InternalEObject)newPrevious).eInverseAdd(this, BindingPackage.PROPERTY_REF__NEXT, PropertyRef.class, msgs);
			msgs = basicSetPrevious(newPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.PROPERTY_REF__PREVIOUS, newPrevious, newPrevious));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRef getNext() {
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNext(PropertyRef newNext, NotificationChain msgs) {
		PropertyRef oldNext = next;
		next = newNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BindingPackage.PROPERTY_REF__NEXT, oldNext, newNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNext(PropertyRef newNext) {
		if (newNext != next) {
			NotificationChain msgs = null;
			if (next != null)
				msgs = ((InternalEObject)next).eInverseRemove(this, BindingPackage.PROPERTY_REF__PREVIOUS, PropertyRef.class, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, BindingPackage.PROPERTY_REF__PREVIOUS, PropertyRef.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.PROPERTY_REF__NEXT, newNext, newNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BindingPackage.PROPERTY_REF__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((UimBinding)otherEnd, msgs);
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPrevious((PropertyRef)otherEnd, msgs);
			case BindingPackage.PROPERTY_REF__NEXT:
				if (next != null)
					msgs = ((InternalEObject)next).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BindingPackage.PROPERTY_REF__NEXT, null, msgs);
				return basicSetNext((PropertyRef)otherEnd, msgs);
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
			case BindingPackage.PROPERTY_REF__BINDING:
				return basicSetBinding(null, msgs);
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				return basicSetPrevious(null, msgs);
			case BindingPackage.PROPERTY_REF__NEXT:
				return basicSetNext(null, msgs);
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
			case BindingPackage.PROPERTY_REF__BINDING:
				return eInternalContainer().eInverseRemove(this, BindingPackage.UIM_BINDING__NEXT, UimBinding.class, msgs);
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				return eInternalContainer().eInverseRemove(this, BindingPackage.PROPERTY_REF__NEXT, PropertyRef.class, msgs);
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
			case BindingPackage.PROPERTY_REF__BINDING:
				return getBinding();
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				return getPrevious();
			case BindingPackage.PROPERTY_REF__NEXT:
				return getNext();
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
			case BindingPackage.PROPERTY_REF__BINDING:
				setBinding((UimBinding)newValue);
				return;
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				setPrevious((PropertyRef)newValue);
				return;
			case BindingPackage.PROPERTY_REF__NEXT:
				setNext((PropertyRef)newValue);
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
			case BindingPackage.PROPERTY_REF__BINDING:
				setBinding((UimBinding)null);
				return;
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				setPrevious((PropertyRef)null);
				return;
			case BindingPackage.PROPERTY_REF__NEXT:
				setNext((PropertyRef)null);
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
			case BindingPackage.PROPERTY_REF__BINDING:
				return getBinding() != null;
			case BindingPackage.PROPERTY_REF__PREVIOUS:
				return getPrevious() != null;
			case BindingPackage.PROPERTY_REF__NEXT:
				return next != null;
		}
		return super.eIsSet(featureID);
	}

} //PropertyRefImpl
