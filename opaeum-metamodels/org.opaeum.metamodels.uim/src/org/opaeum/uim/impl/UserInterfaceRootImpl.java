/**
 */
package org.opaeum.uim.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interface Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getLabelOverride <em>Label Override</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getIgnoredElements <em>Ignored Elements</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getSuperUserInterfaces <em>Super User Interfaces</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getPageOrdering <em>Page Ordering</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceRootImpl#getSubUserInterfaces <em>Sub User Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInterfaceRootImpl extends UserInteractionElementImpl implements UserInterfaceRoot {
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLabelOverride() <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelOverride()
	 * @generated
	 * @ordered
	 */
	protected Labels labelOverride;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint editability;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint visibility;

	/**
	 * The cached value of the '{@link #getIgnoredElements() <em>Ignored Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredElements()
	 * @generated
	 * @ordered
	 */
	protected EList<IgnoredElement> ignoredElements;

	/**
	 * The cached value of the '{@link #getSuperUserInterfaces() <em>Super User Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperUserInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<UserInterfaceRoot> superUserInterfaces;

	/**
	 * The cached value of the '{@link #getPageOrdering() <em>Page Ordering</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageOrdering()
	 * @generated
	 * @ordered
	 */
	protected EList<PageOrdering> pageOrdering;

	/**
	 * The cached value of the '{@link #getSubUserInterfaces() <em>Sub User Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubUserInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<UserInterfaceRoot> subUserInterfaces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInterfaceRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.USER_INTERFACE_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid) {
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabelOverride() {
		return labelOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelOverride(Labels newLabelOverride, NotificationChain msgs) {
		Labels oldLabelOverride = labelOverride;
		labelOverride = newLabelOverride;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE, oldLabelOverride, newLabelOverride);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelOverride(Labels newLabelOverride) {
		if (newLabelOverride != labelOverride) {
			NotificationChain msgs = null;
			if (labelOverride != null)
				msgs = ((InternalEObject)labelOverride).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE, null, msgs);
			if (newLabelOverride != null)
				msgs = ((InternalEObject)newLabelOverride).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE, null, msgs);
			msgs = basicSetLabelOverride(newLabelOverride, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE, newLabelOverride, newLabelOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(RootUserInteractionConstraint newEditability, NotificationChain msgs) {
		RootUserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(RootUserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(RootUserInteractionConstraint newVisibility, NotificationChain msgs) {
		RootUserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(RootUserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ROOT__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ROOT__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IgnoredElement> getIgnoredElements() {
		if (ignoredElements == null) {
			ignoredElements = new EObjectContainmentWithInverseEList<IgnoredElement>(IgnoredElement.class, this, UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS, UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT);
		}
		return ignoredElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserInterfaceRoot> getSuperUserInterfaces() {
		if (superUserInterfaces == null) {
			superUserInterfaces = new EObjectWithInverseResolvingEList.ManyInverse<UserInterfaceRoot>(UserInterfaceRoot.class, this, UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES, UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES);
		}
		return superUserInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PageOrdering> getPageOrdering() {
		if (pageOrdering == null) {
			pageOrdering = new EObjectContainmentEList<PageOrdering>(PageOrdering.class, this, UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING);
		}
		return pageOrdering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserInterfaceRoot> getSubUserInterfaces() {
		if (subUserInterfaces == null) {
			subUserInterfaces = new EObjectWithInverseResolvingEList.ManyInverse<UserInterfaceRoot>(UserInterfaceRoot.class, this, UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES, UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES);
		}
		return subUserInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<? extends Page> getPages() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIgnoredElements()).basicAdd(otherEnd, msgs);
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSuperUserInterfaces()).basicAdd(otherEnd, msgs);
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubUserInterfaces()).basicAdd(otherEnd, msgs);
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
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
				return basicSetLabelOverride(null, msgs);
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
				return basicSetEditability(null, msgs);
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				return ((InternalEList<?>)getIgnoredElements()).basicRemove(otherEnd, msgs);
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				return ((InternalEList<?>)getSuperUserInterfaces()).basicRemove(otherEnd, msgs);
			case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING:
				return ((InternalEList<?>)getPageOrdering()).basicRemove(otherEnd, msgs);
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				return ((InternalEList<?>)getSubUserInterfaces()).basicRemove(otherEnd, msgs);
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
			case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID:
				return getUmlElementUid();
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
				return getLabelOverride();
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
				return getEditability();
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
				return getVisibility();
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				return getIgnoredElements();
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				return getSuperUserInterfaces();
			case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING:
				return getPageOrdering();
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				return getSubUserInterfaces();
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
			case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
				setEditability((RootUserInteractionConstraint)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				getIgnoredElements().clear();
				getIgnoredElements().addAll((Collection<? extends IgnoredElement>)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				getSuperUserInterfaces().clear();
				getSuperUserInterfaces().addAll((Collection<? extends UserInterfaceRoot>)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING:
				getPageOrdering().clear();
				getPageOrdering().addAll((Collection<? extends PageOrdering>)newValue);
				return;
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				getSubUserInterfaces().clear();
				getSubUserInterfaces().addAll((Collection<? extends UserInterfaceRoot>)newValue);
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
			case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
				return;
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
				setEditability((RootUserInteractionConstraint)null);
				return;
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)null);
				return;
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				getIgnoredElements().clear();
				return;
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				getSuperUserInterfaces().clear();
				return;
			case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING:
				getPageOrdering().clear();
				return;
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				getSubUserInterfaces().clear();
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
			case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
				return labelOverride != null;
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
				return editability != null;
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
				return visibility != null;
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
				return ignoredElements != null && !ignoredElements.isEmpty();
			case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES:
				return superUserInterfaces != null && !superUserInterfaces.isEmpty();
			case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING:
				return pageOrdering != null && !pageOrdering.isEmpty();
			case UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES:
				return subUserInterfaces != null && !subUserInterfaces.isEmpty();
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
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (derivedFeatureID) {
				case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE: return UimPackage.LABELED_ELEMENT__LABEL_OVERRIDE;
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
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (baseFeatureID) {
				case UimPackage.LABELED_ELEMENT__LABEL_OVERRIDE: return UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(')');
		return result.toString();
	}

} //UserInterfaceRootImpl
