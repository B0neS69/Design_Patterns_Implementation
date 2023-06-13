package data;

import datalist.DataList;
import filehandler.FileHandler;
import interfaces.*;
import dataobjects.*;
import sort_filter.FilteringStrategy;
import sort_filter.SortingOrder;
import sort_filter.SortingStrategy;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class BakeryFacade<T> {
    private final DataList dataList = new DataList();
    private final IFileHandler<T> fileHandler = new FileHandler<>();
    private final Class<T> class_;
    private final Model model;
    private final int numColumns;
    private final String fileName;
    public BakeryFacade(String className){
        try {
            class_ = (Class<T>) Class.forName("dataobjects."+className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.model =Model.createNewModel(class_.getName());
        this.numColumns = model.getNumColumns();
        this.fileName = class_.getSimpleName()+".txt";
    }
    public void loadDataList() {
        dataList.loadDataList(fileName, class_);

    }
    public void outputDataList() {
        fileHandler.printTable(dataList.getList(), numColumns, model);
    }
    public void addDataToList(){
        fileHandler.writeInFile(fileName, model);
    }
    public void removeDataFromList(){
        fileHandler.removeRow(dataList.getList(), fileName);
    }
    // Виклик сортування списку за полем і вибір типу сортування
    public void sortDataListByField() {
        String encoding = "Cp1251";
        Scanner sc = new Scanner(new BufferedInputStream(System.in), encoding);
        System.out.println("Виберіть поле за яким потрібно сортувати: ");
        for (int i = 0; i < numColumns; i++) {
            System.out.println(i + 1 + ". " + model.getFieldName(i));
        }
        int fieldNum = sc.nextInt();
        SortingOrder sortingOrder = getSortingOrderFromUserInput(sc);
        if (sortingOrder != null) {
            dataList.setSortingStrategy(new SortingStrategy(sortingOrder.getValue()));
            dataList.sortListByField(fieldNum - 1);
        } else {
            System.out.println("Неправильне значення для сортування.");
        }
    }
    private SortingOrder getSortingOrderFromUserInput(Scanner sc) {
        SortingOrder sortingOrder = null;
        while (sortingOrder == null) {
            System.out.println("Як сортувати:\n" +
                    "1. За зростанням\n" +
                    "2. За спаданням\n");
            int userInput = sc.nextInt();
            int[] s = {1,-1};
            sortingOrder = SortingOrder.fromValue(s[userInput-1]);
        }
        return sortingOrder;
    }
    //Виклик фільтрування
    public void filterDataList(){
        String encoding = "Cp1251";
        Scanner sc = new Scanner(new BufferedInputStream(System.in), encoding);
        System.out.println("Введіть значення для фільтрації: ");
        String filterValue = sc.nextLine();
        dataList.setFilteringStrategy(new FilteringStrategy<>(filterValue));
        dataList.filterListByValue();
    }

    public void deliverToday(){
        dataList.loadDataList(fileName, class_);
        if(fileHandler.checkDate(dataList.getList())){
            dataList.loadDataList("DeliverToday.txt", class_);
            fileHandler.printTable(dataList.getList(), numColumns, model);
        }
    }
    public void deleteOldDelivery(){
        dataList.loadDataList(fileName, class_);
        fileHandler.checkDate(dataList.getList());
    }

}
