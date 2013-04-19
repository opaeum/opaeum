package org.opaeum.test.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		putClass(org.opaeum.test.Aunt.class,"Structures.uml@_edqnoIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Brother.class,"Structures.uml@_bzlKgIhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Child.class,"Structures.uml@_V2hysIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.ChildHasRelation.class,"Structures.uml@_I7GooIhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Cousin.class,"Structures.uml@_htfZoIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Family.class,"Structures.uml@_TL7NoIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.FamilyHasFamilyMember.class,"Structures.uml@_N7WfII08EeKHBNiW4NWnIg");
		putClass(org.opaeum.test.FamilyMember.class,"Structures.uml@_uAFMoIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.FamilyMemberHasRelation.class,"Structures.uml@_wPOkwIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.FamilyStepChild.class,"Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg");
		putClass(org.opaeum.test.Father.class,"Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw");
		putClass(org.opaeum.test.Marriage.class,"Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg");
		putClass(org.opaeum.test.Mother.class,"Structures.uml@_YergsIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.MotherStepChildren.class,"Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw");
		putClass(org.opaeum.test.Relation.class,"Structures.uml@_bVPeIIhqEeK4s7QGypAJBA");
		putClass(org.opaeum.test.SiblingStepSibling.class,"Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw");
		putClass(org.opaeum.test.Sister.class,"Structures.uml@_dS3zAIhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Spouse.class,"Structures.uml@_wqZp8IjPEeKq68owPnlvHg");
		putClass(org.opaeum.test.StepBrother.class,"Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg");
		putClass(org.opaeum.test.StepChild.class,"Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg");
		putClass(org.opaeum.test.StepSister.class,"Structures.uml@_r-BmsIlZEeKhILqZBrW9Hg");
		putClass(org.opaeum.test.SurnameProvider.class,"Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.SurnameProviderHasDaughter.class,"Structures.uml@_gtNy8IhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.SurnameProviderHasSon.class,"Structures.uml@_g-YbcIhrEeK4s7QGypAJBA");
		putClass(org.opaeum.test.Uncle.class,"Structures.uml@_dH6VoIhqEeK4s7QGypAJBA");
	}


}