/**
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.opaeum.uim.constraint.ConstraintPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.UimFactory
 * @model kind="package"
 * @generated
 */
public interface UimPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uim";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uim";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UimPackage eINSTANCE = org.opaeum.uim.impl.UimPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UserInteractionElementImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInteractionElement()
	 * @generated
	 */
	int USER_INTERACTION_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL = 1;

	/**
	 * The number of structural features of the '<em>User Interaction Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UmlReferenceImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUmlReference()
	 * @generated
	 */
	int UML_REFERENCE = 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE__UML_ELEMENT_UID = 0;

	/**
	 * The number of structural features of the '<em>Uml Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UserInterfaceRootImpl <em>User Interface Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UserInterfaceRootImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterfaceRoot()
	 * @generated
	 */
	int USER_INTERFACE_ROOT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__UNDER_USER_CONTROL = USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__UML_ELEMENT_UID = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__LABEL_OVERRIDE = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__EDITABILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__VISIBILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__IGNORED_ELEMENTS = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__SUPER_USER_INTERFACES = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT__PAGE_ORDERING = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>User Interface Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ROOT_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.PageImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 3;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__VISIBILITY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__EDITABILITY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__UML_ELEMENT_UID = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__UNDER_USER_CONTROL = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LABEL_OVERRIDE = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PANEL = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.LabelsImpl <em>Labels</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.LabelsImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getLabels()
	 * @generated
	 */
	int LABELS = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__SOURCE = EcorePackage.EANNOTATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Details</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__DETAILS = EcorePackage.EANNOTATION__DETAILS;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

	/**
	 * The number of structural features of the '<em>Labels</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELS_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.LabeledElementImpl <em>Labeled Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.LabeledElementImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getLabeledElement()
	 * @generated
	 */
	int LABELED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_ELEMENT__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_ELEMENT__NAME = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_ELEMENT__UNDER_USER_CONTROL = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_ELEMENT__LABEL_OVERRIDE = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Labeled Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_ELEMENT_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.IgnoredElementImpl <em>Ignored Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.IgnoredElementImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getIgnoredElement()
	 * @generated
	 */
	int IGNORED_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGNORED_ELEMENT__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>User Interface Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGNORED_ELEMENT__USER_INTERFACE_ROOT = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ignored Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGNORED_ELEMENT_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.PageOrderingImpl <em>Page Ordering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.PageOrderingImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getPageOrdering()
	 * @generated
	 */
	int PAGE_ORDERING = 7;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_ORDERING__PAGE = 0;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_ORDERING__POSITION = 1;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_ORDERING__LABEL_OVERRIDE = 2;

	/**
	 * The number of structural features of the '<em>Page Ordering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_ORDERING_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Element</em>'.
	 * @see org.opaeum.uim.UserInteractionElement
	 * @generated
	 */
	EClass getUserInteractionElement();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UserInteractionElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.opaeum.uim.UserInteractionElement#getName()
	 * @see #getUserInteractionElement()
	 * @generated
	 */
	EAttribute getUserInteractionElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UserInteractionElement#isUnderUserControl <em>Under User Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Under User Control</em>'.
	 * @see org.opaeum.uim.UserInteractionElement#isUnderUserControl()
	 * @see #getUserInteractionElement()
	 * @generated
	 */
	EAttribute getUserInteractionElement_UnderUserControl();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uml Reference</em>'.
	 * @see org.opaeum.uim.UmlReference
	 * @generated
	 */
	EClass getUmlReference();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UmlReference#getUmlElementUid <em>Uml Element Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uml Element Uid</em>'.
	 * @see org.opaeum.uim.UmlReference#getUmlElementUid()
	 * @see #getUmlReference()
	 * @generated
	 */
	EAttribute getUmlReference_UmlElementUid();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UserInterfaceRoot <em>User Interface Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interface Root</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot
	 * @generated
	 */
	EClass getUserInterfaceRoot();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UserInterfaceRoot#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot#getEditability()
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	EReference getUserInterfaceRoot_Editability();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UserInterfaceRoot#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot#getVisibility()
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	EReference getUserInterfaceRoot_Visibility();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.UserInterfaceRoot#getIgnoredElements <em>Ignored Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ignored Elements</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot#getIgnoredElements()
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	EReference getUserInterfaceRoot_IgnoredElements();

	/**
	 * Returns the meta object for the reference list '{@link org.opaeum.uim.UserInterfaceRoot#getSuperUserInterfaces <em>Super User Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super User Interfaces</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot#getSuperUserInterfaces()
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	EReference getUserInterfaceRoot_SuperUserInterfaces();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.UserInterfaceRoot#getPageOrdering <em>Page Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page Ordering</em>'.
	 * @see org.opaeum.uim.UserInterfaceRoot#getPageOrdering()
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	EReference getUserInterfaceRoot_PageOrdering();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.opaeum.uim.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.Page#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.Page#getPanel()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Panel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.Labels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Labels</em>'.
	 * @see org.opaeum.uim.Labels
	 * @generated
	 */
	EClass getLabels();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.LabeledElement <em>Labeled Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Labeled Element</em>'.
	 * @see org.opaeum.uim.LabeledElement
	 * @generated
	 */
	EClass getLabeledElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.LabeledElement#getLabelOverride <em>Label Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label Override</em>'.
	 * @see org.opaeum.uim.LabeledElement#getLabelOverride()
	 * @see #getLabeledElement()
	 * @generated
	 */
	EReference getLabeledElement_LabelOverride();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.IgnoredElement <em>Ignored Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ignored Element</em>'.
	 * @see org.opaeum.uim.IgnoredElement
	 * @generated
	 */
	EClass getIgnoredElement();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.IgnoredElement#getUserInterfaceRoot <em>User Interface Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>User Interface Root</em>'.
	 * @see org.opaeum.uim.IgnoredElement#getUserInterfaceRoot()
	 * @see #getIgnoredElement()
	 * @generated
	 */
	EReference getIgnoredElement_UserInterfaceRoot();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.PageOrdering <em>Page Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page Ordering</em>'.
	 * @see org.opaeum.uim.PageOrdering
	 * @generated
	 */
	EClass getPageOrdering();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.PageOrdering#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see org.opaeum.uim.PageOrdering#getPage()
	 * @see #getPageOrdering()
	 * @generated
	 */
	EReference getPageOrdering_Page();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.PageOrdering#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.opaeum.uim.PageOrdering#getPosition()
	 * @see #getPageOrdering()
	 * @generated
	 */
	EAttribute getPageOrdering_Position();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.PageOrdering#getLabelOverride <em>Label Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Label Override</em>'.
	 * @see org.opaeum.uim.PageOrdering#getLabelOverride()
	 * @see #getPageOrdering()
	 * @generated
	 */
	EReference getPageOrdering_LabelOverride();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UimFactory getUimFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UserInteractionElementImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInteractionElement()
		 * @generated
		 */
		EClass USER_INTERACTION_ELEMENT = eINSTANCE.getUserInteractionElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INTERACTION_ELEMENT__NAME = eINSTANCE.getUserInteractionElement_Name();

		/**
		 * The meta object literal for the '<em><b>Under User Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL = eINSTANCE.getUserInteractionElement_UnderUserControl();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UmlReferenceImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUmlReference()
		 * @generated
		 */
		EClass UML_REFERENCE = eINSTANCE.getUmlReference();

		/**
		 * The meta object literal for the '<em><b>Uml Element Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UML_REFERENCE__UML_ELEMENT_UID = eINSTANCE.getUmlReference_UmlElementUid();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UserInterfaceRootImpl <em>User Interface Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UserInterfaceRootImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterfaceRoot()
		 * @generated
		 */
		EClass USER_INTERFACE_ROOT = eINSTANCE.getUserInterfaceRoot();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ROOT__EDITABILITY = eINSTANCE.getUserInterfaceRoot_Editability();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ROOT__VISIBILITY = eINSTANCE.getUserInterfaceRoot_Visibility();

		/**
		 * The meta object literal for the '<em><b>Ignored Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ROOT__IGNORED_ELEMENTS = eINSTANCE.getUserInterfaceRoot_IgnoredElements();

		/**
		 * The meta object literal for the '<em><b>Super User Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ROOT__SUPER_USER_INTERFACES = eINSTANCE.getUserInterfaceRoot_SuperUserInterfaces();

		/**
		 * The meta object literal for the '<em><b>Page Ordering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ROOT__PAGE_ORDERING = eINSTANCE.getUserInterfaceRoot_PageOrdering();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.PageImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__PANEL = eINSTANCE.getPage_Panel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.LabelsImpl <em>Labels</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.LabelsImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getLabels()
		 * @generated
		 */
		EClass LABELS = eINSTANCE.getLabels();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.LabeledElementImpl <em>Labeled Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.LabeledElementImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getLabeledElement()
		 * @generated
		 */
		EClass LABELED_ELEMENT = eINSTANCE.getLabeledElement();

		/**
		 * The meta object literal for the '<em><b>Label Override</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABELED_ELEMENT__LABEL_OVERRIDE = eINSTANCE.getLabeledElement_LabelOverride();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.IgnoredElementImpl <em>Ignored Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.IgnoredElementImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getIgnoredElement()
		 * @generated
		 */
		EClass IGNORED_ELEMENT = eINSTANCE.getIgnoredElement();

		/**
		 * The meta object literal for the '<em><b>User Interface Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IGNORED_ELEMENT__USER_INTERFACE_ROOT = eINSTANCE.getIgnoredElement_UserInterfaceRoot();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.PageOrderingImpl <em>Page Ordering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.PageOrderingImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getPageOrdering()
		 * @generated
		 */
		EClass PAGE_ORDERING = eINSTANCE.getPageOrdering();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE_ORDERING__PAGE = eINSTANCE.getPageOrdering_Page();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_ORDERING__POSITION = eINSTANCE.getPageOrdering_Position();

		/**
		 * The meta object literal for the '<em><b>Label Override</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE_ORDERING__LABEL_OVERRIDE = eINSTANCE.getPageOrdering_LabelOverride();

	}

} //UimPackage
