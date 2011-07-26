package org.nakeduml.jsf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("tableBean")
@RequestScoped
public class TableBean {

	private static final String[] colors = new String[]{"A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A"};

	private static final String[] manufacturers = new String[]{"A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A"};

	private static final String[] models = new String[]{"A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A"};

	private LazyDataModel<Car> lazyModel;  
	  
    private Car selectedCar;  
  
    public TableBean() {  
  
        lazyModel = new LazyDataModel<Car>() {  
  
			@Override
			public List<Car> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
				// TODO Auto-generated method stub
				List<Car> lazyCars = new ArrayList<Car>();  
				populateLazyRandomCars(lazyCars, pageSize);  
				
				return lazyCars;  
			}  
        };  
  
        /** 
         * In a real application, this number should be resolved by a projection query 
         */  
        lazyModel.setRowCount(100000000);  
  
    }  
  
    public Car getSelectedCar() {  
        return selectedCar;  
    }  
  
    public void setSelectedCar(Car selectedCar) {  
        this.selectedCar = selectedCar;  
    }  
      
    public LazyDataModel<Car> getLazyModel() {  
        return lazyModel;  
    }  
  
    private void populateLazyRandomCars(List<Car> list, int size) {  
        for(int i = 0 ; i < size ; i++) {  
            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
        }  
    }  
  
    private String getRandomModel() {
		return models[(int) (Math.random() * 10)];
		
	}

	private String getRandomColor() {  
        return colors[(int) (Math.random() * 10)];  
    }  
  
    private String getRandomManufacturer() {  
        return manufacturers[(int) (Math.random() * 10)];  
    }  
  
    private int getRandomYear() {  
        return (int) (Math.random() * 50 + 1960);  
    }  	
	
}
