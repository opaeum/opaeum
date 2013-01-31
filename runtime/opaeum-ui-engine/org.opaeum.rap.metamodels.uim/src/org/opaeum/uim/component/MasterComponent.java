package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;

public interface MasterComponent extends EObject {
	public List<DetailComponent> getDetailComponents();
	
	public void setDetailComponents(List<DetailComponent> detailComponents);

}