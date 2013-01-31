/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.opaeum.annotation.PropertyConstraint;
import org.opaeum.jface.runtime.Activator;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.strategy.AfterConvertValidator;
import org.opaeum.runtime.strategy.ValidationResult;

public class GenericValidator implements IValidator{
	private AfterConvertValidator afterConvertValidator;
	private JavaTypedElement typedElement;
	private Class<?> parentClass;
	private Validator validator;
	public GenericValidator(Class<?> parentClass,JavaTypedElement typedElement,Validator validator){
		super();
		this.parentClass = parentClass;
		this.typedElement = typedElement;
		this.validator = validator;
		this.afterConvertValidator = typedElement.getStrategyFactory().getStrategy(AfterConvertValidator.class);
	}
	public IStatus validate(Object value){
		List<IStatus> statusList = new ArrayList<IStatus>();
		Set<? extends ConstraintViolation<?>> vs = validator.validateValue(parentClass, typedElement.getName(), value);
		for(ConstraintViolation<?> v:vs){
			statusList.add(new Status(IStatus.ERROR, Activator.ID, v.getMessage()));
		}
		PropertyConstraint[] constraints = typedElement.getConstraints();
		for(PropertyConstraint propertyConstraint:constraints){
			Set<? extends ConstraintViolation<?>> vs2 = validator.validateValue(parentClass, propertyConstraint.name(), value);
			for(ConstraintViolation<?> v:vs2){
				statusList.add(new Status(IStatus.ERROR, Activator.ID, v.getMessage()));
			}
		}
		if(afterConvertValidator != null){
			ValidationResult vr = afterConvertValidator.validate(value);
			if(vr != ValidationResult.SUCCESSES){
				for(String string:vr.getMessages()){
					statusList.add(new Status(IStatus.ERROR, Activator.ID, string));
				}
			}
		}
		if(statusList.isEmpty()){
			return Status.OK_STATUS;
		}else if(statusList.size() == 1){
			return statusList.get(0);
		}else{
			return new MultiStatus(Activator.ID, IStatus.ERROR, statusList.toArray(new IStatus[statusList.size()]), "Validation errors", null);
		}
	}
}
