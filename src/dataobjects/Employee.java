package dataobjects;

public class Employee extends Model {
    String name;
    String post;
    int salary;
    String phone;
    String graph;
    String address;
    public Employee(String[] data){
        super(data);
        this.name = data[0];
        this.post = data[1];
        this.salary = Integer.parseInt(data[2]);
        this.phone = data[3];
        this.graph = data[4];
        this.address = data[5];
    }
    public Employee(){
        super();
    }
    @Override
    public int getNumColumns() {
        return 6;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"ПІБ", "Посада", "Зарплата", "Телефон", "Графік", "Адреса"};
    }
}
