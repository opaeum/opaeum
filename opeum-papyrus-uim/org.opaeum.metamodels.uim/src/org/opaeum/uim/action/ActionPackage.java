/**
 */
package org.opaeum.uim.action;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	int UIM_ACTION__NAME = UimPackage.UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__VISIBILITY = UimPackage.UIM_COMPONENT__VISIBILITY;

	/**
	 * The number of structural features of the '<em>Uim Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION_FEATURE_COUNT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.UimLinkImpl <em>Uim Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.UimLinkImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimLink()
	 * @generated
	 */
	int UIM_LINK = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__NAME = UimPackage.UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__VISIBILITY = UimPackage.UIM_COMPONENT__VISIBILITY;

	/**
	 * The number of structural features of the '<em>Uim Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_FEATURE_COUNT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.LinkToOperationImpl <em>Link To Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.LinkToOperationImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToOperation()
	 * @generated
	 */
	int LINK_TO_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_OPERATION__NAME = UIM_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_OPERATION__VISIBILITY = UIM_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_OPERATION__UML_ELEMENT_UID = UIM_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_OPERATION__TO_FORM = UIM_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Link To Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_OPERATION_FEATURE_COUNT = UIM_LINK_FEATURE_COUNT + 2;

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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Popup</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__POPUP = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.LinkToEntityImpl <em>Link To Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.LinkToEntityImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToEntity()
	 * @generated
	 */
	int LINK_TO_ENTITY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_ENTITY__NAME = UIM_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_ENTITY__VISIBILITY = UIM_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_ENTITY__UML_ELEMENT_UID = UIM_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_ENTITY__BINDING = UIM_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Link To Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_ENTITY_FEATURE_COUNT = UIM_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.OperationActionPopupImpl <em>Operation Action Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.OperationActionPopupImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationActionPopup()
	 * @generated
	 */
	int OPERATION_ACTION_POPUP = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_POPUP__NAME = UimPackage.USER_INTERFACE__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_POPUP__PANEL = UimPackage.USER_INTERFACE__PANEL;

	/**
	 * The feature id for the '<em><b>Operation Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_POPUP__OPERATION_ACTION = UimPackage.USER_INTERFACE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Action Popup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_POPUP_FEATURE_COUNT = UimPackage.USER_INTERFACE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.ActionKind
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getActionKind()
	 * @generated
	 */
	int ACTION_KIND = 8;


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
	 * Returns the meta object for class '{@link org.opaeum.uim.action.LinkToOperation <em>Link To Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link To Operation</em>'.
	 * @see org.opaeum.uim.action.LinkToOperation
	 * @generated
	 */
	EClass getLinkToOperation();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.LinkToOperation#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.opaeum.uim.action.LinkToOperation#getToForm()
	 * @see #getLinkToOperation()
	 * @generated
	 */
	EReference getLinkToOperation_ToForm();

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
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.OperationAction#getPopup <em>Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Popup</em>'.
	 * @see org.opaeum.uim.action.OperationAction#getPopup()
	 * @see #getOperationAction()
	 * @generated
	 */
	EReference getOperationAction_Popup();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.UimLink <em>Uim Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Link</em>'.
	 * @see org.opaeum.uim.action.UimLink
	 * @generated
	 */
	EClass getUimLink();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.LinkToEntity <em>Link To Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link To Entity</em>'.
	 * @see org.opaeum.uim.action.LinkToEntity
	 * @generated
	 */
	EClass getLinkToEntity();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.action.LinkToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.action.LinkToEntity#getBinding()
	 * @see #getLinkToEntity()
	 * @generated
	 */
	EReference getLinkToEntity_Binding();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.OperationActionPopup <em>Operation Action Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Action Popup</em>'.
	 * @see org.opaeum.uim.action.OperationActionPopup
	 * @generated
	 */
	EClass getOperationActionPopup();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation Action</em>'.
	 * @see org.opaeum.uim.action.OperationActionPopup#getOperationAction()
	 * @see #getOperationActionPopup()
	 * @generated
	 */
	EReference getOperationActionPopup_OperationAction();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.LinkToOperationImpl <em>Link To Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.LinkToOperationImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToOperation()
		 * @generated
		 */
		EClass LINK_TO_OPERATION = eINSTANCE.getLinkToOperation();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_TO_OPERATION__TO_FORM = eINSTANCE.getLinkToOperation_ToForm();

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
		 * The meta object literal for the '<em><b>Popup</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_ACTION__POPUP = eINSTANCE.getOperationAction_Popup();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.UimLinkImpl <em>Uim Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.UimLinkImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getUimLink()
		 * @generated
		 */
		EClass UIM_LINK = eINSTANCE.getUimLink();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.LinkToEntityImpl <em>Link To Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.LinkToEntityImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToEntity()
		 * @generated
		 */
		EClass LINK_TO_ENTITY = eINSTANCE.getLinkToEntity();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_TO_ENTITY__BINDING = eINSTANCE.getLinkToEntity_Binding();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.OperationActionPopupImpl <em>Operation Action Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.OperationActionPopupImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationActionPopup()
		 * @generated
		 */
		EClass OPERATION_ACTION_POPUP = eINSTANCE.getOperationActionPopup();

		/**
		 * The meta object literal for the '<em><b>Operation Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_ACTION_POPUP__OPERATION_ACTION = eINSTANCE.getOperationActionPopup_OperationAction();

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
