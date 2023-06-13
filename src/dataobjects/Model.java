package dataobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Model {
    private String[] data;
    protected Model() {}
    protected Model(String[] data){
        this.data = data;
    }
    public static Model createModel(String[] data, String modelName) {
        try {
            Class<?> class_ = Class.forName(modelName);
            Constructor<?> constructor = class_.getConstructor(String[].class);
            return (Model) constructor.newInstance((Object) data);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Model createNewModel(String modelName) {
        try {
            Class<?> class_ = Class.forName(modelName);
            Constructor<?> constructor = class_.getConstructor();
            return (Model) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  abstract int getNumColumns();
    public  abstract String[] getFieldName();

    public String getFieldName(int num) {
        String[] fieldNames = getFieldName(); // Виклик методу getFieldName()
        if (num >= 0 && num < fieldNames.length) {
            return fieldNames[num];
        } else {
            throw new IndexOutOfBoundsException("Invalid field number: " + num);
        }
    }
}
