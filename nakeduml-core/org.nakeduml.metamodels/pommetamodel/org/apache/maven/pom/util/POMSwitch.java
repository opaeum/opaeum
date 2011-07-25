/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom.util;

import org.apache.maven.pom.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.apache.maven.pom.POMPackage
 * @generated
 */
public class POMSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static POMPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public POMSwitch() {
		if (modelPackage == null) {
			modelPackage = POMPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case POMPackage.ACTIVATION: {
				Activation activation = (Activation)theEObject;
				T result = caseActivation(activation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ACTIVATION_FILE: {
				ActivationFile activationFile = (ActivationFile)theEObject;
				T result = caseActivationFile(activationFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ACTIVATION_OS: {
				ActivationOS activationOS = (ActivationOS)theEObject;
				T result = caseActivationOS(activationOS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ACTIVATION_PROPERTY: {
				ActivationProperty activationProperty = (ActivationProperty)theEObject;
				T result = caseActivationProperty(activationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.BUILD: {
				Build build = (Build)theEObject;
				T result = caseBuild(build);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.BUILD_BASE: {
				BuildBase buildBase = (BuildBase)theEObject;
				T result = caseBuildBase(buildBase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CI_MANAGEMENT: {
				CiManagement ciManagement = (CiManagement)theEObject;
				T result = caseCiManagement(ciManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONFIGURATION_TYPE: {
				ConfigurationType configurationType = (ConfigurationType)theEObject;
				T result = caseConfigurationType(configurationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONFIGURATION_TYPE1: {
				ConfigurationType1 configurationType1 = (ConfigurationType1)theEObject;
				T result = caseConfigurationType1(configurationType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONFIGURATION_TYPE2: {
				ConfigurationType2 configurationType2 = (ConfigurationType2)theEObject;
				T result = caseConfigurationType2(configurationType2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONFIGURATION_TYPE3: {
				ConfigurationType3 configurationType3 = (ConfigurationType3)theEObject;
				T result = caseConfigurationType3(configurationType3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONFIGURATION_TYPE4: {
				ConfigurationType4 configurationType4 = (ConfigurationType4)theEObject;
				T result = caseConfigurationType4(configurationType4);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONTRIBUTOR: {
				Contributor contributor = (Contributor)theEObject;
				T result = caseContributor(contributor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.CONTRIBUTORS_TYPE: {
				ContributorsType contributorsType = (ContributorsType)theEObject;
				T result = caseContributorsType(contributorsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCIES_TYPE: {
				DependenciesType dependenciesType = (DependenciesType)theEObject;
				T result = caseDependenciesType(dependenciesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCIES_TYPE1: {
				DependenciesType1 dependenciesType1 = (DependenciesType1)theEObject;
				T result = caseDependenciesType1(dependenciesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCIES_TYPE2: {
				DependenciesType2 dependenciesType2 = (DependenciesType2)theEObject;
				T result = caseDependenciesType2(dependenciesType2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCIES_TYPE3: {
				DependenciesType3 dependenciesType3 = (DependenciesType3)theEObject;
				T result = caseDependenciesType3(dependenciesType3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCY: {
				Dependency dependency = (Dependency)theEObject;
				T result = caseDependency(dependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPENDENCY_MANAGEMENT: {
				DependencyManagement dependencyManagement = (DependencyManagement)theEObject;
				T result = caseDependencyManagement(dependencyManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEPLOYMENT_REPOSITORY: {
				DeploymentRepository deploymentRepository = (DeploymentRepository)theEObject;
				T result = caseDeploymentRepository(deploymentRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEVELOPER: {
				Developer developer = (Developer)theEObject;
				T result = caseDeveloper(developer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DEVELOPERS_TYPE: {
				DevelopersType developersType = (DevelopersType)theEObject;
				T result = caseDevelopersType(developersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DISTRIBUTION_MANAGEMENT: {
				DistributionManagement distributionManagement = (DistributionManagement)theEObject;
				T result = caseDistributionManagement(distributionManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXCLUDES_TYPE: {
				ExcludesType excludesType = (ExcludesType)theEObject;
				T result = caseExcludesType(excludesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXCLUSION: {
				Exclusion exclusion = (Exclusion)theEObject;
				T result = caseExclusion(exclusion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXCLUSIONS_TYPE: {
				ExclusionsType exclusionsType = (ExclusionsType)theEObject;
				T result = caseExclusionsType(exclusionsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXECUTIONS_TYPE: {
				ExecutionsType executionsType = (ExecutionsType)theEObject;
				T result = caseExecutionsType(executionsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXTENSION: {
				Extension extension = (Extension)theEObject;
				T result = caseExtension(extension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.EXTENSIONS_TYPE: {
				ExtensionsType extensionsType = (ExtensionsType)theEObject;
				T result = caseExtensionsType(extensionsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.FILTERS_TYPE: {
				FiltersType filtersType = (FiltersType)theEObject;
				T result = caseFiltersType(filtersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.FILTERS_TYPE1: {
				FiltersType1 filtersType1 = (FiltersType1)theEObject;
				T result = caseFiltersType1(filtersType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.GOALS_TYPE: {
				GoalsType goalsType = (GoalsType)theEObject;
				T result = caseGoalsType(goalsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.GOALS_TYPE1: {
				GoalsType1 goalsType1 = (GoalsType1)theEObject;
				T result = caseGoalsType1(goalsType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.INCLUDES_TYPE: {
				IncludesType includesType = (IncludesType)theEObject;
				T result = caseIncludesType(includesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ISSUE_MANAGEMENT: {
				IssueManagement issueManagement = (IssueManagement)theEObject;
				T result = caseIssueManagement(issueManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.LICENSE: {
				License license = (License)theEObject;
				T result = caseLicense(license);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.LICENSES_TYPE: {
				LicensesType licensesType = (LicensesType)theEObject;
				T result = caseLicensesType(licensesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.MAILING_LIST: {
				MailingList mailingList = (MailingList)theEObject;
				T result = caseMailingList(mailingList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.MAILING_LISTS_TYPE: {
				MailingListsType mailingListsType = (MailingListsType)theEObject;
				T result = caseMailingListsType(mailingListsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.MODEL: {
				Model model = (Model)theEObject;
				T result = caseModel(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.MODULES_TYPE: {
				ModulesType modulesType = (ModulesType)theEObject;
				T result = caseModulesType(modulesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.MODULES_TYPE1: {
				ModulesType1 modulesType1 = (ModulesType1)theEObject;
				T result = caseModulesType1(modulesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.NOTIFIER: {
				Notifier notifier = (Notifier)theEObject;
				T result = caseNotifier(notifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.NOTIFIERS_TYPE: {
				NotifiersType notifiersType = (NotifiersType)theEObject;
				T result = caseNotifiersType(notifiersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ORGANIZATION: {
				Organization organization = (Organization)theEObject;
				T result = caseOrganization(organization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.OTHER_ARCHIVES_TYPE: {
				OtherArchivesType otherArchivesType = (OtherArchivesType)theEObject;
				T result = caseOtherArchivesType(otherArchivesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PARENT: {
				Parent parent = (Parent)theEObject;
				T result = caseParent(parent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGIN: {
				Plugin plugin = (Plugin)theEObject;
				T result = casePlugin(plugin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGIN_EXECUTION: {
				PluginExecution pluginExecution = (PluginExecution)theEObject;
				T result = casePluginExecution(pluginExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGIN_MANAGEMENT: {
				PluginManagement pluginManagement = (PluginManagement)theEObject;
				T result = casePluginManagement(pluginManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGIN_REPOSITORIES_TYPE: {
				PluginRepositoriesType pluginRepositoriesType = (PluginRepositoriesType)theEObject;
				T result = casePluginRepositoriesType(pluginRepositoriesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGIN_REPOSITORIES_TYPE1: {
				PluginRepositoriesType1 pluginRepositoriesType1 = (PluginRepositoriesType1)theEObject;
				T result = casePluginRepositoriesType1(pluginRepositoriesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGINS_TYPE: {
				PluginsType pluginsType = (PluginsType)theEObject;
				T result = casePluginsType(pluginsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGINS_TYPE1: {
				PluginsType1 pluginsType1 = (PluginsType1)theEObject;
				T result = casePluginsType1(pluginsType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGINS_TYPE2: {
				PluginsType2 pluginsType2 = (PluginsType2)theEObject;
				T result = casePluginsType2(pluginsType2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PLUGINS_TYPE3: {
				PluginsType3 pluginsType3 = (PluginsType3)theEObject;
				T result = casePluginsType3(pluginsType3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PREREQUISITES: {
				Prerequisites prerequisites = (Prerequisites)theEObject;
				T result = casePrerequisites(prerequisites);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROFILE: {
				Profile profile = (Profile)theEObject;
				T result = caseProfile(profile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROFILES_TYPE: {
				ProfilesType profilesType = (ProfilesType)theEObject;
				T result = caseProfilesType(profilesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROPERTIES_TYPE: {
				PropertiesType propertiesType = (PropertiesType)theEObject;
				T result = casePropertiesType(propertiesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROPERTIES_TYPE1: {
				PropertiesType1 propertiesType1 = (PropertiesType1)theEObject;
				T result = casePropertiesType1(propertiesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROPERTIES_TYPE2: {
				PropertiesType2 propertiesType2 = (PropertiesType2)theEObject;
				T result = casePropertiesType2(propertiesType2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.PROPERTIES_TYPE3: {
				PropertiesType3 propertiesType3 = (PropertiesType3)theEObject;
				T result = casePropertiesType3(propertiesType3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.RELOCATION: {
				Relocation relocation = (Relocation)theEObject;
				T result = caseRelocation(relocation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORTING: {
				Reporting reporting = (Reporting)theEObject;
				T result = caseReporting(reporting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORT_PLUGIN: {
				ReportPlugin reportPlugin = (ReportPlugin)theEObject;
				T result = caseReportPlugin(reportPlugin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORT_SET: {
				ReportSet reportSet = (ReportSet)theEObject;
				T result = caseReportSet(reportSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORT_SETS_TYPE: {
				ReportSetsType reportSetsType = (ReportSetsType)theEObject;
				T result = caseReportSetsType(reportSetsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORTS_TYPE: {
				ReportsType reportsType = (ReportsType)theEObject;
				T result = caseReportsType(reportsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORTS_TYPE1: {
				ReportsType1 reportsType1 = (ReportsType1)theEObject;
				T result = caseReportsType1(reportsType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPORTS_TYPE2: {
				ReportsType2 reportsType2 = (ReportsType2)theEObject;
				T result = caseReportsType2(reportsType2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPOSITORIES_TYPE: {
				RepositoriesType repositoriesType = (RepositoriesType)theEObject;
				T result = caseRepositoriesType(repositoriesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPOSITORIES_TYPE1: {
				RepositoriesType1 repositoriesType1 = (RepositoriesType1)theEObject;
				T result = caseRepositoriesType1(repositoriesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPOSITORY: {
				Repository repository = (Repository)theEObject;
				T result = caseRepository(repository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.REPOSITORY_POLICY: {
				RepositoryPolicy repositoryPolicy = (RepositoryPolicy)theEObject;
				T result = caseRepositoryPolicy(repositoryPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.RESOURCES_TYPE: {
				ResourcesType resourcesType = (ResourcesType)theEObject;
				T result = caseResourcesType(resourcesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.RESOURCES_TYPE1: {
				ResourcesType1 resourcesType1 = (ResourcesType1)theEObject;
				T result = caseResourcesType1(resourcesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ROLES_TYPE: {
				RolesType rolesType = (RolesType)theEObject;
				T result = caseRolesType(rolesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.ROLES_TYPE1: {
				RolesType1 rolesType1 = (RolesType1)theEObject;
				T result = caseRolesType1(rolesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.SCM: {
				Scm scm = (Scm)theEObject;
				T result = caseScm(scm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.SITE: {
				Site site = (Site)theEObject;
				T result = caseSite(site);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.TEST_RESOURCES_TYPE: {
				TestResourcesType testResourcesType = (TestResourcesType)theEObject;
				T result = caseTestResourcesType(testResourcesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case POMPackage.TEST_RESOURCES_TYPE1: {
				TestResourcesType1 testResourcesType1 = (TestResourcesType1)theEObject;
				T result = caseTestResourcesType1(testResourcesType1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivation(Activation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationFile(ActivationFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation OS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation OS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationOS(ActivationOS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationProperty(ActivationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Build</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Build</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuild(Build object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Build Base</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Build Base</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuildBase(BuildBase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ci Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ci Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCiManagement(CiManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationType(ConfigurationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationType1(ConfigurationType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Type2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Type2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationType2(ConfigurationType2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Type3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Type3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationType3(ConfigurationType3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Type4</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Type4</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationType4(ConfigurationType4 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contributor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contributor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContributor(Contributor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contributors Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contributors Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContributorsType(ContributorsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependencies Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependencies Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependenciesType(DependenciesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependencies Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependencies Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependenciesType1(DependenciesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependencies Type2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependencies Type2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependenciesType2(DependenciesType2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependencies Type3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependencies Type3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependenciesType3(DependenciesType3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependency(Dependency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependencyManagement(DependencyManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentRepository(DeploymentRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Developer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Developer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeveloper(Developer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Developers Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Developers Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDevelopersType(DevelopersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Distribution Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Distribution Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDistributionManagement(DistributionManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Excludes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Excludes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExcludesType(ExcludesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exclusion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exclusion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExclusion(Exclusion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exclusions Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exclusions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExclusionsType(ExclusionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executions Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionsType(ExecutionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtension(Extension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensions Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensionsType(ExtensionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filters Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filters Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFiltersType(FiltersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filters Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filters Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFiltersType1(FiltersType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goals Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goals Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalsType(GoalsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goals Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goals Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalsType1(GoalsType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Includes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Includes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncludesType(IncludesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueManagement(IssueManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicense(License object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Licenses Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Licenses Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensesType(LicensesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mailing List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mailing List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMailingList(MailingList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mailing Lists Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mailing Lists Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMailingListsType(MailingListsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modules Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modules Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModulesType(ModulesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modules Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modules Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModulesType1(ModulesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotifier(Notifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notifiers Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notifiers Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotifiersType(NotifiersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganization(Organization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Other Archives Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Other Archives Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOtherArchivesType(OtherArchivesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParent(Parent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlugin(Plugin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginExecution(PluginExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginManagement(PluginManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin Repositories Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin Repositories Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginRepositoriesType(PluginRepositoriesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin Repositories Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin Repositories Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginRepositoriesType1(PluginRepositoriesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugins Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugins Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginsType(PluginsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugins Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugins Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginsType1(PluginsType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugins Type2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugins Type2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginsType2(PluginsType2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plugins Type3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugins Type3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePluginsType3(PluginsType3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prerequisites</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prerequisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrerequisites(Prerequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProfile(Profile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Profiles Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Profiles Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProfilesType(ProfilesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesType(PropertiesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesType1(PropertiesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Type2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Type2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesType2(PropertiesType2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Type3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Type3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesType3(PropertiesType3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelocation(Relocation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reporting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reporting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReporting(Reporting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report Plugin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report Plugin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportPlugin(ReportPlugin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportSet(ReportSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report Sets Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report Sets Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportSetsType(ReportSetsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reports Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reports Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportsType(ReportsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reports Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reports Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportsType1(ReportsType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reports Type2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reports Type2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportsType2(ReportsType2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repositories Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repositories Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepositoriesType(RepositoriesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repositories Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repositories Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepositoriesType1(RepositoriesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepository(Repository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepositoryPolicy(RepositoryPolicy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resources Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resources Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourcesType(ResourcesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resources Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resources Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourcesType1(ResourcesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Roles Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Roles Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRolesType(RolesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Roles Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Roles Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRolesType1(RolesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScm(Scm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Site</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Site</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSite(Site object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Resources Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Resources Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestResourcesType(TestResourcesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Resources Type1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Resources Type1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestResourcesType1(TestResourcesType1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //POMSwitch
