package dataobjects;

public class Supplier extends Model {
    private String name;
    private String address;
    private String phone;
    public Supplier(String[] data){
        super(data);
        this.name = data[0];
        this.address = data[1];
        this.phone = data[2];
    }
    public Supplier() {
        super();
    }
    @Override
    public int getNumColumns() {
        return 3;
    }
    @Override
    public String[] getFieldName() {
        return new String[]{"Name", "Address", "Phone"};
    }

}
