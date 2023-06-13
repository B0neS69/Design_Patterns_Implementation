package dataobjects;

public class Order extends Model{
    String client_Named;
    int id_Order;
    String descript;
    String address;
    String delivery_date;
    int price;
    public Order(String[] data){
        super(data);
        this.client_Named = data[0];
        this.id_Order = Integer.parseInt(data[1]);
        this.descript = data[2];
        this.address = data[3];
        this.delivery_date = data[4];
        this.price = Integer.parseInt(data[5]);
    }
    public Order(){
        super();
    }
    @Override
    public int getNumColumns() {
        return 6;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"ПІБ", "Код", "Опис", "Адреса", "Дата доставки", "Ціна"};
    }
}
