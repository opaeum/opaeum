/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective <em>Perspective</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredClasses <em>Configured Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getConfiguredProperties <em>Configured Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration()
 * @model
 * @generated
 */
public interface ExplorerConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Perspective</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perspective</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Perspective</em>' container reference.
	 * @see #setPerspective(UimPerspective)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_Perspective()
	 * @see org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" required="true" transient="false"
	 * @generated
	 */
	UimPerspective getPerspective();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective <em>Perspective</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Perspective</em>' container reference.
	 * @see #getPerspective()
	 * @generated
	 */
	void setPerspective(UimPerspective value);

	/**
	 * Returns the value of the '<em><b>Configured Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerClassConfiguration}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configured Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configured Classes</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_ConfiguredClasses()
	 * @see org.opaeum.uim.perspective.ExplorerClassConfiguration#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<ExplorerClassConfiguration> getConfiguredClasses();

	/**
	 * Returns the value of the '<em><b>Configured Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configured Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configured Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_ConfiguredProperties()
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConfiguration#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<ExplorerPropertyConfiguration> getConfiguredProperties();

} // ExplorerConfiguration
