/**
 */
package org.opaeum.uim.perspective;

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
 * @see org.opaeum.uim.perspective.PerspectiveFactory
 * @model kind="package"
 * @generated
 */
public interface PerspectivePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "perspective";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/perspective/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "perspective";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PerspectivePackage eINSTANCE = org.opaeum.uim.perspective.impl.PerspectivePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl <em>Uim Perspective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.UimPerspectiveImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getUimPerspective()
	 * @generated
	 */
	int UIM_PERSPECTIVE = 0;

	/**
	 * The feature id for the '<em><b>View Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PERSPECTIVE__VIEW_ALLOCATIONS = 0;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PERSPECTIVE__EXPLORER_CONFIGURATION = 1;

	/**
	 * The number of structural features of the '<em>Uim Perspective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PERSPECTIVE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl <em>View Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ViewAllocationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewAllocation()
	 * @generated
	 */
	int VIEW_ALLOCATION = 1;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__HEIGHT = 1;

	/**
	 * The feature id for the '<em><b>Perspective</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__PERSPECTIVE = 2;

	/**
	 * The feature id for the '<em><b>View Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__VIEW_KIND = 3;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__POSITION = 4;

	/**
	 * The number of structural features of the '<em>View Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl <em>Explorer Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConfiguration()
	 * @generated
	 */
	int EXPLORER_CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Perspective</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__PERSPECTIVE = 0;

	/**
	 * The feature id for the '<em><b>Hidden Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__HIDDEN_CLASSES = 1;

	/**
	 * The feature id for the '<em><b>Visible Non Composite Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__VISIBLE_NON_COMPOSITE_PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Hidden Composite Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__HIDDEN_COMPOSITE_PROPERTIES = 3;

	/**
	 * The number of structural features of the '<em>Explorer Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.HiddenClassImpl <em>Hidden Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.HiddenClassImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getHiddenClass()
	 * @generated
	 */
	int HIDDEN_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_CLASS__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_CLASS__EXPLORER_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hidden Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_CLASS_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.VisibleNonCompositePropertyImpl <em>Visible Non Composite Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.VisibleNonCompositePropertyImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getVisibleNonCompositeProperty()
	 * @generated
	 */
	int VISIBLE_NON_COMPOSITE_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_NON_COMPOSITE_PROPERTY__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_NON_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Visible Non Composite Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_NON_COMPOSITE_PROPERTY_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.HiddenCompositePropertyImpl <em>Hidden Composite Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.HiddenCompositePropertyImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getHiddenCompositeProperty()
	 * @generated
	 */
	int HIDDEN_COMPOSITE_PROPERTY = 5;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_COMPOSITE_PROPERTY__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hidden Composite Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIDDEN_COMPOSITE_PROPERTY_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.ViewKind <em>View Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.ViewKind
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewKind()
	 * @generated
	 */
	int VIEW_KIND = 6;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
	 * @generated
	 */
	int POSITION_IN_PERSPECTIVE = 7;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.UimPerspective <em>Uim Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Perspective</em>'.
	 * @see org.opaeum.uim.perspective.UimPerspective
	 * @generated
	 */
	EClass getUimPerspective();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.UimPerspective#getViewAllocations <em>View Allocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View Allocations</em>'.
	 * @see org.opaeum.uim.perspective.UimPerspective#getViewAllocations()
	 * @see #getUimPerspective()
	 * @generated
	 */
	EReference getUimPerspective_ViewAllocations();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration()
	 * @see #getUimPerspective()
	 * @generated
	 */
	EReference getUimPerspective_ExplorerConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ViewAllocation <em>View Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Allocation</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation
	 * @generated
	 */
	EClass getViewAllocation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getWidth()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getHeight()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Height();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ViewAllocation#getPerspective <em>Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Perspective</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getPerspective()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EReference getViewAllocation_Perspective();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getViewKind <em>View Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>View Kind</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getViewKind()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_ViewKind();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getPosition()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Position();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration
	 * @generated
	 */
	EClass getExplorerConfiguration();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective <em>Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Perspective</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_Perspective();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenClasses <em>Hidden Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hidden Classes</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenClasses()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_HiddenClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getVisibleNonCompositeProperties <em>Visible Non Composite Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Visible Non Composite Properties</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getVisibleNonCompositeProperties()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_VisibleNonCompositeProperties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenCompositeProperties <em>Hidden Composite Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hidden Composite Properties</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenCompositeProperties()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_HiddenCompositeProperties();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.HiddenClass <em>Hidden Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hidden Class</em>'.
	 * @see org.opaeum.uim.perspective.HiddenClass
	 * @generated
	 */
	EClass getHiddenClass();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration()
	 * @see #getHiddenClass()
	 * @generated
	 */
	EReference getHiddenClass_ExplorerConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.VisibleNonCompositeProperty <em>Visible Non Composite Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visible Non Composite Property</em>'.
	 * @see org.opaeum.uim.perspective.VisibleNonCompositeProperty
	 * @generated
	 */
	EClass getVisibleNonCompositeProperty();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.VisibleNonCompositeProperty#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.VisibleNonCompositeProperty#getExplorerConfiguration()
	 * @see #getVisibleNonCompositeProperty()
	 * @generated
	 */
	EReference getVisibleNonCompositeProperty_ExplorerConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.HiddenCompositeProperty <em>Hidden Composite Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hidden Composite Property</em>'.
	 * @see org.opaeum.uim.perspective.HiddenCompositeProperty
	 * @generated
	 */
	EClass getHiddenCompositeProperty();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.HiddenCompositeProperty#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.HiddenCompositeProperty#getExplorerConfiguration()
	 * @see #getHiddenCompositeProperty()
	 * @generated
	 */
	EReference getHiddenCompositeProperty_ExplorerConfiguration();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.perspective.ViewKind <em>View Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>View Kind</em>'.
	 * @see org.opaeum.uim.perspective.ViewKind
	 * @generated
	 */
	EEnum getViewKind();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Position In Perspective</em>'.
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @generated
	 */
	EEnum getPositionInPerspective();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PerspectiveFactory getPerspectiveFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.UimPerspectiveImpl <em>Uim Perspective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.UimPerspectiveImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getUimPerspective()
		 * @generated
		 */
		EClass UIM_PERSPECTIVE = eINSTANCE.getUimPerspective();

		/**
		 * The meta object literal for the '<em><b>View Allocations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_PERSPECTIVE__VIEW_ALLOCATIONS = eINSTANCE.getUimPerspective_ViewAllocations();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_PERSPECTIVE__EXPLORER_CONFIGURATION = eINSTANCE.getUimPerspective_ExplorerConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl <em>View Allocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ViewAllocationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewAllocation()
		 * @generated
		 */
		EClass VIEW_ALLOCATION = eINSTANCE.getViewAllocation();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__WIDTH = eINSTANCE.getViewAllocation_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__HEIGHT = eINSTANCE.getViewAllocation_Height();

		/**
		 * The meta object literal for the '<em><b>Perspective</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW_ALLOCATION__PERSPECTIVE = eINSTANCE.getViewAllocation_Perspective();

		/**
		 * The meta object literal for the '<em><b>View Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__VIEW_KIND = eINSTANCE.getViewAllocation_ViewKind();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__POSITION = eINSTANCE.getViewAllocation_Position();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl <em>Explorer Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConfiguration()
		 * @generated
		 */
		EClass EXPLORER_CONFIGURATION = eINSTANCE.getExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Perspective</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__PERSPECTIVE = eINSTANCE.getExplorerConfiguration_Perspective();

		/**
		 * The meta object literal for the '<em><b>Hidden Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__HIDDEN_CLASSES = eINSTANCE.getExplorerConfiguration_HiddenClasses();

		/**
		 * The meta object literal for the '<em><b>Visible Non Composite Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__VISIBLE_NON_COMPOSITE_PROPERTIES = eINSTANCE.getExplorerConfiguration_VisibleNonCompositeProperties();

		/**
		 * The meta object literal for the '<em><b>Hidden Composite Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__HIDDEN_COMPOSITE_PROPERTIES = eINSTANCE.getExplorerConfiguration_HiddenCompositeProperties();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.HiddenClassImpl <em>Hidden Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.HiddenClassImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getHiddenClass()
		 * @generated
		 */
		EClass HIDDEN_CLASS = eINSTANCE.getHiddenClass();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIDDEN_CLASS__EXPLORER_CONFIGURATION = eINSTANCE.getHiddenClass_ExplorerConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.VisibleNonCompositePropertyImpl <em>Visible Non Composite Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.VisibleNonCompositePropertyImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getVisibleNonCompositeProperty()
		 * @generated
		 */
		EClass VISIBLE_NON_COMPOSITE_PROPERTY = eINSTANCE.getVisibleNonCompositeProperty();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VISIBLE_NON_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION = eINSTANCE.getVisibleNonCompositeProperty_ExplorerConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.HiddenCompositePropertyImpl <em>Hidden Composite Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.HiddenCompositePropertyImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getHiddenCompositeProperty()
		 * @generated
		 */
		EClass HIDDEN_COMPOSITE_PROPERTY = eINSTANCE.getHiddenCompositeProperty();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION = eINSTANCE.getHiddenCompositeProperty_ExplorerConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.ViewKind <em>View Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.ViewKind
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewKind()
		 * @generated
		 */
		EEnum VIEW_KIND = eINSTANCE.getViewKind();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.PositionInPerspective
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
		 * @generated
		 */
		EEnum POSITION_IN_PERSPECTIVE = eINSTANCE.getPositionInPerspective();

	}

} //PerspectivePackage
