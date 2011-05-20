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
import org.nakeduml.uim.UimControl;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimLookup;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UimSingleSelectTreeView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Select Tree View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl#getField <em>Field</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl#getLookupSource <em>Lookup Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimSingleSelectTreeViewImpl extends EObjectImpl implements UimSingleSelectTreeView {
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
	protected UimSingleSelectTreeViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_SINGLE_SELECT_TREE_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailPanel> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS, UimPackage.DETAIL_PANEL__MASTER_COMPONENT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimField getField() {
		if (eContainerFeatureID() != UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD) return null;
		return (UimField)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetField(UimField newField, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newField, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(UimField newField) {
		if (newField != eInternalContainer() || (eContainerFeatureID() != UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD && newField != null)) {
			if (EcoreUtil.isAncestor(this, newField))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newField != null)
				msgs = ((InternalEObject)newField).eInverseAdd(this, UimPackage.UIM_FIELD__CONTROL, UimField.class, msgs);
			msgs = basicSetField(newField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD, newField, newField));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, oldLookupSource, newLookupSource);
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
				msgs = ((InternalEObject)lookupSource).eInverseRemove(this, UimPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			if (newLookupSource != null)
				msgs = ((InternalEObject)newLookupSource).eInverseAdd(this, UimPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			msgs = basicSetLookupSource(newLookupSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, newLookupSource, newLookupSource));
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailPanels()).basicAdd(otherEnd, msgs);
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetField((UimField)otherEnd, msgs);
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
				if (lookupSource != null)
					msgs = ((InternalEObject)lookupSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE, null, msgs);
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return ((InternalEList<?>)getDetailPanels()).basicRemove(otherEnd, msgs);
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return basicSetField(null, msgs);
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return eInternalContainer().eInverseRemove(this, UimPackage.UIM_FIELD__CONTROL, UimField.class, msgs);
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return getDetailPanels();
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				return getWidth();
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return getField();
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailPanel>)newValue);
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				setWidth((String)newValue);
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				setField((UimField)newValue);
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				getDetailPanels().clear();
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				setField((UimField)null);
				return;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
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
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD:
				return getField() != null;
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE:
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
		if (baseClass == UimControl.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH: return UimPackage.UIM_CONTROL__WIDTH;
				case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD: return UimPackage.UIM_CONTROL__FIELD;
				default: return -1;
			}
		}
		if (baseClass == UimLookup.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE: return UimPackage.UIM_LOOKUP__LOOKUP_SOURCE;
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
		if (baseClass == UimControl.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_CONTROL__WIDTH: return UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__WIDTH;
				case UimPackage.UIM_CONTROL__FIELD: return UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__FIELD;
				default: return -1;
			}
		}
		if (baseClass == UimLookup.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_LOOKUP__LOOKUP_SOURCE: return UimPackage.UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE;
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

} //UimSingleSelectTreeViewImpl
