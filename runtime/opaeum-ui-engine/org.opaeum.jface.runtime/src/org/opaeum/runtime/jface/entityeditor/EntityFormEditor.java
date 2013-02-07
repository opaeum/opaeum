package org.opaeum.runtime.jface.entityeditor;

import java.util.Date;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.jface.builder.ComponentTreeBuilder;
import org.opaeum.runtime.jface.ui.IEditorInput;
import org.opaeum.runtime.jface.ui.IEditorSite;
import org.opaeum.runtime.jface.ui.OpaeumEditor;
import org.opaeum.runtime.rwt.IOpaeumApplication;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.model.ClassUserInteractionModel;

public class EntityFormEditor extends OpaeumEditor implements IDirtyListener{
	public static final class DummyObservableValue extends AbstractObservableValue{
		Object status;
		public Object getValueType(){
			return Object.class;
		}
		@Override
		protected Object doGetValue(){
			return status;
		}
		protected void doSetValue(Object value){
			status = value;
		}
	}

	private static final long serialVersionUID = 11231512131231L;
	private EntityFormPage[] editorPages;
	private EntityEditorInputJface editorInput;
	public void init(final IEditorSite site,final IEditorInput input) {
		super.init(site, input);
		for(EntityFormPage ep:this.editorPages){
			ep.init(editorInput);
		}
		setPartName(input.getName());
		setTitleImage(input.getImageDescriptor().createImage());
		getEditorInput().setDirtyListener(this);
	}
	@Override
	public void createButtonBarContents(Composite bar){
		Composite headerConentPane = new Composite(bar,SWT.NONE);
		Object rootUimObject = getRootUimObject();
		final DataBindingContext bc = getEditorInput().getDataBindingContext();
		if(rootUimObject instanceof ClassUserInteractionModel){
			GridLayout gl = new GridLayout();
			headerConentPane.setLayout(gl);
			gl.numColumns = 20;
			ClassUserInteractionModel cuim = (ClassUserInteractionModel) rootUimObject;
			List<UimComponent> children = cuim.getPrimaryEditor().getActionBar().getChildren();
			ComponentTreeBuilder builder = new ComponentTreeBuilder(getEditorInput());
			for(UimComponent uimComponent:children){
				builder.addComponent(headerConentPane, uimComponent, bc);
			}
			headerConentPane.getParent().layout();
		}
		IObservableValue errorObservable = new DummyObservableValue();
		// This one listenes to all changes
		ComputedValue computedValue = new ComputedValue(bc.getValidationRealm(), Object.class){
			@Override
			protected Object calculate(){
				messageTable.refresh(getEditorInput().getDataBindingContext());
				return Status.OK;
			}
		};
		bc.bindValue(errorObservable, computedValue/* new AggregateValidationStatus(bc, AggregateValidationStatus.MAX_SEVERITY) */, null, null);
		messageTable.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}


	@Override
	protected void addPages(){
		Object content = getRootUimObject();
		if(content instanceof ClassUserInteractionModel){
			ClassUserInteractionModel cuim = (ClassUserInteractionModel) content;
			List<EditorPage> pages2 = cuim.getPrimaryEditor().getPages();
			editorPages = new EntityFormPage[pages2.size()];
			for(int i = 0;i < editorPages.length;i++){
				editorPages[i] = new EntityFormPage(pages2.get(i));
				addPage(editorPages[i]);
			}
		}
	}
	public Object getRootUimObject(){
		IOpaeumApplication opaeumApplication = getOpaeumApplication();
		Class<IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(getEditorInput().getPersistentObject());
		String uuid = originalClass.getAnnotation(NumlMetaInfo.class).uuid();
		return opaeumApplication.getUserInteractionModel(uuid);
	}
	public IOpaeumApplication getOpaeumApplication(){
		IOpaeumApplication opaeumApplication = getEditorInput().getOpaeumSession().getApplication();
		return opaeumApplication;
	}
	public void doSave(final IProgressMonitor monitor){
		getEditorInput().setDirty(false);
		getEditorInput().getPersistence().flush();
	}
	public boolean isDirty(){
		return getEditorInput().isDirty();
	}
	public void doSaveAs(){
	}
	public void dirtyChanged(boolean dirty){
		if(!dirty && ((HibernateEntity) getEditorInput().getPersistentObject()).getDeletedOn().before(new Date(System.currentTimeMillis() + 1))){
			close(false);
		}
	}
	public EntityEditorInputJface getEditorInput(){
		return editorInput;
	}
	public void setEditorInput(EntityEditorInputJface editorInput){
		this.editorInput = editorInput;
	}

	public void close(boolean saveChanges){
		
	}


}
