package org.opaeum.rap.wizards.contacts;

import java.util.Collection;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.opaeum.rap.runtime.widgets.CSingleObjectChooser;
import org.opaeum.runtime.organization.IBusinessRoleBase;

import com.google.gdata.data.contacts.ContactEntry;

public class SingleUserRoleAllocationTable extends Composite{
	public SingleUserRoleAllocationTable(Composite parent,int style,final UserRoleAllocationWizardData model){
		super(parent, style);
		setLayout(new GridLayout(2, false));
		Collection<Class<? extends IBusinessRoleBase>> values = model.getSingleUserRoleComponentMap().keySet();
		for(final Class<? extends IBusinessRoleBase> class1:values){
			Label l = new Label(this, SWT.NONE);
			l.setText(class1.getSimpleName());
			l.setLayoutData(new GridData(200, 30));
			final CSingleObjectChooser c = new CSingleObjectChooser(this, SWT.BORDER);
			c.addSelectionListener(new SelectionListener(){
				public void widgetSelected(SelectionEvent e){
					try{
						model.instantiateBusinessRole(model.createPerson((ContactEntry) c.getSelection()), class1);
					}catch(InstantiationException e1){
						e1.printStackTrace();
					}catch(IllegalAccessException e1){
						e1.printStackTrace();
					}
				}
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
			c.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					return model.calcUserName((ContactEntry) element);
				}
			});
			c.setLayoutData(new GridData(250, 30));
			c.setAdvancedLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					ContactEntry element2 = (ContactEntry) element;
					String prefix;
					if(element2.getName()!=null){
						prefix =element2.getName().getFullName().getValue();
					}else{
						prefix = model.calcUserName(element2);
						
					}
					prefix=prefix+": ";
					if(element2.getPhoneNumbers().size() > 1){
						return prefix + element2.getPhoneNumbers().get(0).getPhoneNumber();
					}else if(element2.getEmailAddresses().size() > 0){
						return prefix + element2.getEmailAddresses().get(0).getAddress();
					}else{
						return prefix;
					}
				}
			});
			c.setChoices(model.getContactEntries().toArray());
		}
	}
}
