package net.sf.nakeduml.metamodel.mapping;


public interface IWorkspaceMappingInfo {
	IMappingInfo getMappingInfo(String modelId, boolean b);
	void store();
	void incrementRevision();
	int getCurrentRevision();
	void setCurrentRevision(int currentRevision);
	float getCurrentVersion();
	void setCurrentVersion(float currentVersion);

}