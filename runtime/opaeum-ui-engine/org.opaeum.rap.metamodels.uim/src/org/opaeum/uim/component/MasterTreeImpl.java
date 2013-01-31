package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;

public class MasterTreeImpl implements MasterTree {
	private List<DetailComponent> detailComponents;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<DetailComponent> getDetailComponents() {
		return this.detailComponents;
	}
	
	public void setDetailComponents(List<DetailComponent> detailComponents) {
		this.detailComponents=detailComponents;
	}

}