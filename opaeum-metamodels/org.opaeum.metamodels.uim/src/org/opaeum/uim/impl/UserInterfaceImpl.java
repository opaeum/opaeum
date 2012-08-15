/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>User Interface</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInterfaceImpl extends UserInteractionElementImpl implements UserInterface{
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;
	/**
	 * The cached value of the '{@link #getPanel() <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPanel()
	 * @generated
	 * @ordered
	 */
	protected AbstractPanel panel;
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInterfaceImpl(){
		super();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return UimPackage.Literals.USER_INTERFACE;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid(){
		return umlElementUid;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid){
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractPanel getPanel(){
		return panel;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPanel(AbstractPanel newPanel,NotificationChain msgs){
		AbstractPanel oldPanel = panel;
		panel = newPanel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE__PANEL, oldPanel, newPanel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanel(AbstractPanel newPanel){
		if (newPanel != panel) {
			NotificationChain msgs = null;
			if (panel != null)
				msgs = ((InternalEObject)panel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE__PANEL, null, msgs);
			if (newPanel != null)
				msgs = ((InternalEObject)newPanel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE__PANEL, null, msgs);
			msgs = basicSetPanel(newPanel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE__PANEL, newPanel, newPanel));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case UimPackage.USER_INTERFACE__PANEL:
				return basicSetPanel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case UimPackage.USER_INTERFACE__UML_ELEMENT_UID:
				return getUmlElementUid();
			case UimPackage.USER_INTERFACE__PANEL:
				return getPanel();
		}
		return super.eGet(featureID, resolve, coreType);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID,Object newValue){
		switch (featureID) {
			case UimPackage.USER_INTERFACE__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case UimPackage.USER_INTERFACE__PANEL:
				setPanel((AbstractPanel)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID){
		switch (featureID) {
			case UimPackage.USER_INTERFACE__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case UimPackage.USER_INTERFACE__PANEL:
				setPanel((AbstractPanel)null);
				return;
		}
		super.eUnset(featureID);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID){
		switch (featureID) {
			case UimPackage.USER_INTERFACE__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case UimPackage.USER_INTERFACE__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,Class<?> baseClass){
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case UimPackage.USER_INTERFACE__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID,Class<?> baseClass){
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return UimPackage.USER_INTERFACE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(')');
		return result.toString();
	}
} // UserInterfaceImpl
