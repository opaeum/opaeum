/**
 */
package org.opaeum.uim.action;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.component.ComponentPackage;

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
	String eNS_PREFIX = "action";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ActionPackage eINSTANCE = org.opaeum.uim.action.impl.ActionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.AbstractActionButtonImpl <em>Abstract Action Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.AbstractActionButtonImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getAbstractActionButton()
	 * @generated
	 */
	int ABSTRACT_ACTION_BUTTON = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__NAME = ComponentPackage.UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__UNDER_USER_CONTROL = ComponentPackage.UIM_COMPONENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__VISIBILITY = ComponentPackage.UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__PREFERRED_WIDTH = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__PREFERRED_HEIGHT = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__FILL_HORIZONTALLY = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON__FILL_VERTICALLY = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Abstract Action Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BUTTON_FEATURE_COUNT = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 4;

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
	int BUILT_IN_ACTION_BUTTON__NAME = ABSTRACT_ACTION_BUTTON__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__UNDER_USER_CONTROL = ABSTRACT_ACTION_BUTTON__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__VISIBILITY = ABSTRACT_ACTION_BUTTON__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__PREFERRED_WIDTH = ABSTRACT_ACTION_BUTTON__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__PREFERRED_HEIGHT = ABSTRACT_ACTION_BUTTON__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__FILL_HORIZONTALLY = ABSTRACT_ACTION_BUTTON__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__FILL_VERTICALLY = ABSTRACT_ACTION_BUTTON__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON__KIND = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Action Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_BUTTON_FEATURE_COUNT = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 1;

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
	int TRANSITION_BUTTON__NAME = ABSTRACT_ACTION_BUTTON__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__UNDER_USER_CONTROL = ABSTRACT_ACTION_BUTTON__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__VISIBILITY = ABSTRACT_ACTION_BUTTON__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__PREFERRED_WIDTH = ABSTRACT_ACTION_BUTTON__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__PREFERRED_HEIGHT = ABSTRACT_ACTION_BUTTON__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__FILL_HORIZONTALLY = ABSTRACT_ACTION_BUTTON__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__FILL_VERTICALLY = ABSTRACT_ACTION_BUTTON__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__UML_ELEMENT_UID = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON__LABEL_OVERRIDE = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Transition Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_BUTTON_FEATURE_COUNT = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.AbstractLinkImpl <em>Abstract Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.AbstractLinkImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getAbstractLink()
	 * @generated
	 */
	int ABSTRACT_LINK = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__NAME = ComponentPackage.UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__UNDER_USER_CONTROL = ComponentPackage.UIM_COMPONENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__VISIBILITY = ComponentPackage.UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__PREFERRED_WIDTH = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__PREFERRED_HEIGHT = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__FILL_HORIZONTALLY = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK__FILL_VERTICALLY = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Abstract Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_LINK_FEATURE_COUNT = ComponentPackage.UIM_COMPONENT_FEATURE_COUNT + 4;

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
	int LINK_TO_QUERY__NAME = ABSTRACT_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__UNDER_USER_CONTROL = ABSTRACT_LINK__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__VISIBILITY = ABSTRACT_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__PREFERRED_WIDTH = ABSTRACT_LINK__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__PREFERRED_HEIGHT = ABSTRACT_LINK__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__FILL_HORIZONTALLY = ABSTRACT_LINK__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__FILL_VERTICALLY = ABSTRACT_LINK__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__UML_ELEMENT_UID = ABSTRACT_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY__LABEL_OVERRIDE = ABSTRACT_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Link To Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TO_QUERY_FEATURE_COUNT = ABSTRACT_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.InvocationButtonImpl <em>Invocation Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.InvocationButtonImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getInvocationButton()
	 * @generated
	 */
	int INVOCATION_BUTTON = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__NAME = ABSTRACT_ACTION_BUTTON__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__UNDER_USER_CONTROL = ABSTRACT_ACTION_BUTTON__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__VISIBILITY = ABSTRACT_ACTION_BUTTON__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__PREFERRED_WIDTH = ABSTRACT_ACTION_BUTTON__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__PREFERRED_HEIGHT = ABSTRACT_ACTION_BUTTON__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__FILL_HORIZONTALLY = ABSTRACT_ACTION_BUTTON__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__FILL_VERTICALLY = ABSTRACT_ACTION_BUTTON__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__UML_ELEMENT_UID = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__LABEL_OVERRIDE = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Popup</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON__POPUP = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Invocation Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_BUTTON_FEATURE_COUNT = ABSTRACT_ACTION_BUTTON_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.action.impl.BuiltInLinkImpl <em>Built In Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.impl.BuiltInLinkImpl
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLink()
	 * @generated
	 */
	int BUILT_IN_LINK = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__NAME = ABSTRACT_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__UNDER_USER_CONTROL = ABSTRACT_LINK__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__VISIBILITY = ABSTRACT_LINK__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__PREFERRED_WIDTH = ABSTRACT_LINK__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__PREFERRED_HEIGHT = ABSTRACT_LINK__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__FILL_HORIZONTALLY = ABSTRACT_LINK__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__FILL_VERTICALLY = ABSTRACT_LINK__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK__KIND = ABSTRACT_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_LINK_FEATURE_COUNT = ABSTRACT_LINK_FEATURE_COUNT + 1;

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
	 * The meta object id for the '{@link org.opaeum.uim.action.BuiltInLinkKind <em>Built In Link Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.action.BuiltInLinkKind
	 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getBuiltInLinkKind()
	 * @generated
	 */
	int BUILT_IN_LINK_KIND = 8;


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
	 * Returns the meta object for class '{@link org.opaeum.uim.action.AbstractActionButton <em>Abstract Action Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Action Button</em>'.
	 * @see org.opaeum.uim.action.AbstractActionButton
	 * @generated
	 */
	EClass getAbstractActionButton();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.action.InvocationButton <em>Invocation Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invocation Button</em>'.
	 * @see org.opaeum.uim.action.InvocationButton
	 * @generated
	 */
	EClass getInvocationButton();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.action.InvocationButton#getPopup <em>Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Popup</em>'.
	 * @see org.opaeum.uim.action.InvocationButton#getPopup()
	 * @see #getInvocationButton()
	 * @generated
	 */
	EReference getInvocationButton_Popup();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.action.AbstractLink <em>Abstract Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Link</em>'.
	 * @see org.opaeum.uim.action.AbstractLink
	 * @generated
	 */
	EClass getAbstractLink();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.AbstractActionButtonImpl <em>Abstract Action Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.AbstractActionButtonImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getAbstractActionButton()
		 * @generated
		 */
		EClass ABSTRACT_ACTION_BUTTON = eINSTANCE.getAbstractActionButton();

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
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.InvocationButtonImpl <em>Invocation Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.InvocationButtonImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getInvocationButton()
		 * @generated
		 */
		EClass INVOCATION_BUTTON = eINSTANCE.getInvocationButton();

		/**
		 * The meta object literal for the '<em><b>Popup</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INVOCATION_BUTTON__POPUP = eINSTANCE.getInvocationButton_Popup();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.action.impl.AbstractLinkImpl <em>Abstract Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.action.impl.AbstractLinkImpl
		 * @see org.opaeum.uim.action.impl.ActionPackageImpl#getAbstractLink()
		 * @generated
		 */
		EClass ABSTRACT_LINK = eINSTANCE.getAbstractLink();

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
