/**
 */
package org.opaeum.uim.control;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.opaeum.uim.control.ControlFactory
 * @model kind="package"
 * @generated
 */
public interface ControlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "control";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/control/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ctl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlPackage eINSTANCE = org.opaeum.uim.control.impl.ControlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimControlImpl <em>Uim Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimControlImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimControl()
	 * @generated
	 */
	int UIM_CONTROL = 10;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL__MIMUM_WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL__FIELD = 1;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL__MINIMUM_HEIGHT = 2;

	/**
	 * The number of structural features of the '<em>Uim Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimNumberScrollerImpl <em>Uim Number Scroller</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimNumberScrollerImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimNumberScroller()
	 * @generated
	 */
	int UIM_NUMBER_SCROLLER = 0;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Number Scroller</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimToggleButtonImpl <em>Uim Toggle Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimToggleButtonImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimToggleButton()
	 * @generated
	 */
	int UIM_TOGGLE_BUTTON = 1;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Toggle Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimLookupImpl <em>Uim Lookup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimLookupImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLookup()
	 * @generated
	 */
	int UIM_LOOKUP = 5;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__LOOKUP_SOURCE = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Lookup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimPopupSearchImpl <em>Uim Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimPopupSearchImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimPopupSearch()
	 * @generated
	 */
	int UIM_POPUP_SEARCH = 2;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_POPUP_SEARCH__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_POPUP_SEARCH__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_POPUP_SEARCH__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_POPUP_SEARCH__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Popup Search</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_POPUP_SEARCH_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimDropdownImpl <em>Uim Dropdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimDropdownImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDropdown()
	 * @generated
	 */
	int UIM_DROPDOWN = 3;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Dropdown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimCheckBoxImpl <em>Uim Check Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimCheckBoxImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimCheckBox()
	 * @generated
	 */
	int UIM_CHECK_BOX = 4;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Check Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimTextAreaImpl <em>Uim Text Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimTextAreaImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimTextArea()
	 * @generated
	 */
	int UIM_TEXT_AREA = 6;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__ROWS = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Text Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimTextImpl <em>Uim Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimTextImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimText()
	 * @generated
	 */
	int UIM_TEXT = 7;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimDatePopupImpl <em>Uim Date Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimDatePopupImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDatePopup()
	 * @generated
	 */
	int UIM_DATE_POPUP = 8;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Date Popup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimListBoxImpl <em>Uim List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimListBoxImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimListBox()
	 * @generated
	 */
	int UIM_LIST_BOX = 9;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX__ROWS = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim List Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LIST_BOX_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimTreeViewImpl <em>Uim Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimTreeViewImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimTreeView()
	 * @generated
	 */
	int UIM_TREE_VIEW = 11;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TREE_VIEW__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TREE_VIEW__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TREE_VIEW__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TREE_VIEW__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Tree View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TREE_VIEW_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimLinkControlImpl <em>Uim Link Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimLinkControlImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLinkControl()
	 * @generated
	 */
	int UIM_LINK_CONTROL = 12;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_CONTROL__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_CONTROL__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_CONTROL__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Editor To Open</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_CONTROL__EDITOR_TO_OPEN = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Link Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LINK_CONTROL_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimDateScrollerImpl <em>Uim Date Scroller</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimDateScrollerImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDateScroller()
	 * @generated
	 */
	int UIM_DATE_SCROLLER = 13;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_SCROLLER__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_SCROLLER__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_SCROLLER__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Date Scroller</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_SCROLLER_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimSelectionTableImpl <em>Uim Selection Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimSelectionTableImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSelectionTable()
	 * @generated
	 */
	int UIM_SELECTION_TABLE = 14;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SELECTION_TABLE__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SELECTION_TABLE__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SELECTION_TABLE__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Selection Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SELECTION_TABLE_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimRadioButtonImpl <em>Uim Radio Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimRadioButtonImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimRadioButton()
	 * @generated
	 */
	int UIM_RADIO_BUTTON = 15;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_RADIO_BUTTON__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_RADIO_BUTTON__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_RADIO_BUTTON__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Radio Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_RADIO_BUTTON_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimLabelImpl <em>Uim Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimLabelImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLabel()
	 * @generated
	 */
	int UIM_LABEL = 16;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LABEL__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LABEL__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LABEL__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LABEL_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimDateTimePopupImpl <em>Uim Date Time Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimDateTimePopupImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDateTimePopup()
	 * @generated
	 */
	int UIM_DATE_TIME_POPUP = 17;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_TIME_POPUP__MIMUM_WIDTH = UIM_CONTROL__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_TIME_POPUP__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_TIME_POPUP__MINIMUM_HEIGHT = UIM_CONTROL__MINIMUM_HEIGHT;

	/**
	 * The number of structural features of the '<em>Uim Date Time Popup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_TIME_POPUP_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.ControlKind <em>Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.ControlKind
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getControlKind()
	 * @generated
	 */
	int CONTROL_KIND = 18;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimNumberScroller <em>Uim Number Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Number Scroller</em>'.
	 * @see org.opaeum.uim.control.UimNumberScroller
	 * @generated
	 */
	EClass getUimNumberScroller();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimToggleButton <em>Uim Toggle Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Toggle Button</em>'.
	 * @see org.opaeum.uim.control.UimToggleButton
	 * @generated
	 */
	EClass getUimToggleButton();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimPopupSearch <em>Uim Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Popup Search</em>'.
	 * @see org.opaeum.uim.control.UimPopupSearch
	 * @generated
	 */
	EClass getUimPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimDropdown <em>Uim Dropdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Dropdown</em>'.
	 * @see org.opaeum.uim.control.UimDropdown
	 * @generated
	 */
	EClass getUimDropdown();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimCheckBox <em>Uim Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Check Box</em>'.
	 * @see org.opaeum.uim.control.UimCheckBox
	 * @generated
	 */
	EClass getUimCheckBox();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimLookup <em>Uim Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Lookup</em>'.
	 * @see org.opaeum.uim.control.UimLookup
	 * @generated
	 */
	EClass getUimLookup();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.control.UimLookup#getLookupSource <em>Lookup Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lookup Source</em>'.
	 * @see org.opaeum.uim.control.UimLookup#getLookupSource()
	 * @see #getUimLookup()
	 * @generated
	 */
	EReference getUimLookup_LookupSource();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimTextArea <em>Uim Text Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Text Area</em>'.
	 * @see org.opaeum.uim.control.UimTextArea
	 * @generated
	 */
	EClass getUimTextArea();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.control.UimTextArea#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.opaeum.uim.control.UimTextArea#getRows()
	 * @see #getUimTextArea()
	 * @generated
	 */
	EAttribute getUimTextArea_Rows();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimText <em>Uim Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Text</em>'.
	 * @see org.opaeum.uim.control.UimText
	 * @generated
	 */
	EClass getUimText();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimDatePopup <em>Uim Date Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Date Popup</em>'.
	 * @see org.opaeum.uim.control.UimDatePopup
	 * @generated
	 */
	EClass getUimDatePopup();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimListBox <em>Uim List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim List Box</em>'.
	 * @see org.opaeum.uim.control.UimListBox
	 * @generated
	 */
	EClass getUimListBox();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.control.UimListBox#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.opaeum.uim.control.UimListBox#getRows()
	 * @see #getUimListBox()
	 * @generated
	 */
	EAttribute getUimListBox_Rows();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimControl <em>Uim Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Control</em>'.
	 * @see org.opaeum.uim.control.UimControl
	 * @generated
	 */
	EClass getUimControl();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.control.UimControl#getMimumWidth <em>Mimum Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mimum Width</em>'.
	 * @see org.opaeum.uim.control.UimControl#getMimumWidth()
	 * @see #getUimControl()
	 * @generated
	 */
	EAttribute getUimControl_MimumWidth();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.control.UimControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Field</em>'.
	 * @see org.opaeum.uim.control.UimControl#getField()
	 * @see #getUimControl()
	 * @generated
	 */
	EReference getUimControl_Field();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.control.UimControl#getMinimumHeight <em>Minimum Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Height</em>'.
	 * @see org.opaeum.uim.control.UimControl#getMinimumHeight()
	 * @see #getUimControl()
	 * @generated
	 */
	EAttribute getUimControl_MinimumHeight();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimTreeView <em>Uim Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Tree View</em>'.
	 * @see org.opaeum.uim.control.UimTreeView
	 * @generated
	 */
	EClass getUimTreeView();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimLinkControl <em>Uim Link Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Link Control</em>'.
	 * @see org.opaeum.uim.control.UimLinkControl
	 * @generated
	 */
	EClass getUimLinkControl();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.control.UimLinkControl#getEditorToOpen <em>Editor To Open</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Editor To Open</em>'.
	 * @see org.opaeum.uim.control.UimLinkControl#getEditorToOpen()
	 * @see #getUimLinkControl()
	 * @generated
	 */
	EReference getUimLinkControl_EditorToOpen();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimDateScroller <em>Uim Date Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Date Scroller</em>'.
	 * @see org.opaeum.uim.control.UimDateScroller
	 * @generated
	 */
	EClass getUimDateScroller();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimSelectionTable <em>Uim Selection Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Selection Table</em>'.
	 * @see org.opaeum.uim.control.UimSelectionTable
	 * @generated
	 */
	EClass getUimSelectionTable();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimRadioButton <em>Uim Radio Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Radio Button</em>'.
	 * @see org.opaeum.uim.control.UimRadioButton
	 * @generated
	 */
	EClass getUimRadioButton();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimLabel <em>Uim Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Label</em>'.
	 * @see org.opaeum.uim.control.UimLabel
	 * @generated
	 */
	EClass getUimLabel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimDateTimePopup <em>Uim Date Time Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Date Time Popup</em>'.
	 * @see org.opaeum.uim.control.UimDateTimePopup
	 * @generated
	 */
	EClass getUimDateTimePopup();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.control.ControlKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Kind</em>'.
	 * @see org.opaeum.uim.control.ControlKind
	 * @generated
	 */
	EEnum getControlKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ControlFactory getControlFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimNumberScrollerImpl <em>Uim Number Scroller</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimNumberScrollerImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimNumberScroller()
		 * @generated
		 */
		EClass UIM_NUMBER_SCROLLER = eINSTANCE.getUimNumberScroller();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimToggleButtonImpl <em>Uim Toggle Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimToggleButtonImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimToggleButton()
		 * @generated
		 */
		EClass UIM_TOGGLE_BUTTON = eINSTANCE.getUimToggleButton();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimPopupSearchImpl <em>Uim Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimPopupSearchImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimPopupSearch()
		 * @generated
		 */
		EClass UIM_POPUP_SEARCH = eINSTANCE.getUimPopupSearch();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimDropdownImpl <em>Uim Dropdown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimDropdownImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDropdown()
		 * @generated
		 */
		EClass UIM_DROPDOWN = eINSTANCE.getUimDropdown();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimCheckBoxImpl <em>Uim Check Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimCheckBoxImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimCheckBox()
		 * @generated
		 */
		EClass UIM_CHECK_BOX = eINSTANCE.getUimCheckBox();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimLookupImpl <em>Uim Lookup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimLookupImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLookup()
		 * @generated
		 */
		EClass UIM_LOOKUP = eINSTANCE.getUimLookup();

		/**
		 * The meta object literal for the '<em><b>Lookup Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LOOKUP__LOOKUP_SOURCE = eINSTANCE.getUimLookup_LookupSource();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimTextAreaImpl <em>Uim Text Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimTextAreaImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimTextArea()
		 * @generated
		 */
		EClass UIM_TEXT_AREA = eINSTANCE.getUimTextArea();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TEXT_AREA__ROWS = eINSTANCE.getUimTextArea_Rows();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimTextImpl <em>Uim Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimTextImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimText()
		 * @generated
		 */
		EClass UIM_TEXT = eINSTANCE.getUimText();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimDatePopupImpl <em>Uim Date Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimDatePopupImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDatePopup()
		 * @generated
		 */
		EClass UIM_DATE_POPUP = eINSTANCE.getUimDatePopup();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimListBoxImpl <em>Uim List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimListBoxImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimListBox()
		 * @generated
		 */
		EClass UIM_LIST_BOX = eINSTANCE.getUimListBox();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_LIST_BOX__ROWS = eINSTANCE.getUimListBox_Rows();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimControlImpl <em>Uim Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimControlImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimControl()
		 * @generated
		 */
		EClass UIM_CONTROL = eINSTANCE.getUimControl();

		/**
		 * The meta object literal for the '<em><b>Mimum Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_CONTROL__MIMUM_WIDTH = eINSTANCE.getUimControl_MimumWidth();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTROL__FIELD = eINSTANCE.getUimControl_Field();

		/**
		 * The meta object literal for the '<em><b>Minimum Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_CONTROL__MINIMUM_HEIGHT = eINSTANCE.getUimControl_MinimumHeight();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimTreeViewImpl <em>Uim Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimTreeViewImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimTreeView()
		 * @generated
		 */
		EClass UIM_TREE_VIEW = eINSTANCE.getUimTreeView();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimLinkControlImpl <em>Uim Link Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimLinkControlImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLinkControl()
		 * @generated
		 */
		EClass UIM_LINK_CONTROL = eINSTANCE.getUimLinkControl();

		/**
		 * The meta object literal for the '<em><b>Editor To Open</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LINK_CONTROL__EDITOR_TO_OPEN = eINSTANCE.getUimLinkControl_EditorToOpen();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimDateScrollerImpl <em>Uim Date Scroller</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimDateScrollerImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDateScroller()
		 * @generated
		 */
		EClass UIM_DATE_SCROLLER = eINSTANCE.getUimDateScroller();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimSelectionTableImpl <em>Uim Selection Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimSelectionTableImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSelectionTable()
		 * @generated
		 */
		EClass UIM_SELECTION_TABLE = eINSTANCE.getUimSelectionTable();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimRadioButtonImpl <em>Uim Radio Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimRadioButtonImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimRadioButton()
		 * @generated
		 */
		EClass UIM_RADIO_BUTTON = eINSTANCE.getUimRadioButton();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimLabelImpl <em>Uim Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimLabelImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimLabel()
		 * @generated
		 */
		EClass UIM_LABEL = eINSTANCE.getUimLabel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimDateTimePopupImpl <em>Uim Date Time Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimDateTimePopupImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDateTimePopup()
		 * @generated
		 */
		EClass UIM_DATE_TIME_POPUP = eINSTANCE.getUimDateTimePopup();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.ControlKind <em>Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.ControlKind
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getControlKind()
		 * @generated
		 */
		EEnum CONTROL_KIND = eINSTANCE.getControlKind();

	}

} //ControlPackage
