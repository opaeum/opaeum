/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Property;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.PropertyRefImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.PropertyRefImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.PropertyRefImpl#getNext <em>Next</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.PropertyRefImpl#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyRefImpl extends EObjectImpl implements PropertyRef {
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
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

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
		return UIMPackage.Literals.PROPERTY_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMBinding getBinding() {
		if (eContainerFeatureID() != UIMPackage.PROPERTY_REF__BINDING) return null;
		return (UIMBinding)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(UIMBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, UIMPackage.PROPERTY_REF__BINDING, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(UIMBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != UIMPackage.PROPERTY_REF__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, UIMPackage.UIM_BINDING__NEXT, UIMBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.PROPERTY_REF__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRef getPrevious() {
		if (eContainerFeatureID() != UIMPackage.PROPERTY_REF__PREVIOUS) return null;
		return (PropertyRef)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrevious(PropertyRef newPrevious, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPrevious, UIMPackage.PROPERTY_REF__PREVIOUS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrevious(PropertyRef newPrevious) {
		if (newPrevious != eInternalContainer() || (eContainerFeatureID() != UIMPackage.PROPERTY_REF__PREVIOUS && newPrevious != null)) {
			if (EcoreUtil.isAncestor(this, newPrevious))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPrevious != null)
				msgs = ((InternalEObject)newPrevious).eInverseAdd(this, UIMPackage.PROPERTY_REF__NEXT, PropertyRef.class, msgs);
			msgs = basicSetPrevious(newPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.PROPERTY_REF__PREVIOUS, newPrevious, newPrevious));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.PROPERTY_REF__NEXT, oldNext, newNext);
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
				msgs = ((InternalEObject)next).eInverseRemove(this, UIMPackage.PROPERTY_REF__PREVIOUS, PropertyRef.class, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, UIMPackage.PROPERTY_REF__PREVIOUS, PropertyRef.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.PROPERTY_REF__NEXT, newNext, newNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIMPackage.PROPERTY_REF__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.PROPERTY_REF__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.PROPERTY_REF__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((UIMBinding)otherEnd, msgs);
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPrevious((PropertyRef)otherEnd, msgs);
			case UIMPackage.PROPERTY_REF__NEXT:
				if (next != null)
					msgs = ((InternalEObject)next).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.PROPERTY_REF__NEXT, null, msgs);
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
			case UIMPackage.PROPERTY_REF__BINDING:
				return basicSetBinding(null, msgs);
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				return basicSetPrevious(null, msgs);
			case UIMPackage.PROPERTY_REF__NEXT:
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
			case UIMPackage.PROPERTY_REF__BINDING:
				return eInternalContainer().eInverseRemove(this, UIMPackage.UIM_BINDING__NEXT, UIMBinding.class, msgs);
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				return eInternalContainer().eInverseRemove(this, UIMPackage.PROPERTY_REF__NEXT, PropertyRef.class, msgs);
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
			case UIMPackage.PROPERTY_REF__BINDING:
				return getBinding();
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				return getPrevious();
			case UIMPackage.PROPERTY_REF__NEXT:
				return getNext();
			case UIMPackage.PROPERTY_REF__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
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
			case UIMPackage.PROPERTY_REF__BINDING:
				setBinding((UIMBinding)newValue);
				return;
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				setPrevious((PropertyRef)newValue);
				return;
			case UIMPackage.PROPERTY_REF__NEXT:
				setNext((PropertyRef)newValue);
				return;
			case UIMPackage.PROPERTY_REF__PROPERTY:
				setProperty((Property)newValue);
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
			case UIMPackage.PROPERTY_REF__BINDING:
				setBinding((UIMBinding)null);
				return;
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				setPrevious((PropertyRef)null);
				return;
			case UIMPackage.PROPERTY_REF__NEXT:
				setNext((PropertyRef)null);
				return;
			case UIMPackage.PROPERTY_REF__PROPERTY:
				setProperty((Property)null);
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
			case UIMPackage.PROPERTY_REF__BINDING:
				return getBinding() != null;
			case UIMPackage.PROPERTY_REF__PREVIOUS:
				return getPrevious() != null;
			case UIMPackage.PROPERTY_REF__NEXT:
				return next != null;
			case UIMPackage.PROPERTY_REF__PROPERTY:
				return property != null;
		}
		return super.eIsSet(featureID);
	}

} //PropertyRefImpl
