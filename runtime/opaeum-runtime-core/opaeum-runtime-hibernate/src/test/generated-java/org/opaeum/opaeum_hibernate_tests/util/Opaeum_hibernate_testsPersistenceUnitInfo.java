package org.opaeum.opaeum_hibernate_tests.util;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.runtime.jpa.AbstractPersistenceUnitInfo;

public class Opaeum_hibernate_testsPersistenceUnitInfo extends AbstractPersistenceUnitInfo {


	/** Constructor for Opaeum_hibernate_testsPersistenceUnitInfo
	 * 
	 * @param env 
	 */
	public Opaeum_hibernate_testsPersistenceUnitInfo(Opaeum_hibernate_testsEnvironment env) {
	super(env);
	}

	public List<String> getManagedClassNames() {
		List result = new ArrayList<String>();
		result.add("org.opaeum.hibernate.domain.EventOccurrence");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentEnum");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum");
		result.add("org.opaeum.opaeum_hibernate_tests.util");
		result.add("org.opaeum.test.hibernate.Hand");
		result.add("org.opaeum.test.hibernate.Finger");
		result.add("org.opaeum.test.Family");
		result.add("org.opaeum.test.Child");
		result.add("org.opaeum.test.Mother");
		result.add("org.opaeum.test.Uncle");
		result.add("org.opaeum.test.Aunt");
		result.add("org.opaeum.test.Cousin");
		result.add("org.opaeum.test.FamilyMemberHasRelation");
		result.add("org.opaeum.test.ChildHasRelation");
		result.add("org.opaeum.test.Brother");
		result.add("org.opaeum.test.Sister");
		result.add("org.opaeum.test.SurnameProviderHasDaughter");
		result.add("org.opaeum.test.SurnameProviderHasSon");
		result.add("org.opaeum.test.Father");
		result.add("org.opaeum.test.StepBrother");
		result.add("org.opaeum.test.StepSister");
		result.add("org.opaeum.test.FamilyStepChild");
		result.add("org.opaeum.test.Marriage");
		result.add("org.opaeum.test.FamilyHasFamilyMember");
		result.add("org.opaeum.test.MotherStepChildren");
		result.add("org.opaeum.test.SiblingStepSibling");
		return result;
	}
	
	public String getPersistenceUnitName() {
		String result = "opaeum_hibernate_tests";
		
		return result;
	}

}