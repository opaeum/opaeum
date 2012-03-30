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
import org.opaeum.uim.perspective.ExplorerClassConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerPropertyConfiguration;
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
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getConfiguredClasses <em>Configured Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl#getConfiguredProperties <em>Configured Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExplorerConfigurationImpl extends EObjectImpl implements ExplorerConfiguration {
	/**
	 * The cached value of the '{@link #getConfiguredClasses() <em>Configured Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguredClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerClassConfiguration> configuredClasses;

	/**
	 * The cached value of the '{@link #getConfiguredProperties() <em>Configured Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguredProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ExplorerPropertyConfiguration> configuredProperties;

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
	public EList<ExplorerClassConfiguration> getConfiguredClasses() {
		if (configuredClasses == null) {
			configuredClasses = new EObjectContainmentWithInverseEList<ExplorerClassConfiguration>(ExplorerClassConfiguration.class, this, PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES, PerspectivePackage.EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION);
		}
		return configuredClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExplorerPropertyConfiguration> getConfiguredProperties() {
		if (configuredProperties == null) {
			configuredProperties = new EObjectContainmentWithInverseEList<ExplorerPropertyConfiguration>(ExplorerPropertyConfiguration.class, this, PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES, PerspectivePackage.EXPLORER_PROPERTY_CONFIGURATION__EXPLORER_CONFIGURATION);
		}
		return configuredProperties;
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConfiguredClasses()).basicAdd(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConfiguredProperties()).basicAdd(otherEnd, msgs);
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				return ((InternalEList<?>)getConfiguredClasses()).basicRemove(otherEnd, msgs);
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				return ((InternalEList<?>)getConfiguredProperties()).basicRemove(otherEnd, msgs);
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				return getConfiguredClasses();
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				return getConfiguredProperties();
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				getConfiguredClasses().clear();
				getConfiguredClasses().addAll((Collection<? extends ExplorerClassConfiguration>)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				getConfiguredProperties().clear();
				getConfiguredProperties().addAll((Collection<? extends ExplorerPropertyConfiguration>)newValue);
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				getConfiguredClasses().clear();
				return;
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				getConfiguredProperties().clear();
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
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_CLASSES:
				return configuredClasses != null && !configuredClasses.isEmpty();
			case PerspectivePackage.EXPLORER_CONFIGURATION__CONFIGURED_PROPERTIES:
				return configuredProperties != null && !configuredProperties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExplorerConfigurationImpl
