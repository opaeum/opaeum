/**
 */
package org.opaeum.uim.perspective;

import org.opaeum.uim.UserInteractionElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ViewAllocation#getWidth <em>Width</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ViewAllocation#getHeight <em>Height</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ViewAllocation#getPosition <em>Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getViewAllocation()
 * @model abstract="true"
 * @generated
 */
public interface ViewAllocation extends UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(Integer)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getViewAllocation_Width()
	 * @model
	 * @generated
	 */
	Integer getWidth();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ViewAllocation#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(Integer value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(Integer)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getViewAllocation_Height()
	 * @model
	 * @generated
	 */
	Integer getHeight();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ViewAllocation#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(Integer value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * The literals are from the enumeration {@link org.opaeum.uim.perspective.PositionInPerspective}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see #setPosition(PositionInPerspective)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getViewAllocation_Position()
	 * @model
	 * @generated
	 */
	PositionInPerspective getPosition();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ViewAllocation#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(PositionInPerspective value);

} // ViewAllocation
