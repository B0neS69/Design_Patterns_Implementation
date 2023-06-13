package dataobjects;

public class SaleStatistics extends Model{
    private String named;
    private int price;
    private int unitsSold;
    private int income;
    private int receipts;
    public SaleStatistics(String data[]){
        super(data);
        this.named=data[0];
        this.price=Integer.parseInt(data[1]);
        this.unitsSold=Integer.parseInt(data[2]);
        this.income=Integer.parseInt(data[3]);
        this.receipts=Integer.parseInt(data[4]);
    }
    public SaleStatistics(){super();}
    @Override
    public int getNumColumns() {
        return 5;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"Назва", "Ціна", "Продано шт.", "Дохід", "Прибуток"};
    }
}
