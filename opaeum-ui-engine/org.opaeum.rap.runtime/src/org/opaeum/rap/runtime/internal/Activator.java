// Created on 11.09.2007
package org.opaeum.rap.runtime.internal;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.eclipse.core.runtime.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.rap.rms.data.*;
import org.eclipse.rap.rms.data.ThrowableManager.IThrowableHandler;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.ISessionStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.opaeum.rap.runtime.Constants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


public class Activator extends AbstractUIPlugin {

  private static final String IMAGE_REGISTRY
    = Activator.class.getName() + "#ImageRegistry";
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
  public static final String[] COUNTRIES
    = new String[]
  {
	 "", "Afghanistan", //$NON-NLS-1$ //$NON-NLS-2$
	"Albania", "Algeria", "American Samoa", "Andorra", "Angola", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Anguilla", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Ashmore and Cartier Islands", "Australia", "Austria", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"British Indian Ocean Territory", "British Virgin Islands", //$NON-NLS-1$ //$NON-NLS-2$
	"Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Central African Republic", "Chad", "Chile", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"China (People's Republic)", "Christmas Island", //$NON-NLS-1$ //$NON-NLS-2$
	"Coco (Keeling) Islands", "Colombia", "Comoro Island", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Congo, Democratic Republic of", "Congo, Republic of", //$NON-NLS-1$ //$NON-NLS-2$
	"Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Czech Republic", "Denmark", "Djibouti", "Dominica", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Dominican Republic", "East Timor", "Ecuador", "Egypt", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"France", "French Guiana", "French Polynesia", "Gabon", "Gambia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Honduras", "Hong Kong SAR", "Hungary", "Iceland", "India", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Italy", "Ivory Coast", "Jamaica", "Japan", "Jersey", "Jordan", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Liechtenstein", "Lithuania", "Luxembourg", "Macau", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Macedonia, Republic of", "Madagascar", "Malawi", "Malaysia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Mauritania", "Mauritius", "Mexico", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Micronesia, Federated States", "Moldova", "Monaco", "Mongolia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Netherlands", "Netherlands Antilles", "New Caledonia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Norfolk Island", "North Korea", "Northern Mariana Islands", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Norway", "Oman", "Pakistan", "Palau", "Panama", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Papua New Guinea", "Paraguay", "Peru", "Philippines", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Republic of San Marino", "Reunion", "Romania", "Russia", "Rwanda", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Saint Kitts and Nevis", "Saint Lucia", //$NON-NLS-1$ //$NON-NLS-2$
	"Saint Pierre and Miquelon", "Saint Vincent and Grenadines", //$NON-NLS-1$ //$NON-NLS-2$
	"Samoa", "Saudi Arabia", "Senegal", "Serbia And Montenegro", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Solomon Islands", "Somalia", "South Africa", "South Korea", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Spain", "Sri Lanka", "Sudan", "Surinam", "Svalbard", "Swaziland", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	"Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	"Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"United Arab Emirates", "United Kingdom", //$NON-NLS-1$ //$NON-NLS-2$
	"United States of America", "Uruguay", "Uzbekistan", "Vanuatu", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Vatican City (Holy See)", "Venezuela", "Vietnam", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Virgin Islands", "Wallis and Futuna", "West Bank", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	"Western Sahara", "Yemen", "Yugoslavia (Historical)", "Zaire", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	"Zambia", "Zimbabwe" //$NON-NLS-1$ //$NON-NLS-2$
  };
  
  private static Activator plugin;
  
  public void start( final BundleContext context ) throws Exception {
    super.start( context );
    plugin = new Activator();
    ThrowableManager.setThrowableHandler( new IThrowableHandler() {
      public void handle( final Throwable throwable ) {
        String id = "org.opaeum.rap.runtime"; //$NON-NLS-1$
        String msg = throwable.getMessage();
        Status status = new Status( IStatus.ERROR, id, msg, throwable );
        plugin.getLog().log( status );
      }
    } );
    initDataModel();
  }
  
  private void initDataModel() {
    DataModelRegistry.register( DataModelRegistry.DEFAULT_MODEL_TYPE );
    IDataModel factory = DataModelRegistry.getFactory();
    for( int i = 0; i < 4; i++ ) {
      factory.newEmployee( "Lastname_" + i , "Firstname_" + i ); //$NON-NLS-1$ //$NON-NLS-2$
    }
    for( int i = 0; i < 10; i++ ) {
      IPrincipal principal = factory.newPrincipal( "Principal " + i ); //$NON-NLS-1$
      principal.setCity( "City " + i ); //$NON-NLS-1$
      principal.setCountry( COUNTRIES[ i ] );
      principal.setLastName( "Lastname" + i ); //$NON-NLS-1$
      principal.setFirstName( "Firstname" + i ); //$NON-NLS-1$
      principal.setEMail( "contact" + i + "@company.com" ); //$NON-NLS-1$ //$NON-NLS-2$
      principal.setStreet( "street " + i ); //$NON-NLS-1$
      principal.setPostCode( "4711" ); //$NON-NLS-1$
      for( int k = 0; k < 3; k++ ) {
		IProject project = principal.newProject("Project " + k); //$NON-NLS-1$
		Calendar calendar = createCalendar();
		calendar.add(Calendar.DATE, 20);
		project.setEndDate(cut(calendar.getTime()));
		calendar.add(Calendar.DATE, -40);
		project.setStartDate(cut(calendar.getTime()));
		project.setDescription("This is the description of Project" + k);
		int employeeSize = factory.getEmployees().size();
		for (int j = 0; j < employeeSize; j++) {
			project.newAssignment(factory.getEmployees().get(j));
		}
		for (int j = 0; j < 10; j++) {
			ITask task = project.newTask("task " + j); //$NON-NLS-1$
			task.setDescription("This is the description of Task" + j);
			task.setStartDate(cut(calendar.getTime()));
			calendar.add(Calendar.DATE, 4);
			task.setEndDate(cut(calendar.getTime()));
		}
	}
    }
  }
  
  private Date cut( final Date time ) {
    Date result = time;
    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" ); //$NON-NLS-1$
    try {
      result = sdf.parse( sdf.format( time ) );
    } catch( ParseException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  public static Calendar createCalendar() {
    String[] ids = TimeZone.getAvailableIDs( +1 * 60 * 60 * 1000 );
    SimpleTimeZone stz = new SimpleTimeZone( +1 * 60 * 60 * 1000, ids[ 0 ] );
    Calendar result = new GregorianCalendar( stz );
    result.setTime( new Date() );
    return result;
  }

  public void stop( final BundleContext context ) throws Exception {
    plugin = null;
    super.stop( context );
  }
  
  public static Activator getDefault() {
    return plugin;
  }
  
  
  /////////
  // images
  
  public void initializeImageRegistry( final String pluginId ) {
    ImageRegistry imageRegistry = getImageRegistry();
    registerImage( pluginId,
                   imageRegistry,
                   IMG_FORM_BG,
                   "form_banner.gif" ); //$NON-NLS-1$
    registerImage( pluginId, 
                   imageRegistry, 
                   IMG_FORM_HEAD_OVERVIEW, 
                   "overview_banner.png" ); //$NON-NLS-1$
    registerImage( pluginId, 
                   imageRegistry, 
                   IMG_FORM_HEAD_TASKS, 
                   "tasks_banner.png" ); //$NON-NLS-1$
    registerImage( pluginId, 
                   imageRegistry, 
                   IMG_FORM_HEAD_GANTT, 
                   "gantt_banner.png" ); //$NON-NLS-1$
    registerImage( pluginId, 
                   imageRegistry, 
                   IMG_DATE_PICKER, 
                   "datepicker.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_NEW_TASK,
                   "new_task.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_REPOSITORY,
                   "repository.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_PROJECT,
                   "project.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_PRINCIPAL,
                   "principal.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO,
                   "intro.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_BANNER,
                   "sa_banner_main.jpg" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_BANNER_SECONDARY,
                   "sa_banner_secondary.jpg" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_OVERVIEW,
                   "ov_nav_64.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_SKIP,
                   "wb_nav.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_OVERVIEW_TEXT,
                   "bg_overview.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_CONTEXT_MENU,
                   "context_menu.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_NAVIGATOR,
                   "navigator.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_DATE_PICKER,
                   "datepicker.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_INTRO_NEW_PROJECT,
                   "new_project.png" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_DIALOG,
                   "dialog.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_WIZ_BAN,
                   "wiz_ban.gif" ); //$NON-NLS-1$
    registerImage( pluginId,
                   imageRegistry,
                   IMG_BANNER_SECONDARY_TXT,
                   "main_banner_txt.gif" ); //$NON-NLS-1$
  }

  private void registerImage( final String pluginId,
		                      final ImageRegistry registry,
                              final String key,
                              final String fileName )
  {
    try {
      IPath path = new Path( "icons/" + findLanguage() + fileName ); //$NON-NLS-1$
      Bundle rmsUiBundle = Platform.getBundle( pluginId );
      URL url = FileLocator.find( rmsUiBundle, path, null );
      if( url != null ) {
        ImageDescriptor desc = ImageDescriptor.createFromURL( url );
        registry.put( key, desc );
      }
    } catch( final Exception shouldNotHappen ) {
      shouldNotHappen.printStackTrace();
    }
  }

  private String findLanguage() {
    String result = "";
    Locale locale = RWT.getLocale();
    if(    locale.getCountry().equals( "DE" ) 
        || locale.getLanguage().equals( "de" ) )
    {
      result = "de/";
    } else if(    locale.getCountry().equals( "CN" ) 
               || locale.getLanguage().equals( "zh" ) )
    {
      result = "zh/";
    }
    return result;
  }

  public Image getImage( final String key ) {
    return getImageRegistry().get( key );
  }
  
  @Override
  public ImageRegistry getImageRegistry() {
    ISessionStore session = RWT.getSessionStore();
    ImageRegistry result
      = ( ImageRegistry )session.getAttribute( IMAGE_REGISTRY );
    if( result == null ) {
      result = new ImageRegistry();
      session.setAttribute( IMAGE_REGISTRY, result );
    }
    return result;
  }

  public static ImageDescriptor getImageDescriptor( final String path ) {
    return imageDescriptorFromPlugin( Constants.PLUGIN_ID, path );
  }
}
