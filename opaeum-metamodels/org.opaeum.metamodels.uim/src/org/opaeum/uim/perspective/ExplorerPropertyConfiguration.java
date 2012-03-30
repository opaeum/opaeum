/**
 */
package org.opaeum.uim.perspective;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Property Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getIsVisible <em>Is Visible</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerPropertyConfiguration()
 * @model
 * @generated
 */
public interface ExplorerPropertyConfiguration extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredProperties <em>Configured Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerPropertyConfiguration_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredProperties
	 * @model opposite="configuredProperties" required="true" transient="false"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(ExplorerConfiguration value);

	/**
	 * Returns the value of the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Visible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Visible</em>' attribute.
	 * @see #setIsVisible(Boolean)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerPropertyConfiguration_IsVisible()
	 * @model
	 * @generated
	 */
	Boolean getIsVisible();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getIsVisible <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Visible</em>' attribute.
	 * @see #getIsVisible()
	 * @generated
	 */
	void setIsVisible(Boolean value);

} // ExplorerPropertyConfiguration
