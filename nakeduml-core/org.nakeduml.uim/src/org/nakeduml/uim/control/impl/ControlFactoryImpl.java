/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.control.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nakeduml.uim.control.ControlFactory;
import org.nakeduml.uim.control.ControlKind;
import org.nakeduml.uim.control.ControlPackage;
import org.nakeduml.uim.control.UimCheckBox;
import org.nakeduml.uim.control.UimControl;
import org.nakeduml.uim.control.UimDatePopup;
import org.nakeduml.uim.control.UimDropdown;
import org.nakeduml.uim.control.UimLookup;
import org.nakeduml.uim.control.UimMultiSelectListBox;
import org.nakeduml.uim.control.UimMultiSelectPopupSearch;
import org.nakeduml.uim.control.UimMultiSelectTreeView;
import org.nakeduml.uim.control.UimNumberScroller;
import org.nakeduml.uim.control.UimSingleSelectListBox;
import org.nakeduml.uim.control.UimSingleSelectPopupSearch;
import org.nakeduml.uim.control.UimSingleSelectTreeView;
import org.nakeduml.uim.control.UimText;
import org.nakeduml.uim.control.UimTextArea;
import org.nakeduml.uim.control.UimToggleButton;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlFactoryImpl extends EFactoryImpl implements ControlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlFactory init() {
		try {
			ControlFactory theControlFactory = (ControlFactory)EPackage.Registry.INSTANCE.getEFactory("http://nakeduml.org/uimetamodel/control/1.0"); 
			if (theControlFactory != null) {
				return theControlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ControlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlFactoryImpl() {
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
			case ControlPackage.UIM_NUMBER_SCROLLER: return createUimNumberScroller();
			case ControlPackage.UIM_TOGGLE_BUTTON: return createUimToggleButton();
			case ControlPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: return createUimSingleSelectPopupSearch();
			case ControlPackage.UIM_MULTI_SELECT_POPUP_SEARCH: return createUimMultiSelectPopupSearch();
			case ControlPackage.UIM_MULTI_SELECT_TREE_VIEW: return createUimMultiSelectTreeView();
			case ControlPackage.UIM_MULTI_SELECT_LIST_BOX: return createUimMultiSelectListBox();
			case ControlPackage.UIM_DROPDOWN: return createUimDropdown();
			case ControlPackage.UIM_CHECK_BOX: return createUimCheckBox();
			case ControlPackage.UIM_LOOKUP: return createUimLookup();
			case ControlPackage.UIM_TEXT_AREA: return createUimTextArea();
			case ControlPackage.UIM_TEXT: return createUimText();
			case ControlPackage.UIM_DATE_POPUP: return createUimDatePopup();
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX: return createUimSingleSelectListBox();
			case ControlPackage.UIM_CONTROL: return createUimControl();
			case ControlPackage.UIM_SINGLE_SELECT_TREE_VIEW: return createUimSingleSelectTreeView();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ControlPackage.CONTROL_KIND:
				return createControlKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ControlPackage.CONTROL_KIND:
				return convertControlKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimNumberScroller createUimNumberScroller() {
		UimNumberScrollerImpl uimNumberScroller = new UimNumberScrollerImpl();
		return uimNumberScroller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimToggleButton createUimToggleButton() {
		UimToggleButtonImpl uimToggleButton = new UimToggleButtonImpl();
		return uimToggleButton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectPopupSearch createUimSingleSelectPopupSearch() {
		UimSingleSelectPopupSearchImpl uimSingleSelectPopupSearch = new UimSingleSelectPopupSearchImpl();
		return uimSingleSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectPopupSearch createUimMultiSelectPopupSearch() {
		UimMultiSelectPopupSearchImpl uimMultiSelectPopupSearch = new UimMultiSelectPopupSearchImpl();
		return uimMultiSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectTreeView createUimMultiSelectTreeView() {
		UimMultiSelectTreeViewImpl uimMultiSelectTreeView = new UimMultiSelectTreeViewImpl();
		return uimMultiSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectListBox createUimMultiSelectListBox() {
		UimMultiSelectListBoxImpl uimMultiSelectListBox = new UimMultiSelectListBoxImpl();
		return uimMultiSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDropdown createUimDropdown() {
		UimDropdownImpl uimDropdown = new UimDropdownImpl();
		return uimDropdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimCheckBox createUimCheckBox() {
		UimCheckBoxImpl uimCheckBox = new UimCheckBoxImpl();
		return uimCheckBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLookup createUimLookup() {
		UimLookupImpl uimLookup = new UimLookupImpl();
		return uimLookup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTextArea createUimTextArea() {
		UimTextAreaImpl uimTextArea = new UimTextAreaImpl();
		return uimTextArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimText createUimText() {
		UimTextImpl uimText = new UimTextImpl();
		return uimText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDatePopup createUimDatePopup() {
		UimDatePopupImpl uimDatePopup = new UimDatePopupImpl();
		return uimDatePopup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectListBox createUimSingleSelectListBox() {
		UimSingleSelectListBoxImpl uimSingleSelectListBox = new UimSingleSelectListBoxImpl();
		return uimSingleSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimControl createUimControl() {
		UimControlImpl uimControl = new UimControlImpl();
		return uimControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectTreeView createUimSingleSelectTreeView() {
		UimSingleSelectTreeViewImpl uimSingleSelectTreeView = new UimSingleSelectTreeViewImpl();
		return uimSingleSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlKind createControlKindFromString(EDataType eDataType, String initialValue) {
		ControlKind result = ControlKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertControlKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlPackage getControlPackage() {
		return (ControlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ControlPackage getPackage() {
		return ControlPackage.eINSTANCE;
	}

} //ControlFactoryImpl
