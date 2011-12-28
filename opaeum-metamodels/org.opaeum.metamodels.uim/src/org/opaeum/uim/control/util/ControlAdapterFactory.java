/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.control.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.control.*;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimCheckBox;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimDatePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.control.UimMultiSelectListBox;
import org.opaeum.uim.control.UimMultiSelectPopupSearch;
import org.opaeum.uim.control.UimMultiSelectTreeView;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimSingleSelectListBox;
import org.opaeum.uim.control.UimSingleSelectPopupSearch;
import org.opaeum.uim.control.UimSingleSelectTreeView;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.control.ControlPackage
 * @generated
 */
public class ControlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ControlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ControlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlSwitch<Adapter> modelSwitch =
		new ControlSwitch<Adapter>() {
			@Override
			public Adapter caseUimNumberScroller(UimNumberScroller object) {
				return createUimNumberScrollerAdapter();
			}
			@Override
			public Adapter caseUimToggleButton(UimToggleButton object) {
				return createUimToggleButtonAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectPopupSearch(UimSingleSelectPopupSearch object) {
				return createUimSingleSelectPopupSearchAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectPopupSearch(UimMultiSelectPopupSearch object) {
				return createUimMultiSelectPopupSearchAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectTreeView(UimMultiSelectTreeView object) {
				return createUimMultiSelectTreeViewAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectListBox(UimMultiSelectListBox object) {
				return createUimMultiSelectListBoxAdapter();
			}
			@Override
			public Adapter caseUimDropdown(UimDropdown object) {
				return createUimDropdownAdapter();
			}
			@Override
			public Adapter caseUimCheckBox(UimCheckBox object) {
				return createUimCheckBoxAdapter();
			}
			@Override
			public Adapter caseUimLookup(UimLookup object) {
				return createUimLookupAdapter();
			}
			@Override
			public Adapter caseUimTextArea(UimTextArea object) {
				return createUimTextAreaAdapter();
			}
			@Override
			public Adapter caseUimText(UimText object) {
				return createUimTextAdapter();
			}
			@Override
			public Adapter caseUimDatePopup(UimDatePopup object) {
				return createUimDatePopupAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectListBox(UimSingleSelectListBox object) {
				return createUimSingleSelectListBoxAdapter();
			}
			@Override
			public Adapter caseUimControl(UimControl object) {
				return createUimControlAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectTreeView(UimSingleSelectTreeView object) {
				return createUimSingleSelectTreeViewAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimNumberScroller <em>Uim Number Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimNumberScroller
	 * @generated
	 */
	public Adapter createUimNumberScrollerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimToggleButton <em>Uim Toggle Button</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimToggleButton
	 * @generated
	 */
	public Adapter createUimToggleButtonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimSingleSelectPopupSearch <em>Uim Single Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimSingleSelectPopupSearch
	 * @generated
	 */
	public Adapter createUimSingleSelectPopupSearchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimMultiSelectPopupSearch <em>Uim Multi Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimMultiSelectPopupSearch
	 * @generated
	 */
	public Adapter createUimMultiSelectPopupSearchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimMultiSelectTreeView <em>Uim Multi Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimMultiSelectTreeView
	 * @generated
	 */
	public Adapter createUimMultiSelectTreeViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimMultiSelectListBox <em>Uim Multi Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimMultiSelectListBox
	 * @generated
	 */
	public Adapter createUimMultiSelectListBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimDropdown <em>Uim Dropdown</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimDropdown
	 * @generated
	 */
	public Adapter createUimDropdownAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimCheckBox <em>Uim Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimCheckBox
	 * @generated
	 */
	public Adapter createUimCheckBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimLookup <em>Uim Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimLookup
	 * @generated
	 */
	public Adapter createUimLookupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimTextArea <em>Uim Text Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimTextArea
	 * @generated
	 */
	public Adapter createUimTextAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimText <em>Uim Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimText
	 * @generated
	 */
	public Adapter createUimTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimDatePopup <em>Uim Date Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimDatePopup
	 * @generated
	 */
	public Adapter createUimDatePopupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimSingleSelectListBox <em>Uim Single Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimSingleSelectListBox
	 * @generated
	 */
	public Adapter createUimSingleSelectListBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimControl <em>Uim Control</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimControl
	 * @generated
	 */
	public Adapter createUimControlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.control.UimSingleSelectTreeView <em>Uim Single Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.control.UimSingleSelectTreeView
	 * @generated
	 */
	public Adapter createUimSingleSelectTreeViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ControlAdapterFactory
