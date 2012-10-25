package org.opaeum.eclipse.uml.editingsupport.bpmprofile;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class DeadlineKindEditingSupport extends EditingDomainEditingSupport{
	public DeadlineKindEditingSupport(ColumnViewer viewer){
		super(viewer, "Deadline Kind", 100);
		
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		ComboBoxViewerCellEditor result = new ComboBoxViewerCellEditor(((TableViewer) viewer).getTable());
		result.setContentProvider(new ArrayContentProvider());
		result.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element){
				EEnumLiteral v=(EEnumLiteral)element;
				if(v==null){
					return "";
				}else{
					return v.getName();
				}
			}
		});
		TimeEvent te = (TimeEvent) element;
		Stereotype st = StereotypesHelper.getStereotype(te, StereotypeNames.DEADLINE);
		EEnum type = (EEnum) st.getDefinition().getEStructuralFeature(TagNames.DEADLINE_KIND).getEType();
		result.setInput(type.getELiterals());
		return result;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		TimeEvent te = (TimeEvent) element;
		Stereotype st = StereotypesHelper.getStereotype(te, StereotypeNames.DEADLINE);
		EObject sa = te.getStereotypeApplication(st);
		return sa.eGet(sa.eClass().getEStructuralFeature(TagNames.DEADLINE_KIND));
	}
	@Override
	protected void setValue(Object element,Object value){
		TimeEvent te = (TimeEvent) element;
		Stereotype st = StereotypesHelper.getStereotype(te, StereotypeNames.DEADLINE);
		Command cmd2 = SetCommand.create(editingDomain, te.getStereotypeApplication(st), st.getDefinition().getEStructuralFeature(TagNames.DEADLINE_KIND), value);
		editingDomain.getCommandStack().execute(cmd2);
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				EEnumLiteral l= (EEnumLiteral) getValue(cell.getElement());
				cell.setText(l.getName());
			}
		};
	}
}
