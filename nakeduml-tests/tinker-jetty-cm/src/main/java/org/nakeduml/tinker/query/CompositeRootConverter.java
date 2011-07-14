package org.nakeduml.tinker.query;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TinkerNode;

@Advanced
@FacesConverter("compositeRootConverter")
public class CompositeRootConverter implements Converter {
	
	@Inject
	List<AbstractEntity> compositeRoots;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		for (AbstractEntity node : this.compositeRoots) {
			if (node.getId().equals(Long.valueOf(value))) {
				return node;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value != null) {
			return ((TinkerNode) value).getId().toString();
		} else {
			return null;
		}
	}
	
}
