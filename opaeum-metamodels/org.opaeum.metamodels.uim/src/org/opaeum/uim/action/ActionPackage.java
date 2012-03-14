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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__PREFERRED_WIDTH = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__PREFERRED_HEIGHT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__FILL_HORIZONTALLY = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__FILL_VERTICALLY = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Uim Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION_FEATURE_COUNT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.BuiltInActionButtonImpl <em>Built In Action Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.BuiltInActionButtonImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInActionButton()
	 * @generated
	 */
	int BUILT_IN_ACTION_BUTTON = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__PREFERRED_WIDTH = UIM_ACTION__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__PREFERRED_HEIGHT = UIM_ACTION__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__FILL_HORIZONTALLY = UIM_ACTION__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__FILL_VERTICALLY = UIM_ACTION__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__KIND = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Action Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.TransitionButtonImpl <em>Transition Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.TransitionButtonImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getTransitionButton()
	 * @generated
	 */
	int TRANSITION_BUTTON = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__PREFERRED_WIDTH = UIM_ACTION__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__PREFERRED_HEIGHT = UIM_ACTION__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__FILL_HORIZONTALLY = UIM_ACTION__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__FILL_VERTICALLY = UIM_ACTION__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transition Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__PREFERRED_WIDTH = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__PREFERRED_HEIGHT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__FILL_HORIZONTALLY = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK__FILL_VERTICALLY = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Uim Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_FEATURE_COUNT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.LinkToQueryImpl <em>Link To Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.LinkToQueryImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToQuery()
	 * @generated
	 */
	int LINK_TO_QUERY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__NAME = UIM_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__VISIBILITY = UIM_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__PREFERRED_WIDTH = UIM_LINK__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__PREFERRED_HEIGHT = UIM_LINK__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__FILL_HORIZONTALLY = UIM_LINK__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__FILL_VERTICALLY = UIM_LINK__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__UML_ELEMENT_UID = UIM_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__TO_FORM = UIM_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Link To Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY_FEATURE_COUNT = UIM_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.OperationButtonImpl <em>Operation Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.OperationButtonImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationButton()
	 * @generated
	 */
	int OPERATION_BUTTON = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__PREFERRED_WIDTH = UIM_ACTION__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__PREFERRED_HEIGHT = UIM_ACTION__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__FILL_HORIZONTALLY = UIM_ACTION__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__FILL_VERTICALLY = UIM_ACTION__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Popup</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON__POPUP = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BUTTON_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.OperationPopupImpl <em>Operation Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.OperationPopupImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationPopup()
	 * @generated
	 */
	int OPERATION_POPUP = 6;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP__UML_ELEMENT_UID = UimPackage.PAGE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Action</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP__OPERATION_ACTION = UimPackage.PAGE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP__PAGES = UimPackage.PAGE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation Popup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_FEATURE_COUNT = UimPackage.PAGE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.OperationPopupPageImpl <em>Operation Popup Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.OperationPopupPageImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationPopupPage()
	 * @generated
	 */
	int OPERATION_POPUP_PAGE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE__NAME = UimPackage.PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE__UML_ELEMENT_UID = UimPackage.PAGE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE__PANEL = UimPackage.PAGE__PANEL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE__VISIBILITY = UimPackage.PAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE__EDITABILITY = UimPackage.PAGE__EDITABILITY;

	/**
	 * The number of structural features of the '<em>Operation Popup Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_POPUP_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.BuiltInLinkImpl <em>Built In Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.BuiltInLinkImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLink()
	 * @generated
	 */
	int BUILT_IN_LINK = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__NAME = UIM_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__VISIBILITY = UIM_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__PREFERRED_WIDTH = UIM_LINK__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__PREFERRED_HEIGHT = UIM_LINK__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__FILL_HORIZONTALLY = UIM_LINK__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__FILL_VERTICALLY = UIM_LINK__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__KIND = UIM_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK_FEATURE_COUNT = UIM_LINK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.ActionKind
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getActionKind()
	 * @generated
	 */
	int ACTION_KIND = 9;


	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.BuiltInLinkKind <em>Built In Link Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.BuiltInLinkKind
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLinkKind()
	 * @generated
	 */
	int BUILT_IN_LINK_KIND = 10;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.BuiltInActionButton <em>Built In Action Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Action Button</em>'.
	 * @see org.opaeum.uim.action.BuiltInActionButton
	 * @generated
	 */
	EClass getBuiltInActionButton();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.action.BuiltInActionButton#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.opaeum.uim.action.BuiltInActionButton#getKind()
	 * @see #getBuiltInActionButton()
	 * @generated
	 */
	EAttribute getBuiltInActionButton_Kind();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.action.TransitionButton <em>Transition Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Button</em>'.
	 * @see org.opaeum.uim.action.TransitionButton
	 * @generated
	 */
	EClass getTransitionButton();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.LinkToQuery <em>Link To Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link To Query</em>'.
	 * @see org.opaeum.uim.action.LinkToQuery
	 * @generated
	 */
	EClass getLinkToQuery();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.LinkToQuery#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.opaeum.uim.action.LinkToQuery#getToForm()
	 * @see #getLinkToQuery()
	 * @generated
	 */
	EReference getLinkToQuery_ToForm();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.OperationButton <em>Operation Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Button</em>'.
	 * @see org.opaeum.uim.action.OperationButton
	 * @generated
	 */
	EClass getOperationButton();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.action.OperationButton#getPopup <em>Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Popup</em>'.
	 * @see org.opaeum.uim.action.OperationButton#getPopup()
	 * @see #getOperationButton()
	 * @generated
	 */
	EReference getOperationButton_Popup();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.action.OperationPopup <em>Operation Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Popup</em>'.
	 * @see org.opaeum.uim.action.OperationPopup
	 * @generated
	 */
	EClass getOperationPopup();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.action.OperationPopup#getOperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Operation Action</em>'.
	 * @see org.opaeum.uim.action.OperationPopup#getOperationAction()
	 * @see #getOperationPopup()
	 * @generated
	 */
	EReference getOperationPopup_OperationAction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.action.OperationPopup#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see org.opaeum.uim.action.OperationPopup#getPages()
	 * @see #getOperationPopup()
	 * @generated
	 */
	EReference getOperationPopup_Pages();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.OperationPopupPage <em>Operation Popup Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Popup Page</em>'.
	 * @see org.opaeum.uim.action.OperationPopupPage
	 * @generated
	 */
	EClass getOperationPopupPage();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.BuiltInLink <em>Built In Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Link</em>'.
	 * @see org.opaeum.uim.action.BuiltInLink
	 * @generated
	 */
	EClass getBuiltInLink();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.action.BuiltInLink#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.opaeum.uim.action.BuiltInLink#getKind()
	 * @see #getBuiltInLink()
	 * @generated
	 */
	EAttribute getBuiltInLink_Kind();

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
	 * Returns the meta object for enum '{@link org.opaeum.uim.action.BuiltInLinkKind <em>Built In Link Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Built In Link Kind</em>'.
	 * @see org.opaeum.uim.action.BuiltInLinkKind
	 * @generated
	 */
	EEnum getBuiltInLinkKind();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.BuiltInActionButtonImpl <em>Built In Action Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.BuiltInActionButtonImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInActionButton()
		 * @generated
		 */
		EClass BUILT_IN_ACTION_BUTTON = eINSTANCE.getBuiltInActionButton();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILT_IN_ACTION_BUTTON__KIND = eINSTANCE.getBuiltInActionButton_Kind();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.TransitionButtonImpl <em>Transition Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.TransitionButtonImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getTransitionButton()
		 * @generated
		 */
		EClass TRANSITION_BUTTON = eINSTANCE.getTransitionButton();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.LinkToQueryImpl <em>Link To Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.LinkToQueryImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getLinkToQuery()
		 * @generated
		 */
		EClass LINK_TO_QUERY = eINSTANCE.getLinkToQuery();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_TO_QUERY__TO_FORM = eINSTANCE.getLinkToQuery_ToForm();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.OperationButtonImpl <em>Operation Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.OperationButtonImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationButton()
		 * @generated
		 */
		EClass OPERATION_BUTTON = eINSTANCE.getOperationButton();

		/**
		 * The meta object literal for the '<em><b>Popup</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_BUTTON__POPUP = eINSTANCE.getOperationButton_Popup();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.OperationPopupImpl <em>Operation Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.OperationPopupImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationPopup()
		 * @generated
		 */
		EClass OPERATION_POPUP = eINSTANCE.getOperationPopup();

		/**
		 * The meta object literal for the '<em><b>Operation Action</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_POPUP__OPERATION_ACTION = eINSTANCE.getOperationPopup_OperationAction();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_POPUP__PAGES = eINSTANCE.getOperationPopup_Pages();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.OperationPopupPageImpl <em>Operation Popup Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.OperationPopupPageImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getOperationPopupPage()
		 * @generated
		 */
		EClass OPERATION_POPUP_PAGE = eINSTANCE.getOperationPopupPage();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.BuiltInLinkImpl <em>Built In Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.BuiltInLinkImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLink()
		 * @generated
		 */
		EClass BUILT_IN_LINK = eINSTANCE.getBuiltInLink();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILT_IN_LINK__KIND = eINSTANCE.getBuiltInLink_Kind();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.ActionKind <em>Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.ActionKind
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getActionKind()
		 * @generated
		 */
		EEnum ACTION_KIND = eINSTANCE.getActionKind();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.BuiltInLinkKind <em>Built In Link Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.BuiltInLinkKind
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLinkKind()
		 * @generated
		 */
		EEnum BUILT_IN_LINK_KIND = eINSTANCE.getBuiltInLinkKind();

	}

} //ActionPackage
