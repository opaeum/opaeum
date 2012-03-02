package org.opaeum.rap.runtime.internal.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.annotation.BusinessActor;
import org.opaeum.rap.runtime.ClassSelectionEditingSupport;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.SelectionForContact;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessActorBase;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;

import com.google.gdata.data.contacts.ContactEntry;

public class MultiUserRoleAllocationMatrix extends Composite{
	private static final long serialVersionUID = -6736823027694944900L;
	public static final String ID = "raptest.view";
	private TableViewer viewer;
	private boolean initialized;
	private UserRoleAllocationWizardData model;
	private Collection<SelectionForContact> selections;
	public MultiUserRoleAllocationMatrix(Composite parent,int style, UserRoleAllocationWizardData model){
		super(parent, style);
		this.model=model;
		setLayout(new GridLayout(1, true));
		createViewer();
		viewer.setInput(getEntries());
	}
	private Object[] getEntries(){
		List<ContactEntry> entries = model.getContactEntries();
		return builSelectionsForContacts(entries, model.application.getEnvironment()).toArray();
	}

	public void createViewer(){
		int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL;
		viewer = new TableViewer(this, style);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setColumnProperties(new String[]{"name","email"});
		createColumns(this, viewer);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true));
		Button applyButton = new Button(this, SWT.PUSH);
		applyButton.setText("Apply");
		applyButton.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				try{
					testIt();
				}catch(Exception e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		applyButton.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false));
	}
	private TableViewerColumn createTableViewerColumn(String title,int bound,final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(this.viewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	private void createColumns(final Composite parent,final TableViewer viewer){
		createContactDetailsColumns();
		createUserRoleColumns(viewer);
	}
	protected void createUserRoleColumns(final TableViewer viewer){
		TableViewerColumn col;
		IOpaeumApplication application = model.application;
		Collection<Class<?>> cs = getActorClasses(application.getEnvironment());
		int i = 0;
		for(Class<?> class1:cs){
			col = createTableViewerColumn(class1.getSimpleName(), 130, 3 + i);
			col.setEditingSupport(new ClassSelectionEditingSupport(viewer, i));
			final int index = i;
			col.setLabelProvider(new CheckBoxColumnLabelProvider(index));
			i++;
		}
		for(Class<?> br:model.getMultiUserRoleComponentMap().keySet()){
			col = createTableViewerColumn(br.getSimpleName(), 100, 3 + i);
			col.setEditingSupport(new ClassSelectionEditingSupport(viewer, i));
			final int index = i;
			col.setLabelProvider(new CheckBoxColumnLabelProvider(index));
			i++;
		}
	}
	protected void createContactDetailsColumns(){
		TableViewerColumn col = createTableViewerColumn("Full Name", 150, 0);
		col.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
				ContactEntry p = ((SelectionForContact) element).getContact();
				return p.getName() == null ? "" : p.getName().getFullName().getValue();
			}
		});
		col = createTableViewerColumn("e-Mail Address", 200, 1);
		col.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
				ContactEntry p = ((SelectionForContact) element).getContact();
				if(p.getEmailAddresses().size() >= 1){
					return p.getEmailAddresses().get(0).getAddress();
				}
				return "";
			}
		});
		col = createTableViewerColumn("Phone Number", 120, 2);
		col.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
				ContactEntry p = ((SelectionForContact) element).getContact();
				if(p.getPhoneNumbers().size() >= 1){
					return p.getPhoneNumbers().get(0).getPhoneNumber();
				}
				return "";
			}
		});
	}
	public boolean setFocus(){
		return super.setFocus();
	}
	private final class CheckBoxColumnLabelProvider extends ColumnLabelProvider{
		private static final long serialVersionUID = -6548836880309834081L;
		private final int index;
		private CheckBoxColumnLabelProvider(int index){
			this.index = index;
		}
		public String getText(Object element){
			return null;
		}
		@Override
		public Image getImage(Object element){
			Boolean boolean1 = ((SelectionForContact) element).getSelection().get(index);
			if(boolean1){
				return Activator.getDefault().getImage("checked.gif");
			}else{
				return Activator.getDefault().getImage("unchecked.gif");
			}
		}
	}
	public void testIt() throws Exception{
		Iterator<IOpaeumApplication> iterator = Activator.getDefault().getApplications().values().iterator();
		if(iterator.hasNext()){
			IOpaeumApplication app = iterator.next();
			IBusinessNetwork bn = app.getBusinessNetwork();
			IBusinessCollaborationBase sb = app.createRootBusinessCollaboration();
			bn.addToBusinessCollaboration(sb);
			Collection<SelectionForContact> result = this.selections;
			for(SelectionForContact s:result){
				IPersonNode person = null;
				for(Class<?> c:s.getSelectedClasses()){
					if(person == null){
						person = model.createPerson(s.getContact());
					}
					Object newInstance = c.newInstance();
					if(newInstance instanceof IBusinessActorBase){
						IBusinessActorBase ba = (IBusinessActorBase) c.newInstance();
						ba.init(sb);
						ba.addToOwningObject();
						ba.setRepresentedPerson(person);
					}else{
						model.instantiateBusinessRole(person, c);
					}
				}
			}
		}
	}
	public Collection<SelectionForContact> builSelectionsForContacts(Collection<ContactEntry> contactEntries,Environment app){
		Collection<SelectionForContact> result = new ArrayList<SelectionForContact>();
		List<Class<?>> businessActors = getActorClasses(app);
		businessActors.addAll(model.getMultiUserRoleComponentMap().keySet());
		for(ContactEntry contactEntry:contactEntries){
			result.add(new SelectionForContact(contactEntry, (List<Class<?>>) businessActors));
		}
		this.selections = result;
		return result;
	}
	protected List<Class<?>> getActorClasses(Environment app){
		Collection<Class<?>> businessActors = app.getMetaInfoMap().getClassesByAnnotation(BusinessActor.class);
		businessActors = new ArrayList<Class<?>>(businessActors);
		Collections.sort((List<Class<?>>) businessActors, UserRoleAllocationWizardData.getClassNameComparator());
		return (List<Class<?>>) businessActors;
	}

	public Collection<String> test(){
		return Collections.emptySet();
	};
	public static void main(String[] args) throws Exception{
	}
}