package org.opaeum.test.structure;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Assert;

import org.junit.Test;
import org.opaeum.test.Brother;
import org.opaeum.test.Family;
import org.opaeum.test.Father;
import org.opaeum.test.Mother;
import org.opaeum.test.Sister;
import org.opaeum.test.StepBrother;
import org.opaeum.test.StepSister;
import org.w3c.dom.Document;

public class XmlTests {
	@Test
	public void testit() throws Exception {
		Family family = new Family();
		Father father = new Father();
		family.setFather(father);
		Mother mother = new Mother();
		family.setMother(mother);
		mother.setHusband(father);
		// Compositional non association class oneToMany
		Brother brother = new Brother(family, "Pete");
		Sister sister = new Sister(family, "Sue");
		// Compositional association class oneToMany
		StepBrother stepBrother = new StepBrother(family, "StepPete");
		StepSister stepSister = new StepSister(family, "StepSue");
		// Non compositional association class
		brother.addToStepSibling(stepBrother);
		String xmlString = family.toXmlString();
		Family loadedFamily = new Family();
		Map<String, Object> map = new HashMap<String, Object>();
		Document xml = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(new ByteArrayInputStream(xmlString.getBytes()));
		loadedFamily.buildTreeFromXml(xml.getDocumentElement(), map);
		loadedFamily.populateReferencesFromXml(xml.getDocumentElement(), map);
		Assert.assertNotNull(loadedFamily.getFather());
		Assert.assertNotNull(loadedFamily.getMother());
		Assert.assertSame(loadedFamily.getFather(), loadedFamily.getMother()
				.getHusband());
		Assert.assertTrue(loadedFamily.getChild("Pete") instanceof Brother);
		Assert.assertEquals("Pete", loadedFamily.getChild("Pete").getName());
		Assert.assertTrue(loadedFamily.getChild("Sue") instanceof Sister);
		Assert.assertTrue(loadedFamily.getStepChild("StepSue") instanceof StepSister);
		Assert.assertTrue(loadedFamily.getStepChild("StepPete")
				.getStepSibling().contains(loadedFamily.getChild("Pete")));
	}
}
