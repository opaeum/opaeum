package org.opaeum.runtime.costing;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IPricePerUnit extends IPersistentObject, CompositionNode{
  Date getEffectiveFrom();
	Double getPricePaidByCompany();

	Double getPricePaidByCustomer();

	Double getAdditionalCostToCompany();
}
