package org.opaeum.structuretests;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.opaeum.runtime.bpm.businesscalendar.impl.BusinessCalendar;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opaeum.runtime.jpa.StandaloneJpaUmtPersistence;
import org.opaeum.structuretests.test2.StructuredBusinessUnit;
import org.opaeum.structuretests.test2.structuredbusinessunit.Employee;
import org.opaeum.structuretests.test2.structuredbusinessunit.Employer;
import org.opaeum.structuretests.test2.structuredbusinessunit.Employment;
import org.opaeum.structuretests.test2.structuredbusinessunit.Manager;

public class JpaTest {
	@After
	public void reset() {
		StandaloneJpaEnvironment.getInstance().reset();
	}

	@Test
	public void testAssocationClassAndManyToAny() {
		StandaloneJpaUmtPersistence p = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		p.beginTransaction();
		StructuredBusinessUnit bu = new StructuredBusinessUnit();
		Employer employer = new Employer(bu);
		Employee employee = new Employee(bu);
		new Employee(bu);
		employer.addToEmployee(employee);
		Employment employment = employer.getEmployment_employeeFor(employee);
		Manager manager = new Manager(bu);
		Assert.assertEquals(3, bu.getStructuredBusinessUnitTest2BusinessService_test2BusinessService().size());
		Assert.assertEquals(3,bu.getTest2BusinessService().size());
		p.persist(bu);
		employment.addToManager(manager);
		p.commitTransaction();
		p.close();
		StandaloneJpaEnvironment.getInstance().reset();
		p = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		p.beginTransaction();
		StructuredBusinessUnit loadedBu = p.find(StructuredBusinessUnit.class, bu.getId());
		Employer loadedEmployer = p.find(Employer.class, employer.getId());
		Assert.assertEquals(employer, loadedEmployer);
		Employee loadedEmployee = p.find(Employee.class, employee.getId());
		Assert.assertEquals(employee, loadedEmployee);
		Assert.assertTrue(loadedEmployee.getEmployer().contains(loadedEmployer));
		Assert.assertTrue(loadedEmployer.getEmployee().contains(loadedEmployee));
		Assert.assertEquals(employment, loadedEmployee.getEmployment_employerFor(loadedEmployer));
		Employment loadedEmployment = loadedEmployer.getEmployment_employeeFor(loadedEmployee);
		Assert.assertEquals(employment, loadedEmployment);
		Manager loadedManager = p.find(Manager.class, manager.getId());
		Assert.assertEquals(manager, loadedManager);
		Assert.assertTrue(loadedManager.getEmployment().contains(loadedEmployment));
		Assert.assertEquals(3,loadedBu.getStructuredBusinessUnitTest2BusinessService_test2BusinessService().size());
		Assert.assertEquals(3,loadedBu.getTest2BusinessService().size());
		
		// Now remove
		loadedManager.getStructuredBusinessUnit().removeFromTest2BusinessService(manager);
		p.commitTransaction();
		p.close();
		StandaloneJpaEnvironment.getInstance().reset();
		p = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		p.beginTransaction();
		Assert.assertEquals(2,loadedBu.getTest2BusinessService().size());

	}

	@Test
	public void testInterfaceCompositeOnToClassOne() {
		StandaloneJpaUmtPersistence p = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		p.beginTransaction();
		StructuredBusinessUnit bu = new StructuredBusinessUnit();
		BusinessCalendar bc = new BusinessCalendar();
		bc.setBusinessHoursPerWeek(123d);
		bu.setBusinessCalendar(bc);
		p.persist(bu);
		p.commitTransaction();
		p.close();
		StandaloneJpaEnvironment.getInstance().reset();
		p = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		p.beginTransaction();
		StructuredBusinessUnit loadedBu = p.find(StructuredBusinessUnit.class, bu.getId());
		Assert.assertEquals(bu, loadedBu);
		Assert.assertEquals(123d, loadedBu.getBusinessCalendar().getBusinessHoursPerWeek());
		p.close();
		StandaloneJpaEnvironment.getInstance().reset();
	}
}
