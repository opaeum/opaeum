package net.sf.nakeduml.feature.visit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * VisitorAdapter is an abstract adapter class that traverses a tree starting
 * with the root. Subclasses have to annotate methods that serve as so called
 * "accept" or "visit" methods using the {@link VisitAfter} and the
 * {@link VisitBefore} annotations. A method annotated with VisitAfter will be
 * invoked once all the children of the current node have been visited. Methods
 * annotated with VisitBefore will be invoked before any of the children nodes
 * have been visited. No guarrantee is made regarding the sequence of invocation
 * of two or more methods with the same annotation

 * Contract:
 * Implement the getChildren(NODE) method to allow the traversal from a node to its children
 * 
 * @author abarnard
 * 
 * @param <NODE> This is the common superclass/interface for all nodes in a tree. It is assumed to have the potential to have children
 * @param <ROOT> This it the class of the root node of the tree.
 */
public abstract class VisitorAdapter<NODE, ROOT extends NODE> {
	protected List<VisitSpec> beforeMethods = new ArrayList<VisitSpec>();
	protected List<VisitSpec> afterMethods = new ArrayList<VisitSpec>();
	protected ROOT workspace;

	protected VisitorAdapter() {
		// No polymorphism suport in visit methods
		Method[] methods = getClass().getDeclaredMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent(VisitBefore.class)) {
				beforeMethods.add(new VisitSpec(m, true));
			}
			if (m.isAnnotationPresent(VisitAfter.class)) {
				afterMethods.add(new VisitSpec(m, false));
			}
		}
	}

	/**
	 * Implement this method to provide the logic for traversal from a node to
	 * its children
	 */
	public abstract Collection<? extends NODE> getChildren(NODE parent);

	/**
	 * "Visit" methods can either have one or two parameters. When there is only
	 * one parameter, this parameter is assumed to represent the current node.
	 * When there are two parameters, the second parameter is assumed to be a
	 * "peer" node from mapped model. This is particularly useful when two very
	 * similar models are traversed at the same time, as is often the case with
	 * transformations. When using two parameters, the subclass has to implement
	 * the resolvePeer() method
	 * 
	 * @param o
	 * @param peerClass
	 * @return
	 */
	protected Object resolvePeer(NODE o, Class peerClass) {
		throw new RuntimeException("not implemented");
	}

	public void startVisiting(ROOT root) {
		this.workspace=root;
		visitRecursively(root);
	}

	public void visitRecursively(NODE o) {
		for (VisitSpec v : beforeMethods) {
			maybeVisit(o, v);
		}
		ArrayList<NODE> children = new ArrayList<NODE>(getChildren(o));
		for (NODE child : children) {
			visitRecursively(child);
		}
		for (VisitSpec v : afterMethods) {
			maybeVisit(o, v);
		}
	}

	protected void maybeVisit(NODE o, VisitSpec v) {
		if (v.matches(o)) {
			if (v.resolvePeer()) {
				v.visit(this, o, resolvePeer(o, v.getPeerClass()));
			} else {
				v.visit(this, o);
			}
		}
	}
}
