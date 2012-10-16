/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.perspective.*;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerOperationConstraint;
import org.opaeum.uim.perspective.ExplorerPropertyConstraint;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PerspectiveFactoryImpl extends EFactoryImpl implements PerspectiveFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PerspectiveFactory init() {
		try {
			PerspectiveFactory thePerspectiveFactory = (PerspectiveFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/perspective/1.0"); 
			if (thePerspectiveFactory != null) {
				return thePerspectiveFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PerspectiveFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectiveFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION: return createPerspectiveConfiguration();
			case PerspectivePackage.EXPLORER_CONFIGURATION: return createExplorerConfiguration();
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT: return createExplorerClassConstraint();
			case PerspectivePackage.EXPLORER_PROPERTY_CONSTRAINT: return createExplorerPropertyConstraint();
			case PerspectivePackage.EDITOR_CONFIGURATION: return createEditorConfiguration();
			case PerspectivePackage.PROPERTIES_CONFIGURATION: return createPropertiesConfiguration();
			case PerspectivePackage.EXPLORER_OPERATION_CONSTRAINT: return createExplorerOperationConstraint();
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT: return createExplorerBehaviorConstraint();
			case PerspectivePackage.INBOX_CONFIGURATION: return createInboxConfiguration();
			case PerspectivePackage.OUTBOX_CONFIGURATION: return createOutboxConfiguration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PerspectivePackage.POSITION_IN_PERSPECTIVE:
				return createPositionInPerspectiveFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PerspectivePackage.POSITION_IN_PERSPECTIVE:
				return convertPositionInPerspectiveToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectiveConfiguration createPerspectiveConfiguration() {
		PerspectiveConfigurationImpl perspectiveConfiguration = new PerspectiveConfigurationImpl();
		return perspectiveConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration createExplorerConfiguration() {
		ExplorerConfigurationImpl explorerConfiguration = new ExplorerConfigurationImpl();
		return explorerConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerClassConstraint createExplorerClassConstraint() {
		ExplorerClassConstraintImpl explorerClassConstraint = new ExplorerClassConstraintImpl();
		return explorerClassConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerPropertyConstraint createExplorerPropertyConstraint() {
		ExplorerPropertyConstraintImpl explorerPropertyConstraint = new ExplorerPropertyConstraintImpl();
		return explorerPropertyConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorConfiguration createEditorConfiguration() {
		EditorConfigurationImpl editorConfiguration = new EditorConfigurationImpl();
		return editorConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesConfiguration createPropertiesConfiguration() {
		PropertiesConfigurationImpl propertiesConfiguration = new PropertiesConfigurationImpl();
		return propertiesConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerOperationConstraint createExplorerOperationConstraint() {
		ExplorerOperationConstraintImpl explorerOperationConstraint = new ExplorerOperationConstraintImpl();
		return explorerOperationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerBehaviorConstraint createExplorerBehaviorConstraint() {
		ExplorerBehaviorConstraintImpl explorerBehaviorConstraint = new ExplorerBehaviorConstraintImpl();
		return explorerBehaviorConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InboxConfiguration createInboxConfiguration() {
		InboxConfigurationImpl inboxConfiguration = new InboxConfigurationImpl();
		return inboxConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboxConfiguration createOutboxConfiguration() {
		OutboxConfigurationImpl outboxConfiguration = new OutboxConfigurationImpl();
		return outboxConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PositionInPerspective createPositionInPerspectiveFromString(EDataType eDataType, String initialValue) {
		PositionInPerspective result = PositionInPerspective.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPositionInPerspectiveToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectivePackage getPerspectivePackage() {
		return (PerspectivePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PerspectivePackage getPackage() {
		return PerspectivePackage.eINSTANCE;
	}

} //PerspectiveFactoryImpl
