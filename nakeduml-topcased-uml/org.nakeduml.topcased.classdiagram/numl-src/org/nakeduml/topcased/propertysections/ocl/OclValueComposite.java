package org.nakeduml.topcased.propertysections.ocl;

import net.sf.nakeduml.emf.extraction.AbstractExtractorFromEmf;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.nakeduml.eclipse.EmfValidationUtil;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.NakedOclViewer;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class OclValueComposite extends Composite{
	public static interface OclChangeListener{
		void oclChanged(String value);
	}
	private NakedOclViewer viewer;
	private NakedUmlOclFactory factory;
	private Element element;
	public static final String DEFAULT_TEXT = EmfValidationUtil.TYPE_EXPRESSION_HERE;
	private long lastKeyChange = System.currentTimeMillis();
	private OclChangeListener listener;
	private OCLDocument document;
	public OclValueComposite(Composite parent,FormToolkit toolkit,OclChangeListener l){
		super(parent, SWT.NONE);
		this.listener = l;
		setBackground(parent.getBackground());
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		setLayout(layout);
		viewer = new NakedOclViewer(this, new ColorManager(), SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		document = new OCLDocument();
		factory = new NakedUmlOclFactory();
		document.setOCLFactory(factory);
		document.setModelingLevel(ModelingLevel.M1);
		viewer.setInput(document);
		viewer.getTextWidget().addKeyListener(new KeyListener(){
			@Override
			public void keyReleased(KeyEvent e){
			}
			@Override
			public void keyPressed(KeyEvent e){
				lastKeyChange = System.currentTimeMillis();
				Display.getDefault().timerExec(500, new Runnable(){
					public void run(){
						if(System.currentTimeMillis() - lastKeyChange >= 400){
							fireOclChanged();
						}
					}
				});
			}
		});
		TextChangeListener textListener = new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				fireOclChanged();
			}
			@Override
			public void focusOut(Control control){
			}
			@Override
			public void focusIn(Control control){
			}
		};
		textListener.startListeningTo(viewer.getTextWidget());
		manageContentAssist();
	}
	protected void fireOclChanged(){
		if(!getTextControl().getText().equals(OclValueComposite.DEFAULT_TEXT)){
			listener.oclChanged(getTextControl().getText());
			Runnable runnable2 = new Runnable(){
				public void run(){
					highlightError(element);
				}
			};
			Display.getDefault().timerExec(500, runnable2);
		}
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
		this.element = vp;
		if(viewer != null){
			factory.setContext(vp);
			document.setOCLContext(EmfBehaviorUtil.getSelf((Element) vp));
			manageContentAssist();
			Runnable runnable2 = new Runnable(){
				public void run(){
					highlightError(element);
				}
			};
			Display.getDefault().timerExec(100, runnable2);
		}
	}
	protected void highlightError(final Element vp){
		StyledText t = viewer.getTextWidget();
		if(!(vp == null || t == null || t.isDisposed())){
			UmlElementCache map = NakedUmlEditor.getCurrentContext().getUmlElementCache();
			INakedModelWorkspace ws = map.getTransformationProcess().findModel(INakedModelWorkspace.class);
			ErrorMap errors = ws.getErrorMap();
			String id = NakedUmlEditor.getCurrentContext().getId(vp);
			BrokenElement be = errors.getErrors().get(id);
			if(be != null && be.hasBroken(CoreValidationRule.OCL)){
				Object[] objects = be.getBrokenRules().get(CoreValidationRule.OCL);
				Integer i = objects.length == 2 ? ((Integer) objects[1])-1: 0;
				StyleRange[] srs = t.getStyleRanges();
				if(srs.length <= 0){
					srs = new StyleRange[]{
						new StyleRange(0, t.getText().length(), null, null, SWT.NORMAL)
					};
				}
				for(StyleRange sr:srs){
					if(sr.start <= i && sr.start + sr.length > i){
						sr.underline = true;
						sr.underlineStyle = SWT.UNDERLINE_ERROR;
						sr.underlineColor = ColorConstants.red;
					}
				}
				t.setStyleRanges(srs);
				String message = (String) objects[0];
				t.setToolTipText(message);
			}else{
				StyleRange[] srs = t.getStyleRanges();
				for(StyleRange s:srs){
					s.underline = false;
				}
				t.setStyleRanges(srs);
				t.setToolTipText("");
			}
			t.redraw();
		}
	}
	public void setCompositeValue(String text){
		document.set(text);
	}
}
