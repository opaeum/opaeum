package org.opaeum.feature.visit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

import org.opaeum.runtime.domain.ExceptionAnalyser;

/**
 * VisitorAdapter is an abstract adapter class that traverses a tree starting with the root. Subclasses have to annotate methods that serve
 * as so called "accept" or "visit" methods using the {@link VisitAfter} and the {@link VisitBefore} annotations. A method annotated with
 * VisitAfter will be invoked once all the children of the current node have been visited. Methods annotated with VisitBefore will be
 * invoked before any of the children nodes have been visited. No guarrantee is made regarding the sequence of invocation of two or more
 * methods with the same annotation
 * 
 * Contract: Implement the getChildren(NODE) method to allow the traversal from a node to its children
 * 
 * @author abarnard
 * 
 * @param <NODE>
 *            This is the common superclass/interface for all nodes in a tree. It is assumed to have the potential to have children
 * @param <ROOT>
 *            This it the class of the root node of the tree.
 */
public abstract class VisitorAdapter<NODE,ROOT extends NODE>{
	public static ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(8);
	private Throwable throwable;
	int depth = 0;
	static Map<Class<?>,MethodInvokers> metaInfoMap = new HashMap<Class<?>,VisitorAdapter.MethodInvokers>();
	private static final class EclipseClassPath implements ClassPath{
		Map<String,Class<?>> classes = new HashMap<String,Class<?>>();
		@Override
		public InputStream openClassfile(String classname) throws NotFoundException{
			try{
				URL url = find(classname);
				return url.openStream();
			}catch(IOException e){
				throw new NotFoundException(e.getMessage());
			}
		}
		@Override
		public URL find(String classname){
			Class<?> asdf = classes.get(classname);
			String jarname = "/" + classname.replace('.', '/') + ".class";
			if(asdf != null){
				URL resource = asdf.getResource(jarname);
				return resource;
			}else{
				URL resource = Thread.currentThread().getContextClassLoader().getResource(jarname);
				return resource;
			}
		}
		@Override
		public void close(){
		}
		public void addClass(Class<?> class1){
			classes.put(class1.getName(), class1);
			if(class1.getSuperclass() != Object.class && class1.getSuperclass() != null){
				addClass(class1.getSuperclass());
			}
		}
	}
	static volatile int classCount = 0;
	protected static class MethodInvokers{
		public List<VisitSpec> beforeMethods = new ArrayList<VisitSpec>();
		public List<VisitSpec> afterMethods = new ArrayList<VisitSpec>();
	}
	ClassPool pool = new ClassPool(null){
		@Override
		public ClassLoader getClassLoader(){
			return VisitorAdapter.this.getClass().getClassLoader();
		}
	};
	private static EclipseClassPath cp = new EclipseClassPath();
	protected MethodInvokers methodInvokers;
	@SuppressWarnings("unchecked")
	protected VisitorAdapter(){
		pool.appendSystemPath();
		pool.appendClassPath(cp);
		methodInvokers = loadMethodsFromClass((Class<? extends VisitorAdapter<NODE,ROOT>>) getClass());
	}
	protected MethodInvokers loadMethodsFromClass(Class<? extends VisitorAdapter<NODE,ROOT>> class1){
		MethodInvokers result = metaInfoMap.get(getClass());
		if(result == null){
			result = new MethodInvokers();
			Method[] methods = class1.getMethods();
			for(Method m:methods){
				if(m.isAnnotationPresent(VisitBefore.class)){
					VisitSpec newInstance = buildVisitSpec(m, true);
					result.beforeMethods.add(newInstance);
				}
				if(m.isAnnotationPresent(VisitAfter.class)){
					VisitSpec newInstance = buildVisitSpec(m, false);
					result.afterMethods.add(newInstance);
				}
			}
			metaInfoMap.put(getClass(), result);
		}
		return result;
	}
	private VisitSpec buildVisitSpec(Method m,boolean before){
		boolean b=false;
		if(b){
			return new VisitSpec(m, before);
		}
		try{
			cp.addClass(getClass());
			cp.addClass(m.getParameterTypes()[0]);
			if(m.getParameterTypes().length > 1){
				cp.addClass(m.getParameterTypes()[1]);
			}
			long random = Math.round(Math.random() * 10000000000l);
			cp.addClass(VisitSpec.class);
			CtClass ctClass = pool.makeClass(VisitSpec.class.getName() + random, pool.get(VisitSpec.class.getName()));
			StringBuilder sb = new StringBuilder("public void visit(" + VisitorAdapter.class.getName() + " vi, Object[] o) {((");
			sb.append(getClass().getName());
			sb.append(")vi).");
			sb.append(m.getName());
			sb.append("((");
			sb.append(m.getParameterTypes()[0].getName());
			sb.append(")o[0]");
			if(m.getParameterTypes().length > 1){
				sb.append(",(");
				sb.append(m.getParameterTypes()[1].getName());
				sb.append(")o[1]");
			}
			sb.append(");}");
			CtMethod ctm = CtNewMethod.make(sb.toString(), ctClass);
			ctClass.addMethod(ctm);
			Class<?> class1 = ctClass.toClass();
			VisitSpec newInstance = (VisitSpec) class1.newInstance();
			newInstance.init(m, before);
			return newInstance;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * Implement this method to provide the logic for traversal from a node to its children
	 */
	public abstract Collection<? extends NODE> getChildren(NODE parent);
	/**
	 * "Visit" methods can either have one or two parameters. When there is only one parameter, this parameter is assumed to represent the
	 * current node. When there are two parameters, the second parameter is assumed to be a "peer" node from mapped model. This is
	 * particularly useful when two very similar models are traversed at the same time, as is often the case with transformations. When
	 * using two parameters, the subclass has to implement the resolvePeer() method
	 * 
	 * @param o
	 * @param peerClass
	 * @return
	 */
	protected Object resolvePeer(NODE o,Class<?> peerClass){
		throw new RuntimeException("not implemented");
	}
	public void startVisiting(ROOT root){
		depth = 0;
		visitRecursively(root);
	}
	public void visitOnly(NODE o){
		for(VisitSpec v:methodInvokers.beforeMethods){
			maybeVisit(o, v);
		}
		for(VisitSpec v:methodInvokers.afterMethods){
			maybeVisit(o, v);
		}
	}
	public void visitRecursively(NODE o){
		visitBeforeMethods(o);
		visitChildren(o);
		visitAfterMethods(o);
	}
	protected void visitAfterMethods(NODE o){
		for(VisitSpec v:methodInvokers.afterMethods){
			maybeVisit(o, v);
		}
		depth--;
	}
	protected void visitChildren(NODE o){
		ArrayList<NODE> children = new ArrayList<NODE>(getChildren(o));
		if(shouldMultiThread(children)){
			depth = 1000000;
			throwable = null;
			Set<ScheduledFuture<?>> jobs = new HashSet<ScheduledFuture<?>>();
			for(final NODE child:children){
				jobs.add(exec.schedule(new Runnable(){
					@Override
					public void run(){
						try{
							visitRecursively(child);
						}catch(Throwable t){
							t.printStackTrace();
							throwable = t;
						}
					}
				}, 0, TimeUnit.MICROSECONDS));
			}
			for(ScheduledFuture<?> j:jobs){
				try{
					j.get();
				}catch(Exception e){
					new ExceptionAnalyser(e).throwRootCause();
				}
				if(throwable!=null){
					new ExceptionAnalyser(throwable).throwRootCause();
				}
			}
		}else{
			for(final NODE child:children){
				visitRecursively(child);
			}
		}
	}
	protected void visitBeforeMethods(NODE o){
		depth++;
		for(VisitSpec v:methodInvokers.beforeMethods){
			maybeVisit(o, v);
		}
	}
	protected boolean shouldMultiThread(ArrayList<NODE> children){
		return depth == 1 && getThreadPoolSize() > 1 && children.size() > 4;
	}
	protected void maybeVisit(NODE o,VisitSpec v){
		if(v.matches(o)){
			if(v.resolvePeer()){
				Object peer = resolvePeer(o, v.getPeerClass());
				if(peer != null){
					v.visit(this, new Object[]{
							o,peer
					});
				}
			}else{
				v.visit(this, new Object[]{
					o
				});
			}
		}
	}
	protected boolean shouldVisitChildren(NODE o){
		return true;
	}
	protected abstract int getThreadPoolSize();
}
