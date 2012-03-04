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
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenClasses <em>Hidden Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getVisibleProperties <em>Visible Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenProperties <em>Hidden Properties</em>}</li>
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
	 * @model opposite="explorerConfiguration" transient="false"
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
	 * Returns the value of the '<em><b>Hidden Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.HiddenClass}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden Classes</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_HiddenClasses()
	 * @see org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<HiddenClass> getHiddenClasses();

	/**
	 * Returns the value of the '<em><b>Visible Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.VisibleNonCompositeProperty}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.VisibleNonCompositeProperty#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_VisibleProperties()
	 * @see org.opaeum.uim.perspective.VisibleNonCompositeProperty#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<VisibleNonCompositeProperty> getVisibleProperties();

	/**
	 * Returns the value of the '<em><b>Hidden Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.HiddenCompositeProperty}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.HiddenCompositeProperty#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_HiddenProperties()
	 * @see org.opaeum.uim.perspective.HiddenCompositeProperty#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<HiddenCompositeProperty> getHiddenProperties();

} // ExplorerConfiguration
