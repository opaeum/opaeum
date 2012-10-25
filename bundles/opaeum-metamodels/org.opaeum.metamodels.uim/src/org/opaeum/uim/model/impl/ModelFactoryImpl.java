/**
 */
package org.opaeum.uim.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.model.*;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.ModelFactory;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/model/1.0"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL: return createClassUserInteractionModel();
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL: return createResponsibilityUserInteractionModel();
			case ModelPackage.BEHAVIOR_USER_INTERACTION_MODEL: return createBehaviorUserInteractionModel();
			case ModelPackage.QUERY_INVOKER: return createQueryInvoker();
			case ModelPackage.OPERATION_INVOCATION_WIZARD: return createOperationInvocationWizard();
			case ModelPackage.EMBEDDED_TASK_EDITOR: return createEmbeddedTaskEditor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassUserInteractionModel createClassUserInteractionModel() {
		ClassUserInteractionModelImpl classUserInteractionModel = new ClassUserInteractionModelImpl();
		return classUserInteractionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityUserInteractionModel createResponsibilityUserInteractionModel() {
		ResponsibilityUserInteractionModelImpl responsibilityUserInteractionModel = new ResponsibilityUserInteractionModelImpl();
		return responsibilityUserInteractionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorUserInteractionModel createBehaviorUserInteractionModel() {
		BehaviorUserInteractionModelImpl behaviorUserInteractionModel = new BehaviorUserInteractionModelImpl();
		return behaviorUserInteractionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryInvoker createQueryInvoker() {
		QueryInvokerImpl queryInvoker = new QueryInvokerImpl();
		return queryInvoker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInvocationWizard createOperationInvocationWizard() {
		OperationInvocationWizardImpl operationInvocationWizard = new OperationInvocationWizardImpl();
		return operationInvocationWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmbeddedTaskEditor createEmbeddedTaskEditor() {
		EmbeddedTaskEditorImpl embeddedTaskEditor = new EmbeddedTaskEditorImpl();
		return embeddedTaskEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
