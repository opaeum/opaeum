package org.opaeum.runtime.costing;

import org.opaeum.runtime.domain.BusinessTimeUnit;

public interface IRatePerTimeUnit{
	BusinessTimeUnit getTimeUnit();
	Double getRatePaidByCompany();
	Double getRatePaidByCustomer();
	Double getAdditionalCostToCompany();
}
