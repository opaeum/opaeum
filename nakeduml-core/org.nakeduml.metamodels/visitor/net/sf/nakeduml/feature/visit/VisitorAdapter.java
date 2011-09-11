package net.sf.nakeduml.feature.visit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

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
	private Map<NODE,String> currentVisitSpec = Collections.synchronizedMap(new HashMap<NODE,String>());
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
		if(false){
			return new VisitSpec(m, before);
		}
		try{
			cp.addClass(getClass());
			cp.addClass(m.getParameterTypes()[0]);
			if(m.getParameterTypes().length > 1){
				cp.addClass(m.getParameterTypes()[1]);
			}
			CtClass ctClass = pool.makeClass(VisitSpec.class.getName() + (++classCount), pool.get(VisitSpec.class.getName()));
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
		depth++;
		for(VisitSpec v:methodInvokers.beforeMethods){
			currentVisitSpec.put(o, v.getMethod(this).getName());
			maybeVisit(o, v);
		}
		ArrayList<NODE> children = new ArrayList<NODE>(getChildren(o));
		if(depth == 1 && getThreadPoolSize() > 1){
			ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(getThreadPoolSize());
			depth = 1000000;
			for(final NODE child:children){
				exec.schedule(new Runnable(){
					@Override
					public void run(){
						visitRecursively(child);
					}
				}, 0, TimeUnit.MICROSECONDS);
			}
			try{
				exec.shutdown();
				exec.awaitTermination(10, TimeUnit.MINUTES);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}else{
			for(final NODE child:children){
				visitRecursively(child);
			}
		}
		for(VisitSpec v:methodInvokers.afterMethods){
			currentVisitSpec.put(o, v.getMethod(this).getName());
			maybeVisit(o, v);
		}
		currentVisitSpec.remove(o);
		depth--;
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
	protected boolean visitChildren(NODE o){
		return true;
	}
	protected int getThreadPoolSize(){
		return 1;
	}
}
