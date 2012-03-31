/**
 */
package org.opaeum.uim.binding.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.impl.UmlReferenceImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Uim Binding</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.opaeum.uim.binding.impl.UimBindingImpl#getNext <em>Next</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class UimBindingImpl extends UmlReferenceImpl implements UimBinding{
	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected PropertyRef next;
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected UimBindingImpl(){
		super();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return BindingPackage.Literals.UIM_BINDING;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyRef getNext(){
		return next;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetNext(PropertyRef newNext,NotificationChain msgs){
		PropertyRef oldNext = next;
		next = newNext;
		if(eNotificationRequired()){
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BindingPackage.UIM_BINDING__NEXT, oldNext, newNext);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNext(PropertyRef newNext){
		if(newNext != next){
			NotificationChain msgs = null;
			if(next != null)
				msgs = ((InternalEObject) next).eInverseRemove(this, BindingPackage.PROPERTY_REF__BINDING, PropertyRef.class, msgs);
			if(newNext != null)
				msgs = ((InternalEObject) newNext).eInverseAdd(this, BindingPackage.PROPERTY_REF__BINDING, PropertyRef.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if(msgs != null)
				msgs.dispatch();
		}else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.UIM_BINDING__NEXT, newNext, newNext));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getLastPropertyUuid(){
		PropertyRef next2 = getNext();
		if(next2 == null){
			return getUmlElementUid();
		}else{
			return getLastPropertyUuid(next2);
		}
	}
	public String getLastPropertyUuid(PropertyRef next2){
		if(next.getNext() == null){
			return next2.getUmlElementUid();
		}else{
			return getLastPropertyUuid(next2);
		}
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getExpression(){
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			if(next != null)
				msgs = ((InternalEObject) next).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BindingPackage.UIM_BINDING__NEXT, null, msgs);
			return basicSetNext((PropertyRef) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			return basicSetNext(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			return getNext();
		}
		return super.eGet(featureID, resolve, coreType);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID,Object newValue){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			setNext((PropertyRef) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			setNext((PropertyRef) null);
			return;
		}
		super.eUnset(featureID);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID){
		switch(featureID){
		case BindingPackage.UIM_BINDING__NEXT:
			return next != null;
		}
		return super.eIsSet(featureID);
	}
} // UimBindingImpl
