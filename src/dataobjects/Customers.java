package dataobjects;

public class Customers extends Model{
    private String name;
    private int order;
    private String sales;
    public Customers (String[] data){
        super(data);
        this.name = data[0];
        this.order = Integer.parseInt(data[1]);
        this.sales = data[2];
    }
    public Customers (){
        super();
    }
    @Override
    public int getNumColumns() {
        return 3;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"Ім'я", "К-сть замовлень", "Знижка"};
    }
}
