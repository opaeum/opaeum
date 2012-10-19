package org.opaeum.eclipse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.name.NameConverter;

public class OpaeumConfigDialog extends TitleAreaDialog{
	private Text txtWorkspaceName;
	private Text txtWorkspaceIdentifier;
	private Text txtCompanyDomain;
	private Button chkGeneratePoms;
	private Button chkAutoSync;
	private CCombo cboSourceFolderStrategy;
	private List lstTransformationSteps;
	private OpaeumConfig config;
	private VersionText txtNewVersionNumber;
	private CheckboxTableViewer localeTableViewer;
	private ComboViewer currencyComboViewer;
	private IContainer modelDir;
	public OpaeumConfigDialog(Shell shell,OpaeumConfig config,IContainer modelDir){
		super(shell);
		this.config = config;
		this.modelDir = modelDir;
	}
	protected Control createContents(Composite parent){
		Control contents = super.createContents(parent);
		setTitle("NakedUml Model Compiler");
		setMessage("Please provide input", IMessageProvider.NONE);
		// Set the image
		return contents;
	}
	protected Control createDialogArea(Composite parent){
		Composite composite = (Composite) super.createDialogArea(parent);
		TabFolder tabFolder = new TabFolder(composite, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TabItem generalItem = new TabItem(tabFolder, SWT.NONE);
		generalItem.setControl(createGeneralTab(tabFolder));
		generalItem.setText("General");
		TabItem i8nItem = new TabItem(tabFolder, SWT.NONE);
		i8nItem.setControl(createI8nTab(tabFolder));
		i8nItem.setText("Internationalization");
		return composite;
	}
	private Control createI8nTab(TabFolder composite){
		Composite panel = new Composite(composite, 0);
		panel.setLayout(new GridLayout(2, true));
		new Label(panel, 0).setText("Supported Locales");
		this.localeTableViewer = new CheckboxTableViewer(new Table(panel, SWT.CHECK));
		this.localeTableViewer.getTable().setHeaderVisible(true);
		this.localeTableViewer.getTable().setLinesVisible(true);
		TableViewerColumn country = new TableViewerColumn(localeTableViewer, SWT.NONE);
		country.getColumn().setResizable(true);
		country.getColumn().setText("Country");
		country.getColumn().setWidth(200);
		country.setLabelProvider(new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText(((Locale) cell.getElement()).getDisplayCountry());
			}
		});
		TableViewerColumn language = new TableViewerColumn(localeTableViewer, SWT.NONE);
		language.setLabelProvider(new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText(((Locale) cell.getElement()).getDisplayLanguage());
			}
		});
		language.getColumn().setText("Language");
		language.getColumn().setWidth(200);
		localeTableViewer.addCheckStateListener(new ICheckStateListener(){
			@Override
			public void checkStateChanged(CheckStateChangedEvent event){
				updateCurrencyCombo();
			}
		});
		localeTableViewer.setContentProvider(new ArrayContentProvider());
		java.util.List<Locale> availableLocales = OpaeumConfig.getAvailableLocales();
		localeTableViewer.setInput(availableLocales);
		localeTableViewer.setCheckedElements(config.getSupportedLocales().toArray());
		localeTableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		localeTableViewer.setInput(availableLocales);
		new Label(panel, 0).setText("Default Currency");
		this.currencyComboViewer = new ComboViewer(new CCombo(panel, SWT.BORDER));
		this.currencyComboViewer.setContentProvider(new ArrayContentProvider());
		updateCurrencyCombo();
		currencyComboViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		return panel;
	}
	protected void updateCurrencyCombo(){
		Object[] checkedElements = localeTableViewer.getCheckedElements();
		java.util.SortedSet<Currency> cc = new TreeSet<Currency>(new Comparator<Currency>(){
			@Override
			public int compare(Currency o1,Currency o2){
				return o1.getCurrencyCode().compareTo(o2.getCurrencyCode());
			}
		});
		for(Object object:checkedElements){
			Locale object2 = (Locale) object;
			if(object2.getCountry().length() == 2){
				Currency c = Currency.getInstance(object2);
				if(c != null){
					cc.add(c);
				}
			}
		}
		ISelection selection;
		if(this.currencyComboViewer.getSelection().isEmpty()){
			selection = new StructuredSelection(config.getDefaultCurrency());
		}else{
			selection = this.currencyComboViewer.getSelection();
		}
		this.currencyComboViewer.setInput(cc);
		this.currencyComboViewer.setSelection(selection);
	}
	protected Composite createGeneralTab(TabFolder composite){
		Composite panel = new Composite(composite, 0);
		panel.setLayout(new GridLayout(2, true));
		new Label(panel, 0).setText("Project Name");
		txtWorkspaceName = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtWorkspaceName.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceName.setText(config.getWorkspaceName());
		new Label(panel, 0).setText("Identifier for project");
		txtWorkspaceIdentifier = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtWorkspaceIdentifier.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceIdentifier.setText(config.getWorkspaceIdentifier());
		new Label(panel, 0).setText("Company domain name");
		txtCompanyDomain = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtCompanyDomain.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtCompanyDomain.setText(getDomainName());
		new Label(panel, 0).setText("New Version Number");
		txtNewVersionNumber = new VersionText(panel, SWT.SINGLE | SWT.BORDER);
		txtNewVersionNumber.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtNewVersionNumber.setVersion(config.getVersion());
		new Label(panel, 0).setText("Generate Maven POMS");
		chkGeneratePoms = new Button(panel, SWT.CHECK);
		chkGeneratePoms.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		chkGeneratePoms.setSelection(config.generateMavenPoms());
		new Label(panel, 0).setText("Compile Automatically");
		chkAutoSync = new Button(panel, SWT.CHECK);
		chkAutoSync.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		chkAutoSync.setSelection(config.synchronizeAutomatically());
		new Label(panel, 0).setText("Source Folder Strategy");
		cboSourceFolderStrategy = new CCombo(panel, SWT.BORDER);
		cboSourceFolderStrategy.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		for(Class<? extends ISourceFolderStrategy> s:OpaeumEclipsePlugin.getDefault().getSourceFolderStrategies()){
			cboSourceFolderStrategy.add(s.getName());
		}
		cboSourceFolderStrategy.setText(config.getSourceFolderStrategy().getClass().getName());
		new Label(panel, 0).setText("Additional Transformation Steps");
		lstTransformationSteps = new List(panel, SWT.MULTI | SWT.BORDER);
		lstTransformationSteps.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		for(Class<? extends ITransformationStep> t:OpaeumEclipsePlugin.getDefault().getTransformationSteps()){
			lstTransformationSteps.add(t.getName());
		}
		String[] items = lstTransformationSteps.getItems();
		for(int i = 0;i < items.length;i++){
			if(config.getAdditionalTransformationSteps().contains(OpaeumConfig.getClass(items[i]))){
				lstTransformationSteps.select(i);
			}
		}
		return panel;
	}
	private String getDomainName(){
		if(config.getMavenGroupId().length() == 0){
			return "acme.com";
		}else{
			StringBuilder sb = new StringBuilder();
			String[] split = config.getMavenGroupId().split("\\.");
			for(int i = split.length - 2;i >= 0;i--){
				sb.append(split[i]);
				if(i != 0){
					sb.append(".");
				}
			}
			return sb.toString();
		}
	}
	@SuppressWarnings({"rawtypes","unchecked"})
	public void okPressed(){
		config.loadDefaults(txtWorkspaceIdentifier.getText());
		String domain = txtCompanyDomain.getText();
		StringBuilder mavenGroup = null;
		StringTokenizer st = new StringTokenizer(domain, ".");
		while(st.hasMoreTokens()){
			if(mavenGroup == null){
				mavenGroup = new StringBuilder(st.nextToken());
			}else{
				mavenGroup.insert(0, '.');
				mavenGroup.insert(0, st.nextToken());
			}
		}
		mavenGroup.append('.');
		mavenGroup.append(NameConverter.separateWordsToCamelCase(txtWorkspaceName.getText()).toLowerCase());
		config.setWorkspaceName(NameConverter.separateWordsToCamelCase(txtWorkspaceName.getText()));
		config.setAdditionalTransformationSteps(new HashSet<String>(Arrays.asList(lstTransformationSteps.getSelection())));
		config.setMavenGroupId(mavenGroup.toString());
		config.setVersion(txtNewVersionNumber.getVersion());
		config.setSourceFolderStrategy(cboSourceFolderStrategy.getText());
		config.setWorkspaceIdentifier(txtWorkspaceIdentifier.getText());
		config.setGenerateMavenPoms(this.chkGeneratePoms.getSelection());
		config.setAutoSync(this.chkAutoSync.getSelection());
		config.setDefaultCurrency((Currency) ((IStructuredSelection) currencyComboViewer.getSelection()).getFirstElement());
		config.setSupportedLocales((java.util.List) Arrays.asList(localeTableViewer.getCheckedElements()));
		config.store();
		if(config.synchronizeAutomatically()){
			addOpaeumBuildCommand();
		}
		super.okPressed();
	}
	private void addOpaeumBuildCommand(){
		try{
			final String BUILDER_ID = "org.opaeum.eclipse.compiler.OpaeumIncrementalProjectBuilder";
			IProjectDescription desc = modelDir.getProject().getDescription();
			ICommand[] commands = desc.getBuildSpec();
			boolean found = false;
			for(int i = 0;i < commands.length;++i){
				if(commands[i].getBuilderName().equals(BUILDER_ID)){
					found = true;
					break;
				}
			}
			if(!found){
				// add builder to project
				ICommand command = desc.newCommand();
				command.setBuilderName(BUILDER_ID);
				ICommand[] newCommands = new ICommand[commands.length + 1];
				// Add it before other builders.
				System.arraycopy(commands, 0, newCommands, 1, commands.length);
				newCommands[0] = command;
				desc.setBuildSpec(newCommands);
				modelDir.getProject().setDescription(desc, null);
			}
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void createButtonsForButtonBar(Composite parent){
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}
	public OpaeumConfig getConfig(){
		return config;
	}
}
