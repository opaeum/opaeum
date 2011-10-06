/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.binding.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.LookupBinding;
import org.opaeum.uim.binding.NavigationBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.TableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BindingFactoryImpl extends EFactoryImpl implements BindingFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BindingFactory init() {
		try {
			BindingFactory theBindingFactory = (BindingFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/binding/1.0"); 
			if (theBindingFactory != null) {
				return theBindingFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BindingFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingFactoryImpl() {
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
			case BindingPackage.LOOKUP_BINDING: return createLookupBinding();
			case BindingPackage.NAVIGATION_BINDING: return createNavigationBinding();
			case BindingPackage.TABLE_BINDING: return createTableBinding();
			case BindingPackage.FIELD_BINDING: return createFieldBinding();
			case BindingPackage.PROPERTY_REF: return createPropertyRef();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LookupBinding createLookupBinding() {
		LookupBindingImpl lookupBinding = new LookupBindingImpl();
		return lookupBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationBinding createNavigationBinding() {
		NavigationBindingImpl navigationBinding = new NavigationBindingImpl();
		return navigationBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBinding createTableBinding() {
		TableBindingImpl tableBinding = new TableBindingImpl();
		return tableBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldBinding createFieldBinding() {
		FieldBindingImpl fieldBinding = new FieldBindingImpl();
		return fieldBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRef createPropertyRef() {
		PropertyRefImpl propertyRef = new PropertyRefImpl();
		return propertyRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingPackage getBindingPackage() {
		return (BindingPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BindingPackage getPackage() {
		return BindingPackage.eINSTANCE;
	}

} //BindingFactoryImpl
