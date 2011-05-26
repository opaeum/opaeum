package org.nakeduml.emf.validation;

import net.sf.nakeduml.metamodel.validation.IValidationRule;
import net.sf.nakeduml.validation.CoreValidationRule;
import net.sf.nakeduml.validation.activities.ActivityValidationRule;
import net.sf.nakeduml.validation.activities.StructuralFeatureActionValidationRule;
import net.sf.nakeduml.validation.composition.CompositionValidationRule;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.xml.XmlConstraintProvider;
import org.eclipse.uml2.uml.UMLPackage;

public class NakedConstraintProvider extends XmlConstraintProvider {
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);
		addContraint("ocl", CoreValidationRule.OCL, UMLPackage.Literals.VALUE_SPECIFICATION);
		addContraint("persistence", CoreValidationRule.INVERSE, UMLPackage.Literals.PROPERTY);
		addContraint("core", CoreValidationRule.PRIMITIVE_TYPE_MUST_EXTEND_OCL_PRIMITIVE, UMLPackage.Literals.CLASSIFIER);
		addContraint("core", CoreValidationRule.PRIMITIVE_MUST_SPECIALIZE_PRIMITIVES, UMLPackage.Literals.CLASSIFIER);
		addContraint("composition", CompositionValidationRule.PERSISTENT_CONTAINS_PERSISTENT, UMLPackage.Literals.CLASSIFIER);
		addContraint("activities", ActivityValidationRule.ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS, UMLPackage.Literals.PIN);
		addContraint("activities", ActivityValidationRule.AT_LEAST_ONE_DEFAULT_FLOW, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.IMPLICIT_FORK_OR_DECISION, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.ONE_FLOW_FROM_OBJECT_NODES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.ONE_FLOW_TO_OBJECT_NODES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.OPAQUE_ACTION_ONLY_IN_PROCESS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.PIN_FOR_PARAMETER, UMLPackage.Literals.PIN);
		addContraint("activities", ActivityValidationRule.PROCESS_INVOCATION_ONLY_IN_PROCESS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.RESPONSIBILITIY_INVOCATION_ONLY_IN_PROCESS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.SEND_OBJECT_ACTION_ONLY_IN_PROCESS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.TARGET_FOR_OPAQUE_ACTIONS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.UNIQUE_ACTION_NAMES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.UNIQUE_EMULATED_ATTRIBUTES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", StructuralFeatureActionValidationRule.NO_TARGET_REQUIRES_ACTIVITY_OWNERSHIP,
				UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", StructuralFeatureActionValidationRule.TARGET_OBJECT_DOES_NOT_CONFORM_TO_OWNER,
				UMLPackage.Literals.ACTIVITY_NODE);
	}

	private void addContraint(String feature, IValidationRule rule, EClass... metaClass) {
		try {
			Category c = CategoryManager.getInstance().findCategory("nakeduml/" + feature);
			ConstraintAdapter cd = new ConstraintAdapter(rule, metaClass);
			super.getConstraints().add(cd);
			cd.addCategory(c);
			ConstraintRegistry.getInstance().register(cd);
		} catch (ConstraintExistsException e) {
			e.printStackTrace();
		}
	}
}
