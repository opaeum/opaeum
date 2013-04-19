package org.opaeum.rap.runtime.internal.editors;

import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.runtime.jface.builder.ComponentTreeBuilder;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.panel.GridPanel;

public class OpaeumEditorPage extends FormPage{
	EditorPage opaeumPage;
	private static final String OVERVIEW = "Overview"; //$NON-NLS-1$
	public OpaeumEditorPage(final FormEditor editor,EditorPage opaeumPage){
		super(editor, OVERVIEW, opaeumPage.getName());
		this.opaeumPage = opaeumPage;
	}
	public void init(final IEditorSite site,final IEditorInput input){
		super.init(site, input);
		setTitleToolTip(RMSMessages.get().AssignmentOverviewPage_ToolTip);
	}
	protected void createFormContent(final IManagedForm managedForm){
		ScrolledForm form = managedForm.getForm();
		Composite body = form.getBody();
		if(opaeumPage.getPanel() instanceof GridPanel){
			GridLayout gl = new GridLayout();
			body.setLayout(gl);
			gl.numColumns = ((GridPanel) opaeumPage.getPanel()).getNumberOfColumns();
		}
		ComponentTreeBuilder builder = new ComponentTreeBuilder(getEditorInput());
		List<UimComponent> children = opaeumPage.getPanel().getChildren();
		for(UimComponent child:children){
			builder.addComponent(body, child,getEditorInput().getDataBindingContext());
		}
		body.layout();
	}
	public EntityEditorInput getEditorInput(){
		EntityEditorInput eei = (EntityEditorInput) super.getEditorInput();
		return eei;
	}
}
