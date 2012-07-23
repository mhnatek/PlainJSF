/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.hnatek.plainjsf;

import at.hnatek.utils.DTOComparator;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Markus
 */
@ManagedBean
@SessionScoped
public class MyBean implements Serializable {

    // Init --------------------------------------------------------------------
    private MyData dataItem;
    
    private MyDataDAO dataDAO = new MyDataDAO();
    private List<MyData> dataList;
    private HtmlDataTable dataTable;
    
    private Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();
    
    private String dropdownValue = "";
    
    private String sortField = null;
    private boolean sortAscending = true;

    // Constructor -------------------------------------------------------------
    public MyBean() {
        loadDataList(); 
    }

    // Actions -----------------------------------------------------------------
    private void loadDataList() {
        dataList = dataDAO.list();
        if (sortField != null) {
            Collections.sort(dataList, new DTOComparator(sortField, sortAscending));
        }
    }

    public String editDataItem() {

        // The dataItem is already set by f:setPropertyActionListener.
        // Store the ID of the data item in hidden input element.
        //dataItemId.setValue(dataItem.getId());
        System.out.println("editDataItem: " + dataItem.getId());

        return ""; // Navigation case.
    }
    
    public String doSaveItem() {

        dataItem = null;
        return ""; // Navigation case.
    }
    
    public String addNewDataItem() {
        dataList.add(new MyData());
        return "";
    }

    public String getSelectedItems() {

        // Do your thing with the MyData items in List selectedDataList.
        if (dataList == null) {
            return "";
        }
        for (MyData di : dataList) {
//            System.out.println("selectedIds = " + selectedIds);
//            System.out.println("di.getId() =" + di.getId());
//            System.out.println("get() = " + selectedIds.get(di.getId()));

            Boolean b = selectedIds.get(di.getId());
            if ( b != null && b.booleanValue()) {
                System.out.println("Selected: " + di);
            }
        }
        return ""; // Navigation case.
    }

    public String testBinding() {
        if (dataTable != null) {
            int rows = dataTable.getRows();
            int first = dataTable.getFirst();
            System.out.println("rows=" + rows + ", first=" + first);
        }
        return "";
    }

    public void sortDataList(ActionEvent event) {
        String sortFieldAttribute = getAttribute(event, "sortField");

        // Get and set sort field and sort order.
        if (sortField != null && sortField.equals(sortFieldAttribute)) {
            sortAscending = !sortAscending;
        } else {
            sortField = sortFieldAttribute;
            sortAscending = true;
        }

        // Sort results.
        if (sortField != null) {
            Collections.sort(dataList, new DTOComparator(sortField, sortAscending));
        }
    }

    // Paging Actions ----------------------------------------------------------
    public void pageFirst() {
        dataTable.setFirst(0);
    }

    public void pagePrevious() {
        dataTable.setFirst(dataTable.getFirst() - dataTable.getRows());
    }

    public void pageNext() {
        dataTable.setFirst(dataTable.getFirst() + dataTable.getRows());
    }

    public void pageLast() {
        int count = dataTable.getRowCount();
        int rows = dataTable.getRows();
        dataTable.setFirst(count - ((count % rows != 0) ? count % rows : rows));
    }

    // Getters -----------------------------------------------------------------
    public List<MyData> getDataList() {
        return dataList;
    }

    public MyData getDataItem() {
        return dataItem;
    }

    public Map<Long, Boolean> getSelectedIds() {
        return selectedIds;
    }

    public String getSortField() {
        return sortField;
    }

    public boolean getSortAscending() {
        return sortAscending;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public MyDataDAO getDataDAO() {
        return dataDAO;
    }

    public String getDropdownValue() {
        return dropdownValue;
    }    

    // Setters -----------------------------------------------------------------
    public void setDataItem(MyData dataItem) {
        this.dataItem = dataItem;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void setDataDAO(MyDataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }

    public void setDropdownValue(String dropdownValue) {
        this.dropdownValue = dropdownValue;
    }       
    
    // Helpers -----------------------------------------------------------------
    private static String getAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }
}
