/**
 */
package org.opaeum.uim.panel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outlayable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.impl.OutlayableImpl#getPreferredWidth <em>Preferred Width</em>}</li>
 *   <li>{@link org.opaeum.uim.panel.impl.OutlayableImpl#getPreferredHeight <em>Preferred Height</em>}</li>
 *   <li>{@link org.opaeum.uim.panel.impl.OutlayableImpl#getFillHorizontally <em>Fill Horizontally</em>}</li>
 *   <li>{@link org.opaeum.uim.panel.impl.OutlayableImpl#getFillVertically <em>Fill Vertically</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OutlayableImpl extends EObjectImpl implements Outlayable {
	/**
	 * The default value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredWidth = PREFERRED_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredHeight = PREFERRED_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_HORIZONTALLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillHorizontally = FILL_HORIZONTALLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_VERTICALLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillVertically = FILL_VERTICALLY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutlayableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PanelPackage.Literals.OUTLAYABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredWidth() {
		return preferredWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredWidth(Integer newPreferredWidth) {
		Integer oldPreferredWidth = preferredWidth;
		preferredWidth = newPreferredWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.OUTLAYABLE__PREFERRED_WIDTH, oldPreferredWidth, preferredWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredHeight() {
		return preferredHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredHeight(Integer newPreferredHeight) {
		Integer oldPreferredHeight = preferredHeight;
		preferredHeight = newPreferredHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT, oldPreferredHeight, preferredHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillHorizontally() {
		return fillHorizontally;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillHorizontally(Boolean newFillHorizontally) {
		Boolean oldFillHorizontally = fillHorizontally;
		fillHorizontally = newFillHorizontally;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY, oldFillHorizontally, fillHorizontally));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillVertically() {
		return fillVertically;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillVertically(Boolean newFillVertically) {
		Boolean oldFillVertically = fillVertically;
		fillVertically = newFillVertically;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.OUTLAYABLE__FILL_VERTICALLY, oldFillVertically, fillVertically));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
				return getPreferredWidth();
			case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
				return getPreferredHeight();
			case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY:
				return getFillHorizontally();
			case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
				return getFillVertically();
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
			case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
				setPreferredWidth((Integer)newValue);
				return;
			case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
				setPreferredHeight((Integer)newValue);
				return;
			case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY:
				setFillHorizontally((Boolean)newValue);
				return;
			case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
				setFillVertically((Boolean)newValue);
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
			case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
				setPreferredWidth(PREFERRED_WIDTH_EDEFAULT);
				return;
			case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
				setPreferredHeight(PREFERRED_HEIGHT_EDEFAULT);
				return;
			case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY:
				setFillHorizontally(FILL_HORIZONTALLY_EDEFAULT);
				return;
			case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
				setFillVertically(FILL_VERTICALLY_EDEFAULT);
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
			case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
				return PREFERRED_WIDTH_EDEFAULT == null ? preferredWidth != null : !PREFERRED_WIDTH_EDEFAULT.equals(preferredWidth);
			case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
				return PREFERRED_HEIGHT_EDEFAULT == null ? preferredHeight != null : !PREFERRED_HEIGHT_EDEFAULT.equals(preferredHeight);
			case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY:
				return FILL_HORIZONTALLY_EDEFAULT == null ? fillHorizontally != null : !FILL_HORIZONTALLY_EDEFAULT.equals(fillHorizontally);
			case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
				return FILL_VERTICALLY_EDEFAULT == null ? fillVertically != null : !FILL_VERTICALLY_EDEFAULT.equals(fillVertically);
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
		result.append(" (preferredWidth: ");
		result.append(preferredWidth);
		result.append(", preferredHeight: ");
		result.append(preferredHeight);
		result.append(", fillHorizontally: ");
		result.append(fillHorizontally);
		result.append(", fillVertically: ");
		result.append(fillVertically);
		result.append(')');
		return result.toString();
	}

} //OutlayableImpl
