package org.opaeum.metamodel.components.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.IPackage;

import org.eclipse.uml2.uml.INakedComponent;
import org.eclipse.uml2.uml.INakedConnector;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedPackage;
import org.eclipse.uml2.uml.INakedPort;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedEntityImpl;

public class NakedComponentImpl extends NakedEntityImpl implements INakedComponent{
	protected Collection<INakedPackage> subPackages = new ArrayList<INakedPackage>();
	protected Collection<INakedConnector> ownedConnectors = new ArrayList<INakedConnector>();
	private boolean isSchema;
	private static final long serialVersionUID = -5658739232216672479L;
	public static final String META_CLASS = "component";
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public boolean isSchema(){
		return this.isSchema;
	}
	public Collection<IPackage> getSubpackages(){
		return new ArrayList<IPackage>(subPackages);
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("isSchema")){
			this.isSchema=stereotype.getFirstValueFor("isSchema").booleanValue();
		}
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedPackage){
			this.subPackages.add((INakedPackage) element);
		}else if(element instanceof INakedConnector){
			this.ownedConnectors.add((INakedConnector) element);
		}
		
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedPackage;
	}
	@Override
	public Collection<INakedPort> getOwnedPorts() {
		return selectPorts(getOwnedAttributes());
	}
	private Collection<INakedPort> selectPorts(
			List<INakedProperty> ownedAttributes2) {
		Collection<INakedPort> result = new ArrayList<INakedPort>();
		for (INakedProperty p : ownedAttributes2) {
			if(p instanceof INakedPort){
				result.add((INakedPort)p);
			}
		}
		return result;
	}
	@Override
	public Collection<INakedPort> getEffectivePorts() {
		return selectPorts(getEffectiveAttributes());
	}
	@Override
	public boolean isOrganizationUnit(){
		Collection<INakedPort> effectivePorts = getEffectivePorts();
		for(INakedPort p:effectivePorts){
			if(p.isBusinessService()){
				return true;
			}
		}
		return false;
	}
	@Override
	public Collection<INakedConnector> getOwnedConnectors(){
		return this.ownedConnectors;
	}
}
