/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.editor.*;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.editor.OperationMenuItem;
import org.opaeum.uim.editor.QueryResultPage;
import org.opaeum.uim.editor.ResponsibilityViewer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EditorFactoryImpl extends EFactoryImpl implements EditorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EditorFactory init() {
		try {
			EditorFactory theEditorFactory = (EditorFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/editor/1.0"); 
			if (theEditorFactory != null) {
				return theEditorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EditorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorFactoryImpl() {
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
			case EditorPackage.ABSTRACT_EDITOR: return createAbstractEditor();
			case EditorPackage.BEHAVIOR_EXECUTION_EDITOR: return createBehaviorExecutionEditor();
			case EditorPackage.EDITOR_PAGE: return createEditorPage();
			case EditorPackage.ACTION_BAR: return createActionBar();
			case EditorPackage.MENU_CONFIGURATION: return createMenuConfiguration();
			case EditorPackage.OPERATION_MENU_ITEM: return createOperationMenuItem();
			case EditorPackage.RESPONSIBILITY_VIEWER: return createResponsibilityViewer();
			case EditorPackage.QUERY_RESULT_PAGE: return createQueryResultPage();
			case EditorPackage.OBJECT_EDITOR: return createObjectEditor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractEditor createAbstractEditor() {
		AbstractEditorImpl abstractEditor = new AbstractEditorImpl();
		return abstractEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorExecutionEditor createBehaviorExecutionEditor() {
		BehaviorExecutionEditorImpl behaviorExecutionEditor = new BehaviorExecutionEditorImpl();
		return behaviorExecutionEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorPage createEditorPage() {
		EditorPageImpl editorPage = new EditorPageImpl();
		return editorPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionBar createActionBar() {
		ActionBarImpl actionBar = new ActionBarImpl();
		return actionBar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuConfiguration createMenuConfiguration() {
		MenuConfigurationImpl menuConfiguration = new MenuConfigurationImpl();
		return menuConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationMenuItem createOperationMenuItem() {
		OperationMenuItemImpl operationMenuItem = new OperationMenuItemImpl();
		return operationMenuItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityViewer createResponsibilityViewer() {
		ResponsibilityViewerImpl responsibilityViewer = new ResponsibilityViewerImpl();
		return responsibilityViewer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryResultPage createQueryResultPage() {
		QueryResultPageImpl queryResultPage = new QueryResultPageImpl();
		return queryResultPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectEditor createObjectEditor() {
		ObjectEditorImpl objectEditor = new ObjectEditorImpl();
		return objectEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorPackage getEditorPackage() {
		return (EditorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EditorPackage getPackage() {
		return EditorPackage.eINSTANCE;
	}

} //EditorFactoryImpl
