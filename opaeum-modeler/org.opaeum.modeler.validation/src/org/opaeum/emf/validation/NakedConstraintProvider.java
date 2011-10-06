package org.opaeum.emf.validation;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.xml.XmlConstraintProvider;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.linkage.ActivityValidationRule;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.metamodel.validation.IValidationRule;
import org.opaeum.validation.composition.CompositionValidationRule;

public class NakedConstraintProvider extends XmlConstraintProvider{
	@Override
	public void setInitializationData(IConfigurationElement config,String propertyName,Object data) throws CoreException{
		super.setInitializationData(config, propertyName, data);
		addContraint("ocl", CoreValidationRule.OCL, UMLPackage.Literals.VALUE_SPECIFICATION);
		addContraint("persistence", CoreValidationRule.INVERSE, UMLPackage.Literals.PROPERTY);
		addContraint("core", CoreValidationRule.PRIMITIVE_TYPE_MUST_EXTEND_OCL_PRIMITIVE, UMLPackage.Literals.CLASSIFIER);
		addContraint("core", CoreValidationRule.PRIMITIVE_MUST_SPECIALIZE_PRIMITIVES, UMLPackage.Literals.CLASSIFIER);
		addContraint("composition", CompositionValidationRule.PERSISTENT_CONTAINS_PERSISTENT, UMLPackage.Literals.CLASSIFIER);
		addContraint("activities", ActivityValidationRule.ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS, UMLPackage.Literals.PIN);
		addContraint("activities", ActivityValidationRule.AT_LEAST_ONE_DEFAULT_FLOW, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.ONE_FLOW_FROM_OBJECT_NODES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.ONE_FLOW_TO_OBJECT_NODES, UMLPackage.Literals.ACTIVITY_NODE);
		addContraint("activities", ActivityValidationRule.PIN_FOR_PARAMETER, UMLPackage.Literals.PIN);
	}
	private void addContraint(String feature,IValidationRule rule,EClass...metaClass){
		try{
			Category c = CategoryManager.getInstance().findCategory("opaeum/" + feature);
			ConstraintAdapter cd = new ConstraintAdapter(rule, metaClass);
			super.getConstraints().add(cd);
			cd.addCategory(c);
			ConstraintRegistry.getInstance().register(cd);
		}catch(ConstraintExistsException e){
			e.printStackTrace();
		}
	}
}
