package org.opaeum.eclipse.uml.propertysections.sql;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposalsService;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
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
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.commands.SetOclBodyCommand;

public abstract class SqlBodyComposite extends Composite{
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
					updating=true;
					fireOclChanged(text);
					updating=false;
				}
			}
		}
	}
	protected SQLSourceViewer viewer;
	protected NamedElement oclBodyOwner;
	public static final String DEFAULT_TEXT = EmfValidationUtil.TYPE_EXPRESSION_HERE;
	public static final String REQUIRED_TEXT = EmfValidationUtil.OCL_EXPRESSION_REQUIRED;
	private Control tabTo;
	private KeyListener keyListener;
	private ErrorHighlighter highlighter;
	public SqlBodyComposite(Composite parent,FormToolkit toolkit){
		this(parent, toolkit, SWT.BORDER);
	}
	public SqlBodyComposite(Composite parent,FormToolkit toolkit,int textControlStyle){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout(layout);
		viewer = new SQLSourceViewer(this, null	, null, false, SWT.MULTI|SWT.BORDER);
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
	protected abstract EditingDomain getEditingDomain();
	protected void fireOclChanged(String text){
		if(!containsExpression(text)){
			// Assume that if we got here, an OclExpression would be required
			text = REQUIRED_TEXT;
			keyListener.lastVal = text;
			getTextControl().setText(text);
		}
		getEditingDomain().getCommandStack().execute(SetOclBodyCommand.create(getEditingDomain(), oclBodyOwner, getBodiesFeature(), getLanguagesFeature(), text));
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
		Document document = new Document();
		viewer.setDocument(document);

		SimpleSQLSourceViewerConfiguration cfg = new SimpleSQLSourceViewerConfiguration();
		DatabaseVendorDefinitionId vendorId=new DatabaseVendorDefinitionId("PostgreSQL", "8.x");
		cfg.setDBProposalsService(new SQLDBProposalsService(new SQLEditorConnectionInfo(vendorId,"openCMGenLocal","opencmgen")));
		viewer.configure(cfg);
		SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByVendorIdentifier(new DatabaseVendorDefinitionId("UNKOWN", "1.0"));
		SQLPartitionScanner _sqlPartitionSanner = new SQLPartitionScanner(factory.getSQLService().getSQLSyntax());
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension3 = (IDocumentExtension3) document;

			IDocumentPartitioner partitioner = new FastPartitioner(_sqlPartitionSanner, SQLPartitionScanner.SQL_PARTITION_TYPES);

			partitioner.connect(document);
			extension3.setDocumentPartitioner(SQLPartitionScanner.SQL_PARTITIONING, partitioner);
		}
		viewer.setDocument(document);

		viewer.enableOperation(SourceViewer.CONTENTASSIST_PROPOSALS, true);
		viewer.enableOperation(SourceViewer.QUICK_ASSIST, true);
		viewer.enableOperation(SourceViewer.CONTENTASSIST_CONTEXT_INFORMATION, true);
	}
	public String getCompositeValue(){
		return viewer.getDocument().get();
	}
	protected void setOclContextImpl(NamedElement context,final NamedElement oclBodyowner){
		this.oclBodyOwner=oclBodyowner;
		viewer.getTextWidget().setText(getOclText(getBodies(), getLanguages()));
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
	}
	public void setCompositeValue(String text){
		viewer.getDocument().set(text);
	}
	public static String getOclText(EList<String> bodies,EList<String> languages){
		for(int i = 0;i < languages.size();i++){
			if(languages.get(i).equalsIgnoreCase("sql")){
				if(bodies.size() > i){
					return bodies.get(i);
				}
			}
		}
		if(bodies.size() == 1){
			return bodies.get(0);
		}
		return DEFAULT_TEXT;
	}
	public Control getTabTo(){
		return tabTo;
	}
	public void setTabTo(Control tabTo){
		this.tabTo = tabTo;
	}
	protected boolean isOclContext(EObject container){
		return(container instanceof Operation || container instanceof Property || container instanceof Classifier || container instanceof Action
				|| container instanceof InstanceSpecification || container instanceof ValuePin || container instanceof Transition || container instanceof ActivityEdge || container instanceof JoinNode || container instanceof Constraint);
	}
	public abstract EStructuralFeature getBodiesFeature();
	public abstract EStructuralFeature getLanguagesFeature();
	public static boolean containsExpression(String text){
		return text != null && text.trim().length() != 0 && !text.equals(DEFAULT_TEXT);
	}

}
