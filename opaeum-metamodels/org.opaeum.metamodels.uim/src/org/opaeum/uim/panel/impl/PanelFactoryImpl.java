/**
 */
package org.opaeum.uim.panel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.panel.*;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.HorizontalPanel;
import org.opaeum.uim.panel.PanelFactory;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.panel.VerticalPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PanelFactoryImpl extends EFactoryImpl implements PanelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PanelFactory init() {
		try {
			PanelFactory thePanelFactory = (PanelFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/panel/1.0"); 
			if (thePanelFactory != null) {
				return thePanelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PanelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PanelFactoryImpl() {
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
			case PanelPackage.GRID_PANEL: return createGridPanel();
			case PanelPackage.VERTICAL_PANEL: return createVerticalPanel();
			case PanelPackage.HORIZONTAL_PANEL: return createHorizontalPanel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GridPanel createGridPanel() {
		GridPanelImpl gridPanel = new GridPanelImpl();
		return gridPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalPanel createVerticalPanel() {
		VerticalPanelImpl verticalPanel = new VerticalPanelImpl();
		return verticalPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HorizontalPanel createHorizontalPanel() {
		HorizontalPanelImpl horizontalPanel = new HorizontalPanelImpl();
		return horizontalPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PanelPackage getPanelPackage() {
		return (PanelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PanelPackage getPackage() {
		return PanelPackage.eINSTANCE;
	}

} //PanelFactoryImpl
