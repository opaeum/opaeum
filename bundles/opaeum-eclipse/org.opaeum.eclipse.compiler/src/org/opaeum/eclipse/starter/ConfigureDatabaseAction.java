package org.opaeum.eclipse.starter;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.oda.design.ui.profile.db.wizards.NewDbDataSourceWizard;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizardPage;
import org.eclipse.jface.dialogs.IDialogBlockedHandler;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;

public class ConfigureDatabaseAction extends AbstractOpaeumAction{
	public ConfigureDatabaseAction(IStructuredSelection selection2){
		super(selection2, "Configure Database");
	}
	@Override
	// public void run(IAction action){
	public void run(){
		// Load classes
		IContainer umlDir = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext ne = OpaeumEclipseContext.findOrCreateContextFor(umlDir);
		if(!(ne == null)){
			// The settings would have been edited from there -huh?
			// OpaeumConfigDialog dlg = new OpaeumConfigDialog(Display.getCurrent().getActiveShell(), ne.getConfig(),getCfgFile().getParent());
			final NewDbDataSourceWizard wiz = new NewDbDataSourceWizard();
			wiz.getProfileProperties();
			final Properties propsIn = new Properties();
			final WizardDialog dlg = new WizardDialog(Display.getCurrent().getActiveShell(), wiz){
				@Override
				public void create(){
					// TODO find better way of doing this
					super.create();
					wiz.setProfileName(ne.getConfig().getApplicationName() + " Database Connection");
					IConnectionProfile values = ProfileManager.getInstance().getProfileByName(wiz.getProfileName());
					if(values != null){
						try{
							ProfileManager.getInstance().deleteProfile(values);
						}catch(ConnectionProfileException e){
							e.printStackTrace();
						}
					}
					//TODO try to populate the profilePage
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.driverClass", ne.getConfig().getJdbcDriver());
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.username", ne.getConfig().getDbUserName());
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.databaseName", ne.getConfig().getDbName());
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.password", ne.getConfig().getDbPassword());
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.vendor", ne.getConfig().getDbVendor());
					propsIn.setProperty("org.eclipse.datatools.connectivity.db.URL", ne.getConfig().getJdbcConnectionUrl());
				}
				@Override
				protected void finishPressed(){
					NewConnectionProfileWizard pp = (NewConnectionProfileWizard) getCurrentPage().getWizard();
					ConnectionProfile cp = new ConnectionProfile(wiz.getProfileName(), wiz.getProfileDescription(), pp.getProfileProviderID());
					cp.setBaseProperties(wiz.getProfileProperties());
					try{
						ProfileManager.getInstance().addProfile(cp);
					}catch(ConnectionProfileException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					super.finishPressed();
				}
			};
			if(dlg.open() == Window.OK){
				Properties props = wiz.getProfileProperties();
				String driver = props.getProperty("org.eclipse.datatools.connectivity.db.driverClass");
				ne.getConfig().setJdbcDriver(driver);
				String userName = props.getProperty("org.eclipse.datatools.connectivity.db.username");
				ne.getConfig().setDbUserName(userName);
				String dbName = props.getProperty("org.eclipse.datatools.connectivity.db.databaseName");
				ne.getConfig().setDbName(dbName);
				String password = props.getProperty("org.eclipse.datatools.connectivity.db.password");
				ne.getConfig().setDbPassword(password);
				String vendor = props.getProperty("org.eclipse.datatools.connectivity.db.vendor");
				ne.getConfig().setDbVendor(vendor);
				String url = props.getProperty("org.eclipse.datatools.connectivity.db.URL");
				ne.getConfig().setJdbcConnectionUrl(url);
				ne.getConfig().store();
				reinitialiseConfig(ne);
				wiz.getProfileProperties();
			}
		}
	}
}
