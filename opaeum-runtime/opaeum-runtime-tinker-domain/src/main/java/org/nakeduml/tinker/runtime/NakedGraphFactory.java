package org.nakeduml.tinker.runtime;


public interface NakedGraphFactory {
	NakedGraph getNakedGraph(String url, TinkerSchemaHelper schemaHelper, boolean withSchema);
}
