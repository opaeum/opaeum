// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.actions.NewAction;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;
import org.opaeum.runtime.domain.IPersistentObject;

final class DataModelAdapter implements IEntityAdapter{
	private static final String REPOSITORY = "Repository"; //$NON-NLS-1$
	public Object[] getElements(final Object parent){
		// List<IPrincipal> principals = ( ( IDataModel )parent ).getPrincipals();
		// List<IEmployee> employees = ( ( IDataModel )parent ).getEmployees();
		// List<IEntity> result = new ArrayList<IEntity>();
		// result.addAll( principals );
		// result.addAll( employees );
		// return result.toArray();
		return ((IDataModel) parent).getPrincipals().toArray();
	}
	public Object getParent(final Object child){
		return null;
	}
	public Object[] getChildren(final Object parent){
		return getElements(parent);
	}
	public boolean hasChildren(final Object parent){
		return getElements(parent).length > 0;
	}
	public Image getImage(final Object element){
		return getEditorImage(element).createImage();
	}
	public String getText(final Object element){
		return REPOSITORY;
	}
	public String getEditorName(final Object element){
		return REPOSITORY;
	}
	public ImageDescriptor getEditorImage(final Object element){
		Image image = Activator.getDefault().getImage(Activator.IMG_REPOSITORY);
		return ImageDescriptor.createFromImage(image);
	}
	public void createNewMenu(final Object element,final IMenuManager menuManager){
		// MenuManager newMenu = new MenuManager( "New" );
		// NewAction newPrincipal
		// = new NewAction( ( IEntity )element,
		// IPrincipal.class,
		// "Principal",
		// EntityAdapter.getImageDescriptor( EntityImage.PRINCIPAL ) );
		// newMenu.add( newPrincipal );
		// NewAction newEmployee
		// = new NewAction( ( IEntity )element,
		// IEmployee.class,
		// "Employee",
		// EntityAdapter.getImageDescriptor( EntityImage.EMPLOYEE ) );
		// newMenu.add( newEmployee );
		// menuManager.add( newMenu );
		NewAction newPrincipal = new NewAction((IPersistentObject) element, IPrincipal.class, RMSMessages.get().DataModelAdapter_NewPrincipal,
				getEditorImage(element), null);
		menuManager.add(newPrincipal);
	}
	@SuppressWarnings("unchecked")//$NON-NLS-1$
	public IWizardPage createWizardPage(final Object element,final Class destinationType){
		IWizardPage result;
		if(destinationType == IPrincipal.class){
			result = new NewPrincipalWizardPage();
		}else{
			result = new NewEmployeeWizardPage();
		}
		return result;
	}
	@SuppressWarnings("unchecked")//$NON-NLS-1$
	public String createWizardTitle(final Class destinationType){
		String result;
		if(destinationType == IPrincipal.class){
			result = RMSMessages.get().DataModelAdapter_CreatePrincipal;
		}else{
			result = RMSMessages.get().DataModelAdapter_CreateUser;
		}
		return result;
	}
	public void refreshAssociations(final Object element,final StructuredViewer viewer){
	}
	public IPropertySource getPropertySource(final Object element){
		return new IPropertySource(){
			private final static String ID_PRINCIPALS = "Principals"; //$NON-NLS-1$
			private final static String ID_EMPLOYEES = "Employees"; //$NON-NLS-1$
			private final IDataModel dataModel = (IDataModel) element;
			public Object getEditableValue(){
				return null;
			}
			public IPropertyDescriptor[] getPropertyDescriptors(){
				return new IPropertyDescriptor[]{new PropertyDescriptor(ID_PRINCIPALS, RMSMessages.get().DataModelAdapter_Principals),
						new PropertyDescriptor(ID_EMPLOYEES, RMSMessages.get().DataModelAdapter_Employees)};
			}
			public Object getPropertyValue(final Object id){
				Object result = null;
				if(ID_PRINCIPALS == id){
					result = new Integer(dataModel.getPrincipals().size());
				}else if(ID_EMPLOYEES == id){
					result = new Integer(dataModel.getEmployees().size());
				}
				return result;
			}
			public boolean isPropertySet(final Object id){
				return false;
			}
			public void resetPropertyValue(final Object id){
			}
			public void setPropertyValue(final Object id,final Object value){
			}
		};
	}
	public FormPage[] getEditorPages(final Object element,final FormEditor editor){
		return null;
	}
}