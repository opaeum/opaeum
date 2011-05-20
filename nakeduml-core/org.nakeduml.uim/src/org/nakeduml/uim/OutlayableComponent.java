/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outlayable Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.OutlayableComponent#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getOutlayableComponent()
 * @model abstract="true"
 * @generated
 */
public interface OutlayableComponent extends UimComponent {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UimLayout#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(UimLayout)
	 * @see org.nakeduml.uim.UimPackage#getOutlayableComponent_Parent()
	 * @see org.nakeduml.uim.UimLayout#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	UimLayout getParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.OutlayableComponent#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(UimLayout value);

} // OutlayableComponent
