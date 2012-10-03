/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertiesConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl#getExplorer <em>Explorer</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl#getInbox <em>Inbox</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl#getOutbox <em>Outbox</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PerspectiveConfigurationImpl extends EObjectImpl implements PerspectiveConfiguration {
	/**
	 * The cached value of the '{@link #getExplorer() <em>Explorer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplorer()
	 * @generated
	 * @ordered
	 */
	protected ExplorerConfiguration explorer;

	/**
	 * The cached value of the '{@link #getEditor() <em>Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditor()
	 * @generated
	 * @ordered
	 */
	protected EditorConfiguration editor;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected PropertiesConfiguration properties;

	/**
	 * The cached value of the '{@link #getInbox() <em>Inbox</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInbox()
	 * @generated
	 * @ordered
	 */
	protected InboxConfiguration inbox;

	/**
	 * The cached value of the '{@link #getOutbox() <em>Outbox</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutbox()
	 * @generated
	 * @ordered
	 */
	protected OutboxConfiguration outbox;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PerspectiveConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.PERSPECTIVE_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration getExplorer() {
		return explorer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplorer(ExplorerConfiguration newExplorer, NotificationChain msgs) {
		ExplorerConfiguration oldExplorer = explorer;
		explorer = newExplorer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER, oldExplorer, newExplorer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplorer(ExplorerConfiguration newExplorer) {
		if (newExplorer != explorer) {
			NotificationChain msgs = null;
			if (explorer != null)
				msgs = ((InternalEObject)explorer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER, null, msgs);
			if (newExplorer != null)
				msgs = ((InternalEObject)newExplorer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER, null, msgs);
			msgs = basicSetExplorer(newExplorer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER, newExplorer, newExplorer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorConfiguration getEditor() {
		return editor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditor(EditorConfiguration newEditor, NotificationChain msgs) {
		EditorConfiguration oldEditor = editor;
		editor = newEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR, oldEditor, newEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditor(EditorConfiguration newEditor) {
		if (newEditor != editor) {
			NotificationChain msgs = null;
			if (editor != null)
				msgs = ((InternalEObject)editor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR, null, msgs);
			if (newEditor != null)
				msgs = ((InternalEObject)newEditor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR, null, msgs);
			msgs = basicSetEditor(newEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR, newEditor, newEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesConfiguration getProperties() {
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperties(PropertiesConfiguration newProperties, NotificationChain msgs) {
		PropertiesConfiguration oldProperties = properties;
		properties = newProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES, oldProperties, newProperties);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperties(PropertiesConfiguration newProperties) {
		if (newProperties != properties) {
			NotificationChain msgs = null;
			if (properties != null)
				msgs = ((InternalEObject)properties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES, null, msgs);
			if (newProperties != null)
				msgs = ((InternalEObject)newProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES, null, msgs);
			msgs = basicSetProperties(newProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES, newProperties, newProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InboxConfiguration getInbox() {
		return inbox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInbox(InboxConfiguration newInbox, NotificationChain msgs) {
		InboxConfiguration oldInbox = inbox;
		inbox = newInbox;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX, oldInbox, newInbox);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInbox(InboxConfiguration newInbox) {
		if (newInbox != inbox) {
			NotificationChain msgs = null;
			if (inbox != null)
				msgs = ((InternalEObject)inbox).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX, null, msgs);
			if (newInbox != null)
				msgs = ((InternalEObject)newInbox).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX, null, msgs);
			msgs = basicSetInbox(newInbox, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX, newInbox, newInbox));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboxConfiguration getOutbox() {
		if (outbox != null && outbox.eIsProxy()) {
			InternalEObject oldOutbox = (InternalEObject)outbox;
			outbox = (OutboxConfiguration)eResolveProxy(oldOutbox);
			if (outbox != oldOutbox) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX, oldOutbox, outbox));
			}
		}
		return outbox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboxConfiguration basicGetOutbox() {
		return outbox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutbox(OutboxConfiguration newOutbox) {
		OutboxConfiguration oldOutbox = outbox;
		outbox = newOutbox;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX, oldOutbox, outbox));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER:
				return basicSetExplorer(null, msgs);
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR:
				return basicSetEditor(null, msgs);
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES:
				return basicSetProperties(null, msgs);
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX:
				return basicSetInbox(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER:
				return getExplorer();
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR:
				return getEditor();
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES:
				return getProperties();
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX:
				return getInbox();
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX:
				if (resolve) return getOutbox();
				return basicGetOutbox();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER:
				setExplorer((ExplorerConfiguration)newValue);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR:
				setEditor((EditorConfiguration)newValue);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES:
				setProperties((PropertiesConfiguration)newValue);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX:
				setInbox((InboxConfiguration)newValue);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX:
				setOutbox((OutboxConfiguration)newValue);
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
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER:
				setExplorer((ExplorerConfiguration)null);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR:
				setEditor((EditorConfiguration)null);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES:
				setProperties((PropertiesConfiguration)null);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX:
				setInbox((InboxConfiguration)null);
				return;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX:
				setOutbox((OutboxConfiguration)null);
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
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EXPLORER:
				return explorer != null;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__EDITOR:
				return editor != null;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__PROPERTIES:
				return properties != null;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__INBOX:
				return inbox != null;
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION__OUTBOX:
				return outbox != null;
		}
		return super.eIsSet(featureID);
	}

} //PerspectiveConfigurationImpl
