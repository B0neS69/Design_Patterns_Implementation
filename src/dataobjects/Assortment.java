package dataobjects;

public class Assortment extends Model {
    private String name;
    private String typeBaking;
    private int price;
    private String descript;
    private String dietetic;
    public Assortment(String[] data) {
        super(data);
        this.name = data[0];
        this.typeBaking = data[1];
        this.price = Integer.parseInt(data[2]);
        this.descript = data[3];
        this.dietetic = data[4];
    }
    public Assortment() {
        super();
    }
    @Override
    public int getNumColumns() {
        return 5;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"Назва", "Тип випічки", "Ціна", "Опис", "Дієтична(+/-)"};
    }
}