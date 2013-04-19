package org.opaeum.papyrus;

import java.io.File;
import java.io.Serializable;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.misc.ProgramImageDescriptor;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.tweaklets.InterceptContributions;
import org.eclipse.ui.internal.tweaklets.Tweaklets;
import org.eclipse.ui.internal.util.Util;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class EditorDescriptor implements IEditorDescriptor,Serializable,IPluginContribution{
	private static final long serialVersionUID = 3905241225668998961L;
	public static final int OPEN_INTERNAL = 0x01;
	public static final int OPEN_INPLACE = 0x02;
	public static final int OPEN_EXTERNAL = 0x04;
	private static final String ATT_MATCHING_STRATEGY = "matchingStrategy";
	private static final String ATT_NAME = "name";
	private static final String ATT_LAUNCHER = "launcher";
	private static final String ATT_CONTRIBUTOR_CLASS = "contributorClass";
	private static final String ATT_CLASS = "class";
	private static final String ATT_COMMAND = "command";
	private static final String ATT_ID = "id";
	private static final String ATT_ICON = "icon";
	private String editorName;
	private String imageFilename;
	private transient ImageDescriptor imageDesc;
	private transient Object imageDescLock = new Object();
	private boolean testImage = true;
	private String className;
	private String launcherName;
	private String fileName;
	private String id = Util.ZERO_LENGTH_STRING;
	private boolean matchingStrategyChecked = false;
	private IEditorMatchingStrategy matchingStrategy;
	private Program program;
	// The id of the plugin which contributed this editor, null for external editors
	private String pluginIdentifier;
	private int openMode = 0;
	private transient IConfigurationElement configurationElement;
	public EditorDescriptor(String id2,IConfigurationElement element){
		setID(id2);
		setConfigurationElement(element);
	}
	/* package */EditorDescriptor(){
		super();
	}
	/**
	 * Creates a descriptor for an external program.
	 * 
	 * @param filename
	 *          the external editor full path and filename
	 * @return the editor descriptor
	 */
	public static EditorDescriptor createForProgram(String filename){
		if(filename == null){
			throw new IllegalArgumentException();
		}
		EditorDescriptor editor = new EditorDescriptor();
		editor.setFileName(filename);
		editor.setID(filename);
		editor.setOpenMode(OPEN_EXTERNAL);
		// Isolate the program name (no directory or extension)
		int start = filename.lastIndexOf(File.separator);
		String name;
		if(start != -1){
			name = filename.substring(start + 1);
		}else{
			name = filename;
		}
		int end = name.lastIndexOf('.');
		if(end != -1){
			name = name.substring(0, end);
		}
		editor.setName(name);
		// get the program icon without storing it in the registry
		ImageDescriptor imageDescriptor = new ProgramImageDescriptor(filename, 0);
		editor.setImageDescriptor(imageDescriptor);
		return editor;
	}
	/**
	 * Return the program called programName. Return null if it is not found.
	 * 
	 * @return org.eclipse.swt.program.Program
	 */
	private static Program findProgram(String programName){
		Program[] programs = Program.getPrograms();
		for(int i = 0;i < programs.length;i++){
			if(programs[i].getName().equals(programName)){
				return programs[i];
			}
		}
		return null;
	}
	/**
	 * Create the editor action bar contributor for editors of this type.
	 * 
	 * @return the action bar contributor, or <code>null</code>
	 */
	public IEditorActionBarContributor createActionBarContributor(){
		// Handle case for predefined editor descriptors, like the
		// one for IEditorRegistry.SYSTEM_INPLACE_EDITOR_ID, which
		// don't have a configuration element.
		if(configurationElement == null){
			return null;
		}
		// Get the contributor class name.
		String className = configurationElement.getAttribute(ATT_CONTRIBUTOR_CLASS);
		if(className == null){
			return null;
		}
		// Create the contributor object.
		IEditorActionBarContributor contributor = null;
		try{
			contributor = (IEditorActionBarContributor) WorkbenchPlugin.createExtension(configurationElement, ATT_CONTRIBUTOR_CLASS);
		}catch(CoreException e){
			WorkbenchPlugin.log("Unable to create editor contributor: " + //$NON-NLS-1$
					id, e.getStatus());
		}
		return contributor;
	}
	public static String getClassValue(IConfigurationElement configElement,String classAttributeName){
		String className = configElement.getAttribute(classAttributeName);
		if(className != null){
			return className;
		}
		IConfigurationElement[] candidateChildren = configElement.getChildren(classAttributeName);
		if(candidateChildren.length == 0){
			return null;
		}
		return candidateChildren[0].getAttribute(IWorkbenchRegistryConstants.ATT_CLASS);
	}
	public String getClassName(){
		if(configurationElement == null){
			return className;
		}
		return getClassValue(configurationElement, ATT_CLASS);
	}
	/**
	 * Return the configuration element used to define this editor, or <code>null</code>.
	 * 
	 * @return the element or null
	 */
	public IConfigurationElement getConfigurationElement(){
		return configurationElement;
	}
	/**
	 * Create an editor part based on this descriptor.
	 * 
	 * @return the editor part
	 * @throws CoreException
	 *           thrown if there is an issue creating the editor
	 */
	public IEditorPart createEditor() throws CoreException{
		Object extension = WorkbenchPlugin.createExtension(getConfigurationElement(), ATT_CLASS);
		return ((InterceptContributions) Tweaklets.get(InterceptContributions.KEY)).tweakEditor(extension);
	}
	/**
	 * Return the file name of the command to execute for this editor.
	 * 
	 * @return the file name to execute
	 */
	public String getFileName(){
		if(program == null){
			if(configurationElement == null){
				return fileName;
			}
			return configurationElement.getAttribute(ATT_COMMAND);
		}
		return program.getName();
	}
	/**
	 * Return the id for this editor.
	 * 
	 * @return the id
	 */
	public String getId(){
		if(program == null){
			if(configurationElement == null){
				return Util.safeString(id);
			}
			return Util.safeString(configurationElement.getAttribute(ATT_ID));
		}
		return Util.safeString(program.getName());
	}
	/**
	 * Return the image descriptor describing this editor.
	 * 
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(){
		ImageDescriptor tempDescriptor = null;
		synchronized(imageDescLock){
			if(!testImage)
				return imageDesc;
			if(imageDesc == null){
				String imageFileName = getImageFilename();
				String command = getFileName();
				if(imageFileName != null && configurationElement != null)
					tempDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(configurationElement.getNamespaceIdentifier(), imageFileName);
				else if(command != null)
					tempDescriptor = WorkbenchImages.getImageDescriptorFromProgram(command, 0);
			}else
				tempDescriptor = imageDesc;
			if(tempDescriptor == null){ // still null? return default image
				imageDesc = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_OBJ_FILE);
				testImage = false;
				return imageDesc;
			}
		}
		// Verifies that the image descriptor generates an image. If not, the descriptor is
		// replaced with the default image.
		// We must create the image without holding any locks, since there is a potential for deadlock
		// on Linux due to SWT's implementation of Image. See bugs 265028 and 256316 for details.
		Image img = tempDescriptor.createImage(false);
		if(img == null) // @issue what should be the default image?
			tempDescriptor = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_OBJ_FILE);
		else
			img.dispose();
		// <----- End of must-not-lock part
		// reenter synchronized block
		synchronized(imageDescLock){
			// if another thread has set the image description, use it
			if(!testImage)
				return imageDesc;
			// otherwise set the image description we calculated above
			imageDesc = tempDescriptor;
			testImage = false;
			return imageDesc;
		}
	}
	/**
	 * The Image to use to repesent this editor
	 */
	/* package */void setImageDescriptor(ImageDescriptor desc){
		synchronized(imageDescLock){
			imageDesc = desc;
			testImage = true;
		}
	}
	/**
	 * The name of the image describing this editor.
	 * 
	 * @return the image file name
	 */
	public String getImageFilename(){
		if(configurationElement == null){
			return imageFilename;
		}
		return configurationElement.getAttribute(ATT_ICON);
	}
	/**
	 * Return the user printable labelCombo for this editor.
	 * 
	 * @return the labelCombo
	 */
	public String getLabel(){
		if(program == null){
			if(configurationElement == null){
				return editorName;
			}
			return configurationElement.getAttribute(ATT_NAME);
		}
		return program.getName();
	}
	/**
	 * Returns the class name of the launcher.
	 * 
	 * @return the launcher class name
	 */
	public String getLauncher(){
		if(configurationElement == null){
			return launcherName;
		}
		return configurationElement.getAttribute(ATT_LAUNCHER);
	}
	/**
	 * Return the contributing plugin id.
	 * 
	 * @return the contributing plugin id
	 */
	public String getPluginID(){
		if(configurationElement != null){
			return configurationElement.getNamespace();
		}
		return pluginIdentifier;
	}
	/**
	 * Get the program for the receiver if there is one.
	 * 
	 * @return Program
	 */
	public Program getProgram(){
		return this.program;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorDescriptor#isInternal
	 */
	public boolean isInternal(){
		return getOpenMode() == OPEN_INTERNAL;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorDescriptor#isOpenInPlace
	 */
	public boolean isOpenInPlace(){
		return getOpenMode() == OPEN_INPLACE;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorDescriptor#isOpenExternal
	 */
	public boolean isOpenExternal(){
		return getOpenMode() == OPEN_EXTERNAL;
	}
	/**
	 * Load the object properties from a memento.
	 * 
	 * @return <code>true</code> if the values are valid, <code>false</code> otherwise
	 */
	protected boolean loadValues(IMemento memento){
		editorName = memento.getString(IWorkbenchConstants.TAG_LABEL);
		imageFilename = memento.getString(IWorkbenchConstants.TAG_IMAGE);
		className = memento.getString(IWorkbenchConstants.TAG_CLASS);
		launcherName = memento.getString(IWorkbenchConstants.TAG_LAUNCHER);
		fileName = memento.getString(IWorkbenchConstants.TAG_FILE);
		id = Util.safeString(memento.getString(IWorkbenchConstants.TAG_ID));
		pluginIdentifier = memento.getString(IWorkbenchConstants.TAG_PLUGIN);
		Integer openModeInt = memento.getInteger(IWorkbenchConstants.TAG_OPEN_MODE);
		if(openModeInt != null){
			openMode = openModeInt.intValue();
		}else{
			// legacy: handle the older attribute names, needed to allow reading of pre-3.0-RCP workspaces
			boolean internal = new Boolean(memento.getString(IWorkbenchConstants.TAG_INTERNAL)).booleanValue();
			boolean openInPlace = new Boolean(memento.getString(IWorkbenchConstants.TAG_OPEN_IN_PLACE)).booleanValue();
			if(internal){
				openMode = OPEN_INTERNAL;
			}else{
				if(openInPlace){
					openMode = OPEN_INPLACE;
				}else{
					openMode = OPEN_EXTERNAL;
				}
			}
		}
		if(openMode != OPEN_EXTERNAL && openMode != OPEN_INTERNAL && openMode != OPEN_INPLACE){
			WorkbenchPlugin.log("Ignoring editor descriptor with invalid openMode: " + this); //$NON-NLS-1$
			return false;
		}
		String programName = memento.getString(IWorkbenchConstants.TAG_PROGRAM_NAME);
		if(programName != null){
			this.program = findProgram(programName);
		}
		return true;
	}
	/**
	 * Save the object values in a IMemento
	 */
	protected void saveValues(IMemento memento){
		memento.putString(IWorkbenchConstants.TAG_LABEL, getLabel());
		memento.putString(IWorkbenchConstants.TAG_IMAGE, getImageFilename());
		memento.putString(IWorkbenchConstants.TAG_CLASS, getClassName());
		memento.putString(IWorkbenchConstants.TAG_LAUNCHER, getLauncher());
		memento.putString(IWorkbenchConstants.TAG_FILE, getFileName());
		memento.putString(IWorkbenchConstants.TAG_ID, getId());
		memento.putString(IWorkbenchConstants.TAG_PLUGIN, getPluginId());
		memento.putInteger(IWorkbenchConstants.TAG_OPEN_MODE, getOpenMode());
		// legacy: handle the older attribute names, needed to allow reading of workspace by pre-3.0-RCP eclipses
		memento.putString(IWorkbenchConstants.TAG_INTERNAL, String.valueOf(isInternal()));
		memento.putString(IWorkbenchConstants.TAG_OPEN_IN_PLACE, String.valueOf(isOpenInPlace()));
		if(this.program != null){
			memento.putString(IWorkbenchConstants.TAG_PROGRAM_NAME, this.program.getName());
		}
	}
	/**
	 * Return the open mode of this editor.
	 * 
	 * @return the open mode of this editor
	 * @since 3.1
	 */
	private int getOpenMode(){
		if(configurationElement == null){ // if we've been serialized, return our serialized value
			return openMode;
		}else if(getLauncher() != null){
			// open using a launcer
			return EditorDescriptor.OPEN_EXTERNAL;
		}else if(getFileName() != null){
			// open using an external editor
			return EditorDescriptor.OPEN_EXTERNAL;
		}else if(getPluginId() != null){
			// open using an internal editor
			return EditorDescriptor.OPEN_INTERNAL;
		}else{
			return 0; // default for system editor
		}
	}
	/**
	 * Set the class name of an internal editor.
	 */
	/* package */void setClassName(String newClassName){
		className = newClassName;
	}
	/**
	 * Set the configuration element which contributed this editor.
	 */
	/* package */void setConfigurationElement(IConfigurationElement newConfigurationElement){
		configurationElement = newConfigurationElement;
	}
	/**
	 * Set the filename of an external editor.
	 */
	/* package */void setFileName(String aFileName){
		fileName = aFileName;
	}
	/**
	 * Set the id of the editor. For internal editors this is the id as provided in the extension point For external editors it is path and
	 * filename of the editor
	 */
	/* package */void setID(String anID){
		Assert.isNotNull(anID);
		id = anID;
	}
	/**
	 * The name of the image to use for this editor.
	 */
	/* package */void setImageFilename(String aFileName){
		imageFilename = aFileName;
	}
	/**
	 * Sets the new launcher class name
	 * 
	 * @param newLauncher
	 *          the new launcher
	 */
	/* package */void setLauncher(String newLauncher){
		launcherName = newLauncher;
	}
	/**
	 * The labelCombo to show for this editor.
	 */
	/* package */void setName(String newName){
		editorName = newName;
	}
	/**
	 * Sets the open mode of this editor descriptor.
	 * 
	 * @param mode
	 *          the open mode
	 * 
	 * @issue this method is public as a temporary fix for bug 47600
	 */
	public void setOpenMode(int mode){
		openMode = mode;
	}
	/**
	 * The id of the plugin which contributed this editor, null for external editors.
	 */
	/* package */void setPluginIdentifier(String anID){
		pluginIdentifier = anID;
	}
	/**
	 * Set the receivers program.
	 * 
	 * @param newProgram
	 */
	/* package */void setProgram(Program newProgram){
		this.program = newProgram;
		if(editorName == null){
			setName(newProgram.getName());
		}
	}
	/**
	 * For debugging purposes only.
	 */
	public String toString(){
		return "EditorDescriptor(id=" + getId() + ", labelCombo=" + getLabel() + ")"; //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-1$
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.activities.support.IPluginContribution#getLocalId()
	 */
	public String getLocalId(){
		return getId();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.activities.support.IPluginContribution#getPluginId()
	 */
	public String getPluginId(){
		return getPluginID();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorDescriptor#getEditorManagementPolicy()
	 */
	public IEditorMatchingStrategy getEditorMatchingStrategy(){
		if(matchingStrategy == null && !matchingStrategyChecked){
			matchingStrategyChecked = true;
			if(program == null && configurationElement != null){
				if(configurationElement.getAttribute(ATT_MATCHING_STRATEGY) != null){
					try{
						matchingStrategy = (IEditorMatchingStrategy) WorkbenchPlugin.createExtension(configurationElement, ATT_MATCHING_STRATEGY);
					}catch(CoreException e){
						WorkbenchPlugin.log("Error creating editor management policy for editor id " + getId(), e); //$NON-NLS-1$
					}
				}
			}
		}
		return matchingStrategy;
	}
}
