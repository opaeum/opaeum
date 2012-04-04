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
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PANEL__UNDER_USER_CONTROL = UimPackage.UIM_CONTAINER__UNDER_USER_CONTROL;

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
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__UNDER_USER_CONTROL = ABSTRACT_PANEL__UNDER_USER_CONTROL;

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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__PREFERRED_WIDTH = ABSTRACT_PANEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__PREFERRED_HEIGHT = ABSTRACT_PANEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__FILL_HORIZONTALLY = ABSTRACT_PANEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__FILL_VERTICALLY = ABSTRACT_PANEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL__IS_COLLAPSIBLE = ABSTRACT_PANEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Collapsible Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSIBLE_PANEL_FEATURE_COUNT = ABSTRACT_PANEL_FEATURE_COUNT + 5;

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
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__UNDER_USER_CONTROL = COLLAPSIBLE_PANEL__UNDER_USER_CONTROL;

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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__PREFERRED_WIDTH = COLLAPSIBLE_PANEL__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__PREFERRED_HEIGHT = COLLAPSIBLE_PANEL__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__FILL_HORIZONTALLY = COLLAPSIBLE_PANEL__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__FILL_VERTICALLY = COLLAPSIBLE_PANEL__FILL_VERTICALLY;

	/**
	 * The feature id for the '<em><b>Is Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__IS_COLLAPSIBLE = COLLAPSIBLE_PANEL__IS_COLLAPSIBLE;

	/**
	 * The feature id for the '<em><b>Number Of Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL__NUMBER_OF_COLUMNS = COLLAPSIBLE_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Grid Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_PANEL_FEATURE_COUNT = COLLAPSIBLE_PANEL_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__UNDER_USER_CONTROL = COLLAPSIBLE_PANEL__UNDER_USER_CONTROL;

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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__PREFERRED_WIDTH = COLLAPSIBLE_PANEL__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__PREFERRED_HEIGHT = COLLAPSIBLE_PANEL__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__FILL_HORIZONTALLY = COLLAPSIBLE_PANEL__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_PANEL__FILL_VERTICALLY = COLLAPSIBLE_PANEL__FILL_VERTICALLY;

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
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__UNDER_USER_CONTROL = COLLAPSIBLE_PANEL__UNDER_USER_CONTROL;

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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__PREFERRED_WIDTH = COLLAPSIBLE_PANEL__PREFERRED_WIDTH;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__PREFERRED_HEIGHT = COLLAPSIBLE_PANEL__PREFERRED_HEIGHT;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__FILL_HORIZONTALLY = COLLAPSIBLE_PANEL__FILL_HORIZONTALLY;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HORIZONTAL_PANEL__FILL_VERTICALLY = COLLAPSIBLE_PANEL__FILL_VERTICALLY;

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
	 * The meta object id for the '{@link org.opaeum.uim.panel.impl.OutlayableImpl <em>Outlayable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.panel.impl.OutlayableImpl
	 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getOutlayable()
	 * @generated
	 */
	int OUTLAYABLE = 5;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE__PREFERRED_WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE__PREFERRED_HEIGHT = 1;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE__FILL_HORIZONTALLY = 2;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE__FILL_VERTICALLY = 3;

	/**
	 * The number of structural features of the '<em>Outlayable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_FEATURE_COUNT = 4;


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
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.GridPanel#getNumberOfColumns <em>Number Of Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Columns</em>'.
	 * @see org.opaeum.uim.panel.GridPanel#getNumberOfColumns()
	 * @see #getGridPanel()
	 * @generated
	 */
	EAttribute getGridPanel_NumberOfColumns();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.panel.Outlayable <em>Outlayable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outlayable</em>'.
	 * @see org.opaeum.uim.panel.Outlayable
	 * @generated
	 */
	EClass getOutlayable();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.Outlayable#getPreferredWidth <em>Preferred Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Width</em>'.
	 * @see org.opaeum.uim.panel.Outlayable#getPreferredWidth()
	 * @see #getOutlayable()
	 * @generated
	 */
	EAttribute getOutlayable_PreferredWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.Outlayable#getPreferredHeight <em>Preferred Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Height</em>'.
	 * @see org.opaeum.uim.panel.Outlayable#getPreferredHeight()
	 * @see #getOutlayable()
	 * @generated
	 */
	EAttribute getOutlayable_PreferredHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.Outlayable#getFillHorizontally <em>Fill Horizontally</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Horizontally</em>'.
	 * @see org.opaeum.uim.panel.Outlayable#getFillHorizontally()
	 * @see #getOutlayable()
	 * @generated
	 */
	EAttribute getOutlayable_FillHorizontally();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.panel.Outlayable#getFillVertically <em>Fill Vertically</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Vertically</em>'.
	 * @see org.opaeum.uim.panel.Outlayable#getFillVertically()
	 * @see #getOutlayable()
	 * @generated
	 */
	EAttribute getOutlayable_FillVertically();

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
		 * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_PANEL__NUMBER_OF_COLUMNS = eINSTANCE.getGridPanel_NumberOfColumns();

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

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.panel.impl.OutlayableImpl <em>Outlayable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.panel.impl.OutlayableImpl
		 * @see org.opaeum.uim.panel.impl.PanelPackageImpl#getOutlayable()
		 * @generated
		 */
		EClass OUTLAYABLE = eINSTANCE.getOutlayable();

		/**
		 * The meta object literal for the '<em><b>Preferred Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLAYABLE__PREFERRED_WIDTH = eINSTANCE.getOutlayable_PreferredWidth();

		/**
		 * The meta object literal for the '<em><b>Preferred Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLAYABLE__PREFERRED_HEIGHT = eINSTANCE.getOutlayable_PreferredHeight();

		/**
		 * The meta object literal for the '<em><b>Fill Horizontally</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLAYABLE__FILL_HORIZONTALLY = eINSTANCE.getOutlayable_FillHorizontally();

		/**
		 * The meta object literal for the '<em><b>Fill Vertically</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTLAYABLE__FILL_VERTICALLY = eINSTANCE.getOutlayable_FillVertically();

	}

} //PanelPackage
