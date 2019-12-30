package gestionnaire.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import gestionnaire.entities.Person;

public class LazyPersonDataModel extends LazyDataModel<Person> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Person> datasource;
	 
    public LazyPersonDataModel(List<Person> datasource) {
    	System.out.println("TAILLE : " + datasource.size());
        this.datasource = datasource;
        this.setWrappedData(this.datasource);
        this.setRowCount(datasource.size());
    }
 
    @Override
    public Person getRowData(String rowKey) {
    	System.out.println("oui");
        for (Person car : datasource) {
            if (car.getId() == Long.parseLong(rowKey)) {
                return car;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Person car) {
    	System.out.println("non");
        return car.getId();
    }
 
    @Override
    public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    	System.out.println("ooooOOOOOOOOOO");
        List<Person> data = new ArrayList<>();
        for(Person p : datasource) {
        	data.add(p);
        }
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
        System.out.println("DataSize : " +  dataSize + " " + datasource.size() + " " + pageSize + " " + first);
 
        //paginate
        if (dataSize > pageSize) {
            try {
            	List<Person> retour = data.subList(first, first + pageSize);
            	System.out.println("Retour size : " + retour.size());
                return data.subList(first, first + pageSize);
            }
            catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}