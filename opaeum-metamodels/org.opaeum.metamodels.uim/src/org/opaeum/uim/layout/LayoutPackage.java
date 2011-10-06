/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.opaeum.uim.layout.LayoutFactory
 * @model kind="package"
 * @generated
 */
public interface LayoutPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "layout";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/layout/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "lay";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LayoutPackage eINSTANCE = org.opaeum.uim.layout.impl.LayoutPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimLayoutImpl <em>Uim Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimLayout()
	 * @generated
	 */
	int UIM_LAYOUT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__NAME = UimPackage.UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__VISIBILITY = UimPackage.UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__EDITABILITY = UimPackage.UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__PARENT = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__CHILDREN = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uim Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT_FEATURE_COUNT = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimColumnLayoutImpl <em>Uim Column Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimColumnLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimColumnLayout()
	 * @generated
	 */
	int UIM_COLUMN_LAYOUT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Uim Column Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COLUMN_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimFullLayoutImpl <em>Uim Full Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimFullLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimFullLayout()
	 * @generated
	 */
	int UIM_FULL_LAYOUT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Uim Full Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimXYLayoutImpl <em>Uim XY Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimXYLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimXYLayout()
	 * @generated
	 */
	int UIM_XY_LAYOUT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Uim XY Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimBorderLayoutImpl <em>Uim Border Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimBorderLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimBorderLayout()
	 * @generated
	 */
	int UIM_BORDER_LAYOUT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Horizontal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__HORIZONTAL = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Border Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimToolbarLayoutImpl <em>Uim Toolbar Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimToolbarLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimToolbarLayout()
	 * @generated
	 */
	int UIM_TOOLBAR_LAYOUT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Uim Toolbar Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.OutlayableComponentImpl <em>Outlayable Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.OutlayableComponentImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getOutlayableComponent()
	 * @generated
	 */
	int OUTLAYABLE_COMPONENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__NAME = UimPackage.UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__VISIBILITY = UimPackage.UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__PARENT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Outlayable Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT_FEATURE_COUNT = UimPackage.UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.LayoutContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.LayoutContainerImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getLayoutContainer()
	 * @generated
	 */
	int LAYOUT_CONTAINER = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__NAME = UimPackage.UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__VISIBILITY = UimPackage.UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__EDITABILITY = UimPackage.UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__LAYOUT = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER_FEATURE_COUNT = UimPackage.UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.layout.impl.UimGridLayoutImpl <em>Uim Grid Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.layout.impl.UimGridLayoutImpl
	 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimGridLayout()
	 * @generated
	 */
	int UIM_GRID_LAYOUT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Number Of Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Grid Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimColumnLayout <em>Uim Column Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Column Layout</em>'.
	 * @see org.opaeum.uim.layout.UimColumnLayout
	 * @generated
	 */
	EClass getUimColumnLayout();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimFullLayout <em>Uim Full Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Full Layout</em>'.
	 * @see org.opaeum.uim.layout.UimFullLayout
	 * @generated
	 */
	EClass getUimFullLayout();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimXYLayout <em>Uim XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim XY Layout</em>'.
	 * @see org.opaeum.uim.layout.UimXYLayout
	 * @generated
	 */
	EClass getUimXYLayout();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimBorderLayout <em>Uim Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Border Layout</em>'.
	 * @see org.opaeum.uim.layout.UimBorderLayout
	 * @generated
	 */
	EClass getUimBorderLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.layout.UimBorderLayout#isHorizontal <em>Horizontal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal</em>'.
	 * @see org.opaeum.uim.layout.UimBorderLayout#isHorizontal()
	 * @see #getUimBorderLayout()
	 * @generated
	 */
	EAttribute getUimBorderLayout_Horizontal();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimToolbarLayout <em>Uim Toolbar Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Toolbar Layout</em>'.
	 * @see org.opaeum.uim.layout.UimToolbarLayout
	 * @generated
	 */
	EClass getUimToolbarLayout();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outlayable Component</em>'.
	 * @see org.opaeum.uim.layout.OutlayableComponent
	 * @generated
	 */
	EClass getOutlayableComponent();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.layout.OutlayableComponent#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.opaeum.uim.layout.OutlayableComponent#getParent()
	 * @see #getOutlayableComponent()
	 * @generated
	 */
	EReference getOutlayableComponent_Parent();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimLayout <em>Uim Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Layout</em>'.
	 * @see org.opaeum.uim.layout.UimLayout
	 * @generated
	 */
	EClass getUimLayout();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.layout.UimLayout#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.opaeum.uim.layout.UimLayout#getParent()
	 * @see #getUimLayout()
	 * @generated
	 */
	EReference getUimLayout_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.layout.UimLayout#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.opaeum.uim.layout.UimLayout#getChildren()
	 * @see #getUimLayout()
	 * @generated
	 */
	EReference getUimLayout_Children();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.LayoutContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.opaeum.uim.layout.LayoutContainer
	 * @generated
	 */
	EClass getLayoutContainer();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.layout.LayoutContainer#getLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Layout</em>'.
	 * @see org.opaeum.uim.layout.LayoutContainer#getLayout()
	 * @see #getLayoutContainer()
	 * @generated
	 */
	EReference getLayoutContainer_Layout();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.layout.UimGridLayout <em>Uim Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Grid Layout</em>'.
	 * @see org.opaeum.uim.layout.UimGridLayout
	 * @generated
	 */
	EClass getUimGridLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.layout.UimGridLayout#getNumberOfColumns <em>Number Of Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Columns</em>'.
	 * @see org.opaeum.uim.layout.UimGridLayout#getNumberOfColumns()
	 * @see #getUimGridLayout()
	 * @generated
	 */
	EAttribute getUimGridLayout_NumberOfColumns();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LayoutFactory getLayoutFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimColumnLayoutImpl <em>Uim Column Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimColumnLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimColumnLayout()
		 * @generated
		 */
		EClass UIM_COLUMN_LAYOUT = eINSTANCE.getUimColumnLayout();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimFullLayoutImpl <em>Uim Full Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimFullLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimFullLayout()
		 * @generated
		 */
		EClass UIM_FULL_LAYOUT = eINSTANCE.getUimFullLayout();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimXYLayoutImpl <em>Uim XY Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimXYLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimXYLayout()
		 * @generated
		 */
		EClass UIM_XY_LAYOUT = eINSTANCE.getUimXYLayout();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimBorderLayoutImpl <em>Uim Border Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimBorderLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimBorderLayout()
		 * @generated
		 */
		EClass UIM_BORDER_LAYOUT = eINSTANCE.getUimBorderLayout();

		/**
		 * The meta object literal for the '<em><b>Horizontal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_BORDER_LAYOUT__HORIZONTAL = eINSTANCE.getUimBorderLayout_Horizontal();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimToolbarLayoutImpl <em>Uim Toolbar Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimToolbarLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimToolbarLayout()
		 * @generated
		 */
		EClass UIM_TOOLBAR_LAYOUT = eINSTANCE.getUimToolbarLayout();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.OutlayableComponentImpl <em>Outlayable Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.OutlayableComponentImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getOutlayableComponent()
		 * @generated
		 */
		EClass OUTLAYABLE_COMPONENT = eINSTANCE.getOutlayableComponent();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLAYABLE_COMPONENT__PARENT = eINSTANCE.getOutlayableComponent_Parent();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimLayoutImpl <em>Uim Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimLayout()
		 * @generated
		 */
		EClass UIM_LAYOUT = eINSTANCE.getUimLayout();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LAYOUT__PARENT = eINSTANCE.getUimLayout_Parent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LAYOUT__CHILDREN = eINSTANCE.getUimLayout_Children();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.LayoutContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.LayoutContainerImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getLayoutContainer()
		 * @generated
		 */
		EClass LAYOUT_CONTAINER = eINSTANCE.getLayoutContainer();

		/**
		 * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYOUT_CONTAINER__LAYOUT = eINSTANCE.getLayoutContainer_Layout();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.layout.impl.UimGridLayoutImpl <em>Uim Grid Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.layout.impl.UimGridLayoutImpl
		 * @see org.opaeum.uim.layout.impl.LayoutPackageImpl#getUimGridLayout()
		 * @generated
		 */
		EClass UIM_GRID_LAYOUT = eINSTANCE.getUimGridLayout();

		/**
		 * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS = eINSTANCE.getUimGridLayout_NumberOfColumns();

	}

} //LayoutPackage
