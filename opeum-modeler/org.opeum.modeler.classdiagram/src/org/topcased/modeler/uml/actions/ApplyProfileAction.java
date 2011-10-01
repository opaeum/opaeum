/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;

/**
 * Action that allows user to apply profiles
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class ApplyProfileAction extends UMLEObjectAction
{

    private Package thePackage;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param p the target package
     */
    public ApplyProfileAction(Modeler ed, org.eclipse.uml2.uml.Package p)
    {
        super("Apply Profile", ed);
        this.thePackage = p;
    }

    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        ((CommandStack) getEditor().getAdapter(CommandStack.class)).execute(createActionCommand(thePackage));
    }

    /**
     * Create the generation command
     * 
     * @param p the UML package
     * @return the command that creates the primitive types
     */
    protected Command createActionCommand(final org.eclipse.uml2.uml.Package p)
    {
        final List<Profile> choiceOfValues = new ArrayList<Profile>();

        Resource eResource = p.eResource();
        ResourceSet resourceSet = eResource == null ? null : eResource.getResourceSet();

        if (resourceSet != null)
        {
            try
            {
                // fix bug #2422
                for (URI profileURI : UMLPlugin.getEPackageNsURIToProfileLocationMap().values())
                {
                    resourceSet.getResource(profileURI.trimFragment(), true);
                }
                // BUG #2422
                // resourceSet.getResource(URI.createURI(UMLResource.STANDARD_PROFILE_URI), true);
                //
                // resourceSet.getResource(URI.createURI(UMLResource.ECORE_PROFILE_URI), true);
            }
            catch (Exception e)
            {
                // ignore
            }

            for (Resource resource : resourceSet.getResources())
            {
                Iterator<EObject> allContents = resource.getAllContents();

                while (allContents.hasNext())
                {
                    new UMLSwitch<Object>()
                    {
                        public Object caseProfile(Profile profile)
                        {
                            if (profile.isDefined())
                            {
                                ProfileApplication profileApplication = p.getProfileApplication(profile);

                                if (profileApplication == null || profileApplication.getAppliedDefinition() != profile.getDefinition())
                                {
                                    choiceOfValues.add(profile);
                                }
                            }

                            return profile;
                        }
                    }.doSwitch(allContents.next());
                }
            }
        }
        Collections.sort(choiceOfValues, new TextComparator<Profile>());

        String label = "Choose the profile to apply";

        final FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), getLabelProvider(), p, UMLPackage.Literals.PROFILE, Collections.EMPTY_LIST, label,
                choiceOfValues);
        dialog.open();

        if (dialog.getReturnCode() == Window.OK)
        {
        	final List<Profile> profilesToApply = new ArrayList<Profile>();
        	List<Profile> untypedPropertiesProfiles = new ArrayList<Profile>();

            for (Iterator< ? > profiles = dialog.getResult().iterator(); profiles.hasNext();)
            {
            	Profile profileToApply = (Profile) profiles.next();
            	if (hasUntypedAttributes(profileToApply))
            	{
            		profilesToApply.add(profileToApply);
            	}
            	else
            	{
            		untypedPropertiesProfiles.add(profileToApply);
            	}
            }

            openInformationDialog(untypedPropertiesProfiles);

            return new RecordingChangeCommand(p.eResource().getResourceSet())
            {
                /**
                 * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
                 */
                protected void doExecute()
                {
                	for (Profile profile : profilesToApply)
                	{
                		p.applyProfile(profile);
                	}
                }
            };
        }

        return UnexecutableCommand.INSTANCE;
    }

	@Override
    public EObject getEObject()
    {
        return thePackage;
    }

    /**
     * Check if a profile have untyped attributes.
     * A profile with untyped attributes generates an invalid ecore metamodel (untyped EAttribute)
     * and will generate errors on some generic components used in TOPCASED UI
     * 
     * @param profile
     * @return
     */
    public static boolean hasUntypedAttributes(Profile profile)
    {
		for (Stereotype stereotype : profile.getOwnedStereotypes())
		{
			for (Property att : stereotype.getOwnedAttributes())
			{
				if (att.getType() == null)
				{
					return false;
				}
			}
		}
		return true;
	}

    public static void openInformationDialog(List<Profile> untypedPropertiesProfiles)
    {
        if (!untypedPropertiesProfiles.isEmpty())
        {
        	String message = "These profiles are invalid because they contain untyped properties.\nThey will not be applied.\n\n";
        	for (Profile p : untypedPropertiesProfiles)
        	{
        		message += p.getName() + "\n";
        	}
        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Invalid profile(s)", message);
        }
	} 

}
