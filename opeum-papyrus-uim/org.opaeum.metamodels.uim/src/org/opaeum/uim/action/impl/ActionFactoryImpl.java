/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.action.*;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.LinkToEntity;
import org.opaeum.uim.action.LinkToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.TransitionAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ActionFactoryImpl extends EFactoryImpl implements ActionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionFactory init() {
		try {
			ActionFactory theActionFactory = (ActionFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/action/1.0"); 
			if (theActionFactory != null) {
				return theActionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ActionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionFactoryImpl() {
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
			case ActionPackage.BUILT_IN_ACTION: return createBuiltInAction();
			case ActionPackage.TRANSITION_ACTION: return createTransitionAction();
			case ActionPackage.LINK_TO_OPERATION: return createLinkToOperation();
			case ActionPackage.OPERATION_ACTION: return createOperationAction();
			case ActionPackage.LINK_TO_ENTITY: return createLinkToEntity();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ActionPackage.ACTION_KIND:
				return createActionKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ActionPackage.ACTION_KIND:
				return convertActionKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInAction createBuiltInAction() {
		BuiltInActionImpl builtInAction = new BuiltInActionImpl();
		return builtInAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionAction createTransitionAction() {
		TransitionActionImpl transitionAction = new TransitionActionImpl();
		return transitionAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkToOperation createLinkToOperation() {
		LinkToOperationImpl linkToOperation = new LinkToOperationImpl();
		return linkToOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationAction createOperationAction() {
		OperationActionImpl operationAction = new OperationActionImpl();
		return operationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkToEntity createLinkToEntity() {
		LinkToEntityImpl linkToEntity = new LinkToEntityImpl();
		return linkToEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionKind createActionKindFromString(EDataType eDataType, String initialValue) {
		ActionKind result = ActionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionPackage getActionPackage() {
		return (ActionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ActionPackage getPackage() {
		return ActionPackage.eINSTANCE;
	}

} //ActionFactoryImpl
