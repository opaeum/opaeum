/**
 */
package org.opaeum.uim.panel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.panel.PanelPackage
 * @generated
 */
public interface PanelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PanelFactory eINSTANCE = org.opaeum.uim.panel.impl.PanelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Grid Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Panel</em>'.
	 * @generated
	 */
	GridPanel createGridPanel();

	/**
	 * Returns a new object of class '<em>Vertical Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertical Panel</em>'.
	 * @generated
	 */
	VerticalPanel createVerticalPanel();

	/**
	 * Returns a new object of class '<em>Horizontal Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Horizontal Panel</em>'.
	 * @generated
	 */
	HorizontalPanel createHorizontalPanel();

	/**
	 * Returns a new object of class '<em>Collapsible Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collapsible Panel</em>'.
	 * @generated
	 */
	CollapsiblePanel createCollapsiblePanel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PanelPackage getPanelPackage();

} //PanelFactory
