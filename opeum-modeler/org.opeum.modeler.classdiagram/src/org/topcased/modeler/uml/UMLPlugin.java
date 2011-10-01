/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UMLPlugin extends AbstractUIPlugin
{

    /**
     * That property is fired to the preferenceStore's listeners to inform them that the preference store has been
     * modified.
     */
    public static final String PREFERENCE_STORE_PROPERTY = "PreferenceStore";

    /** The UML URI. */
    public static final String UML_URI = "http://www.eclipse.org/uml2/3.0.0/UML";

    // The shared instance
    private static UMLPlugin plugin;

    /**
     * The constructor. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UMLPlugin()
    {
        super();
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     * @generated
     */
    @Override
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     * @generated
     */
    @Override
    public void stop(BundleContext context) throws Exception
    {
        super.stop(context);
        plugin = null;
    }

    /**
     * Returns the shared instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the singleton
     * @generated
     */
    public static UMLPlugin getDefault()
    {
        return plugin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Plugin Id
     * @generated
     */
    public static String getId()
    {
        return getDefault().getBundle().getSymbolicName();
    }

    /**
     * Log a message with given level into the Eclipse log file <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param message the message to log
     * @param level the message priority
     * @generated
     */
    public static void log(String message, int level)
    {
        IStatus status = null;
        status = new Status(level, getId(), IStatus.OK, message, null);
        log(status);
    }

    /**
     * Log an exception into the Eclipse log file <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param e the exception to log
     * @generated
     */
    public static void log(Throwable e)
    {
        if (e instanceof InvocationTargetException)
        {
            e = ((InvocationTargetException) e).getTargetException();
        }

        IStatus status = null;
        if (e instanceof CoreException)
        {
            status = ((CoreException) e).getStatus();
        }
        else
        {
            status = new Status(IStatus.ERROR, getId(), IStatus.OK, "Error", e);
        }

        log(status);
    }

    /**
     * Log an IStatus <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param status the status to log
     * @generated
     */
    public static void log(IStatus status)
    {
        ResourcesPlugin.getPlugin().getLog().log(status);
    }

    /**
     * Display a dialog box with the specified level
     * 
     * @param title title dialog box
     * @param message message displayed
     * @param level message level
     */
    public static void displayDialog(final String title, final String message, final int level)
    {
        if (level == IStatus.INFO)
        {
            Display.getDefault().asyncExec(new Runnable()
            {
                public void run()
                {
                    MessageDialog.openInformation(getActiveWorkbenchShell(), title == null ? "Information" : title, message == null ? "" : message);
                }
            });
        }
        else if (level == IStatus.WARNING)
        {
            Display.getDefault().asyncExec(new Runnable()
            {
                public void run()
                {
                    MessageDialog.openWarning(getActiveWorkbenchShell(), title == null ? "Warning" : title, message == null ? "" : message);
                }
            });
        }
        else if (level == IStatus.ERROR)
        {
            Display.getDefault().asyncExec(new Runnable()
            {
                public void run()
                {
                    MessageDialog.openError(getActiveWorkbenchShell(), title == null ? "Error" : title, message == null ? "" : message);
                }
            });
        }
    }

    /**
     * Returns the active workbench shell
     * 
     * @return the active workbench shell
     */
    public static Shell getActiveWorkbenchShell()
    {
        IWorkbenchWindow workBenchWindow = getActiveWorkbenchWindow();
        if (workBenchWindow == null)
        {
            return null;
        }
        return workBenchWindow.getShell();
    }

    /**
     * Returns the active workbench page or <code>null</code> if none.
     * 
     * @return the active workbench page
     */
    public static IWorkbenchPage getActivePage()
    {
        IWorkbenchWindow window = getActiveWorkbenchWindow();
        if (window != null)
        {
            return window.getActivePage();
        }
        return null;
    }

    /**
     * Returns the active workbench window
     * 
     * @return the active workbench window
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
        if (getDefault() == null)
        {
            return null;
        }
        IWorkbench workBench = getDefault().getWorkbench();
        if (workBench == null)
        {
            return null;
        }
        return workBench.getActiveWorkbenchWindow();
    }
}
