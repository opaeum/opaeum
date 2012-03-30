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
	 * The feature id for the '<em><b>Configured Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__CONFIGURED_CLASSES = 1;

	/**
	 * The feature id for the '<em><b>Configured Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES = 2;

	/**
	 * The number of structural features of the '<em>Explorer Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl <em>Explorer Class Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerClassConfiguration()
	 * @generated
	 */
	int EXPLORER_CLASS_CONFIGURATION = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONFIGURATION__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Explorer Class Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONFIGURATION_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerPropertyConfigurationImpl <em>Explorer Property Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerPropertyConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerPropertyConfiguration()
	 * @generated
	 */
	int EXPLORER_PROPERTY_CONFIGURATION = 4;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONFIGURATION__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONFIGURATION__EXPLORER_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONFIGURATION__IS_VISIBLE = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Explorer Property Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONFIGURATION_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.ViewKind <em>View Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.ViewKind
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewKind()
	 * @generated
	 */
	int VIEW_KIND = 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
	 * @generated
	 */
	int POSITION_IN_PERSPECTIVE = 6;


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
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredClasses <em>Configured Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configured Classes</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredClasses()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_ConfiguredClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredProperties <em>Configured Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configured Properties</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredProperties()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_ConfiguredProperties();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration <em>Explorer Class Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Class Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConfiguration
	 * @generated
	 */
	EClass getExplorerClassConfiguration();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration()
	 * @see #getExplorerClassConfiguration()
	 * @generated
	 */
	EReference getExplorerClassConfiguration_ExplorerConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getIsVisible <em>Is Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Visible</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConfiguration#getIsVisible()
	 * @see #getExplorerClassConfiguration()
	 * @generated
	 */
	EAttribute getExplorerClassConfiguration_IsVisible();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration <em>Explorer Property Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Property Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConfiguration
	 * @generated
	 */
	EClass getExplorerPropertyConfiguration();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration()
	 * @see #getExplorerPropertyConfiguration()
	 * @generated
	 */
	EReference getExplorerPropertyConfiguration_ExplorerConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getIsVisible <em>Is Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Visible</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getIsVisible()
	 * @see #getExplorerPropertyConfiguration()
	 * @generated
	 */
	EAttribute getExplorerPropertyConfiguration_IsVisible();

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
		 * The meta object literal for the '<em><b>Configured Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__CONFIGURED_CLASSES = eINSTANCE.getExplorerConfiguration_ConfiguredClasses();

		/**
		 * The meta object literal for the '<em><b>Configured Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES = eINSTANCE.getExplorerConfiguration_ConfiguredProperties();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl <em>Explorer Class Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerClassConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerClassConfiguration()
		 * @generated
		 */
		EClass EXPLORER_CLASS_CONFIGURATION = eINSTANCE.getExplorerClassConfiguration();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION = eINSTANCE.getExplorerClassConfiguration_ExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Is Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE = eINSTANCE.getExplorerClassConfiguration_IsVisible();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerPropertyConfigurationImpl <em>Explorer Property Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerPropertyConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerPropertyConfiguration()
		 * @generated
		 */
		EClass EXPLORER_PROPERTY_CONFIGURATION = eINSTANCE.getExplorerPropertyConfiguration();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_PROPERTY_CONFIGURATION__EXPLORER_CONFIGURATION = eINSTANCE.getExplorerPropertyConfiguration_ExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Is Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPLORER_PROPERTY_CONFIGURATION__IS_VISIBLE = eINSTANCE.getExplorerPropertyConfiguration_IsVisible();

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
