/**
 */
package org.opaeum.uim.perspective;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hidden Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getHiddenClass()
 * @model
 * @generated
 */
public interface HiddenClass extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenClasses <em>Hidden Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getHiddenClass_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getHiddenClasses
	 * @model opposite="hiddenClasses" required="true" transient="false"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.HiddenClass#getExplorerConfiguration <em>Explorer Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(ExplorerConfiguration value);

} // HiddenClass
