/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.PackageFolderImpl#getUmlPackage <em>Uml Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageFolderImpl extends AbstractFormFolderImpl implements PackageFolder {
	/**
	 * The cached value of the '{@link #getUmlPackage() <em>Uml Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlPackage()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Package umlPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.PACKAGE_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package getUmlPackage() {
		if (umlPackage != null && umlPackage.eIsProxy()) {
			InternalEObject oldUmlPackage = (InternalEObject)umlPackage;
			umlPackage = (org.eclipse.uml2.uml.Package)eResolveProxy(oldUmlPackage);
			if (umlPackage != oldUmlPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIMPackage.PACKAGE_FOLDER__UML_PACKAGE, oldUmlPackage, umlPackage));
			}
		}
		return umlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package basicGetUmlPackage() {
		return umlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlPackage(org.eclipse.uml2.uml.Package newUmlPackage) {
		org.eclipse.uml2.uml.Package oldUmlPackage = umlPackage;
		umlPackage = newUmlPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.PACKAGE_FOLDER__UML_PACKAGE, oldUmlPackage, umlPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIMPackage.PACKAGE_FOLDER__UML_PACKAGE:
				if (resolve) return getUmlPackage();
				return basicGetUmlPackage();
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
			case UIMPackage.PACKAGE_FOLDER__UML_PACKAGE:
				setUmlPackage((org.eclipse.uml2.uml.Package)newValue);
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
			case UIMPackage.PACKAGE_FOLDER__UML_PACKAGE:
				setUmlPackage((org.eclipse.uml2.uml.Package)null);
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
			case UIMPackage.PACKAGE_FOLDER__UML_PACKAGE:
				return umlPackage != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageFolderImpl
