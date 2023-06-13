import menu.Menu;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"Assortment.txt", "Customers.txt", "Employee.txt", "Order.txt", "SaleStatistics.txt", "Storage.txt", "Supplier.txt"};
        Test test = new Test();
        List<String> unexistfiles = test.checkAllFilesExist(fileNames);
        if(unexistfiles.isEmpty()){
            Menu m = new Menu();
            m.startMenu();
        }else {
            System.out.println("Помилка немає таких файлів: ");
            for (int i = 0; i < unexistfiles.size(); i++) {
                System.out.println(i+1+" "+ unexistfiles.get(i));
            }
        }
    }
}