package org.nakeduml.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * Necessary for traversal back up the containmenttree
 */
public @interface CompositionFromOwningObject {
	String attributeToChild();
	String attributeToParent();
	Class parentType();
}
