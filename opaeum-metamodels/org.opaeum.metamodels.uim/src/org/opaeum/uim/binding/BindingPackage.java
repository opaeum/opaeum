/**
 */
package org.opaeum.uim.binding;

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
 * @see org.opaeum.uim.binding.BindingFactory
 * @model kind="package"
 * @generated
 */
public interface BindingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "binding";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/binding/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bind";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BindingPackage eINSTANCE = org.opaeum.uim.binding.impl.BindingPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.binding.impl.UimBindingImpl <em>Uim Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.binding.impl.UimBindingImpl
	 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getUimBinding()
	 * @generated
	 */
	int UIM_BINDING = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__NEXT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.binding.impl.LookupBindingImpl <em>Lookup Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.binding.impl.LookupBindingImpl
	 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getLookupBinding()
	 * @generated
	 */
	int LOOKUP_BINDING = 0;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Lookup</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__LOOKUP = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lookup Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.binding.impl.TableBindingImpl <em>Table Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.binding.impl.TableBindingImpl
	 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getTableBinding()
	 * @generated
	 */
	int TABLE_BINDING = 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__TABLE = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Table Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.binding.impl.FieldBindingImpl <em>Field Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.binding.impl.FieldBindingImpl
	 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getFieldBinding()
	 * @generated
	 */
	int FIELD_BINDING = 2;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__FIELD = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Field Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.binding.impl.PropertyRefImpl <em>Property Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.binding.impl.PropertyRefImpl
	 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getPropertyRef()
	 * @generated
	 */
	int PROPERTY_REF = 4;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__BINDING = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__PREVIOUS = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__NEXT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Property Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.binding.LookupBinding <em>Lookup Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lookup Binding</em>'.
	 * @see org.opaeum.uim.binding.LookupBinding
	 * @generated
	 */
	EClass getLookupBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.binding.LookupBinding#getLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Lookup</em>'.
	 * @see org.opaeum.uim.binding.LookupBinding#getLookup()
	 * @see #getLookupBinding()
	 * @generated
	 */
	EReference getLookupBinding_Lookup();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.binding.TableBinding <em>Table Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Binding</em>'.
	 * @see org.opaeum.uim.binding.TableBinding
	 * @generated
	 */
	EClass getTableBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.binding.TableBinding#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.opaeum.uim.binding.TableBinding#getTable()
	 * @see #getTableBinding()
	 * @generated
	 */
	EReference getTableBinding_Table();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.binding.FieldBinding <em>Field Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Binding</em>'.
	 * @see org.opaeum.uim.binding.FieldBinding
	 * @generated
	 */
	EClass getFieldBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.binding.FieldBinding#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Field</em>'.
	 * @see org.opaeum.uim.binding.FieldBinding#getField()
	 * @see #getFieldBinding()
	 * @generated
	 */
	EReference getFieldBinding_Field();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.binding.UimBinding <em>Uim Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Binding</em>'.
	 * @see org.opaeum.uim.binding.UimBinding
	 * @generated
	 */
	EClass getUimBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.binding.UimBinding#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Next</em>'.
	 * @see org.opaeum.uim.binding.UimBinding#getNext()
	 * @see #getUimBinding()
	 * @generated
	 */
	EReference getUimBinding_Next();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.binding.PropertyRef <em>Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Ref</em>'.
	 * @see org.opaeum.uim.binding.PropertyRef
	 * @generated
	 */
	EClass getPropertyRef();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.binding.PropertyRef#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.binding.PropertyRef#getBinding()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Binding();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.binding.PropertyRef#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Previous</em>'.
	 * @see org.opaeum.uim.binding.PropertyRef#getPrevious()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Previous();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.binding.PropertyRef#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Next</em>'.
	 * @see org.opaeum.uim.binding.PropertyRef#getNext()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Next();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BindingFactory getBindingFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.binding.impl.LookupBindingImpl <em>Lookup Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.binding.impl.LookupBindingImpl
		 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getLookupBinding()
		 * @generated
		 */
		EClass LOOKUP_BINDING = eINSTANCE.getLookupBinding();

		/**
		 * The meta object literal for the '<em><b>Lookup</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOKUP_BINDING__LOOKUP = eINSTANCE.getLookupBinding_Lookup();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.binding.impl.TableBindingImpl <em>Table Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.binding.impl.TableBindingImpl
		 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getTableBinding()
		 * @generated
		 */
		EClass TABLE_BINDING = eINSTANCE.getTableBinding();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_BINDING__TABLE = eINSTANCE.getTableBinding_Table();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.binding.impl.FieldBindingImpl <em>Field Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.binding.impl.FieldBindingImpl
		 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getFieldBinding()
		 * @generated
		 */
		EClass FIELD_BINDING = eINSTANCE.getFieldBinding();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_BINDING__FIELD = eINSTANCE.getFieldBinding_Field();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.binding.impl.UimBindingImpl <em>Uim Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.binding.impl.UimBindingImpl
		 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getUimBinding()
		 * @generated
		 */
		EClass UIM_BINDING = eINSTANCE.getUimBinding();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_BINDING__NEXT = eINSTANCE.getUimBinding_Next();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.binding.impl.PropertyRefImpl <em>Property Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.binding.impl.PropertyRefImpl
		 * @see org.opaeum.uim.binding.impl.BindingPackageImpl#getPropertyRef()
		 * @generated
		 */
		EClass PROPERTY_REF = eINSTANCE.getPropertyRef();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__BINDING = eINSTANCE.getPropertyRef_Binding();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__PREVIOUS = eINSTANCE.getPropertyRef_Previous();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__NEXT = eINSTANCE.getPropertyRef_Next();

	}

} //BindingPackage
