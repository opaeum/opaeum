package org.nakeduml.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.inject.Named;

import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;

@Named("dataTableController")
@RequestScoped
public class DataTableController implements Serializable {

	DataTable dataTable;

	@PostConstruct
	public void init() {
		constructDatatable();
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	private void constructDatatable() {
		ExpressionFactory f = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		dataTable = new DataTable();
		dataTable.setId("veryspecial1");
		dataTable.setDynamic(true);
		dataTable.setVar("many");
		dataTable.setLazy(true);
		dataTable.setValueExpression("value", f.createValueExpression(elContext, "#{contextController.contextObject.blackHoleLazy}", LazyDataModel.class));
		dataTable.setPaginator(true);
		dataTable.setRows(10);
		dataTable.setPaginatorTemplate("{CurrentPageReport}  {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
		dataTable.setRowsPerPageTemplate("5,10,15");
		HtmlOutputText header = new HtmlOutputText();
		header.setValue("Displaying Blackholes");
		header.setId("asdasd");
		dataTable.getFacets().put("header", header);
		
		Column column = new Column();
		column.setId("dsadas");
		column.setHeaderText("Blackhole name");
		HtmlOutputText columnsValue = new HtmlOutputText();
		columnsValue.setId("ddddddddd");
		columnsValue.setValueExpression("value", f.createValueExpression(elContext, "#{many.name}", String.class));
		column.getChildren().add(columnsValue);
		dataTable.getChildren().add(column);
	}
	
}
