package dataobjects;

public class Storage extends Model{
    private int code;
    private String raw;
    private String suppliers;
    private int rest;
    private int min_Rest;
    private String unit_of_Measurement;
    public Storage(String[] data){
        super(data);
        this.code=Integer.parseInt(data[0]);
        this.raw=data[1];
        this.suppliers=data[2];
        this.rest=Integer.parseInt(data[3]);
        this.min_Rest=Integer.parseInt(data[4]);
        this.unit_of_Measurement=data[5];
    }
    public Storage(){
        super();
    }
    @Override
    public int getNumColumns() {
        return 6;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"Code", "Raw", "Suppliers", "Rest", "Min_Rest", "Unit_of_Measurement"};
    }
}
