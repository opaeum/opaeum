package org.opaeum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.opaeum.runtime.organization.IBusinessRoleBase;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BusinessComponent{
	boolean isRoot() default false;
	Class<? extends IBusinessRoleBase>[] businessRoles();
	Class<? extends IBusinessRoleBase> adminRole()default IBusinessRoleBase.class;
}
