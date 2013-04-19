/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.perspective.BehaviorNavigationConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.ParameterNavigationConstraint;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;

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
			case PerspectivePackage.NAVIGATOR_CONFIGURATION: return createNavigatorConfiguration();
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT: return createClassNavigationConstraint();
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT: return createPropertyNavigationConstraint();
			case PerspectivePackage.EDITOR_CONFIGURATION: return createEditorConfiguration();
			case PerspectivePackage.PROPERTIES_CONFIGURATION: return createPropertiesConfiguration();
			case PerspectivePackage.NAVIGATION_CONSTRAINT: return createNavigationConstraint();
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT: return createOperationNavigationConstraint();
			case PerspectivePackage.BEHAVIOR_NAVIGATION_CONSTRAINT: return createBehaviorNavigationConstraint();
			case PerspectivePackage.INBOX_CONFIGURATION: return createInboxConfiguration();
			case PerspectivePackage.OUTBOX_CONFIGURATION: return createOutboxConfiguration();
			case PerspectivePackage.PARAMETER_NAVIGATION_CONSTRAINT: return createParameterNavigationConstraint();
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT: return createMultiplicityElementNavigationConstraint();
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
	public NavigatorConfiguration createNavigatorConfiguration() {
		NavigatorConfigurationImpl navigatorConfiguration = new NavigatorConfigurationImpl();
		return navigatorConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassNavigationConstraint createClassNavigationConstraint() {
		ClassNavigationConstraintImpl classNavigationConstraint = new ClassNavigationConstraintImpl();
		return classNavigationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyNavigationConstraint createPropertyNavigationConstraint() {
		PropertyNavigationConstraintImpl propertyNavigationConstraint = new PropertyNavigationConstraintImpl();
		return propertyNavigationConstraint;
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
	public NavigationConstraint createNavigationConstraint() {
		NavigationConstraintImpl navigationConstraint = new NavigationConstraintImpl();
		return navigationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationNavigationConstraint createOperationNavigationConstraint() {
		OperationNavigationConstraintImpl operationNavigationConstraint = new OperationNavigationConstraintImpl();
		return operationNavigationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorNavigationConstraint createBehaviorNavigationConstraint() {
		BehaviorNavigationConstraintImpl behaviorNavigationConstraint = new BehaviorNavigationConstraintImpl();
		return behaviorNavigationConstraint;
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
	public ParameterNavigationConstraint createParameterNavigationConstraint() {
		ParameterNavigationConstraintImpl parameterNavigationConstraint = new ParameterNavigationConstraintImpl();
		return parameterNavigationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplicityElementNavigationConstraint createMultiplicityElementNavigationConstraint() {
		MultiplicityElementNavigationConstraintImpl multiplicityElementNavigationConstraint = new MultiplicityElementNavigationConstraintImpl();
		return multiplicityElementNavigationConstraint;
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
