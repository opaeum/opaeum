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
	int ABSTRACT_WIZARD__NAME = UimPackage.USER_INTERFACE_ROOT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__UNDER_USER_CONTROL = UimPackage.USER_INTERFACE_ROOT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__UML_ELEMENT_UID = UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__LABEL_OVERRIDE = UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__EDITABILITY = UimPackage.USER_INTERFACE_ROOT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__VISIBILITY = UimPackage.USER_INTERFACE_ROOT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__IGNORED_ELEMENTS = UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__SUPER_USER_INTERFACES = UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__PAGE_ORDERING = UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD__PAGES = UimPackage.USER_INTERFACE_ROOT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WIZARD_FEATURE_COUNT = UimPackage.USER_INTERFACE_ROOT_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__UML_ELEMENT_UID = ABSTRACT_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__LABEL_OVERRIDE = ABSTRACT_WIZARD__LABEL_OVERRIDE;

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
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__IGNORED_ELEMENTS = ABSTRACT_WIZARD__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__SUPER_USER_INTERFACES = ABSTRACT_WIZARD__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__PAGE_ORDERING = ABSTRACT_WIZARD__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__PAGES = ABSTRACT_WIZARD__PAGES;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD__MODEL = ABSTRACT_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Object Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_OBJECT_WIZARD_FEATURE_COUNT = ABSTRACT_WIZARD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.InvocationWizardImpl <em>Invocation Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.InvocationWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getInvocationWizard()
	 * @generated
	 */
	int INVOCATION_WIZARD = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__NAME = ABSTRACT_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__UNDER_USER_CONTROL = ABSTRACT_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__UML_ELEMENT_UID = ABSTRACT_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__LABEL_OVERRIDE = ABSTRACT_WIZARD__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__EDITABILITY = ABSTRACT_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__VISIBILITY = ABSTRACT_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__IGNORED_ELEMENTS = ABSTRACT_WIZARD__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__SUPER_USER_INTERFACES = ABSTRACT_WIZARD__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__PAGE_ORDERING = ABSTRACT_WIZARD__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD__PAGES = ABSTRACT_WIZARD__PAGES;

	/**
	 * The number of structural features of the '<em>Invocation Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_WIZARD_FEATURE_COUNT = ABSTRACT_WIZARD_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__LABEL_OVERRIDE = UimPackage.PAGE__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIZARD_PAGE__PANEL = UimPackage.PAGE__PANEL;

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
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.ResponsibilityInvocationWizardImpl <em>Responsibility Invocation Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.ResponsibilityInvocationWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getResponsibilityInvocationWizard()
	 * @generated
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__NAME = INVOCATION_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__UNDER_USER_CONTROL = INVOCATION_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__UML_ELEMENT_UID = INVOCATION_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__LABEL_OVERRIDE = INVOCATION_WIZARD__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__EDITABILITY = INVOCATION_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__VISIBILITY = INVOCATION_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__IGNORED_ELEMENTS = INVOCATION_WIZARD__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__SUPER_USER_INTERFACES = INVOCATION_WIZARD__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__PAGE_ORDERING = INVOCATION_WIZARD__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__PAGES = INVOCATION_WIZARD__PAGES;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD__MODEL = INVOCATION_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Responsibility Invocation Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_INVOCATION_WIZARD_FEATURE_COUNT = INVOCATION_WIZARD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.BehaviorInvocationWizardImpl <em>Behavior Invocation Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.BehaviorInvocationWizardImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getBehaviorInvocationWizard()
	 * @generated
	 */
	int BEHAVIOR_INVOCATION_WIZARD = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__NAME = INVOCATION_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__UNDER_USER_CONTROL = INVOCATION_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__UML_ELEMENT_UID = INVOCATION_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__LABEL_OVERRIDE = INVOCATION_WIZARD__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__EDITABILITY = INVOCATION_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__VISIBILITY = INVOCATION_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__IGNORED_ELEMENTS = INVOCATION_WIZARD__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__SUPER_USER_INTERFACES = INVOCATION_WIZARD__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__PAGE_ORDERING = INVOCATION_WIZARD__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__PAGES = INVOCATION_WIZARD__PAGES;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD__MODEL = INVOCATION_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Behavior Invocation Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_INVOCATION_WIZARD_FEATURE_COUNT = INVOCATION_WIZARD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.wizard.impl.OperationResultPageImpl <em>Operation Result Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.wizard.impl.OperationResultPageImpl
	 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getOperationResultPage()
	 * @generated
	 */
	int OPERATION_RESULT_PAGE = 6;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__VISIBILITY = UimPackage.PAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__EDITABILITY = UimPackage.PAGE__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__NAME = UimPackage.PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__UNDER_USER_CONTROL = UimPackage.PAGE__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__UML_ELEMENT_UID = UimPackage.PAGE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__LABEL_OVERRIDE = UimPackage.PAGE__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__PANEL = UimPackage.PAGE__PANEL;

	/**
	 * The feature id for the '<em><b>Wizard</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE__WIZARD = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Result Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_RESULT_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 1;


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
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.wizard.NewObjectWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.wizard.NewObjectWizard#getModel()
	 * @see #getNewObjectWizard()
	 * @generated
	 */
	EReference getNewObjectWizard_Model();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.InvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invocation Wizard</em>'.
	 * @see org.opaeum.uim.wizard.InvocationWizard
	 * @generated
	 */
	EClass getInvocationWizard();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.ResponsibilityInvocationWizard <em>Responsibility Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility Invocation Wizard</em>'.
	 * @see org.opaeum.uim.wizard.ResponsibilityInvocationWizard
	 * @generated
	 */
	EClass getResponsibilityInvocationWizard();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel()
	 * @see #getResponsibilityInvocationWizard()
	 * @generated
	 */
	EReference getResponsibilityInvocationWizard_Model();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.BehaviorInvocationWizard <em>Behavior Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Invocation Wizard</em>'.
	 * @see org.opaeum.uim.wizard.BehaviorInvocationWizard
	 * @generated
	 */
	EClass getBehaviorInvocationWizard();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel()
	 * @see #getBehaviorInvocationWizard()
	 * @generated
	 */
	EReference getBehaviorInvocationWizard_Model();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.wizard.OperationResultPage <em>Operation Result Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Result Page</em>'.
	 * @see org.opaeum.uim.wizard.OperationResultPage
	 * @generated
	 */
	EClass getOperationResultPage();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.wizard.OperationResultPage#getWizard <em>Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Wizard</em>'.
	 * @see org.opaeum.uim.wizard.OperationResultPage#getWizard()
	 * @see #getOperationResultPage()
	 * @generated
	 */
	EReference getOperationResultPage_Wizard();

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
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_OBJECT_WIZARD__MODEL = eINSTANCE.getNewObjectWizard_Model();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.InvocationWizardImpl <em>Invocation Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.InvocationWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getInvocationWizard()
		 * @generated
		 */
		EClass INVOCATION_WIZARD = eINSTANCE.getInvocationWizard();

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

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.ResponsibilityInvocationWizardImpl <em>Responsibility Invocation Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.ResponsibilityInvocationWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getResponsibilityInvocationWizard()
		 * @generated
		 */
		EClass RESPONSIBILITY_INVOCATION_WIZARD = eINSTANCE.getResponsibilityInvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_INVOCATION_WIZARD__MODEL = eINSTANCE.getResponsibilityInvocationWizard_Model();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.BehaviorInvocationWizardImpl <em>Behavior Invocation Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.BehaviorInvocationWizardImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getBehaviorInvocationWizard()
		 * @generated
		 */
		EClass BEHAVIOR_INVOCATION_WIZARD = eINSTANCE.getBehaviorInvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_INVOCATION_WIZARD__MODEL = eINSTANCE.getBehaviorInvocationWizard_Model();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.wizard.impl.OperationResultPageImpl <em>Operation Result Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.wizard.impl.OperationResultPageImpl
		 * @see org.opaeum.uim.wizard.impl.WizardPackageImpl#getOperationResultPage()
		 * @generated
		 */
		EClass OPERATION_RESULT_PAGE = eINSTANCE.getOperationResultPage();

		/**
		 * The meta object literal for the '<em><b>Wizard</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_RESULT_PAGE__WIZARD = eINSTANCE.getOperationResultPage_Wizard();

	}

} //WizardPackage
