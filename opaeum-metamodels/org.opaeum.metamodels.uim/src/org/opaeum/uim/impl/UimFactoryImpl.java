/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.*;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimFactoryImpl extends EFactoryImpl implements UimFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UimFactory init() {
		try {
			UimFactory theUimFactory = (UimFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/1.0"); 
			if (theUimFactory != null) {
				return theUimFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UimFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFactoryImpl() {
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
			case UimPackage.UML_REFERENCE: return createUmlReference();
			case UimPackage.USER_INTERFACE_ROOT: return createUserInterfaceRoot();
			case UimPackage.PAGE: return createPage();
			case UimPackage.LABELS: return createLabels();
			case UimPackage.LABELED_ELEMENT: return createLabeledElement();
			case UimPackage.IGNORED_ELEMENT: return createIgnoredElement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlReference createUmlReference() {
		UmlReferenceImpl umlReference = new UmlReferenceImpl();
		return umlReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInterfaceRoot createUserInterfaceRoot() {
		UserInterfaceRootImpl userInterfaceRoot = new UserInterfaceRootImpl();
		return userInterfaceRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels createLabels() {
		LabelsImpl labels = new LabelsImpl();
		return labels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabeledElement createLabeledElement() {
		LabeledElementImpl labeledElement = new LabeledElementImpl();
		return labeledElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IgnoredElement createIgnoredElement() {
		IgnoredElementImpl ignoredElement = new IgnoredElementImpl();
		return ignoredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPackage getUimPackage() {
		return (UimPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UimPackage getPackage() {
		return UimPackage.eINSTANCE;
	}

} //UimFactoryImpl
