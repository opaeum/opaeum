package org.opaeum.rap.runtime.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.nebula.widgets.pagination.IPageLoader;
import org.eclipse.nebula.widgets.pagination.PageableController;
import org.eclipse.nebula.widgets.pagination.collections.PageResult;
import org.eclipse.nebula.widgets.pagination.collections.PageResultLoaderList;
import org.eclipse.nebula.widgets.pagination.table.PageableTable;
import org.eclipse.swt.widgets.Table;

public class SortablePageableCheckboxTableViewer extends CheckboxTableViewer implements IPageLoader{
	private PageResultLoaderList<Object> list;
	private List<Object> newOnes = new ArrayList<Object>();
	private PageableTable pageableTable;
	public SortablePageableCheckboxTableViewer(Table table2,PageableTable table){
		super(table2);
		this.pageableTable = table;
	}
	public void init(Object input){
		setInput(input);
		list = new PageResultLoaderList<Object>(new ArrayList<Object>((Collection<?>) input));
		pageableTable.getController().setCurrentPage(0);
	}
	public void refreshPage(){
		newOnes.clear();
		list.getItems().addAll(newOnes);
		pageableTable.refreshPage();
	}
	public Object loadPage(PageableController controller){
		PageResult<Object> loadPage = list.loadPage(controller);
		PageableController ctl = pageableTable.getController();
		if(ctl.getCurrentPage() == (int) ctl.getTotalElements() / ctl.getPageSize()){
			loadPage.getContent().addAll(newOnes);
		}
		return loadPage;
	}
	public void addNew(Object o){
		newOnes.add(o);
		PageableController ctl = pageableTable.getController();
		ctl.setCurrentPage((int) ctl.getTotalElements() / ctl.getPageSize());
	}
	public void delete(Object ... objects){
		list.getItems().removeAll(Arrays.asList(objects));
		refreshPage();
	}
}
