/**
 */
package org.opaeum.uim.panel;

import org.opaeum.uim.Labels;
import org.opaeum.uim.component.UimContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.AbstractPanel#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.panel.PanelPackage#getAbstractPanel()
 * @model abstract="true"
 * @generated
 */
public interface AbstractPanel extends UimContainer {
	/**
	 * Returns the value of the '<em><b>Labels</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Labels</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' containment reference.
	 * @see #setLabels(Labels)
	 * @see org.opaeum.uim.panel.PanelPackage#getAbstractPanel_Labels()
	 * @model containment="true"
	 * @generated
	 */
	Labels getLabels();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.panel.AbstractPanel#getLabels <em>Labels</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Labels</em>' containment reference.
	 * @see #getLabels()
	 * @generated
	 */
	void setLabels(Labels value);

} // AbstractPanel
