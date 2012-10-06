/**
 */
package org.opaeum.uim.model;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.AbstractUserInteractionModel#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getAbstractUserInteractionModel()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AbstractUserInteractionModel extends UmlReference, UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked Uml Resource</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked Uml Resource</em>' attribute.
	 * @see #setLinkedUmlResource(String)
	 * @see org.opaeum.uim.model.ModelPackage#getAbstractUserInteractionModel_LinkedUmlResource()
	 * @model default=""
	 * @generated
	 */
	String getLinkedUmlResource();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.AbstractUserInteractionModel#getLinkedUmlResource <em>Linked Uml Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Uml Resource</em>' attribute.
	 * @see #getLinkedUmlResource()
	 * @generated
	 */
	void setLinkedUmlResource(String value);

} // AbstractUserInteractionModel
