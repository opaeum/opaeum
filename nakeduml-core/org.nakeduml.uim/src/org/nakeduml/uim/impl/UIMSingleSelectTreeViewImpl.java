/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import java.util.Collection;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMLookup;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UIMSingleSelectTreeView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Select Tree View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl#getField <em>Field</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl#getLookupSource <em>Lookup Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIMSingleSelectTreeViewImpl extends EObjectImpl implements UIMSingleSelectTreeView {
	/**
	 * The cached value of the '{@link #getDetailPanels() <em>Detail Panels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailPanels()
	 * @generated
	 * @ordered
	 */
	protected EList<DetailPanel> detailPanels;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected String width = WIDTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLookupSource() <em>Lookup Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupSource()
	 * @generated
	 * @ordered
	 */
	protected LookupBinding lookupSource;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIMSingleSelectTreeViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_SINGLE_SELECT_TREE_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailPanel> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS, UIMPackage.DETAIL_PANEL__MASTER_COMPONENT);
		}
		return detailPanels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(String newWidth) {
		String oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMField getField() {
		if (eContainerFeatureID() != UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD) return null;
		return (UIMField)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetField(UIMField newField, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newField, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(UIMField newField) {
		if (newField != eInternalContainer() || (eContainerFeatureID() != UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD && newField != null)) {
			if (EcoreUtil.isAncestor(this, newField))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newField != null)
				msgs = ((InternalEObject)newField).eInverseAdd(this, UIMPackage.UIM_FIELD__CONTROL, UIMField.class, msgs);
			msgs = basicSetField(newField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD, newField, newField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LookupBinding getLookupSource() {
		return lookupSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLookupSource(LookupBinding newLookupSource, NotificationChain msgs) {
		LookupBinding oldLookupSource = lookupSource;
		lookupSource = newLookupSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, oldLookupSource, newLookupSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookupSource(LookupBinding newLookupSource) {
		if (newLookupSource != lookupSource) {
			NotificationChain msgs = null;
			if (lookupSource != null)
				msgs = ((InternalEObject)lookupSource).eInverseRemove(this, UIMPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			if (newLookupSource != null)
				msgs = ((InternalEObject)newLookupSource).eInverseAdd(this, UIMPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			msgs = basicSetLookupSource(newLookupSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, newLookupSource, newLookupSource));
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailPanels()).basicAdd(otherEnd, msgs);
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetField((UIMField)otherEnd, msgs);
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				if (lookupSource != null)
					msgs = ((InternalEObject)lookupSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, null, msgs);
				return basicSetLookupSource((LookupBinding)otherEnd, msgs);
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return ((InternalEList<?>)getDetailPanels()).basicRemove(otherEnd, msgs);
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return basicSetField(null, msgs);
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				return basicSetLookupSource(null, msgs);
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return eInternalContainer().eInverseRemove(this, UIMPackage.UIM_FIELD__CONTROL, UIMField.class, msgs);
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return getDetailPanels();
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				return getWidth();
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return getField();
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				return getLookupSource();
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailPanel>)newValue);
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				setWidth((String)newValue);
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				setField((UIMField)newValue);
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				setLookupSource((LookupBinding)newValue);
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				getDetailPanels().clear();
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				setField((UIMField)null);
				return;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				setLookupSource((LookupBinding)null);
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
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return getField() != null;
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				return lookupSource != null;
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
		if (baseClass == UIMControl.class) {
			switch (derivedFeatureID) {
				case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH: return UIMPackage.UIM_CONTROL__WIDTH;
				case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD: return UIMPackage.UIM_CONTROL__FIELD;
				default: return -1;
			}
		}
		if (baseClass == UIMLookup.class) {
			switch (derivedFeatureID) {
				case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE: return UIMPackage.UIM_LOOKUP__LOOKUP_SOURCE;
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
		if (baseClass == UIMControl.class) {
			switch (baseFeatureID) {
				case UIMPackage.UIM_CONTROL__WIDTH: return UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH;
				case UIMPackage.UIM_CONTROL__FIELD: return UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD;
				default: return -1;
			}
		}
		if (baseClass == UIMLookup.class) {
			switch (baseFeatureID) {
				case UIMPackage.UIM_LOOKUP__LOOKUP_SOURCE: return UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE;
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
		result.append(" (width: ");
		result.append(width);
		result.append(')');
		return result.toString();
	}

} //UIMSingleSelectTreeViewImpl
