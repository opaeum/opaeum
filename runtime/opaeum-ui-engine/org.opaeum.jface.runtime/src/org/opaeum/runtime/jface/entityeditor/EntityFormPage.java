package org.opaeum.runtime.jface.entityeditor;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.builder.ComponentTreeBuilder;
import org.opaeum.runtime.jface.ui.IEditorInput;
import org.opaeum.runtime.jface.ui.IFormPage;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.panel.GridPanel;

public class EntityFormPage implements IFormPage{
	EditorPage opaeumPage;
	private EntityEditorInputJface input;
	private Composite body;
	public EntityFormPage(EditorPage opaeumPage){
		super();
		this.opaeumPage = opaeumPage;
	}
	public void init(IEditorInput input){
		this.input=(EntityEditorInputJface) input;
	}
	private EntityEditorInputJface getEditorInput(){
		return input;
	}
	@Override
	public IPersistentObject getAdapter(Class<?> class1){
		return null;
	}
	@Override
	public Control getPartControl(){
		return body;
	}
	@Override
	public void createPartControl(Composite c){
		this.body=new Composite(c, SWT.NONE);
		if(opaeumPage.getPanel() instanceof GridPanel){
			GridLayout gl = new GridLayout();
			body.setLayout(gl);
			Integer numberOfColumns = ((GridPanel) opaeumPage.getPanel()).getNumberOfColumns();
			gl.numColumns = numberOfColumns==null?1:numberOfColumns;
		}
		ComponentTreeBuilder builder = new ComponentTreeBuilder(getEditorInput());
		List<UimComponent> children = opaeumPage.getPanel().getChildren();
		for(UimComponent child:children){
			builder.addComponent(body, child,getEditorInput().getDataBindingContext());
		}
		body.layout();
	}
	@Override
	public String getPartName(){
		return this.opaeumPage.getName();
	}

}
