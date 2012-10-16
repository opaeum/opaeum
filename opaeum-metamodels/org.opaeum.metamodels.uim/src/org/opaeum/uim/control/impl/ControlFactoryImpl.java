/**
 */
package org.opaeum.uim.control.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.control.*;
import org.opaeum.uim.control.ControlFactory;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimCheckBox;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimDatePopup;
import org.opaeum.uim.control.UimDateScroller;
import org.opaeum.uim.control.UimDateTimePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimLabel;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.control.UimListBox;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimPopupSearch;
import org.opaeum.uim.control.UimRadioButton;
import org.opaeum.uim.control.UimSelectionTable;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;
import org.opaeum.uim.control.UimTreeView;

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
			ControlFactory theControlFactory = (ControlFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/control/1.0"); 
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
			case ControlPackage.UIM_POPUP_SEARCH: return createUimPopupSearch();
			case ControlPackage.UIM_DROPDOWN: return createUimDropdown();
			case ControlPackage.UIM_CHECK_BOX: return createUimCheckBox();
			case ControlPackage.UIM_LOOKUP: return createUimLookup();
			case ControlPackage.UIM_TEXT_AREA: return createUimTextArea();
			case ControlPackage.UIM_TEXT: return createUimText();
			case ControlPackage.UIM_DATE_POPUP: return createUimDatePopup();
			case ControlPackage.UIM_LIST_BOX: return createUimListBox();
			case ControlPackage.UIM_CONTROL: return createUimControl();
			case ControlPackage.UIM_TREE_VIEW: return createUimTreeView();
			case ControlPackage.UIM_LINK_CONTROL: return createUimLinkControl();
			case ControlPackage.UIM_DATE_SCROLLER: return createUimDateScroller();
			case ControlPackage.UIM_SELECTION_TABLE: return createUimSelectionTable();
			case ControlPackage.UIM_RADIO_BUTTON: return createUimRadioButton();
			case ControlPackage.UIM_LABEL: return createUimLabel();
			case ControlPackage.UIM_DATE_TIME_POPUP: return createUimDateTimePopup();
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
	public UimPopupSearch createUimPopupSearch() {
		UimPopupSearchImpl uimPopupSearch = new UimPopupSearchImpl();
		return uimPopupSearch;
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
	public UimListBox createUimListBox() {
		UimListBoxImpl uimListBox = new UimListBoxImpl();
		return uimListBox;
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
	public UimTreeView createUimTreeView() {
		UimTreeViewImpl uimTreeView = new UimTreeViewImpl();
		return uimTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLinkControl createUimLinkControl() {
		UimLinkControlImpl uimLinkControl = new UimLinkControlImpl();
		return uimLinkControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDateScroller createUimDateScroller() {
		UimDateScrollerImpl uimDateScroller = new UimDateScrollerImpl();
		return uimDateScroller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSelectionTable createUimSelectionTable() {
		UimSelectionTableImpl uimSelectionTable = new UimSelectionTableImpl();
		return uimSelectionTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimRadioButton createUimRadioButton() {
		UimRadioButtonImpl uimRadioButton = new UimRadioButtonImpl();
		return uimRadioButton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLabel createUimLabel() {
		UimLabelImpl uimLabel = new UimLabelImpl();
		return uimLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDateTimePopup createUimDateTimePopup() {
		UimDateTimePopupImpl uimDateTimePopup = new UimDateTimePopupImpl();
		return uimDateTimePopup;
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
