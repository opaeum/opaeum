package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ContainerEvent;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.deploy.FilterDef;
import org.apache.catalina.deploy.FilterMap;
import org.apache.tomcat.InstanceManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.osgi.internal.HttpSessionWrapper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator,ServiceListener{
	public static final String PLUGIN_ID = "org.opaeum.runtime.rwt"; //$NON-NLS-1$
	public static final String ID = PLUGIN_ID;
	public static final String IMG_PROJECT = "project.gif";
	private Map<String,ServiceRegistration<?>> registration=new HashMap<String,ServiceRegistration<?>>();
	private ServletContext contextFound;
	Map<String,Image> images = new HashMap<String,Image>();
	private BundleContext bundleContext;
	private static Activator plugin;
	public Activator(){
	}
	public void start(BundleContext context) throws Exception{
		this.bundleContext = context;
		context.addServiceListener(this, "(" + Constants.OBJECTCLASS + "=" + IOpaeumApplication.class.getName() + ")");
		plugin = this;
		contextFound = null;
		ServiceReference<?> httpServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
		HttpService httpService = (HttpService) bundleContext.getService(httpServiceReference);
		Field fld = httpService.getClass().getDeclaredField("virtualHost");
		fld.setAccessible(true);
		StandardHost host = (StandardHost) fld.get(httpService);
		host.addContainerListener(new ContainerListener(){
			@Override
			public void containerEvent(ContainerEvent event){
				if(event.getType().equals("addChild") && event.getData() instanceof Context){
					final StandardContext context = (StandardContext) event.getData();
					if(context.getInstanceManager() == null){
						try{
							Field sd = StandardContext.class.getDeclaredField("context");
							sd.setAccessible(true);
							if(contextFound == null){
								contextFound = new ApplicationContext("", context){
									@Override
									public void removeAttribute(String name){
										// super.removeAttribute(name);
									}
								};
							}
							sd.set(context, contextFound);
						}catch(Exception ee){
							ee.printStackTrace();
						}
						FilterDef fd = new FilterDef();
						fd.setFilterName("d");
						fd.setFilterClass("d");
						FilterMap m = new FilterMap();
						m.setFilterName("d");
						m.setFilterName("d");
						m.addURLPattern("*");
						context.addFilterDef(fd);
						context.addFilterMap(m);
						context.setCrossContext(true);
						context.setInstanceManager(new InstanceManager(){
							@Override
							public Object newInstance(String className) throws IllegalAccessException,InvocationTargetException,NamingException,
									InstantiationException,ClassNotFoundException{
								if(className.equals("d")){
									return new Filter(){
										@Override
										public void init(FilterConfig filterConfig) throws ServletException{
											// TODO Auto-generated method stub
										}
										@Override
										public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,
												ServletException{
											chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request){
												@Override
												public String getPathInfo(){
													if("/".equals(super.getPathInfo()) && getContextPath().equals("/opaeum")){
														return null;
													}
													return super.getPathInfo();
												}
												@Override
												public ServletContext getServletContext(){
													return context.getServletContext();
												}
												@SuppressWarnings("restriction")
												@Override
												public HttpSession getSession(){
													return new HttpSessionWrapper(super.getSession(), context.getServletContext());
												}
												@SuppressWarnings("restriction")
												@Override
												public HttpSession getSession(boolean b){
													return new HttpSessionWrapper(super.getSession(b), context.getServletContext());
												}
											}, response);
										}
										@Override
										public void destroy(){
											// TODO Auto-generated method stub
										}
									};
								}
								return null;
							}
							@Override
							public Object newInstance(String fqcn,ClassLoader classLoader) throws IllegalAccessException,InvocationTargetException,
									NamingException,InstantiationException,ClassNotFoundException{
								return newInstance(fqcn);
							}
							@Override
							public Object newInstance(Class<?> c) throws IllegalAccessException,InvocationTargetException,NamingException,
									InstantiationException{
								// TODO Auto-generated method stub
								return null;
							}
							@Override
							public void newInstance(Object o) throws IllegalAccessException,InvocationTargetException,NamingException{
								// TODO Auto-generated method stub
							}
							@Override
							public void destroyInstance(Object o) throws IllegalAccessException,InvocationTargetException{
								// TODO Auto-generated method stub
							}
						});
					}
				}
			}
		});
	}
	public void stop(BundleContext context) throws Exception{
		plugin = null;
	}
	public static Activator getDefault(){
		return plugin;
	}
	public Image getImage(String string){
		return images.get(string);
	}
	public ImageDescriptor getImageDescriptor(String string){
		try{
			final InputStream openStream = bundleContext.getBundle().getResource(string).openStream();
			return new ImageDescriptor(){
				@Override
				public ImageData getImageData(){
					return new ImageData(openStream);
				}
			};
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void serviceChanged(ServiceEvent event){
		if(event.getType() == ServiceEvent.REGISTERED){
			final IOpaeumApplication service = (IOpaeumApplication) bundleContext.getService(event.getServiceReference());
			final Dictionary<String,String> d = new Hashtable<String,String>();
			registration.put(service.getIdentifier(), bundleContext.registerService(ApplicationConfiguration.class.getName(), new ApplicationConfiguration(){
				@Override
				public void configure(Application application){
					Map<String,String> emptyMap = Collections.emptyMap();
					application.addEntryPoint("/"+  service.getIdentifier(), service.getEntrypointType(), emptyMap);
				}
			},d));
		}else if(event.getType()==ServiceEvent.UNREGISTERING){
			final IOpaeumApplication service = (IOpaeumApplication) bundleContext.getService(event.getServiceReference());
			registration.get(service.getIdentifier()).unregister();
			registration.remove(service.getIdentifier());
		}
	}
}
