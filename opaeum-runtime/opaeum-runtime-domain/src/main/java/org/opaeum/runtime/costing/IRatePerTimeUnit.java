package org.opaeum.runtime.costing;

import java.util.Date;

import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IRatePerTimeUnit extends IPersistentObject,CompositionNode{
	BusinessTimeUnit getTimeUnit();
	Double getRatePaidByCompany();
	Double getRatePaidByCustomer();
	Double getAdditionalCostToCompany();
	Date getEffectiveFrom();
}
