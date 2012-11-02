package org.opaeum.eclipse.uml.propertysections.ocl;

import java.util.Map.Entry;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;
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
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;
import org.opaeum.metamodel.validation.IValidationRule;
import org.opaeum.metamodel.workspace.OpaeumOcl;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaeumDiagnostic;
import org.opaeum.validation.OclValidator;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OpaeumOclViewer;

public abstract class OclBodyComposite extends Composite{
	protected boolean updating = false;
	private Set<TextChangeListener> listeners = new HashSet<TextChangeListener>();
	public void addTextChangeListener(TextChangeListener l){
		listeners.add(l);
	}
	public void removeTextChangeListener(TextChangeListener l){
		listeners.remove(l);
	}
	@Deprecated
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
				if(text != null && !text.equals(lastVal)){
					lastVal = text;
					updating = true;
					fireOclChanged(text);
					updating = false;
				}
			}
		}
	}
	protected OpaeumOclViewer viewer;
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
		viewer = new OpaeumOclViewer(this, new ColorManager(), SWT.MULTI | textControlStyle);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
	protected EditingDomain getEditingDomain(){
		return null;
	}
	protected void fireOclChanged(String text){
		if(listeners.size() > 0){
			for(TextChangeListener l:listeners){
				l.textChanged(new TextChangedEvent(this.viewer.getTextWidget().getContent()));
			}
		}else{
			if(!containsExpression(text)){
				// Assume that if we got here, an OclExpression would be required
				text = REQUIRED_TEXT;
				keyListener.lastVal = text;
				getTextControl().setText(text);
			}
			getEditingDomain().getCommandStack().execute(buildCommand(text));
		}
		highlightError();
	}
	protected Command buildCommand(String text){
		return SetOclBodyCommand.create(getEditingDomain(), oclBodyOwner, getBodiesFeature(), getLanguagesFeature(), text);
	}
	public StyledText getTextControl(){
		return viewer.getTextWidget();
	}
	@Override
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(viewer.getTextWidget() != null){
			viewer.getTextWidget().setEnabled(enabled);
		}
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
		if(viewer != null && context !=null){
			if(document == null){
				document = new OCLDocument();
				factory = new OpaeumOclFactory(OpaeumEclipseContext.findOpenUmlFileFor(context).getEmfWorkspace().getOpaeumLibrary().getOcl());
				document.setOCLFactory(factory);
				document.setModelingLevel(ModelingLevel.M1);
				viewer.setInput(document);
			}
			this.oclBodyOwner = oclBodyowner;
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
			highlightError();
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
		OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(factory.getContext());
		if(!(oclBodyOwner == null || t == null || t.isDisposed() || ouf == null)){
			OpaeumOcl lib = ouf.getEmfWorkspace().getOpaeumLibrary().getOcl();
			AbstractOclContext ctx = null;
			if(oclBodyOwner instanceof OpaqueExpression){
				ctx = lib.getOclExpressionContext((OpaqueExpression) oclBodyOwner);
			}else if(oclBodyOwner instanceof OpaqueBehavior){
				ctx = lib.getOclBehaviorContext((OpaqueBehavior) oclBodyOwner);
			}else{
				ctx = lib.getOclActionContext((OpaqueAction) oclBodyOwner);
			}
			if(ctx.hasErrors() && ctx.getHelper().getProblems() != null){
				OpaeumDiagnostic od = (OpaeumDiagnostic) ctx.getHelper().getProblems();
				StyleRange[] srs = t.getStyleRanges();
				if(srs.length <= 0){
					srs = new StyleRange[]{new StyleRange(0, t.getText().length(), null, null, SWT.NORMAL)};
				}
				for(StyleRange sr:srs){
					if(od.getEndPosition() == 0 || (sr.start <= od.getStartPosition() && sr.start + sr.length > od.getStartPosition())){
						sr.underline = true;
						sr.underlineStyle = SWT.UNDERLINE_ERROR;
						sr.underlineColor = ColorConstants.red;
					}
				}
				t.setStyleRanges(srs);
				String msg = od.getMessage();
				t.setToolTipText(msg);
			}else{
				String id = EmfWorkspace.getId(ctx.getBodyContainer());
				ouf.getEmfWorkspace().getErrorMap().getErrors().remove(id);
				OclValidator v = new OclValidator();
				v.initialize(ouf.getEmfWorkspace(), ouf.getConfig());
				v.visitRecursively(oclBodyOwner);
				BrokenElement brokenElement = ouf.getEmfWorkspace().getErrorMap().getErrors().get(id);
				if(brokenElement != null){
					StyleRange[] srs = t.getStyleRanges();
					if(srs.length <= 0){
						srs = new StyleRange[]{new StyleRange(0, t.getText().length(), null, null, SWT.NORMAL)};
					}
					for(StyleRange sr:srs){
						sr.underline = true;
						sr.underlineStyle = SWT.UNDERLINE_ERROR;
						sr.underlineColor = ColorConstants.red;
					}
					t.setStyleRanges(srs);
					String message = "";
					Entry<IValidationRule,BrokenRule> brokenRule = brokenElement.getBrokenRules().entrySet().iterator().next();
					String[] split = brokenRule.getKey().getMessagePattern().split("[\\{\\}]");
					if(split.length == 1 && split[0].length() == 0){
						message = brokenRule.getKey().name();
					}else{
						Object[] parameters = brokenRule.getValue().getParameters();
						message = split[0] + " " + oclBodyOwner.getName() + " ";
						for(int i = 2;i < split.length;i++){
							if(i % 2 == 0){
								message += split[i];
							}else if(parameters.length > (i / 2) - 1 && parameters[(i / 2) - 1] instanceof NamedElement){
								message += ((NamedElement) parameters[(i / 2) - 1]).getName();
							}
						}
					}
					t.setToolTipText(message);
				}else{
					StyleRange[] srs = t.getStyleRanges();
					for(StyleRange s:srs){
						s.underline = false;
					}
					t.setStyleRanges(srs);
					t.setToolTipText("");
				}
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
		return(container instanceof Operation || container instanceof Property || container instanceof Classifier || container instanceof Action
				|| container instanceof InstanceSpecification || container instanceof ValuePin || container instanceof Transition || container instanceof ActivityEdge
				|| container instanceof JoinNode || container instanceof Constraint || container instanceof Package);
	}
	public abstract EStructuralFeature getBodiesFeature();
	public abstract EStructuralFeature getLanguagesFeature();
	public static boolean containsExpression(String text){
		return text != null && text.trim().length() != 0 && !text.equals(DEFAULT_TEXT);
	}
	public void addVariable(String name,Classifier type){
		factory.addVariable(name, type);
	}
	public NamedElement getBodyOwner(){
		return oclBodyOwner;
	}
}
