package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;

public final class NakedUmlEnvironment extends UMLEnvironment{
	@Override
	public Variable<Classifier,Parameter> lookup(String name){
		// TODO Auto-generated method stub
		return super.lookup(name);
	}
	@Override
	public Collection<Variable<Classifier,Parameter>> getVariables(){
		return super.getVariables();
	}
	@Override
	public Package getContextPackage(){
		return super.getContextPackage();
	}
	@Override
	public void setSelfVariable(Variable<Classifier,Parameter> var){
		super.setSelfVariable(var);
	}
	@Override
	public List<Property> getAdditionalAttributes(Classifier c){
		List<Property> additionalAttributes = new ArrayList<Property>( super.getAdditionalAttributes(c));
		for(Association a:c.getAssociations()){
			for(Property end:a.getNavigableOwnedEnds()){
				if(end.getOtherEnd().getType().equals(c)){
					additionalAttributes.add(end);
				}
			}
		}
		return additionalAttributes;
	}
	public NakedUmlEnvironment(EPackage.Registry registry,ResourceSet rset){
		super(registry, rset);
	}
	public void setFactory(EnvironmentFactory<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> d){
		super.setFactory(d);
	}
}