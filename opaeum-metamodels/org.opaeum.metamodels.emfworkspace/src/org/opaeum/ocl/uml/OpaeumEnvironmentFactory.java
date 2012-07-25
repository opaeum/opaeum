package org.opaeum.ocl.uml;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.ocl.uml.UMLFactory;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public final class OpaeumEnvironmentFactory extends UMLEnvironmentFactory{
	private Collection<Variable> variables = new HashSet<Variable>();
	private Element context;
	OpaeumLibrary library;
	public OpaeumEnvironmentFactory(Element context, Registry registry,OpaeumLibrary opaeumLibrary){
		super(registry, opaeumLibrary.getResourceSet());
		this.context=context;
		this.library=opaeumLibrary;
	}
	public OpaeumEnvironmentFactory(Element context, OpaeumLibrary opaeumLibrary){
		super(opaeumLibrary.getResourceSet());
		this.context=context;
		this.library=opaeumLibrary;
	}
	@Override
	public Environment<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> createEnvironment(
			Environment<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> parent){
		if(!(parent instanceof UMLEnvironment)){
			throw new IllegalArgumentException("Parent environment must be a UML environment: " + parent); //$NON-NLS-1$
		}
		OpaeumEnvironment result = new OpaeumEnvironment(context, parent,library,variables);
		result.setFactory(this);
		return result;
	}


	public void addVariables(Map<String,Classifier> variables2){
		for(Entry<String,Classifier> entry:variables2.entrySet()){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(entry.getValue());
			var.setName(entry.getKey());
			variables.add(var);
		}
	}
}