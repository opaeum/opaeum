package org.opaeum.textmetamodel;

public enum ProjectNameStrategy{
	WORKSPACE_NAME_AND_SUFFIX{

		@Override
		public String generateProjectName(String projectQualifier, String workspaceIdentifier, String qualifiedWorkspaceIdentifier, String modelIdentifier){
			return workspaceIdentifier + projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier, String workspaceIdentifier){
			return sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return true;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return false;
		}
	},
	MODEL_NAME_AND_SUFFIX{

		@Override
		public String generateProjectName(String projectQualifier,String workspaceIdentifier,String qualifiedWorkspaceIdentifier, String modelIdentifier){
			return modelIdentifier+projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier,String modelIdentifier){
			return sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return false;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return false;
		}
	},
	SUFFIX_ONLY{

		@Override
		public String generateProjectName(String projectQualifier,String workspaceIdentifier,String qualifiedWorkspaceIdentifier, String modelIdentifier){
			return projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier,String modelIdentifier){
			return sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return true;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return false;
		}
	},
	WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER{

		@Override
		public String generateProjectName(String projectQualifier,String workspaceIdentifier,String qualifiedWorkspaceIdentifier, String modelIdentifier){
			return workspaceIdentifier + projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier,String modelIdentifier){
			return modelIdentifier+"/"+sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return true;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return true;
		}
	},
	QUALIFIED_WORKSPACE_NAME_AND_SUFFIX{

		@Override
		public String generateProjectName(String projectQualifier,String workspaceIdentifier,String qualifiedWorkspaceIdentifier,
				String modelIdentifier){
			return qualifiedWorkspaceIdentifier+projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier,String modelIdentifier){
			return sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return false;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return false;
		}
	},
	QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER{

		@Override
		public String generateProjectName(String projectQualifier,String workspaceIdentifier,String qualifiedWorkspaceIdentifier,
				String modelIdentifier){
			return qualifiedWorkspaceIdentifier+projectQualifier;
		}

		@Override
		public String sourceFolderName(String sourceFolderQualifier,String modelIdentifier){
			return modelIdentifier+"/" + sourceFolderQualifier;
		}

		@Override
		public boolean isOneProjectPerWorkspace(){
			return true;
		}

		@Override
		public boolean definesSeparateSourceFoldersForModels(){
			return true;
		}
	};
	public abstract String generateProjectName(String projectQualifier, String workspaceIdentifier, String qualifiedWorkspaceIdentifier, String modelIdentifier);
	public abstract String sourceFolderName(String sourceFolderQualifier,String modelIdentifier);
	public abstract boolean isOneProjectPerWorkspace();
	public abstract boolean definesSeparateSourceFoldersForModels();
}
