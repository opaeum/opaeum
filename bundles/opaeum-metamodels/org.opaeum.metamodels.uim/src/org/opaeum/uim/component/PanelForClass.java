/**
 */
package org.opaeum.uim.component;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Panel For Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.PanelForClass#getDetailComponent <em>Detail Component</em>}</li>
 *   <li>{@link org.opaeum.uim.component.PanelForClass#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.component.ComponentPackage#getPanelForClass()
 * @model
 * @generated
 */
public interface PanelForClass extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Detail Component</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.component.DetailComponent#getPanelsForClasses <em>Panels For Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detail Component</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detail Component</em>' container reference.
	 * @see #setDetailComponent(DetailComponent)
	 * @see org.opaeum.uim.component.ComponentPackage#getPanelForClass_DetailComponent()
	 * @see org.opaeum.uim.component.DetailComponent#getPanelsForClasses
	 * @model opposite="panelsForClasses" required="true" transient="false"
	 * @generated
	 */
	DetailComponent getDetailComponent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.component.PanelForClass#getDetailComponent <em>Detail Component</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Detail Component</em>' container reference.
	 * @see #getDetailComponent()
	 * @generated
	 */
	void setDetailComponent(DetailComponent value);

	/**
	 * Returns the value of the '<em><b>Panel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel</em>' reference.
	 * @see #setPanel(AbstractPanel)
	 * @see org.opaeum.uim.component.ComponentPackage#getPanelForClass_Panel()
	 * @model required="true"
	 * @generated
	 */
	AbstractPanel getPanel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.component.PanelForClass#getPanel <em>Panel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel</em>' reference.
	 * @see #getPanel()
	 * @generated
	 */
	void setPanel(AbstractPanel value);

} // PanelForClass
