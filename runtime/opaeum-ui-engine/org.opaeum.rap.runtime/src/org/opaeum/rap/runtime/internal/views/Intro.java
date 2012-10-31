package org.opaeum.rap.runtime.internal.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.graphics.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;
import org.opaeum.annotation.BusinessActor;
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.BusinessRole;
import org.opaeum.rap.runtime.Constants;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.startup.RMSPerspective;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;

public class Intro extends ViewPart{
	public static final String ID = "raptest.view";
	private IOpaeumApplication application;
	private OpaeumRapSession opaeumRapSession;
	public void setFocus(){
		Display display = Display.getCurrent();
		OpaeumRapSession ors= (OpaeumRapSession) RWT.getRequest().getSession(true).getAttribute(OpaeumRapSession.class.getName());
		if(ors != null){
			IBusinessCollaborationBase bc = ors.getApplication().getRootBusinessCollaboration();
			// TODO Make a bit more sophisticated maybe introduced an
			// initiliazed flag
			if(bc == null){
				UserRoleAllocationWizard wizard = new UserRoleAllocationWizard(opaeumRapSession, getApplication());
//				WizardDialog dlg = new WizardDialog(display.getActiveShell(), wizard);
//				if(dlg.open() == Window.OK){
//				}
			}else{
				try{
					getSite().getWorkbenchWindow().getWorkbench().showPerspective(RMSPerspective.ID, getSite().getWorkbenchWindow());
				}catch(WorkbenchException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private static final Color COLOR_WHITE = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	protected IOpaeumApplication getApplication(){
		if(this.application == null){
			Iterator<IOpaeumApplication> iterator = Activator.getDefault().getApplications().values().iterator();
			application = iterator.next();
		}
		return application;
	}
	private final class SwitchPerspective extends SelectionAdapter{
		private static final long serialVersionUID = 7241149101387376500L;
		private final ISelection selection;
		private final boolean openEditor;
		private final Action action;
		private SwitchPerspective(final ISelection selection,final boolean openEditor,final Action action){
			this.selection = selection;
			this.openEditor = openEditor;
			this.action = action;
		}
		@Override
		public void widgetSelected(final SelectionEvent evt){
			try{
				if(selection != null){
					String key = Constants.PRE_SELECTION;
					RWT.getServiceStore().setAttribute(key, selection);
				}
				IWorkbench workbench = PlatformUI.getWorkbench();
				String id = RMSPerspective.class.getName();
				IWorkbenchPage page = getSite().getPage();
				workbench.showPerspective(id, page.getWorkbenchWindow());
				if(openEditor){
				}
				if(action != null){
					action.run();
				}
			}catch(final WorkbenchException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private final FormToolkit toolkit;
	public Intro(){
		toolkit = new FormToolkit(Display.getCurrent());
	}
	public void createPartControl(final Composite parent){
		FormLayout formLayout = new FormLayout();
		parent.setLayout(formLayout);
		Composite body = createBG(parent);
		body.setLayout(new FillLayout());
		body.setBackground(COLOR_WHITE);
		createFormContent(body);
	}
	private Composite createBG(final Composite parent){
		Label lblBackground = new Label(parent, SWT.NONE);
		String imgBanner = Activator.IMG_INTRO_BANNER;
		Activator activator = Activator.getDefault();
		lblBackground.setImage(activator.getImage(imgBanner));
		lblBackground.setBackground(COLOR_WHITE);
		FormData fdBackground = new FormData();
		fdBackground.top = new FormAttachment(0, 0);
		fdBackground.left = new FormAttachment(0, 0);
		fdBackground.right = new FormAttachment(100, 0);
		fdBackground.bottom = new FormAttachment(100, 0);
		lblBackground.setLayoutData(fdBackground);
		String imgOverview = Activator.IMG_INTRO_OVERVIEW;
		Label lblOverview = new Label(parent, SWT.NONE);
		lblOverview.setImage(activator.getImage(imgOverview));
		FormData fdOverview = new FormData();
		fdOverview.top = new FormAttachment(0, 2);
		fdOverview.left = new FormAttachment(0, 4);
		fdOverview.right = new FormAttachment(0, 68);
		fdOverview.bottom = new FormAttachment(0, 66);
		lblOverview.setLayoutData(fdOverview);
		lblOverview.moveAbove(lblBackground);
		lblOverview.setBackground(Graphics.getColor(225, 234, 241));
		String imgOvTxt = Activator.IMG_INTRO_OVERVIEW_TEXT;
		Label lblOvTxt = new Label(parent, SWT.NONE);
		lblOvTxt.setImage(activator.getImage(imgOvTxt));
		lblOvTxt.setBackground(COLOR_WHITE);
		FormData fdOvTxt = new FormData();
		fdOvTxt.top = new FormAttachment(0, 74);
		fdOvTxt.left = new FormAttachment(0, 72);
		fdOvTxt.right = new FormAttachment(100, 0);
		fdOvTxt.bottom = new FormAttachment(0, 106);
		lblOvTxt.setLayoutData(fdOvTxt);
		lblOvTxt.moveAbove(lblBackground);
		Button btnSkipIntro = new Button(parent, SWT.PUSH | SWT.BORDER);
		btnSkipIntro.setToolTipText(RMSMessages.get().Intro_SkipIntro);
		String imgSkipIntro = Activator.IMG_INTRO_SKIP;
		btnSkipIntro.setImage(activator.getImage(imgSkipIntro));
		FormData fdSkipIntro = new FormData();
		fdSkipIntro.top = new FormAttachment(0, 7);
		fdSkipIntro.left = new FormAttachment(0, 80);
		fdSkipIntro.right = new FormAttachment(0, 112);
		fdSkipIntro.bottom = new FormAttachment(0, 41);
		btnSkipIntro.setLayoutData(fdSkipIntro);
		btnSkipIntro.moveAbove(lblBackground);
		btnSkipIntro.setBackground(Graphics.getColor(109, 126, 131));
		SwitchPerspective action = new SwitchPerspective(null, false, null);
		btnSkipIntro.addSelectionListener(action);
		Composite result = new Composite(parent, SWT.NONE);
		FormData fdResult = new FormData();
		fdResult.top = new FormAttachment(0, 106);
		fdResult.left = new FormAttachment(0, 80);
		fdResult.right = new FormAttachment(100, 0);
		fdResult.bottom = new FormAttachment(100, 0);
		result.setLayoutData(fdResult);
		result.moveAbove(lblBackground);
		return result;
	}
	private void createFormContent(final Composite parent){
		ScrolledForm form = new ScrolledForm(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		form.setBackground(COLOR_WHITE);
		form.setText(RMSMessages.get().Intro_Overview);
		Composite body = form.getBody();
		body.setBackground(COLOR_WHITE);
		RowLayout fillLayout = new RowLayout(SWT.HORIZONTAL);
		fillLayout.spacing = 40;
		body.setLayout(fillLayout);
		Composite left = new Composite(body, SWT.NONE);
		createContentLayout(left);
		Composite right = new Composite(body, SWT.NONE);
		createContentLayout(right);
		createActionSections(form, left);
	}
	private void createActionSections(final ScrolledForm form,final Composite left){
		ISelection selection = new StructuredSelection();
		createImageLabelSection(form, left, RMSMessages.get().Intro_SelectionTitle, RMSMessages.get().Intro_SelectionDesc,
				Activator.IMG_INTRO_NAVIGATOR, RMSMessages.get().Intro_SelectionContent, new SwitchPerspective(selection, false, null), true);
		createImageLabelSection(form, left, RMSMessages.get().Intro_EditorTitle, RMSMessages.get().Intro_EditorDesc,
				Activator.IMG_INTRO_CONTEXT_MENU, RMSMessages.get().Intro_EditorContent, new SwitchPerspective(null, true, null), true);
//		NewAction newAction = new NewAction(principal, IProject.class, "", null,opaeumRapSession); //$NON-NLS-1$
//		createImageLabelSection(form, left, RMSMessages.get().Intro_WizardTitle, RMSMessages.get().Intro_WizardDesc,
//				Activator.IMG_INTRO_NEW_PROJECT, RMSMessages.get().Intro_WizardContent, new SwitchPerspective(null, false, newAction), false);
		createImageLabelSection(form, left, RMSMessages.get().Intro_RcsTitle, RMSMessages.get().Intro_RcsDesc, Activator.IMG_INTRO_DATE_PICKER,
				RMSMessages.get().Intro_RcsContent, new SwitchPerspective(null, true, null), false);
	}
	private void createContentLayout(final Composite composite){
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 0;
		layout.bottomMargin = 5;
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		layout.horizontalSpacing = 20;
		layout.verticalSpacing = 20;
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setBackground(COLOR_WHITE);
	}
	private Composite createImageLabelSection(final ScrolledForm form,final Composite client,final String title,final String desc,
			final String imageName,final String explanation,final SelectionListener action,final boolean expanded){
		Composite result = createSection(form, client, title, desc, 2, expanded);
		// using a CLabel instead of a Label is a workaround for Safari
		CLabel image = new CLabel(result, SWT.NONE);
		Activator activator = Activator.getDefault();
		image.setImage(activator.getImage(imageName));
		GridData gdImage = new GridData();
		gdImage.heightHint = 100;
		gdImage.widthHint = 200;
		gdImage.verticalSpan = 2;
		image.setLayoutData(gdImage);
		image.setBackground(COLOR_WHITE);
		Label text = new Label(result, SWT.WRAP);
		text.setText(explanation);
		GridData gdText = new GridData();
		gdText.widthHint = 300;
		gdText.heightHint = 80;
		text.setLayoutData(gdText);
		text.setBackground(COLOR_WHITE);
		Button button = new Button(result, SWT.NONE);
		button.setImage(activator.getImage(Activator.IMG_INTRO_SKIP));
		button.setText(RMSMessages.get().Intro_DoIt);
		GridData gdButton = new GridData();
		gdButton.heightHint = 20;
		gdButton.horizontalAlignment = GridData.END;
		button.setLayoutData(gdButton);
		button.addSelectionListener(action);
		return result;
	}
	private Composite createSection(final ScrolledForm form,final Composite client,final String title,final String desc,final int numColumns,
			final boolean expanded){
		int style = ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR | Section.DESCRIPTION | ExpandableComposite.EXPANDED;
		Section section = toolkit.createSection(client, style);
		section.setExpanded(expanded);
		section.setText(title);
		section.setDescription(desc);
		Composite result = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = numColumns;
		result.setLayout(layout);
		section.setClient(result);
		section.addExpansionListener(new ExpansionAdapter(){
			@Override
			public void expansionStateChanged(final ExpansionEvent e){
				form.reflow(false);
			}
		});
		return result;
	}
	protected Collection<Class<?>> getActorClasses(Environment app){
		Collection<Class<?>> businessActors = app.getMetaInfoMap().getClassesByAnnotation(BusinessActor.class);
		businessActors = new ArrayList<Class<?>>(businessActors);
		Collections.sort((List<Class<?>>) businessActors, new Comparator<Class<?>>(){
			public int compare(Class<?> o1,Class<?> o2){
				return o1.getSimpleName().compareTo(o2.getName());
			}
		});
		return businessActors;
	}
	protected Class<?> getBusinessClass(Environment app){
		for(Class<?> class1:app.getMetaInfoMap().getClassesByAnnotation(BusinessComponent.class)){
			if(class1.getAnnotation(BusinessComponent.class).isRoot()){
				return class1;
			}
		}
		return null;
	}
	protected Collection<Class<?>> getBusinessRoleClasses(Environment app){
		Collection<Class<?>> businessRoles = app.getMetaInfoMap().getClassesByAnnotation(BusinessRole.class);
		businessRoles = new ArrayList<Class<?>>(businessRoles);
		Collections.sort((List<Class<?>>) businessRoles, new Comparator<Class<?>>(){
			public int compare(Class<?> o1,Class<?> o2){
				return o1.getSimpleName().compareTo(o2.getName());
			}
		});
		return businessRoles;
	}
}