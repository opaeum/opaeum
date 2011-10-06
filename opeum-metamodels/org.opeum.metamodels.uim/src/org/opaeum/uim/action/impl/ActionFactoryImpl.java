/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.action.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opeum.uim.action.ActionFactory;
import org.opeum.uim.action.ActionKind;
import org.opeum.uim.action.ActionPackage;
import org.opeum.uim.action.BuiltInAction;
import org.opeum.uim.action.NavigationToEntity;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.action.TransitionAction;

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
			ActionFactory theActionFactory = (ActionFactory)EPackage.Registry.INSTANCE.getEFactory("http://opeum.org/uimetamodel/action/1.0"); 
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
			case ActionPackage.NAVIGATION_TO_OPERATION: return createNavigationToOperation();
			case ActionPackage.OPERATION_ACTION: return createOperationAction();
			case ActionPackage.NAVIGATION_TO_ENTITY: return createNavigationToEntity();
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
	public NavigationToOperation createNavigationToOperation() {
		NavigationToOperationImpl navigationToOperation = new NavigationToOperationImpl();
		return navigationToOperation;
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
	public NavigationToEntity createNavigationToEntity() {
		NavigationToEntityImpl navigationToEntity = new NavigationToEntityImpl();
		return navigationToEntity;
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
