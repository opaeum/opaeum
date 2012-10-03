/**
 */
package org.opaeum.uim.panel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.GridPanel#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.panel.PanelPackage#getGridPanel()
 * @model
 * @generated
 */
public interface GridPanel extends CollapsiblePanel {
	/**
	 * Returns the value of the '<em><b>Number Of Columns</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Columns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Columns</em>' attribute.
	 * @see #setNumberOfColumns(Integer)
	 * @see org.opaeum.uim.panel.PanelPackage#getGridPanel_NumberOfColumns()
	 * @model default=""
	 * @generated
	 */
	Integer getNumberOfColumns();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.panel.GridPanel#getNumberOfColumns <em>Number Of Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Columns</em>' attribute.
	 * @see #getNumberOfColumns()
	 * @generated
	 */
	void setNumberOfColumns(Integer value);

} // GridPanel
