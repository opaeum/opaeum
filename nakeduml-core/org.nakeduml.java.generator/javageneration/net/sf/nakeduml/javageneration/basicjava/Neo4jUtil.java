package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJPathName;

public class Neo4jUtil {

	public static String constructCompositeRelationshipName(INakedProperty prop) {
		INakedProperty composite = prop.isComposite()?prop:prop.getOtherEnd();
		INakedProperty other = composite.getOtherEnd();
		String relationshipName = composite.getOwner().getMappingInfo().getQualifiedUmlName();
		relationshipName += prop.getAssociation().getName();
		relationshipName += other.getOwner().getMappingInfo().getQualifiedUmlName();
		return relationshipName;
	}

	public static String constructSingleRelationshipToCompositeParent(INakedProperty prop, boolean incoming) {
		return "Relationship rel = this.underlyingNode.getSingleRelationship(new RelationshipType(){public String name() {return \""
				+ Neo4jUtil.constructCompositeRelationshipName(prop.isComposite() ? prop : prop.getOtherEnd()) + "\";}}, Direction."
				+ (incoming ? "INCOMING" : "OUTGOING") + ")";
	}
	
	public static String constructSingleRelationshipToReferenceNode(INakedEntity root) {
		return "Relationship rel = this.underlyingNode.getSingleRelationship(new RelationshipType(){public String name() {return \""
				+ root.getMappingInfo().getQualifiedUmlName() + "\";}}, Direction.INCOMING)";
	}
	
	public static void addNeoToImports(OJClass clazz) {
		clazz.addToImports(new OJPathName("org.neo4j.graphdb.Relationship"));
		clazz.addToImports(new OJPathName("org.neo4j.graphdb.RelationshipType"));
		clazz.addToImports(new OJPathName("org.neo4j.graphdb.Direction"));
	}

}
