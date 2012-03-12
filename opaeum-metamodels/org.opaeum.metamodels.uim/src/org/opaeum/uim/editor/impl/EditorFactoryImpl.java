/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.editor.*;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.editor.VisibleOperation;

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
			EditorFactory theEditorFactory = (EditorFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/form/1.0"); 
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
			case EditorPackage.ACTION_TASK_EDITOR: return createActionTaskEditor();
			case EditorPackage.CLASS_EDITOR: return createClassEditor();
			case EditorPackage.RESPONSIBILITY_TASK_EDITOR: return createResponsibilityTaskEditor();
			case EditorPackage.QUERY_INVOCATION_EDITOR: return createQueryInvocationEditor();
			case EditorPackage.EDITOR_PAGE: return createEditorPage();
			case EditorPackage.EDITOR_ACTION_BAR: return createEditorActionBar();
			case EditorPackage.MENU_CONFIGURATION: return createMenuConfiguration();
			case EditorPackage.VISIBLE_OPERATION: return createVisibleOperation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionTaskEditor createActionTaskEditor() {
		ActionTaskEditorImpl actionTaskEditor = new ActionTaskEditorImpl();
		return actionTaskEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassEditor createClassEditor() {
		ClassEditorImpl classEditor = new ClassEditorImpl();
		return classEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityTaskEditor createResponsibilityTaskEditor() {
		ResponsibilityTaskEditorImpl responsibilityTaskEditor = new ResponsibilityTaskEditorImpl();
		return responsibilityTaskEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryInvocationEditor createQueryInvocationEditor() {
		QueryInvocationEditorImpl queryInvocationEditor = new QueryInvocationEditorImpl();
		return queryInvocationEditor;
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
	public EditorActionBar createEditorActionBar() {
		EditorActionBarImpl editorActionBar = new EditorActionBarImpl();
		return editorActionBar;
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
	public VisibleOperation createVisibleOperation() {
		VisibleOperationImpl visibleOperation = new VisibleOperationImpl();
		return visibleOperation;
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
