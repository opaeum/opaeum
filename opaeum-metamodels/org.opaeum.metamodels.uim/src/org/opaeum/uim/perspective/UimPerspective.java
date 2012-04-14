/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Perspective</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.UimPerspective#getEditorConfiguration <em>Editor Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.UimPerspective#getPropertiesConfiguration <em>Properties Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective()
 * @model
 * @generated
 */
public interface UimPerspective extends EObject {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' containment reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective_ExplorerConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' containment reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(ExplorerConfiguration value);

	/**
	 * Returns the value of the '<em><b>Editor Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor Configuration</em>' containment reference.
	 * @see #setEditorConfiguration(EditorConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective_EditorConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	EditorConfiguration getEditorConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.UimPerspective#getEditorConfiguration <em>Editor Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor Configuration</em>' containment reference.
	 * @see #getEditorConfiguration()
	 * @generated
	 */
	void setEditorConfiguration(EditorConfiguration value);

	/**
	 * Returns the value of the '<em><b>Properties Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties Configuration</em>' containment reference.
	 * @see #setPropertiesConfiguration(PropertiesConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective_PropertiesConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	PropertiesConfiguration getPropertiesConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.UimPerspective#getPropertiesConfiguration <em>Properties Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Properties Configuration</em>' containment reference.
	 * @see #getPropertiesConfiguration()
	 * @generated
	 */
	void setPropertiesConfiguration(PropertiesConfiguration value);

} // UimPerspective
