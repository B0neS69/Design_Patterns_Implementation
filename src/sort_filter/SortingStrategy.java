package sort_filter;

import interfaces.ISorting;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class SortingStrategy<T> implements ISorting<T> {

    private int num;
    public SortingStrategy(int num) {
    this.num=num;
    }

    public List<T> sort(List<T> dataList, int fieldNum) {
        try {
            Field[] fields = dataList.get(0).getClass().getDeclaredFields();
            Field field = fields[fieldNum];
            if (field != null) {
                field.setAccessible(true);
                Collections.sort(dataList, (o1, o2) -> {
                    try {
                        Comparable fieldValue1 = (Comparable) field.get(o1);
                        Comparable fieldValue2 = (Comparable) field.get(o2);
                        return num * fieldValue1.compareTo(fieldValue2);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
                field.setAccessible(false);

                //System.out.println("List sorted by field: " + field);
            }
            //else {
             //   System.out.println("Field not found.");
            //}
        } catch (Exception e) {
            System.out.println("Error sorting list by field: " + e.getMessage());
        }
        return dataList;
    }
}
