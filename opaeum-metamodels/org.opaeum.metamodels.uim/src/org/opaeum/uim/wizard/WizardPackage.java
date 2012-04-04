/**
 */
package org.opaeum.uim.wizard;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.wizard.WizardFactory
 * @model kind="package"
 * @generated
 */
public interface WizardPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "wizard";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/wizard/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "wizard";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WizardPackage eINSTANCE = org.opaeum.uim.wizard.impl.WizardPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.AbstractWizardImpl <em>Abstract Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.AbstractWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getAbstractWizard()
	 * @generated
	 */
	int ABSTRACT_WIZARD = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__NAME = UimPackage.USER_INTERFACE_ENTRY_POINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__UNDER_USER_CONTROL = UimPackage.USER_INTERFACE_ENTRY_POINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__EDITABILITY = UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__VISIBILITY = UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__UML_ELEMENT_UID = UimPackage.USER_INTERFACE_ENTRY_POINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__PAGES = UimPackage.USER_INTERFACE_ENTRY_POINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD_FEATURE_COUNT = UimPackage.USER_INTERFACE_ENTRY_POINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.NewObjectWizardImpl <em>New Object Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.NewObjectWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getNewObjectWizard()
	 * @generated
	 */
	int NEW_OBJECT_WIZARD = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__NAME = ABSTRACT_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__UNDER_USER_CONTROL = ABSTRACT_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__EDITABILITY = ABSTRACT_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__VISIBILITY = ABSTRACT_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__UML_ELEMENT_UID = ABSTRACT_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__PAGES = ABSTRACT_WIZARD__PAGES;

	/**
	 * The number of structural features of the '<em>New Object Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD_FEATURE_COUNT = ABSTRACT_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.InvokeResponsibilityWizardImpl <em>Invoke Responsibility Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.InvokeResponsibilityWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getInvokeResponsibilityWizard()
	 * @generated
	 */
	int INVOKE_RESPONSIBILITY_WIZARD = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__NAME = ABSTRACT_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__UNDER_USER_CONTROL = ABSTRACT_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__EDITABILITY = ABSTRACT_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__VISIBILITY = ABSTRACT_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__UML_ELEMENT_UID = ABSTRACT_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD__PAGES = ABSTRACT_WIZARD__PAGES;

	/**
	 * The number of structural features of the '<em>Invoke Responsibility Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOKE_RESPONSIBILITY_WIZARD_FEATURE_COUNT = ABSTRACT_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.WizardPageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.WizardPageImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getWizardPage()
	 * @generated
	 */
	int WIZARD_PAGE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__NAME = UimPackage.PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__UNDER_USER_CONTROL = UimPackage.PAGE__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__UML_ELEMENT_UID = UimPackage.PAGE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__PANEL = UimPackage.PAGE__PANEL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__VISIBILITY = UimPackage.PAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__EDITABILITY = UimPackage.PAGE__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Wizard</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__WIZARD = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.AbstractWizard <em>Abstract Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Wizard</em>'.
	 * @see org.opaeum.uim.wizard.AbstractWizard
	 * @generated
	 */
	EClass getAbstractWizard();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.wizard.AbstractWizard#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see org.opaeum.uim.wizard.AbstractWizard#getPages()
	 * @see #getAbstractWizard()
	 * @generated
	 */
	EReference getAbstractWizard_Pages();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.NewObjectWizard <em>New Object Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Object Wizard</em>'.
	 * @see org.opaeum.uim.wizard.NewObjectWizard
	 * @generated
	 */
	EClass getNewObjectWizard();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.InvokeResponsibilityWizard <em>Invoke Responsibility Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invoke Responsibility Wizard</em>'.
	 * @see org.opaeum.uim.wizard.InvokeResponsibilityWizard
	 * @generated
	 */
	EClass getInvokeResponsibilityWizard();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.WizardPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.opaeum.uim.wizard.WizardPage
	 * @generated
	 */
	EClass getWizardPage();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.wizard.WizardPage#getWizard <em>Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Wizard</em>'.
	 * @see org.opaeum.uim.wizard.WizardPage#getWizard()
	 * @see #getWizardPage()
	 * @generated
	 */
	EReference getWizardPage_Wizard();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WizardFactory getWizardFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.AbstractWizardImpl <em>Abstract Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.AbstractWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getAbstractWizard()
		 * @generated
		 */
		EClass ABSTRACT_WIZARD = eINSTANCE.getAbstractWizard();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_WIZARD__PAGES = eINSTANCE.getAbstractWizard_Pages();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.NewObjectWizardImpl <em>New Object Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.NewObjectWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getNewObjectWizard()
		 * @generated
		 */
		EClass NEW_OBJECT_WIZARD = eINSTANCE.getNewObjectWizard();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.InvokeResponsibilityWizardImpl <em>Invoke Responsibility Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.InvokeResponsibilityWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getInvokeResponsibilityWizard()
		 * @generated
		 */
		EClass INVOKE_RESPONSIBILITY_WIZARD = eINSTANCE.getInvokeResponsibilityWizard();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.WizardPageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.WizardPageImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getWizardPage()
		 * @generated
		 */
		EClass WIZARD_PAGE = eINSTANCE.getWizardPage();

		/**
		 * The meta object literal for the '<em><b>Wizard</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIZARD_PAGE__WIZARD = eINSTANCE.getWizardPage_Wizard();

	}

} //WizardPackage
