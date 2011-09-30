/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.form.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opeum.uim.form.ActionTaskForm;
import org.opeum.uim.form.ClassForm;
import org.opeum.uim.form.DetailPanel;
import org.opeum.uim.form.FormFactory;
import org.opeum.uim.form.FormPackage;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.form.OperationInvocationForm;
import org.opeum.uim.form.OperationTaskForm;
import org.opeum.uim.form.StateForm;
import org.opeum.uim.form.UimForm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FormFactoryImpl extends EFactoryImpl implements FormFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FormFactory init() {
		try {
			FormFactory theFormFactory = (FormFactory)EPackage.Registry.INSTANCE.getEFactory("http://opeum.org/uimetamodel/form/1.0"); 
			if (theFormFactory != null) {
				return theFormFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FormFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormFactoryImpl() {
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
			case FormPackage.FORM_PANEL: return createFormPanel();
			case FormPackage.ACTION_TASK_FORM: return createActionTaskForm();
			case FormPackage.STATE_FORM: return createStateForm();
			case FormPackage.CLASS_FORM: return createClassForm();
			case FormPackage.UIM_FORM: return createUimForm();
			case FormPackage.OPERATION_TASK_FORM: return createOperationTaskForm();
			case FormPackage.OPERATION_INVOCATION_FORM: return createOperationInvocationForm();
			case FormPackage.DETAIL_PANEL: return createDetailPanel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormPanel createFormPanel() {
		FormPanelImpl formPanel = new FormPanelImpl();
		return formPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionTaskForm createActionTaskForm() {
		ActionTaskFormImpl actionTaskForm = new ActionTaskFormImpl();
		return actionTaskForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateForm createStateForm() {
		StateFormImpl stateForm = new StateFormImpl();
		return stateForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassForm createClassForm() {
		ClassFormImpl classForm = new ClassFormImpl();
		return classForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimForm createUimForm() {
		UimFormImpl uimForm = new UimFormImpl();
		return uimForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationTaskForm createOperationTaskForm() {
		OperationTaskFormImpl operationTaskForm = new OperationTaskFormImpl();
		return operationTaskForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInvocationForm createOperationInvocationForm() {
		OperationInvocationFormImpl operationInvocationForm = new OperationInvocationFormImpl();
		return operationInvocationForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailPanel createDetailPanel() {
		DetailPanelImpl detailPanel = new DetailPanelImpl();
		return detailPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormPackage getFormPackage() {
		return (FormPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FormPackage getPackage() {
		return FormPackage.eINSTANCE;
	}

} //FormFactoryImpl
