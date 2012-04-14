/**
 */
package org.opaeum.uim.cube.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

import org.opaeum.uim.cube.ColumnAxisEntry;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;

import org.opaeum.uim.cube.RowAxisEntry;
import org.opaeum.uim.impl.UserInteractionElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getColumnAxis <em>Column Axis</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getRowAxis <em>Row Axis</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CubeQueryImpl extends UserInteractionElementImpl implements CubeQuery {
	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint visibility;

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
	 * The cached value of the '{@link #getColumnAxis() <em>Column Axis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnAxis()
	 * @generated
	 * @ordered
	 */
	protected EList<ColumnAxisEntry> columnAxis;

	/**
	 * The cached value of the '{@link #getRowAxis() <em>Row Axis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRowAxis()
	 * @generated
	 * @ordered
	 */
	protected EList<RowAxisEntry> rowAxis;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CubeQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.CUBE_QUERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(UserInteractionConstraint newVisibility, NotificationChain msgs) {
		UserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(UserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY__VISIBILITY, newVisibility, newVisibility));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ColumnAxisEntry> getColumnAxis() {
		if (columnAxis == null) {
			columnAxis = new EObjectContainmentEList<ColumnAxisEntry>(ColumnAxisEntry.class, this, CubePackage.CUBE_QUERY__COLUMN_AXIS);
		}
		return columnAxis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RowAxisEntry> getRowAxis() {
		if (rowAxis == null) {
			rowAxis = new EObjectContainmentEList<RowAxisEntry>(RowAxisEntry.class, this, CubePackage.CUBE_QUERY__ROW_AXIS);
		}
		return rowAxis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return ((InternalEList<?>)getColumnAxis()).basicRemove(otherEnd, msgs);
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return ((InternalEList<?>)getRowAxis()).basicRemove(otherEnd, msgs);
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
			case CubePackage.CUBE_QUERY__VISIBILITY:
				return getVisibility();
			case CubePackage.CUBE_QUERY__UML_ELEMENT_UID:
				return getUmlElementUid();
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return getColumnAxis();
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return getRowAxis();
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
			case CubePackage.CUBE_QUERY__VISIBILITY:
				setVisibility((UserInteractionConstraint)newValue);
				return;
			case CubePackage.CUBE_QUERY__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				getColumnAxis().clear();
				getColumnAxis().addAll((Collection<? extends ColumnAxisEntry>)newValue);
				return;
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				getRowAxis().clear();
				getRowAxis().addAll((Collection<? extends RowAxisEntry>)newValue);
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
			case CubePackage.CUBE_QUERY__VISIBILITY:
				setVisibility((UserInteractionConstraint)null);
				return;
			case CubePackage.CUBE_QUERY__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				getColumnAxis().clear();
				return;
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				getRowAxis().clear();
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
			case CubePackage.CUBE_QUERY__VISIBILITY:
				return visibility != null;
			case CubePackage.CUBE_QUERY__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return columnAxis != null && !columnAxis.isEmpty();
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return rowAxis != null && !rowAxis.isEmpty();
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
		if (baseClass == ConstrainedObject.class) {
			switch (derivedFeatureID) {
				case CubePackage.CUBE_QUERY__VISIBILITY: return ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case CubePackage.CUBE_QUERY__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
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
		if (baseClass == ConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY: return CubePackage.CUBE_QUERY__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return CubePackage.CUBE_QUERY__UML_ELEMENT_UID;
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

} //CubeQueryImpl
