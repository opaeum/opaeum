package net.sf.nakeduml.feature.visit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.uml2.uml.Element;
import org.nakeduml.runtime.domain.IntrospectionUtil;

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
	static int classCount = 0;
	protected List<VisitSpec> beforeMethods = new ArrayList<VisitSpec>();
	protected List<VisitSpec> afterMethods = new ArrayList<VisitSpec>();
	ClassPool pool = new ClassPool(null){
		@Override
		public ClassLoader getClassLoader(){
			return VisitorAdapter.this.getClass().getClassLoader();
		}
	};
	private static EclipseClassPath cp = new EclipseClassPath();
	protected VisitorAdapter(){
		pool.appendSystemPath();
		pool.appendClassPath(cp);
		loadMethodsFromClass((Class<? extends VisitorAdapter<NODE,ROOT>>) getClass());
	}
	protected void loadMethodsFromClass(Class<? extends VisitorAdapter<NODE,ROOT>> class1){
		Method[] methods = class1.getMethods();
		for(Method m:methods){
			if(m.isAnnotationPresent(VisitBefore.class)){
				VisitSpec newInstance = buildVisitSpec(m, true);
				beforeMethods.add(newInstance);
			}
			if(m.isAnnotationPresent(VisitAfter.class)){
				VisitSpec newInstance = buildVisitSpec(m, false);
				afterMethods.add(newInstance);
			}
		}
	}
	public VisitSpec buildVisitSpec(Method m,boolean before){
		// return new VisitSpec(m,before);
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
			Class class1 = ctClass.toClass();
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
		visitRecursively(root);
	}
	public void visitOnly(NODE o){
		for(VisitSpec v:beforeMethods){
			maybeVisit(o, v);
		}
		for(VisitSpec v:afterMethods){
			maybeVisit(o, v);
		}
	}
	public void visitRecursively(NODE o){
//		System.out.println(getClass().getSimpleName()+ ".visitRecursively()" + o);
		for(VisitSpec v:beforeMethods){
			maybeVisit(o, v);
		}
		ArrayList<NODE> children = new ArrayList<NODE>(getChildren(o));
		for(NODE child:children){
			long start = System.currentTimeMillis();
			visitRecursively(child);
			long dur = System.currentTimeMillis()-start;
			if(dur>500){
				System.out.println(child);
			}
		}
		for(VisitSpec v:afterMethods){
			maybeVisit(o, v);
		}
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
				long start = System.currentTimeMillis();
				v.visit(this, new Object[]{
					o
				});
				if(System.currentTimeMillis()-start>10){
					System.out.println();
				}
			}
		}
	}
}
