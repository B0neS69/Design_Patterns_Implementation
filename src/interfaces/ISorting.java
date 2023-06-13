package interfaces;

import java.util.List;

public interface ISorting<T> {
    List<T> sort(List<T> dataList, int fieldNum);
}
