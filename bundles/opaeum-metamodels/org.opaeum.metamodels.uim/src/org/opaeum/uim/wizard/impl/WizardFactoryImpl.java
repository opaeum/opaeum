/**
 */
package org.opaeum.uim.wizard.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.OperationResultPage;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.WizardPage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WizardFactoryImpl extends EFactoryImpl implements WizardFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WizardFactory init() {
		try {
			WizardFactory theWizardFactory = (WizardFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/wizard/1.0"); 
			if (theWizardFactory != null) {
				return theWizardFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WizardFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WizardFactoryImpl() {
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
			case WizardPackage.NEW_OBJECT_WIZARD: return createNewObjectWizard();
			case WizardPackage.WIZARD_PAGE: return createWizardPage();
			case WizardPackage.RESPONSIBILITY_INVOCATION_WIZARD: return createResponsibilityInvocationWizard();
			case WizardPackage.BEHAVIOR_INVOCATION_WIZARD: return createBehaviorInvocationWizard();
			case WizardPackage.OPERATION_RESULT_PAGE: return createOperationResultPage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewObjectWizard createNewObjectWizard() {
		NewObjectWizardImpl newObjectWizard = new NewObjectWizardImpl();
		return newObjectWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WizardPage createWizardPage() {
		WizardPageImpl wizardPage = new WizardPageImpl();
		return wizardPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityInvocationWizard createResponsibilityInvocationWizard() {
		ResponsibilityInvocationWizardImpl responsibilityInvocationWizard = new ResponsibilityInvocationWizardImpl();
		return responsibilityInvocationWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorInvocationWizard createBehaviorInvocationWizard() {
		BehaviorInvocationWizardImpl behaviorInvocationWizard = new BehaviorInvocationWizardImpl();
		return behaviorInvocationWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationResultPage createOperationResultPage() {
		OperationResultPageImpl operationResultPage = new OperationResultPageImpl();
		return operationResultPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WizardPackage getWizardPackage() {
		return (WizardPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WizardPackage getPackage() {
		return WizardPackage.eINSTANCE;
	}

} //WizardFactoryImpl
