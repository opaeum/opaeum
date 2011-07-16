package org.nakeduml.topcased.propertysections;

import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OCLSourceViewer;

public class OclValueComposite extends Composite{
	Composite composite = null;
	private OCLSourceViewer viewer;
	private OCLDocument document;
	private NakedUmlOclFactory factory;
	public OclValueComposite(Composite parent,FormToolkit toolkit){
		super(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		setLayout(layout);
		viewer = new OCLSourceViewer(this, new ColorManager(), SWT.MULTI | SWT.V_SCROLL | SWT.BORDER){
			{
			}
		};
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		document = new OCLDocument();
		factory = new NakedUmlOclFactory();
		document.setOCLFactory(factory);
		document.setModelingLevel(ModelingLevel.M1);
		viewer.setInput(document);
		manageContentAssist();
	}
	public StyledText getTextControl(){
		return viewer.getTextWidget();
	}
	private void manageContentAssist(){
		viewer.enableOperation(SourceViewer.CONTENTASSIST_PROPOSALS, true);
		viewer.enableOperation(SourceViewer.QUICK_ASSIST, true);
		viewer.enableOperation(SourceViewer.CONTENTASSIST_CONTEXT_INFORMATION, true);
	}
	public String getCompositeValue(){
		return document.get();
	}
	public void setValueElement(final Element vp){
		if(viewer != null){
			factory.setContext(vp);
			document.setOCLContext(EmfBehaviorUtil.getSelf((Element) vp));
			manageContentAssist();
		}
	}
	public void setCompositeValue(String text){
		document.set(text);
	}
}
