package org.opaeum.rap.runtime.internal;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;
import org.eclipse.rap.rms.data.ThrowableManager;
import org.eclipse.rap.rms.data.ThrowableManager.IThrowableHandler;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.ISessionStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.opaeum.rap.runtime.Constants;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin implements IRegistryChangeListener{
	private static final String IMAGE_REGISTRY = Activator.class.getName() + "#ImageRegistry";
	public static final String IMG_FORM_BG = "formBg"; //$NON-NLS-1$
	public static final String IMG_FORM_HEAD_OVERVIEW = "formHeadOverView"; //$NON-NLS-1$
	public static final String IMG_FORM_HEAD_TASKS = "formHeadTasks"; //$NON-NLS-1$
	public static final String IMG_FORM_HEAD_GANTT = "formHeadGantt"; //$NON-NLS-1$
	public static final String IMG_DATE_PICKER = "datePicker"; //$NON-NLS-1$
	public static final String IMG_NEW_TASK = "newTask"; //$NON-NLS-1$
	public static final String IMG_REPOSITORY = "repository"; //$NON-NLS-1$
	public static final String IMG_PRINCIPAL = "principal"; //$NON-NLS-1$
	public static final String IMG_PROJECT = "project"; //$NON-NLS-1$
	public static final String IMG_DIALOG = "dialog"; //$NON-NLS-1$
	public static final String IMG_WIZ_BAN = "wiz_ban"; //$NON-NLS-1$
	public static final String IMG_INTRO = "intro"; //$NON-NLS-1$
	public static final String IMG_INTRO_BANNER = "introBanner"; //$NON-NLS-1$
	public static final String IMG_INTRO_OVERVIEW = "introOverview"; //$NON-NLS-1$
	public static final String IMG_INTRO_OVERVIEW_TEXT = "introOverviewText"; //$NON-NLS-1$
	public static final String IMG_INTRO_SKIP = "introSkip"; //$NON-NLS-1$
	public static final String IMG_INTRO_NAVIGATOR = "navigator"; //$NON-NLS-1$
	public static final String IMG_INTRO_CONTEXT_MENU = "introContextMenu"; //$NON-NLS-1$
	public static final String IMG_INTRO_DATE_PICKER = "introDatePicker"; //$NON-NLS-1$
	public static final String IMG_INTRO_NEW_PROJECT = "introNewProject"; //$NON-NLS-1$
	public static final String IMG_BANNER_SECONDARY = "bannerSecondary"; //$NON-NLS-1$
	public static final String IMG_BANNER_SECONDARY_TXT = "bannerSecondaryTxt"; //$NON-NLS-1$
	public static final String[] COUNTRIES = new String[]{"","Afghanistan", //$NON-NLS-1$ //$NON-NLS-2$
			"Albania","Algeria","American Samoa","Andorra","Angola", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"Anguilla","Antigua and Barbuda","Argentina","Armenia","Aruba", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"Ashmore and Cartier Islands","Australia","Austria", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	};
	private static Activator plugin;
	private Map<Class<? extends IOpaeumApplication>, IOpaeumApplication> applications=new HashMap<Class<? extends IOpaeumApplication>,IOpaeumApplication>();
	public Map<Class<? extends IOpaeumApplication>,IOpaeumApplication> getApplications(){
		return applications;
	}
	public void start(final BundleContext context) throws Exception{
		super.start(context);
		plugin = this;
		ThrowableManager.setThrowableHandler(new IThrowableHandler(){
			public void handle(final Throwable throwable){
				String id = "org.opaeum.rap.runtime"; //$NON-NLS-1$
				String msg = throwable.getMessage();
				Status status = new Status(IStatus.ERROR, id, msg, throwable);
				plugin.getLog().log(status);
			}
		});
		try{
		initDataModel();}catch(Exception e){}
		IExtensionRegistry r = Platform.getExtensionRegistry();
		IConfigurationElement[] configurationElementsFor = r.getConfigurationElementsFor("org.opaeum.rap.runtime", "opaeumRAPApplication");
		registerExtensions(configurationElementsFor);

	}
	private void initDataModel(){
		DataModelRegistry.register(DataModelRegistry.DEFAULT_MODEL_TYPE);
		IDataModel factory = DataModelRegistry.getFactory();
		for(int i = 0;i < 4;i++){
			factory.newEmployee("Lastname_" + i, "Firstname_" + i); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for(int i = 0;i < 10;i++){
			IPrincipal principal = factory.newPrincipal("Principal " + i); //$NON-NLS-1$
			principal.setCity("City " + i); //$NON-NLS-1$
			principal.setCountry(COUNTRIES[i]);
			principal.setLastName("Lastname" + i); //$NON-NLS-1$
			principal.setFirstName("Firstname" + i); //$NON-NLS-1$
			principal.setEMail("contact" + i + "@company.com"); //$NON-NLS-1$ //$NON-NLS-2$
			principal.setStreet("street " + i); //$NON-NLS-1$
			principal.setPostCode("4711"); //$NON-NLS-1$
			for(int k = 0;k < 3;k++){
				IProject project = principal.newProject("Project " + k); //$NON-NLS-1$
				Calendar calendar = createCalendar();
				calendar.add(Calendar.DATE, 20);
				project.setEndDate(cut(calendar.getTime()));
				calendar.add(Calendar.DATE, -40);
				project.setStartDate(cut(calendar.getTime()));
				project.setDescription("This is the description of Project" + k);
				int employeeSize = factory.getEmployees().size();
				for(int j = 0;j < employeeSize;j++){
					project.newAssignment(factory.getEmployees().get(j));
				}
				for(int j = 0;j < 10;j++){
					ITask task = project.newTask("task " + j); //$NON-NLS-1$
					task.setDescription("This is the description of Task" + j);
					task.setStartDate(cut(calendar.getTime()));
					calendar.add(Calendar.DATE, 4);
					task.setEndDate(cut(calendar.getTime()));
				}
			}
		}
	}
	private Date cut(final Date time){
		Date result = time;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
		try{
			result = sdf.parse(sdf.format(time));
		}catch(ParseException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static Calendar createCalendar(){
		String[] ids = TimeZone.getAvailableIDs(+1 * 60 * 60 * 1000);
		SimpleTimeZone stz = new SimpleTimeZone(+1 * 60 * 60 * 1000, ids[0]);
		Calendar result = new GregorianCalendar(stz);
		result.setTime(new Date());
		return result;
	}
	public void stop(final BundleContext context) throws Exception{
		plugin = null;
		super.stop(context);
	}
	public static Activator getDefault(){
		return plugin;
	}
	// ///////
	// images
	public void initializeImageRegistry(final String pluginId){
		ImageRegistry imageRegistry = getImageRegistry();
		registerImage(pluginId, imageRegistry, IMG_FORM_BG, "form_banner.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_FORM_HEAD_OVERVIEW, "overview_banner.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_FORM_HEAD_TASKS, "tasks_banner.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_FORM_HEAD_GANTT, "gantt_banner.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_DATE_PICKER, "datepicker.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_NEW_TASK, "new_task.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_REPOSITORY, "repository.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_PROJECT, "project.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_PRINCIPAL, "principal.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO, "intro.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_BANNER, "sa_banner_main.jpg"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_BANNER_SECONDARY, "sa_banner_secondary.jpg"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_OVERVIEW, "ov_nav_64.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_SKIP, "wb_nav.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_OVERVIEW_TEXT, "bg_overview.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_CONTEXT_MENU, "context_menu.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_NAVIGATOR, "navigator.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_DATE_PICKER, "datepicker.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_INTRO_NEW_PROJECT, "new_project.png"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_DIALOG, "dialog.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_WIZ_BAN, "wiz_ban.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, IMG_BANNER_SECONDARY_TXT, "main_banner_txt.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, "unchecked.gif", "unchecked.gif"); //$NON-NLS-1$
		registerImage(pluginId, imageRegistry, "checked.gif", "checked.gif"); //$NON-NLS-1$
	}
	private void registerImage(final String pluginId,final ImageRegistry registry,final String key,final String fileName){
		try{
			IPath path = new Path("icons/" + findLanguage() + fileName); //$NON-NLS-1$
			Bundle rmsUiBundle = Platform.getBundle(pluginId);
			URL url = FileLocator.find(rmsUiBundle, path, null);
			if(url != null){
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		}catch(final Exception shouldNotHappen){
			shouldNotHappen.printStackTrace();
		}
	}
	private String findLanguage(){
		String result = "";
		Locale locale = RWT.getLocale();
		if(locale.getCountry().equals("DE") || locale.getLanguage().equals("de")){
			result = "de/";
		}else if(locale.getCountry().equals("CN") || locale.getLanguage().equals("zh")){
			result = "zh/";
		}
		return result;
	}
	public Image getImage(final String key){
		return getImageRegistry().get(key);
	}
	@Override
	public ImageRegistry getImageRegistry(){
		ISessionStore session = RWT.getSessionStore();
		ImageRegistry result = (ImageRegistry) session.getAttribute(IMAGE_REGISTRY);
		if(result == null){
			result = new ImageRegistry();
			session.setAttribute(IMAGE_REGISTRY, result);
		}
		return result;
	}
	public static ImageDescriptor getImageDescriptor(final String path){
		return imageDescriptorFromPlugin(Constants.PLUGIN_ID, path);
	}
	public void registryChanged(IRegistryChangeEvent event){
		registerExtensionDeltas(event.getExtensionDeltas("org.opaeum.rap.runtime", "opaeumRAPApplication"));
	}
	public void registerExtensionDeltas(IExtensionDelta[] extensionDeltas){
		for(IExtensionDelta delta:extensionDeltas){
			if(delta.getKind() == IExtensionDelta.ADDED){
				registerExtensions(delta.getExtension().getConfigurationElements());
			}
		}
	}
	private void registerExtensions(IConfigurationElement[] configurationElements){
		try{
			for(IConfigurationElement ce:configurationElements){
				IOpaeumApplication app = (IOpaeumApplication) ce.createExecutableExtension("className");
				Class<? extends IOpaeumApplication> clz = app.getClass();
				applications.put(clz, app);
			}
		}catch(CoreException e){
			e.printStackTrace();
		}
	}
}
