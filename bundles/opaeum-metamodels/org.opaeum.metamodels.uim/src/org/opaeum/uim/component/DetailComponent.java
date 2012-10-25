/**
 */
package org.opaeum.uim.component;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Detail Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.DetailComponent#getMasterComponent <em>Master Component</em>}</li>
 *   <li>{@link org.opaeum.uim.component.DetailComponent#getPanelsForClasses <em>Panels For Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.component.ComponentPackage#getDetailComponent()
 * @model
 * @generated
 */
public interface DetailComponent extends UimComponent {
	/**
	 * Returns the value of the '<em><b>Master Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.component.MasterComponent#getDetailComponents <em>Detail Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master Component</em>' reference.
	 * @see #setMasterComponent(MasterComponent)
	 * @see org.opaeum.uim.component.ComponentPackage#getDetailComponent_MasterComponent()
	 * @see org.opaeum.uim.component.MasterComponent#getDetailComponents
	 * @model opposite="detailComponents" required="true"
	 * @generated
	 */
	MasterComponent getMasterComponent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.component.DetailComponent#getMasterComponent <em>Master Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master Component</em>' reference.
	 * @see #getMasterComponent()
	 * @generated
	 */
	void setMasterComponent(MasterComponent value);

	/**
	 * Returns the value of the '<em><b>Panels For Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.component.PanelForClass}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.component.PanelForClass#getDetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panels For Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panels For Classes</em>' containment reference list.
	 * @see org.opaeum.uim.component.ComponentPackage#getDetailComponent_PanelsForClasses()
	 * @see org.opaeum.uim.component.PanelForClass#getDetailComponent
	 * @model opposite="detailComponent" containment="true"
	 * @generated
	 */
	EList<PanelForClass> getPanelsForClasses();

} // DetailComponent
