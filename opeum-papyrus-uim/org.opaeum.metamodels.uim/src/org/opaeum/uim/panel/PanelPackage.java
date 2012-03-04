/**
 */
package org.opaeum.uim.panel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
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
 * @see org.opaeum.uim.panel.PanelFactory
 * @model kind="package"
 * @generated
 */
public interface PanelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "panel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/panel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "panel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PanelPackage eINSTANCE = org.opaeum.uim.panel.impl.PanelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.AbstractPanelImpl <em>Abstract Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.AbstractPanelImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getAbstractPanel()
	 * @generated
	 */
	int ABSTRACT_PANEL = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL__NAME = UimPackage.UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL__VISIBILITY = UimPackage.UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL__EDITABILITY = UimPackage.UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL__CHILDREN = UimPackage.UIM_CONTAINER__CHILDREN;

	/**
	 * The number of structural features of the '<em>Abstract Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL_FEATURE_COUNT = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.CollapsiblePanelImpl <em>Collapsible Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.CollapsiblePanelImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getCollapsiblePanel()
	 * @generated
	 */
	int COLLAPSIBLE_PANEL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__NAME = ABSTRACT_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__VISIBILITY = ABSTRACT_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__EDITABILITY = ABSTRACT_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__CHILDREN = ABSTRACT_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__IS_COLLAPSIBLE = ABSTRACT_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collapsible Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL_FEATURE_COUNT = ABSTRACT_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.GridPanelImpl <em>Grid Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.GridPanelImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getGridPanel()
	 * @generated
	 */
	int GRID_PANEL = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__NAME = COLLAPSIBLE_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__VISIBILITY = COLLAPSIBLE_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__EDITABILITY = COLLAPSIBLE_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__CHILDREN = COLLAPSIBLE_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__IS_COLLAPSIBLE = COLLAPSIBLE_PANEL__IS_COLLAPSIBLE;

	/**
	 * The number of structural features of the '<em>Grid Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL_FEATURE_COUNT = COLLAPSIBLE_PANEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.VerticalPanelImpl <em>Vertical Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.VerticalPanelImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getVerticalPanel()
	 * @generated
	 */
	int VERTICAL_PANEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__NAME = COLLAPSIBLE_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__VISIBILITY = COLLAPSIBLE_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__EDITABILITY = COLLAPSIBLE_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__CHILDREN = COLLAPSIBLE_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__IS_COLLAPSIBLE = COLLAPSIBLE_PANEL__IS_COLLAPSIBLE;

	/**
	 * The number of structural features of the '<em>Vertical Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL_FEATURE_COUNT = COLLAPSIBLE_PANEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.HorizontalPanelImpl <em>Horizontal Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.HorizontalPanelImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getHorizontalPanel()
	 * @generated
	 */
	int HORIZONTAL_PANEL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__NAME = COLLAPSIBLE_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__VISIBILITY = COLLAPSIBLE_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__EDITABILITY = COLLAPSIBLE_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__CHILDREN = COLLAPSIBLE_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__IS_COLLAPSIBLE = COLLAPSIBLE_PANEL__IS_COLLAPSIBLE;

	/**
	 * The number of structural features of the '<em>Horizontal Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL_FEATURE_COUNT = COLLAPSIBLE_PANEL_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.GridPanel <em>Grid Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Panel</em>'.
	 * @see org.opaeum.uim.panel.GridPanel
	 * @generated
	 */
	EClass getGridPanel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.VerticalPanel <em>Vertical Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertical Panel</em>'.
	 * @see org.opaeum.uim.panel.VerticalPanel
	 * @generated
	 */
	EClass getVerticalPanel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.HorizontalPanel <em>Horizontal Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Horizontal Panel</em>'.
	 * @see org.opaeum.uim.panel.HorizontalPanel
	 * @generated
	 */
	EClass getHorizontalPanel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.AbstractPanel <em>Abstract Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Panel</em>'.
	 * @see org.opaeum.uim.panel.AbstractPanel
	 * @generated
	 */
	EClass getAbstractPanel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.CollapsiblePanel <em>Collapsible Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collapsible Panel</em>'.
	 * @see org.opaeum.uim.panel.CollapsiblePanel
	 * @generated
	 */
	EClass getCollapsiblePanel();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.CollapsiblePanel#getIsCollapsible <em>Is Collapsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Collapsible</em>'.
	 * @see org.opaeum.uim.panel.CollapsiblePanel#getIsCollapsible()
	 * @see #getCollapsiblePanel()
	 * @generated
	 */
	EAttribute getCollapsiblePanel_IsCollapsible();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PanelFactory getPanelFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.GridPanelImpl <em>Grid Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.GridPanelImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getGridPanel()
		 * @generated
		 */
		EClass GRID_PANEL = eINSTANCE.getGridPanel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.VerticalPanelImpl <em>Vertical Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.VerticalPanelImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getVerticalPanel()
		 * @generated
		 */
		EClass VERTICAL_PANEL = eINSTANCE.getVerticalPanel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.HorizontalPanelImpl <em>Horizontal Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.HorizontalPanelImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getHorizontalPanel()
		 * @generated
		 */
		EClass HORIZONTAL_PANEL = eINSTANCE.getHorizontalPanel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.AbstractPanelImpl <em>Abstract Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.AbstractPanelImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getAbstractPanel()
		 * @generated
		 */
		EClass ABSTRACT_PANEL = eINSTANCE.getAbstractPanel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.CollapsiblePanelImpl <em>Collapsible Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.CollapsiblePanelImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getCollapsiblePanel()
		 * @generated
		 */
		EClass COLLAPSIBLE_PANEL = eINSTANCE.getCollapsiblePanel();

		/**
		 * The meta object literal for the '<em><b>Is Collapsible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLAPSIBLE_PANEL__IS_COLLAPSIBLE = eINSTANCE.getCollapsiblePanel_IsCollapsible();

	}

} //PanelPackage
