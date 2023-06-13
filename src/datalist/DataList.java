package datalist;

import dataobjects.Model;
import interfaces.IFiltering;
import interfaces.ISorting;
import sort_filter.FilteringStrategy;
import sort_filter.SortingStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static dataobjects.Model.createModel;

public class DataList<T> {
    private List<T> dataList;
    private ISorting<T> sortingStrategy;
    private IFiltering<T> filteringStrategy; // Доданий поле для стратегії фільтрації
    public DataList() {
        dataList = new ArrayList<>();
    }
    public List<T> getList() {
        return dataList;
    }
    public void loadDataList(String fileName, Class<T> class_ ) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1251"));
            List<T> dataList = new ArrayList<T>();
            String line;
            //витянує конструктор класу який ми передали з параметрами String[]
            while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    Model model = createModel(data, class_.getName());
                    dataList.add((T) model);
            }
            this.dataList = dataList;
            reader.close();
            System.out.println("File list loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("Error loading file list: " + e.getMessage());
        }
    }
    //СОРТУВАННЯ
    public void setSortingStrategy(SortingStrategy<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
    public void sortListByField(int fieldNum) {
        if (sortingStrategy != null) {
            dataList = sortingStrategy.sort(dataList, fieldNum);
            System.out.println("List sorted successfully.");
        }
    }
    //ФІЛЬТРУВАННЯ
    public void setFilteringStrategy(FilteringStrategy<T> filteringStrategy) {
        this.filteringStrategy = filteringStrategy;
    }
    public void filterListByValue(){
        if (filteringStrategy != null) {
        dataList = filteringStrategy.filter(dataList);
        }
    }
}