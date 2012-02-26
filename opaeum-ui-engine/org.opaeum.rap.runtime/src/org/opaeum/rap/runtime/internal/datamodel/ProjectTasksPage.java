// Created on 30.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rap.rms.data.ITask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil.Container;
import org.opaeum.rap.runtime.internal.wizards.NewEntityWizard;


public class ProjectTasksPage extends FormPage {
  private static final String TASKS = "Tasks"; //$NON-NLS-1$
  private final ProjectCopy project;
  private Text txtName;
  private Text txtDesc;
  private DateTime dtStart;
  private DateTime dtEnd;
  private DataBindingContext ctx;
  private TaskCopy currentTask;
  private TableViewer taskViewer;
  
  
  private final class TaskLabelProvider extends LabelProvider
    implements ITableLabelProvider
  {
    public Image getColumnImage( final Object element, 
                                 final int columnIndex )
    {
      return null;
    }
    public String getColumnText( final Object element, 
                                 final int columnIndex )
    {
      String result = null;
      ITask task = ( ITask )element;
      IConverter converter = PageUtil.DATE_TO_STRING_CONVERTER;
      switch( columnIndex ) {
        case 0:
          result = task.getName();
        break;
        case 1:
          result = task.getDescription();
        break;
        case 2:
          result = ( String )converter.convert( task.getStartDate() );
        break;
        case 3:
          result = ( String )converter.convert( task.getEndDate() );
        break;
        default:
          Object[] param = new Object[] { new Integer( columnIndex ) };
          String msg
            = NLS.bind( RMSMessages.get().ProjectTasksPage_ColumnIndexNotSupported,
                        param );
          throw new IllegalStateException( msg );
      }
      return result;
    }
  }

  private final class TaskContentProvider
    implements IStructuredContentProvider
  {
    public Object[] getElements( final Object inputElement ) {
      return project.getTasks().toArray();
    }

    public void dispose() {
    }

    public void inputChanged( final Viewer viewer,
                              final Object oldInput, 
                              final Object newInput )
    {
    }
  }

  
  public ProjectTasksPage( final FormEditor editor, 
                           final ProjectCopy project )
  {
    super( editor, TASKS, RMSMessages.get().ProjectTasksPage_Title );
    this.project = project;
  }

  public void init( final IEditorSite site, final IEditorInput input ) {
    super.init( site, input );
    setTitleToolTip( RMSMessages.get().ProjectTasksPage_ToolTip );
  }
  
  protected void createFormContent( final IManagedForm managedForm ) {
    ScrolledForm scrolledForm = managedForm.getForm();
    FormToolkit toolkit = managedForm.getToolkit();
    Composite body
      = PageUtil.createBody( scrolledForm, Activator.IMG_FORM_HEAD_TASKS );
    
    // task detail section
    final Composite detail
      = PageUtil.createSection( scrolledForm, 
                                toolkit, 
                                body, 
                                RMSMessages.get().ProjectTasksPage_TaskDetails,
                                RMSMessages.get().ProjectTasksPage_AddOrEditTask,
                                3,
                                false );
    Container cDetail = new Container( toolkit, detail );
    SelectionAdapter newTask = new SelectionAdapter() {
      @Override
      public void widgetSelected( final SelectionEvent evt ) {
        Display display = Display.getCurrent();
        NewEntityWizard wizard = new NewEntityWizard( project, ITask.class );
        WizardDialog dlg = new WizardDialog( display.getActiveShell(), wizard );
        if( dlg.open() == Window.OK ) {
          taskViewer.refresh();
          StructuredSelection selection
            = new StructuredSelection( wizard.getEntity() );
          taskViewer.setSelection( selection );
        }
      }
    };
    txtName = PageUtil.createLabelTextButton( cDetail,
                                              RMSMessages.get().ProjectTasksPage_Name, 
                                              "", //$NON-NLS-1$
                                              Activator.IMG_NEW_TASK,
                                              newTask );
    txtDesc = PageUtil.createLabelMultiText( cDetail, 
                                             RMSMessages.get().ProjectTasksPage_Description, 
                                             "" ); //$NON-NLS-1$
    dtStart = PageUtil.createLabelDate( cDetail, 
                                        RMSMessages.get().ProjectTasksPage_StartDate, 
                                        null );
    dtEnd = PageUtil.createLabelDate( cDetail, 
                                      RMSMessages.get().ProjectTasksPage_EndDate, 
                                      null );
    // task list section
    Composite tasks
      = PageUtil.createSection( scrolledForm,
                                toolkit,
                                body, 
                                RMSMessages.get().ProjectTasksPage_TaskList,
                                RMSMessages.get().ProjectTasksPage_SelectTaskToEdit, 
                                1,
                                true );
    taskViewer = new TableViewer( tasks );
    taskViewer.setContentProvider( new TaskContentProvider() );
    taskViewer.setLabelProvider( new TaskLabelProvider() );
    Table table = ( Table )taskViewer.getControl();
    taskViewer.setColumnProperties( initColumnProperties( table ) );
    taskViewer.setInput( project );
    taskViewer.addSelectionChangedListener( new ISelectionChangedListener() {
      public void selectionChanged( final SelectionChangedEvent event ) {
        ISelection selection = event.getSelection();
        IStructuredSelection sSelection = ( IStructuredSelection )selection;
        TaskCopy task = ( TaskCopy )sSelection.getFirstElement();
        bindDetailSection( task );
      }
    } );
    table.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetDefaultSelected( final SelectionEvent e ) {
        Section section = ( Section )detail.getParent();
        section.setExpanded( true );
      }
    } );
    GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
    gridData.heightHint = table.getItemHeight() * 10;
    table.setLayoutData( gridData );
    table.setHeaderVisible( true );
  }
  
  private void bindDetailSection( final TaskCopy task ) {
    if( currentTask != null ) {
      taskViewer.refresh( currentTask );
    }
    currentTask = task;
    if( ctx != null ) {
      ctx.dispose();
    }
    ctx = PageUtil.createBindingContext();
    txtName.setText( task.getName() );
    PageUtil.bindText( ctx, task, txtDesc, TaskCopy.DESCRIPTION );
    PageUtil.bindDate( ctx, task, dtStart, TaskCopy.START_DATE );
    PageUtil.bindDate( ctx, task, dtEnd, TaskCopy.END_DATE );
  }

  private String[] initColumnProperties( final Table table ) {
    String[] result = new String[] {
      RMSMessages.get().ProjectTasksPage_TableName, RMSMessages.get().ProjectTasksPage_TableDescription, RMSMessages.get().ProjectTasksPage_TableStart, RMSMessages.get().ProjectTasksPage_TableEnd
    };
    for( int i = 0; i < result.length; i++ ) {
      TableColumn tableColumn = new TableColumn( table, SWT.NONE );
      tableColumn.setText( result[ i ] );
      if( i == 1 ) {
        tableColumn.setWidth( 250 );
      } else {
        tableColumn.setWidth( 100 );
      }
    }
    return result;
  }

  @Override
  public void doSave( final IProgressMonitor monitor ) {
    if( currentTask != null ) {
      taskViewer.refresh( currentTask );
    }
    super.doSave( monitor );
  }
}
