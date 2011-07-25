package org.nakeduml.topcased.propertysections;

import net.sf.nakeduml.emf.extraction.AbstractExtractorFromEmf;
import net.sf.nakeduml.emf.workspace.UmlElementMap;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.nakeduml.eclipse.EmfValidationUtil;
import org.nakeduml.topcased.commands.SetOclExpressionCommand;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OCLSourceViewer;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class OclValueComposite extends Composite{
	Composite composite = null;
	private OCLSourceViewer viewer;
	private OCLDocument document;
	private NakedUmlOclFactory factory;
	private Element element;
	public static final String DEFAULT_TEXT = "Type expression here";
	public OclValueComposite(Composite parent,FormToolkit toolkit){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
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
		TextChangeListener listener = new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				Display.getDefault().asyncExec(new Runnable(){
					public void run(){
						try{
							Thread.sleep(500);
						}catch(InterruptedException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						highlightError(element);
					}
				});
			}
			@Override
			public void focusOut(Control control){
			}
			@Override
			public void focusIn(Control control){
			}
		};
		listener.startListeningTo(viewer.getTextWidget());
		manageContentAssist();
	}
	public StyledText getTextControl(){
		return viewer.getTextWidget();
	}
	@Override
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		viewer.getTextWidget().setEnabled(enabled);
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
		this.element=vp;
		if(viewer != null){
			factory.setContext(vp);
			document.setOCLContext(EmfBehaviorUtil.getSelf((Element) vp));
			manageContentAssist();
			highlightError(vp);
		}
	}
	protected void highlightError(final Element vp){
		UmlElementMap map = NakedUmlPlugin.findNakedUmlEditor((NamedElement) vp).getUmlElementMap();
		INakedModelWorkspace ws = map.getTransformationProcess().findModel(INakedModelWorkspace.class);
		ErrorMap errors = ws.getErrorMap();
		BrokenElement be = errors.getErrors().get(AbstractExtractorFromEmf.getId(vp));
		if(be != null && be.hasBroken(CoreValidationRule.OCL)){
			Object[] objects = be.getBrokenRules().get(CoreValidationRule.OCL);
			viewer.getTextWidget().setForeground(ColorConstants.red);
			String message = (String) objects[0];
			viewer.getTextWidget().setToolTipText(message);
		}else{
			viewer.getTextWidget().setForeground(getForeground());
			viewer.getTextWidget().setToolTipText("");
		}
		viewer.getTextWidget().redraw();
	}
	public void setCompositeValue(String text){
		document.set(text);
	}
}
