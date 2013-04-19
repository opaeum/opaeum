/**
 */
package org.opaeum.uim.component.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.component.ComponentFactory;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.MasterTree;
import org.opaeum.uim.component.PanelForClass;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentFactoryImpl extends EFactoryImpl implements ComponentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentFactory init() {
		try {
			ComponentFactory theComponentFactory = (ComponentFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/component/1.0"); 
			if (theComponentFactory != null) {
				return theComponentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentFactoryImpl() {
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
			case ComponentPackage.UIM_FIELD: return createUimField();
			case ComponentPackage.UIM_DATA_TABLE: return createUimDataTable();
			case ComponentPackage.MASTER_TREE: return createMasterTree();
			case ComponentPackage.DETAIL_COMPONENT: return createDetailComponent();
			case ComponentPackage.PANEL_FOR_CLASS: return createPanelForClass();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimField createUimField() {
		UimFieldImpl uimField = new UimFieldImpl();
		return uimField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDataTable createUimDataTable() {
		UimDataTableImpl uimDataTable = new UimDataTableImpl();
		return uimDataTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterTree createMasterTree() {
		MasterTreeImpl masterTree = new MasterTreeImpl();
		return masterTree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailComponent createDetailComponent() {
		DetailComponentImpl detailComponent = new DetailComponentImpl();
		return detailComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PanelForClass createPanelForClass() {
		PanelForClassImpl panelForClass = new PanelForClassImpl();
		return panelForClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPackage getComponentPackage() {
		return (ComponentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentPackage getPackage() {
		return ComponentPackage.eINSTANCE;
	}

} //ComponentFactoryImpl
