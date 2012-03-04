/**
 */
package org.opaeum.uim.perspective.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.HiddenClass;
import org.opaeum.uim.perspective.HiddenCompositeProperty;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.VisibleNonCompositeProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explorer Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getPerspective <em>Perspective</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getHiddenClasses <em>Hidden Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getVisibleProperties <em>Visible Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getHiddenProperties <em>Hidden Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplorerConfigurationImpl extends EObjectImpl implements ExplorerConfiguration {
	/**
	 * The cached value of the '{@link #getHiddenClasses() <em>Hidden Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHiddenClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<HiddenClass> hiddenClasses;

	/**
	 * The cached value of the '{@link #getVisibleProperties() <em>Visible Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibleProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<VisibleNonCompositeProperty> visibleProperties;

	/**
	 * The cached value of the '{@link #getHiddenProperties() <em>Hidden Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHiddenProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<HiddenCompositeProperty> hiddenProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.EXPLORER_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPerspective getPerspective() {
		if (eContainerFeatureID() != PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE) return null;
		return (UimPerspective)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerspective(UimPerspective newPerspective, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPerspective, PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerspective(UimPerspective newPerspective) {
		if (newPerspective != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE && newPerspective != null)) {
			if (EcoreUtil.isAncestor(this, newPerspective))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPerspective != null)
				msgs = ((InternalEObject)newPerspective).eInverseAdd(this, PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, UimPerspective.class, msgs);
			msgs = basicSetPerspective(newPerspective, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE, newPerspective, newPerspective));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HiddenClass> getHiddenClasses() {
		if (hiddenClasses == null) {
			hiddenClasses = new EObjectContainmentWithInverseEList<HiddenClass>(HiddenClass.class, this, PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES, PerspectivePackage.HIDDEN_CLASS__EXPLORER_CONFIGURATION);
		}
		return hiddenClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VisibleNonCompositeProperty> getVisibleProperties() {
		if (visibleProperties == null) {
			visibleProperties = new EObjectContainmentWithInverseEList<VisibleNonCompositeProperty>(VisibleNonCompositeProperty.class, this, PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES, PerspectivePackage.VISIBLE_NON_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION);
		}
		return visibleProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HiddenCompositeProperty> getHiddenProperties() {
		if (hiddenProperties == null) {
			hiddenProperties = new EObjectContainmentWithInverseEList<HiddenCompositeProperty>(HiddenCompositeProperty.class, this, PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES, PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY__EXPLORER_CONFIGURATION);
		}
		return hiddenProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPerspective((UimPerspective)otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getHiddenClasses()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVisibleProperties()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getHiddenProperties()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				return basicSetPerspective(null, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				return ((InternalEList<?>)getHiddenClasses()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				return ((InternalEList<?>)getVisibleProperties()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				return ((InternalEList<?>)getHiddenProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.UIM_PERSPECTIVE__EXPLORER_CONFIGURATION, UimPerspective.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				return getPerspective();
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				return getHiddenClasses();
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				return getVisibleProperties();
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				return getHiddenProperties();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				setPerspective((UimPerspective)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				getHiddenClasses().clear();
				getHiddenClasses().addAll((Collection<? extends HiddenClass>)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				getVisibleProperties().clear();
				getVisibleProperties().addAll((Collection<? extends VisibleNonCompositeProperty>)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				getHiddenProperties().clear();
				getHiddenProperties().addAll((Collection<? extends HiddenCompositeProperty>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				setPerspective((UimPerspective)null);
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				getHiddenClasses().clear();
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				getVisibleProperties().clear();
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				getHiddenProperties().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONFIGURATION__PERSPECTIVE:
				return getPerspective() != null;
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_CLASSES:
				return hiddenClasses != null && !hiddenClasses.isEmpty();
			case PerspectivePackage.EXPLORER_CONFIGURATION__VISIBLE_PROPERTIES:
				return visibleProperties != null && !visibleProperties.isEmpty();
			case PerspectivePackage.EXPLORER_CONFIGURATION__HIDDEN_PROPERTIES:
				return hiddenProperties != null && !hiddenProperties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExplorerConfigurationImpl
