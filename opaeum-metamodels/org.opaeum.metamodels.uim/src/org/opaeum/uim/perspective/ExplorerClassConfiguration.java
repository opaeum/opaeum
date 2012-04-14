/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Class Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getIsVisible <em>Is Visible</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getConfiguredProperties <em>Configured Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConfiguration()
 * @model
 * @generated
 */
public interface ExplorerClassConfiguration extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredClasses <em>Configured Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConfiguration_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredClasses
	 * @model opposite="configuredClasses" required="true" transient="false"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}' container reference.
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
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConfiguration_IsVisible()
	 * @model
	 * @generated
	 */
	Boolean getIsVisible();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getIsVisible <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Visible</em>' attribute.
	 * @see #getIsVisible()
	 * @generated
	 */
	void setIsVisible(Boolean value);

	/**
	 * Returns the value of the '<em><b>Configured Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configured Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configured Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConfiguration_ConfiguredProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExplorerPropertyConfiguration> getConfiguredProperties();

} // ExplorerClassConfiguration
