package org.opaeum.rwt.runtime;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;

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
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.deploy.FilterDef;
import org.apache.catalina.deploy.FilterMap;
import org.apache.tomcat.InstanceManager;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.osgi.internal.HttpSessionWrapper;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator{
	private ServiceRegistration<?> registration;
	private ServletContext contextFound;
	@Override
	public void start(BundleContext bundleContext) throws Exception{
		contextFound = null;
//		ServiceReference<?> httpServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
//		HttpService httpService = (HttpService) bundleContext.getService(httpServiceReference);
//		Field fld = httpService.getClass().getDeclaredField("virtualHost");
//		fld.setAccessible(true);
//		StandardHost host = (StandardHost) fld.get(httpService);
//		host.addContainerListener(new ContainerListener(){
//			@Override
//			public void containerEvent(ContainerEvent event){
//				if(event.getType().equals("addChild") && event.getData() instanceof Context){
//					final StandardContext context = (StandardContext) event.getData();
//					if(context.getInstanceManager() == null){
//						try{
//							Field sd = StandardContext.class.getDeclaredField("context");
//							sd.setAccessible(true);
//							if(contextFound == null){
//								contextFound = new ApplicationContext( context){
//									@Override
//									public void removeAttribute(String name){
////										super.removeAttribute(name);
//									}
//								};
//							}
//							sd.set(context, contextFound);
//						}catch(Exception ee){
//							ee.printStackTrace();
//						}
//						FilterDef fd = new FilterDef();
//						fd.setFilterName("d");
//						fd.setFilterClass("d");
//						FilterMap m = new FilterMap();
//						m.setFilterName("d");
//						m.setFilterName("d");
//						m.addURLPattern("*");
//						context.addFilterDef(fd);
//						context.addFilterMap(m);
//						context.setCrossContext(true);
//						String contextPath = context.getServletContext().getContextPath();
//						//TODO just at the freakin filter instance directly to the  context:
////						context.getServletContext().addFilter("*", new Filter(){});
//						context.setInstanceManager(new InstanceManager(){
//							@Override
//							public Object newInstance(String className) throws IllegalAccessException,InvocationTargetException,NamingException,
//									InstantiationException,ClassNotFoundException{
//								if(className.equals("d")){
//									return new Filter(){
//										@Override
//										public void init(FilterConfig filterConfig) throws ServletException{
//											// TODO Auto-generated method stub
//										}
//										@Override
//										public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,
//												ServletException{
//											chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request){
//												@Override
//												public String getPathInfo(){
//													if("/".equals(super.getPathInfo()) && getContextPath().equals("/opaeum")){
//														return null;
//													}
//													return super.getPathInfo();
//												}
//												@Override
//												public ServletContext getServletContext(){
//													return context.getServletContext();
//												}
//												@SuppressWarnings("restriction")
//												@Override
//												public HttpSession getSession(){
//													return new HttpSessionWrapper(super.getSession(), context.getServletContext());
//												}
//												@SuppressWarnings("restriction")
//												@Override
//												public HttpSession getSession(boolean b){
//													return new HttpSessionWrapper(super.getSession(b), context.getServletContext());
//												}
//											}, response);
//										}
//										@Override
//										public void destroy(){
//											// TODO Auto-generated method stub
//										}
//									};
//								}
//								return null;
//							}
//							@Override
//							public Object newInstance(String fqcn,ClassLoader classLoader) throws IllegalAccessException,InvocationTargetException,
//									NamingException,InstantiationException,ClassNotFoundException{
//								return newInstance(fqcn);
//							}
//							public Object newInstance(Class<?> c) throws IllegalAccessException,InvocationTargetException,NamingException,
//									InstantiationException{
//								// TODO Auto-generated method stub
//								return null;
//							}
//							@Override
//							public void newInstance(Object o) throws IllegalAccessException,InvocationTargetException,NamingException{
//								// TODO Auto-generated method stub
//							}
//							@Override
//							public void destroyInstance(Object o) throws IllegalAccessException,InvocationTargetException{
//								// TODO Auto-generated method stub
//							}
//						});
//					}
//				}
//			}
//		});
		ApplicationConfiguration configuration = new TestOpaeumConfiguration();
		Dictionary<String,String> d = new Hashtable<String,String>();
		registration = bundleContext.registerService(ApplicationConfiguration.class.getName(), configuration, d);
	}
	@Override
	public void stop(BundleContext bundleContext) throws Exception{
		registration.unregister();
	}
}