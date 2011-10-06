/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.folder.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.folder.AbstractFolder;
import org.opaeum.uim.folder.AbstractFormFolder;
import org.opaeum.uim.folder.FolderPackage;
import org.opaeum.uim.folder.UserInteractionModel;
import org.opaeum.uim.impl.UmlReferenceImpl;
import org.opaeum.uim.security.EditableSecureObject;
import org.opaeum.uim.security.SecureObject;
import org.opaeum.uim.security.SecurityConstraint;
import org.opaeum.uim.security.SecurityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInteractionModelImpl extends UmlReferenceImpl implements UserInteractionModel {
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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractFormFolder> children;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected SecurityConstraint visibility;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected SecurityConstraint editability;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInteractionModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FolderPackage.Literals.USER_INTERACTION_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractFormFolder> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<AbstractFormFolder>(AbstractFormFolder.class, this, FolderPackage.USER_INTERACTION_MODEL__CHILDREN, FolderPackage.ABSTRACT_FORM_FOLDER__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(SecurityConstraint newVisibility, NotificationChain msgs) {
		SecurityConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(SecurityConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_MODEL__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_MODEL__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(SecurityConstraint newEditability, NotificationChain msgs) {
		SecurityConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(SecurityConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_MODEL__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FolderPackage.USER_INTERACTION_MODEL__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractFolder getParent() {
		if (eContainerFeatureID() != FolderPackage.USER_INTERACTION_MODEL__PARENT) return null;
		return (AbstractFolder)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(AbstractFolder newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, FolderPackage.USER_INTERACTION_MODEL__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(AbstractFolder newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != FolderPackage.USER_INTERACTION_MODEL__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, FolderPackage.ABSTRACT_FOLDER__CHILDREN, AbstractFolder.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FolderPackage.USER_INTERACTION_MODEL__PARENT, newParent, newParent));
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
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((AbstractFolder)otherEnd, msgs);
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
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY:
				return basicSetEditability(null, msgs);
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				return basicSetParent(null, msgs);
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
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				return eInternalContainer().eInverseRemove(this, FolderPackage.ABSTRACT_FOLDER__CHILDREN, AbstractFolder.class, msgs);
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
			case FolderPackage.USER_INTERACTION_MODEL__NAME:
				return getName();
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				return getChildren();
			case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY:
				return getVisibility();
			case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY:
				return getEditability();
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				return getParent();
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
			case FolderPackage.USER_INTERACTION_MODEL__NAME:
				setName((String)newValue);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends AbstractFormFolder>)newValue);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY:
				setVisibility((SecurityConstraint)newValue);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY:
				setEditability((SecurityConstraint)newValue);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				setParent((AbstractFolder)newValue);
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
			case FolderPackage.USER_INTERACTION_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				getChildren().clear();
				return;
			case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY:
				setVisibility((SecurityConstraint)null);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY:
				setEditability((SecurityConstraint)null);
				return;
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				setParent((AbstractFolder)null);
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
			case FolderPackage.USER_INTERACTION_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FolderPackage.USER_INTERACTION_MODEL__CHILDREN:
				return children != null && !children.isEmpty();
			case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY:
				return visibility != null;
			case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY:
				return editability != null;
			case FolderPackage.USER_INTERACTION_MODEL__PARENT:
				return getParent() != null;
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
				case FolderPackage.USER_INTERACTION_MODEL__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == AbstractFolder.class) {
			switch (derivedFeatureID) {
				case FolderPackage.USER_INTERACTION_MODEL__CHILDREN: return FolderPackage.ABSTRACT_FOLDER__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (derivedFeatureID) {
				case FolderPackage.USER_INTERACTION_MODEL__VISIBILITY: return SecurityPackage.SECURE_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (derivedFeatureID) {
				case FolderPackage.USER_INTERACTION_MODEL__EDITABILITY: return SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == AbstractFormFolder.class) {
			switch (derivedFeatureID) {
				case FolderPackage.USER_INTERACTION_MODEL__PARENT: return FolderPackage.ABSTRACT_FORM_FOLDER__PARENT;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return FolderPackage.USER_INTERACTION_MODEL__NAME;
				default: return -1;
			}
		}
		if (baseClass == AbstractFolder.class) {
			switch (baseFeatureID) {
				case FolderPackage.ABSTRACT_FOLDER__CHILDREN: return FolderPackage.USER_INTERACTION_MODEL__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.SECURE_OBJECT__VISIBILITY: return FolderPackage.USER_INTERACTION_MODEL__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY: return FolderPackage.USER_INTERACTION_MODEL__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == AbstractFormFolder.class) {
			switch (baseFeatureID) {
				case FolderPackage.ABSTRACT_FORM_FOLDER__PARENT: return FolderPackage.USER_INTERACTION_MODEL__PARENT;
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

} //UserInteractionModelImpl
