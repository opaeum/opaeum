package org.opaeum.papyrus;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.opaeum.eclipse.newchild.RelationshipDirection;

public class RelationshipExtractor extends UMLSwitch<Object>{
	private Set<Element> relationships = new HashSet<Element>();
	private Set<Element> nodeElements = new HashSet<Element>();
	private EClass[] relationshipTypes;
	private RelationshipDirection direction;
	public RelationshipExtractor(EClass[] relationshipTypes,RelationshipDirection d){
		this.relationshipTypes = relationshipTypes;
		this.direction = d;
	}
	private boolean forward(){
		return direction == RelationshipDirection.BOTH || direction == RelationshipDirection.FORWARD;
	}
	private boolean backward(){
		return direction == RelationshipDirection.BOTH || direction == RelationshipDirection.BACKWARD;
	}
	@Override
	public Object casePackage(Package object){
		for(EClass eClass:this.relationshipTypes){
			if(eClass == UMLPackage.eINSTANCE.getPackageImport()){
				if(backward()){
					EList<DirectedRelationship> targetDirectedRelationships = object.getTargetDirectedRelationships(UMLPackage.eINSTANCE.getPackageImport());
					getRelationships().addAll(targetDirectedRelationships);
					for(DirectedRelationship d:targetDirectedRelationships){
						nodeElements.addAll(d.getRelatedElements());
					}
				}
			}
		}
		return super.casePackage(object);
	}
	@Override
	public Object caseNamespace(Namespace object){
		for(EClass eClass:this.relationshipTypes){
			if(eClass == UMLPackage.eINSTANCE.getPackageImport()){
				if(forward()){
					EList<PackageImport> packageImports = object.getPackageImports();
					getRelationships().addAll(packageImports);
					for(PackageImport pi:packageImports){
						nodeElements.addAll(pi.getRelatedElements());
					}
				}
			}
		}
		return super.caseNamespace(object);
	}
	@Override
	public Object caseClassifier(Classifier object){
		EList<Association> associations = object.getAssociations();
		getRelationships().addAll(associations);
		for(Association association:associations){
			nodeElements.addAll(association.getEndTypes());
		}
		//TODO navigability
		return super.caseClassifier(object);
	}
	public Set<Element> getRelationships(){
		return relationships;
	}
	public Set<Element> getNodeElements(){
		return nodeElements;
	}
}
