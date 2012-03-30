package org.opaeum.rap.runtime.internal.editors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uim.swt.UimFieldComposite;
import org.opaeum.uim.swt.UimSwtUtil;

public class OpaeumPage extends FormPage{
	EditorPage opaeumPage;
	private static final String OVERVIEW = "Overview"; //$NON-NLS-1$
	public OpaeumPage(final FormEditor editor,EditorPage opaeumPage){
		super(editor, OVERVIEW, opaeumPage.getName());
		this.opaeumPage = opaeumPage;
	}
	public void init(final IEditorSite site,final IEditorInput input){
		super.init(site, input);
		setTitleToolTip(RMSMessages.get().AssignmentOverviewPage_ToolTip);
	}
	protected void createFormContent(final IManagedForm managedForm){
		ScrolledForm form = managedForm.getForm();
		// FormToolkit toolkit = managedForm.getToolkit();
		// Composite body
		// = PageUtil.createBody( form, Activator.IMG_FORM_HEAD_OVERVIEW );
		Composite body = form.getBody();
		if(opaeumPage.getPanel() instanceof GridPanel){
			GridLayout gl = new GridLayout();
			body.setLayout(gl);
			Composite parent = body.getParent().getParent().getParent();
//			body.setLayoutData(new FormData(400,400));
			GridPanel gp = (GridPanel) opaeumPage.getPanel();
			gl.numColumns = 2;
		}
		EList<UimComponent> children = opaeumPage.getPanel().getChildren();
		for(UimComponent child:children){
			addComponent(body, child);
		}
	}
	public static void addComponent(Composite body,UimComponent comp){
		if(comp instanceof GridPanel){
			GridPanelComposite gpc = new GridPanelComposite(body, SWT.NONE);
			Integer numberOfColumns = ((GridPanel) comp).getNumberOfColumns();
			gpc.getContentPane().setLayout(new GridLayout(numberOfColumns, false));
			EList<UimComponent> children = ((GridPanel) comp).getChildren();
			for(UimComponent child:children){
				addComponent(gpc.getContentPane(), child);
			}
			gpc.layout();
		}else if(comp instanceof UimField){
			if(comp.eContainer() instanceof UimDataTable){
			}else{
				UimFieldComposite uimFieldComposite = new UimFieldComposite(body, SWT.NONE);
				UimField uimField = (UimField) comp;
				UimSwtUtil.populateControl(uimFieldComposite, uimField.getControlKind(), uimField.getOrientation());
				setLayoutData(uimFieldComposite, uimField);
				uimFieldComposite.getLabel().setText(uimField.getName());
				UimSwtUtil.setOrientation(uimField.getOrientation(), uimFieldComposite, uimField.getMinimumLabelWidth());
				uimFieldComposite.layout();
			}
		}else if(comp instanceof BuiltInActionButton){
			BuiltInActionButton btn  = (BuiltInActionButton) comp;
			Button button = new Button(body,SWT.PUSH);
			button.setText(btn.getName());
			setLayoutData(button	, btn);
		}else if(comp instanceof OperationButton){
			OperationButton ob = (OperationButton) comp;
			Button button = new Button(body,SWT.PUSH);
			button.setText(ob.getName());
			setLayoutData(button	, ob);
		}
	}
	public static void setLayoutData(Control uimFieldComposite,Outlayable uimField){
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = Boolean.TRUE.equals(uimField.getFillHorizontally());
		gd.horizontalAlignment = Boolean.TRUE.equals(uimField.getFillHorizontally()) ? GridData.FILL : GridData.CENTER;
		gd.grabExcessVerticalSpace = Boolean.TRUE.equals(uimField.getFillVertically());
		gd.verticalAlignment = Boolean.TRUE.equals(uimField.getFillVertically()) ? GridData.FILL : GridData.CENTER;
		uimFieldComposite.setLayoutData(gd);
	}
}
