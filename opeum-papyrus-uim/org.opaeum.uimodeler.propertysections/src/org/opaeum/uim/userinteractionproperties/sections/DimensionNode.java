package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.cube.CubeFactory;
import org.opaeum.uim.cube.DimensionBinding;

public class DimensionNode{
	private Property property;
	private Classifier fromClass;
	DimensionNode detail;
	DimensionNode master;
	public DimensionNode(Classifier fromClass,Property p){
		this.property = p;
		this.fromClass = fromClass;
	}
	public DimensionNode(){
	}
	public Classifier getFromClass(){
		return fromClass;
	}
	public DimensionNode linkToInnermostDetail(){
		if(detail == null){
			return this;
		}else{
			detail.master = this;
			return detail.linkToInnermostDetail();
		}
	}
	public DimensionNode getCopy(){
		DimensionNode result = new DimensionNode(fromClass, property);
		if(detail != null){
			result.detail = detail.getCopy();
		}
		return result;
	}
	public PropertyRef toPropertyRef(){
		PropertyRef result = BindingFactory.eINSTANCE.createPropertyRef();
		result.setUmlElementUid(EmfWorkspace.getId(property));
		if(master!= null){
			PropertyRef propertyRef = master.toPropertyRef();
			result.setNext(propertyRef);
			return result;
		}else{
			return result;
		}
	}
	public DimensionBinding toDimensionBinding(){
		DimensionBinding binding = CubeFactory.eINSTANCE.createDimensionBinding();
		binding.setUmlElementUid(EmfWorkspace.getId(property));
		if(master!=null){
			binding.setNext(master.toPropertyRef());
		}
		return binding;
	}
	@Override
	public String toString(){
		return getName();
	}
	Property getProperty(){
		return property;
	}
	public String getName(){
		if(master == null){
			return property.getName() + ":" + property.getType().getName();
		}else{
			return property.getName() + "." + master.getName();
		}
	}
}