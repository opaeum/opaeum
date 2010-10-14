/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom.impl;

import org.apache.maven.pom.Build;
import org.apache.maven.pom.CiManagement;
import org.apache.maven.pom.ContributorsType;
import org.apache.maven.pom.DependenciesType;
import org.apache.maven.pom.DependencyManagement;
import org.apache.maven.pom.DevelopersType;
import org.apache.maven.pom.DistributionManagement;
import org.apache.maven.pom.IssueManagement;
import org.apache.maven.pom.LicensesType;
import org.apache.maven.pom.MailingListsType;
import org.apache.maven.pom.Model;
import org.apache.maven.pom.ModulesType;
import org.apache.maven.pom.Organization;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.Parent;
import org.apache.maven.pom.PluginRepositoriesType;
import org.apache.maven.pom.Prerequisites;
import org.apache.maven.pom.ProfilesType;
import org.apache.maven.pom.PropertiesType2;
import org.apache.maven.pom.Reporting;
import org.apache.maven.pom.ReportsType1;
import org.apache.maven.pom.RepositoriesType;
import org.apache.maven.pom.Scm;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getPackaging <em>Packaging</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getPrerequisites <em>Prerequisites</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getIssueManagement <em>Issue Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getCiManagement <em>Ci Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getInceptionYear <em>Inception Year</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getMailingLists <em>Mailing Lists</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getDevelopers <em>Developers</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getContributors <em>Contributors</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getLicenses <em>Licenses</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getScm <em>Scm</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getBuild <em>Build</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getProfiles <em>Profiles</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getModules <em>Modules</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getPluginRepositories <em>Plugin Repositories</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getReports <em>Reports</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getReporting <em>Reporting</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getDependencyManagement <em>Dependency Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getDistributionManagement <em>Distribution Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ModelImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends EObjectImpl implements Model {
	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected Parent parent;

	/**
	 * The default value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelVersion()
	 * @generated
	 * @ordered
	 */
	protected String modelVersion = MODEL_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String artifactId = ARTIFACT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackaging() <em>Packaging</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackaging()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGING_EDEFAULT = "jar";

	/**
	 * The cached value of the '{@link #getPackaging() <em>Packaging</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackaging()
	 * @generated
	 * @ordered
	 */
	protected String packaging = PACKAGING_EDEFAULT;

	/**
	 * This is true if the Packaging attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean packagingESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrerequisites()
	 * @generated
	 * @ordered
	 */
	protected Prerequisites prerequisites;

	/**
	 * The cached value of the '{@link #getIssueManagement() <em>Issue Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueManagement()
	 * @generated
	 * @ordered
	 */
	protected IssueManagement issueManagement;

	/**
	 * The cached value of the '{@link #getCiManagement() <em>Ci Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCiManagement()
	 * @generated
	 * @ordered
	 */
	protected CiManagement ciManagement;

	/**
	 * The default value of the '{@link #getInceptionYear() <em>Inception Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInceptionYear()
	 * @generated
	 * @ordered
	 */
	protected static final String INCEPTION_YEAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInceptionYear() <em>Inception Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInceptionYear()
	 * @generated
	 * @ordered
	 */
	protected String inceptionYear = INCEPTION_YEAR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMailingLists() <em>Mailing Lists</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMailingLists()
	 * @generated
	 * @ordered
	 */
	protected MailingListsType mailingLists;

	/**
	 * The cached value of the '{@link #getDevelopers() <em>Developers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDevelopers()
	 * @generated
	 * @ordered
	 */
	protected DevelopersType developers;

	/**
	 * The cached value of the '{@link #getContributors() <em>Contributors</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributors()
	 * @generated
	 * @ordered
	 */
	protected ContributorsType contributors;

	/**
	 * The cached value of the '{@link #getLicenses() <em>Licenses</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicenses()
	 * @generated
	 * @ordered
	 */
	protected LicensesType licenses;

	/**
	 * The cached value of the '{@link #getScm() <em>Scm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScm()
	 * @generated
	 * @ordered
	 */
	protected Scm scm;

	/**
	 * The cached value of the '{@link #getOrganization() <em>Organization</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected Organization organization;

	/**
	 * The cached value of the '{@link #getBuild() <em>Build</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild()
	 * @generated
	 * @ordered
	 */
	protected Build build;

	/**
	 * The cached value of the '{@link #getProfiles() <em>Profiles</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfiles()
	 * @generated
	 * @ordered
	 */
	protected ProfilesType profiles;

	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected ModulesType modules;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected RepositoriesType repositories;

	/**
	 * The cached value of the '{@link #getPluginRepositories() <em>Plugin Repositories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginRepositories()
	 * @generated
	 * @ordered
	 */
	protected PluginRepositoriesType pluginRepositories;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected DependenciesType dependencies;

	/**
	 * The cached value of the '{@link #getReports() <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReports()
	 * @generated
	 * @ordered
	 */
	protected ReportsType1 reports;

	/**
	 * The cached value of the '{@link #getReporting() <em>Reporting</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReporting()
	 * @generated
	 * @ordered
	 */
	protected Reporting reporting;

	/**
	 * The cached value of the '{@link #getDependencyManagement() <em>Dependency Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyManagement()
	 * @generated
	 * @ordered
	 */
	protected DependencyManagement dependencyManagement;

	/**
	 * The cached value of the '{@link #getDistributionManagement() <em>Distribution Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionManagement()
	 * @generated
	 * @ordered
	 */
	protected DistributionManagement distributionManagement;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected PropertiesType2 properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return POMPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parent getParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(Parent newParent, NotificationChain msgs) {
		Parent oldParent = parent;
		parent = newParent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PARENT, oldParent, newParent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(Parent newParent) {
		if (newParent != parent) {
			NotificationChain msgs = null;
			if (parent != null)
				msgs = ((InternalEObject)parent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PARENT, null, msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PARENT, null, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModelVersion() {
		return modelVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelVersion(String newModelVersion) {
		String oldModelVersion = modelVersion;
		modelVersion = newModelVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__MODEL_VERSION, oldModelVersion, modelVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupId(String newGroupId) {
		String oldGroupId = groupId;
		groupId = newGroupId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__GROUP_ID, oldGroupId, groupId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifactId(String newArtifactId) {
		String oldArtifactId = artifactId;
		artifactId = newArtifactId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__ARTIFACT_ID, oldArtifactId, artifactId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackaging() {
		return packaging;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackaging(String newPackaging) {
		String oldPackaging = packaging;
		packaging = newPackaging;
		boolean oldPackagingESet = packagingESet;
		packagingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PACKAGING, oldPackaging, packaging, !oldPackagingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPackaging() {
		String oldPackaging = packaging;
		boolean oldPackagingESet = packagingESet;
		packaging = PACKAGING_EDEFAULT;
		packagingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, POMPackage.MODEL__PACKAGING, oldPackaging, PACKAGING_EDEFAULT, oldPackagingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPackaging() {
		return packagingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Prerequisites getPrerequisites() {
		return prerequisites;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrerequisites(Prerequisites newPrerequisites, NotificationChain msgs) {
		Prerequisites oldPrerequisites = prerequisites;
		prerequisites = newPrerequisites;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PREREQUISITES, oldPrerequisites, newPrerequisites);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrerequisites(Prerequisites newPrerequisites) {
		if (newPrerequisites != prerequisites) {
			NotificationChain msgs = null;
			if (prerequisites != null)
				msgs = ((InternalEObject)prerequisites).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PREREQUISITES, null, msgs);
			if (newPrerequisites != null)
				msgs = ((InternalEObject)newPrerequisites).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PREREQUISITES, null, msgs);
			msgs = basicSetPrerequisites(newPrerequisites, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PREREQUISITES, newPrerequisites, newPrerequisites));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IssueManagement getIssueManagement() {
		return issueManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIssueManagement(IssueManagement newIssueManagement, NotificationChain msgs) {
		IssueManagement oldIssueManagement = issueManagement;
		issueManagement = newIssueManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__ISSUE_MANAGEMENT, oldIssueManagement, newIssueManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIssueManagement(IssueManagement newIssueManagement) {
		if (newIssueManagement != issueManagement) {
			NotificationChain msgs = null;
			if (issueManagement != null)
				msgs = ((InternalEObject)issueManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__ISSUE_MANAGEMENT, null, msgs);
			if (newIssueManagement != null)
				msgs = ((InternalEObject)newIssueManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__ISSUE_MANAGEMENT, null, msgs);
			msgs = basicSetIssueManagement(newIssueManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__ISSUE_MANAGEMENT, newIssueManagement, newIssueManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CiManagement getCiManagement() {
		return ciManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCiManagement(CiManagement newCiManagement, NotificationChain msgs) {
		CiManagement oldCiManagement = ciManagement;
		ciManagement = newCiManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__CI_MANAGEMENT, oldCiManagement, newCiManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCiManagement(CiManagement newCiManagement) {
		if (newCiManagement != ciManagement) {
			NotificationChain msgs = null;
			if (ciManagement != null)
				msgs = ((InternalEObject)ciManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__CI_MANAGEMENT, null, msgs);
			if (newCiManagement != null)
				msgs = ((InternalEObject)newCiManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__CI_MANAGEMENT, null, msgs);
			msgs = basicSetCiManagement(newCiManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__CI_MANAGEMENT, newCiManagement, newCiManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInceptionYear() {
		return inceptionYear;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInceptionYear(String newInceptionYear) {
		String oldInceptionYear = inceptionYear;
		inceptionYear = newInceptionYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__INCEPTION_YEAR, oldInceptionYear, inceptionYear));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailingListsType getMailingLists() {
		return mailingLists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMailingLists(MailingListsType newMailingLists, NotificationChain msgs) {
		MailingListsType oldMailingLists = mailingLists;
		mailingLists = newMailingLists;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__MAILING_LISTS, oldMailingLists, newMailingLists);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMailingLists(MailingListsType newMailingLists) {
		if (newMailingLists != mailingLists) {
			NotificationChain msgs = null;
			if (mailingLists != null)
				msgs = ((InternalEObject)mailingLists).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__MAILING_LISTS, null, msgs);
			if (newMailingLists != null)
				msgs = ((InternalEObject)newMailingLists).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__MAILING_LISTS, null, msgs);
			msgs = basicSetMailingLists(newMailingLists, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__MAILING_LISTS, newMailingLists, newMailingLists));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DevelopersType getDevelopers() {
		return developers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDevelopers(DevelopersType newDevelopers, NotificationChain msgs) {
		DevelopersType oldDevelopers = developers;
		developers = newDevelopers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEVELOPERS, oldDevelopers, newDevelopers);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDevelopers(DevelopersType newDevelopers) {
		if (newDevelopers != developers) {
			NotificationChain msgs = null;
			if (developers != null)
				msgs = ((InternalEObject)developers).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEVELOPERS, null, msgs);
			if (newDevelopers != null)
				msgs = ((InternalEObject)newDevelopers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEVELOPERS, null, msgs);
			msgs = basicSetDevelopers(newDevelopers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEVELOPERS, newDevelopers, newDevelopers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributorsType getContributors() {
		return contributors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContributors(ContributorsType newContributors, NotificationChain msgs) {
		ContributorsType oldContributors = contributors;
		contributors = newContributors;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__CONTRIBUTORS, oldContributors, newContributors);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContributors(ContributorsType newContributors) {
		if (newContributors != contributors) {
			NotificationChain msgs = null;
			if (contributors != null)
				msgs = ((InternalEObject)contributors).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__CONTRIBUTORS, null, msgs);
			if (newContributors != null)
				msgs = ((InternalEObject)newContributors).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__CONTRIBUTORS, null, msgs);
			msgs = basicSetContributors(newContributors, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__CONTRIBUTORS, newContributors, newContributors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensesType getLicenses() {
		return licenses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLicenses(LicensesType newLicenses, NotificationChain msgs) {
		LicensesType oldLicenses = licenses;
		licenses = newLicenses;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__LICENSES, oldLicenses, newLicenses);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLicenses(LicensesType newLicenses) {
		if (newLicenses != licenses) {
			NotificationChain msgs = null;
			if (licenses != null)
				msgs = ((InternalEObject)licenses).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__LICENSES, null, msgs);
			if (newLicenses != null)
				msgs = ((InternalEObject)newLicenses).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__LICENSES, null, msgs);
			msgs = basicSetLicenses(newLicenses, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__LICENSES, newLicenses, newLicenses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scm getScm() {
		return scm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScm(Scm newScm, NotificationChain msgs) {
		Scm oldScm = scm;
		scm = newScm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__SCM, oldScm, newScm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScm(Scm newScm) {
		if (newScm != scm) {
			NotificationChain msgs = null;
			if (scm != null)
				msgs = ((InternalEObject)scm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__SCM, null, msgs);
			if (newScm != null)
				msgs = ((InternalEObject)newScm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__SCM, null, msgs);
			msgs = basicSetScm(newScm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__SCM, newScm, newScm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrganization(Organization newOrganization, NotificationChain msgs) {
		Organization oldOrganization = organization;
		organization = newOrganization;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__ORGANIZATION, oldOrganization, newOrganization);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrganization(Organization newOrganization) {
		if (newOrganization != organization) {
			NotificationChain msgs = null;
			if (organization != null)
				msgs = ((InternalEObject)organization).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__ORGANIZATION, null, msgs);
			if (newOrganization != null)
				msgs = ((InternalEObject)newOrganization).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__ORGANIZATION, null, msgs);
			msgs = basicSetOrganization(newOrganization, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__ORGANIZATION, newOrganization, newOrganization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Build getBuild() {
		return build;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBuild(Build newBuild, NotificationChain msgs) {
		Build oldBuild = build;
		build = newBuild;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__BUILD, oldBuild, newBuild);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBuild(Build newBuild) {
		if (newBuild != build) {
			NotificationChain msgs = null;
			if (build != null)
				msgs = ((InternalEObject)build).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__BUILD, null, msgs);
			if (newBuild != null)
				msgs = ((InternalEObject)newBuild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__BUILD, null, msgs);
			msgs = basicSetBuild(newBuild, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__BUILD, newBuild, newBuild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProfilesType getProfiles() {
		return profiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProfiles(ProfilesType newProfiles, NotificationChain msgs) {
		ProfilesType oldProfiles = profiles;
		profiles = newProfiles;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PROFILES, oldProfiles, newProfiles);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProfiles(ProfilesType newProfiles) {
		if (newProfiles != profiles) {
			NotificationChain msgs = null;
			if (profiles != null)
				msgs = ((InternalEObject)profiles).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PROFILES, null, msgs);
			if (newProfiles != null)
				msgs = ((InternalEObject)newProfiles).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PROFILES, null, msgs);
			msgs = basicSetProfiles(newProfiles, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PROFILES, newProfiles, newProfiles));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModulesType getModules() {
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModules(ModulesType newModules, NotificationChain msgs) {
		ModulesType oldModules = modules;
		modules = newModules;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__MODULES, oldModules, newModules);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModules(ModulesType newModules) {
		if (newModules != modules) {
			NotificationChain msgs = null;
			if (modules != null)
				msgs = ((InternalEObject)modules).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__MODULES, null, msgs);
			if (newModules != null)
				msgs = ((InternalEObject)newModules).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__MODULES, null, msgs);
			msgs = basicSetModules(newModules, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__MODULES, newModules, newModules));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepositoriesType getRepositories() {
		return repositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRepositories(RepositoriesType newRepositories, NotificationChain msgs) {
		RepositoriesType oldRepositories = repositories;
		repositories = newRepositories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPOSITORIES, oldRepositories, newRepositories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepositories(RepositoriesType newRepositories) {
		if (newRepositories != repositories) {
			NotificationChain msgs = null;
			if (repositories != null)
				msgs = ((InternalEObject)repositories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPOSITORIES, null, msgs);
			if (newRepositories != null)
				msgs = ((InternalEObject)newRepositories).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPOSITORIES, null, msgs);
			msgs = basicSetRepositories(newRepositories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPOSITORIES, newRepositories, newRepositories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PluginRepositoriesType getPluginRepositories() {
		return pluginRepositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPluginRepositories(PluginRepositoriesType newPluginRepositories, NotificationChain msgs) {
		PluginRepositoriesType oldPluginRepositories = pluginRepositories;
		pluginRepositories = newPluginRepositories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PLUGIN_REPOSITORIES, oldPluginRepositories, newPluginRepositories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginRepositories(PluginRepositoriesType newPluginRepositories) {
		if (newPluginRepositories != pluginRepositories) {
			NotificationChain msgs = null;
			if (pluginRepositories != null)
				msgs = ((InternalEObject)pluginRepositories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PLUGIN_REPOSITORIES, null, msgs);
			if (newPluginRepositories != null)
				msgs = ((InternalEObject)newPluginRepositories).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PLUGIN_REPOSITORIES, null, msgs);
			msgs = basicSetPluginRepositories(newPluginRepositories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PLUGIN_REPOSITORIES, newPluginRepositories, newPluginRepositories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependenciesType getDependencies() {
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencies(DependenciesType newDependencies, NotificationChain msgs) {
		DependenciesType oldDependencies = dependencies;
		dependencies = newDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEPENDENCIES, oldDependencies, newDependencies);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencies(DependenciesType newDependencies) {
		if (newDependencies != dependencies) {
			NotificationChain msgs = null;
			if (dependencies != null)
				msgs = ((InternalEObject)dependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEPENDENCIES, null, msgs);
			if (newDependencies != null)
				msgs = ((InternalEObject)newDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEPENDENCIES, null, msgs);
			msgs = basicSetDependencies(newDependencies, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEPENDENCIES, newDependencies, newDependencies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportsType1 getReports() {
		return reports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReports(ReportsType1 newReports, NotificationChain msgs) {
		ReportsType1 oldReports = reports;
		reports = newReports;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPORTS, oldReports, newReports);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReports(ReportsType1 newReports) {
		if (newReports != reports) {
			NotificationChain msgs = null;
			if (reports != null)
				msgs = ((InternalEObject)reports).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPORTS, null, msgs);
			if (newReports != null)
				msgs = ((InternalEObject)newReports).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPORTS, null, msgs);
			msgs = basicSetReports(newReports, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPORTS, newReports, newReports));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reporting getReporting() {
		return reporting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReporting(Reporting newReporting, NotificationChain msgs) {
		Reporting oldReporting = reporting;
		reporting = newReporting;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPORTING, oldReporting, newReporting);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReporting(Reporting newReporting) {
		if (newReporting != reporting) {
			NotificationChain msgs = null;
			if (reporting != null)
				msgs = ((InternalEObject)reporting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPORTING, null, msgs);
			if (newReporting != null)
				msgs = ((InternalEObject)newReporting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__REPORTING, null, msgs);
			msgs = basicSetReporting(newReporting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__REPORTING, newReporting, newReporting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependencyManagement getDependencyManagement() {
		return dependencyManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencyManagement(DependencyManagement newDependencyManagement, NotificationChain msgs) {
		DependencyManagement oldDependencyManagement = dependencyManagement;
		dependencyManagement = newDependencyManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEPENDENCY_MANAGEMENT, oldDependencyManagement, newDependencyManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencyManagement(DependencyManagement newDependencyManagement) {
		if (newDependencyManagement != dependencyManagement) {
			NotificationChain msgs = null;
			if (dependencyManagement != null)
				msgs = ((InternalEObject)dependencyManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEPENDENCY_MANAGEMENT, null, msgs);
			if (newDependencyManagement != null)
				msgs = ((InternalEObject)newDependencyManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DEPENDENCY_MANAGEMENT, null, msgs);
			msgs = basicSetDependencyManagement(newDependencyManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DEPENDENCY_MANAGEMENT, newDependencyManagement, newDependencyManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistributionManagement getDistributionManagement() {
		return distributionManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDistributionManagement(DistributionManagement newDistributionManagement, NotificationChain msgs) {
		DistributionManagement oldDistributionManagement = distributionManagement;
		distributionManagement = newDistributionManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DISTRIBUTION_MANAGEMENT, oldDistributionManagement, newDistributionManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistributionManagement(DistributionManagement newDistributionManagement) {
		if (newDistributionManagement != distributionManagement) {
			NotificationChain msgs = null;
			if (distributionManagement != null)
				msgs = ((InternalEObject)distributionManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DISTRIBUTION_MANAGEMENT, null, msgs);
			if (newDistributionManagement != null)
				msgs = ((InternalEObject)newDistributionManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__DISTRIBUTION_MANAGEMENT, null, msgs);
			msgs = basicSetDistributionManagement(newDistributionManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__DISTRIBUTION_MANAGEMENT, newDistributionManagement, newDistributionManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesType2 getProperties() {
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperties(PropertiesType2 newProperties, NotificationChain msgs) {
		PropertiesType2 oldProperties = properties;
		properties = newProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PROPERTIES, oldProperties, newProperties);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperties(PropertiesType2 newProperties) {
		if (newProperties != properties) {
			NotificationChain msgs = null;
			if (properties != null)
				msgs = ((InternalEObject)properties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PROPERTIES, null, msgs);
			if (newProperties != null)
				msgs = ((InternalEObject)newProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.MODEL__PROPERTIES, null, msgs);
			msgs = basicSetProperties(newProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.MODEL__PROPERTIES, newProperties, newProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case POMPackage.MODEL__PARENT:
				return basicSetParent(null, msgs);
			case POMPackage.MODEL__PREREQUISITES:
				return basicSetPrerequisites(null, msgs);
			case POMPackage.MODEL__ISSUE_MANAGEMENT:
				return basicSetIssueManagement(null, msgs);
			case POMPackage.MODEL__CI_MANAGEMENT:
				return basicSetCiManagement(null, msgs);
			case POMPackage.MODEL__MAILING_LISTS:
				return basicSetMailingLists(null, msgs);
			case POMPackage.MODEL__DEVELOPERS:
				return basicSetDevelopers(null, msgs);
			case POMPackage.MODEL__CONTRIBUTORS:
				return basicSetContributors(null, msgs);
			case POMPackage.MODEL__LICENSES:
				return basicSetLicenses(null, msgs);
			case POMPackage.MODEL__SCM:
				return basicSetScm(null, msgs);
			case POMPackage.MODEL__ORGANIZATION:
				return basicSetOrganization(null, msgs);
			case POMPackage.MODEL__BUILD:
				return basicSetBuild(null, msgs);
			case POMPackage.MODEL__PROFILES:
				return basicSetProfiles(null, msgs);
			case POMPackage.MODEL__MODULES:
				return basicSetModules(null, msgs);
			case POMPackage.MODEL__REPOSITORIES:
				return basicSetRepositories(null, msgs);
			case POMPackage.MODEL__PLUGIN_REPOSITORIES:
				return basicSetPluginRepositories(null, msgs);
			case POMPackage.MODEL__DEPENDENCIES:
				return basicSetDependencies(null, msgs);
			case POMPackage.MODEL__REPORTS:
				return basicSetReports(null, msgs);
			case POMPackage.MODEL__REPORTING:
				return basicSetReporting(null, msgs);
			case POMPackage.MODEL__DEPENDENCY_MANAGEMENT:
				return basicSetDependencyManagement(null, msgs);
			case POMPackage.MODEL__DISTRIBUTION_MANAGEMENT:
				return basicSetDistributionManagement(null, msgs);
			case POMPackage.MODEL__PROPERTIES:
				return basicSetProperties(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case POMPackage.MODEL__PARENT:
				return getParent();
			case POMPackage.MODEL__MODEL_VERSION:
				return getModelVersion();
			case POMPackage.MODEL__GROUP_ID:
				return getGroupId();
			case POMPackage.MODEL__ARTIFACT_ID:
				return getArtifactId();
			case POMPackage.MODEL__PACKAGING:
				return getPackaging();
			case POMPackage.MODEL__NAME:
				return getName();
			case POMPackage.MODEL__VERSION:
				return getVersion();
			case POMPackage.MODEL__DESCRIPTION:
				return getDescription();
			case POMPackage.MODEL__URL:
				return getUrl();
			case POMPackage.MODEL__PREREQUISITES:
				return getPrerequisites();
			case POMPackage.MODEL__ISSUE_MANAGEMENT:
				return getIssueManagement();
			case POMPackage.MODEL__CI_MANAGEMENT:
				return getCiManagement();
			case POMPackage.MODEL__INCEPTION_YEAR:
				return getInceptionYear();
			case POMPackage.MODEL__MAILING_LISTS:
				return getMailingLists();
			case POMPackage.MODEL__DEVELOPERS:
				return getDevelopers();
			case POMPackage.MODEL__CONTRIBUTORS:
				return getContributors();
			case POMPackage.MODEL__LICENSES:
				return getLicenses();
			case POMPackage.MODEL__SCM:
				return getScm();
			case POMPackage.MODEL__ORGANIZATION:
				return getOrganization();
			case POMPackage.MODEL__BUILD:
				return getBuild();
			case POMPackage.MODEL__PROFILES:
				return getProfiles();
			case POMPackage.MODEL__MODULES:
				return getModules();
			case POMPackage.MODEL__REPOSITORIES:
				return getRepositories();
			case POMPackage.MODEL__PLUGIN_REPOSITORIES:
				return getPluginRepositories();
			case POMPackage.MODEL__DEPENDENCIES:
				return getDependencies();
			case POMPackage.MODEL__REPORTS:
				return getReports();
			case POMPackage.MODEL__REPORTING:
				return getReporting();
			case POMPackage.MODEL__DEPENDENCY_MANAGEMENT:
				return getDependencyManagement();
			case POMPackage.MODEL__DISTRIBUTION_MANAGEMENT:
				return getDistributionManagement();
			case POMPackage.MODEL__PROPERTIES:
				return getProperties();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case POMPackage.MODEL__PARENT:
				setParent((Parent)newValue);
				return;
			case POMPackage.MODEL__MODEL_VERSION:
				setModelVersion((String)newValue);
				return;
			case POMPackage.MODEL__GROUP_ID:
				setGroupId((String)newValue);
				return;
			case POMPackage.MODEL__ARTIFACT_ID:
				setArtifactId((String)newValue);
				return;
			case POMPackage.MODEL__PACKAGING:
				setPackaging((String)newValue);
				return;
			case POMPackage.MODEL__NAME:
				setName((String)newValue);
				return;
			case POMPackage.MODEL__VERSION:
				setVersion((String)newValue);
				return;
			case POMPackage.MODEL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case POMPackage.MODEL__URL:
				setUrl((String)newValue);
				return;
			case POMPackage.MODEL__PREREQUISITES:
				setPrerequisites((Prerequisites)newValue);
				return;
			case POMPackage.MODEL__ISSUE_MANAGEMENT:
				setIssueManagement((IssueManagement)newValue);
				return;
			case POMPackage.MODEL__CI_MANAGEMENT:
				setCiManagement((CiManagement)newValue);
				return;
			case POMPackage.MODEL__INCEPTION_YEAR:
				setInceptionYear((String)newValue);
				return;
			case POMPackage.MODEL__MAILING_LISTS:
				setMailingLists((MailingListsType)newValue);
				return;
			case POMPackage.MODEL__DEVELOPERS:
				setDevelopers((DevelopersType)newValue);
				return;
			case POMPackage.MODEL__CONTRIBUTORS:
				setContributors((ContributorsType)newValue);
				return;
			case POMPackage.MODEL__LICENSES:
				setLicenses((LicensesType)newValue);
				return;
			case POMPackage.MODEL__SCM:
				setScm((Scm)newValue);
				return;
			case POMPackage.MODEL__ORGANIZATION:
				setOrganization((Organization)newValue);
				return;
			case POMPackage.MODEL__BUILD:
				setBuild((Build)newValue);
				return;
			case POMPackage.MODEL__PROFILES:
				setProfiles((ProfilesType)newValue);
				return;
			case POMPackage.MODEL__MODULES:
				setModules((ModulesType)newValue);
				return;
			case POMPackage.MODEL__REPOSITORIES:
				setRepositories((RepositoriesType)newValue);
				return;
			case POMPackage.MODEL__PLUGIN_REPOSITORIES:
				setPluginRepositories((PluginRepositoriesType)newValue);
				return;
			case POMPackage.MODEL__DEPENDENCIES:
				setDependencies((DependenciesType)newValue);
				return;
			case POMPackage.MODEL__REPORTS:
				setReports((ReportsType1)newValue);
				return;
			case POMPackage.MODEL__REPORTING:
				setReporting((Reporting)newValue);
				return;
			case POMPackage.MODEL__DEPENDENCY_MANAGEMENT:
				setDependencyManagement((DependencyManagement)newValue);
				return;
			case POMPackage.MODEL__DISTRIBUTION_MANAGEMENT:
				setDistributionManagement((DistributionManagement)newValue);
				return;
			case POMPackage.MODEL__PROPERTIES:
				setProperties((PropertiesType2)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case POMPackage.MODEL__PARENT:
				setParent((Parent)null);
				return;
			case POMPackage.MODEL__MODEL_VERSION:
				setModelVersion(MODEL_VERSION_EDEFAULT);
				return;
			case POMPackage.MODEL__GROUP_ID:
				setGroupId(GROUP_ID_EDEFAULT);
				return;
			case POMPackage.MODEL__ARTIFACT_ID:
				setArtifactId(ARTIFACT_ID_EDEFAULT);
				return;
			case POMPackage.MODEL__PACKAGING:
				unsetPackaging();
				return;
			case POMPackage.MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case POMPackage.MODEL__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case POMPackage.MODEL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case POMPackage.MODEL__URL:
				setUrl(URL_EDEFAULT);
				return;
			case POMPackage.MODEL__PREREQUISITES:
				setPrerequisites((Prerequisites)null);
				return;
			case POMPackage.MODEL__ISSUE_MANAGEMENT:
				setIssueManagement((IssueManagement)null);
				return;
			case POMPackage.MODEL__CI_MANAGEMENT:
				setCiManagement((CiManagement)null);
				return;
			case POMPackage.MODEL__INCEPTION_YEAR:
				setInceptionYear(INCEPTION_YEAR_EDEFAULT);
				return;
			case POMPackage.MODEL__MAILING_LISTS:
				setMailingLists((MailingListsType)null);
				return;
			case POMPackage.MODEL__DEVELOPERS:
				setDevelopers((DevelopersType)null);
				return;
			case POMPackage.MODEL__CONTRIBUTORS:
				setContributors((ContributorsType)null);
				return;
			case POMPackage.MODEL__LICENSES:
				setLicenses((LicensesType)null);
				return;
			case POMPackage.MODEL__SCM:
				setScm((Scm)null);
				return;
			case POMPackage.MODEL__ORGANIZATION:
				setOrganization((Organization)null);
				return;
			case POMPackage.MODEL__BUILD:
				setBuild((Build)null);
				return;
			case POMPackage.MODEL__PROFILES:
				setProfiles((ProfilesType)null);
				return;
			case POMPackage.MODEL__MODULES:
				setModules((ModulesType)null);
				return;
			case POMPackage.MODEL__REPOSITORIES:
				setRepositories((RepositoriesType)null);
				return;
			case POMPackage.MODEL__PLUGIN_REPOSITORIES:
				setPluginRepositories((PluginRepositoriesType)null);
				return;
			case POMPackage.MODEL__DEPENDENCIES:
				setDependencies((DependenciesType)null);
				return;
			case POMPackage.MODEL__REPORTS:
				setReports((ReportsType1)null);
				return;
			case POMPackage.MODEL__REPORTING:
				setReporting((Reporting)null);
				return;
			case POMPackage.MODEL__DEPENDENCY_MANAGEMENT:
				setDependencyManagement((DependencyManagement)null);
				return;
			case POMPackage.MODEL__DISTRIBUTION_MANAGEMENT:
				setDistributionManagement((DistributionManagement)null);
				return;
			case POMPackage.MODEL__PROPERTIES:
				setProperties((PropertiesType2)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case POMPackage.MODEL__PARENT:
				return parent != null;
			case POMPackage.MODEL__MODEL_VERSION:
				return MODEL_VERSION_EDEFAULT == null ? modelVersion != null : !MODEL_VERSION_EDEFAULT.equals(modelVersion);
			case POMPackage.MODEL__GROUP_ID:
				return GROUP_ID_EDEFAULT == null ? groupId != null : !GROUP_ID_EDEFAULT.equals(groupId);
			case POMPackage.MODEL__ARTIFACT_ID:
				return ARTIFACT_ID_EDEFAULT == null ? artifactId != null : !ARTIFACT_ID_EDEFAULT.equals(artifactId);
			case POMPackage.MODEL__PACKAGING:
				return isSetPackaging();
			case POMPackage.MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case POMPackage.MODEL__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case POMPackage.MODEL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case POMPackage.MODEL__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case POMPackage.MODEL__PREREQUISITES:
				return prerequisites != null;
			case POMPackage.MODEL__ISSUE_MANAGEMENT:
				return issueManagement != null;
			case POMPackage.MODEL__CI_MANAGEMENT:
				return ciManagement != null;
			case POMPackage.MODEL__INCEPTION_YEAR:
				return INCEPTION_YEAR_EDEFAULT == null ? inceptionYear != null : !INCEPTION_YEAR_EDEFAULT.equals(inceptionYear);
			case POMPackage.MODEL__MAILING_LISTS:
				return mailingLists != null;
			case POMPackage.MODEL__DEVELOPERS:
				return developers != null;
			case POMPackage.MODEL__CONTRIBUTORS:
				return contributors != null;
			case POMPackage.MODEL__LICENSES:
				return licenses != null;
			case POMPackage.MODEL__SCM:
				return scm != null;
			case POMPackage.MODEL__ORGANIZATION:
				return organization != null;
			case POMPackage.MODEL__BUILD:
				return build != null;
			case POMPackage.MODEL__PROFILES:
				return profiles != null;
			case POMPackage.MODEL__MODULES:
				return modules != null;
			case POMPackage.MODEL__REPOSITORIES:
				return repositories != null;
			case POMPackage.MODEL__PLUGIN_REPOSITORIES:
				return pluginRepositories != null;
			case POMPackage.MODEL__DEPENDENCIES:
				return dependencies != null;
			case POMPackage.MODEL__REPORTS:
				return reports != null;
			case POMPackage.MODEL__REPORTING:
				return reporting != null;
			case POMPackage.MODEL__DEPENDENCY_MANAGEMENT:
				return dependencyManagement != null;
			case POMPackage.MODEL__DISTRIBUTION_MANAGEMENT:
				return distributionManagement != null;
			case POMPackage.MODEL__PROPERTIES:
				return properties != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (modelVersion: ");
		result.append(modelVersion);
		result.append(", groupId: ");
		result.append(groupId);
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", packaging: ");
		if (packagingESet) result.append(packaging); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(", description: ");
		result.append(description);
		result.append(", url: ");
		result.append(url);
		result.append(", inceptionYear: ");
		result.append(inceptionYear);
		result.append(')');
		return result.toString();
	}

} //ModelImpl
