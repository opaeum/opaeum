package org.opeum.java.metamodel.annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opeum.java.metamodel.OJElement;
import org.opeum.java.metamodel.OJPathName;

/**
 * A helper class that provides common operations for elements that have annotations, i.e. <br>
 * implementations of the OJAnnotatedElement interface
 * 
 * @author abarnard
 * 
 */
public class AnnotationHelper {
	/**
	 * Adds an annotation value to the source element. if an annotation value of this type already exists, it removes
	 * the existing one and returns it.
	 * 
	 * @param value
	 * @param source
	 * @return
	 */
	public static OJAnnotationValue putAnnotation(OJAnnotationValue value, OJAnnotatedElement source) {
		Set<OJAnnotationValue> sourceSet = source.getAnnotations();
		OJAnnotationValue result = null;
		for (Iterator<OJAnnotationValue> iter = sourceSet.iterator(); iter.hasNext();) {
			OJAnnotationValue ov = iter.next();
			if (ov.getType().equals(value.getType())) {
				iter.remove();
				result = ov;
				break;
			}
		}
		sourceSet.add(value);
		return result;
	}
	public static Set<OJPathName> getImportsFrom(Collection<? extends OJElement> sources) {
		Set<OJPathName> result = new HashSet<OJPathName>();
		for (OJElement v : sources) {
			if (v instanceof OJAnnotatedElement) {
				addTypesUsed((OJAnnotatedElement) v, result);
			}
		}
		return result;
	}
	public static Set<OJPathName> getImportsFrom(OJAnnotatedElement element) {
		Set<OJPathName> result = new HashSet<OJPathName>();
		addTypesUsed(element, result);
		return result;
	}
	private static void addTypesUsed(OJAnnotatedElement element, Set<OJPathName> result) {
		for (OJAnnotationValue v : element.getAnnotations()) {
			result.addAll(v.getAllTypesUsed());
		}
	}
	/**
	 * Adds an annotation to the target element, but only if an annotation value of this type has not been associated
	 * with the target yet. Returns true if the annotation value was added, false if an exeisting value was found for
	 * that annotation
	 * 
	 * @param value
	 * @param target
	 * @return
	 */
	public static boolean maybeAddAnnotation(OJAnnotationValue value, OJAnnotatedElement target) {
		Set<OJAnnotationValue> sourceSet = target.getAnnotations();
		for (Iterator<OJAnnotationValue> iter = sourceSet.iterator(); iter.hasNext();) {
			if (value.getType().equals(iter.next().getType())) {
				return false;
			}
		}
		sourceSet.add(value);
		return true;
	}
	public static OJAnnotationValue getAnnotation(OJAnnotatedElement target, OJPathName path) {
		for (OJAnnotationValue v:target.getAnnotations()) {
			if (v.getType().equals(path)) {
				return v;
			}
		}
		return null;
	}
	public static OJAnnotationValue removeAnnotation(OJAnnotatedElement target, OJPathName type) {
		Iterator<OJAnnotationValue> iter= target.getAnnotations().iterator();
		while(iter.hasNext()){
			OJAnnotationValue v=iter.next();
			if (v.getType().equals(type)) {
				iter.remove();
				return v;
			}
		}
		return null;
	}
	
}
