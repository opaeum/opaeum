package model.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		putClass(model.Aunt.class,"Structures.uml@_edqnoIhqEeK4s7QGypAJBA");
		putClass(model.Brother.class,"Structures.uml@_bzlKgIhrEeK4s7QGypAJBA");
		putClass(model.Child.class,"Structures.uml@_V2hysIhqEeK4s7QGypAJBA");
		putClass(model.Cousin.class,"Structures.uml@_htfZoIhqEeK4s7QGypAJBA");
		putClass(model.Family.class,"Structures.uml@_TL7NoIhqEeK4s7QGypAJBA");
		putClass(model.FamilyMember.class,"Structures.uml@_uAFMoIhqEeK4s7QGypAJBA");
		putClass(model.FamilyMemberHasRelation.class,"Structures.uml@_wPOkwIhqEeK4s7QGypAJBA");
		putClass(model.Father.class,"Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw");
		putClass(model.Mother.class,"Structures.uml@_YergsIhqEeK4s7QGypAJBA");
		putClass(model.Relation.class,"Structures.uml@_bVPeIIhqEeK4s7QGypAJBA");
		putClass(model.Sister.class,"Structures.uml@_dS3zAIhrEeK4s7QGypAJBA");
		putClass(model.Spouse.class,"Structures.uml@_wqZp8IjPEeKq68owPnlvHg");
		putClass(model.SurnameProvider.class,"Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA");
		putClass(model.SurnameProviderHasDaughter.class,"Structures.uml@_gtNy8IhrEeK4s7QGypAJBA");
		putClass(model.SurnameProviderHasSon.class,"Structures.uml@_g-YbcIhrEeK4s7QGypAJBA");
		putClass(model.Uncle.class,"Structures.uml@_dH6VoIhqEeK4s7QGypAJBA");
	}


}