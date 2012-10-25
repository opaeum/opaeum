/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.Labels;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page Ordering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.PageOrderingImpl#getPage <em>Page</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.PageOrderingImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.PageOrderingImpl#getLabelOverride <em>Label Override</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageOrderingImpl extends EObjectImpl implements PageOrdering {
	/**
	 * The cached value of the '{@link #getPage() <em>Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPage()
	 * @generated
	 * @ordered
	 */
	protected Page page;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int POSITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected int position = POSITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLabelOverride() <em>Label Override</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelOverride()
	 * @generated
	 * @ordered
	 */
	protected Labels labelOverride;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageOrderingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.PAGE_ORDERING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page getPage() {
		if (page != null && page.eIsProxy()) {
			InternalEObject oldPage = (InternalEObject)page;
			page = (Page)eResolveProxy(oldPage);
			if (page != oldPage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.PAGE_ORDERING__PAGE, oldPage, page));
			}
		}
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page basicGetPage() {
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPage(Page newPage) {
		Page oldPage = page;
		page = newPage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.PAGE_ORDERING__PAGE, oldPage, page));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(int newPosition) {
		int oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.PAGE_ORDERING__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabelOverride() {
		if (labelOverride != null && labelOverride.eIsProxy()) {
			InternalEObject oldLabelOverride = (InternalEObject)labelOverride;
			labelOverride = (Labels)eResolveProxy(oldLabelOverride);
			if (labelOverride != oldLabelOverride) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.PAGE_ORDERING__LABEL_OVERRIDE, oldLabelOverride, labelOverride));
			}
		}
		return labelOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels basicGetLabelOverride() {
		return labelOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelOverride(Labels newLabelOverride) {
		Labels oldLabelOverride = labelOverride;
		labelOverride = newLabelOverride;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.PAGE_ORDERING__LABEL_OVERRIDE, oldLabelOverride, labelOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UimPackage.PAGE_ORDERING__PAGE:
				if (resolve) return getPage();
				return basicGetPage();
			case UimPackage.PAGE_ORDERING__POSITION:
				return getPosition();
			case UimPackage.PAGE_ORDERING__LABEL_OVERRIDE:
				if (resolve) return getLabelOverride();
				return basicGetLabelOverride();
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
			case UimPackage.PAGE_ORDERING__PAGE:
				setPage((Page)newValue);
				return;
			case UimPackage.PAGE_ORDERING__POSITION:
				setPosition((Integer)newValue);
				return;
			case UimPackage.PAGE_ORDERING__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
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
			case UimPackage.PAGE_ORDERING__PAGE:
				setPage((Page)null);
				return;
			case UimPackage.PAGE_ORDERING__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case UimPackage.PAGE_ORDERING__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
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
			case UimPackage.PAGE_ORDERING__PAGE:
				return page != null;
			case UimPackage.PAGE_ORDERING__POSITION:
				return position != POSITION_EDEFAULT;
			case UimPackage.PAGE_ORDERING__LABEL_OVERRIDE:
				return labelOverride != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

} //PageOrderingImpl
