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
	int UIM_CONTROL = 13;

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
	int UIM_LOOKUP = 8;

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
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimSingleSelectPopupSearchImpl <em>Uim Single Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimSingleSelectPopupSearchImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectPopupSearch()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH = 2;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Single Select Popup Search</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimMultiSelectPopupSearchImpl <em>Uim Multi Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimMultiSelectPopupSearchImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectPopupSearch()
	 * @generated
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH = 3;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Multi Select Popup Search</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimMultiSelectTreeViewImpl <em>Uim Multi Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimMultiSelectTreeViewImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectTreeView()
	 * @generated
	 */
	int UIM_MULTI_SELECT_TREE_VIEW = 4;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Multi Select Tree View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimMultiSelectListBoxImpl <em>Uim Multi Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimMultiSelectListBoxImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectListBox()
	 * @generated
	 */
	int UIM_MULTI_SELECT_LIST_BOX = 5;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Multi Select List Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimDropdownImpl <em>Uim Dropdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimDropdownImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimDropdown()
	 * @generated
	 */
	int UIM_DROPDOWN = 6;

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
	int UIM_CHECK_BOX = 7;

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
	int UIM_TEXT_AREA = 9;

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
	int UIM_TEXT = 10;

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
	int UIM_DATE_POPUP = 11;

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
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimSingleSelectListBoxImpl <em>Uim Single Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimSingleSelectListBoxImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectListBox()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_LIST_BOX = 12;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__ROWS = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Single Select List Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.impl.UimSingleSelectTreeViewImpl <em>Uim Single Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.impl.UimSingleSelectTreeViewImpl
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectTreeView()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW = 14;

	/**
	 * The feature id for the '<em><b>Mimum Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__MIMUM_WIDTH = UIM_LOOKUP__MIMUM_WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Minimum Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__MINIMUM_HEIGHT = UIM_LOOKUP__MINIMUM_HEIGHT;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Uim Single Select Tree View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.control.ControlKind <em>Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.control.ControlKind
	 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getControlKind()
	 * @generated
	 */
	int CONTROL_KIND = 15;


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
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimSingleSelectPopupSearch <em>Uim Single Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Single Select Popup Search</em>'.
	 * @see org.opaeum.uim.control.UimSingleSelectPopupSearch
	 * @generated
	 */
	EClass getUimSingleSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimMultiSelectPopupSearch <em>Uim Multi Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Multi Select Popup Search</em>'.
	 * @see org.opaeum.uim.control.UimMultiSelectPopupSearch
	 * @generated
	 */
	EClass getUimMultiSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimMultiSelectTreeView <em>Uim Multi Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Multi Select Tree View</em>'.
	 * @see org.opaeum.uim.control.UimMultiSelectTreeView
	 * @generated
	 */
	EClass getUimMultiSelectTreeView();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimMultiSelectListBox <em>Uim Multi Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Multi Select List Box</em>'.
	 * @see org.opaeum.uim.control.UimMultiSelectListBox
	 * @generated
	 */
	EClass getUimMultiSelectListBox();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimSingleSelectListBox <em>Uim Single Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Single Select List Box</em>'.
	 * @see org.opaeum.uim.control.UimSingleSelectListBox
	 * @generated
	 */
	EClass getUimSingleSelectListBox();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.control.UimSingleSelectListBox#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.opaeum.uim.control.UimSingleSelectListBox#getRows()
	 * @see #getUimSingleSelectListBox()
	 * @generated
	 */
	EAttribute getUimSingleSelectListBox_Rows();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.control.UimSingleSelectTreeView <em>Uim Single Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Single Select Tree View</em>'.
	 * @see org.opaeum.uim.control.UimSingleSelectTreeView
	 * @generated
	 */
	EClass getUimSingleSelectTreeView();

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
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimSingleSelectPopupSearchImpl <em>Uim Single Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimSingleSelectPopupSearchImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_POPUP_SEARCH = eINSTANCE.getUimSingleSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimMultiSelectPopupSearchImpl <em>Uim Multi Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimMultiSelectPopupSearchImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_POPUP_SEARCH = eINSTANCE.getUimMultiSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimMultiSelectTreeViewImpl <em>Uim Multi Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimMultiSelectTreeViewImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectTreeView()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_TREE_VIEW = eINSTANCE.getUimMultiSelectTreeView();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimMultiSelectListBoxImpl <em>Uim Multi Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimMultiSelectListBoxImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimMultiSelectListBox()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_LIST_BOX = eINSTANCE.getUimMultiSelectListBox();

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
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimSingleSelectListBoxImpl <em>Uim Single Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimSingleSelectListBoxImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectListBox()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_LIST_BOX = eINSTANCE.getUimSingleSelectListBox();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_SINGLE_SELECT_LIST_BOX__ROWS = eINSTANCE.getUimSingleSelectListBox_Rows();

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
		 * The meta object literal for the '{@link org.opaeum.uim.control.impl.UimSingleSelectTreeViewImpl <em>Uim Single Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.control.impl.UimSingleSelectTreeViewImpl
		 * @see org.opaeum.uim.control.impl.ControlPackageImpl#getUimSingleSelectTreeView()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_TREE_VIEW = eINSTANCE.getUimSingleSelectTreeView();

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
