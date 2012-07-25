package org.opaeum.topcased.propertysections.ocl;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.NakedOclViewer;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OpaeumOclFactory;

public abstract class OclBodyComposite extends Composite{
	protected boolean updating = false;
	private final class ErrorHighlighter implements Runnable{
		private boolean stopped;
		private long nextRun = 0;
		public void run(){
			if(!stopped){
				highlightError();
				StyledText t = viewer.getTextWidget();
				if(!(t == null || t.isDisposed()) && (nextRun == 0 || System.currentTimeMillis() >= nextRun)){
					nextRun = System.currentTimeMillis() + 14999;
					Display.getDefault().timerExec(15000, this);
				}
			}
		}
		public void stop(){
			this.stopped = true;
		}
	}
	private final class KeyListener implements Listener{
		String lastVal = null;
		private long lastTextEmpty = 0;
		private long lastKeyChange = System.currentTimeMillis();
		@Override
		public void handleEvent(Event event){
			if(lastVal == null){
				lastVal = viewer.getTextWidget().getText();
			}
			if(event.type == SWT.KeyDown){
				if(event.keyCode == SWT.TAB && tabTo != null){
					event.keyCode = SWT.None;
					event.type = SWT.None;
					event.doit = false;
					viewer.getTextWidget().setSelectionRange(0, 0);
					tabTo.forceFocus();
					if(tabTo instanceof Text){
						((Text) tabTo).selectAll();
					}else if(tabTo instanceof StyledText){
						((StyledText) tabTo).selectAll();
					}
					maybeFireOclChanged();
				}
			}else{
				if(event.type == SWT.KeyUp){
					if(viewer != null && viewer.getTextWidget() != null){
						lastKeyChange = System.currentTimeMillis();
						int delay = 400;
						if(viewer.getTextWidget().getText().isEmpty()){
							lastTextEmpty = System.currentTimeMillis();
							delay = 2000;
						}
						Display.getDefault().timerExec(delay, new Runnable(){
							public void run(){
								if(System.currentTimeMillis() - lastKeyChange >= 350 && viewer != null){
									if(viewer.getTextWidget().getText().isEmpty()){
										if(System.currentTimeMillis() - lastTextEmpty >= 1700){
											maybeFireOclChanged();
										}
									}else{
										maybeFireOclChanged();
									}
								}
							}
						});
					}
				}
			}
		}
		private void maybeFireOclChanged(){
			if(viewer != null && viewer.getTextWidget() != null){
				String text = viewer.getTextWidget().getText();
				if(text != null && !text.trim().equals(lastVal.trim())){
					lastVal = text;
					updating = true;
					fireOclChanged(text);
					updating = false;
				}
			}
		}
	}
	protected NakedOclViewer viewer;
	private OpaeumOclFactory factory;
	protected NamedElement oclBodyOwner;
	public static final String DEFAULT_TEXT = EmfValidationUtil.TYPE_EXPRESSION_HERE;
	public static final String REQUIRED_TEXT = EmfValidationUtil.OCL_EXPRESSION_REQUIRED;
	private OCLDocument document;
	private Control tabTo;
	private KeyListener keyListener;
	protected ErrorHighlighter highlighter;
	public OclBodyComposite(Composite parent,FormToolkit toolkit){
		this(parent, toolkit, SWT.BORDER);
	}
	public OclBodyComposite(Composite parent,FormToolkit toolkit,int textControlStyle){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout(layout);
		viewer = new NakedOclViewer(this, new ColorManager(), SWT.MULTI | textControlStyle);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		document = new OCLDocument();
		factory = new OpaeumOclFactory();
		document.setOCLFactory(factory);
		document.setModelingLevel(ModelingLevel.M1);
		viewer.setInput(document);
		this.keyListener = new KeyListener();
		Listener[] listeners = viewer.getTextWidget().getListeners(SWT.KeyDown);
		for(Listener listener:listeners){
			viewer.getTextWidget().removeListener(SWT.KeyDown, listener);
		}
		viewer.getTextWidget().addListener(SWT.KeyDown, keyListener);
		for(Listener listener:listeners){
			viewer.getTextWidget().addListener(SWT.KeyDown, listener);
		}
		viewer.getTextWidget().addListener(SWT.KeyUp, keyListener);
		manageContentAssist();
		this.highlighter = new ErrorHighlighter();
	}
	protected abstract EditingDomain getEditingDomain();
	protected void fireOclChanged(String text){
		if(!containsExpression(text)){
			// Assume that if we got here, an OclExpression would be required
			text = REQUIRED_TEXT;
			keyListener.lastVal = text;
			getTextControl().setText(text);
		}
		getEditingDomain().getCommandStack().execute(
				SetOclBodyCommand.create(getEditingDomain(), oclBodyOwner, getBodiesFeature(), getLanguagesFeature(), text));
		Display.getDefault().timerExec(1000, highlighter);
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
	protected void setOclContextImpl(NamedElement context,final NamedElement oclBodyowner){
		this.oclBodyOwner = oclBodyowner;
		if(viewer != null){
			if(oclBodyowner != null){
				if(!updating){
					viewer.getTextWidget().setText(getOclText(getBodies(), getLanguages()));
				}
			}else{
				viewer.getTextWidget().setText(DEFAULT_TEXT);
			}
			keyListener.lastVal = viewer.getTextWidget().getText();
			factory.setContext(context);
			document.setOCLContext(EmfBehaviorUtil.getSelf(context));
			manageContentAssist();
			Display.getDefault().timerExec(1000, this.highlighter);
		}
	}
	public void dispose(){
		this.highlighter.stop();
	}
	@SuppressWarnings("unchecked")
	public EList<String> getLanguages(){
		return (EList<String>) oclBodyOwner.eGet(getLanguagesFeature());
	}
	@SuppressWarnings("unchecked")
	public EList<String> getBodies(){
		return (EList<String>) oclBodyOwner.eGet(getBodiesFeature());
	}
	public void highlightError(){
		StyledText t = viewer.getTextWidget();
		if(!(oclBodyOwner == null || t == null || t.isDisposed())){
			ModelWorkspace ws = OpaeumEclipseContext.getCurrentContext().getNakedWorkspace();
			ErrorMap errors = ws.getErrorMap();
			String id = OpaeumEclipseContext.getCurrentContext().getId(oclBodyOwner);
			BrokenElement be = errors.getErrors().get(id);
			if(be != null && be.hasBroken(CoreValidationRule.OCL)){
				Object[] objects = be.getBrokenRules().get(CoreValidationRule.OCL).getParameters();
				Integer i = objects.length == 2 ? ((Integer) objects[1]) - 1 : 0;
				StyleRange[] srs = t.getStyleRanges();
				if(srs.length <= 0){
					srs = new StyleRange[]{new StyleRange(0, t.getText().length(), null, null, SWT.NORMAL)};
				}
				for(StyleRange sr:srs){
					if(i == 0 || (sr.start <= i && sr.start + sr.length > i)){
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
	public static String getOclText(EList<String> bodies,EList<String> languages){
		String result = EmfValueSpecificationUtil.getOclBody(bodies, languages);
		if(result == null){
			result = DEFAULT_TEXT;
		}
		return result;
	}
	public Control getTabTo(){
		return tabTo;
	}
	public void setTabTo(Control tabTo){
		this.tabTo = tabTo;
	}
	protected boolean isOclContext(EObject container){
		return(container instanceof Operation || container instanceof Property || container instanceof Classifier
				|| container instanceof Action || container instanceof InstanceSpecification || container instanceof ValuePin
				|| container instanceof Transition || container instanceof ActivityEdge || container instanceof JoinNode || container instanceof Constraint);
	}
	public abstract EStructuralFeature getBodiesFeature();
	public abstract EStructuralFeature getLanguagesFeature();
	public static boolean containsExpression(String text){
		return text != null && text.trim().length() != 0 && !text.equals(DEFAULT_TEXT);
	}
	public void addVariable(String name,Classifier type){
		factory.addVariable(name, type);
	}
}
