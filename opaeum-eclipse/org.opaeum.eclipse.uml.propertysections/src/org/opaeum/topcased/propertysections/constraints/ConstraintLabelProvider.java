package org.opaeum.topcased.propertysections.constraints;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;

public class ConstraintLabelProvider implements ITableLabelProvider{
	public Image getColumnImage(Object element,int columnIndex){
		return null;
	}
	public String getColumnText(Object element,int columnIndex){
		if(element instanceof Constraint){
			Constraint constraint = (Constraint) element;
			switch(columnIndex){
			case 0:
				return getName(constraint);
			case 1:
				if(constraint.getSpecification() instanceof OpaqueExpression){
					OpaqueExpression a=(OpaqueExpression) constraint.getSpecification();
					if(a.getBodies().size()>0){
						return a.getBodies().get(0);
					}
				}
				return "";
			default:
			}
		}
		return null;
	}
	private String getName(Constraint c){
		String name = c.getName();
		if(name.trim().length() == 0){
			return "no name";
		}
		return name;
	}
	public void addListener(ILabelProviderListener listener){
	}
	public void dispose(){
	}
	public boolean isLabelProperty(Object element,String property){
		return false;
	}
	public void removeListener(ILabelProviderListener listener){
	}
}
