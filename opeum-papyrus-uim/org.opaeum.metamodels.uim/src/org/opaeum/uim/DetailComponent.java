/**
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;

import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Detail Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.DetailComponent#getMasterComponent <em>Master Component</em>}</li>
 *   <li>{@link org.opaeum.uim.DetailComponent#getPanelClasses <em>Panel Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.DetailComponent#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getDetailComponent()
 * @model
 * @generated
 */
public interface DetailComponent extends UimComponent {
	/**
	 * Returns the value of the '<em><b>Master Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master Component</em>' reference.
	 * @see #setMasterComponent(MasterComponent)
	 * @see org.opaeum.uim.UimPackage#getDetailComponent_MasterComponent()
	 * @see org.opaeum.uim.MasterComponent#getDetailPanels
	 * @model opposite="detailPanels" required="true"
	 * @generated
	 */
	MasterComponent getMasterComponent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.DetailComponent#getMasterComponent <em>Master Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master Component</em>' reference.
	 * @see #getMasterComponent()
	 * @generated
	 */
	void setMasterComponent(MasterComponent value);

	/**
	 * Returns the value of the '<em><b>Panel Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.PanelClass}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.PanelClass#getDetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel Classes</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getDetailComponent_PanelClasses()
	 * @see org.opaeum.uim.PanelClass#getDetailComponent
	 * @model opposite="detailComponent" containment="true"
	 * @generated
	 */
	EList<PanelClass> getPanelClasses();

	/**
	 * Returns the value of the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel</em>' containment reference.
	 * @see #setPanel(AbstractPanel)
	 * @see org.opaeum.uim.UimPackage#getDetailComponent_Panel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AbstractPanel getPanel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.DetailComponent#getPanel <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel</em>' containment reference.
	 * @see #getPanel()
	 * @generated
	 */
	void setPanel(AbstractPanel value);

} // DetailComponent
