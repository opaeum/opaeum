/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.*;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UimPanel;
import org.opaeum.uim.UimTab;
import org.opaeum.uim.UimTabPanel;
import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimFactoryImpl extends EFactoryImpl implements UimFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UimFactory init() {
		try {
			UimFactory theUimFactory = (UimFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/1.0"); 
			if (theUimFactory != null) {
				return theUimFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UimFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UimPackage.UIM_FIELD: return createUimField();
			case UimPackage.UIM_DATA_TABLE: return createUimDataTable();
			case UimPackage.UIM_TAB_PANEL: return createUimTabPanel();
			case UimPackage.UIM_TAB: return createUimTab();
			case UimPackage.UIM_CONTAINER: return createUimContainer();
			case UimPackage.UML_REFERENCE: return createUmlReference();
			case UimPackage.UIM_PANEL: return createUimPanel();
			case UimPackage.OBJECT_SELECTOR_TREE: return createObjectSelectorTree();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimField createUimField() {
		UimFieldImpl uimField = new UimFieldImpl();
		return uimField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDataTable createUimDataTable() {
		UimDataTableImpl uimDataTable = new UimDataTableImpl();
		return uimDataTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTabPanel createUimTabPanel() {
		UimTabPanelImpl uimTabPanel = new UimTabPanelImpl();
		return uimTabPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTab createUimTab() {
		UimTabImpl uimTab = new UimTabImpl();
		return uimTab;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer createUimContainer() {
		UimContainerImpl uimContainer = new UimContainerImpl();
		return uimContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlReference createUmlReference() {
		UmlReferenceImpl umlReference = new UmlReferenceImpl();
		return umlReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPanel createUimPanel() {
		UimPanelImpl uimPanel = new UimPanelImpl();
		return uimPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectSelectorTree createObjectSelectorTree() {
		ObjectSelectorTreeImpl objectSelectorTree = new ObjectSelectorTreeImpl();
		return objectSelectorTree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPackage getUimPackage() {
		return (UimPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UimPackage getPackage() {
		return UimPackage.eINSTANCE;
	}

} //UimFactoryImpl
