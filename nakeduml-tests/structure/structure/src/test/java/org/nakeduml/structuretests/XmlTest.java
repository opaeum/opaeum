package org.nakeduml.structuretests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.environment.MockEnvironment;
import org.nakeduml.structuretests.test2.StructureApplication;
import org.nakeduml.structuretests.test2.StructuredBusinessUnit;
import org.nakeduml.structuretests.test2.structuredbusinessunit.BusinessUnitKind;
import org.nakeduml.structuretests.test2.structuredbusinessunit.Employee;
import org.nakeduml.structuretests.test2.structuredbusinessunit.Employer;
import org.nakeduml.structuretests.test2.structuredbusinessunit.Employment;
import org.nakeduml.structuretests.test2.structuredbusinessunit.EmploymentKind;
import org.nakeduml.structuretests.test2.structuredbusinessunit.Folder;
import org.nakeduml.structuretests.test2.structuredbusinessunit.Manager;
import org.nakeduml.structuretests.test2.structuredbusinessunit.RootFolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlTest {
	private StructuredBusinessUnit businessUnit;
	private Employment employment1;
	private Manager manager1;
	private Employee employee1;
	private Employer employer;

	@Test
	public void testHierarchy(){
		this.businessUnit = new StructuredBusinessUnit();
		RootFolder rootFolder1 = new RootFolder(businessUnit);
		RootFolder rootFolder2 = new RootFolder(businessUnit);
		Folder folder1_1 = new Folder(rootFolder1);
		Folder folder1_2 = new Folder(rootFolder1);
		Folder folder2_1 = new Folder(rootFolder2);
		Folder folder2_2 = new Folder(rootFolder2);
		Folder folder1_1_1 = new Folder(folder1_1);
		Folder folder1_2_1 = new Folder(folder1_2);
		Folder folder2_1_1 = new Folder(folder2_1);
		Folder folder2_2_1 = new Folder(folder2_2);
		Folder folder1_1_2 = new Folder(folder1_1);
		Folder folder1_2_2 = new Folder(folder1_2);
		Folder folder2_1_2 = new Folder(folder2_1);
		Folder folder2_2_2 = new Folder(folder2_2);
		HashMap<String, IPersistentObject> map = new HashMap<String, IPersistentObject>();
		Element root = parse(businessUnit.toXmlString()).getDocumentElement();
		StructuredBusinessUnit parsedBu =new StructuredBusinessUnit();
		
		parsedBu.buildTreeFromXml(root, map);
		parsedBu.populateReferencesFromXml(root, map);
		Assert.assertEquals(businessUnit.toXmlString(), parsedBu.toXmlString());
		
	}

	@Test
	public void testReferences() {
		this.businessUnit = new StructuredBusinessUnit();
		this.employer = new Employer();
		businessUnit.addToEmployer(employer);
		this.employee1 = new Employee(businessUnit);
		Employee employee2 = new Employee(businessUnit);
		manager1 = new Manager(businessUnit);
		Manager manager2 = new Manager(businessUnit);
		employee1.setCurrentManager(manager1);
		employer.addToEmployee(employee1);
		this.employment1 = employer.getEmployment_employeeFor(employee1);
		employment1.setEmploymentKind(EmploymentKind.PERMANENT);
		employment1.setFromDate(new Date());
		employment1.setToDate(new Date(System.currentTimeMillis() + 1000000));
		employer.addToEmployee(employee2);
		Employment employment2 = employer.getEmployment_employeeFor(employee2);
		manager1.addToEmployment(employment1);
		manager1.addToEmployment(employment2);
		manager2.addToEmployment(employment1);
		String xmlString = businessUnit.toXmlString();
		System.out.println(xmlString);
		Element root = this.parse(xmlString).getDocumentElement();
		assertBusinessUnitEquals(root);
	}

	@Test
	public void testMigration() throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		Document doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getClass().getResourceAsStream("/oldBusinessUnit.xml"));
		Document doc2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getClass().getResourceAsStream("/newBusinessUnit.xml"));
		this.businessUnit = new StructuredBusinessUnit();
		HashMap<String, IPersistentObject> map = new HashMap<String, IPersistentObject>();
		businessUnit.buildTreeFromXml(doc1.getDocumentElement(), map);

		businessUnit.populateReferencesFromXml(doc1.getDocumentElement(), map);
		manager1 = (Manager) map.get("db601fd8-e405-4fa5-af2c-46fc56ba4ee5");
		employee1 = (Employee) map.get("3935d25f-67a5-45e1-aa0a-e99d2b8415cd");
		employer = (Employer) map.get("aecc966c-039b-4345-83b5-cfbdbf909126");
		employment1 = (Employment) map.get("9aeb1dfd-6db7-4d36-b4bd-df30ef90e6f5");
		assertBusinessUnitEquals(doc2.getDocumentElement());
	}

	private void assertBusinessUnitEquals(Element root) {
		StructuredBusinessUnit buParsed = new StructuredBusinessUnit();
		HashMap<String, IPersistentObject> map = new HashMap<String, IPersistentObject>();
		buParsed.buildTreeFromXml(root, map);
		buParsed.populateReferencesFromXml(root, map);
		System.out.println(buParsed.toXmlString());
		Assert.assertEquals(businessUnit, buParsed);
		Assert.assertEquals(businessUnit.getTest2BusinessService().size(), buParsed.getTest2BusinessService().size());
		Assert.assertTrue(businessUnit.getTest2BusinessService().containsAll(buParsed.getTest2BusinessService()));
		Employer employerParsed = (Employer) map.get(employer.getUid());
		Assert.assertEquals(employer.getEmployment_employee().size(), employerParsed.getEmployment_employee().size());
		Assert.assertTrue(employer.getEmployment_employee().containsAll(employerParsed.getEmployment_employee()));
		Assert.assertEquals(employer.getEmployee().size(), employerParsed.getEmployee().size());
		Assert.assertTrue(employer.getEmployee().containsAll(employerParsed.getEmployee()));
		Manager manager1Parsed = (Manager) map.get(manager1.getUid());
		Assert.assertTrue(manager1Parsed.getManagedEmployee().contains(employee1));
		Assert.assertEquals(this.manager1.getEmployment().size(), manager1Parsed.getEmployment().size());
		Assert.assertTrue(manager1.getEmployment().containsAll(manager1Parsed.getEmployment()));
		Employment employment1Parsed = (Employment) map.get(employment1.getUid());
		Assert.assertEquals(employment1Parsed.getEmployee(), this.employment1.getEmployee());
		Assert.assertEquals(employment1Parsed.getEmployer(), employment1.getEmployer());
		Assert.assertEquals(employment1.getManager().size(), employment1Parsed.getManager().size());
		Assert.assertTrue(employment1.getManager().containsAll(employment1Parsed.getManager()));
	}

	@Before()
	public void setup() {
		MockEnvironment.getInstance();
	}

	@Test
	public void testBasicTreeStructure() throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		MockEnvironment.getInstance();
		StructureApplication app = new StructureApplication();
		app.setActive(true);
		app.setName("App");
		app.setAllowedNames(new HashSet<String>(Arrays.asList("john", "peter")));
		app.setAllowedBusinessUnitKinds(new HashSet<BusinessUnitKind>(Arrays.asList(BusinessUnitKind.FACILITIES, BusinessUnitKind.ACCOUNTING)));
		StructuredBusinessUnit bu = new StructuredBusinessUnit(app);
		bu.setKind(BusinessUnitKind.ACCOUNTING);
		bu.setName("Accounting");
		StructuredBusinessUnit bu2 = new StructuredBusinessUnit(app);
		bu2.setKind(BusinessUnitKind.FACILITIES);
		bu2.setName("Facilities");
		String xmlString = app.toXmlString();
		System.out.println(xmlString);
		Document doc = parse(xmlString);
		StructureApplication parsed = new StructureApplication();
		HashMap<String, IPersistentObject> map = new HashMap<String, IPersistentObject>();
		parsed.buildTreeFromXml(doc.getDocumentElement(), map);
		Assert.assertEquals(app.getUid(), parsed.getUid());
		Assert.assertEquals(app.getActive(), parsed.getActive());
		Assert.assertEquals(app.getName(), parsed.getName());
		Assert.assertEquals(app.getAllowedNames().size(), parsed.getAllowedNames().size());
		Assert.assertTrue(app.getAllowedNames().containsAll(parsed.getAllowedNames()));
		Assert.assertEquals(app.getAllowedBusinessUnitKinds().size(), parsed.getAllowedBusinessUnitKinds().size());
		Assert.assertTrue(app.getAllowedBusinessUnitKinds().containsAll(parsed.getAllowedBusinessUnitKinds()));
		Assert.assertEquals(app.getBusinessUnit().size(), parsed.getBusinessUnit().size());
		Assert.assertTrue(app.getBusinessUnit().containsAll(parsed.getBusinessUnit()));
		StructuredBusinessUnit parsedBu = (StructuredBusinessUnit) map.get(bu.getUid());
		Assert.assertEquals(bu.getUid(), parsedBu.getUid());
		Assert.assertEquals(bu.getName(), parsedBu.getName());
		Assert.assertEquals(bu.getKind(), parsedBu.getKind());
	}

	private Document parse(String xmlString) {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlString.getBytes()));
			return doc;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
