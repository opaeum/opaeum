/**
 */
package org.opaeum.uim.cube;

import org.eclipse.emf.common.util.EList;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.CubeQueryEditor#getQueries <em>Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.cube.CubePackage#getCubeQueryEditor()
 * @model
 * @generated
 */
public interface CubeQueryEditor extends UserInteractionElement, UmlReference {
	/**
	 * Returns the value of the '<em><b>Queries</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.CubeQuery}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Queries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Queries</em>' containment reference list.
	 * @see org.opaeum.uim.cube.CubePackage#getCubeQueryEditor_Queries()
	 * @model containment="true"
	 * @generated
	 */
	EList<CubeQuery> getQueries();

} // CubeQueryEditor
