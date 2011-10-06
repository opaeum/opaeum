/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.action;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.layout.LayoutPackage;

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
 * @see org.opaeum.uim.action.ActionFactory
 * @model kind="package"
 * @generated
 */
public interface ActionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "action";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/action/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uact";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ActionPackage eINSTANCE = org.opaeum.uim.action.impl.ActionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.UimActionImpl <em>Uim Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.UimActionImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimAction()
	 * @generated
	 */
	int UIM_ACTION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__NAME = LayoutPackage.OUTLAYABLE_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__VISIBILITY = LayoutPackage.OUTLAYABLE_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__PARENT = LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Uim Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION_FEATURE_COUNT = LayoutPackage.OUTLAYABLE_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.BuiltInActionImpl <em>Built In Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.BuiltInActionImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInAction()
	 * @generated
	 */
	int BUILT_IN_ACTION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__KIND = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.TransitionActionImpl <em>Transition Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.TransitionActionImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getTransitionAction()
	 * @generated
	 */
	int TRANSITION_ACTION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transition Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.UimNavigationImpl <em>Uim Navigation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.UimNavigationImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimNavigation()
	 * @generated
	 */
	int UIM_NAVIGATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__NAME = LayoutPackage.OUTLAYABLE_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__VISIBILITY = LayoutPackage.OUTLAYABLE_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__PARENT = LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Uim Navigation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION_FEATURE_COUNT = LayoutPackage.OUTLAYABLE_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.NavigationToOperationImpl <em>Navigation To Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.NavigationToOperationImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getNavigationToOperation()
	 * @generated
	 */
	int NAVIGATION_TO_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__NAME = UIM_NAVIGATION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__VISIBILITY = UIM_NAVIGATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__UML_ELEMENT_UID = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Navigation To Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.OperationActionImpl <em>Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.OperationActionImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationAction()
	 * @generated
	 */
	int OPERATION_ACTION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.NavigationToEntityImpl <em>Navigation To Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.NavigationToEntityImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getNavigationToEntity()
	 * @generated
	 */
	int NAVIGATION_TO_ENTITY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__NAME = UIM_NAVIGATION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__VISIBILITY = UIM_NAVIGATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__UML_ELEMENT_UID = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__BINDING = UIM_NAVIGATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Navigation To Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.ActionKind
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getActionKind()
	 * @generated
	 */
	int ACTION_KIND = 7;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.BuiltInAction <em>Built In Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Action</em>'.
	 * @see org.opaeum.uim.action.BuiltInAction
	 * @generated
	 */
	EClass getBuiltInAction();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.action.BuiltInAction#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.opaeum.uim.action.BuiltInAction#getKind()
	 * @see #getBuiltInAction()
	 * @generated
	 */
	EAttribute getBuiltInAction_Kind();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.UimAction <em>Uim Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Action</em>'.
	 * @see org.opaeum.uim.action.UimAction
	 * @generated
	 */
	EClass getUimAction();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.TransitionAction <em>Transition Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Action</em>'.
	 * @see org.opaeum.uim.action.TransitionAction
	 * @generated
	 */
	EClass getTransitionAction();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.NavigationToOperation <em>Navigation To Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation To Operation</em>'.
	 * @see org.opaeum.uim.action.NavigationToOperation
	 * @generated
	 */
	EClass getNavigationToOperation();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.NavigationToOperation#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.opaeum.uim.action.NavigationToOperation#getToForm()
	 * @see #getNavigationToOperation()
	 * @generated
	 */
	EReference getNavigationToOperation_ToForm();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.OperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Action</em>'.
	 * @see org.opaeum.uim.action.OperationAction
	 * @generated
	 */
	EClass getOperationAction();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.UimNavigation <em>Uim Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Navigation</em>'.
	 * @see org.opaeum.uim.action.UimNavigation
	 * @generated
	 */
	EClass getUimNavigation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.NavigationToEntity <em>Navigation To Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation To Entity</em>'.
	 * @see org.opaeum.uim.action.NavigationToEntity
	 * @generated
	 */
	EClass getNavigationToEntity();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.NavigationToEntity#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.opaeum.uim.action.NavigationToEntity#getToForm()
	 * @see #getNavigationToEntity()
	 * @generated
	 */
	EReference getNavigationToEntity_ToForm();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.action.NavigationToEntity#getBinding()
	 * @see #getNavigationToEntity()
	 * @generated
	 */
	EReference getNavigationToEntity_Binding();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Kind</em>'.
	 * @see org.opaeum.uim.action.ActionKind
	 * @generated
	 */
	EEnum getActionKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ActionFactory getActionFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.BuiltInActionImpl <em>Built In Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.BuiltInActionImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInAction()
		 * @generated
		 */
		EClass BUILT_IN_ACTION = eINSTANCE.getBuiltInAction();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILT_IN_ACTION__KIND = eINSTANCE.getBuiltInAction_Kind();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.UimActionImpl <em>Uim Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.UimActionImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimAction()
		 * @generated
		 */
		EClass UIM_ACTION = eINSTANCE.getUimAction();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.TransitionActionImpl <em>Transition Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.TransitionActionImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getTransitionAction()
		 * @generated
		 */
		EClass TRANSITION_ACTION = eINSTANCE.getTransitionAction();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.NavigationToOperationImpl <em>Navigation To Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.NavigationToOperationImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getNavigationToOperation()
		 * @generated
		 */
		EClass NAVIGATION_TO_OPERATION = eINSTANCE.getNavigationToOperation();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_OPERATION__TO_FORM = eINSTANCE.getNavigationToOperation_ToForm();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.OperationActionImpl <em>Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.OperationActionImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationAction()
		 * @generated
		 */
		EClass OPERATION_ACTION = eINSTANCE.getOperationAction();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.UimNavigationImpl <em>Uim Navigation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.UimNavigationImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimNavigation()
		 * @generated
		 */
		EClass UIM_NAVIGATION = eINSTANCE.getUimNavigation();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.NavigationToEntityImpl <em>Navigation To Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.NavigationToEntityImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getNavigationToEntity()
		 * @generated
		 */
		EClass NAVIGATION_TO_ENTITY = eINSTANCE.getNavigationToEntity();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_ENTITY__TO_FORM = eINSTANCE.getNavigationToEntity_ToForm();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_ENTITY__BINDING = eINSTANCE.getNavigationToEntity_Binding();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.ActionKind
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getActionKind()
		 * @generated
		 */
		EEnum ACTION_KIND = eINSTANCE.getActionKind();

	}

} //ActionPackage
