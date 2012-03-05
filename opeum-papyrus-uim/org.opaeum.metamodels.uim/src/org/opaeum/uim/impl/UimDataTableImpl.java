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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimDataTableImpl extends MasterComponentImpl implements UimDataTable {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint editability;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UimComponent> children;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected TableBinding binding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, oldVisibility, newVisibility);
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
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(UserInteractionConstraint newEditability, NotificationChain msgs) {
		UserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(UserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UimComponent> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<UimComponent>(UimComponent.class, this, UimPackage.UIM_DATA_TABLE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBinding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(TableBinding newBinding, NotificationChain msgs) {
		TableBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(TableBinding newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer getParent() {
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
			case UimPackage.UIM_DATA_TABLE__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__BINDING, null, msgs);
				return basicSetBinding((TableBinding)otherEnd, msgs);
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
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return basicSetEditability(null, msgs);
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return basicSetBinding(null, msgs);
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				return getName();
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return getVisibility();
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return getEditability();
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return getChildren();
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return getBinding();
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName((String)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((UserInteractionConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((UserInteractionConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UimComponent>)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)newValue);
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((UserInteractionConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((UserInteractionConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)null);
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return visibility != null;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return editability != null;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return children != null && !children.isEmpty();
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return binding != null;
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
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ConstrainedObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__VISIBILITY: return ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableConstrainedObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__EDITABILITY: return ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__CHILDREN: return UimPackage.UIM_CONTAINER__CHILDREN;
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
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.UIM_DATA_TABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == ConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY: return UimPackage.UIM_DATA_TABLE__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY: return UimPackage.UIM_DATA_TABLE__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_CONTAINER__CHILDREN: return UimPackage.UIM_DATA_TABLE__CHILDREN;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //UimDataTableImpl
