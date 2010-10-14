/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Build Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 3.0.0+
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.BuildBase#getDefaultGoal <em>Default Goal</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getResources <em>Resources</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getTestResources <em>Test Resources</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getDirectory <em>Directory</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getFinalName <em>Final Name</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getPluginManagement <em>Plugin Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.BuildBase#getPlugins <em>Plugins</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getBuildBase()
 * @model extendedMetaData="name='BuildBase' kind='elementOnly'"
 * @generated
 */
public interface BuildBase extends EObject {
	/**
	 * Returns the value of the '<em><b>Default Goal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * 
	 *             The default goal (or phase in Maven 2) to execute when none is specified for the project.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Goal</em>' attribute.
	 * @see #setDefaultGoal(String)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_DefaultGoal()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='defaultGoal' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDefaultGoal();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getDefaultGoal <em>Default Goal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Goal</em>' attribute.
	 * @see #getDefaultGoal()
	 * @generated
	 */
	void setDefaultGoal(String value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 3.0.0+
	 * 
	 *             This element describes all of the classpath resources such as properties files associated with a
	 *             project. These resources are often included in the final package.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference.
	 * @see #setResources(ResourcesType)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_Resources()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='resources' namespace='##targetNamespace'"
	 * @generated
	 */
	ResourcesType getResources();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getResources <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resources</em>' containment reference.
	 * @see #getResources()
	 * @generated
	 */
	void setResources(ResourcesType value);

	/**
	 * Returns the value of the '<em><b>Test Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             This element describes all of the classpath resources such as properties files associated with a
	 *             project's unit tests.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Test Resources</em>' containment reference.
	 * @see #setTestResources(TestResourcesType)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_TestResources()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='testResources' namespace='##targetNamespace'"
	 * @generated
	 */
	TestResourcesType getTestResources();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getTestResources <em>Test Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Resources</em>' containment reference.
	 * @see #getTestResources()
	 * @generated
	 */
	void setTestResources(TestResourcesType value);

	/**
	 * Returns the value of the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The directory where all files generated by the build are placed.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Directory</em>' attribute.
	 * @see #setDirectory(String)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_Directory()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='directory' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDirectory();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getDirectory <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Directory</em>' attribute.
	 * @see #getDirectory()
	 * @generated
	 */
	void setDirectory(String value);

	/**
	 * Returns the value of the '<em><b>Final Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The filename (excluding the extension, and with no path information) that the produced artifact
	 *             will be called. The default value is &lt;code&gt;${artifactId}-${version}&lt;/code&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Final Name</em>' attribute.
	 * @see #setFinalName(String)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_FinalName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='finalName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFinalName();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getFinalName <em>Final Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final Name</em>' attribute.
	 * @see #getFinalName()
	 * @generated
	 */
	void setFinalName(String value);

	/**
	 * Returns the value of the '<em><b>Filters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *           The list of filter properties files that are used when filtering is enabled.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Filters</em>' containment reference.
	 * @see #setFilters(FiltersType)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_Filters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='filters' namespace='##targetNamespace'"
	 * @generated
	 */
	FiltersType getFilters();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getFilters <em>Filters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filters</em>' containment reference.
	 * @see #getFilters()
	 * @generated
	 */
	void setFilters(FiltersType value);

	/**
	 * Returns the value of the '<em><b>Plugin Management</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Default plugin information to be made available for reference by 
	 *             projects derived from this one. This plugin configuration will not
	 *             be resolved or bound to the lifecycle unless referenced. Any local
	 *             configuration for a given plugin will override the plugin's entire
	 *             definition here.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Plugin Management</em>' containment reference.
	 * @see #setPluginManagement(PluginManagement)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_PluginManagement()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='pluginManagement' namespace='##targetNamespace'"
	 * @generated
	 */
	PluginManagement getPluginManagement();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getPluginManagement <em>Plugin Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin Management</em>' containment reference.
	 * @see #getPluginManagement()
	 * @generated
	 */
	void setPluginManagement(PluginManagement value);

	/**
	 * Returns the value of the '<em><b>Plugins</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The list of plugins to use.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Plugins</em>' containment reference.
	 * @see #setPlugins(PluginsType)
	 * @see org.apache.maven.pom.POMPackage#getBuildBase_Plugins()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='plugins' namespace='##targetNamespace'"
	 * @generated
	 */
	PluginsType getPlugins();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.BuildBase#getPlugins <em>Plugins</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugins</em>' containment reference.
	 * @see #getPlugins()
	 * @generated
	 */
	void setPlugins(PluginsType value);

} // BuildBase
