package sort_filter;

import interfaces.IFiltering;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FilteringStrategy<T> implements IFiltering<T> {
    private String filterValue;

    public FilteringStrategy(String filterValue) {
        this.filterValue = filterValue.toLowerCase();
    }
    @Override
    public List<T> filter(List<T> data) {
        List<T> filteredData = new ArrayList<>();

        for (T item : data) {
            if (compareFieldValues(item, filterValue)) {
                filteredData.add(item);
            }
        }
        return filteredData;
    }
    private boolean compareFieldValues(T item, String filterValue) {
        Field[] fields = item.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(item);
                if (fieldValue != null && fieldValue.toString().toLowerCase().contains(filterValue)) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                // Обробка помилки доступу до поля
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return false;
    }
}
